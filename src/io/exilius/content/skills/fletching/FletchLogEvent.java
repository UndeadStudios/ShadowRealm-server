package io.exilius.content.skills.fletching;

import io.exilius.content.SkillcapePerks;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
import io.exilius.content.achievement_diary.impl.WesternDiaryEntry;
import io.exilius.content.skills.Skill;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

public class FletchLogEvent extends Event<Player> {

	private final FletchableLog fletch;

	private int amount;

	public FletchLogEvent(Player attachment, int ticks, FletchableLog fletch, int amount) {
		super("skilling", attachment, ticks);
		this.fletch = fletch;
		this.amount = amount;
	}

	@Override
	public void execute() {
		if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
			stop();
			return;
		}
		if (plr.playerLevel[Skill.FLETCHING.getId()] < fletch.getLevel()) {
			plr.sendMessage("You need a fletching level of " + fletch.getLevel() + " to do this.");
			stop();
			return;
		}
		if (!plr.getItems().playerHasItem(fletch.getItemId())) {
			plr.sendMessage("You have run out of logs.");
			stop();
			return;
		}
		amount--;
		plr.startAnimation(1248);
		Achievements.increase(plr, AchievementType.FLETCH, 1);
		plr.getItems().deleteItem2(fletch.getItemId(), 1);
		switch (fletch.getProduct()) {
		case 70:
			if (Boundary.isIn(plr, Boundary.CATHERBY_BOUNDARY)) {
				plr.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.FLETCH_MAGIC_BOW);
			}
			break;
			
		case 54:
			if (Boundary.isIn(plr, Boundary.GNOME_STRONGHOLD_BOUNDARY)) {
				plr.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.FLETCH_OAK_SHORT_WEST);
			}
			break;
		}
		if (fletch.getProduct() == 52) {
			int amount = 15;
			if (fletch.getItemId() == 1521) {
				amount*= 2;
			}
			if (fletch.getItemId() == 1519) {
				amount*= 3;
			}
			if (fletch.getItemId() == 1517) {
				amount*= 4;
			}
			if (fletch.getItemId() == 1515) {
				amount*= 5;
			}
			if (fletch.getItemId() == 1513) {
				amount*= 6;
			}
			if (fletch.getItemId() == 19669) {
				amount*= 15;
			}
			if (SkillcapePerks.FLETCHING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr)) {
				amount+= 10;
			}
			plr.getItems().addItem(fletch.getProduct(), amount);
		} else {
			plr.getItems().addItem(fletch.getProduct(), 1);
		}

		plr.getPA().addSkillXPMultiplied(fletch.getExperience(), Skill.FLETCHING.getId(), true);
		if (amount <= 0) {
			stop();
			return;
		}
	}

	@Override
	public void stop() {
		super.stop();
		if (plr != null && !plr.isDisconnected() && plr.getSession() != null) {
			plr.stopAnimation();
		}
	}
}
