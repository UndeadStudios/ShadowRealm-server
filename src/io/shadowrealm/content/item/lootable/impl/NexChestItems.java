package io.shadowrealm.content.item.lootable.impl;

import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NexChestItems {

    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    public static Map<LootRarity, List<GameItem>> getItems() {
        return items;
    }

    static {
        items.put(LootRarity.COMMON, Arrays.asList(
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
                new GameItem(Items.TORVA_FULLHELM, 1),
                new GameItem(Items.TORVA_PLATEBODY, 1),
                new GameItem(Items.TORVA_PLATELEGS, 1),
                new GameItem(Items.ZARYTE_CROSSBOW, 1),
                new GameItem(Items.ZARYTE_VAMBRACES, 1),
                new GameItem(Items.TORVA_FULLHELM, 1),
                new GameItem(Items.TORVA_PLATEBODY, 1),
                new GameItem(Items.TORVA_PLATELEGS, 1),
                new GameItem(Items.ZARYTE_CROSSBOW, 1),
                new GameItem(Items.ZARYTE_VAMBRACES, 1)
                ));
    }

}
