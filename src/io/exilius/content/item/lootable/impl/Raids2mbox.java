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
public class Raids2mbox extends MysteryBoxLootable {

	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 */
	static {
		items.put(LootRarity.COMMON, //45% chance
				Arrays.asList( //banned copy these lines below and paste to add more and change id's
						new GameItem(22477, 1),//Avernic  defender hilt
						new GameItem(21326, 5000),//amethyst arrow
		                new GameItem(11230, 5000),//Dragon dart
		                new GameItem(11212, 5000),//Dragon arrow
		                new GameItem(995, 10000000),//Coins
		                new GameItem(11212, 5000),//Saradomin brew(4)
		                new GameItem(3025, 1000),//super restore brew(4)
		                new GameItem(22477, 1),//Avernic  defender hilt
				        new GameItem(21326, 5000),//amethyst arrow
				        new GameItem(11230, 5000),//Dragon dart
				        new GameItem(11212, 5000),//Dragon arrow
				        new GameItem(995, 10000000),//Coins
				        new GameItem(11212, 5000),//d arrow
				        new GameItem(3025, 1000)//super restore brew(4)
				));
		items.put(LootRarity.UNCOMMON, //47% chance
				Arrays.asList(// add some for uncommon table <3
						new GameItem(995, 50000000),//Coins
						new GameItem(22473, 1),//Lil'zik
						new GameItem(3464, 2),//Rare raids key
						new GameItem(21034, 1),//Dexterous prayer scroll
						new GameItem(21079, 1),//Arcane prayer scroll
						new GameItem(22326, 1),//Justiciar Faceguard
						new GameItem(22328, 1),//Justiciar Leggaurds
						new GameItem(22324, 1),//Ghrazi Rapier
		                new GameItem(2396, 1),//$25 scroll
		                new GameItem(22327, 1)//Justiciar Chestguard
		  ));
		items.put(LootRarity.RARE,//8% chance
				Arrays.asList(//add some for rare table too
						new GameItem(995, 100000000),//Coins
						new GameItem(22323, 1),//Sanguinesti staff
						new GameItem(22325, 1),//scythe of vitur
						new GameItem(786, 1),//$50 scroll
		                new GameItem(761, 1)//$100 scroll
				));
	}

	/**
	 * Constructs a new myster box to handle item receiving for this player and this player alone
	 *
	 * @param player the player
	 */
	public Raids2mbox(Player player) {
		super(player);
	}

	@Override
	public int getItemId() {
		return 6830;
	}

	@Override
	public Map<LootRarity, List<GameItem>> getLoot() {
		return items;
	}
}