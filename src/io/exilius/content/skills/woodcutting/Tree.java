package io.exilius.content.skills.woodcutting;

import io.exilius.content.bosses.hespori.Hespori;

public enum Tree {
	NORMAL(new int[] { 1276, 1278, 1279 }, 1342, 1511, 1, 8, 25, 25, 10, 11500),

	//NORMAL(new int[] { 1276, 1278, 1279 }, 1342, 1511, 1, 5, 100, 25, 15, 12000),
	DEAD(new int[] { 1282 }, 1347, 1511, 1, 5, 100, 25, 15, 12000),
	DEAD2(new int[] { 1283 }, 1347, 1511, 1, 5, 100, 25, 15, 12000),
	DEAD3(new int[] { 1289 }, 1353, 1511, 1, 5, 100, 25, 15, 12000),
	DEAD4(new int[] { 1285 }, 1349, 1511, 1, 5, 100, 25, 15, 12000),
	NORMAL_ELF(new int[] { 36679 }, 36680, 1511, 1, 5, 35, 25, 15, 12000),
	NORMAL_ELF_2(new int[] { 36683 }, 36684, 1511, 1, 5, 35, 25, 15, 12000),
	NORMAL_ELF_3(new int[] { 36677 }, 36678, 1511, 1, 5, 35, 25, 15, 12000),
	NORMAL2(new int[] { 40752 }, 40753, 1511, 1, 5, 25, 25, 15, 12000),
	NORMAL3(new int[] { 40750 }, 40751, 1511, 1, 5, 25, 25, 15, 12000),
	OAK(new int[] { 10820, 11756 }, 1356, 1521, 15, 8, 35, 38, 25, 11500),
	WILLOW(new int[] { 10819 }, 9711, 1519, 30, 10, 60, 68, 35, 8000),
	WILLOW2(new int[] {  10829, 10831, 10833 }, 9471, 1519, 30, 10, 60, 68, 35, 8000),
	TEAK(new int[] { 9036 }, 1356, 6333, 35, 10, 651356, 68, 35, 7000),
	TEAK2(new int[] { 40758 }, 40759, 6333, 35, 10, 65, 68, 35, 7000),
	MAHOGANY(new int[] { 9043 }, 9035, 6332, 50, 10, 65, 125, 35, 7000),
	MAHOGANY2(new int[] { 40760 }, 40761, 6332, 50, 10, 65, 125, 35, 7000),
	MATURE_JUNIPER(new int[] { 27499 }, 27500, 13355, 42, 13, 65,35, 45, 6500),
	MAPLE(new int[] { 10832, 36681, 4674, 40754 }, 9712, 1517, 45, 13, 75, 100, 45, 6000),
	HOLLOW(new int[] { 10821 }, 2310, 3239, 45, 13, 75, 100, 45, 6000),
	HOLLOW2(new int[] { 10830 }, 4061, 3239, 45, 13, 75, 100, 45, 6000),
	ARCTIC_PINE(new int[] { 3037 }, 1356, 10810, 54, 14, 85, 100, 50, 5400),
	YEW(new int[] { 10822, 10823, 1754, 11758, 27255 }, 1356, 1515, 60, 15, 100, 175, 60, 5000),
	YEW2(new int[] { 40756 }, 40757, 1515, 60, 15, 100, 175, 60, 5000),
	MAGIC(new int[] { 10834, 11764 }, 9713, 1513, 75, 35, 125, 250, 75, 3600),
	REDWOOD(new int[] { 29668  }, 29669, 19669, 90, 25, 1250, 275, 150, 3000),
	REDWOOD2(new int[] { 29670 }, 29671, 19669, 90, 25, 1250, 275, 150, 3000),
	REDWOOD3(new int[] { 34284 }, 34284, 19669, 90, 25, 1250, 275, 150, 3000),
	REDWOOD4(new int[] { 34286 }, 34286, 19669, 90, 25, 1250, 275, 150, 3000),
	REDWOOD5(new int[] { 34288 }, 34288, 19669, 90, 25, 1250, 275, 150, 3000),
	REDWOOD6(new int[] { 34290 }, 34290, 19669, 90, 25, 1250, 275, 150, 3000),
	SAPLING(new int[] { 29763 }, 29764, 20799, 65, 13, 799995, 25, 15, 100000),
	HESPORI(new int[] { 33730 }, 1342, Hespori.KEY, 1, 5, 299990, 1200, 15, 1000);

	private final int[] treeIds;
	private final int stumpId;
    private final int wood;
    private final int levelRequired;
    private final int chopsRequired;
    private final int deprecationChance;
    private final int respawn;
    private final int petChance;
	private final double experience;

	Tree(int[] treeIds, int stumpId, int wood, int levelRequired, int chopsRequired, int deprecationChance, double experience, int respawn, int petChance) {
		this.treeIds = treeIds;
		this.stumpId = stumpId;
		this.wood = wood;
		this.levelRequired = levelRequired;
		this.experience = experience;
		this.deprecationChance = deprecationChance;
		this.chopsRequired = chopsRequired;
		this.respawn = respawn;
		this.petChance = petChance;
	}

	public int[] getTreeIds() {
		return treeIds;
	}

	public int getStumpId() {
		return stumpId;
	}

	public int getWood() {
		return wood;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public int getChopsRequired() {
		return chopsRequired;
	}

	public int getChopdownChance() {
		return deprecationChance;
	}

	public double getExperience() {
		return experience;
	}

	public int getRespawnTime() {
		return respawn;
	}
	
	public int getPetChance() {
		return petChance;
	}

	public static Tree forObject(int objectId) {
		for (Tree tree : values()) {
			for (int treeId : tree.treeIds) {
				if (treeId == objectId) {
					return tree;
				}
			}
		}
		return null;
	}

}
