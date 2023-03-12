package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class DragonPickaxe extends Special {
	public DragonPickaxe() {
		super(10.0, 1.0, 1.0, new int[] { 11920 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.forcedChat("Smashing!");
		player.startAnimation(7138);
		player.playerLevel[Skill.MINING.getId()] = player.getLevelForXP(player.playerXP[Skill.MINING.getId()]) + 3;
		player.getPA().refreshSkill(Skill.MINING.getId());
	}


	@Override
	public void hit(Player player, Entity target, Damage damage) {

	}
}
