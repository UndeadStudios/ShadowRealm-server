package io.exilius.content.combat.effects.damageeffect.impl;

import java.util.Optional;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.effects.damageeffect.DamageEffect;
import io.exilius.model.entity.HealthStatus;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class ToxicStaffOfTheDeadEffect implements DamageEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		defender.getHealth().proposeStatus(HealthStatus.VENOM, damage.getAmount(), Optional.of(defender));
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		defender.getHealth().proposeStatus(HealthStatus.VENOM, damage.getAmount(), Optional.of(attacker));
	}

	@Override
	public boolean isExecutable(Player operator) {
		return operator.getItems().isWearingItem(12904, Player.playerWeapon) && operator.getToxicStaffOfTheDeadCharge() > 0 && Misc.random(5) == 1;
	}

}
