package io.exilius.content.dailytasks

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.exilius.Server
import io.exilius.model.entity.player.Player
import io.exilius.model.entity.player.PlayerHandler
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.concurrent.TimeUnit
import kotlin.random.Random

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
        private const val additionalPointsEnabled = false

        // What's the maximum additional points a task can be given?
        private const val maxAdditionalEasyPoints = 10
        private const val maxAdditionalMediumPoints = 20
        private const val maxAdditionalHardPoints = 30

        // Players can be given additional actions to complete?
        private const val additionalActionsEnabled = false

        // What's the maximum additional actions a task can be given?
        private const val maxAdditionalEasyActions = 200
        private const val maxAdditionalMediumActions = 500
        private const val maxAdditionalHardActions = 1000

        private val maxTaskDuration = TimeUnit.DAYS.toMillis(1)
        private const val taskExpiryMsg = "@red@[Daily Task]@blu@ Your daily task has expired and has been reset!"
        val saveDirectory = Server.getSaveDirectory() + "/daily_task_saves/"

        /** End Configuration **/

        /**
         * Checks if the task has taken the player over 24h
         * and resets it if needed. Runs on logon.
         */
        private fun checkTaskExpiry(player: Player) {
            if (player.bot) return
            if (!dailyTasksEnabled) return
            // If the player has a null or default task, then reset the timer
            val task = player.currentDailyTask
            if (task == null
                || task == DailyTaskData.DEFAULT_TASK_DO_NOT_DELETE.dailyTask
            ) {
                println("Task was null or defult - setting a new one")
                assignNewTask(player)
                return
            }
            // If the task has expired, reset it
            if (player.dailyTaskStartTime + maxTaskDuration < System.currentTimeMillis()) {
                println("player task has expired!")
                player.sendMessage(taskExpiryMsg)
                assignNewTask(player)
            } else if (!task.complete) {
                player.sendMessage("@red@[Daily Task]@blu@ You have an active daily task! Use ::dt to view it!")
            }
        }

        private fun findValidTasks(player: Player): List<DailyTaskData> {
            return DailyTaskData.values().filter { player.playerLevel[it.dailyTask.skill.id] >= it.dailyTask.levelRequirement }
        }

        /**
         * Assign a new task if the current one is null or expired
         */
        fun assignNewTask(player: Player) {
            if (player.bot) return
            if (!dailyTasksEnabled) return
            println("We're trying to assign a new task to player: ${player.loginName}")
            val validTasks = findValidTasks(player).toSet()
            //println("Player ${player.loginName} is eligable for these tasks:")
            //validTasks.forEach{ println("Task: ${it.dailyTask.taskName}")}
            player.currentDailyTask = if (mustBeMaxedForHardTasks && player.maxRequirements(player)) {
                validTasks.random().dailyTask
            } else {
                validTasks.filter { it.dailyTask.difficulty != TaskDifficulties.HARD }.random().dailyTask
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
            if (player.bot) return
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
            if (player.bot) return
            var currentActions = player.currentDailyTask.actionsRequired
            val difficulty = player.currentDailyTask.difficulty
            currentActions += when (difficulty) {
                TaskDifficulties.EASY -> Random.nextInt(maxAdditionalEasyActions)
                TaskDifficulties.MEDIUM -> Random.nextInt(maxAdditionalMediumActions)
                TaskDifficulties.HARD -> Random.nextInt(maxAdditionalHardActions)
            }
            player.currentDailyTask.actionsRequired = currentActions
        }

        fun checkSaveDir() {
            val saveDirectory = File(saveDirectory)
            if (!saveDirectory.exists()) {
                println("Daily Task Saves directory didn't exist. Creating now.")
                saveDirectory.mkdirs()
            }
        }
        fun savePlayerTaskData(player: Player) {
            if (player.bot) return
            try {
                checkSaveDir()
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
            if (player.bot) return
            val loadedTask: DailyTask
            println("LOADING THE PLAYER TASK DATA FOR ${player.loginName}")
            try {
                checkSaveDir()
                println("REACHED THE TRY LOOP")
                val fr = FileReader(saveDirectory + player.loginName + ".json")
                val fileParser = JsonParser()
                val builder = GsonBuilder().create()
                val reader = fileParser.parse(fr) as JsonObject
                loadedTask = builder.fromJson(reader["task-data"], DailyTask::class.java)
                println("Loaded task stuff = ${loadedTask.taskName}, ${loadedTask.progress} / ${loadedTask.actionsRequired}")
                println("File reader stuff = ${fr.readLines()}")
                println("Loaded task for ${player.loginName}. Task name loaded was: ${loadedTask.taskName}")
                if (loadedTask.taskName == DailyTaskData.DEFAULT_TASK_DO_NOT_DELETE.dailyTask.taskName) {
                    println("Task was default - choosing a new one")
                    assignNewTask(player)
                } else {
                    player.currentDailyTask = loadedTask
                    println("Players current task has been set from the loader. Now: ${player.currentDailyTask.taskName} progress: ${loadedTask.progress} / ${loadedTask.actionsRequired}")
                    checkTaskExpiry(player)
                }
            } catch (exception: Exception) {
                println("An error occurred when loading daily task file... Error: $exception")
                assignNewTask(player)
            }
            println("successfully loaded task data for ${player.loginName}... Task: ${player.currentDailyTask.taskName}")
        }

        fun handleProgress(player: Player, progress: Int) {
            if (player.bot) return
            val task = player.currentDailyTask
            if (task.complete) return
            if (task == null) {
                println("Task was null ffs")
                return
            }
            println("Current task progress is ${task.progress} and we are adding $progress to it.")
            if (task.progress >= task.actionsRequired) {
                completeTask(player)
                return
            }
            player.currentDailyTask.progress += progress
            println("New task progress is ${player.currentDailyTask.progress}.")
        }

        private fun completeTask(player: Player) {
            if (player.bot) return
            val task = player.currentDailyTask ?: return
            player.currentDailyTask.complete = true
            player.dailyTaskPoints += task.minRewardPoints
            player.sendMessage("@red@[Daily Task]@blu@ You have completed your task and were awarded ${task.minRewardPoints} points!")
            PlayerHandler.executeGlobalMessage("@red@[Daily Task]@blu@ ${player.loginName} just completed a @red@${task.difficulty.toString().lowercase()}@blu@ daily task!")
        }
    }
}