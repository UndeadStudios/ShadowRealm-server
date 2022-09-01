package io.exilius.content.combat.effects.damageeffect.impl.bolts;

import java.util.Optional;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.exilius.content.combat.range.RangeData;
import io.exilius.model.Items;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.HealthStatus;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;

public class EmeraldBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		RangeData.createCombatGraphic(defender, 752, true);
		defender.getHealth().proposeStatus(HealthStatus.POISON, 5, Optional.empty());
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		defender.getHealth().proposeStatus(HealthStatus.POISON, 5, Optional.empty());
		RangeData.createCombatGraphic(defender, 752, true);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.EMERALD_BOLTS_E, Items.EMERALD_DRAGON_BOLTS_E);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0;
	}

}
