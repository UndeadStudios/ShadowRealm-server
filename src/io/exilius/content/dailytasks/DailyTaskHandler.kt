package io.exilius.content.dailytasks

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.exilius.Server
import io.exilius.model.entity.player.Player
import io.exilius.model.entity.player.PlayerHandler
import java.io.FileReader
import java.io.FileWriter
import kotlin.random.Random
import java.util.concurrent.TimeUnit

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */

class DailyTaskHandler {

    companion object {

        /** Configuration **/
        // Disable this to stop daily tasks
        private const val dailyTasksEnabled = true

        /** Reward point and difficulty modifiers **/
        // Players must be maxed to be given hard daily tasks
        private const val mustBeMaxedForHardTasks = true

        // Players can be given additional reward points when completing daily tasks?
        private const val additionalPointsEnabled = true

        // What's the maximum additional points a task can be given?
        private const val maxAdditionalEasyPoints = 10
        private const val maxAdditionalMediumPoints = 20
        private const val maxAdditionalHardPoints = 30

        // Players can be given additional actions to complete?
        private const val additionalActionsEnabled = true

        // What's the maximum additional actions a task can be given?
        private const val maxAdditionalEasyActions = 200
        private const val maxAdditionalMediumActions = 500
        private const val maxAdditionalHardActions = 1000

        private val maxTaskDuration = TimeUnit.DAYS.toMillis(1)
        private const val taskExpiryMsg = "@red@[Daily Task]@blu@ Your daily task has expired and has been reset!"
        private val saveDirectory = Server.getSaveDirectory() + "/daily_task_saves/"

        /** End Configuration **/

        /**
         * Checks if the task has taken the player over 24h
         * and resets it if needed. Runs on logon.
         */
        private fun checkTaskExpiry(player: Player) {
            if (!dailyTasksEnabled) return
            // If the player has a null or default task, then reset the timer
            val task = player.currentDailyTask
            if (task == null
                || task == DailyTaskData.DEFAULT_TASK_DO_NOT_DELETE.dailyTask
            ) {
                assignNewTask(player)
                return
            }
            // If the task has expired, reset it
            if (player.dailyTaskStartTime + maxTaskDuration < System.currentTimeMillis()) {
                player.sendMessage(taskExpiryMsg)
                assignNewTask(player)
            } else if (!task.complete) {
                player.sendMessage("@red@[Daily Task]@blu@ You have an active daily task! Use ::dt to view it!")
            }
        }

        /**
         * Assign a new task if the current one is null or expired
         */
        private fun assignNewTask(player: Player) {
            if (!dailyTasksEnabled) return
            player.currentDailyTask = if (mustBeMaxedForHardTasks && player.maxRequirements(player)) {
                DailyTaskData.values().random().dailyTask
            } else {
                DailyTaskData.values().filter { it.dailyTask.difficulty != TaskDifficulties.HARD }.random().dailyTask
            }
            // If the player was randomly given the default task, we will re-roll
            if (player.currentDailyTask == DailyTaskData.DEFAULT_TASK_DO_NOT_DELETE.dailyTask) {
                assignNewTask(player)
                return
            }
            if (additionalPointsEnabled) addAdditionalPoints(player)
            if (additionalActionsEnabled) addAdditionalActions(player)
            player.sendMessage("@red@[Daily Task]@blu@ You have been assigned a new ${player.currentDailyTask.difficulty} daily task! View it using ::dt")
            player.dailyTaskStartTime = System.currentTimeMillis()
            savePlayerTaskData(player)
        }

        private fun addAdditionalPoints(player: Player) {
            var currentPts = player.currentDailyTask.minRewardPoints
            val difficulty = player.currentDailyTask.difficulty
            currentPts += when (difficulty) {
                TaskDifficulties.EASY -> Random.nextInt(maxAdditionalEasyPoints)
                TaskDifficulties.MEDIUM -> Random.nextInt(maxAdditionalMediumPoints)
                TaskDifficulties.HARD -> Random.nextInt(maxAdditionalHardPoints)
            }
            player.currentDailyTask.minRewardPoints = currentPts
        }

        private fun addAdditionalActions(player: Player) {
            var currentActions = player.currentDailyTask.minActionsRequired
            val difficulty = player.currentDailyTask.difficulty
            currentActions += when (difficulty) {
                TaskDifficulties.EASY -> Random.nextInt(maxAdditionalEasyActions)
                TaskDifficulties.MEDIUM -> Random.nextInt(maxAdditionalMediumActions)
                TaskDifficulties.HARD -> Random.nextInt(maxAdditionalHardActions)
            }
            player.currentDailyTask.minActionsRequired = currentActions
        }

        fun savePlayerTaskData(player: Player) {
            try {
                val fw = FileWriter(saveDirectory + player.loginName + ".json")
                val builder = GsonBuilder().setPrettyPrinting().create()
                val obj = JsonObject()
                obj.add("task-data", builder.toJsonTree(player.currentDailyTask))
                fw.write(builder.toJson(obj))
                fw.close()
            } catch (_: Exception) {
            }
        }

        fun loadPlayerTaskDataOnLogin(player: Player) {
            val loadedTask: DailyTask?
            try {
                val fr = FileReader(saveDirectory + player.loginName + ".json")
                val fileParser = JsonParser()
                val builder = GsonBuilder().create()
                val reader = fileParser.parse(fr) as JsonObject
                if (reader.has("task-data")) {
                    loadedTask = builder.fromJson(reader["task-data"], DailyTask::class.java)
                } else {
                    assignNewTask(player)
                    return
                }

                if (loadedTask == null) {
                    assignNewTask(player)
                } else if (loadedTask.taskName == DailyTaskData.DEFAULT_TASK_DO_NOT_DELETE.dailyTask.taskName) {
                    println("Task was default - choosing a new one")
                    assignNewTask(player)
                } else {
                    player.currentDailyTask = loadedTask
                    checkTaskExpiry(player)
                }
            } catch (exception: Exception) {
                println(exception)
            }
            println("successfully loaded task data for ${player.loginName}... Task: ${player.currentDailyTask.taskName}")
        }

        fun handleProgress(player: Player, progress: Int) {
            val task = player.currentDailyTask ?: return
            task.progress += progress
            if (task.progress >= task.minActionsRequired) {
                completeTask(player)
                return
            }
        }

        private fun completeTask(player: Player) {
            val task = player.currentDailyTask ?: return
            player.dailyTaskPoints += task.minRewardPoints
            player.sendMessage("@red@[Daily Task]@blu@ You have completed your task and were awarded ${task.minRewardPoints} points!")
            PlayerHandler.executeGlobalMessage("@red@[Daily Task]@blu@ ${player.loginName} just completed a @red@${task.difficulty.toString().lowercase()}@blu@ daily task!")
        }
    }
}