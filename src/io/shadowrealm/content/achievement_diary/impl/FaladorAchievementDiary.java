package io.shadowrealm.content.achievement_diary.impl;

import java.util.EnumSet;
import java.util.Set;

import io.shadowrealm.content.achievement_diary.StatefulAchievementDiary;
import io.shadowrealm.model.entity.player.Player;

public final class FaladorAchievementDiary extends StatefulAchievementDiary<FaladorDiaryEntry> {
	
	public static final Set<FaladorDiaryEntry> EASY_TASKS = EnumSet.of(FaladorDiaryEntry.TELEPORT_ESSENCE_FAL, FaladorDiaryEntry.CHOP_WILLOW, FaladorDiaryEntry.PICK_FLAX, FaladorDiaryEntry.PICKPOCKET_MAN, FaladorDiaryEntry.COMPOST_BUCKET, FaladorDiaryEntry.GRAPPLE_NORTH_WALL, FaladorDiaryEntry.TELEPORT_TO_FALADOR);
	
	public static final Set<FaladorDiaryEntry> MEDIUM_TASKS = EnumSet.of(FaladorDiaryEntry.KILL_WHITE_KNIGHT, FaladorDiaryEntry.RECOLOR_GRACEFUL, FaladorDiaryEntry.CHOP_YEW, FaladorDiaryEntry.PICKPOCKET_MASTER_FARMER_FAL, FaladorDiaryEntry.ALTAR_OF_GUTHIX, FaladorDiaryEntry.CRAFT_MIND);
	
	public static final Set<FaladorDiaryEntry> HARD_TASKS = EnumSet.of(FaladorDiaryEntry.PICK_POSION_BERRY, FaladorDiaryEntry.STEAL_GEM_FAL, FaladorDiaryEntry.KILL_GIANT_MOLE);
	
	public static final Set<FaladorDiaryEntry> ELITE_TASKS = EnumSet.of(FaladorDiaryEntry.HARVEST_TORSTOL, FaladorDiaryEntry.FISH_MANTA, FaladorDiaryEntry.MINE_GEM_FAL);
	
	public static final String NAME = "Falador area";

	public FaladorAchievementDiary(Player player) {
		super(NAME, player);
	}
	
	int REWARD = 13117;
	public void claimReward() {
		//EASY
		if (!hasDone(EntryDifficulty.EASY)) {
			npcDialogue("Come back when you've completed the easy tasks of this area.");
			return;
		} else {
			if (!hasClaimed(EntryDifficulty.EASY)) {
				npcDialogue("Nice job, here have the tier 1 reward.");
				addReward(REWARD);
				claim(EntryDifficulty.EASY);
				return;
			} else {
				if (getCount(REWARD) == 0 && !hasClaimed(EntryDifficulty.MEDIUM)) {
					npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
					addReward(REWARD);
					return;
				}
			}
		}
		
		//MEDIUM
		if (hasDone(EntryDifficulty.EASY) && hasDone(EntryDifficulty.MEDIUM) && hasClaimed(EntryDifficulty.EASY)) {
			if (hasClaimed(EntryDifficulty.MEDIUM)) {
				if (getCount(REWARD + 1) == 0) {
					if (!hasClaimed(EntryDifficulty.HARD)) {
						npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
						addReward(REWARD + 1);
						return;
					}
				}
			} else {
				if (player.getItems().playerHasItem(REWARD)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD, REWARD + 1);
					claim(EntryDifficulty.MEDIUM);
					return;
				} else {
					npcDialogue("Bring me the previous tier reward and I will upgrade it for you!");
					return;
				}
			}
		}
		
