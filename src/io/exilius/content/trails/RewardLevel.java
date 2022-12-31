package io.exilius.content.trails;

import io.exilius.util.Misc;

import java.util.Arrays;
import java.util.List;

public enum RewardLevel {
	SHARED(-1, -1), // Not gonna remove this because collection log relies on it..
	MASTER(TreasureTrails.MASTER_CLUE_SCROLL, TreasureTrails.MASTER_CASKET);

	public static final RewardLevel[] ALL = { RewardLevel.MASTER };

	private final int clueScrollId;
	private final int casketId;

	RewardLevel(int clueScrollId, int casketId) {
		this.clueScrollId = clueScrollId;
		this.casketId = casketId;
	}

	public String getFormattedName() {
		return Misc.formatPlayerName(name().toLowerCase());
	}

	public int getClueScrollId() {
		return clueScrollId;
	}

	public int getCasketId() {
		return casketId;
	}
	
	public static final List<RewardLevel> VALUES = Arrays.asList(values());
	
	public static List<RewardLevel> getValues() {
		return VALUES;
	}
}
