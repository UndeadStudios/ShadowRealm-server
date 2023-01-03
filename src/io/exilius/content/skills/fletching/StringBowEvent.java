package io.exilius.content.skills.fletching;

import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
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
			
		case 859:
			if (Boundary.isIn(plr, Boundary.LLETYA_BOUNDARY)) {
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
