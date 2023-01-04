package io.exilius.content.dailytasks

import io.exilius.content.skills.Skill

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */

class DailyTask(
    val taskName: String,
    val difficulty: TaskDifficulties,
    var actionsRequired: Int,
    var minRewardPoints: Int,
    var skill: Skill,
    var levelRequirement: Int,
    var progress: Int,
    var complete: Boolean
)