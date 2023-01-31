package io.exilius.content.item.lootable.impl;

import io.exilius.content.item.lootable.LootRarity;
import io.exilius.content.item.lootable.MysteryBoxLootable;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Revamped a simple means of receiving a random item based on chance.
 *
 * @author Jason MacKeigan
 * {@code @date} Oct 29, 2014, 1:43:44 PM
 *
 * @author Michael Sasse (<a href="https://github.com/mikeysasse/">...</a>)
 */
public class MoneyBox extends MysteryBoxLootable {

    /**
     * A map containing a List of {@link GameItem}'s that contain items relevant to their LootRarity.
     */
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    /*
      Stores an array of items into each map with the corresponding rarity to the list
     */
    static {

        items.put(LootRarity.COMMON, //50% chance
                Arrays.asList(
                        new GameItem(995, 500_000),
                        new GameItem(995, 1_000_000),
                        new GameItem(995, 1_500_000),
                        new GameItem(995, 2_000_000),
                        new GameItem(995, 2_500_000),
                        new GameItem(995, 3_000_000),
                        new GameItem(995, 3_500_000),
                        new GameItem(995, 4_000_000),
                        new GameItem(995, 4_500_000),
                        new GameItem(995, 5_000_000),
                        new GameItem(995, 5_500_000)



                ));

        items.put(LootRarity.UNCOMMON,//40% Chance
                Arrays.asList(
                        new GameItem(995, 6_000_000),
                        new GameItem(995, 6_500_000),
                        new GameItem(995, 7_000_000),
                        new GameItem(995, 7_500_000),
                        new GameItem(995, 8_000_000),
                        new GameItem(995, 8_500_000),
                        new GameItem(995, 9_000_000),
                        new GameItem(995, 9_500_000),
                        new GameItem(995, 10_000_000),
                        new GameItem(995, 15_000_000),
                        new GameItem(995, 20_000_000)
                )
        );

        items.put(LootRarity.RARE,//8% chance
                Arrays.asList(
                        new GameItem(995, 25_000_000),
                        new GameItem(995, 300_00_000),
                        new GameItem(995, 350_000_00),
                        new GameItem(995, 40_000_000),
                        new GameItem(995, 45_000_000),
                        new GameItem(995, 50_000_000),
                        new GameItem(995, 55_000_000),
                        new GameItem(995, 60_000_000),
                        new GameItem(995, 65_000_000),
                        new GameItem(995, 70_000_000),
                        new GameItem(995, 75_000_000),
                        new GameItem(995, 80_000_000),
                        new GameItem(995, 85_000_000),
                        new GameItem(995, 90_000_000),
                        new GameItem(995, 95_000_000),
                        new GameItem(995, 100_000_000)
                )
        );


    }

    /**
     * Constructs a new myster box to handle item receiving for this player and this player alone
     *
     * @param player the player
     */
    public MoneyBox(Player player) {
        super(player);
    }

    @Override
    public int getItemId() {
        return 28827;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
}
