package io.shadowrealm.content.skills.runecrafting;

import io.shadowrealm.model.entity.player.Player;

public class RealRunecrafting {
    private final Player c;

    public RealRunecrafting(Player player) {
        this.c = player;
    }

    public static void locate(Player c, int xPos, int yPos) {
        String X = "";
        String Y = "";
        if (c.absX >= xPos) {
            X = "west";
        }
        if (c.absY > yPos) {
            Y = "south";
        }
        if (c.absX < xPos) {
            X = "east";
        }
        if (c.absY <= yPos) {
            Y = "north";
        }
        c.sendMessage("You need to travel " + Y + "-" + X + ".");
    }

    public final static int RC_DATA[][] = {
            { 1438, 5527, 34760 }, // air
            { 1448, 5529, 34761 }, // mind
            { 1444, 5531, 34762 }, // water
            { 1440, 5535, 34763 }, // earth
            { 1442, 5537, 34764 }, // fire
            { 1446, 5533, 34765 }, // body
            { 1454, 5539, 34766 }, // cosmic
            { 1452, 5543, 34769 }, // chaos
            { 1462, 5541, 34768 }, // nature
            { 1458, 5545, 34767 }, // law
            { 1456, 5547, 34770 }, // death
            { 1450, 5549, 25380 },//blood altar
    };

    private enum Altars {
        AIR_ALTAR(34813, new int[] { 1438, 5527 }, new int[] { 2842, 4829 }),
        MIND_ALTAR(34814, new int[] { 1448, 5529 }, new int[] { 2793, 4828 }),
        WATER_ALTAR(34815, new int[] { 1444, 5531 }, new int[] { 2713, 4836 }),
        EARTH_ALTAR(34816, new int[] { 1440, 5535 }, new int[] { 2655, 4831 }),
        FIRE_ALTAR(34817, new int[] { 1442, 5537 }, new int[] { 2577, 4845 }),
        BODY_ALTAR(34818, new int[] { 1446, 5533 }, new int[] { 2521, 4834 }),
        COSMIC_ALTAR(34819, new int[] { 1454, 5539 }, new int[] { 2162, 4833 }),
        CHAOS_ALTAR(34820, new int[] { 1452, 5543 }, new int[] { 2268, 4842 }),
        NATURE_ALTAR(34821, new int[] { 1462, 5541 }, new int[] { 2400, 4835 }),
        LAW_ALTAR(34822, new int[] { 1458, 5545 }, new int[] { 2464, 4819 }),
        DEATH_ALTAR(34823, new int[] { 1456, 5547 }, new int[] { 2208, 4831 }),
        BLOOD_ALTAR(25380, new int[] {1450, 5549 }, new int[] {3235, 4829});

        int objId;
        int[] keys, loc;

        private Altars(int objId, int[] keys, int[] loc) {
            this.objId = objId;
            this.loc = loc;
            this.keys = keys;
        }

        private int getObj() {
            return objId;
        }

        private int[] getKeys() {
            return keys;
        }

        private int[] getNewLoc() {
            return loc;
        }
    }

    public void enterAltar(int objId, int itemUse) {
        Altars a = forAltar(objId);
        if (a != null) {
            if ((a.getKeys()[1] == c.playerEquipment[c.playerHat]) || a.getKeys()[0] == itemUse) {
                c.getPlayerAssistant().movePlayer(a.getNewLoc()[0], a.getNewLoc()[1], 0);
                c.sendMessage("You feel a powerful force take hold of you...");
            } else {
                c.sendMessage("Nothing interesting happens.");
            }
        }
    }

    private enum Altar_Data {
        AIR(34760, 1, 6, 556, new int[][] { { 11, 2 }, { 22, 3 }, { 33, 4 }, { 44, 5 }, { 55, 6 }, { 66, 7 }, { 77, 8 }, { 88, 9 }, { 99, 9 } }),
        MIND(34761, 5, 6, 558, new int[][] { { 14, 2 }, { 28, 3 }, { 42, 4 }, { 56, 5 }, { 70, 6 }, { 84, 7 }, { 98, 8 } }),
        WATER(34762, 9, 7, 555, new int[][] { { 19, 2 }, { 38, 3 }, { 57, 4 }, { 76, 5 }, { 95, 6 } }),
        EARTH(34763, 9, 7, 557, new int[][] { { 26, 2 }, { 52, 3 }, { 78, 4 } }),
        FIRE(34764, 14, 7, 554, new int[][] { { 26, 2 }, { 52, 3 }, { 78, 4 } }),
        BODY(34765, 20, 8, 559, new int[][] { { 35, 2 }, { 70, 3 } }),
        COSMIC(34766, 27, 9, 564, new int[][] { { 59, 2 } }),
        CHAOS(34769, 35, 9, 562, new int[][] { { 74, 2 } }),
        NATURE(34768, 44, 10, 561, new int[][] { { 91, 2 } }),
        LAW(34767, 54, 11, 563, new int[][] {}),
        DEATH(34770, 65, 13, 560, new int[][] { { 99, 2 } }),
        BLOOD(43479, 77, 10, 565, new int[][] {}),
        SOUL(-1, 90, 17, 566, new int[][] {}),
        ASTRAL(34771, 40, 10, 9075, new int[][] { { 82, 2 } });

