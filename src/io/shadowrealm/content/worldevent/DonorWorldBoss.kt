package io.shadowrealm.content.worldevent

import io.shadowrealm.model.entity.npc.NPCSpawning
import io.shadowrealm.model.entity.npc.NpcWalkingType
import io.shadowrealm.model.entity.npc.data.NpcMaxHit
import io.shadowrealm.model.entity.player.PlayerHandler
import io.shadowrealm.model.entity.player.Position
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DonorWorldBoss {
    val npcID = 9049 // Change the ID of the boss
    private val pos = Position(1886, 9309, 0) // Change the position of the boss
    private val respawnMinutes = 1 // Change how many minutes it takes for the boss to respawn
    private val spawnMessage = "A Donor World Boss has spawned!"

    private var queuedDonations = 0 // Counts the amount of donations received

    var isAlive = false

    fun handleDeath() {
        isAlive = !isAlive
    }

    fun initDonorBoss() {
        resetDonations() // Reset the donations
        val executor = Executors.newSingleThreadScheduledExecutor()
        val delay = 0L // Start after 0ms
        val interval = respawnMinutes.toLong() // Run every 60 minutes
        executor.scheduleAtFixedRate({
            if (queuedDonations <= 0) return@scheduleAtFixedRate // Don't spawn if there are no donations
            if (isAlive) return@scheduleAtFixedRate // Don't spawn if the boss is already alive
            removeDonation() // Remove a donation from the queue
            PlayerHandler.executeGlobalMessage(spawnMessage) // Send a message to all players
            // Spawn the NPC
            NPCSpawning.spawn(
                npcID, pos.x, pos.y, pos.height, NpcWalkingType.WALK.ordinal, NpcMaxHit.getMaxHit(npcID), true
            )
        }, delay, interval, TimeUnit.MINUTES)
    }

    // Add a donation to the queue
    fun addDonation() {
        queuedDonations++
    }

    // Remove a donation from the queue
    fun removeDonation() {
        queuedDonations--
    }

    // Reset the donations
    fun resetDonations() {
        queuedDonations = 0
    }

}
