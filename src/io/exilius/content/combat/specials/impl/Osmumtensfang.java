package io.exilius.content.combat.specials.impl;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.core.HitDispatcher;
import io.exilius.content.combat.specials.Special;
import io.exilius.model.CombatType;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;

public class Osmumtensfang extends Special {

	public Osmumtensfang() {
		super(5.0, 1.25, 0.85, new int[] { 26219 });
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
