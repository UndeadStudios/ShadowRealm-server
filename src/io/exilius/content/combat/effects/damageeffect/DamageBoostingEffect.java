package io.exilius.content.combat.effects.damageeffect;

import io.exilius.model.entity.Entity;
import io.exilius.model.entity.player.Player;

public interface DamageBoostingEffect extends DamageEffect {

    double getMaxHitBoost(Player attacker, Entity defender);

}
