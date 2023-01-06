package io.exilius.content.skills.cooking;

import io.exilius.content.SkillcapePerks;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.LumbridgeDraynorDiaryEntry;
import io.exilius.content.achievement_diary.impl.VarrockDiaryEntry;
import io.exilius.content.achievement_diary.impl.WesternDiaryEntry;
import io.exilius.content.achievement_diary.impl.WildernessDiaryEntry;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.skills.SkillHandler;
import io.exilius.model.Items;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.ItemAssistant;
import io.exilius.util.Misc;

import java.security.SecureRandom;

/**
 * Class Cooking Handles Cooking
 * 
 * @author 2012 START: 20:13 25/10/2010 FINISH: 20:21 25/10/2010
 * @author edited by Snappie
 */

public class Cooking extends SkillHandler {

	private static SecureRandom cookingRandom = new SecureRandom(); // The random factor

	private static enum CookingItems {
		//raw, cooked, burnt, levelreq, exp, stopburn, stopburn w/gloves, name
		SHRIMP(317, 315, 7954, 1, 30, 34, 30, "shrimp"),
		SARDINE(327, 325, 369, 1, 40, 38, 38, "sardine"),
		HERRING(345, 347, 357, 5, 50, 41, 41, "herring"),
		TROUT(335, 333, 343, 15, 70, 50, 50, "trout"),
		TUNA(359, 361, 367, 30, 100, 64, 63, "tuna"),
		ANCHOVIES(321, 319, 323, 5, 45, 34, 34, "anchovies"),
		RAW_BEEF(2132, 2142, 2146, 1, 30, 33, 33, "raw beef"),
		RAW_RAT(2134, 2142, 2146, 1, 30, 33, 33, "raw rat meat"),
		BURNT_MEAT(2142, 2146, 2146, 1, 1, 100, 100, "cooked meat"),
		RAW_CHICKEN(2138, 2140, 2144, 1, 30, 33, 33, "raw chicken"),
		RAW_BEAR_MEAT(2136, 2142, 2146, 1, 30, 33, 33, "raw bear meat"),
		MACKERAL(353, 355, 357, 10, 60, 45, 45, "mackeral"),
		SALMON(331, 329, 343, 25, 90, 58, 55, "salmon"),
		UNCOOKED_BERRY_PIE(2321, 2325, 2329, 10, 78, 50, 50, "uncooked pie"),
		PIKE(349, 351, 343, 20, 80, 59, 59, "pike"),
		KARAMBWAN(3142, 3144, 3146, 1, 80, 20, 20, "karambwan"),
		LOBSTER(377, 379, 381, 40, 120, 74, 68, "lobster"),
		SWORDFISH(371, 373, 375, 50, 140, 86, 81, "swordfish"),
		MONKFISH(7944, 7946, 7948, 62, 150, 92, 90,	"monkfish"),
		SHARK(383, 385, 387, 76, 210, 100, 94, "shark"),
		MANTA_RAY(389, 391, 393, 91, 169, 100, 100, "manta ray"),
		SEAWEED(401, 1781, 1781, 1, 1, 1, 1, "sea weed"),
		CURRY(2009, 2011, 2013, 60, 280, 74, 74, "curry"),
		ANGLERFISH(13439, 13441, 13443, 84, 230, 98, 98, "angler fish"),
		DARK_CRAB(11934, 11936, 11938, 90, 215, 100, 100, "dark crab"),
		SEA_TURTLE(Items.RAW_SEA_TURTLE, Items.SEA_TURTLE, Items.BURNT_SEA_TURTLE, 82, 211, 150, 150, "sea turtle");

		int rawItem, cookedItem, burntItem, levelReq, xp, stopBurn, stopBurnGloves;
		String name;

		private CookingItems(int rawItem, int cookedItem, int burntItem, int levelReq, int xp, int stopBurn, int stopBurnGloves, String name) {
			this.rawItem = rawItem;
			this.cookedItem = cookedItem;
			this.burntItem = burntItem;
			this.levelReq = levelReq;
			this.xp = xp;
			this.stopBurn = stopBurn;
			this.name = name;
		}

		private int getRawItem() {
			return rawItem;
		}

		private int getCookedItem() {
			return cookedItem;
		}

		private int getBurntItem() {
			return burntItem;
		}

		private int getLevelReq() {
			return levelReq;
		}

		private int getXp() {
			return xp;
		}

		private int getStopBurn() {
			return stopBurn;
		}

