package io.exilius.content.skills.farming;

import java.util.HashMap;
import java.util.Map;

import io.exilius.model.Items;

public enum Plants {
	REDBERRY_BUSH(Items.REDBERRY_SEED, 1, Items.REDBERRIES, 0x05, 5 + 13, 5 + 19, 8 + 25, 5 + 26, 10, 80, SeedType.BUSH, 11.5, 64, 93.5, 6),
	Cadavaberry_bush(Items.CADAVABERRY_SEED, 1, Items.CADAVA_BERRIES, 15,  15 + 13, 15 + 19, 15 + 25, 15 + 26, 22, 80, SeedType.BUSH, 18, 102.5, 7, 6),
	DWELLBERRY_BUSH(Items.DWELLBERRY_SEED, 1, Items.DWELLBERRIES, 26,  26 + 13, 26 + 19, 26 + 25, 26 + 26, 36, 80, SeedType.BUSH, 31.5, 177.5, 12, 7),
	JANGERBERRY_BUSH(Items.JANGERBERRY_SEED, 1, Items.JANGERBERRIES, 38,  38 + 13, 38 + 19, 38 + 25, 38 + 26, 48, 80, SeedType.BUSH, 50.5, 284.5, 19, 9),
	WHITEBERRY_BUSH(Items.WHITEBERRY_SEED, 1, Items.WHITE_BERRIES, 51,  51 + 13, 51 + 19, 51 + 25, 51 + 26, 59, 80, SeedType.BUSH, 78, 437, 29, 9),
	POISON_INV_VERRY_BUSH(Items.POISON_IVY_SEED, 1, Items.POISON_IVY_BERRIES, 197,  197 + 13, 197 + 19, 197 + 25, 197 + 26, 70, 80, SeedType.BUSH, 120, 675, 45, 9),
	OAK_TREE(Items.OAK_SAPLING, 1, Items.OAK_ROOTS, 8, 8 + 13, 8 + 19, 8 + 25, 8 + 26, 15, 80, SeedType.TREE, 14, 467, 0, 4),
	WILLOW_TREE(Items.WILLOW_SAPLING, 1, Items.WILLOW_ROOTS, 15, 15 + 13, 15 + 19, 15 + 25, 15 + 26, 30, 80, SeedType.TREE, 25, 1456.5, 0, 6),
	Magic_TREE(Items.MAGIC_SAPLING, 1, Items.MAGIC_ROOTS, 48, 48 + 13, 48 + 19, 48 + 25, 48 + 26, 75, 80, SeedType.TREE, 107.0, 5000.0, 50.9, 12),
	REDWOOD_TREE(Items.REDWOOD_SAPLING, 1, Items.REDWOOD_LOGS, 8, 8 + 13, 8 + 19, 8 + 25, 8 + 26, 90, 80, SeedType.REDWOOD, 230, 22450, 0, 10),
	APPLE_TREE(Items.APPLE_SAPLING, 1, Items.COOKING_APPLE, 8, 8 + 12, 8 + 18, 0x22, 0x22, 27, 80, SeedType.FRUIT_TREE, 22.0, 1199.0, 8.5, 7),
	BANANA_TREE(Items.BANANA_SAPLING, 1, Items.BANANA, 35, 35 + 13, 35 + 19, 35 + 25, 35 + 26, 33, 80, SeedType.FRUIT_TREE, 28.0, 1750.5, 10.5, 7),
	ORANGE_TREE(Items.ORANGE_SAPLING, 1, Items.ORANGE, 72, 72 + 13, 72 + 19, 72 + 25, 72 + 26, 39, 80, SeedType.FRUIT_TREE, 35.0, 2470.2, 13.5, 7),
	CURRY_TREE(Items.CURRY_SAPLING, 1, Items.CURRY_LEAF, 99, 99 + 13, 99 + 19, 99 + 25, 99 + 26, 42, 80, SeedType.FRUIT_TREE, 40, 2906.9, 15, 7),
	PINEAPPLE_TREE(Items.PINEAPPLE_SAPLING, 1, Items.PINEAPPLE, 136, 136 + 13, 136 + 19, 136 + 25, 136 + 26, 51, 80, SeedType.FRUIT_TREE, 57, 4605, 21.5, 7),
	PAPAYA_TREE(Items.PAPAYA_SAPLING, 1, Items.PAPAYA_FRUIT, 163, 163 + 13, 163 + 19, 163 + 25, 163 + 26, 57, 80, SeedType.FRUIT_TREE, 72, 6146.6, 27, 7),
	PALM_TREE(Items.PALM_SAPLING, 1, Items.COCONUT, 200, 200 + 13, 200 + 19, 200 + 25, 200 + 26, 68, 80, SeedType.FRUIT_TREE, 110.5, 10150.1, 41.5, 7),
	Spirit_TREE(Items.SPIRIT_SAPLING, 1, -1,8, 8 + 13, 8 + 19, 8 + 25, 8 + 26, 83, 80, SeedType.SPIRIT_TREE, 199.5, 19301, 0, 12),
    celastrus_TREE(Items.CELASTRUS_SAPLING, 1, Items.CELASTRUS_BARK, 8, 8 + 13, 8 + 19, 8 + 25, 8 + 26, 85, 80, SeedType.CELASTRUS, 200, 14130, 23.5, 6),
	HAMMERSTONE_HOPS(Items.HAMMERSTONE_SEED, 4, Items.HAMMERSTONE_HOPS, 4, 173, 170, 4, 80, SeedType.HOPS, 9, 10, 5), //24
	ASGARNIAN_HOPS(Items.ASGARNIAN_SEED, 4, Items.ASGARNIAN_HOPS, 11, 173, 170, 8, 80, SeedType.HOPS, 10.9, 12, 6), //24
	BARLEY_HOPS(Items.BARLEY_SEED, 4, Items.BARLEY, 49, 173, 170, 8, 80, SeedType.HOPS, 8.5, 6.5, 5), //24
	JUTE_HOPS(Items.JUTE_SEED, 3, Items.JUTE_FIBRE, 46, 173, 170, 13, 80, SeedType.HOPS, 13, 14.5, 6), //24
	KRANDORIAN_HOPS(Items.KRANDORIAN_SEED, 4, Items.KRANDORIAN_HOPS, 28, 173, 170, 21, 80, SeedType.HOPS, 17.5, 19.6, 8), //24
	WILDBLOOD_HOPS(Items.WILDBLOOD_SEED, 4, Items.WILDBLOOD_HOPS, 38, 173, 170, 21, 80, SeedType.HOPS, 23, 26, 9), //24
	GUAM(5291, 1, 199, 4, 173, 170, 9, 80, SeedType.HERB, 11, 12, 4), //24
	//test_tree(Items.APPLE_SAPLING,  Items.COOKING_APPLE, 8, 173, 170, 9, 80, SeedType.FRUIT_TREE, 22, 13.5, 4), //24
	MARENTILL(5292, 1, 201, 11, 173, 170, 14, 80, SeedType.HERB, 13, 15, 4), //24
	TARROMIN(5293, 1, 203, 18, 173, 170, 19, 80, SeedType.HERB, 16, 18, 4), //42
	HARRALANDER(5294, 1, 205, 25, 173, 170, 26, 80, SeedType.HERB, 21, 24, 4), //89
	RANARR(5295, 1, 207, 32, 173, 170, 32, 80, SeedType.HERB, 26, 30, 4), //35957
	TOADFLAX(5296, 1, Items.GRIMY_TOADFLAX, 39, 173, 170, 36, 80, SeedType.HERB, 34, 38, 4), //5296
	IRIT(5297, 1, 209, 46, 173, 170, 44, 80, SeedType.HERB, 43, 48, 4), //89
	AVANTOE(5298, 1, 211, 53, 173, 170, 50, 80,	SeedType.HERB, 54, 61, 4), //805
	KWUARM(5299, 1, 213, 68, 173, 170, 56, 80, SeedType.HERB, 69, 78, 4), //1425
	SNAPDRAGON(5300, 1, Items.GRIMY_SNAPDRAGON, 75, 173, 170, 62, 80, SeedType.HERB, 87, 98, 4), //43585
	CADANTINE(5301, 1,	215, 82, 173, 170, 67, 80, SeedType.HERB, 106, 120, 4), //285
	LANTADYME(5302, 1, Items.GRIMY_LANTADYME, 89, 173, 170, 73, 80,	SeedType.HERB, 134, 151, 4), //967
	DWARF_WEED(5303, 1, 217, 96, 173, 170, 79, 80, SeedType.HERB, 170,	192, 4), //268
	TORSTOL(5304, 1, 219, 103, 173, 170, 85, 80, SeedType.HERB, 199, 224, 4), //19813

