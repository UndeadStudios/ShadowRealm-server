package io.exilius.content.skills.fletching;

public enum FletchableLog {
	SHORTBOW(1511, 50, 5, 5, 34205, 34204, 34202, 34202),
	LONGBOW(1511, 48, 10, 10, 34209, 34208, 34207, 34206),
	ARROW_SHAFT(1511, 52, 1, 5, 34213, 34212, 34211, 34210),
	STOCK(1511, 9440, 9, 6, 34217, 34216, 34215, 34214),
	OAK_SHORTBOW(1521, 54, 20, 17, 34245, 34244, 34243, 34242),
	OAK_LONGBOW(1521, 56, 25, 25, 34249, 34248, 34247, 34246),
	OAK_SHAFT(1521, 52, 24, 16, 34253, 34252, 34251, 34250),
	OAK_STOCK(1521, 9442, 24, 16, 35001, 35000, 34255, 34254),
	OAK_SHIELD(1521, 22251, 27, 50, 35005, 35004, 35003, 35002),
	WILLOW_SHORTBOW(1519, 60, 35, 33, 34245, 34244, 34243, 34242),
	WILLOW_LONGBOW(1519, 58, 40, 42, 34249, 34248, 34247, 34246),
	WILLOW_SHAFT(1519, 52, 39, 22, 34253, 34252, 34251, 34250),
	WILLOW_STOCK(1519, 9444, 39, 22, 35001, 35000, 34255, 34254),
	WILLOW_SHIELD(1519, 22254, 42, 83, 35005, 35004, 35003, 35002),
	MAPLE_SHORTBOW(1517, 64, 50, 50, 34245, 34244, 34243, 34242),
	MAPLE_LONGBOW(1517, 62, 55, 58, 34249, 34248, 34247, 34246),
	MAPLE_SHAFT(1517, 52, 54, 32, 34253, 34252, 34251, 34250),
	MAPLE_STOCK(1517, 9448, 54, 32, 35001, 35000, 34255, 34254),
	MAPLE_SHIELD(1517, 22257, 57, 117, 35005, 35004, 35003, 35002),
	YEW_SHORTBOW(1515, 68, 65, 68, 34245, 34244, 34243, 34242),
	YEW_LONGBOW(1515, 66, 70, 75, 34249, 34248, 34247, 34246),
	YEW_SHAFT(1515, 52, 69, 50, 34253, 34252, 34251, 34250),
	YEW_STOCK(1515, 9452, 69, 50, 35001, 35000, 34255, 34254),
	YEW_SHIELD(1515, 22260, 72, 150, 35005, 35004, 35003, 35002),
	MAGIC_SHORTBOW(1513, 72, 80, 83, 34245, 34244, 34243, 34242),
	MAGIC_LONGBOW(1513, 70, 85, 92, 34249, 34248, 34247, 34246),
	MAGIC_SHAFT(1513, 52, 80, 83, 34253, 34252, 34251, 34250),
	MAGIC_STOCK(1513, 21952, 78, 70, 35001, 35000, 34255, 34254),
	MAGIC_SHIELD(1513, 22263, 87, 183, 35005, 35004, 35003, 35002),
	TEAK_SHAFT(6333, 52, 46, 28, 34170, 34169, 34168, 34167),
	TEAK_STOCK(6333, 9446, 46, 27, 34174, 34173, 34172, 34171),
	REDWOOD(19669, 52, 90, 92, 34193, 34192, 34191, 34190);

	private final int[] buttonIds;
	private final int item, product, level, experience;

	FletchableLog(final int logid, final int product, final int level, final int xp, int... buttonIds) {
		this.item = logid;
		this.product = product;
		this.level = level;
		this.experience = xp;
		this.buttonIds = buttonIds;
	}

	public int getItemId() {
		return item;
	}

	public int getProduct() {
		return product;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public int[] getButtonIds() {
		return buttonIds;
	}

	public String getName() {
		String s = name().toLowerCase();
		String t = s.substring(0, 1).toUpperCase() + s.substring(1);
		t = t.replaceAll("_", " ");
		return t;
	}
}