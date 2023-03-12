package io.shadowrealm.content.item.lootable.impl;

import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.content.item.lootable.MysteryBoxLootable;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;

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
public class NexBox extends MysteryBoxLootable {

	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 */
	static {
		items.put(LootRarity.COMMON, //50% chance
				Arrays.asList(
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5),//nex key
						new GameItem(3460, 5)//nex key

		));
		items.put(LootRarity.UNCOMMON, //50% chance
				Arrays.asList(
						new GameItem(3460, 8),//nex key
						new GameItem(3460, 8),//nex key
						new GameItem(3460, 8),//nex key
						new GameItem(3460, 8),//nex key
						new GameItem(26235,1),//Zaryte vambraces
						new GameItem(12002,1),//torva full helm
		new GameItem(11806,1)//torva platelegs
						//new GameItem(26370,1),//ancient hilt
						//new GameItem(27184,1)//ancient godsword
		));
		items.put(LootRarity.RARE,//8% chance
				Arrays.asList(
						new GameItem(26374,1),//Zaryte crossbow
						new GameItem(12002,1),//torva full helm
						new GameItem(11804,1),//torva platebody
						new GameItem(11806,1),//torva platelegs
		new GameItem(26374,1),//Zaryte crossbow
				new GameItem(12002,1),//torva full helm
				new GameItem(11804,1),//torva platebody
				new GameItem(11806,1)//torva platelegs
				));
	}

	/**
	 * Constructs a new myster box to handle item receiving for this player and this player alone
	 *
	 * @param player the player
	 */
	public NexBox(Player player) {
		super(player);
	}

	@Override
	public int getItemId() {
		return 28825;//itemid
	}

	@Override
	public Map<LootRarity, List<GameItem>> getLoot() {
		return items;
	}
}