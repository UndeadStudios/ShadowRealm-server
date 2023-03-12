package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class DragonLongsword extends Special {

	public DragonLongsword() {
		super(2.5, 1.0, 1.25, new int[] { 1305 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.gfx100(248);
		player.startAnimation(1058);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}
}