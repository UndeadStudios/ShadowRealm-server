package io.exilius.content.item.lootable.impl;

import io.exilius.content.item.lootable.LootRarity;
import io.exilius.content.item.lootable.MysteryBoxLootable;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import QuickUltra.Rarity;

/**
 * Revamped a simple means of receiving a random item based on chance.
 *
 * @author Jason MacKeigan
 * @date Oct 29, 2014, 1:43:44 PM
 */
public class Raids1mbox extends MysteryBoxLootable {

	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 */
	static {
		items.put(LootRarity.COMMON, //47% chance
				Arrays.asList( //banned copy these lines below and paste to add more and change id's
						new GameItem(21326, 5000),//amethyst arrow
						new GameItem(995, 15000000),//coins
						new GameItem(2403, 1),//$10 scroll
		                new GameItem(21034, 1),//dexterous prayer scroll
		                new GameItem(21079, 1),//arcane prayer scroll
		                new GameItem(995, 20000000),//coin
		                new GameItem(11212, 5000),//Dragon arrow
		                new GameItem(11230, 5000),//Dragon dart
		                new GameItem(21000, 1),//twisted buckler
		                new GameItem(21015, 1),//dinh's bulwark
		                new GameItem(3456, 1))//common raids key

		);
		items.put(LootRarity.UNCOMMON, //45% chance
				Arrays.asList(// add some for uncommon table
						new GameItem(995, 50000000),//coin
						new GameItem(3464, 2),//rare raids key
						new GameItem(2396, 1),//$25 scroll
						new GameItem(20851, 1),//Olm let
						new GameItem(21012, 1),//dragon hunter crossbow
						new GameItem(20784, 1),//dragon claws
						new GameItem(21018, 1),//ancestral hat
						new GameItem(21024, 1),//ancestral robe bottom
						new GameItem(21021, 1),//ancestral robe top
						new GameItem(21079, 1),//arcane prayer scroll
		                new GameItem(21034, 1))//dexterous prayer scroll
		);
		items.put(LootRarity.RARE,//8% chance
				Arrays.asList(//add some for rare table too
						new GameItem(21006, 1),//KoDai wand
						new GameItem(786, 1),//$50 scroll
				        new GameItem(20997, 1)//twisted bow

				));
	}

	/**
	 * Constructs a new mystery box to handle item receiving for this player and this player alone
	 *
	 * @param player the player
	 */
	public Raids1mbox(Player player) {
		super(player);
	}

	@Override
	public int getItemId() {
		return 10025;
	}

	@Override
	public Map<LootRarity, List<GameItem>> getLoot() {
		return items;
	}
}