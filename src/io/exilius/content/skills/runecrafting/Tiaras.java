package io.exilius.content.skills.runecrafting;

import io.exilius.model.entity.player.Player;

public class Tiaras {


    public static void handleTiara(Player player, int id) {
        int[][] tiaras = {
                { 5527, 1073799169, 1073799168, 491 },
                { 5529, 1073799170, 1073799168, 491 },
                { 5531, 1073799172, 1073799168, 491 },
                { 5535, 1073799176, 1073799168, 491 },
                { 5537, 1073699184, 1073799168, 491 },
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
                player.getPA().sendConfig(t[3],  t[1]);
                return;
            }
            player.getPA().sendConfig(t[3], t[2]);
        }
    }

}
