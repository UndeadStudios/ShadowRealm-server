package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.range.RangeData;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class ToxicBlowpipe extends Special {

	public ToxicBlowpipe() {
		super(5.0, 2.0, 1.5, new int[] { 12926 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(5061);
		if (!player.usingSpecial) {
			player.attacking.fireRangeProjectile(target);
		}
		if (target instanceof NPC && player.npcAttackingIndex > 0) {
			RangeData.fireProjectileNpc(player, (NPC) target, 50, 70, 1043, 28, 28, 37, 10);
		} else if (target instanceof Player && player.playerAttackingIndex > 0) {
			RangeData.fireProjectilePlayer(player, (Player) target, 50, 70, 1043, 28, 31, 37, 10);
		}
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (damage.getAmount() > 0) {
			player.getHealth().increase(damage.getAmount() / 2);
		}
	}

}
