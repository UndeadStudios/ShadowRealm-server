package io.shadowrealm.content.combat.effects.damageeffect;

import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

public interface DamageBoostingEffect extends DamageEffect {

    double getMaxHitBoost(Player attacker, Entity defender);

}
