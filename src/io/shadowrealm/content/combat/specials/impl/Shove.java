package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class Shove extends Special {

	public Shove() {
		super(2.5, 1.00, 1.00, new int[] { 1249, 1263, 5716, 5730, 11824, 11889 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(405);
		player.gfx100(253);
		if (target instanceof Player) {
			Player other = (Player) target;
			if (player.playerAttackingIndex > 0) {
				other.getPA().getSpeared(player.absX, player.absY, 1);
				other.gfx0(80);
			}
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
