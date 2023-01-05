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
            7,
            Skill.AGILITY,
            1,
            0,
            complete = false
        )
    ),
    SMITHING_Steel_BARS(
        DailyTask(
            "Smith 500 Steel Bars",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.SMITHING,
            30,
            0,
            complete = false
        )
    ),
    SMITHING_IRON_BARS(
        DailyTask(
            "Smith 300 Iron Bars",
            TaskDifficulties.EASY,
            300,
            7,
            Skill.SMITHING,
            15,
            0,
            complete = false
        )
    ),
    SMITHING_RUNE_BARS(
        DailyTask(
            "Smith 1000 Rune Bars",
            TaskDifficulties.HARD,
            1000,
            28,
            Skill.SMITHING,
            85,
            0,
            complete = false
        )
    ),
    CHOP_OAK_TREES(
        DailyTask(
            "Chop 300 Oak Trees",
            TaskDifficulties.EASY,
            300,
            7,
            Skill.WOODCUTTING,
            15,
            0,
            complete = false
        )
    ),
    CHOP_NORMAL_TREE(
        DailyTask(
            "Cut 250 normal logs",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.WOODCUTTING,
            0,
            0,
            complete = false
        )
    ),
    CHOP_MAPLE_TREE(
        DailyTask(
            "Cut 750 Maple logs",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.WOODCUTTING,
            45,
            0,
            complete = false
        )
    ),
    CHOP_YEW_TREE(
        DailyTask(
            "Cut 750 yew logs",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.WOODCUTTING,
            60,
            0,
            complete = false
        )
    ),
    CHOP_MAGIC_TREE(
        DailyTask(
            "Cut 500 Magic logs",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.WOODCUTTING,
            75,
            0,
            complete = false
        )
    ),
    CHOP_REDWOOD_TREE(
        DailyTask(
            "Cut 500 Redwood logs",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.WOODCUTTING,
            90,
            0,
            complete = false
        )
    ),
    BURN_OAK_LOGS(
        DailyTask(
            "Burn 250 Oak logs",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.FIREMAKING,
            15,
            0,
            complete = false
        )
    ),
    BURN_MAPLE_LOGS(
        DailyTask(
            "Burn 250 Maple logs",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.FIREMAKING,
            45,
            0,
            complete = false
        )
    ),
    BURN_MAGIC_LOGS(
        DailyTask(
            "Burn 400 Magic logs",
            TaskDifficulties.HARD,
            400,
            28,
            Skill.FIREMAKING,
            75,
            0,
            complete = false
        )
    ),
    MINE_IRON_ORE(
        DailyTask(
            "Mine 250 Iron ore",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.MINING,
            15,
            0,
            complete = false
        )
    ),
    MINE_COPPER_ORE(
        DailyTask(
            "Mine 250 Copper ore",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.MINING,
            1,
            0,
            complete = false
        )
    ),
    MINE_COAL_ORE(
        DailyTask(
            "Mine 500 Coal Ore",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.MINING,
            30,
            0,
            complete = false
        )
    ),
    MINE_GOLD_ORE(
        DailyTask(
            "Mine 500 Gold Ore",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.MINING,
            40,
            0,
            complete = false
        )
    ),
    MINE_ADAMANT_ORE(
        DailyTask(
            "Mine 500 Adamant Ore",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.MINING,
            70,
            0,
            complete = false
        )
    ),
    MINE_RUNE_ORE(
        DailyTask(
            "Mine 500 Runite Ore",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.MINING,
            85,
            0,
            complete = false
        )
    ),
    MINE_AMATHYST_ORE(
        DailyTask(
            "Mine 500 Amathyst Ore",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.MINING,
            92,
            0,
            complete = false
        )
    ),
    RUNECRAFT_AIR_RUNE(
        DailyTask(
            "Runecraft 250 Air runes",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.RUNECRAFTING,
            1,
            0,
            complete = false
        )
    ),
    RUNECRAFT_MIND_RUNE(
        DailyTask(
            "Runecraft 250 Mind runes",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.RUNECRAFTING,
            1,
            0,
            complete = false
        )
    ),
    RUNECRAFT_LAW_RUNE(
        DailyTask(
            "Runecraft 750 Mind runes",
            TaskDifficulties.MEDIUM,
            750,
            14,
            Skill.RUNECRAFTING,
            54,
            0,
            complete = false
        )
    ),
    RUNECRAFT_DEATH_RUNE(
        DailyTask(
            "Runecraft 750 Death runes",
            TaskDifficulties.MEDIUM,
            750,
            14,
            Skill.RUNECRAFTING,
            65,
            0,
            complete = false
        )
    ),
    RUNECRAFT_BLOOD_RUNE(
        DailyTask(
            "Runecraft 750 Blood runes",
            TaskDifficulties.HARD,
            1500,
            28,
            Skill.RUNECRAFTING,
            77,
            0,
            complete = false
        )
    ),
    THEIVE_BAKER_STALL(
        DailyTask(
            "Theive 75 from Baker Stall",
            TaskDifficulties.EASY,
            75,
            7,
            Skill.THIEVING,
            1,
            0,
            complete = false
        )
    ),
    PICKPOCKET_MAN(
        DailyTask(
            "Pickpocket a man 100 times",
            TaskDifficulties.EASY,
            10,
            7,
            Skill.THIEVING,
            1,
            0,
            complete = false
        )
    ),
    THEIVE_SILVER_STALL(
        DailyTask(
            "Theive 150 from Baker Stall",
            TaskDifficulties.MEDIUM,
            150,
            14,
            Skill.THIEVING,
            50,
            0,
            complete = false
        )
    ),
    PICKPOCKET_HERO(
        DailyTask(
            "Pickpocket 500 Hero;s",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.THIEVING,
            80,
            0,
            complete = false
        )
    ),
    THEIVE_GEM_STALL(
        DailyTask(
            "Theive 300 from Gem Stall",
            TaskDifficulties.MEDIUM,
            300,
            28,
            Skill.THIEVING,
            75,
            0,
            complete = false
        )
    ),
    CUTTING_EMERALD(
        DailyTask(
            "Cut 100 Emeralds",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            27,
            0,
            complete = false
        )
    ),
    CUTTING_SAPHIRE(
        DailyTask(
            "Cut 100 Uncut Saphires",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            20,
            0,
            complete = false
        )
    ),
    CUTTING_DIAMOND(
        DailyTask(
            "Cut 100 Uncut Diamonds",
            TaskDifficulties.MEDIUM,
            100,
            14,
            Skill.CRAFTING,
            43,
            0,
            complete = false
        )
    ),
    CUTTING_DRAGONSTONES(
        DailyTask(
            "Cut 100 Uncut Dragonstones",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            55,
            0,
            complete = false
        )
    ),
    CUTTING_ONYX(
        DailyTask(
            "Cut 1 Uncut Onyx",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            55,
            0,
            complete = false
        )
    ),
    COOK_SHRIMP(
        DailyTask(
            "Cook 150 shrimp",
            TaskDifficulties.EASY,
            150,
            7,
            Skill.COOKING,
            1,
            0,
            complete = false
        )
    ),
    COOK_TROUT(
        DailyTask(
            "Cook 150 Trout",
            TaskDifficulties.EASY,
            150,
            7,
            Skill.COOKING,
            15,
            0,
            complete = false
        )
    ),
    COOK_SALMON(
        DailyTask(
            "Cook 200 SALMON",
            TaskDifficulties.EASY,
            200,
            7,
            Skill.COOKING,
            25,
            0,
            complete = false
        )
    ),
    COOK_LOBSTER(
        DailyTask(
            "Cook 200 Lobster",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.COOKING,
            40,
            0,
            complete = false
        )
    ),
    COOK_SWORDFISH(
        DailyTask(
            "Cook 500 Swordfish",
            TaskDifficulties.MEDIUM,
            500,
            14,
            Skill.COOKING,
            45,
            0,
            complete = false
        )
    ),
    COOK_Shark(
        DailyTask(
            "Cook 1000 uncooked Shark",
            TaskDifficulties.HARD,
            1000,
            28,
            Skill.COOKING,
            80,
            0,
            complete = false
        )
    ),
    COOK_DARKCRAB(
        DailyTask(
            "Cook 1000 darkcrabs",
            TaskDifficulties.HARD,
            1000,
            28,
            Skill.COOKING,
            90,
            0,
            complete = false
        )
    ),
    MAKE_SUPER_STRENGTH(
        DailyTask(
            "Make 100 Super Strength Potions",
            TaskDifficulties.MEDIUM,
            100,
            14,
            Skill.HERBLORE,
            55,
            0,
            complete = false
        )
    ),
    MAKE_STRENGTH(
        DailyTask(
            "Make 100 Strength Potions",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HERBLORE,
            12,
            0,
            complete = false
        )
    ),
    MAKE_SUPER_RESTORE(
        DailyTask(
            "Make 50 Super Restore Potions",
            TaskDifficulties.MEDIUM,
            50,
            14,
            Skill.HERBLORE,
            63,
            0,
            complete = false
        )
    ),
    MAKE_ANTIPOISON(
        DailyTask(
            "Make 50 Anti-poison Potions",
            TaskDifficulties.EASY,
            50,
            7,
            Skill.HERBLORE,
            5,
            0,
            complete = false
        )
    ),
    MAKE_ANTIVENOM(
        DailyTask(
            "Make 50 Anti-venom Potions",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HERBLORE,
            87,
            0,
            complete = false
        )
    ),
    MAKE_SUPER_COMBAT(
        DailyTask(
            "Make 50 super combat Potions",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HERBLORE,
            87,
            0,
            complete = false
        )
    ),
    MAKE_SARA_BREWS(
        DailyTask(
            "Make 50 Saradomin brew Potions",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HERBLORE,
            81,
            0,
            complete = false
        )
    ),
    FLETCH_LONGBOW(
        DailyTask(
            "Fletch 50 Normal Longbows",
            TaskDifficulties.EASY,
            50,
            7,
            Skill.FLETCHING,
            1,
            0,
            complete = false
        )
    ),
    FLETCH_OAK_LONGBOW(
        DailyTask(
            "Fletch 50 Oak Longbows",
            TaskDifficulties.EASY,
            50,
            7,
            Skill.FLETCHING,
            25,
            0,
            complete = false
        )
    ),
    FLETCH_WILLOW_LONGBOW(
        DailyTask(
            "Fletch 100 Willow Longbows",
            TaskDifficulties.MEDIUM,
            100,
            14,
            Skill.FLETCHING,
            40,
            0,
            complete = false
        )
    ),
    FLETCH_MAPLE_LONGBOW(
        DailyTask(
            "Fletch 150 Maple Longbows",
            TaskDifficulties.MEDIUM,
            150,
            14,
            Skill.FLETCHING,
            55,
            0,
            complete = false
        )
    ),
    FLETCH_YEW_LONGBOW(
        DailyTask(
            "Fletch 150 yea Longbows",
            TaskDifficulties.HARD,
            150,
            28,
            Skill.FLETCHING,
            70,
            0,
            complete = false
        )
    ),
    FLETCH_MAGIC_LONGBOW(
        DailyTask(
            "Fletch 100 magic Longbows",
            TaskDifficulties.MEDIUM,
            50,
            28,
            Skill.FLETCHING,
            85,
            0,
            complete = false
        )
    ),
    CRAFTING_GREENDHIDEBODY(
        DailyTask(
            "Fletch 100 Green D'hide Bodys",
            TaskDifficulties.MEDIUM,
            100,
            14,
            Skill.CRAFTING,
            63,
            0,
            complete = false
        )
    ),
    CRAFTING_LEATHER_GLOVES(
        DailyTask(
            "Fletch 100 Leather Gloves",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            1,
            0,
            complete = false
        )
    ),
    CRAFTING_BOOTS(
        DailyTask(
            "Fletch 100 Leather Boots",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.CRAFTING,
            7,
            0,
            complete = false
        )
    ),
    CRAFTING_BLACK_DHIDE_BODY(
        DailyTask(
            "Fletch 150 Black D'hide Bodys",
            TaskDifficulties.HARD,
            150,
            28,
            Skill.CRAFTING,
            84,
            0,
            complete = false
        )
    ),
    CRAFTING_BLACK_DHIDE_GLOVES(
        DailyTask(
            "Fletch 150 Black D'hide Gloves",
            TaskDifficulties.HARD,
            150,
            28,
            Skill.CRAFTING,
            84,
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