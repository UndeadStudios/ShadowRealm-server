package io.exilius.content.combat.death.kill_limiter

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */
enum class KillLimits(
    val npcId: Int,
    val killLimit: Int
) {
    BARREL_CHEST(6342, 50),
}