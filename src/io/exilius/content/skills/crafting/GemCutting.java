package io.exilius.content.skills.crafting;

import java.util.Arrays;
import java.util.Optional;

import io.exilius.Server;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.skills.Skill;
import io.exilius.content.skills.smithing.Smelting;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Player;

public class GemCutting {

	public static void cut(Player c, int use, int used) {
		Optional<Gem> gem = Arrays.stream(Gem.values()).filter(g -> g.getUncut() == used || g.getUncut() == use).findFirst();
		gem.ifPresent(g -> {
			c.getPA().stopSkilling();
			if (c.playerLevel[Skill.CRAFTING.getId()] < g.getLevel()) {
				c.sendMessage("You need a crafting level of " + g.getLevel() + " to do this.");
				return;
			}
			c.startAnimation(g.getAnimation());
			Server.getEventHandler().submit(new Event<Player>("skilling", c, 2) {

				@Override
				public void execute() {
					if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
						stop();
						return;
					}
//					if (Misc.random(300) == 0 && attachment.getInterfaceEvent().isExecutable()) {
//						attachment.getInterfaceEvent().execute();
//						super.stop();
//						return;
//					}
					if (plr.getItems().playerHasItem(g.getUncut())) {
						plr.getItems().deleteItem2(g.getUncut(), 1);
						plr.getItems().addItem(g.getCut(), 1);
						plr.getPA().addSkillXPMultiplied(g.getExperience(), Skill.CRAFTING.getId(), true);
						plr.startAnimation(886);
					}
					if (!plr.getItems().playerHasItem(g.getUncut())) {
						stop();
						return;
					}
					if (g.equals(Gem.EMERALD) || c.currentDailyTask.getTaskName().equals(DailyTaskData.CUTTING_EMERALD.getDailyTask().getTaskName())) {
						DailyTaskHandler.Companion.handleProgress(c, 1);
						System.out.println("Player " + c.getLoginName() + " has just progressed on their iron bar task");
					}
					if (g.equals(Gem.DIAMOND) || c.currentDailyTask.getTaskName().equals(DailyTaskData.CUTTING_DIAMOND.getDailyTask().getTaskName())) {
						DailyTaskHandler.Companion.handleProgress(c, 1);
						System.out.println("Player " + c.getLoginName() + " has just progressed on their iron bar task");
					}
					if (g.equals(Gem.SAPPHIRE) || c.currentDailyTask.getTaskName().equals(DailyTaskData.CUTTING_SAPHIRE.getDailyTask().getTaskName())) {
						DailyTaskHandler.Companion.handleProgress(c, 1);
						System.out.println("Player " + c.getLoginName() + " has just progressed on their iron bar task");
					}
					if (g.equals(Gem.DRAGONSTONE) || c.currentDailyTask.getTaskName().equals(DailyTaskData.CUTTING_DRAGONSTONES.getDailyTask().getTaskName())) {
						DailyTaskHandler.Companion.handleProgress(c, 1);
						System.out.println("Player " + c.getLoginName() + " has just progressed on their iron bar task");
					}
					if (g.equals(Gem.ONYX) || c.currentDailyTask.getTaskName().equals(DailyTaskData.CUTTING_ONYX.getDailyTask().getTaskName())) {
						DailyTaskHandler.Companion.handleProgress(c, 1);
						System.out.println("Player " + c.getLoginName() + " has just progressed on their iron bar task");
					}

				}

				@Override
				public void stop() {
					super.stop();
					if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
						return;
					}
					plr.stopAnimation();
				}

			});
		});
	}

}