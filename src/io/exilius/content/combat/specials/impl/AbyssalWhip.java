package io.exilius.content.combat.specials.impl;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.specials.Special;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;

public class AbyssalWhip extends Special {

	public AbyssalWhip() {
		super(5.0, 1.25, 1.0, new int[] { 4151, 12773, 12774, 28213, 28214, 28215, 28216,28217, 28219, 28220 });
	}

	@Override
	public void activate(Player player, Entity target, Damage damage) {
		player.startAnimation(1658);
	}

	@Override
	public void hit(Player player, Entity target, Damage damage) {
		if (target instanceof NPC) {
			((NPC) target).gfx100(341);
		} else if (target instanceof Player) {
			((Player) target).gfx100(341);
			
			if (damage.getAmount() > 0) {
				if (((Player) target).getRunEnergy() > 0) {
					((Player) target).setRunEnergy(((Player) target).getRunEnergy() - 10, true);
				}
				if (!(player.getRunEnergy() > 89)) {
					player.setRunEnergy(player.getRunEnergy() + 10, true);
				}
			}
		}
	}

}
