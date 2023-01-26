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
		items.put(LootRarity.COMMON, //50% chance
				Arrays.asList( //banned copy these lines below and paste to add more and change id's
						new GameItem(21012, 1),//Dragon Crossbow
						new GameItem(13237))//pegasian boots)

		);
		items.put(LootRarity.UNCOMMON, //50% chance
				Arrays.asList(// add some for uncommon table <3
						new GameItem(19550, 1),//ring of sufferin
						new GameItem(21006))//kodai wand
		);
		items.put(LootRarity.RARE,//8% chance
				Arrays.asList(//add some for rare table too
						new GameItem(22325, 1),//scythe of vitur
						new GameItem(786)//$50 scroll
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