		private int getStopBurnGloves() {
			return stopBurnGloves;
		}

		private String getName() {
			return name;
		}
	}

	public static CookingItems forId(int itemId) {
		for (CookingItems item : CookingItems.values()) {
			if (itemId == item.getRawItem()) {
				return item;
			}
		}
		return null;
	}

	public static void makeBreadOptions(Player c, int item) {
		if (c.getItems().playerHasItem(1929) && c.getItems().playerHasItem(1933) && item == c.breadID) {
			c.getItems().deleteItem(1929, 1);
			c.getItems().deleteItem(1933, 1);
			c.getItems().addItem(1925, 1);
			c.getItems().addItem(1931, 1);
			c.getItems().addItem(item, 1);
			c.sendMessage("You mix the water and flour to make some " + ItemAssistant.getItemName(item) + ".");
		}
		c.getPA().closeAllWindows();

	}

	public static void pastryCreation(Player c, int itemID1, int itemID2, int giveItem, String message) {
		if (c.getItems().playerHasItem(itemID1) && c.getItems().playerHasItem(itemID2)) {
			c.getItems().deleteItem(itemID1, 1);
			c.getItems().deleteItem(itemID2, 1);
			c.getItems().addItem(giveItem, 1);
			if (message.equalsIgnoreCase("")) {
				c.sendMessage("You mix the two ingredients and get an " + ItemAssistant.getItemName(giveItem) + ".");
			} else {
				c.sendMessage(message);
			}
		}
	}

	public static void cookingAddon(Player c, int itemID1, int itemID2, int giveItem, int requiredLevel, int expGained) {
		if (c.playerLevel[7] >= requiredLevel) {
			if (c.getItems().playerHasItem(itemID1) && c.getItems().playerHasItem(itemID2)) {
				c.getItems().deleteItem(itemID1, 1);
				c.getItems().deleteItem(itemID2, 1);
				c.getItems().addItem(giveItem, 1);
				c.getPlayerAssistant().addSkillXP(expGained, 7, true);
				c.sendMessage("You create a " + ItemAssistant.getItemName(giveItem) + ".");
			}
		} else {
			c.sendMessage("You don't have the required level to make an " + ItemAssistant.getItemName(giveItem));
		}
	}

	public static void setCooking(Player player, boolean isCooking) {
		player.playerIsCooking = isCooking;
		player.stopPlayerSkill = isCooking;
	}

	private static void viewCookInterface(Player c, int item) {
		c.getPA().sendChatboxInterface(1743);
		c.getPA().sendFrame246(13716, view190 ? 190 : 170, item);
		c.getPA().sendString(getLine(c) + "" + ItemAssistant.getItemName(item) + "", 13717);
	}

	public static boolean startCooking(Player c, int itemId, int objectId) {
		CookingItems item = forId(itemId);
		if (item != null) {
			if (c.playerLevel[c.playerCooking] < item.getLevelReq()) {
				c.getPA().closeAllWindows();
				c.getDH().sendStatement("You need a Cooking level of " + item.getLevelReq() + " to cook this.");
				c.nextChat = 0;
				return false;
			}
			if (c.playerIsCooking) {
				c.getPA().closeAllWindows();
				return false;
			}
			if (!COOKING) {
				c.sendMessage("This skill is currently disabled.");
				return false;
			}
			// save the id of the item and object for the cooking interface.
			c.cookingItem = itemId;
			c.cookingObject = objectId;
			viewCookInterface(c, item.getRawItem());
			return true;
		}
		return false;
	}

	private static boolean getSuccess(Player c, int burnBonus, int levelReq, int stopBurn) {
		if (c.playerLevel[c.playerCooking] >= stopBurn) {
			return true;
		}
		double burn_chance = 55.0 - burnBonus;
		double cook_level = c.playerLevel[c.playerCooking];
		double lev_needed = levelReq;
		double burn_stop = stopBurn;
		double multi_a = burn_stop - lev_needed;
		double burn_dec = burn_chance / multi_a;
		double multi_b = cook_level - lev_needed;
		burn_chance -= multi_b * burn_dec;
		double randNum = cookingRandom.nextDouble() * 100.0;
		return burn_chance <= randNum;
	}

