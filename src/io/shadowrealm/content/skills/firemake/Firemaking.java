package io.shadowrealm.content.skills.firemake;

import io.shadowrealm.Server;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.achievement_diary.impl.LumbridgeDraynorDiaryEntry;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.world.objects.GlobalObject;
import io.shadowrealm.util.Misc;

public class Firemaking {
	
	public static int[] pyromancerOutfit = { 20704, 20706, 20708, 20710 };
	
	public static void lightFire(final Player player, final int logUsed, final String usage) {
		if (Boundary.isIn(player, Boundary.EDGE_TRADING_AREA)) {
			player.sendMessage("You can not light fires here.");
			return;
		}
		double osrsExperience;
		int pieces = 0;
		for (int j : pyromancerOutfit) {
			if (player.getItems().isWearingItem(j)) {
				pieces++;
			}
		}
		final int[] time = new int[3];
		final int[] coords = new int[2];
		final LogData log = LogData.getLogData(player, logUsed);

		if (log == null) {
			player.sendMessage("You can't just go lighting random items on fire!");
			return;
		}

		final int level = log.getlevelRequirement();
		final String name = log.name().toLowerCase().replaceAll("_", " ");

		int clipping = player.getRegionProvider().getClipping(player.absX, player.absY, player.heightLevel);
		boolean occupiedByNpc = player.getRegionProvider().isOccupiedByNpc(player.absX, player.absY, player.heightLevel);
		if (clipping != 0 && !occupiedByNpc
				|| Server.getGlobalObjects().anyExists(player.absX, player.absY, player.heightLevel) || player.getPosition().inBank()
				|| Boundary.isIn(player, Boundary.DUEL_ARENA) || Boundary.isIn(player, Boundary.HALLOWEEN_ORDER_MINIGAME)) {
			player.sendMessage("You cannot light a fire here.");
			return;
		}
		if (player.playerLevel[11] < level) {
			player.sendMessage("You need a firemaking level of at least " + level + " to light the " + name + ".");
			return;
		}
		if (System.currentTimeMillis() - player.lastFire < 1800) {
			return;
		}
		if (player.playerIsFiremaking) {
			return;
		}
		if (log.getlogId() == logUsed) {
			if (!usage.equals("infernal_axe")) {
				if (!player.getItems().playerHasItem(logUsed)) {
					player.sendMessage("You do not have anymore of this log.");
					return;
				}
			}

			coords[0] = player.absX;
			coords[1] = player.absY;

			if (usage.equals("tinderbox")) {
				if (System.currentTimeMillis() - player.lastFire > 3000) {
					player.startAnimation(733);
					time[0] = 4;
					time[1] = 3;
				} else {
					time[0] = 1;
					time[1] = 2;
				}
				
				player.playerIsFiremaking = true;
				if (log.getlogId() == 1521) {
					player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.BURN_OAK);
					DailyTaskHandler.Companion.handleProgress(player,1);
				}
				if (log.getlogId() == 1521) {
					player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.BURN_OAK_LOGS.getDailyTask().getTaskName());
					DailyTaskHandler.Companion.handleProgress(player,1);
					System.out.println("Burnign oak logs");
				}
				if (log.getlogId() == 1517) {
					player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.BURN_MAPLE_LOGS.getDailyTask().getTaskName());
					DailyTaskHandler.Companion.handleProgress(player,1);
					System.out.println("Burnign oak logs");
				}
				if (log.getlogId() == 1513) {
					player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.BURN_MAGIC_LOGS.getDailyTask().getTaskName());
					DailyTaskHandler.Companion.handleProgress(player,1);
					System.out.println("Burnign oak logs");
				}
				player.getItems().deleteItem(log.getlogId(), player.getItems().getItemSlot(log.getlogId()), 1);
				//Server.itemHandler.createGroundItem(player, log.getlogId(), coords[0], coords[1], player.heightLevel, 1,
					//	player.getIndex());

				CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						Server.getGlobalObjects().add(new GlobalObject(5249, coords[0], coords[1], player.heightLevel, 0, 10, 50, -1));
					//	Server.itemHandler.removeGroundItem(player, log.getlogId(), coords[0], coords[1], player.heightLevel, false);
						//Server.itemHandler.removeGroundItem(player, log.getlogId(), false);
						player.playerIsFiremaking = false;
						container.stop();
					}

					@Override
					public void onStopped() {

					}
				}, time[0]);

				if (player.getRegionProvider().getClipping(player.getX() - 1, player.getY(), player.heightLevel, -1, 0)) {
					player.getPA().walkTo(-1, 0);
				} else if (player.getRegionProvider().getClipping(player.getX() + 1, player.getY(), player.heightLevel, 1, 0)) {
					player.getPA().walkTo(1, 0);
				} else if (player.getRegionProvider().getClipping(player.getX(), player.getY() - 1, player.heightLevel, 0, -1)) {
					player.getPA().walkTo(0, -1);
				} else if (player.getRegionProvider().getClipping(player.getX(), player.getY() + 1, player.heightLevel, 0, 1)) {
					player.getPA().walkTo(0, 1);
				}
				Achievements.increase(player, AchievementType.FIRE, 1);
				CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						player.startAnimation(65535);
						//Server.itemHandler.removeGroundItem(player, log.getlogId(), coords[0], coords[1], player.heightLevel, false);
						container.stop();
					}

					@Override
					public void onStopped() {
						//Server.itemHandler.removeGroundItem(player, log.getlogId(), coords[0], coords[1], player.heightLevel, false);
						Server.itemHandler.createGroundItem(player, 592, coords[0], coords[1], player.heightLevel, 1);
					}
				}, time[1]);
				player.facePosition(player.absX + 1, player.absY);
				player.getPA().sendSound(2596);
				player.lastFire = System.currentTimeMillis();
			}
			osrsExperience = log.getExperience() + log.getExperience() / 10 * pieces;
			
			if (usage.equals("infernal_axe")) {
				player.getPA().addSkillXPMultiplied((int)osrsExperience / 2, 11, true);
			} else {
				player.getPA().addSkillXPMultiplied((int) osrsExperience, 11, true);
			}
			if (Misc.random(2000) == 1) {
				if (player.getItems().getItemCount(20693, false) > 0 || player.petSummonId == 20693) {
					return;
				}
				int rights = player.getRights().getPrimary().getValue() - 1;
				player.getItems().addItemUnderAnyCircumstance(20693, 1);
				PlayerHandler.executeGlobalMessage("[@red@PET@bla@] @cr20@<col=255> " + player.getDisplayNameFormatted() + "</col> received a Phoenix pet.");
				player.getCollectionLog().handleDrop(player, 5, 20693, 1);
			}
			player.sendMessage("You light the " + name + ".");
		}
	}
}
