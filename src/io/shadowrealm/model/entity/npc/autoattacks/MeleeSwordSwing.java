package io.shadowrealm.model.entity.npc.autoattacks;

import io.shadowrealm.content.combat.npc.NPCAutoAttackBuilder;
import io.shadowrealm.model.Animation;
import io.shadowrealm.model.CombatType;

public class MeleeSwordSwing extends NPCAutoAttackBuilder {

    public MeleeSwordSwing(int maxHit) {
        setAttackDelay(4);
        setMaxHit(maxHit);
        setAnimation(new Animation(451));
        setCombatType(CombatType.MELEE);
    }
}