		//HARD
		if (hasDone(EntryDifficulty.EASY) && hasDone(EntryDifficulty.MEDIUM) && hasDone(EntryDifficulty.HARD) && hasClaimed(EntryDifficulty.MEDIUM)) {
			if (hasClaimed(EntryDifficulty.HARD)) {
				if (getCount(REWARD + 2) == 0) {
					if (!hasClaimed(EntryDifficulty.ELITE)) {
						npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
						addReward(REWARD + 2);
						return;
					}
				}
			} else {
				if (player.getItems().playerHasItem(REWARD + 1)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD + 1, REWARD + 2);
					claim(EntryDifficulty.HARD);
					return;
				} else {
					npcDialogue("Bring me the previous tier reward and I will upgrade it for you!");
					return;
				}
			}
		}
		
		//ELITE
		if (hasDone(EntryDifficulty.EASY) && hasDone(EntryDifficulty.MEDIUM) && hasDone(EntryDifficulty.HARD) && hasDone(EntryDifficulty.ELITE) && hasClaimed(EntryDifficulty.HARD)) {
			if (hasClaimed(EntryDifficulty.ELITE)) {
				if (getCount(REWARD + 3) == 0) {
					npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
					addReward(REWARD + 3);
					return;
				}
			} else {
				if (player.getItems().playerHasItem(REWARD + 2)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD + 2, REWARD + 3);
					claim(EntryDifficulty.ELITE);
					return;
				} else {
					npcDialogue("Bring me the previous tier reward and I will upgrade it for you!");
					return;
				}
			}
		}
		
	}
	
	public void npcDialogue(String dialogue) {
		player.getDH().sendNpcChat1(dialogue, player.npcType, "Diary Manager");
		player.nextChat = -1;
	}
	public void addReward(int reward) {
		player.getItems().addItem(reward, 1);
		player.getDH().sendNpcChat1("Here you go, you've earned it.", player.npcType, "Diary Manager");
	}
	public void upgradeReward(int reward, int upgrade) {
		player.getItems().replaceItem(player, reward, upgrade);
		player.getDH().sendNpcChat1("Here you go, upgraded and ready.", player.npcType, "Diary Manager");
	}
	
	public int getCount(int id) {
		return player.getItems().getItemCount(id, false);
	}
	
	public boolean hasCompleted(String difficulty) {
		switch (difficulty) {
		case "EASY":
			return achievements.containsAll(EASY_TASKS);
			
		case "MEDIUM":
			return achievements.containsAll(MEDIUM_TASKS);
			
		case "HARD":
			return achievements.containsAll(HARD_TASKS);
			
		case "ELITE":
			return achievements.containsAll(ELITE_TASKS);
		}
		return achievements.containsAll(EASY_TASKS);
	}

	@Override
	public Set<FaladorDiaryEntry> getEasy() {
		return EASY_TASKS;
	}

	@Override
	public Set<FaladorDiaryEntry> getMedium() {
		return MEDIUM_TASKS;
	}

	@Override
	public Set<FaladorDiaryEntry> getHard() {
		return HARD_TASKS;
	}

	@Override
	public Set<FaladorDiaryEntry> getElite() {
		return ELITE_TASKS;
	}
	
	int frameIndex;
	public final void display() {
		Set<FaladorDiaryEntry> all = getAll();
		int[] frames = { 26485, 26486, 26487, 26488, 26489, 26490, 26491, 26493, 26494, 26495, 26496, 26497, 26498, 26500, 26501,
				26502, 26503, 26505, 26506, 26507, 26508, 26509, 26510, 26511, 26512, 26513, 26514, 26515, 26516, 26517,
				26518, 26519, 26520, 26521, 26522, 26523, 26524, 26525, 26526, 26527, 26528, 26529, 26530 };

		player.getPA().resetQuestInterface();
		frameIndex = 0;
		
		player.getPA().sendFrame126("@dre@Falador Diary", 26659);
		player.getPA().sendFrame126("", 8145);
		player.getPA().sendFrame126(hasCompleted("EASY") ? "@blu@<str=1>Easy</str>" : "@blu@Easy", 26484);
		player.getPA().sendFrame126(hasCompleted("MEDIUM") ? "@blu@<str=1>Medium</str>" : "@blu@Medium", 26492);
		player.getPA().sendFrame126(hasCompleted("HARD") ? "@blu@<str=1>Hard</str>" : "@blu@Hard",26499);
		player.getPA().sendFrame126(hasCompleted("ELITE") ? "@blu@<str=1>Elite</str>" : "@blu@Elite", 26504);
		
		all.forEach(entry -> {
			String description = entry.getDescription();
			
			/* %stage gets the current stage (e.g. 1)
			 * %maximumstage gets the maximum stage (e.g. 5)
			 * %totalstage gets both of these (e.g. 1/5)
			 */
			description = description.replace("%stagej", Integer.toString(getAbsoluteAchievementStage(entry)));
			description = description.replace("%maximumstage", Integer.toString(getMaximum(entry)));
			description = description.replace("%totalstage", (getAbsoluteAchievementStage(entry)) + "/" + getMaximum(entry));
			
			player.getPA().sendFrame126(hasDone(entry) ? "<str=0>" +description+ "</str>" : description, frames[frameIndex]);
			frameIndex++;
		});

		player.getPA().openQuestInterface();
	}

	@Override
	public int getMaximum(FaladorDiaryEntry achievement) {
		return achievement.getMaximumStages();
	}

}