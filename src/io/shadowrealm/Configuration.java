package io.shadowrealm;

import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.util.Misc;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.TimeUnit;

public class Configuration {

	public static final int CLIENT_VERSION = 233;
	public static final int MAX_NPCS = 11957;
	public static final boolean DOUBLE_EXP = false;
	public static final String UPDATE_MESSAGE = "Updated the links to work.";
	public static String LOGOUT_MESSAGE = "Click here to logout!";
	public static String DEATH_MESSAGE = "Oh dear you are dead!";

	public static boolean ADMIN_CAN_TRADE = false;

	public static boolean ADMIN_DROP_ITEMS = false;

	public static boolean ADMIN_CAN_SELL_ITEMS = false;

	public static boolean MINI_GAMES = true;

	public static boolean LOCK_EXPERIENCE = false;

	public static String SERVER_NAME = "Shadow Realm";
	public static final int PORT_DEFAULT = 52778;
	public static final int PORT_TEST = 43595;

	public static final String PACKAGE = "io.shadowrealm";
	public static final String DATA_FOLDER = "etc";

	public static final String WEBSITE = "https://www.shadowrealm-rsps.com/";
	public static final String FORUM_TOPIC_URL = "https://www.shadowrealm-rsps.com/";
	public static final String VOTE_LINK = "https://shadowrealm-rsps.com/vote";
	public static final String JIPY= "https://www.youtube.com/@jipyrsps";
	public static final String SANDESU= "https://www.youtube.com/@SandesuSRS";
	public static final String CXRSPS= "https://www.youtube.com/@CXRSPS1";
	public static final String NOOBS = "https://www.youtube.com/c/noobsown";
	public static final String MIKE_AP = "https://www.youtube.com/@mikeap_osrs";
	public static final String HISCORES_LINK = "https://hiscores.shadowrealm-rsps.com/";
	public static final String STORE_LINK = "https://store.shadowrealm-rsps.com/";
	public static final String DISCORD_INVITE = "https://discord.shadowrealm-rsps.com/";
	public static final String RULES_LINK = "https://www.shadowrealm-rsps.com/";
	public static final String GUIDES_LINK = "https://www.shadowrealm-rsps.com/";
	public static final String DONATOR_BENEFITS_LINK = "https://shadowrealmrsps.everythingrs.com/services/store";
	public static final String PRICE_GUIDE = "https://www.shadowrealm-rsps.com/";
	public static final LocalDate exilius_V1_LAUNCH_DATE = LocalDate.of(2020, Month.MARCH, 13);
	public static final LocalDate exilius_V2_LAUNCH_DATE = LocalDate.of(2021, Month.MAY, 1);
	public static final LocalDate RECLAIM_DONATIONS_START_DATE = LocalDate.of(2021, Month.JULY, 12);

	/**
	 * Hours between backup of player files for current {@link ServerState}.
	 */
	public static long PLAYER_SAVE_TIMER_MILLIS = TimeUnit.MINUTES.toMillis(10);
	public static int PLAYER_SAVE_BACKUP_EVERY_X_SAVE_TICKS = 2;

	/**
	 * Amount of days to keep a player backup file.
	 */
	public static int PLAYER_SAVE_BACKUPS_DELETE_AFTER_DAYS = 7;
	public static int PLAYER_SAVE_BACKUPS_EXTERNAL_DELETE_AFTER_DAYS = 1;

	public static final boolean LOWERCASE_CAPTCHA = true;

	public static boolean DISABLE_DISCORD_MESSAGING = false;

	public static boolean DISABLE_CONNECTION_REQUEST_LIMIT = false;
	public static boolean DISABLE_CAPTCHA_EVERY_LOGIN = true;
	public static boolean DISABLE_CHANGE_ADDRESS_CAPTCHA = false;
	public static boolean DISABLE_NEW_ACCOUNT_CAPTCHA = false;
	public static boolean DISABLE_REGISTRATION = false;
	public static boolean DISABLE_ADDRESS_VERIFICATION = true;
	public static boolean DISABLE_FRESH_LOGIN = false;
	public static boolean DISABLE_NEW_MAC = true;

