package io.exilius.content.battle_pass

import io.exilius.content.battle_pass.season_data.BattlePassSeasons
import io.exilius.model.entity.player.Player
import io.exilius.model.entity.player.PlayerHandler
import io.exilius.model.items.GameItem
import io.exilius.util.Misc
import io.exilius.util.Misc.secondsToFormattedCountdown
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class BattlePassHandler {

    companion object {

        var freeRewards = arrayListOf<GameItem>()

        var premiumRewards = arrayListOf<GameItem>()

        private var season = getBattlePassSeason()

        private var endDate = getSeasonEndDate()

        private fun getBattlePassSeason(): Int {
            return BattlePassSeasons.values().firstOrNull {
                it.startDate.isBefore(LocalDateTime.now()) && !LocalDateTime.now().isAfter(
                    it.startDate.plusDays(
                        BattlePassConfig.SEASON_LENGTH
                    )
                )
            }?.ordinal ?: -1
        }

        fun sendInfoToPlayer(player: Player) {
            player.sendMessage("Your season is ${player.battlePassSeason}")
            player.sendMessage("Your XP is ${player.battlePassXP}")
            player.sendMessage("Your level is ${player.battlePassLevel}")
            player.sendMessage("Free rewards claimed: ${player.battlePassFreeRwdsClaimed.filter { it }.size} / ${player.battlePassFreeRwdsClaimed.size}")
            player.sendMessage("Premium rewards claimed: ${player.battlePassPremiumRwdsClaimed.filter { it }.size} / ${player.battlePassPremiumRwdsClaimed.size}")
            player.sendMessage("Season end date is ${getSeasonEndDate()}")
        }

        private fun loadRewards() {
            if (season < 0) {
                println("Error loadingRewards. Season is $season!")
            }
            freeRewards = BattlePassSeasons.values()[season].freeRewards
            premiumRewards = BattlePassSeasons.values()[season].premiumRewards
            println("Successfully loaded season $season of the battle pass. Free Rwds: ${freeRewards.size} Premium Rwds: ${premiumRewards.size}.")
        }

        fun getSeasonEndDate() : String {
            if (season < 0) return "Error"
            return BattlePassSeasons.values()[season].startDate.plusDays(BattlePassConfig.SEASON_LENGTH).toString()
        }

        fun getDaysUntilEndDate() : String {
            val endDate = LocalDateTime.parse(getSeasonEndDate()) ?: return "Error"
            return secondsToFormattedCountdown(LocalDateTime.now().until(endDate, ChronoUnit.SECONDS))
        }

        fun checkSeasonExpiry(){
            if (LocalDateTime.now().isAfter(LocalDateTime.parse(endDate))) {
                PlayerHandler.executeGlobalMessage("The current battle pass season has ended!")
                season = getBattlePassSeason()
                endDate = getSeasonEndDate()
                println("End date $endDate")
                println("Season $season")
            }
        }

        fun handleServerStartup() {
            season = getBattlePassSeason()
            endDate = getSeasonEndDate()
            checkSeasonExpiry()
            loadRewards()
        }

        fun handleTicketRedemption(player: Player, itemid: Int) : Boolean {
            if (itemid != BattlePassConfig.BATTLE_PASS_ITEM_ID) return false
            if (!player.items.hasItemOnOrInventory(BattlePassConfig.BATTLE_PASS_ITEM_ID)) return false
            if (player.battlePassPremiumUnlocked && player.battlePassSeason == season) {
                player.sendMessage("@red@You've already unlocked this seasons premium track!")
                return true
            }
            player.items.deleteItem(BattlePassConfig.BATTLE_PASS_ITEM_ID, player.items.getInventoryItemSlot(BattlePassConfig.BATTLE_PASS_ITEM_ID), 1)
            player.battlePassPremiumUnlocked = true
            player.sendMessage("You've have unlocked this seasons premium track!")
            return true
        }

        fun xpUntilNextLevel(player: Player): String {
            val xpUntilNext = BattlePassConfig.XP_FOR_LEVELS.toList().find { it >= player.battlePassXP }
                ?.minus(player.battlePassXP)
            return if (xpUntilNext != null) Misc.getValueWithoutRepresentation(xpUntilNext) else "Err"
        }


        fun handleXpGain(player: Player, xp: Long) {
            val currentLvl = player.battlePassLevel
            val newLevel = BattlePassConfig.XP_FOR_LEVELS.toList().filter { it <= player.battlePassXP + xp }.size
            if (newLevel > currentLvl) {
                player.sendMessage("@blu@You have unlocked level $newLevel of your battle pass!")
                player.battlePassLevel = newLevel
            }
            player.battlePassXP += xp
        }

        fun handlePlayerLogin(player: Player) {
            val playersCurrentSeason = player.battlePassSeason
            val currentSeason = getBattlePassSeason()
            if (currentSeason < 0) {
                println("The current battle pass season is null!")
                return
            }
            if (playersCurrentSeason != currentSeason) {
                println("Players current season ($playersCurrentSeason) does not match the expected season ($currentSeason).")
                resetPlayerBattlePass(player)
            }
        }

        fun resetPlayerBattlePass(player: Player) {
            player.battlePassXP = 0
            player.battlePassLevel = 0
            player.battlePassSeason = getBattlePassSeason()
            player.battlePassFreeRwdsClaimed = BooleanArray(20)
            player.battlePassPremiumRwdsClaimed = BooleanArray(20)
            player.battlePassPremiumUnlocked = false
            println("BATTLE PASS RESET FOR ${player.loginName}")
        }

    }
}
