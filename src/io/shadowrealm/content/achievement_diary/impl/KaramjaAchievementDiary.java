package io.shadowrealm.content.achievement_diary.impl;

import io.shadowrealm.content.achievement_diary.StatefulAchievementDiary;
import io.shadowrealm.model.entity.player.Player;

import java.util.EnumSet;
import java.util.Set;

public final class KaramjaAchievementDiary extends StatefulAchievementDiary<KaramjaDiaryEntry> {
	
	public static final Set<KaramjaDiaryEntry> EASY_TASKS = EnumSet.of(KaramjaDiaryEntry.PICK_BANANAS, KaramjaDiaryEntry.SAIL_TO_ARDOUGNE, KaramjaDiaryEntry.FISH_LOBSTER_KAR);
	
	public static final Set<KaramjaDiaryEntry> MEDIUM_TASKS = EnumSet.of(KaramjaDiaryEntry.DURADEL, KaramjaDiaryEntry.STEEL_DRAGON, KaramjaDiaryEntry.DANCE_75_TIMES);
	
	public static final Set<KaramjaDiaryEntry> HARD_TASKS = EnumSet.of(KaramjaDiaryEntry.COMPLETE_FIGHT_CAVES, KaramjaDiaryEntry.MINE_GOLD_KAR, KaramjaDiaryEntry.KILL_TZHAAR_XIL);
	
	public static final Set<KaramjaDiaryEntry> ELITE_TASKS = EnumSet.of(KaramjaDiaryEntry.CRAFT_NATURES, KaramjaDiaryEntry.ANTI_VENOM, KaramjaDiaryEntry.EQUIP_FIRE_CAPE);
	
	public static final String NAME = "Karamja area";

	public KaramjaAchievementDiary(Player player) {
		super(NAME, player);
	}
	
	int REWARD = 11136;
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
				if (getCount(REWARD + 2) == 0) {
					if (!hasClaimed(EntryDifficulty.HARD)) {
						npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
						addReward(REWARD + 2);
						return;
					}
				}
			} else {
				if (player.getItems().playerHasItem(REWARD)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD, REWARD + 2);
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
				if (getCount(REWARD + 4) == 0) {
					if (!hasClaimed(EntryDifficulty.ELITE)) {
						npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
						addReward(REWARD + 4);
						return;
					}
				}
			} else {
				if (player.getItems().playerHasItem(REWARD + 2)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD + 2, REWARD + 4);
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
				if (getCount(13103) == 0) {
					npcDialogue("Oh, you lost your reward? Don't worry, here you go.");
					addReward(13103);
					return;
				}
			} else {
				if (player.getItems().playerHasItem(REWARD + 4)) {
					npcDialogue("Nice one, I will upgrade that for you..");
					upgradeReward(REWARD + 4, 13103);
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
		player.getDH().sendNpcChat1("Here you go, upgraded and ready to be used.", player.npcType, "Diary Manager");
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
	public Set<KaramjaDiaryEntry> getEasy() {
		return EASY_TASKS;
	}

	@Override
	public Set<KaramjaDiaryEntry> getMedium() {
		return MEDIUM_TASKS;
	}

	@Override
	public Set<KaramjaDiaryEntry> getHard() {
		return HARD_TASKS;
	}

	@Override
	public Set<KaramjaDiaryEntry> getElite() {
		return ELITE_TASKS;
	}
	
	int frameIndex;
	public final void display() {
		Set<KaramjaDiaryEntry> all = getAll();
		int[] frames = { 26485, 26486, 26487, 26488, 26489, 26491, 26492, 26493, 26495, 26496, 26497, 26499, 26500, 26501, 26502,
				26503, 26504, 26505, 26506, 26507, 26508, 26509, 26510, 26511, 26512, 26513, 26514, 26515, 26516, 26517,
				26518, 26519, 26520, 26521, 26522, 26523, 26524, 26525, 26526, 26527, 26528, 26529, 26530 };

		player.getPA().resetQuestInterface();
		frameIndex = 0;
		
		player.getPA().sendFrame126("@dre@Karamja Diary", 26659);
		player.getPA().sendFrame126("", 8145);
		player.getPA().sendFrame126(hasCompleted("EASY") ? "@blu@<str=1>Easy</str>" : "@blu@Easy", 26484);
		player.getPA().sendFrame126(hasCompleted("MEDIUM") ? "@blu@<str=1>Medium</str>" : "@blu@Medium", 26490);
		player.getPA().sendFrame126(hasCompleted("HARD") ? "@blu@<str=1>Hard</str>" : "@blu@Hard", 26494);
		player.getPA().sendFrame126(hasCompleted("ELITE") ? "@blu@<str=1>Elite</str>" : "@blu@Elite", 26498);
		
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
	public int getMaximum(KaramjaDiaryEntry achievement) {
		return achievement.getMaximumStages();
	}

}