	public static boolean DISABLE_PRESETS = false;
	public static boolean DISABLE_FOE = false;
	public static boolean DISABLE_SHOP_SELL = false;
	public static boolean DISABLE_SHOP_BUY = false;
	public static boolean DISABLE_DISPLAY_NAMES = false;
	public static boolean DISABLE_LOGIN_THROTTLE = false;
	public static boolean DISABLE_CC_MESSAGE = false;
	public static boolean DISABLE_FLOWER_POKER = false;
	public static boolean DISABLE_HC_LOSS_ON_DEATH = false;


	// Logging
	public static boolean DISABLE_PACKET_LOG = false;


	/**
	 * Time to restrict new players from using trade, duel, trading post.
	 */
	public static final long NEW_PLAYER_RESTRICT_TIME_MIN = 15;
	public static final long NEW_PLAYER_RESTRICT_TIME_TICKS = Misc.toCycles(NEW_PLAYER_RESTRICT_TIME_MIN, TimeUnit.MINUTES);


	/**
	 * Cycle time.
	 */
	public static final int CYCLE_TIME = 600;

	public static int PLAYERMODIFIER;

	/**
	 * The max amount of packets that will be processed per cycle per player, everything else is discarded.
	 */
	public static final int MAX_PACKETS_PROCESSED_PER_CYCLE = 100;
	public static final int KICK_PLAYER_AFTER_PACKETS_PER_CYCLE = 150;
	
	/**
	 * Controls where characters will be saved                                          
	 */
	private static final LocalDateTime LAUNCH_TIME = LocalDateTime.now();
	public static String USER_FOLDER = System.getProperty("user.home");
	public static final String SAVE_DIRECTORY = "./save_files/";

	public static final String LOG_DIRECTORY = "./logs/";
	public static final String ERROR_LOG_DIRECTORY = LOG_DIRECTORY + "error_logs/";
	public static String ERROR_LOG_FILE = ("error_log_" + Misc.createFileNameSmallDate(LAUNCH_TIME) + ".txt");


	public static final String CONSOLE_LOG_DIRECTORY = LOG_DIRECTORY + "console_logs/";
	public static String CONSOLE_FILE = ("console_log_" + Misc.createFileNameSmallDate(LAUNCH_TIME) + ".txt");

	/**
	 * Combat applications
	 */
	public static boolean BOUNTY_HUNTER_ACTIVE = true;
	public static boolean NEW_DUEL_ARENA_ACTIVE = true;
	public static boolean VOTE_PANEL_ACTIVE = false;

	/**
	 * The highest amount ID. Change is not needed here unless loading items higher
	 * than the 667 revision.
	 */
	public static final int ITEM_LIMIT = 60000;

	/**
	 * The size of a players bank.
	 */
	public static final int BANK_CAPACITY = 1_000;

	/**
	 * The max amount of players until your server is full.
	 */
	public static final int MAX_PLAYERS = 1100;

	/**
	 * The maximum number of npcs allowed in the local player's list.
	 */
	public static final int MAX_NPCS_IN_LOCAL_LIST = 150;
	public static final int MAX_PLAYERS_IN_LOCAL_LIST = 140;

	/**
	 * How many accounts are allowed to be online at once from the same computer.
	 */
	public static final int SAME_COMPUTER_CONNECTIONS_ALLOWED = 10;

	public static final int IN_COMBAT_TIMER = 10_000;

	/**
	 * dragon ids for the dragon hunter crossbow
	 */
	public static final int[] DRAG_IDS = { 137, 139, 239, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252,
	            253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273,
	            274, 275, 1871, 1872, 2641, 2642, 2918, 2919, 4000, 4385, 5194, 5872, 5873, 5878, 5879, 5880, 5881, 5882,
	            6500, 6501, 6502, 6593, 7039, 7253, 7254, 7255, 7273, 7274, 7275, 8028, 465, 8030, 8031, 7550, 7551, 7552, 7553,
	            7554, 7555, 8615, 8619, 8620, 8621, 8622, 9033, 8609, 2918, 8611};

	public static final int[] LEAF_IDS = { 427, 428, 429, 430, 410, 411, 10397, 7405};

	/**
	 * Items that cannot be dropped.
	 */
	public static final int[] TOURNAMENT_ITEMS_DROPPABLE = { 3144, 385, 11936, 13441 };

