package io.exilius.content.skills.fletching;

import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.skills.Skill;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

public class StringBowEvent extends Event<Player> {

	private final FletchableBow bow;

	public StringBowEvent(FletchableBow bow, Player attachment, int ticks) {
		super("skilling", attachment, ticks);
		this.bow = bow;
	}

	@Override
	public void execute() {
		if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
			stop();
			return;
		}
		if (!plr.getItems().playerHasItem(bow.getItem()) || !plr.getItems().playerHasItem(1777)) {
			stop();
			return;
		}
		switch (bow.getProduct()) {
		case 853:
			if (Boundary.isIn(plr, Boundary.SEERS_BOUNDARY)) {
				plr.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.STRING_MAPLE_SHORT);
			}
			break;
		case 839:
			if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.FLETCH_LONGBOW.getDailyTask().getTaskName())) {
			DailyTaskHandler.Companion.handleProgress(plr, 1);
			System.out.println("Player " + plr.getLoginName() + " has just progressed on their cooking trout task");
		}
			break;
		case 845:
			if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.FLETCH_OAK_LONGBOW.getDailyTask().getTaskName())) {
				DailyTaskHandler.Companion.handleProgress(plr, 1);
				System.out.println("Player " + plr.getLoginName() + " has just progressed on their cooking trout task");
		}
			break;
		case 851:
			if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.FLETCH_MAPLE_LONGBOW.getDailyTask().getTaskName())) {
				DailyTaskHandler.Companion.handleProgress(plr, 1);
				System.out.println("Player " + plr.getLoginName() + " has just progressed on their cooking trout task");
		}
			break;
		case 847:
			if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.FLETCH_WILLOW_LONGBOW.getDailyTask().getTaskName())) {
				DailyTaskHandler.Companion.handleProgress(plr, 1);
				System.out.println("Player " + plr.getLoginName() + " has just progressed on their cooking trout task");
			}
			break;
		case 859:
			if (Boundary.isIn(plr, Boundary.LLETYA_BOUNDARY)) {
			}
			if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.FLETCH_MAGIC_LONGBOW.getDailyTask().getTaskName())) {
				DailyTaskHandler.Companion.handleProgress(plr, 1);
				System.out.println("Player " + plr.getLoginName() + " has just progressed on their cooking trout task");
			}
			break;
		}
		plr.startAnimation(bow.getAnimation());
		plr.getItems().deleteItem2(bow.getItem(), 1);
		plr.getItems().deleteItem2(1777, 1);
		plr.getItems().addItem(bow.getProduct(), 1);
		plr.getPA().addSkillXPMultiplied((int) bow.getExperience(), Skill.FLETCHING.getId(), true);
	}

	@Override
	public void stop() {
		super.stop();
		if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
			return;
		}
		plr.stopAnimation();
	}

}
