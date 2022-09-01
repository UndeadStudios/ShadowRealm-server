package io.exilius.content.combat.effects.damageeffect.impl.bolts;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.exilius.content.combat.range.RangeData;
import io.exilius.model.Items;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class DiamondBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		int change = Misc.random((int) (damage.getAmount() * 1.15));
		damage.setAmount(change);
		RangeData.createCombatGraphic(defender, 758, false);
		defender.ignoreDefence = true;
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		RangeData.createCombatGraphic(defender, 758, false);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.DIAMOND_BOLTS_E, Items.DIAMOND_DRAGON_BOLTS_E);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0.15;
	}

}
