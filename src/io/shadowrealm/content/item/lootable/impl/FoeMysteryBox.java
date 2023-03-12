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
public class FoeMysteryBox extends MysteryBoxLootable {

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
					new GameItem(12646, 1),//mole
					new GameItem(13247, 1),//hell puppy
					new GameItem(21992, 1),//VorKi
					new GameItem(13320, 1),//heron
					new GameItem(13321, 1),//rock GoLem
					new GameItem(13322, 1),//beaver
					new GameItem(13325, 1),//black ChinChomp
					new GameItem(20659, 1),//giant Squirrel
					new GameItem(20661, 1),//tangle root
					new GameItem(20693, 1),//phoenix
		            new GameItem(30010, 1),//postie pet
				    new GameItem(30011, 1),//imp
				    new GameItem(30012, 1),//bird
				    new GameItem(30013, 1)//penguin

				));
			items.put(LootRarity.UNCOMMON, //50% chance
					Arrays.asList(

							new GameItem(20851, 1),//OlmLet
							new GameItem(22382, 1),//Vasa MiniRo
							new GameItem(22384, 1),//VeSpina
							new GameItem(22746, 1),//IkkLe hydra
							new GameItem(23757, 1),//YoungLef
		                    new GameItem(30010, 1),//postie pet
				            new GameItem(30011, 1),//imp
			             	new GameItem(30012, 1),//bird
				            new GameItem(30013, 1),//penguin
				            new GameItem(30014, 1),//KliK
				            new GameItem(30015, 1),//shadow warrior
				            new GameItem(30017, 1),//shadow wizard
							new GameItem(30016, 1),//shadow archer
				            new GameItem(30018, 1),//healer death spawn
				            new GameItem(30019, 1)//holy death spawn
					));

			items.put(LootRarity.RARE,//8% chance
					Arrays.asList(
							new GameItem(30022, 1),//Kratos
							new GameItem(19942, 1),//mimic
							new GameItem(30014, 1),//klik
							new GameItem(30015, 1),//shadow warrior
							new GameItem(30016, 1),//shadow archer
							new GameItem(30017, 1),//shadow wizard
							new GameItem(30018, 1),//healer death spawn
							new GameItem(30019, 1),//holy death spawn
							new GameItem(30020, 1),//corrupt beast
							new GameItem(30021, 1),//roc
							new GameItem(23939, 1),//seren
							new GameItem(30012, 1),//bird
							new GameItem(30020, 1),//corrupt beast
							new GameItem(30021, 1),//roc
							new GameItem(23939, 1),//seren
							new GameItem(30014, 1),//klik
							new GameItem(30015, 1),//shadow warrior
							new GameItem(30016, 1),//shadow archer
							new GameItem(30017, 1),//shadow wizard
							new GameItem(30018, 1),//healer death spawn
							new GameItem(30019, 1),//holy death spawn
							new GameItem(30020, 1),//corrupt beast
							new GameItem(30021, 1),//roc
							new GameItem(30022, 1),//Dark Kratos
							new GameItem(23939, 1),//seren
							new GameItem(30022, 1)//yama

							));
		}

    /**
	 * Constructs a new myster box to handle item receiving for this player and this player alone
	 *
	 * @param player the player
	 */
	public FoeMysteryBox(Player player) {
		super(player);
	}

	@Override
	public int getItemId() {
		return 8167;
	}

	@Override
	public Map<LootRarity, List<GameItem>> getLoot() {
		return items;
	}
}