package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class SaradominGodsword extends Special {

	public SaradominGodsword() {
		super(5.0, 2.0, 1.1, new int[] { 11806, 20372 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(7640);
		player.gfx0(1209);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (damage.getAmount() < 1) {
			return;
		}
		if (damage.getAmount() < 21) {
			player.getHealth().increase(10);
			player.replenishSkill(Skill.PRAYER.getId(), 5);
		} else {
			player.getHealth().increase(damage.getAmount() / 2);
			player.replenishSkill(Skill.PRAYER.getId(), damage.getAmount() / 4);
		}
	}

}
