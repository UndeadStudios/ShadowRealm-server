package io.shadowrealm.content.item.lootable.impl;

import com.google.common.collect.Lists;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.bosspoints.BossPoints;
import io.shadowrealm.content.event.eventcalendar.EventChallenge;
import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.content.item.lootable.Lootable;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.npc.pets.PetHandler;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.model.items.ImmutableItem;
import io.shadowrealm.util.Misc;

import java.util.*;

public class TheatreOfBloodChest implements Lootable {

    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    static {
        items.put(LootRarity.COMMON, Arrays.asList(
                new GameItem(Items.DEATH_RUNE, 600),
                new GameItem(Items.BLOOD_RUNE, 600),
                new GameItem(Items.COAL_NOTED, 600),
                new GameItem(Items.GOLD_ORE_NOTED, 360),
                new GameItem(Items.ADAMANTITE_ORE_NOTED, 150),
                new GameItem(Items.RUNITE_ORE_NOTED, 72),
                new GameItem(Items.GRIMY_CADANTINE_NOTED, 60),
                new GameItem(Items.GRIMY_AVANTOE_NOTED, 48),
                new GameItem(Items.GRIMY_TOADFLAX_NOTED, 44),
                new GameItem(Items.GRIMY_KWUARM_NOTED, 43),
                new GameItem(Items.GRIMY_IRIT_LEAF_NOTED, 40),
                new GameItem(Items.GRIMY_RANARR_WEED_NOTED, 36),
                new GameItem(Items.GRIMY_SNAPDRAGON_NOTED, 32),
                new GameItem(Items.GRIMY_LANTADYME_NOTED, 31),
                new GameItem(Items.GRIMY_DWARF_WEED_NOTED, 28),
                new GameItem(Items.GRIMY_TORSTOL_NOTED, 24),
                new GameItem(Items.BATTLESTAFF_NOTED, 18),
                new GameItem(Items.RUNE_BATTLEAXE_NOTED, 4),
                new GameItem(Items.RUNE_PLATEBODY_NOTED, 4),
                new GameItem(Items.RUNE_CHAINBODY_NOTED, 4),
                new GameItem(995, 7_500_000)
        ));

        items.put(LootRarity.RARE, Arrays.asList(
                new GameItem(Items.JUSTICIAR_FACEGUARD),
                new GameItem(6830, 1),  //raids 2 box
                new GameItem(2403, 1),  //$10 Scroll
                new GameItem(786, 1),  //$50 scroll
                new GameItem(25742, 1),//holy kit
                new GameItem(25744, 1),//sanguine kit
                new GameItem(Items.JUSTICIAR_CHESTGUARD),

                new GameItem(Items.JUSTICIAR_LEGGUARDS),

                new GameItem(Items.GHRAZI_RAPIER),

                new GameItem(Items.SANGUINESTI_STAFF),

                new GameItem(Items.AVERNIC_DEFENDER_HILT),
                new GameItem(Items.SCYTHE_OF_VITUR)

        ));
    }

    public static ArrayList<GameItem> getRareDrops() {
        ArrayList<GameItem> drops = new ArrayList<>();
        List<GameItem> found = items.get(LootRarity.RARE);
        for(GameItem f : found) {
            boolean foundItem = false;
            for(GameItem drop : drops) {
                if (drop.getId() == f.getId()) {
                    foundItem = true;
                    break;
                }
            }
            if (!foundItem) {
                drops.add(f);
            }
        }
        return drops;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
    private static Player c;

    public static List<GameItem> getRandomItems(boolean rollRares, int size) {
        List<GameItem> rewards = Lists.newArrayList();
        int rareChance = 8;
        if (Hespori.activeKronosSeed) {
            rareChance = 4;
        }

        if (rollRares && Misc.trueRand(rareChance) == 0) {
            rewards.add(Misc.getRandomItem(items.get(LootRarity.RARE)));
        } else {
            for (int count = 0; count < 3; count++) {
                rewards.add(Misc.getRandomItem(items.get(LootRarity.COMMON)));
            }
        }
        return rewards;
    }

    public static boolean containsRare(List<GameItem> itemList) {
        return items.get(LootRarity.RARE).stream().anyMatch(rareItem -> itemList.stream().anyMatch(itemReward -> rareItem.getId() == itemReward.getId()));
    }

    /**
     * Reward items that are generated when the treasure room is initialised.
     */
    public static void rewardItems(Player player, List<GameItem> rewards) {
        BossPoints.addManualPoints(player, "theatre of blood");

        PetHandler.roll(player, PetHandler.Pets.LIL_ZIK);
        player.getEventCalendar().progress(EventChallenge.COMPLETE_TOB);
//        LeaderboardUtils.addCount(LeaderboardType.TOB, player, 1);
        Achievements.increase(player, AchievementType.TOB, 1);
        if (Hespori.activeKronosSeed == true) {
            player.sendMessage("@red@The @gre@Kronos seed@red@ doubles your chances!" );
        }
        player.getItems().addItem(995, 500_000 + Misc.random(1_000_000));
        List<GameItem> rareItemList = items.get(LootRarity.RARE);
        for (GameItem reward : rewards) {
            if (rareItemList.stream().anyMatch(rareItem -> reward.getId() == rareItem.getId())) {
                if (player.getRights().isNotAdmin()) {
                    PlayerHandler.executeGlobalMessage("@pur@" + player.getDisplayNameFormatted() + " received a drop: "
                            + ItemDef.forId(reward.getId()).getName() + " x " + reward.getAmount() + " from Theatre of Blood.");
                }
            }
                player.getCollectionLog().handleDrop(player, Npcs.THE_MAIDEN_OF_SUGADINTI, rewards.get(0).getId(), 1);
            }


        for (GameItem item : rewards) {
            player.getInventory().addAnywhere(new ImmutableItem(item.getId(), item.getAmount()));
        }

        player.getTobContainer().displayRewardInterface(rewards);
    }

    /**
     * To be removed but kept for now.
     */
    @Override
    public void roll(Player player) {}
}
