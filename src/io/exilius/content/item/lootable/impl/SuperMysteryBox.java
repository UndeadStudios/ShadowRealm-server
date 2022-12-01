package io.exilius.content.item.lootable.impl;

import io.exilius.content.item.lootable.LootRarity;
import io.exilius.content.item.lootable.MysteryBoxLootable;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperMysteryBox extends MysteryBoxLootable {

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
                        new GameItem(12873),//guthan set
                        new GameItem(12875),//verac set
                        new GameItem(12877),//dharok set
                        new GameItem(12881),//ahrim set
                        new GameItem(12883),//karil set
                        new GameItem(2572),//ring of wealth
                        new GameItem(6585),//fury
                        new GameItem(4151),//whip
                        new GameItem(11838),//saradomin sword
                        new GameItem(12873),//guthan set
                        new GameItem(12875),//verac set
                        new GameItem(12877),//dharok set
                        new GameItem(12879),//torags set
                        new GameItem(12881),//ahrim set
                        new GameItem(12883),//karil set
                        new GameItem(2572),//ring of wealth
                        new GameItem(6585),//fury
                        new GameItem(4151),//whip
                        new GameItem(11838),//saradomin sword
                        new GameItem(11804),//BGS
                        new GameItem(11806),//SGS
                        new GameItem(11808),//ZGS
                        new GameItem(11770),//seers (i)
                        new GameItem(11771),//archers (i)
                        new GameItem(11772),//warrior (i)
                        new GameItem(11773),//berserker (i)
                        new GameItem(11804),//BGS
                        new GameItem(11806),//SGS
                        new GameItem(11808),//ZGS
                        new GameItem(11770),//seers (i)
                        new GameItem(11771),//archers (i)
                        new GameItem(11772),//warrior (i)
                        new GameItem(11773)//berserker (i)

                ));

        items.put(LootRarity.UNCOMMON,//40% Chance
                Arrays.asList(
                        new GameItem(12873),//guthan set
                        new GameItem(12875),//verac set
                        new GameItem(12877),//dharok set
                        new GameItem(12879),//torags set
                        new GameItem(12881),//ahrim set
                        new GameItem(12883),//karil set
                        new GameItem(2572),//ring of wealth
                        new GameItem(6585),//fury
                        new GameItem(4151),//whip
                        new GameItem(11838),//saradomin sword
                        new GameItem(2572),//ring of wealth
                        new GameItem(6585),//fury
                        new GameItem(4151),//whip
                        new GameItem(11838),//saradomin sword
                        new GameItem(11804),//BGS
                        new GameItem(11806),//SGS
                        new GameItem(11808),//ZGS
                        new GameItem(11770),//seers (i)
                        new GameItem(11771),//archers (i)
                        new GameItem(11772),//warrior (i)
                        new GameItem(11773),//berserker (i)
                        new GameItem(11804),//BGS
                        new GameItem(11806),//SGS
                        new GameItem(11808),//ZGS
                        new GameItem(11770),//seers (i)
                        new GameItem(11771),//archers (i)
                        new GameItem(11772),//warrior (i)
                        new GameItem(11773)//berserker (i)
                )
        );

        items.put(LootRarity.RARE,//10% chance
                Arrays.asList(
                        new GameItem(11802),//AGS
                        new GameItem(11826),//armadyl helm
                        new GameItem(11828),//armadyl body
                        new GameItem(11830),//armadyls legs
                        new GameItem(11832),//bandos chestplate
                        new GameItem(11834),//bandos tassets
                        new GameItem(4084),//sled
                        new GameItem(13346),//ultra mystery box
                        new GameItem(11785),//armadyl crossbow
                        new GameItem(2403, 1),//10 dollar scroll
                        new GameItem(13239, 1),//primordial boots
                        new GameItem(13235, 1),//eternal boots
                        new GameItem(13237, 1),//pegasian boots
                        new GameItem(12785)));//ring of wealth (i)
    }

    /**
     * Constructs a new myster box to handle item receiving for this player and this player alone
     *
     * @param player the player
     */
    public SuperMysteryBox(Player player) {
        super(player);
    }

    @Override
    public int getItemId() {
        return 6828;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
}
