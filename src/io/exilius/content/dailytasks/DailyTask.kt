package io.exilius.content.dailytasks

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */

class DailyTask(
    val taskName: String,
    val description: String,
    val difficulty: TaskDifficulties,
    var minActionsRequired: Int,
    var minRewardPoints: Int,
    var progress: Int,
    var complete: Boolean
)