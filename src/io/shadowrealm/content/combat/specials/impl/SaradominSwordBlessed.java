package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.combat.formula.MagicMaxHit;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerAssistant;
import io.shadowrealm.util.Misc;

public class SaradominSwordBlessed extends Special {

	public SaradominSwordBlessed() {
		super(6.5, 1.0, 1.25, new int[] { 12809 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(1133);
		if (damage.getAmount() > 0) {
			int damage2 = MagicMaxHit.magiMaxHit(player) + (1 + Misc.random(15));
			player.getDamageQueue().add(new Damage(target, damage2, 2, player.playerEquipment, Hitmark.HIT, CombatType.MAGE));
			player.getPA().addXpDrop(new PlayerAssistant.XpDrop(damage2, Skill.ATTACK.getId()));
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}

}
