package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class StatiusWarhammer extends Special {

	public StatiusWarhammer() {
		super(3.5, 1.25, 0.25, new int[] { 22622 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.gfx0(1241);
		player.startAnimation(1378);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		new DragonWarhammer().hit(player, target, damage);
	}

}
