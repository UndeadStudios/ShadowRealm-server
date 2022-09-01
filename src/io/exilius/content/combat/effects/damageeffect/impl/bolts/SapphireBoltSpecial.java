package io.exilius.content.combat.effects.damageeffect.impl.bolts;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.exilius.content.combat.range.RangeData;
import io.exilius.model.Items;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class SapphireBoltSpecial implements DamageBoostingEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		int change = Misc.random(damage.getAmount());
		damage.setAmount(change);
		RangeData.createCombatGraphic(defender, 751, false);

		if (attacker.playerAttackingIndex > 0) {
			defender.playerLevel[5] -= 2;
			defender.getPA().refreshSkill(5);
			defender.sendMessage("Your prayer has been lowered!");
			attacker.playerLevel[5] += 2;
			if (attacker.playerLevel[5] >= attacker.getPA().getLevelForXP(attacker.playerXP[5])) {
				attacker.playerLevel[5] = attacker.getPA().getLevelForXP(attacker.playerXP[5]);
			}
			attacker.getPA().refreshSkill(5);
		}
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getName() == null) {
			return;
		}
		RangeData.createCombatGraphic(defender, 751, false);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeData.boltSpecialAvailable(operator, Items.SAPPHIRE_BOLTS_E, Items.SAPPHIRE_DRAGON_BOLTS);
	}

	@Override
	public double getMaxHitBoost(Player attacker, Entity defender) {
		return 0;
	}

}