	public static void cookItem(final Player player, final int itemId, final int amount, final int objectId) {
		CycleEventHandler.getSingleton().stopEvents(player, "cookingEvent".hashCode());
		final CookingItems item = forId(itemId);
		if (item != null) {
			setCooking(player, true);
			//RandomEventHandler.addRandom(player);
			player.getPA().closeAllWindows();
			player.doAmount = amount;
			if (player.doAmount > player.getItems().getItemAmount(itemId)) {
				player.doAmount = player.getItems().getItemAmount(itemId);
			}
			if (objectId > 0) {
				player.startAnimation(objectId == 2732 ? 897 : 896);
			}
			CycleEventHandler.getSingleton().addEvent("cookingEvent".hashCode(), player, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (!player.playerIsCooking) {
						setCooking(player, false);
						container.stop();
						return;
					}
					if (!player.getItems().playerHasItem(item.getRawItem(), 1)) {
						player.sendMessage("You have run out of " + item.getName() + " to cook.");
						setCooking(player, false);
						container.stop();
						return;
					}
					boolean burn;
					if (player.playerEquipment[9] == 775 || SkillcapePerks.COOKING.isWearing(player) || SkillcapePerks.isWearingMaxCape(player)) {
						burn = !getSuccess(player, 3, item.getLevelReq(), item.getStopBurnGloves());
					} else {
						burn = !getSuccess(player, 3, item.getLevelReq(), item.getStopBurn());
					}
					player.getItems().deleteItem(item.getRawItem(),
							player.getItems().getItemSlot(itemId), 1);
					if (!burn) {
						player.sendMessage("You successfully cook the " + item.getName().toLowerCase() + ".");
						switch (itemId) {
							case 7944:
								player.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.COOK_MONK);
								break;
							case 377:
								if (Boundary.isIn(player, Boundary.VARROCK_BOUNDARY)) {
									player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.COOK_LOBSTER);
								}
								if (player.currentDailyTask.getTaskName().equals(DailyTaskData.COOK_LOBSTER.getDailyTask().getTaskName())) {
									DailyTaskHandler.Companion.handleProgress(player, 1);
									System.out.println("Player " + player.getLoginName() + " has just progressed on their Cooking Shrimp task");
								}
								break;
							case 335:
								if (player.currentDailyTask.getTaskName().equals(DailyTaskData.COOK_TROUT.getDailyTask().getTaskName())) {
									DailyTaskHandler.Companion.handleProgress(player, 1);
									System.out.println("Player " + player.getLoginName() + " has just progressed on their cooking trout task");
								}
							case 317:
								if (Boundary.isIn(player, Boundary.LUMRIDGE_BOUNDARY)) {
									player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.COOK_SHRIMP);
								}
								if (player.currentDailyTask.getTaskName().equals(DailyTaskData.COOK_SHRIMP.getDailyTask().getTaskName())) {
									DailyTaskHandler.Companion.handleProgress(player, 1);
									System.out.println("Player " + player.getLoginName() + " has just progressed on their Cooking Shrimp task");
								}
								break;
							case 11934:
							case 11936:
								if (Boundary.isIn(player, Boundary.RESOURCE_AREA_BOUNDARY)) {
									player.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.DARK_CRAB);
								}
								break;
							case 331:
								if (player.currentDailyTask.getTaskName().equals(DailyTaskData.COOK_SALMON.getDailyTask().getTaskName())) {
									DailyTaskHandler.Companion.handleProgress(player, 1);
									System.out.println("Player " + player.getLoginName() + " has just progressed on their cooking trout task");
								}
						}
						player.getPlayerAssistant().addSkillXPMultiplied(item.getXp(), player.playerCooking, true);
						player.getItems().addItem(item.getCookedItem(), 1);
						Achievements.increase(player, AchievementType.COOK, 1);
					} else {
						player.sendMessage(
								"Oops! You accidentally burnt the "
										+ item.getName().toLowerCase() + "!");
						player.getItems().addItem(item.getBurntItem(), 1);
					}
					player.doAmount--;
					if (player.isDisconnected()) {
						container.stop();
						return;
					}
					if (objectId < 0) {
						container.stop();
						return;
					}
					if (player.playerIsCooking && !Misc.goodDistance(player.objectX, player.objectY, player.absX, player.absY, 2)) {
						container.stop();
						return;
					}
					if (player.doAmount > 0) {
						if (objectId > 0) {
							player.startAnimation(objectId == 2732 ? 897 : 896);
						}
					} else if (player.doAmount == 0) {
						setCooking(player, false);
						container.stop();
					}
				}

				public void stop() {

				}
			}, 4);
		}
	}
}
