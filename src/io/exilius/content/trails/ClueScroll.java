package io.exilius.content.trails;

import io.exilius.Server;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.model.Items;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.util.Location3D;
import io.exilius.util.Misc;

import java.util.ArrayList;
import java.util.Arrays;

public class ClueScroll {

    /*
     * level 3 scroll : I expect you to die! find clue : You've found another
     * clue!- You've been given a casket! - You found a casket! 6960 clue reward
     */

    /* the main clue scroll hint interface */

    public static final int CLUE_SCROLL_INTERFACE = 6965;

    public static final int CASKET_LV1 = 2714;
    public static final int CASKET_LV2 = 2802;
    public static final int CASKET_LV3 = 3521;

    public static final int REWARD_CASKET_LV1 = 20546;
    public static final int REWARD_CASKET_LV2 = 20545;
    public static final int REWARD_CASKET_LV3 = 20544;

    public static final int CLUE_ITEM = 2701;

    /* the puzzle class constants */
    private static final int[] CLUE_HUNTER = {Items.CLUE_HUNTER_BOOTS, Items.CLUE_HUNTER_CLOAK,
            Items.CLUE_HUNTER_GARB, Items.CLUE_HUNTER_GLOVES, Items.CLUE_HUNTER_TROUSERS};

    public static final int PUZZLE_INTERFACE = 6976;

    public static final int PUZZLE_INTERFACE_CONTAINER = 6980;

    public static final int PUZZLE_INTERFACE_DEFAULT_CONTAINER = 6985;

    public static final int CASTLE_PUZZLE = 2800;
    public static final int TREE_PUZZLE = 3565;
    public static final int OGRE_PUZZLE = 3571;
    public static final int ZULRAH_PUZZLE = 20280;
    public static final int CERBERUS_PUZZLE = 20281;
    public static final int GNOME_CHILD_PUZZLE = 20282;
    public static final int TOB_PUZZLE = 23417;

    public static final int PUZZLE_LENGTH = 25;

    public static final int[] firstPuzzle = {2749, 2750, 2751, 2752, 2753, 2754, 2755, 2756, 2757, 2758, 2759, 2760, 2761, 2762, 2763, 2764, 2765, 2766, 2767, 2768, 2769, 2770, 2771, 2772, -1};

    public static final int[] secondPuzzle = {3619, 3620, 3621, 3622, 3623, 3624, 3625, 3626, 3627, 3628, 3629, 3630, 3631, 3632, 3633, 3634, 3635, 3636, 3637, 3638, 3639, 3640, 3641, 3642, -1};

    public static final int[] thirdPuzzle = {3643, 3644, 3645, 3646, 3647, 3648, 3649, 3650, 3651, 3652, 3653, 3654, 3655, 3656, 3657, 3658, 3659, 3660, 3661, 3662, 3663, 3664, 3665, 3666, -1};
    public static final int[] fouthPuzzle = {20283, 20284, 20285, 20286, 20287, 20288, 20289, 20290, 20291, 20292, 20293, 20294,20295, 20296, 20297, 20298, 20299, 20300, 20301, 20302, 20303, 20304, 20305, 20306, -1};
    public static final int[] fifthPuzzle = {20307, 20308, 20309, 20310, 20311, 20312, 20313, 20314, 20315, 20316, 20317, 20318, 20319, 20320, 20321, 20322, 20323, 20324, 20325, 20326, 20327, 20328, 20329, 20330, -1};
    public static final int[] sixthPuzzle = {20331, 20332, 20333, 20334, 20335, 20336, 20337, 20338, 20339, 20340, 20341, 20342, 20343, 20344, 20345, 20346, 20347, 20348, 20349, 20350, 20351, 20352, 20353, 20354, -1};
    public static final int[] seventhPuzzle = {23418, 23419, 23420, 23421, 23422, 23423, 23424, 23425, 23426, 23427, 23428, 23429, 23430, 23431, 23432, 23433, 23434, 23435, 23436, 23437, 23438, 23439, 23440, 23441, -1};
    public static String[] levelOneClueNpc = {"Man", "Woman", "Goblin", "Mugger", "Barbarian", "Farmer", "Al-Kharid", "Thug", "Rock Crabs", "Rogue", "Thief", "H.A.M", "Banshees", "Cave Slime", "Afflicted", "Borrakar", "Freidar", "Freygerd", "Inga", "Jennella", "Lensa", "Lanzig"};

