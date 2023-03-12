package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class AbyssalBludgeon extends Special {

	public AbyssalBludgeon() {
		super(5.0, 2.10, 1.30, new int[] { 13263 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(3299);
		if (target instanceof Player) {
			((Player) target).gfx0(1284);
		}
		if (target instanceof NPC) {
			((NPC) target).gfx0(1284);
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
