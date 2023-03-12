package io.shadowrealm.content.skills.woodcutting;

import io.shadowrealm.content.bosses.hespori.Hespori;

public enum Jungle {
	LIGHT_JUNGLE(new int[] { 9010 },9014, 6283, 10, 5, 65, 32, 90, 1000),
	MEDIUM_JUNGLE(new int[] { 9015 },9019, 6283, 20, 5, 65, 55, 90, 1000),
	DENSE_JUNGLE(new int[] { 9020 },9024, 6283, 35, 5, 65, 80, 90, 1000);

	private final int[] treeIds;
	private final int stumpId;
    private final int wood;
    private final int levelRequired;
    private final int chopsRequired;
    private final int deprecationChance;
    private final int respawn;
    private final int petChance;
	private final double experience;

	Jungle(int[] treeIds, int stumpId, int wood, int levelRequired, int chopsRequired, int deprecationChance, double experience, int respawn, int petChance) {
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

	public static Jungle forObject(int objectId) {
		for (Jungle tree : values()) {
			for (int treeId : tree.treeIds) {
				if (treeId == objectId) {
					return tree;
				}
			}
		}
		return null;
	}

}