    public static String[] levelTwoClueNpc = {"Guard", "Tribesman", "Bandit Camp Humans", "Cockatrice", "Abyssal Leech", "Pyrefiend", "Harpie Bug Swarm", "Black Guard", "Rellekka Warriors", "Market Guard", "Jogre", "Ice Warrior", "Abyssal Guardian", "Paladin", "Vampire", "Dagannoth", "Giant Skeleton", "Abyssal Walker", "Dagannoth", "Wallasalki", "Mummy", "Giant Rock Crab"};

    public static String[] levelThreeClueNpc = {"Greater Demon", "Elf Warrior", "Tyras Guard", "Hellhound", "Dragon", "Dagannoth", "Turoth", "Jellie", "Aberrant Specter", "Gargoyle", "Nechryael", "Abyssal Demon"};

    // todo torn page make into mage books + firelighters + junk items to reward

    public static int[] mainJunk = {554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 374, 380, 362, 1379, 1381, 1383, 1385, 1387, 1065, 1099, 1135, 1097, 1169, 841, 843, 845, 847, 849};
    public static int[] junkItem1 = {1367, 1217, 1179, 1151, 1107, 1077, 1269, 1089, 1125, 1165, 1195, 1283, 1297, 1313, 1327, 1341, 1367, 1426, 334, 330, 851, 853, 855, 857, 859, 4821, 1765};
    public static int[] junkItem2 = {1430, 1371, 1345, 1331, 1317, 1301, 1287, 1271, 1211, 1199, 1073, 1161, 1183, 1091, 1111, 1123, 1145, 1199, 1681, 4823};
    public static int[] junkItem3 = {1432, 1373, 1347, 1333, 1319, 1303, 1289, 1275, 1213, 1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 4824, 386, 2491, 2497, 2503};

    public static int[] levelOneRewards = {2583, 2585, 2587, 2589, 2591, 2593, 2595, 2597, 3472, 3473, 2579, 2633, 2635, 2637, 2631, 7362, 7364, 7366, 7368, 7386, 7388, 7390, 7392, 7394, 7396, 7329, 7330, 7331, 7332, 7338, 7344, 7350, 7356, 3827, 3831, 3835, 3827, 3831, 3835, 3827, 3831, 3835, Items.BLACK_PICKAXE, Items.BLACK_CANE, Items.BLUE_ELEGANT_BLOUSE, Items.BLUE_ELEGANT_LEGS, Items.BLUE_ELEGANT_SHIRT, Items.BLUE_ELEGANT_SKIRT, Items.GREEN_ELEGANT_BLOUSE, Items.GREEN_ELEGANT_LEGS, Items.GREEN_ELEGANT_SHIRT, Items.GREEN_ELEGANT_SKIRT, Items.RED_ELEGANT_BLOUSE, Items.RED_ELEGANT_LEGS, Items.RED_ELEGANT_SHIRT, Items.RED_ELEGANT_SKIRT, Items.BOBS_BLUE_SHIRT, Items.BOBS_BLACK_SHIRT, Items.BOBS_GREEN_SHIRT, Items.BOBS_PURPLE_SHIRT, Items.BOBS_RED_SHIRT, Items.STAFF_OF_BOB_THE_CAT, Items.A_POWDERED_WIG, Items.FLARED_TROUSERS, Items.PANTALOONS, Items.SLEEPING_CAP, Items.AMULET_OF_MAGIC_T, Items.AMULET_OF_POWER_T, Items.RAIN_BOW, Items.HAM_JOINT, Items.GUTHIX_ROBE_TOP, Items.GUTHIX_ROBE_LEGS, Items.SARADOMIN_ROBE_TOP, Items.SARADOMIN_ROBE_LEGS, Items.ZAMORAK_ROBE_TOP, Items.ZAMORAK_ROBE_LEGS, Items.ANCIENT_ROBE_TOP, Items.ANCIENT_ROBE_LEGS, Items.BANDOS_ROBE_TOP, Items.BANDOS_ROBE_LEGS, Items.ARMADYL_ROBE_TOP, Items.ARMADYL_ROBE_LEGS, Items.IMP_MASK, Items.GOBLIN_MASK, Items.TEAM_CAPE_I, Items.TEAM_CAPE_X, Items.TEAM_CAPE_ZERO, Items.CAPE_OF_SKULLS, Items.WOODEN_SHIELD_G, Items.GOLDEN_CHEFS_HAT, Items.GOLDEN_APRON, Items.MONKS_ROBE_G, Items.MONKS_ROBE_G_2, Items.LARGE_SPADE};

