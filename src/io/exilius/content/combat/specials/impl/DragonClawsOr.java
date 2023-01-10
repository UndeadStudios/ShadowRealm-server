package io.exilius.content.combat.specials.impl;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.Hitmark;
import io.exilius.content.combat.formula.rework.MeleeCombatFormula;
import io.exilius.content.combat.specials.Special;
import io.exilius.content.skills.Skill;
import io.exilius.model.CombatType;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerAssistant;
import io.exilius.util.Misc;

public class DragonClawsOr extends Special {

	public DragonClawsOr() {
		super(5.0, 2.0, 1.0, new int[] { 26708 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(7527);
		player.gfx0(1171);

		int max = MeleeCombatFormula.get().getMaxHit(player, target, 1.0, 1.0);

		if (damage.getAmount() == 0) {
			int second = Misc.random(0, max);
			if (second == 0) {
				doHit(player, target, 0, 0);
				doHit(player, target, (int) (max * 0.75d), 1);
				doHit(player, target, (int) (max * 0.75d), 1);
			} else {
				doHit(player, target, second, 0);
				doHit(player, target, second / 2, 1);
				doHit(player, target, second / 2, 1);
			}
		} else {
			int halvedHit = damage.getAmount() == 0 ? 0 : damage.getAmount() / 2;
			int finalHit = halvedHit == 0 ? 0 : halvedHit / 2;
			doHit(player, target, halvedHit, 0);
			doHit(player, target, finalHit, 1);
			doHit(player, target, finalHit, 1);
		}
	}

	private void doHit(Player player, Entity target, int damage, int delay) {
		player.getDamageQueue().add(new Damage(target, damage, player.hitDelay + delay, player.playerEquipment, damage > 0 ? Hitmark.HIT : Hitmark.MISS, CombatType.MELEE));
		player.getPA().addXpDrop(new PlayerAssistant.XpDrop(damage, Skill.ATTACK.getId()));
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}