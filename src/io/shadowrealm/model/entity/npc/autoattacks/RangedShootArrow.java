package io.shadowrealm.model.entity.npc.autoattacks;

import io.shadowrealm.content.combat.npc.NPCAutoAttackBuilder;
import io.shadowrealm.model.Animation;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.Graphic;
import io.shadowrealm.model.ProjectileBaseBuilder;

public class RangedShootArrow extends NPCAutoAttackBuilder {

    public RangedShootArrow(int maxHit) {
        setAttackDelay(3);
        setMaxHit(maxHit);
        setAnimation(new Animation(426));
        setCombatType(CombatType.RANGE);
        setDistanceRequiredForAttack(10);
        setHitDelay(3);
        setStartGraphic(new Graphic(19, Graphic.GraphicHeight.MIDDLE));
        setProjectile(new ProjectileBaseBuilder().setSendDelay(2).setProjectileId(11).createProjectileBase());
    }
}