	/**
	 * undead npcs.
	 */
	public static final int[] UNDEAD_NPCS = {
			2,3,4,5,6,7,2514,2515,2516,2517,2518,2519,6608,7257,7296,7864,414,120,448,449,450,451,452,453,454,455,456,
			457,945,946,5622,5623,5624,5625,5626,5627,85,86,87,88,89,90,717,718,719,720,721,722,723,724,725,726,727,728,7881,
			7881,7931,7932,7933,7934,7935,7936,7937,7938,7939,7940,5633,6740,7258,70,71,72,73,74,75,76,77,78,79,80,872,878,
			879,2999,2993,4421,3969,3970,3971,8026,8058,8059,8060,8061,4500,4505,882,5353,5354,5355,3616,547,1163,8359,6611,
			6612,1672,1673,1674,1675,1676,1677,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,26,27,28,29,30,31,32,33,34,
			35,8062,8063,866,867,868,869,870,871,873,874,4312,4318,4319,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,
			54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,81,82,83,91,92,93,94,95,96,97,130,875,876,877,880,920,924,949,950,
			951,952,953,1538,1539,1541,1685,1686,1687,1688,1784,1785,1786,2501,2502,2503,2504,2505,2506,2507,2508,2509,2520,2521,
			2522,2523,2524,2525,2526,2527,2528,2529,2530,2531,2532,2533,2534,2992,3516,3565,3584,3617,3625,3972,3973,3974,3975,
			3976,3977,3978,3979,3980,3981,5237,5342,5343,5344,5345,5346,5347,5348,5349,5350,5351,5370,5506,5507,5568,5571,5574,
			5583,5647,6441,6442,6443,6444,6445,6446,6447,6448,6449,6450,6451,6452,6453,6454,6455,6456,6457,6458,6459,6460,6461, 2145,
			6462,6463,6464,6465,6466,6467,6468,6596,6597,6598,6741, Npcs.SKELETON_HELLHOUND, 7413, Npcs.PESTILENT_BLOAT, 10812, 10813,
			11184, 7604, 7605, 7606
	};
	/**
	 * NPCs that represent demons for the Arclight
	 */
	public static final int[] DEMON_IDS = { 1531, 3134, 2006, 2026, 7244, 1432, 415, 7410, 135, 3133, 484, 1619, 7276,
			3138, 7397, 7398, 11, 7278, 7144, 7145, 7146, 3129, 3132, 3130, 3131, 7286, 5890, 7286, 5862, 5863, 5866 };

	/**
	 * The level in which you can not teleport in the wild, and higher.
	 */
	public static final int NO_TELEPORT_WILD_LEVEL = 20;
	/**
	 * The level in which you can not teleport above 30 wildy using glory.
	 */
	public static final int JEWELERY_TELEPORT_MAX_WILD_LEVEL = 30;

	/**
	 * The time, in game cycles that the skull above a player should exist for.
	 * Every game cycle is 600ms, every minute has 60 seconds. Therefor there are
	 * 100 cycles in 1 minute. .600 * 100 = 60.
	 */
	public static final int SKULL_TIMER = 2000;

	/**
	 * The maximum time for a player skull with an extension in the length.
	 */
	public static final int EXTENDED_SKULL_TIMER = 6000;

	/**
	 * Single and multi player killing zones.
	 */
	public static final boolean SINGLE_AND_MULTI_ZONES = true;

	/**
	 * Wilderness levels and combat level differences. Used when attacking players.
	 */
	public static final boolean COMBAT_LEVEL_DIFFERENCE = true;

	/**
	 * Skill names and id's
	 */
	public static final String[] SKILL_NAME = { "Attack", "Defence", "Strength", "Hitpoints", "Ranged", "Prayer",
			"Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining",
			"Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting", "Hunter" };

	public static final int DEFAULT_COMBAT_EXPERIENCE_RATE = 50;

	public static String QUESTION = "";
	public static String ANSWER = "";
	public static boolean BONUS_WEEKEND;
	public static boolean BONUS_PC = true;
	public static boolean DOUBLE_DROPS;
	public static boolean CYBER_MONDAY;
	public static boolean DOUBLE_PKP;
	public static boolean DOUBLE_VOTE_INCENTIVES;
	public static boolean wildyPursuit;

