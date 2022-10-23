package io.exilius.content.item.lootable.impl;

import io.exilius.content.bosses.Nex;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.item.lootable.LootRarity;
import io.exilius.content.item.lootable.Lootable;
import io.exilius.model.Items;
import io.exilius.model.definitions.ItemDef;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static io.exilius.content.bosses.Nex;

public class NexChest implements Lootable {

    private static final int KEY = Nex.KEY;
    private static final int ANIMATION = 881;

    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    static {
        items.put(LootRarity.COMMON, Arrays.asList(
                //UPDATE HESPORICHESTITEMS TOO
                //new GameItem(21046, 2),//15% chest rate tomb
                new GameItem(Items.BURNT_PAGE, 25),
                new GameItem(Items.OVERLOAD_4, 3),
                new GameItem(Items.WILLOW_LOGS_NOTED, 251),
                new GameItem(Items.MAPLE_LOGS_NOTED, 181),
                new GameItem(Items.YEW_LOGS_NOTED, 120),
                new GameItem(Items.MAGIC_LOGS_NOTED, 75),
                new GameItem(Items.DRAGON_ARROW, 250),

                new GameItem(Items.MAGIC_ROOTS_NOTED, 30),
                new GameItem(Items.CRUSHED_NEST_NOTED, 30),
                new GameItem(Items.WINE_OF_ZAMORAK_NOTED, 30),
                new GameItem(Items.SNAPE_GRASS_NOTED, 40),

                new GameItem(Items.DRAGON_DART_TIP, 159),
                new GameItem(Items.RUNE_DART_TIP, 550),
                new GameItem(Items.ADAMANT_DART_TIP, 350),

                new GameItem(Items.RUNE_ARROWTIPS, 120),
                new GameItem(Items.DRAGON_ARROWTIPS, 80),

                new GameItem(Items.DRAGON_BOLTS_UNF, 80),
                new GameItem(Items.RUNITE_BOLTS_UNF, 100),

                new GameItem(Items.RAW_SHARK_NOTED, 70),
                new GameItem(Items.RAW_ANGLERFISH_NOTED, 100),

                new GameItem(Items.ADAMANTITE_BAR_NOTED, 27),
                new GameItem(Items.RUNITE_BAR_NOTED, 21),
                new GameItem(Items.UNCUT_DIAMOND_NOTED, 21),

                new GameItem(Items.RANARR_POTION_UNF_NOTED, 16),
                new GameItem(Items.DWARF_WEED_POTION_UNF_NOTED, 21),
                new GameItem(Items.HARRALANDER_POTION_UNF_NOTED, 31),

                new GameItem(Items.DRAGON_BONES_NOTED, 30),
                new GameItem(Items.RANARR_WEED_NOTED, 12),
                new GameItem(Items.HARRALANDER_NOTED, 24),
                new GameItem(Items.TORSTOL_NOTED, 120),
                new GameItem(Items.RANGER_BOOTS, 1),
                new GameItem(Items.DRAGON_BOOTS, 1),
                new GameItem(Items.INFINITY_BOOTS, 1),
                new GameItem(Items.DRAGON_BOLTS_UNF, 200),
                new GameItem(Items.DRAGON_ARROW, 400),
                new GameItem(Items.TORSTOL_NOTED, 200)

        ));

        items.put(LootRarity.RARE, Arrays.asList(
                // UPDATE HESPORICHESTITEMS TOO
                new GameItem(26376, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(26378, 1),
                new GameItem(26380, 1),
                new GameItem(26376, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(26378, 1),
                new GameItem(26380, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(Items.ZARYTE_CROSSBOW, 1),
                new GameItem(Items.ZARYTE_VAMBRACES, 1),
                new GameItem(Items.ZARYTE_CROSSBOW, 1),
                new GameItem(Items.BANDOS_TASSETS, 1),
                new GameItem(Items.BANDOS_CHESTPLATE, 1),
                new GameItem(Items.ZARYTE_VAMBRACES, 1)

        ));
    }

    private static GameItem randomChestRewardsCommon() {

        List<GameItem> itemList = NexChestItems.getItems().get(LootRarity.COMMON);
        return Misc.getRandomItem(itemList);
    }

    private static GameItem randomChestRewardsRare() {
        List<GameItem> itemList = NexChestItems.getItems().get(LootRarity.RARE);
        return Misc.getRandomItem(itemList);
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }

    @Override
    public void roll(Player c) {
        int random = Misc.random(4100);
        int rareChance = 4070;
        if (c.getItems().playerHasItem(21046)) {
            rareChance = 4030;
            c.getItems().deleteItem(21046, 1);
            c.sendMessage("@red@You sacrifice your @cya@tablet @red@for an increased drop rate." );
            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
        }
        if (random < rareChance) {
            if (c.getItems().playerHasItem(KEY)) {
                c.getItems().deleteItem(KEY, 1);
                c.getItems().addItem(995, 500_000 + Misc.random(1_000_000));
                c.startAnimation(ANIMATION);
                GameItem reward = randomChestRewardsCommon();
                GameItem reward2 = randomChestRewardsCommon();
                c.getItems().addItem(reward.getId(), reward.getAmount() * 1);
                c.getItems().addItem(reward2.getId(), reward2.getAmount() * 1);
                c.sendMessage("@blu@You received common items out of the chest.");

            } else {
                c.sendMessage("@blu@The chest is locked, it won't budge!");
            }
        } else if (random >= rareChance) {
            if (c.getItems().playerHasItem(KEY)) {
                c.getItems().deleteItem(KEY, 1);
                c.getItems().addItem(995, 500_000 + Misc.random(1_000_000));
                c.startAnimation(ANIMATION);
                GameItem reward = randomChestRewardsRare();
                c.getItems().addItem(reward.getId(), reward.getAmount() * 1);
                if (reward.getId() != Items.ZARYTE_CROSSBOW) {
                    ItemDef def = ItemDef.forId(reward.getId());
                    PlayerHandler.executeGlobalMessage("<col=65280><shad=1>["+ c.getDisplayName() + "]</col></shad>@bla@[<col=3d7018>Nex@bla@] The Nex chest rewarded a<col=47831c> "+ ItemDef.forId(reward.getId()).getName() + "!" );                }
            } else {
                c.sendMessage("@blu@The chest is locked, it won't budge!");
            }
        }
    }
}
