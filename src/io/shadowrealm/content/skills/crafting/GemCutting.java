package io.shadowrealm.content.skills.crafting;

import io.shadowrealm.Server;
import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

import java.util.Arrays;
import java.util.Optional;

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
			Server.getEventHandler().submit(new Event<>("skilling", c, 2) {

				@Override
				public void execute() {
					if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
						stop();
						return;
					}
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