    public static int[] levelTwoRewards = {7329, 7330, 7331, 7319, 7321, 7323, 7325, 7327, 7370, 7372, 7378, 7380, 2645, 2647, 2649, 2579, 2577, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 7334, 7340, 7346, 7352, 7358, 3828, 3832, 3836, 3829, 3833, 3837, 3829, 3833, 3837, 3829, 3833, 3837, Items.CLIMBING_BOOTS_G, Items.SPIKED_MANACLES, Items.HOLY_SANDALS, Items.BLACK_ELEGANT_LEGS, Items.BLACK_ELEGANT_SHIRT, Items.WHITE_ELEGANT_BLOUSE, Items.WHITE_ELEGANT_SKIRT, Items.PURPLE_ELEGANT_BLOUSE, Items.PURPLE_ELEGANT_LEGS, Items.PURPLE_ELEGANT_SHIRT, Items.PURPLE_ELEGANT_SKIRT, Items.PINK_ELEGANT_BLOUSE, Items.PINK_ELEGANT_LEGS, Items.PINK_ELEGANT_SHIRT, Items.PINK_ELEGANT_SKIRT, Items.GOLD_ELEGANT_BLOUSE, Items.GOLD_ELEGANT_LEGS, Items.GOLD_ELEGANT_SHIRT, Items.GOLD_ELEGANT_SKIRT, Items.WOLF_MASK, Items.WOLF_CLOAK, Items.STRENGTH_AMULET_T, Items.ADAMANT_CANE, Items.GUTHIX_MITRE, Items.SARADOMIN_MITRE, Items.ZAMORAK_MITRE, Items.ANCIENT_MITRE, Items.BANDOS_MITRE, Items.ARMADYL_MITRE, Items.GUTHIX_CLOAK, Items.SARADOMIN_CLOAK, Items.ZAMORAK_CLOAK, Items.ANCIENT_CLOAK, Items.BANDOS_CLOAK, Items.ARMADYL_CLOAK, Items.ANCIENT_STOLE, Items.ANCIENT_CROZIER, Items.ARMADYL_STOLE, Items.ARMADYL_CROZIER, Items.BANDOS_STOLE, Items.BANDOS_CROZIER, Items.CAT_MASK, Items.PENGUIN_MASK, Items.GNOMISH_FIRELIGHTER, Items.CRIER_HAT, Items.CRIER_BELL, Items.CRIER_COAT, Items.LEPRECHAUN_HAT, Items.BLACK_LEPRECHAUN_HAT, Items.BLACK_UNICORN_MASK, Items.WHITE_UNICORN_MASK, Items.ARCEUUS_BANNER, Items.HOSIDIUS_BANNER, Items.LOVAKENGJ_BANNER, Items.PISCARILIUS_BANNER, Items.SHAYZIEN_BANNER, Items.CABBAGE_ROUND_SHIELD, Items.CLUELESS_SCROLL};


