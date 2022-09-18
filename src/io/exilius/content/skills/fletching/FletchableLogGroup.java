package io.exilius.content.skills.fletching;

public enum FletchableLogGroup {
	NORMAL(FletchableLog.SHORTBOW, FletchableLog.LONGBOW, FletchableLog.ARROW_SHAFT, FletchableLog.STOCK),
	MAGIC(FletchableLog.MAGIC_SHORTBOW, FletchableLog.MAGIC_LONGBOW, FletchableLog.MAGIC_SHAFT, FletchableLog.MAGIC_STOCK, FletchableLog.MAGIC_SHIELD),
	YEW(FletchableLog.YEW_SHORTBOW, FletchableLog.YEW_LONGBOW, FletchableLog.YEW_SHAFT, FletchableLog.YEW_STOCK, FletchableLog.YEW_SHIELD),
	MAPLE(FletchableLog.MAPLE_SHORTBOW, FletchableLog.MAPLE_LONGBOW, FletchableLog.MAPLE_SHAFT, FletchableLog.MAPLE_STOCK, FletchableLog.MAPLE_SHIELD),
	TEAK(FletchableLog.STOCK),
	WILLOW(FletchableLog.WILLOW_SHORTBOW, FletchableLog.WILLOW_LONGBOW, FletchableLog.WILLOW_SHAFT, FletchableLog.WILLOW_STOCK, FletchableLog.WILLOW_SHIELD),
	OAK(FletchableLog.OAK_SHORTBOW, FletchableLog.OAK_LONGBOW, FletchableLog.OAK_SHAFT, FletchableLog.OAK_STOCK, FletchableLog.OAK_SHIELD),
	REDWOOD(FletchableLog.ARROW_SHAFT);


	private final FletchableLog[] fletchables;

	FletchableLogGroup(FletchableLog... fletchables) {
		this.fletchables = fletchables;
	}

	public FletchableLog[] getFletchables() {
		return fletchables;
	}

}
