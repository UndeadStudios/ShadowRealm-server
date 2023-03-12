package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class VestaLongsword extends Special {

	public VestaLongsword() {
		super(2.5, 1.0, 0.20, new int[] { 22613 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(7515);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