	POTATO(5318, 3, 1942, 6, 0, 0, 1, 40, SeedType.ALLOTMENT, 8, 9, 4),
	ONION(5319, 3, 1957, 13, 0, 0, 5, 40,SeedType.ALLOTMENT,  9, 10, 4),
	CABBAGE(5324, 3, 1967, 20, 0, 0, 7, 40, SeedType.ALLOTMENT, 10, 11,4),
	TOMATO(5322, 3, 1982, 27, 0, 0, 12, 40, SeedType.ALLOTMENT, 12, 14, 4),
	SWEETCORN(5320, 3, 5986, 34, 0,0, 20, 40, SeedType.ALLOTMENT, 17, 19, 6),
	STRAWBERRY(5323, 3, 5504, 43, 0, 0, 31, 40, SeedType.ALLOTMENT, 26, 29, 6),
	WATERMELON(5321, 3, 5982, 52, 0, 0, 47, 40, SeedType.ALLOTMENT, 48, 54, 9),

	MARIGOLD(5096, 1, 6010, 8, 0, 0, 2, 60, SeedType.FLOWER, 8, 47, 4),
	ROSEMARY(5097, 1, 6014, 13, 0, 0, 11, 60, SeedType.FLOWER, 12, 66, 4),
	NASTURTIUM(5098, 1, 6012, 18, 0, 0, 24, 60, SeedType.FLOWER, 19, 111,4),
	WOAD(5099, 1, 5738, 23, 0, 0, 25, 60, SeedType.FLOWER, 20, 115, 4),
	LIMPWURT(5100, 1, 225, 28, 0, 0, 26, 60, SeedType.FLOWER, 21, 120, 4),//156
	//WHITE_LILY(14589, 14583, 37, 0, 0, 52, 7, SeedType.FLOWER, 300, 20547, 4)
	;