	/**
	 * Looting bag and rune pouch
	 */
	public static boolean BAG_AND_POUCH_PERMITTED = true;

	/**
	 * How fast the special attack bar refills.
	 */
	public static final int INCREASE_SPECIAL_AMOUNT = 31000;

	/**
	 * If you need a certain magic level to use a certain spell.
	 */
	public static final boolean MAGIC_LEVEL_REQUIRED = true;

	/**
	 * How long the god charge spell lasts.
	 */
	public static final int GOD_SPELL_CHARGE = 300000;

	/**
	 * If you need runes to use magic spells.
	 */
	public static final boolean RUNES_REQUIRED = true;

	/**
	 * If you need correct arrows to use with bows.
	 */
	public static final boolean CORRECT_ARROWS = true;

	/**
	 * If the crystal bow degrades.
	 */
	public static final boolean CRYSTAL_BOW_DEGRADES = true;

	/**
	 * How often the server saves data.
	 */
	public static final int SAVE_TIMER = 60; // Saves every one minute.

	/**
	 * How far NPCs can walk.
	 */
	public static final int NPC_RANDOM_WALK_DISTANCE = 8; // 5x5 square, NPCs
															// would be able to
															// walk 25 squares
															// around.

	/**
	 * The starting location of your server.
	 */
	public static final int START_LOCATION_X = 3086;
	public static final int START_LOCATION_Y = 3488;
	public static final Position START_POSITION = new Position(START_LOCATION_X, START_LOCATION_Y);

	/**
	 * The re-spawn point of when someone dies.
	 */
	public static final int RESPAWN_X = 3086;
	public static final int RESPAWN_Y = 3488;
	public static final int RESPAWN_Z = 0;

	/**
	 * The re-spawn point of when a duel ends.
	 */
	public static final int DUELING_RESPAWN_X = 3362;
	public static final int DUELING_RESPAWN_Y = 3263;

	/**
	 * Glory locations.
	 */
	public static final int EDGEVILLE_X = 3099;
	public static final int EDGEVILLE_Y = 3488;

	public static final int AL_KHARID_X = 3293;
	public static final int AL_KHARID_Y = 3176;

	public static final int KARAMJA_X = 2925;
	public static final int KARAMJA_Y = 3173;

	public static final int DRAYNOR_X = 3079;
	public static final int DRAYNOR_Y = 3250;


	/*
	 * Modern spells
	 */
	public static final int VARROCK_X = 3210;
	public static final int VARROCK_Y = 3424;

	public static final int LUMBY_X = 3222;
	public static final int LUMBY_Y = 3218;

	public static final int FALADOR_X = 2964;
	public static final int FALADOR_Y = 3378;

	public static final int CAMELOT_X = 2757;
	public static final int CAMELOT_Y = 3477;

	public static final int ARDOUGNE_X = 2662;
	public static final int ARDOUGNE_Y = 3305;

	public static final int WATCHTOWER_X = 2549;
	public static final int WATCHTOWER_Y = 3112;

	public static final int TROLLHEIM_X = 2888;
	public static final int TROLLHEIM_Y = 3676;

	/*
	 * Ancient spells
	 */
	public static final int PADDEWWA_X = 3098;
	public static final int PADDEWWA_Y = 9884;

	public static final int SENNTISTEN_X = 3322;
	public static final int SENNTISTEN_Y = 3336;

	public static final int KHARYRLL_X = 3492;
	public static final int KHARYRLL_Y = 3471;

	public static final int LASSAR_X = 3006;
	public static final int LASSAR_Y = 3471;

	public static final int DAREEYAK_X = 2966;
	public static final int DAREEYAK_Y = 3695;

	public static final int CARRALLANGAR_X = 3156;
	public static final int CARRALLANGAR_Y = 3666;

	public static final int ANNAKARL_X = 3288;
	public static final int ANNAKARL_Y = 3886;

	public static final int GHORROCK_X = 2977;
	public static final int GHORROCK_Y = 3873;

	/**
	 * Buffer size.
	 */
	public static final int BUFFER_SIZE = 512;

