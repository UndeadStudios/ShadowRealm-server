package io.shadowrealm.content.skills.fletching;

import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

public class StringCrossbowEvent extends Event<Player> {

	private final FletchableCrossbow crossbow;

	public StringCrossbowEvent(FletchableCrossbow b, Player attachment, int ticks) {
		super("skilling", attachment, ticks);
		this.crossbow = b;
	}

	@Override
	public void execute() {
		if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
			stop();
			return;
		}
		if (!plr.getItems().playerHasItem(crossbow.getItem()) || !plr.getItems().playerHasItem(9438)) {
			stop();
			return;
		}
		plr.startAnimation(crossbow.getAnimation());
		plr.getItems().deleteItem2(crossbow.getItem(), 1);
		plr.getItems().deleteItem2(9438, 1);
		plr.getItems().addItem(crossbow.getProduct(), 1);
		plr.getPA().addSkillXPMultiplied((int) crossbow.getExperience(), Skill.FLETCHING.getId(), true);
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
