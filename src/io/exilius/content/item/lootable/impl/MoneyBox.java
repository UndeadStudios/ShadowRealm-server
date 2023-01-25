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
 * @date Oct 29, 2014, 1:43:44 PM
 *
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
public class MoneyBox extends MysteryBoxLootable {

    /**
     * A map containing a List of {@link GameItem}'s that contain items relevant to their LootRarity.
     */
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    /**
     * Stores an array of items into each map with the corresponding rarity to the list
     */
    static {

        items.put(LootRarity.COMMON, //50% chance
                Arrays.asList(
                        new GameItem(995, 500000),
                        new GameItem(995, 1000000),
                        new GameItem(995, 1500000),
                        new GameItem(995, 2000000),
                        new GameItem(995, 2500000),
                        new GameItem(995, 3000000),
                        new GameItem(995, 3500000),
                        new GameItem(995, 4000000),
                        new GameItem(995, 4500000),
                        new GameItem(995, 5000000),
                        new GameItem(995, 5500000)



                ));

        items.put(LootRarity.UNCOMMON,//40% Chance
                Arrays.asList(
                        new GameItem(995, 6000000),
                        new GameItem(995, 6500000),
                        new GameItem(995, 7000000),
                        new GameItem(995, 7500000),
                        new GameItem(995, 8000000),
                        new GameItem(995, 8500000),
                        new GameItem(995, 9000000),
                        new GameItem(995, 9500000),
                        new GameItem(995, 10000000),
                        new GameItem(995, 15000000),
                        new GameItem(995, 20000000)
                )
        );

        items.put(LootRarity.RARE,//8% chance
                Arrays.asList(
                        new GameItem(995, 25000000),
                        new GameItem(995, 30000000),
                        new GameItem(995, 35000000),
                        new GameItem(995, 40000000),
                        new GameItem(995, 45000000),
                        new GameItem(995, 50000000),
                        new GameItem(995, 55000000),
                        new GameItem(995, 60000000),
                        new GameItem(995, 65000000),
                        new GameItem(995, 70000000),
                        new GameItem(995, 75000000),
                        new GameItem(995, 80000000),
                        new GameItem(995, 85000000),
                        new GameItem(995, 90000000),
                        new GameItem(995, 95000000),
                        new GameItem(995, 100000000)
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