	public static final Position ONYX_ZONE_TELEPORT = new Position(2783, 4903, 0);
	public static final Position hespori_end = new Position(3074, 3500, 0);
	public static long DOUBLE_DROPS_TIMER;

	public final static int[] COMBAT_RELATED_ITEMS = { 35, 39, 40, 41, 42, 43,
			44, 50, 53, 54, 60, 64, 75, 76, 78, 88, 546, 548, 577, 581, 598,
			626, 628, 630, 632, 634, 667, 687, 746, 747, 767, 772, 775, 776,
			777, 778, 818, 837, 839, 841, 843, 845, 847, 849, 851, 853, 855,
			857, 859, 861, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872,
			873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885,
			886, 887, 888, 889, 890, 891, 892, 893, 942, 975, 1007, 1019, 1021,
			1023, 1027, 1029, 1031, 1033, 1035, 1052, 1059, 1061, 1063, 1065,
			1067, 1069, 1071, 1073, 1075, 1077, 1079, 1081, 1083, 1085, 1087,
			1089, 1091, 1093, 1095, 1097, 1099, 1101, 1103, 1105, 1107, 1109,
			1111, 1113, 1115, 1117, 1119, 1121, 1123, 1125, 1127, 1129, 1131,
			1133, 1135, 1137, 1139, 1141, 1143, 1145, 1147, 1149, 1151, 1153,
			1155, 1157, 1159, 1161, 1163, 1165, 1167, 1169, 1171, 1173, 1175,
			1177, 1179, 1181, 1183, 1185, 1187, 1189, 1191, 1193, 1195, 1197,
			1199, 1201, 1203, 1205, 1207, 1209, 1211, 1213, 1215, 1217, 1219,
			1221, 1223, 1225, 1227, 1229, 1231, 1233, 1237, 1239, 1241, 1243,
			1245, 1247, 1249, 1251, 1253, 1255, 1257, 1259, 1261, 1263, 1265,
			1267, 1269, 1271, 1273, 1275, 1277, 1279, 1281, 1283, 1285, 1287,
			1289, 1291, 1293, 1295, 1297, 1299, 1301, 1303, 1305, 1307, 1309,
			1311, 1313, 1315, 1317, 1319, 1321, 1323, 1325, 1327, 1329, 1331,
			1333, 1335, 1337, 1339, 1341, 1343, 1345, 1347, 1349, 1351, 1353,
			1355, 1357, 1359, 1361, 1363, 1365, 1367, 1369, 1371, 1373, 1375,
			1377, 1379, 1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397,
			1399, 1401, 1403, 1405, 1407, 1409, 1419, 1420, 1422, 1424, 1426,
			1428, 1430, 1432, 1434, 1540, 1718, 1724, 2402, 2412, 2413, 2414,
			2415, 2416, 2417, 2487, 2489, 2491, 2493, 2495, 2497, 2499, 2501,
			2503, 2513, 2532, 2533, 2534, 2535, 2536, 2537, 2538, 2539, 2540,
			2541, 2577, 2579, 2581, 2583, 2585, 2587, 2589, 2591, 2593, 2595,
			2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 2617,
			2619, 2621, 2623, 2625, 2627, 2629, 2653, 2655, 2659, 2661, 2663,
			2667, 2669, 2671, 2673, 2861, 2864, 2865, 2866, 2890, 2896, 2906,
			2916, 2926, 2936, 2961, 2963, 3053, 3054, 3095, 3096, 3097, 3098,
			3099, 3100, 3101, 3105, 3107, 3122, 3140, 3170, 3171, 3172, 3173,
			3174, 3175, 3176, 3190, 3192, 3194, 3196, 3198, 3200, 3202, 3204,
			3327, 3329, 3331, 3333, 3335, 3337, 3339, 3341, 3343, 3385, 3387,
			3389, 3391, 3393, 3472, 3473, 3474, 3475, 3476, 3477, 3479, 3481,
			3483, 3485, 3486, 3488, 3748, 3749, 3751, 3753, 3755, 3757, 3758,
			3759, 3761, 3763, 3765, 3767, 3769, 3771, 3773, 3775, 3777, 3779,
			3781, 3783, 3785, 3787, 3789, 3791, 3797, 3840, 3841, 3842, 3843,
			3844, 4087, 4089, 4091, 4093, 4095, 4097, 4099, 4101, 4103, 4105,
			4107, 4109, 4111, 4113, 4115, 4117, 4119, 4121, 4123, 4125, 4127,
			4129, 4131, 4150, 4151, 4153, 4156, 4158, 4160, 4170, 4172, 4173,
			4174, 4175, 4212, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221,
			4222, 4223, 4224, 4226, 4227, 4228, 4229, 4230, 4231, 4232, 4233,
			4234, 4298, 4300, 4302, 4304, 4308, 4310, 4502, 4503, 4504, 4505,
			4506, 4507, 4508, 4509, 4510, 4511, 4512, 4580, 4582, 4585, 4587,
			4600, 4675, 4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724,
			4726, 4728, 4730, 4732, 4734, 4736, 4738, 4740, 4745, 4747, 4749,
			4751, 4753, 4755, 4757, 4759, 4778, 4783, 4788, 4793, 4803, 4827,
			4860, 4866, 4872, 4878, 4884, 4890, 4896, 4902, 4908, 4914, 4920,
			4926, 4932, 4938, 4944, 4950, 4956, 4962, 4968, 4974, 4980, 4986,
			4992, 4998, 5014, 5016, 5018, 5553, 5554, 5555, 5556, 5557, 5574,
			5575, 5576, 5616, 5617, 5618, 5619, 5620, 5621, 5622, 5623, 5624,
			5625, 5626, 5627, 5648, 5654, 5655, 5656, 5657, 5658, 5659, 5660,
			5661, 5662, 5663, 5664, 5665, 5666, 5667, 5668, 5670, 5672, 5674,
			5676, 5678, 5680, 5682, 5686, 5688, 5690, 5692, 5694, 5696, 5698,
			5700, 5704, 5706, 5708, 5710, 5712, 5714, 5716, 5718, 5720, 5722,
			5724, 5726, 5728, 5730, 5734, 5736, 6061, 6062, 6106, 6107, 6108,
			6109, 6110, 6111, 6128, 6129, 6130, 6131, 6133, 6135, 6137, 6139,
			6141, 6143, 6145, 6147, 6149, 6151, 6153, 6235, 6257, 6279, 6313,
			6315, 6317, 6322, 6324, 6326, 6328, 6330, 6416, 6522, 6523, 6524,
			6525, 6526, 6527, 6528, 6562, 6563, 6568, 6570, 6587, 6589, 6591,
			6593, 6595, 6597, 6599, 6601, 6603, 6605, 6607, 6609, 6611, 6613,
			6615, 6617, 6619, 6621, 6623, 6625, 6627, 6629, 6631, 6633, 6720,
			6724, 6726, 6739, 6745, 6746, 6760, 6762, 6764, 6809, 6889, 6893,
			6894, 6895, 6897, 6908, 6910, 6912, 6914, 6916, 6918, 6920, 6922,
			6924, 6959, 7158, 7159, 7332, 7334, 7336, 7338, 7340, 7342, 7344,
			7346, 7348, 7350, 7352, 7354, 7356, 7358, 7360, 7362, 7364, 7366,
			7368, 7374, 7390, 7392, 7394, 7396, 7398, 7399, 7400, 7410, 7433,
			7435, 7437, 7439, 7441, 7443, 7445, 7447, 7449, 7451, 7453, 7454,
			7455, 7456, 7457, 7458, 7459, 7460, 7461, 7462, 7539, 7552, 7553,
			7639, 7640, 7641, 7642, 7643, 7644, 7645, 7646, 7647, 7648, 7668,
			7686, 7687, 7806, 7807, 7808, 7809 };

	public final static int[] ALCOHOL_RELATED_ITEMS = { 8940, 3803, 3712, 3711,
			2092, 2074, 3801 };

    public static class V14__leaderboards_add_composite_key extends BaseJavaMigration {
        @Override
        public void migrate(Context context) throws Exception {
            context.getConnection().createStatement().execute("ALTER TABLE leaderboards add primary key(username, type, date)");
            context.getConnection().createStatement().execute("DROP INDEX idx_amount ON leaderboards");
        }
    }
}