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
						new GameItem(21012, 1),//Dragon Crossbow

						new GameItem(19550, 1),//ring of sufferin
						new GameItem(12924, 1),//blowpipe
						new GameItem(19547, 1),//anguish
						new GameItem(19544, 1),//tormented

						new GameItem(11785, 1),//ACB
						new GameItem(21633, 1),//ancient wyvern shield
						new GameItem(11832, 1),//BCP
						new GameItem(11834, 1),//TASSETS
						new GameItem(11828, 1),//Arma Chest
						new GameItem(11830, 1),//Arma Legs
						new GameItem(11802, 1),//AGS
						new GameItem(21003, 1),//Elder Maul
						new GameItem(19553, 1),//Amulet of Torture
						new GameItem(19544, 1),//tormented bracelet
						new GameItem(21902, 1),//dragon crossbow
						new GameItem(21000, 1),//twisted buckler
						new GameItem(21034, 1),//dex prayer scroll
						new GameItem(13576, 1),//dragon warhammer
						new GameItem(21079, 1),//arcane prayer scroll
						new GameItem(12809, 1),//saradomin blessed sword

						new GameItem(13239, 1),//primordial boots
						new GameItem(13235, 1),//eternal boots
						new GameItem(13237, 1),//pegasian boots)

						new GameItem(21012, 1),//Dragon Crossbow

						new GameItem(12006, 1),//abby tent whip
						new GameItem(19550, 1),//ring of sufferin
						new GameItem(12924, 1),//blowpipe
						new GameItem(19547, 1),//anguish
						new GameItem(19544, 1),//tormented

						new GameItem(11785, 1),//ACB
						new GameItem(11832, 1),//BCP
						new GameItem(11834, 1),//TASSETS
						new GameItem(11828, 1),//Arma Chest
						new GameItem(11830, 1),//Arma Legs
						new GameItem(11802, 1),//AGS
						new GameItem(21003, 1),//Elder Maul
						new GameItem(19553, 1),//Amulet of Torture
						new GameItem(19544, 1),//tormented bracelet
						new GameItem(21902, 1),//dragon crossbow
						new GameItem(21000, 1),//twisted buckler
						new GameItem(21034, 1),//dex prayer scroll
						new GameItem(13576, 1),//dragon warhammer
						new GameItem(21079, 1),//arcane prayer scroll
						new GameItem(12902, 1),//toxic staff of the dead
						new GameItem(12002, 1),//occult necklace
						new GameItem(13239, 1),//primordial boots
						new GameItem(13235, 1),//eternal boots
						new GameItem(13237))//pegasian boots)

		);
		items.put(LootRarity.UNCOMMON, //50% chance
				Arrays.asList(
						new GameItem(19550, 1),//ring of sufferin
						new GameItem(12924, 1),//blowpipe
						new GameItem(13198, 1),//magma helm
						new GameItem(13196, 1),//tangz helm
						new GameItem(19547, 1),//anguish
						new GameItem(19544, 1),//tormented
						new GameItem(12899, 1),//toxic trident
						new GameItem(11785, 1),//ACB
						new GameItem(2403, 1),//$10 scroll
						new GameItem(11832, 1),//BCP
						new GameItem(11834, 1),//TASSETS
						new GameItem(11828, 1),//Arma Chest
						new GameItem(11830, 1),//Arma Legs
						new GameItem(11802, 1),//AGS
						new GameItem(21003, 1),//Elder Maul
						new GameItem(19553, 1),//Amulet of Torture
						new GameItem(19544, 1),//tormented bracelet
						new GameItem(21034, 1),//dex prayer scroll
						new GameItem(13576, 1),//dragon warhammer
						new GameItem(21079, 1),//arcane prayer scroll

						new GameItem(12002, 1),//occult necklace
						new GameItem(13239, 1),//primordial boots
						new GameItem(13235, 1),//eternal boots
						new GameItem(19550, 1),//ring of sufferin
						new GameItem(12924, 1),//blowpipe
						new GameItem(19547, 1),//anguish
						new GameItem(19544, 1),//tormented

						new GameItem(11785, 1),//ACB
						new GameItem(11832, 1),//BCP
						new GameItem(11834, 1),//TASSETS
						new GameItem(11828, 1),//Arma Chest
						new GameItem(11830, 1),//Arma Legs
						new GameItem(11802, 1),//AGS
						new GameItem(21003, 1),//Elder Maul
						new GameItem(19553, 1),//Amulet of Torture
						new GameItem(19544, 1),//tormented bracelet
						new GameItem(21034, 1),//dex prayer scroll
						new GameItem(13576, 1),//dragon warhammer
						new GameItem(21079, 1),//arcane prayer scroll
						new GameItem(12902, 1),//toxic staff of the dead
						new GameItem(13239, 1),//primordial boots
						new GameItem(13235, 1),//eternal boots
						new GameItem(21015, 1),//dihn bulwark
						new GameItem(21006, 1),//kodai wand
						new GameItem(21015, 1),//dihn bulwark
						new GameItem(21006))//kodai wand
		);
		items.put(LootRarity.RARE,//8% chance
				Arrays.asList(
						//new GameItem(22325, 1),//scythe of vitur
						new GameItem(21295, 1),//infernal cape
						new GameItem(1038, 1),//red phat
						new GameItem(1040, 1),//yellow phat
						new GameItem(1042, 1),//blue phat
						new GameItem(1044, 1),//green phat
						new GameItem(1046, 1),//purp phat
						new GameItem(1048, 1),//white phat
						new GameItem(11862, 1),//black phat
						new GameItem(20784, 1),//dragon claws
						new GameItem(12817, 1),//elysian
						new GameItem(1038, 1),//red phat
						new GameItem(1040, 1),//yellow phat
						new GameItem(1042, 1),//blue phat
						new GameItem(1044, 1),//green phat
						new GameItem(1046, 1),//purp phat
						new GameItem(1048, 1),//white phat
						new GameItem(20784, 1),//dragon claws
						new GameItem(12817, 1),//elysian
						new GameItem(1053, 1),//green hween
						new GameItem(1055, 1),//blue hween
						new GameItem(1057, 1),//red h ween
						new GameItem(1050, 1),//santa hat
						new GameItem(11863, 1),//rainbow phat
						new GameItem(1053, 1),//green hween
						new GameItem(1055, 1),//blue hween
						new GameItem(1057, 1),//red h ween
						new GameItem(1050, 1),//santa hat
						new GameItem(12825, 1),//arcane spirit shield
						new GameItem(12821, 1),//spectral spirit shield
						new GameItem(22326, 1),//justiciar helm
						new GameItem(22327, 1),//justiciar body
						new GameItem(22328, 1),// justiciar legs
						new GameItem(21021, 1),//ancestral body
						new GameItem(21024, 1),//ancesetral legs
						new GameItem(21018, 1),//ancestral hat
						//new GameItem(22324, 1),//ghrazi rapier
						//new GameItem(20997, 1),//twisted bow
						new GameItem(12825, 1),//arcane spirit shield
						new GameItem(12821, 1),//spectral spirit shield
						new GameItem(22326, 1),//justiciar helm
						new GameItem(22327, 1),//justiciar body
						new GameItem(22328, 1),// justiciar legs
						new GameItem(21021, 1),//ancestral body
						new GameItem(21024, 1),//ancesetral legs
						new GameItem(21018, 1),//ancestral hat
						//new GameItem(22324, 1),//ghrazi rapier
						new GameItem(20784, 1),//dragon claws,
						new GameItem(12825, 1),//arcane spirit shield
						new GameItem(12821, 1),//spectral spirit shield
						new GameItem(22326, 1),//justiciar helm
						new GameItem(22327, 1),//justiciar body
						new GameItem(22328, 1),// justiciar legs
						new GameItem(21021, 1),//ancestral body
						new GameItem(21024, 1),//ancesetral legs
						new GameItem(21018, 1),//ancestral hat
						//new GameItem(22324, 1),//ghrazi rapier
						new GameItem(2403, 1),//$10 scroll
						new GameItem(2403, 1),//$10 scroll
						new GameItem(2403, 1),//$10 scroll
						new GameItem(2403, 1),//$10 scroll
						new GameItem(2403, 1),//$10 scroll
						new GameItem(761, 1),//$100 scroll
						new GameItem(761, 1),//$100
						new GameItem(786, 1),//$50 scroll
						new GameItem(786, 1),//$50 scroll
						new GameItem(786)//$50 scroll
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