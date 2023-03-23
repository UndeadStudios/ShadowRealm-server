package io.shadowrealm.content.battle_pass

import io.shadowrealm.content.battle_pass.BattlePassHandler.Companion.getDaysUntilEndDate
import io.shadowrealm.content.battle_pass.BattlePassHandler.Companion.xpUntilNextLevel
import io.shadowrealm.model.entity.player.Player
import io.shadowrealm.model.entity.player.PlayerAssistant
import io.shadowrealm.model.items.GameItem
import io.shadowrealm.model.items.ImmutableItem


class BattlePassInterface(val player: Player) {

    companion object {
        /** Interface IDs **/
        const val interfaceID = 27725

        /** Button IDs **/
        const val closeBtn = 108120
        val freeLevelBtns = IntRange(108083, 108092).step(3).toList()
        val premiumLevelBtns = IntRange(108095, 108104).step(3).toList()
        const val purchaseBtn = 108121
        const val nextBtn = 108110
        const val prevBtn = 108109
        val otherBtns = intArrayOf(nextBtn, prevBtn, purchaseBtn, closeBtn)

        /** String IDs **/
        val freeLevelStrs = IntRange(27732, 27741).step(3).toList()
        val premiumLevelStrs = IntRange(27744, 27753).step(3).toList()
        const val pageStr = 27729
        const val levelStr = 27765
        const val timeStr = 27766
        const val xpStr = 27767

        /** Item Container **/
        const val freeItemContainer = 27755
        const val premItemContainer = 27756

        private fun getLevelString(player: Player): String {
            val obtained = BattlePassConfig.XP_FOR_LEVELS.toList().filter { it <= player.battlePassXP }.size
            val remaining = BattlePassConfig.XP_FOR_LEVELS.size
            return "$obtained/$remaining"
        }

        fun sendData(player: Player, pageSent: Int) {
            println("page send: $pageSent")
            var page = pageSent
            if (page > 14) {
                page = 14
                return
            }
            if (page < 0) {
                page = 0
                return
            }
            //println("new page: $page")
            player.battlePassPage = page
            player.pa.sendString(pageStr, "Page ${page + 1}")
            player.pa.sendString(timeStr, getDaysUntilEndDate())
            player.pa.sendString(levelStr, getLevelString(player))
            player.pa.sendString(xpStr, xpUntilNextLevel(player))
            val freeRewards = BattlePassHandler.freeRewards
            val premRewards = BattlePassHandler.premiumRewards
            val freeClaimed = player.battlePassFreeRwdsClaimed
            val premClaimed = player.battlePassPremiumRwdsClaimed
            var index = 0
            freeLevelStrs.iterator().forEach { freeBtn ->
                val metXpReq = player.battlePassXP >= BattlePassConfig.XP_FOR_LEVELS[index + (4 * page)]
                val color = if (metXpReq) "@yel@Claim!" else "@red@Level ${index + 1 + (4 * page)}"
                val text = if (freeClaimed[index + (4 * page)]) "@gre@Claimed!" else color
                player.pa.sendString(freeBtn, text)
                index++
            }
            premiumLevelStrs.iterator().forEach { premBtn ->
                val metXpReq = player.battlePassXP >= BattlePassConfig.XP_FOR_LEVELS[index - 4 + (4 * page)]
                val color = if (metXpReq) "@yel@Claim!" else "@red@Level ${index - 3 + (4 * page)}"
                val text = if (premClaimed[index - 4 + (4 * page)]) "@gre@Claimed!" else color
                player.pa.sendString(premBtn, text)
                index++
            }
            val freeItems = mutableListOf<GameItem>()
            val premiumItems = mutableListOf<GameItem>()

            for ((index1, _) in (1..4).withIndex()) {
                freeItems.add(freeRewards[index1 + (4 * page)])
                premiumItems.add(premRewards[index1 + (4 * page)])
            }
            PlayerAssistant.sendItems(player, freeItemContainer, freeItems, 4)
            PlayerAssistant.sendItems(player, premItemContainer, premiumItems, 4)

        }

        fun open(player: Player) {
            sendData(player, 0)
            player.pa.showInterface(interfaceID)
        }

        fun claimRewards(buttonID: Int, player: Player) {
            if (!freeLevelBtns.contains(buttonID) && !premiumLevelBtns.contains(buttonID)) return
            // Even - Free Rewards
            val reward: GameItem?
            val xpReq: Long?
            var rewardIndex = (buttonID - 108083) / 3
            //println("player.battlePassPage = ${player.battlePassPage}")
            if (freeLevelBtns.contains(buttonID)) {
                val modifiedIndex = rewardIndex + (player.battlePassPage * 4)
                //println("mod index = $modifiedIndex")
                xpReq = BattlePassConfig.XP_FOR_LEVELS[modifiedIndex]
                //println("XP REQ = $xpReq")
                if (player.battlePassXP < xpReq) {
                    player.sendMessage("You haven't got enough XP yet!")
                    return
                }
                if (player.battlePassFreeRwdsClaimed[modifiedIndex]) {
                    player.sendMessage("You have already claimed that item!")
                    return
                }
                //println("Free Btn Index clicked $rewardIndex")
                reward = BattlePassHandler.freeRewards[modifiedIndex]
                //println("Free reward for index = ${reward.id} x ${reward.amount} name: ${reward.def.name}")
                player.battlePassFreeRwdsClaimed[modifiedIndex] = true
                if (player.inventory.hasRoomInInventory(ImmutableItem(reward.id).withAmount(reward.amount))) {
                    player.items.addItem(reward.id, reward.amount)
                } else {
                    player.items.addItemToBankOrDrop(reward.id, reward.amount)
                    player.sendMessage("You don't have enough inventory so the reward was sent to bank.")
                }
            } else if (premiumLevelBtns.contains(buttonID)) {
                if (!player.battlePassPremiumUnlocked) {
                    player.sendMessage("You must purchase a battle pass to claim this premium reward!")
                    return
                }
                rewardIndex-=4
                val modifiedIndex = rewardIndex + (player.battlePassPage * 4)
               // println("Prem mod index: $modifiedIndex")
                xpReq = BattlePassConfig.XP_FOR_LEVELS[modifiedIndex]
                //println("Prem XP req: $xpReq")
                if (player.battlePassXP < xpReq) {
                    player.sendMessage("You haven't got enough XP yet!")
                    return
                }
                if (player.battlePassPremiumRwdsClaimed[modifiedIndex]) {
                    player.sendMessage("You have already claimed that item!")
                    return
                }
                //println("Premium Btn Index clicked $rewardIndex")
                reward = BattlePassHandler.premiumRewards[modifiedIndex]
                player.battlePassPremiumRwdsClaimed[modifiedIndex] = true
                if (player.inventory.hasRoomInInventory(ImmutableItem(reward.id).withAmount(reward.amount))) {
                    player.items.addItem(reward.id, reward.amount)
                } else {
                    player.items.addItemToBankOrDrop(reward.id, reward.amount)
                    player.sendMessage("You don't have enough inventory so the reward was sent to bank.")
                }
            } else {
                return
            }
            sendData(player, player.battlePassPage)


        }

        fun handleButton(buttonID: Int, player: Player): Boolean {
            if (!freeLevelBtns.contains(buttonID) && !premiumLevelBtns.contains(buttonID) && !otherBtns.contains(buttonID)) return false
            when (buttonID) {
                closeBtn -> player.pa.removeAllWindows()
                nextBtn -> sendData(player, player.battlePassPage + 1)
                prevBtn -> sendData(player, player.battlePassPage - 1)
                purchaseBtn -> player.pa.sendFrame126("https://exiliusosrsps.everythingrs.com/services/store/", 12000)
                else -> claimRewards(buttonID, player)
            }
            //println("Button being handled in BattlePass interface: $buttonID")
            return true
        }
    }

}