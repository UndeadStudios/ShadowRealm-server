package io.exilius.model.cycleevent.impl;

import io.exilius.content.SkillcapePerks;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Player;

public class SkillRestorationEvent extends Event<Player> {

	public SkillRestorationEvent(Player attachment) {
		super(attachment, 100);
	}

	@Override
	public void execute() {
		if (plr.isDead || plr.getHealth().getCurrentHealth() <= 0) {
			return;
		}
		plr.getHealth().tick(SkillcapePerks.HITPOINTS.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr) ? 2 : 1);

		for (int index = 0; index < plr.playerLevel.length; index++) {
			if (index == 3 || index == 5) {
				continue;
			}
			if ((index == 0 || index == 1 || index == 2) && plr.hasDivineCombatBoost) {
				continue;
			}
			if ((index == 4) && plr.hasDivineRangeBoost) {
				continue;
			}
			if ((index == 6) && plr.hasDivineMagicBoost) {
				continue;
			}
			if ((index == 0 || index == 1 || index == 2
					|| index == 4 || index == 6) && plr.hasOverloadBoost) {
				continue;
			}
			final int maximum = plr.getLevelForXP(plr.playerXP[index]);
			if (plr.playerLevel[index] < maximum) {
				plr.playerLevel[index]++;
			} else if (plr.playerLevel[index] > maximum) {
				plr.playerLevel[index]--;
			}
			plr.getPA().refreshSkill(index);
		}
	}

}
