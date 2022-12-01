package io.exilius.content.item.lootable.impl;

import io.exilius.content.item.lootable.LootRarity;
import io.exilius.content.item.lootable.MysteryBoxLootable;
import io.exilius.model.Items;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrCapeMysteryBox extends MysteryBoxLootable {

    /**
     * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
     */
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    /**
     * Stores an array of items into each map with the corresponding rarity to the list
     */

    static {
        items.put(LootRarity.COMMON,//50% chance
                Arrays.asList(
                        new GameItem(28715),//Dr Cape (Blue)
                        new GameItem(28716),//Dr Cape (Green)
                        new GameItem(28717),//Dr Cape (Light Blue)
                        new GameItem(28718),//Dr Cape (Orange)
                        new GameItem(28719),//Dr Cape (Pink)
                        new GameItem(28720),//Dr Cape (Purple)
                        new GameItem(28721),//Dr Cape (Red)
                        new GameItem(3050, 2), //grimy toadflax
                        new GameItem(212, 2), //grimy avantoe
                        new GameItem(214, 2), //grimy kwuarm
                        new GameItem(3052, 2),  //grimy snapdragon
                        new GameItem(216, 2),  //grimy cadantine
                new GameItem(3003, 2), //toadflax potion (unf)
                new GameItem(104, 2), //avantoe potion (unf)
                new GameItem(106, 2), //kwuarm potion (unf)
                new GameItem(3005, 2),  //snapdragon potion (unf)
                new GameItem(108, 2),  //cadantine potion (unf)
                        new GameItem(454, 2), //coal
                        new GameItem(448, 2), //mithril ore
                        new GameItem(450, 2), //adamantite ore
                        new GameItem(452, 7),  //runite ore
                        new GameItem(2360, 1),  //mithril bar
                        new GameItem(2362, 1), //adamantite bar
                        new GameItem(2364, 1), //runite bar
                        new GameItem(1518, 2), //maple logs
                        new GameItem(1516, 2),  //yew logs
                        new GameItem(1514, 2),  //magic logs
                        new GameItem(1620, 2), //uncut ruby
                        new GameItem(1618, 2), //uncut diamond
                        new GameItem(378, 2), //raw lobster
                        new GameItem(372, 7),  //raw swordfish
                        new GameItem(7945, 2),  //raw monkfish
                        new GameItem(3143, 2), //raw karamwban

                        new GameItem(Items.LIMPWURT_ROOT_NOTED, 3, 6),
                        new GameItem(Items.RED_SPIDERS_EGGS_NOTED, 5, 10),
                        new GameItem(Items.MORT_MYRE_FUNGUS_NOTED, 5, 10),
                        new GameItem(Items.CRUSHED_NEST_NOTED, 1, 2)

                ));

        items.put(LootRarity.RARE,//10% chance
                Arrays.asList(
                        new GameItem(28722),//Dr Cape (White)
                        new GameItem(28723),//Dr Cape (Neon Green)
                        new GameItem(28724),//Dr Cape (Neon Pink)
                        new GameItem(28725),//Dr Cape (Neon Blue)
                        new GameItem(28726)
                ));
    }

    /**
     * Constructs a new myster box to handle item receiving for this player and this player alone
     *
     * @param player the player
     */
    public DrCapeMysteryBox(Player player) {
        super(player);
    }

    @Override
    public int getItemId() {
        return 29326;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
}
