package io.exilius.content.skills.runecrafting;

import io.exilius.model.entity.player.Player;

public class Tiaras {

    public static boolean bindTiara(Player player, int itemId, int objectId) {
        for (int[] ruin : RealRunecrafting.RC_DATA) {
            if (itemId == ruin[0] && objectId == ruin[2]) {
                if (player.getItems().playerHasItem(5525)) {
                    player.getItems().deleteItem(ruin[0], 1);
                    player.getItems().deleteItem(5525, 1);
                    player.getItems().addItem(ruin[1], 1);
                    player.sendMessage("You bind the power of the talisman into the tiara.");
                }
                return true;
            }
        }
        return false;
    }
    public static void handleTiaraEquip(Player player, int id) {
        int[][] tiaras = {
                { 5527, 1073799169, 1073799168, 491 },
                { 5529, 1073799170, 1073799168, 491 },
                { 5531, 1073799172, 1073799168, 491 },
                { 5535, 1073799176, 1073799168, 491 },
                { 5537, 1073799184, 1073799168, 491 },
                { 5533, 1073799200, 1073799168, 491 },
                { 5539, 1073799232, 1073799168, 491 },
                { 5543, 1073799680, 1073799168, 491 },
                { 5541, 1073799424, 1073799168, 491 },
                { 5545, 9, 9, 491 },
                { 5547, 1073800192, 1073799168, 491 },
                { 5549, 1, 0, 3413 }
        };
        for (int[] t : tiaras) {
            if (t[0] == id) {
                    player.getPA().sendConfig(t[3], t[1]);
                return;
            }
            player.getPA().sendConfig(t[3], 0);
        }
    }
    public static void handleTiaraunEquip(Player player, int id) {
        int[][] tiaras = {
                { 5527, 1073799169, 1073799168, 491 },
                { 5529, 1073799170, 1073799168, 491 },
                { 5531, 1073799172, 1073799168, 491 },
                { 5535, 1073799176, 1073799168, 491 },
                { 5537, 1073799184, 1073799168, 491 },
                { 5533, 1073799200, 1073799168, 491 },
                { 5539, 1073799232, 1073799168, 491 },
                { 5543, 1073799680, 1073799168, 491 },
                { 5541, 1073799424, 1073799168, 491 },
                { 5545, 9, 9, 491 },
                { 5547, 1073800192, 1073799168, 491 },
                { 5549, 1, 0, 3413 }
        };
        for (int[] t : tiaras) {
            if (t[0] == id) {
                player.getPA().sendConfig(t[3], 0);
                return;
            }
        }
    }
}
