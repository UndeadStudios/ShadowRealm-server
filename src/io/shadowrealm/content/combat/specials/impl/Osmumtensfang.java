package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.core.HitDispatcher;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class Osmumtensfang extends Special {

	public Osmumtensfang() {
		super(2.5, 1.25, 0.85, new int[] { 26219 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(9544);
		player.gfx0(2128);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
