package io.exilius.model.entity.npc.autoattacks;

import io.exilius.content.combat.npc.NPCAutoAttackBuilder;
import io.exilius.model.Animation;
import io.exilius.model.CombatType;

public class MeleeSwordSwing extends NPCAutoAttackBuilder {

    public MeleeSwordSwing(int maxHit) {
        setAttackDelay(4);
        setMaxHit(maxHit);
        setAnimation(new Animation(451));
        setCombatType(CombatType.MELEE);
    }
}