    public static int[] levelThreeRewards = {3480, 3481, 3483, 3486, 3488, 2653, 2655, 2657, 2659, 2661, 2663, 2665, 2667, 2669, 2671, 2673, 2675, 2581, 2651, 7398, 7399, 7400, 7329, 7330, 7331, 7374, 7376, 7382, 7384, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629, 7336, 7342, 7348, 7354, 7360, 3830, 3834, 3838, 3830, 3834, 3838, 3830, 3834, 3838, 2639, 2640, 2643, Items.GILDED_MED_HELM, Items.GILDED_CHAINBODY, Items.GILDED_SQ_SHIELD, Items.GILDED_2H_SWORD, Items.GILDED_SPEAR, Items.GILDED_HASTA, 10286, 10288, 10190, 10292, 10294, 23209, 23212, 23215, 23218, 23221, 12321, 12323, 12325, 10350, 10348, 10346, 23242, 10352};
    public static void cleanClueInterface(Player player) {
        for (int i = 6968; i <= 6975; i++) {
            player.getPA().sendString("", i);
        }
    }
    public static void clueReward(Player player, int clueLevel, String string, boolean isDialogue, String rewardString) {
        switch (clueLevel) {
            case 1:
                if (player.clue1Amount < 4 && Misc.random(3) == 0 || player.clue1Amount == 4) {
                    player.clue1Amount = 0;
                    if (isDialogue) {
                        //Dialogues.setNextDialogue(player, 10009, 1);
                        // player.getDialogue().sendNpcChat(rewardString, Dialogues.HAPPY);
                    } else {
                        itemReward(player, clueLevel);
                    }
                } else {
                    player.getDH().sendItemStatement(string, CLUE_ITEM);
                    addNewClue(player, clueLevel);
                    player.clue1Amount++;
                }
                break;
            case 2:
                if (player.clue2Amount < 5 && Misc.random(4) == 0 || player.clue2Amount == 5) {
                    player.clue2Amount = 0;
                    if (isDialogue) {
                        // Dialogues.setNextDialogue(player, 10009, 1);
                        //player.getDialogue().sendNpcChat(rewardString, Dialogues.HAPPY);
                    } else {
                        itemReward(player, clueLevel);
                    }
                } else {
                    player.getDH().sendItemStatement(string, CLUE_ITEM);
                    // player.getDialogue().endDialogue();
                    addNewClue(player, clueLevel);
                    player.clue2Amount++;
                }
                break;
            case 3:
                if (player.clue3Amount < 7 && Misc.random(6) == 0 || player.clue3Amount == 7) {
                    player.clue3Amount = 0;
                    if (isDialogue) {

                    } else {
                        itemReward(player, clueLevel);
                    }
                } else {
                    player.getDH().sendItemStatement(string, CLUE_ITEM);
                    // player.getDialogue().endDialogue();
                    addNewClue(player, clueLevel);
                    player.clue3Amount++;
                }
                break;
        }
    }
    private static void addNewClue(Player player, int clueLevel) {
        player.getItems().addItem(getRandomClue(clueLevel), 1);
    }
    public static void handleCasket(Player player, int itemId) {
        switch (itemId) {
            case REWARD_CASKET_LV1 :
                Achievements.increase(player, AchievementType.CLUES, 1);
                player.getItems().deleteItem2(itemId, 1);
                itemReward(player, 1);
                break;
            case REWARD_CASKET_LV2 :
                Achievements.increase(player, AchievementType.CLUES, 1);
                player.getItems().deleteItem2(itemId, 1);
                itemReward(player, 2);
                break;
            case REWARD_CASKET_LV3 :
                Achievements.increase(player, AchievementType.CLUES, 1);
                player.getItems().deleteItem2(itemId, 1);
                itemReward(player, 3);
                break;
            case CASKET_LV1 :
                player.getItems().deleteItem2(itemId, 1);
                clueReward(player, 1, "You've found another clue!", false, "Here is your reward!");
                break;
            case CASKET_LV2 :
                player.getItems().deleteItem2(itemId, 1);
                clueReward(player, 2, "You've found another clue!", false, "Here is your reward!");
                break;
            case CASKET_LV3 :
                player.getItems().deleteItem2(itemId, 1);
                clueReward(player, 3, "You've found another clue!", false, "Here is your reward!");
                break;
        }

    }
    public static void itemReward(Player player, int clueLevel) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        int random = Misc.random(4) + 2;
        switch (clueLevel) {
            case 1 :
                for (int i = 0; i < random; i++) {
                    int percent = Misc.random(100);
                    if (percent <= 7) {
                        array.add(levelOneRewards[Misc.random(levelOneRewards.length - 1)]);
                    } else if (percent > 7 && percent <= 30 && !array.contains(995)) {
                        array.add(995);
                    } else {
                        array.add(Misc.random(2) == 1 ? junkItem1[Misc.random(junkItem1.length - 1)] : mainJunk[Misc.random(mainJunk.length - 1)]);
                    }
                }
                break;
            case 2 :
                for (int i = 0; i < random; i++) {
                    int percent = Misc.random(100);
                    if (percent <= 7) {
                        array.add(levelTwoRewards[Misc.random(levelTwoRewards.length - 1)]);
                    } else if (percent > 7 && percent <= 30 && !array.contains(995)) {
                        array.add(995);
                    } else {
                        array.add(Misc.random(2) == 1 ? junkItem2[Misc.random(junkItem2.length - 1)] : mainJunk[Misc.random(mainJunk.length - 1)]);
                    }

                }
                break;
            case 3 :
                for (int i = 0; i < random; i++) {
                    int percent = Misc.random(100);
                    if (percent <= 7) {
                        array.add(levelThreeRewards[Misc.random(levelThreeRewards.length - 1)]);
                    } else if (percent > 7 && percent <= 30 && !array.contains(995)) {
                        array.add(995);
                    } else {
                        array.add(Misc.random(2) == 1 ? junkItem3[Misc.random(junkItem3.length - 1)] : mainJunk[Misc.random(mainJunk.length - 1)]);
                    }
                }
                break;
        }

