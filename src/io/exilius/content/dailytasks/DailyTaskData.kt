package io.exilius.content.dailytasks

import io.exilius.content.skills.Skill

/**
 * @author Flub
 * https://www.rune-server.ee/members/365422-flub/
 * Created for Exilius ~ 2023
 */

/**
 * Holds all the daily tasks.
 * Leave progress as 0 and complete as false
 */
enum class DailyTaskData(
    val dailyTask: DailyTask
) {
    DEFAULT_TASK_DO_NOT_DELETE(
        DailyTask(
            "Default Task",
            TaskDifficulties.EASY,
            0,
            3,
            Skill.AGILITY,
            1,
            0,
            complete = false
        )
    ),
    SMITHING_IRON_BARS(
        DailyTask(
            "Smith 300 Iron Bars",
            TaskDifficulties.EASY,
            300,
            1,
            Skill.SMITHING,
            15,
            0,
            complete = false
        )
    ),
    CHOP_OAK_TREES(
        DailyTask(
            "Chop 300 Oak Trees",
            TaskDifficulties.EASY,
            300,
            1,
            Skill.WOODCUTTING,
            15,
            0,
            complete = false
        )
    ),
    TEST_MEDIUM_TASK(
        DailyTask(
            "A medium task",
            TaskDifficulties.MEDIUM,
            3,
            3,
            Skill.WOODCUTTING,
            0,
            0,
            complete = false
        )
    ),
    TEST_HARD_TASK(
        DailyTask(
            "A hard task",
            TaskDifficulties.HARD,
            3,
            3,
            Skill.WOODCUTTING,
            0,
            0,
            complete = false
        )
    ),


}