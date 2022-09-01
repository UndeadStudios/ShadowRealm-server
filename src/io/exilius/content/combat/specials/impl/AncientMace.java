package io.exilius.content.combat.specials.impl;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.specials.Special;
import io.exilius.content.skills.Skill;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.player.Player;

public class AncientMace extends Special {

	public AncientMace() {
		super(10.0, 1.0, 1.0, new int[] { 11061 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.gfx0(1052);
		player.startAnimation(6147);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (target.isPlayer()) {
			if (damage.isSuccess()) {
				int damageDealt = damage.getAmount();
				int prayerLevel = player.getLevelForXP(player.playerXP[Skill.PRAYER.getId()]);
				int currLevel = player.playerLevel[Skill.PRAYER.getId()];
				int maxBoostedLevel = prayerLevel + damageDealt;

				if (damageDealt + currLevel > maxBoostedLevel && currLevel < maxBoostedLevel) {
					player.playerLevel[Skill.PRAYER.getId()] = maxBoostedLevel;
				} else {
					player.playerLevel[Skill.PRAYER.getId()] += damageDealt;
				}
			}
		}
	}

}