        int altarID, levelReq, xp, rewardedRune;
        int[][] multiRunes;

        private Altar_Data(int altarID, int levelReq, int xp, int rewardedRune, int[][] multiRunes) {
            this.altarID = altarID;
            this.levelReq = levelReq;
            this.xp = xp;
            this.rewardedRune = rewardedRune;
            this.multiRunes = multiRunes;
        }
    }

    public Altar_Data forObj(int obj) {
        for (Altar_Data ad : Altar_Data.values()) {
            if (ad.altarID == obj) {
                return ad;
            }
        }
        return null;
    }

    int objId;
    int[] keys, loc;

    public Altars forAltar(int id) {
        for (Altars a : Altars.values()) {
            if (a.getObj() == id) {
                return a;
            }
        }
        return null;
    }

    public boolean craftRunes(int obj) {
        Altar_Data ad = forObj(obj);
        if (ad != null) {
            if (c.playerLevel[c.playerRunecrafting] >= ad.levelReq) {
                if(c.getItems().playerHasItem(7936) || c.getItems().playerHasItem(1436)) {
                    getMultiSupport(obj);
                    c.startAnimation(791);
                    c.gfx100(186);
                } else {
                    c.sendMessage("You do not have any essence to craft this rune.");
                }
            } else {
                c.sendMessage("You need a Runecrafting level of " + ad.levelReq + " to craft this rune.");
            }
        }
        return false;
    }

    public void getMultiSupport(int obj) {
        Altar_Data ad = forObj(obj);
        if (ad != null) {
            int amount = c.getItems().getItemAmount(7936), amount2 = c.getItems().getItemAmount(1436);
            if (amount2 > 0 && amount > 0) {
                c.getItems().deleteItem2(7936, c.getItems().getItemAmount(7936));
                c.getItems().addItem(ad.rewardedRune, amount * (getMultiplier(ad) <= 1 ? 1 : getMultiplier(ad)));
                c.getPlayerAssistant().addSkillXPMultiplied(ad.xp * amount, c.playerRunecrafting, true);
                c.getItems().deleteItem2(1436, c.getItems().getItemAmount(1436));
                c.getItems().addItem(ad.rewardedRune, amount2 * (getMultiplier(ad) <= 1 ? 1 : getMultiplier(ad)));
                c.getPlayerAssistant().addSkillXPMultiplied(ad.xp * amount2, c.playerRunecrafting, true);
            } else if (amount > 0) {
                c.getItems().deleteItem2(7936, c.getItems().getItemAmount(7936));
                c.getItems().addItem(ad.rewardedRune, amount * (getMultiplier(ad) <= 1 ? 1 : getMultiplier(ad)));
                c.getPlayerAssistant().addSkillXPMultiplied(ad.xp * amount, c.playerRunecrafting, true);
            } else if (amount2 > 0) {
                c.getItems().deleteItem2(1436, c.getItems().getItemAmount(1436));
                c.getItems().addItem(ad.rewardedRune, amount2 * (getMultiplier(ad) <= 1 ? 1 : getMultiplier(ad)));
                c.getPlayerAssistant().addSkillXPMultiplied(ad.xp * amount2, c.playerRunecrafting, true);
            } else {
                c.sendMessage("You don't have any essence left.");
            }
        }
    }

    public int getMultiplier(Altar_Data ad) {
        int temp = 1;
        for (int[] multiRune : ad.multiRunes) {
            for (int j = 0; j < multiRune.length; j++) {
                if (c.playerLevel[c.playerRunecrafting] >= multiRune[0]) {
                    temp++;
                }
            }
        }
        return temp;
    }
}