	public final int seed;
	public final int seedAmount;
	public final int harvest;
	public final int healthy;
	public final int diseased;
	public final int dead;

	/**
	 * The Experience gained when checking a Tree
	 */
	public final double checkHealthExperience;
	/**
	 * The configuration to check the health of a tree
	 */
	public final int checkHealth;

	/**
	 * The configuration to see a tree stump
	 */
	public final int treeStump;

	public final int level;
	public final int minutes;// no longer used but going to keep here
	public final byte stages;
	public final double plantExperience;
	public final double harvestExperience;
	public final SeedType type;

	private static final Map<Integer, Plants> plantsBySeed = new HashMap<Integer, Plants>();
	
	static {
		for (Plants plant : Plants.values()) {
			plantsBySeed.put(plant.seed, plant);
		}
	}
	
	public static Plants getPlantForSeed(int seed) {
		return plantsBySeed.get(seed);
	}
	
	public static boolean isSeed(int id) {
		return plantsBySeed.containsKey(id);
	}

	Plants(int seed, int seedAmount, int harvest, int config, int diseased, int dead, int level, int minutes, SeedType type,
			double plantExperience, double harvestExperience, int stages) {
		this.seed = seed;
		this.seedAmount = seedAmount;
		this.harvest = harvest;
		healthy = config;
		this.level = level;
		this.diseased = diseased;
		this.dead = dead;
		this.minutes = minutes;
		this.type = type;
		this.checkHealth = 0;
		this.checkHealthExperience = 0;
		this.treeStump = 0;
		this.plantExperience = plantExperience;
		this.harvestExperience = harvestExperience;
		this.stages = ((byte) stages);
	}

	Plants(int seed, int seedAmount, int harvest, int config, int diseased, int dead, int treeStump, int checkHealth, int level, int minutes, SeedType type,
		   double plantExperience, double checkHealthExperience, double harvestExperience, int stages) {
		this.seed = seed;
		this.seedAmount = seedAmount;
		this.harvest = harvest;
		healthy = config;
		this.level = level;
		this.diseased = diseased;
		this.dead = dead;
		this.treeStump = treeStump;
		this.checkHealth = checkHealth;
		this.checkHealthExperience = checkHealthExperience;
		this.minutes = minutes;
		this.type = type;
		this.plantExperience = plantExperience;
		this.harvestExperience = harvestExperience;
		this.stages = ((byte) stages);
	}

	public int getMinutes() {
		switch (type) {
			case HERB:
				return 7;
			case FLOWER:
				return 5;
			case HOPS:
				return 6;
			default:
				return 3;
		}
	}
}
