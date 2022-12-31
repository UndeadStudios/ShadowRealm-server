package io.exilius.content.trails;

import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.model.Items;
import io.exilius.model.definitions.ItemDef;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;

import java.util.List;

public class TreasureTrails {

	public static final int EASY_CASKET = 20546;
	public static final int MEDIUM_CASKET = 20545;
	public static final int HARD_CASKET = 20544;
	public static final int MASTER_CASKET = 19836;

	public static final int EASY_CLUE_SCROLL = 2677;
	public static final int MEDIUM_CLUE_SCROLL = 2801;
	public static final int HARD_CLUE_SCROLL = 2722;
	public static final int MASTER_CLUE_SCROLL = 19835;

	private final Player player;

	public TreasureTrails(Player player) {
		this.player = player;
	}

	private static final int[] CLUE_HUNTER = {Items.CLUE_HUNTER_BOOTS, Items.CLUE_HUNTER_CLOAK,
			Items.CLUE_HUNTER_GARB, Items.CLUE_HUNTER_GLOVES, Items.CLUE_HUNTER_TROUSERS};

	private static int getCluesCompleted(Player player, RewardLevel difficulty) {
		switch (difficulty) {
			case MASTER:
				return player.getMasterClueCounter();
		}
		return 0;
	}

	private static void announceRare(Player player, GameItem item, RewardLevel difficulty) {
		PlayerHandler.executeGlobalMessage("[<col=CC0000>Treasure</col>] <col=255>" + player.getDisplayNameFormatted() + "</col> " +
				"<col=CC0000>received " +
				"<col=255>" + ItemDef.forId(item.getId()).getName() + "</col> " +
				"<col=CC0000>from <col=255>" + Misc.formatPlayerName(difficulty.name().toLowerCase()) + " clue #" + getCluesCompleted(player, difficulty) + ".");
	}

	public static boolean rollMimicCasket(RewardLevel rewardLevel) {
		int roll = rewardLevel == RewardLevel.MASTER ? 10 : 0;
		return roll != 0 && Misc.trueRand(roll) == 0;
	}

	/**
	 * This method is used by clues.
	 * Rolls=1+rand(1, 2)
	 */
	public List<GameItem> generateRewardList(RewardLevel rewardLevel) {
		return generateRewardList(rewardLevel, 1 + Misc.random(2));
	}

	/**
	 * This will generate the drop list, globally announce rares and update collection log. It's assumed
	 * that you will be dropped/adding these items immediately after calling!
	 * @param rewardLevel The {@link RewardLevel}
	 * @return List of random items.
	 */
	public List<GameItem> generateRewardList(RewardLevel rewardLevel, int rolls) {
		player.getNpcDeathTracker().add(Misc.optimizeText(rewardLevel.name().toLowerCase()), -1, 0);
		List<GameItem> rewards = TreasureTrailsRewards.getRandomRewardItems(rewardLevel, rolls);

		for (GameItem item : rewards) {
			if (ItemDef.forId(item.getId()).getName().contains("3rd") || item.getId() == 2577 || ItemDef.forId(item.getId()).getName().contains("mage's")) {
				announceRare(player, item, rewardLevel);
			}

			if (TreasureTrailsRewards.possibleDrops.get(rewardLevel).stream().anyMatch(it -> it.getItemId() == item.getId())) {
				player.getCollectionLog().handleDrop(player, rewardLevel.ordinal(), item.getId(), item.getAmount());
			}
		}

		if (rewardLevel == RewardLevel.MASTER) {
			int rand = Misc.trueRand(rewardLevel == RewardLevel.MASTER ? 512 : 1024);
			int item = CLUE_HUNTER[Misc.trueRand(CLUE_HUNTER.length)];
			if (rand == 0) {
				announceRare(player, new GameItem(item, 1), rewardLevel);
				rewards.add(new GameItem(item, 1));
			}
		}

		return rewards;
	}

	public void addRewards(RewardLevel rewardLevel) {
		List<GameItem> rewards = generateRewardList(rewardLevel);
		rewards.forEach(it -> player.getItems().addItemUnderAnyCircumstance(it.getId(), it.getAmount()));
		displayRewards(rewards);
	}

	public void displayRewards(List<GameItem> rewards) {
		player.outStream.createFrameVarSizeWord(53);
		player.outStream.writeUnsignedWord(6963);
		player.outStream.writeUnsignedWord(rewards.size());
		for (int i = 0; i < rewards.size(); i++) {
			if (player.playerItemsN[i] > 254) {
				player.outStream.writeByte(255);
				player.outStream.writeDWord_v2(rewards.get(i).getAmount());
			} else {
				player.outStream.writeByte(rewards.get(i).getAmount());
			}
			if (rewards.size() > 0) {
				player.outStream.writeWordBigEndianA(rewards.get(i).getId() + 1);
			} else {
				player.outStream.writeWordBigEndianA(0);
			}
		}
		player.outStream.endFrameVarSizeWord();
		player.flushOutStream();
		player.getPA().showInterface(6960);
	}

	private static void openCasket(Player player, RewardLevel rewardLevel) {
		if (player.getItems().freeSlots() < 3) {
			player.sendMessage("You need at least 3 free slots to open this.");
			return;
		}

		player.getItems().deleteItem(rewardLevel.getCasketId(), 1);
		player.getTrails().addRewards(rewardLevel);

		switch (rewardLevel) {
			case MASTER:
				player.setMasterClueCounter(player.getMasterClueCounter() + 1);
				player.sendMessage("<col=2d256d>You have completed " + player.getMasterClueCounter() + " master Treasure Trails.");
				PetHandler.roll(player, PetHandler.Pets.BLOODHOUND);
				break;
		}
	}

	private static void openClueScroll(Player c, RewardLevel rewardLevel) {
		if (rewardLevel == RewardLevel.MASTER && !MasterClue.checkRequirementOnOpen(c)) {
			return;
		}

		Achievements.increase(c, AchievementType.CLUES, 1);
		c.getItems().deleteItem(rewardLevel.getClueScrollId(), 1);

		if (rollMimicCasket(rewardLevel)) {
			c.getItems().addItem(Items.MIMIC, 1);
			c.sendMessage("You've received a Mimic casket!");
		} else {
			c.getItems().addItem(rewardLevel.getCasketId(), 1);
			c.sendMessage("You've received a " + rewardLevel.getFormattedName() + " clue scroll casket.");
		}
	}

	public static boolean firstClickItem(Player c, int itemId) {
		for (RewardLevel rewardLevel : RewardLevel.values()) {
			if (rewardLevel.getCasketId() == itemId) {
				openCasket(c, rewardLevel);
				return true;
			}

			if (rewardLevel.getClueScrollId() == itemId) {
				openClueScroll(c, rewardLevel);
				return true;
			}
		}

		return false;
	}
}
