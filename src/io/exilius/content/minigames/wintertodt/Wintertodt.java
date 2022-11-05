package io.exilius.content.minigames.wintertodt;

import io.exilius.content.combat.Hitmark;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;

import java.util.Arrays;
import java.util.List;

public class Wintertodt {

    public static final int REGION_ID = 6462;
    static int x = (REGION_ID >> 8) << 6;
    static int y = (REGION_ID & 0xFF) << 6;

    static final int EMPTY_BRAZIER = 29312;
    static final int BROKEN_BRAZIER = 29313;
    static final int BURNING_BRAZIER = 29314;

    private static final int MAX_HP = 3500;
    private static int health = 0;

    private static final String[] PYROMANCER_DEAD_TEXT = {"My flame burns low.", "Mummy!", "I think I'm dying.", "We are doomed.", "Ugh, help me!"};
    private static boolean started = false;

    public static final int BRUMA_ROOT = 20695;
    public static final int BRUMA_KINDLING = 20696;
    public static final int REJUV_POT = 20697;
    public static final int BRUMA_HERB = 20698;

    static final int PYROMANCER = 7371;
    public static final int INCAPACITATED_PYROMANCER = 7372;
    private static final int FLAME = 7373;

    private static final int SNOW_EFFECT = 26690;

    private static final int ACTIVE_STORM = 29308;
    private static final int INACTIVE_STORM = 29309;

    private static final GlobalObject WINTERTODT = new GlobalObject(ACTIVE_STORM, 1627, 4004, 0, 0, 10);

    private static final List<Integer> GAME_ITEMS = Arrays.asList(BRUMA_ROOT, Wintertodt.BRUMA_KINDLING, 20697, Wintertodt.BRUMA_HERB, 20699, 20700, 20701, 20702);

    public static final Brazier[] BRAZIERS = {
            new Brazier(new GlobalObject(29312, 1620, 3997, 0, 0, 10), NPCSpawning.spawnNpc(7371, 1619, 3996, 0, 1, 0), 0, 0), // sw
            new Brazier(new GlobalObject(29312, 1620, 4015, 0, 0, 10), NPCSpawning.spawnNpc(7371, 1619, 4018, 0, 1, 0), 0, 2), // nw
            new Brazier(new GlobalObject(29312, 1638, 4015, 0, 10, 0),  NPCSpawning.spawnNpc(7371,1641, 4018, 0, 1, 0), 2, 2), // ne
            new Brazier(new GlobalObject(29312, 1638, 3997, 0, 10, 0),  NPCSpawning.spawnNpc(7371,1641, 3996, 0, 1, 0), 2, 0) // se
    };

    private static void applyColdDamage(Player c) {
        PlayerHandler.nonNullStream()
                .filter(p -> Boundary.isIn(c, Boundary.FLOWER_POKER_AREA) && p.heightLevel == 0);
        if (c.getX() <= 3987 || Misc.random(25) == 1) {
            c.appendDamage(getColdDamage(c), Hitmark.HIT);
            c.sendMessage("The cold of the Wintertodt seeps into your bones.");
        }
    };
    public static int getColdDamage(Player player) {
        return (int) ((16.0 - getWarmItemsWorn(player) - (2 * Math.min(3, getBraziersLit()))) * (player.playerLevel[3] + 1) / player.playerLevel[11]);
    }

    public static int getBrazierAttackDamage(Player player) {
        return (int) ((10.0 - getWarmItemsWorn(player)) * (player.playerLevel[3] + 1) / player.playerLevel[11] * 2);
    }

    public static int getAreaAttackDamage(Player player) {
        return (int) ((10.0 - getWarmItemsWorn(player)) * (player.playerLevel[3] + 1) / player.playerLevel[11] * 3);
    }
    public static int getWarmItemsWorn(Player player) {
        return 4; //todo
    }
    public static int getBraziersLit() {
        int count = 0;
        for (Brazier b : BRAZIERS)
            if (b.getObject().getObjectId() == BURNING_BRAZIER)
                count++;
        return count;
    }

}
