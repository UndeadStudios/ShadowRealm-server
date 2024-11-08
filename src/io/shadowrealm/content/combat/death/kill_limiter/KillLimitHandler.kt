package io.shadowrealm.content.combat.death.kill_limiter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.shadowrealm.Server
import io.shadowrealm.model.definitions.NpcDef
import io.shadowrealm.model.entity.Entity
import io.shadowrealm.model.entity.player.Player
import io.shadowrealm.model.entity.player.Right
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */
class KillLimitHandler {

    companion object {

        private val saveDirectory = Server.getSaveDirectory() + "/kill_limit_data/"

        private val killLimits = HashMap<Int, Int>()

        const val killHandlerEnabled = true

        private const val playerCapModifierEnabled = true

        /**
         * What multiplier should players receive to their daily kill caps
         * based on their player rights?
         */
        private fun killCapMultiplier(player: Player): Double {
            if (!playerCapModifierEnabled) return 1.0
            // Staff
            if (player.rights.isOrInherits(Right.OWNER)) return 1000.0
            if (player.rights.isOrInherits(Right.GAME_DEVELOPER)) return 1000.0
            if (player.rights.isOrInherits(Right.ADMINISTRATOR)) return 1000.0
            if (player.rights.isOrInherits(Right.MODERATOR)) return 1.0
            if (player.rights.isOrInherits(Right.HELPER)) return 1.0
            // Donators
            if (player.rights.isOrInherits(Right.Divine_Club)) return 3.5
            if (player.rights.isOrInherits(Right.Mythic_Club)) return 3.0
            if (player.rights.isOrInherits(Right.ONYX_CLUB)) return 2.7
            if (player.rights.isOrInherits(Right.DIAMOND_CLUB)) return 2.3
            if (player.rights.isOrInherits(Right.LEGENDARY_DONATOR)) return 2.0
            if (player.rights.isOrInherits(Right.EXTREME_DONOR)) return 1.6
            if (player.rights.isOrInherits(Right.REGULAR_DONATOR)) return 1.3
            // Game Modes
            if (player.rights.isOrInherits(Right.IRONMAN)) return 1.0 // No reason to limit irons too heavily
            if (player.rights.isOrInherits(Right.ULTIMATE_IRONMAN)) return 1.0 // No reason to limit irons too heavily
            if (player.rights.isOrInherits(Right.HC_IRONMAN)) return 1.0
            if (player.rights.isOrInherits(Right.ROGUE_IRONMAN)) return 1.0
            if (player.rights.isOrInherits(Right.ROGUE_HARDCORE_IRONMAN)) return 1.0
            if (player.rights.isOrInherits(Right.GROUP_IRONMAN)) return 1.0
            if (player.rights.isOrInherits(Right.HITBOX)) return 1.0 // I don't know what HITBOX is
            if (player.rights.isOrInherits(Right.YOUTUBER)) return 1.3
            if (player.rights.isOrInherits(Right.OSRS)) return 1.0
            if (player.rights.isOrInherits(Right.ROGUE)) return 1.0
            return 1.0
        }

        fun loadKillLimits() {
            if (!killHandlerEnabled) return
            KillLimits.values().forEach { npc ->
                killLimits[npc.npcId] = npc.killLimit
            }
            println("Loaded kill limits successfully:")
            killLimits.forEach { println("NPC: ${it.key}, Kills: ${it.value}") }
        }

        fun allowAttackRequest(player: Player, entityUnderAttack: Entity): Boolean {
            if (entityUnderAttack.isPlayer || !killHandlerEnabled) return true
            val npcUnderAttack = entityUnderAttack.asNPC().npcId
            if (!killLimits.containsKey(npcUnderAttack)) return true
            var killLimit = killLimits[npcUnderAttack]
            if (killLimit == null) {
                println("There was an issue with the kill limit for NPC ID: $npcUnderAttack")
                player.sendMessage("There was an issue the kill limit for this NPC. Alert staff. NPC ID: $npcUnderAttack")
                return false
            }
            killLimit = (killLimit * killCapMultiplier(player)).toInt()
            50 * 1.0
            val currentKills = player.killTracker[npcUnderAttack] ?: 0
            if (currentKills >= killLimit) {
                player.sendMessage("You have hit the kill limit for ${NpcDef.forId(npcUnderAttack).name}!")
                return false
            }
            return true
        }

        fun handleNpcDeath(player: Player, npcKilled: Int) {
            if (!killHandlerEnabled) return
            if (!killLimits.containsKey(npcKilled)) return
            player.killTracker.putIfAbsent(npcKilled, 0)
            player.killTracker[npcKilled] = player.killTracker[npcKilled]!! + 1
            val mapper = ObjectMapper()
            mapper.writeValue(File(saveDirectory + player.loginName + ".json"), player.killTracker)
        }

        fun savePlayerKillData(player: Player) {
            if (!killHandlerEnabled) return
            val mapper = ObjectMapper()
            mapper.writeValue(File(saveDirectory + player.loginName + ".json"), player.killTracker)
        }

        fun loadKilLData(player: Player) {
            if (!killHandlerEnabled) return
            val file = File(saveDirectory + player.loginName + ".json")
            if (!file.exists()) return
            val mapper = ObjectMapper()
            player.killTracker = mapper.readValue(file,
                object : TypeReference<HashMap<Int, Int>>() {}
            )
        }

        /**
         * Checks the last reset time when the player logs in
         */
        fun checkResetTimer(player: Player) {
            if (player.lastKillReset + TimeUnit.DAYS.toMillis(1) < System.currentTimeMillis()) {
                player.lastKillReset = System.currentTimeMillis()
                player.sendMessage("@blu@Your daily kill caps have been reset!")
                resetPlayerKillData(player)
            }
        }

        private fun resetPlayerKillData(player: Player) {
            player.killTracker.clear()
        }

    }
}