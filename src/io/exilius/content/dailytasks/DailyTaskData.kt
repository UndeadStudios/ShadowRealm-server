package io.exilius.content.dailytasks

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
    DEFAULT_TASK_DO_NOT_DELETE(DailyTask("Default Task", "Description", TaskDifficulties.EASY, 0, 3, 0, complete = false)),
    CHOP_OAK_TREES(DailyTask("Chop Oak Trees", "Chop Oak Trees anywhere in Exilius!", TaskDifficulties.EASY, 3, 1, 0, complete = false)),
    TEST_EASY_TASK1(DailyTask("An easy task1", "Description", TaskDifficulties.EASY, 3, 3, 0, complete = false)),
    TEST_EASY_TASK2(DailyTask("An easy task2", "Description", TaskDifficulties.EASY, 3, 3, 0, complete = false)),
    TEST_EASY_TASK3(DailyTask("An easy task3", "Description", TaskDifficulties.EASY, 500, 3, 0, complete = false)),
    TEST_MEDIUM_TASK(DailyTask("A medium task", "Description", TaskDifficulties.MEDIUM, 3, 3, 0, complete = false)),
    TEST_MEDIUM_TASK1(DailyTask("A medium task1", "Description", TaskDifficulties.MEDIUM, 3, 3, 0, complete = false)),
    TEST_MEDIUM_TASK2(DailyTask("A medium task2", "Description", TaskDifficulties.MEDIUM, 3, 3, 0, complete = false)),
    TEST_MEDIUM_TASK3(DailyTask("A medium task3", "Description", TaskDifficulties.MEDIUM, 3, 3, 0, complete = false)),
    TEST_HARD_TASK(DailyTask("A hard task", "Description", TaskDifficulties.HARD, 3, 3, 0, complete = false)),
    TEST_HARD_TASK1(DailyTask("A hard task1", "Description", TaskDifficulties.HARD, 3, 3, 0, complete = false)),
    TEST_HARD_TASK2(DailyTask("A hard task2", "Description", TaskDifficulties.HARD, 3, 3, 0, complete = false)),
    TEST_HARD_TASK3(DailyTask("A hard task3", "Description", TaskDifficulties.HARD, 3, 3, 0, complete = false)),

}