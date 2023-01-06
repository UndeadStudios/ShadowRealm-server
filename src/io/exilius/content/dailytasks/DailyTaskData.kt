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
    SMITHING_CANNONBALLS3(
        DailyTask(
            "Smith 1500 Cannonballs",
            TaskDifficulties.HARD,
            1500,
            28,
            Skill.SMITHING,
            25,
            0,
            complete = false
        )
    ),
    SMITHING_CANNONBALLS2(
        DailyTask(
            "Smith 750 Cannonballs",
            TaskDifficulties.MEDIUM,
            750,
            14,
            Skill.SMITHING,
            25,
            0,
            complete = false
        )
    ),
    SMITHING_CANNONBALLS1(
        DailyTask(
            "Smith 250 Cannonballs",
            TaskDifficulties.EASY,
            250,
            7,
            Skill.SMITHING,
            25,
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
            "Craft 100 Green D'hide Bodys",
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
            "Craft 100 Leather Gloves",
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
            "Craft 100 Leather Boots",
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
            "Craft 150 Black D'hide Bodys",
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
            "Craft 150 Black D'hide Gloves",
            TaskDifficulties.HARD,
            150,
            28,
            Skill.CRAFTING,
            84,
            0,
            complete = false
        )
    ),
    FISH_RAW_SHRIMP(
        DailyTask(
            "Fish 150 Raw Shrimp",
            TaskDifficulties.EASY,
            150,
            7,
            Skill.FISHING,
            1,
            0,
            complete = false
        )
    ),
    FISH_RAW_TROUT(
        DailyTask(
            "Fish 150 Raw Trout",
            TaskDifficulties.EASY,
            150,
            7,
            Skill.FISHING,
            20,
            0,
            complete = false
        )
    ),
    FISH_RAW_LOBSTER(
        DailyTask(
            "Fish 150 Raw Lobster",
            TaskDifficulties.MEDIUM,
            300,
            14,
            Skill.FISHING,
            40,
            0,
            complete = false
        )
    ),
    FISH_RAW_MONKFISH(
        DailyTask(
            "Fish 300 Raw Monkfish",
            TaskDifficulties.MEDIUM,
            300,
            14,
            Skill.FISHING,
            62,
            0,
            complete = false
        )
    ),
    FISH_RAW_SHARK(
        DailyTask(
            "Fish 500 Raw Shark",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.FISHING,
            76,
            0,
            complete = false
        )
    ),
    FISH_RAW_DARKCRAB(
        DailyTask(
            "Fish 500 Raw Dark crab",
            TaskDifficulties.MEDIUM,
            500,
            28,
            Skill.FISHING,
            90,
            0,
            complete = false
        )
    ),
    RUN_GNOME1(
        DailyTask(
            "Run 25 Laps of gnome Agility Course",
            TaskDifficulties.EASY,
            25,
            7,
            Skill.AGILITY,
            1,
            0,
            complete = false
        )
    ),
    RUN_VARROCK_ROOFTOPS(
        DailyTask(
            "Run 75 Laps of Varrock Rooftop Agility Course",
            TaskDifficulties.MEDIUM,
            75,
            14,
            Skill.AGILITY,
            30,
            0,
            complete = false
        )
    ),
    RUN_WILDY_COURSE(
        DailyTask(
            "Run 150 Laps of wilderness Agility Course",
            TaskDifficulties.HARD,
            75,
            28,
            Skill.AGILITY,
            52,
            0,
            complete = false
        )
    ),
    RUN_ADRY_COURSE(
        DailyTask(
            "Run 150 Laps of wilderness Agility Course",
            TaskDifficulties.HARD,
            75,
            28,
            Skill.AGILITY,
            90,
            0,
            complete = false
        )
    ),
    ALTAR1(
        DailyTask(
            "Use 100 Bone On home altar",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.PRAYER,
            1,
            0,
            complete = false
        )
    ),
    ALTAR2(
        DailyTask(
            "Use 400 Bone On home altar",
            TaskDifficulties.MEDIUM,
            400,
            14,
            Skill.PRAYER,
            35,
            0,
            complete = false
        )
    ),
    ALTAR3(
        DailyTask(
            "Use 1000 Bone On home altar",
            TaskDifficulties.HARD,
            100,
            28,
            Skill.PRAYER,
            75,
            0,
            complete = false
        )
    ),
    WILDYALTAR1(
        DailyTask(
            "Use 100 Bone On home altar",
            TaskDifficulties.MEDIUM,
            100,
            7,
            Skill.PRAYER,
            1,
            0,
            complete = false
        )
    ),
    WILDYALTAR2(
        DailyTask(
            "Use 400 Bone On home altar",
            TaskDifficulties.MEDIUM,
            400,
            14,
            Skill.PRAYER,
            35,
            0,
            complete = false
        )
    ),
    WILDYALTAR3(
        DailyTask(
            "Use 400 Bone On home altar",
            TaskDifficulties.HARD,
            1000,
            28,
            Skill.PRAYER,
            75,
            0,
            complete = false
        )
    ),
    KILL_REV_IMP(
        DailyTask(
            "Kill 100 Revenant Imp",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_SANDCRABS(
        DailyTask(
            "Kill 100 Sand Crabs",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_HILL_GIANTS(
        DailyTask(
            "Kill 100 hill giants",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            20,
            0,
            complete = false
        )
    ),
    KILL_MOSS_GIANTS(
        DailyTask(
            "Kill 100 moss giants",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_ROCKCRABS(
        DailyTask(
            "Kill 100 Rock Crabs",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_COWSS(
        DailyTask(
            "Kill 100 Cows",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_GHOSTS(
        DailyTask(
            "Kill 100 Ghost",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_ROCK_SLUG(
        DailyTask(
            "Kill 100 Rock Slugs",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            20,
            0,
            complete = false
        )
    ),
    KILL_CAVE_BUG(
        DailyTask(
            "Kill 100 Cave Bugs",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_OGRES(
        DailyTask(
            "Kill 100 Ogres",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            20,
            0,
            complete = false
        )
    ),
    KILL_BANSHEES(
        DailyTask(
            "Kill 100 Banshees",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            20,
            0,
            complete = false
        )
    ),
    KILL_CRAWLING_HANDINGS(
        DailyTask(
            "Kill 100 Crawling hands",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            10,
            0,
            complete = false
        )
    ),
    KILL_GREEN_DRAGONS(
        DailyTask(
            "Kill 100 Green Dragons",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            40,
            0,
            complete = false
        )
    ),
    KILL_SKELETONS(
        DailyTask(
            "Kill 100 Skeletons",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            20,
            0,
            complete = false
        )
    ),
    KILL_ENTS(
        DailyTask(
            "Kill 100 Ents",
            TaskDifficulties.EASY,
            100,
            7,
            Skill.HITPOINTS,
            40,
            0,
            complete = false
        )
    ),
    KILL_MAMOTHS(
        DailyTask(
            "Kill 250 Mamoths",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_REV_PYREFRIEND(
        DailyTask(
            "Kill 250 Revenant Pyrefiend",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_BLOODVELD(
        DailyTask(
            "Kill 250 BloodVeld",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_WRAP_JELLY(
        DailyTask(
            "Kill 250 Wraped Jellys",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_DUST_DEVILS(
        DailyTask(
            "Kill 250 Dust Devils",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_GIANT_MOLE(
        DailyTask(
            "Kill 250 Giant Mole",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            60,
            0,
            complete = false
        )
    ),
    KILL_NECHRAELS(
        DailyTask(
            "Kill 250 Nechraels",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_SKELETON_WYVERNS(
        DailyTask(
            "Kill 250 Skeletal Wyverns ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_SMOKE_DEVILS(
        DailyTask(
            "Kill 250 Smoke Devils ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_REVENANT_ORK(
        DailyTask(
            "Kill 250 Revenant Orks ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_BLUE_DRAGONS(
        DailyTask(
            "Kill 250 Blue Dragons ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_LIZARD_SHAMEN(
        DailyTask(
            "Kill 100 Lizardmen Shamen  ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_KBD(
        DailyTask(
            "Kill 100 King Black Dragon ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_FIRE_GIANTS(
        DailyTask(
            "Kill 250 Fire Giants ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            45,
            0,
            complete = false
        )
    ),
    KILL_BLACK_DEMONS(
        DailyTask(
            "Kill 100 Black Demons ",
            TaskDifficulties.MEDIUM,
            100,
            14,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_HELL_HOUNDS(
        DailyTask(
            "Kill 250 Hell Hounds ",
            TaskDifficulties.MEDIUM,
            250,
            14,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_CERBERUS(
        DailyTask(
            "Kill 100 Cerberus ",
            TaskDifficulties.HARD,
            100,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_ABYSSAL_DEMONS(
        DailyTask(
            "Kill 500 Abyssal Demons ",
            TaskDifficulties.HARD,
            500,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_DARK_BEASTS(
        DailyTask(
            "Kill 100 Dark Beast",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_LAVA_DRAGONS(
        DailyTask(
            "Kill 200 Lava Dragons ",
            TaskDifficulties.HARD,
            200,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_ZULRAH(
        DailyTask(
            "Kill 250 Zulrah ",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_KRAKEN(
        DailyTask(
            "Kill 100 Kraken ",
            TaskDifficulties.HARD,
            100,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_CHAOS_FANATIC(
        DailyTask(
            "Kill 100 Chaos Fanatic ",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_CHAOS_ELEMENTAL(
        DailyTask(
            "Kill 100 Chaos Elemental ",
            TaskDifficulties.HARD,
            100,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_SCORPIA(
        DailyTask(
            "Kill 150 SCORPIA ",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_KREEARRA(
        DailyTask(
            "Kill 75 Kree'arra ",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_GENERAL_GRAARDOR(
        DailyTask(
            "Kill 100 General Graardor ",
            TaskDifficulties.HARD,
            100,
            28,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_COMMANDER_ZILY(
        DailyTask(
            "Kill 75 Commander Zilyana ",
            TaskDifficulties.HARD,
            75,
            28,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_Kril(
        DailyTask(
            "Kill 75 K'ril Tsutsaroth ",
            TaskDifficulties.HARD,
            75,
            28,
            Skill.HITPOINTS,
            85,
            0,
            complete = false
        )
    ),
    KILL_A_HYDRA(
        DailyTask(
            "Kill 30 Alchemical Hydra ",
            TaskDifficulties.HARD,
            30,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_NEX(
        DailyTask(
            "Kill 30 Nex ",
            TaskDifficulties.HARD,
            30,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_THE_NIGHTMARE(
        DailyTask(
            "Kill 30 The Nightmare ",
            TaskDifficulties.HARD,
            30,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_VORKATH(
        DailyTask(
            "Kill 50 Vorkath ",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_ABYSSAL_SIRE(
        DailyTask(
            "Kill 50 Abyssal Sire ",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_CORP(
        DailyTask(
            "Kill 20 Corperal Beast ",
            TaskDifficulties.HARD,
            20,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_SKOTIZIO(
        DailyTask(
            "Kill 20 Skotizio",
            TaskDifficulties.HARD,
            20,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_DAG_PRIME(
        DailyTask(
            "Kill 50 Dagannoth Prime ",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HITPOINTS,
            90,
            0,
            complete = false
        )
    ),
    KILL_DAG_REX(
        DailyTask(
            "Kill 50 Dagannoth Rex ",
            TaskDifficulties.HARD,
            50,
            28,
            Skill.HITPOINTS,
            80,
            0,
            complete = false
        )
    ),
    KILL_DAG_SUPREME(
        DailyTask(
            "Kill 250 Dagannoth Supreme ",
            TaskDifficulties.HARD,
            250,
            28,
            Skill.HITPOINTS,
            80,
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
    )
}