        int[] items = new int[random];
        int[] amounts = new int[random];
        GameItem[] item = new GameItem[random];
        for (int i = 0; i < random; i++) {
            items[i] = array.get(i);
            amounts[i] = new GameItem(items[i]).isStackable() ? items[i] == 995 ? Misc.random(10000) : Misc.random(4) + 11 : 1;
            if (new GameItem(items[i]).getDef().getName().toLowerCase().contains("page")) {
                amounts[i] = 1;
            }
            item[i] = new GameItem(items[i], amounts[i]);
            player.getItems().addItem(items[i], amounts[i]);
        }
        player.getPA().sendItems(player, 6963, Arrays.asList(item), 0);
        player.getPA().showInterface(6960);
        switch (clueLevel) {
            case 1:
                player.setEasyClueCounter(player.getEasyClueCounter() + 1);
                player.sendMessage("<col=2d256d>You have completed " + player.getEasyClueCounter() + " Easy Treasure Trails.");
                break;
            case 2:
                player.setMediumClueCounter(player.getMediumClueCounter() + 1);
                player.sendMessage("<col=2d256d>You have completed " + player.getMediumClueCounter() + " medium Treasure Trails.");
                break;
            case 3:
                player.setHardClueCounter(player.getHardClueCounter() + 1);
                player.sendMessage("<col=2d256d>You have completed " + player.getHardClueCounter() + " hard Treasure Trails.");
                break;
            case 4:
                player.setMasterClueCounter(player.getMasterClueCounter() + 1);
                player.sendMessage("<col=2d256d>You have completed " + player.getMasterClueCounter() + " master Treasure Trails.");
                PetHandler.roll(player, PetHandler.Pets.BLOODHOUND);
                break;
        }
       // player.sendMessage("Well done, you've completed the Treasure Trail!");
    }
    public static void dropClue(Player player, NPC npc, Location3D location) {
        if (Misc.random(25) != 0) { //1% chance
            return;
        }
        //if (player.hasClueScroll()) {
            //return;
     //   }
        for (String element : levelOneClueNpc) {
            if (npc.getDefinition().getName().toLowerCase().contains(element.toLowerCase())) {
                //GroundItemManager.getManager().dropItem(new GroundItem(new Item(getRandomClue(1)), player,  new Position(npc.getPosition().getX(), npc.getPosition().getY(), npc.getPosition().getZ())));
                Server.itemHandler.createGroundItem(player, getRandomClue(1), location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                player.sendMessage("@bla@You notice a @blu@clue scroll@bla@ on the floor.");
                return;
            }
        }
        for (String element : levelTwoClueNpc) {
            if (npc.getDefinition().getName().toLowerCase().contains(element.toLowerCase())) {
                Server.itemHandler.createGroundItem(player, getRandomClue(2),location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                player.sendMessage("@bla@You notice a @blu@clue scroll@bla@ on the floor.");
               // GroundItemManager.getManager().dropItem(new GroundItem(new Item(getRandomClue(2)), player,  new Position(npc.getPosition().getX(), npc.getPosition().getY(), npc.getPosition().getZ())));
                return;
            }
        }
        for (String element : levelThreeClueNpc) {
            if (npc.getDefinition().getName().toLowerCase().contains(element.toLowerCase())) {
                Server.itemHandler.createGroundItem(player, getRandomClue(3), location.getX(), location.getY(), location.getZ(), 1, player.getIndex());
                player.sendMessage("@bla@You notice a @blu@clue scroll@bla@ on the floor.");
               // GroundItemManager.getManager().dropItem(new GroundItem(new Item(getRandomClue(3)), player,  new Position(npc.getPosition().getX(), npc.getPosition().getY(), npc.getPosition().getZ())));
                return;
            }
        }
    }

    public static int getRandomClue(int clueLevel) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        switch (clueLevel) {
            case 1 :
                array.add(MapScrolls.getRandomScroll(1));
                array.add(SearchScrolls.getRandomScroll(1));
             //   array.add(SpeakToScrolls.getRandomScroll(1));

                array.add(MapScrolls.getRandomScroll(1));
                array.add(SearchScrolls.getRandomScroll(1));
                //array.add(SpeakToScrolls.getRandomScroll(1));

                array.add(DiggingScrolls.getRandomScroll(1));

                return array.get(Misc.random(array.size() - 1));

            case 2 :
              //  array.add(AnagramsScrolls.getRandomScroll(2));
                array.add(MapScrolls.getRandomScroll(2));
                array.add(SearchScrolls.getRandomScroll(2));
               // array.add(SpeakToScrolls.getRandomScroll(2));
                array.add(CoordinateScrolls.getRandomScroll(2));

                return array.get(Misc.random(array.size() - 1));

            case 3 :
                //array.add(AnagramsScrolls.getRandomScroll(3));
                array.add(MapScrolls.getRandomScroll(3));
                array.add(SearchScrolls.getRandomScroll(3));
               // array.add(SpeakToScrolls.getRandomScroll(3));
                array.add(DiggingScrolls.getRandomScroll(3));
                array.add(CoordinateScrolls.getRandomScroll(3));

                return array.get(Misc.random(array.size() - 1));

        }

        return -1;
    }
}
