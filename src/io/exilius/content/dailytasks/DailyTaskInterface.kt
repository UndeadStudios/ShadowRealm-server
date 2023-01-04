package io.exilius.content.dailytasks

import io.exilius.model.entity.player.Player
import java.time.Duration

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */

class DailyTaskInterface {

    companion object {

        /** Interface ID **/
        const val interfaceId = 27269

        /** String IDs **/
        private const val taskName = 27299
        private const val description = 27300
        private const val difficulty = 27301
        private const val pointsOnCompletion = 27302
        private const val currentProgress = 27303
        private const val nextTaskTime = 27304

        /** Button ID **/
        private const val exitBtn = 106136

        private fun timeUntilReset(player: Player): String {
            val timeLastReset = player.dailyTaskStartTime
            val oneDay = 86400000L
            val nextResetTime: Long = Duration.ofMillis((timeLastReset + oneDay) - System.currentTimeMillis()).seconds
            //println("Time was last reset: ${miniMe.lastTimeReset}")
            val hour = nextResetTime / 3600
            val minute = (nextResetTime % 3600) / 60
            val second = nextResetTime % 60
            return String.format(
                "%02d:%02d:%02d", hour, minute, second
            )
        }

        private fun sendTaskData(player: Player) {
            if (player.openInterface != interfaceId) return
            val task = player.currentDailyTask ?: return
            player.pa.sendString(taskName, task.taskName)
            player.pa.sendString(description, task.description)
            player.pa.sendString(difficulty, task.difficulty.toString())
            player.pa.sendString(pointsOnCompletion, task.minRewardPoints.toString())
            player.pa.sendString(currentProgress, "${task.progress} / ${task.actionsRequired}")
            player.pa.sendString(nextTaskTime, timeUntilReset(player))
        }

        fun open(player: Player) {
            sendTaskData(player)
            player.pa.showInterface(interfaceId)
        }


    }
}