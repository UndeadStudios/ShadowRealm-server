package io.shadowrealm.content.combat.effects.damageeffect.impl.bolts;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class RubydragonBoltSpecial implements DamageBoostingEffect {

    @Override
    public void execute(Player attacker, Player defender, Damage damage) {

    }

    @Override
    public void execute(Player attacker, NPC defender, Damage damage) {
        if (defender.getDefinition().getName() == null) {
            return;
        }
    }

    @Override
    public boolean isExecutable(Player p) {
        if (p.playerEquipment[Player.playerArrows] != 21944) {
            return false;
        }
        double chance = Math.random();
        if (p.npcAttackingIndex > 0) {
            if (chance <= 0.206)
                p.rubyBoltSpecial = true;
        }
        if (p.playerAttackingIndex > 0) {
            if (chance <= 0.281)
                p.rubyBoltSpecial = true;
        }


        return p.rubyBoltSpecial;
    }

    @Override
    public double getMaxHitBoost(Player attacker, Entity defender) {
        return 0;
    }

}
