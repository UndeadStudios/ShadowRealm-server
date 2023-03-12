package io.shadowrealm.content.combat.core;


import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.melee.MeleeExtras;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public class HitExecutorPlayer extends HitExecutor {

    public HitExecutorPlayer(Player c, Entity defender, Damage damage) {
        super(c, defender, damage);
    }

    @Override
    public void onHit() {
        Player o = defender.asPlayer();
        o.addDamageTaken(attacker, damage.getAmount());
        o.logoutDelay = System.currentTimeMillis();
        o.underAttackByPlayer = attacker.getIndex();
        o.killerId = attacker.getIndex();
        o.singleCombatDelay = System.currentTimeMillis();
        /**
         * Upon receiving damage
         */
        MeleeExtras.applyOnHit(attacker, o, damage);
    }
}
