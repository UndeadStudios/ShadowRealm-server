package io.shadowrealm.content.bosses.sarachnis;

import io.shadowrealm.content.combat.npc.NPCAutoAttack;
import io.shadowrealm.content.combat.npc.NPCAutoAttackBuilder;
import io.shadowrealm.content.combat.npc.NPCCombatAttack;
import io.shadowrealm.model.*;

import java.util.function.Function;

public class SarachnisMinionMage implements Function<SarachnisNpc, NPCAutoAttack> {

    private static ProjectileBase projectile() {
        return new ProjectileBaseBuilder()
                .setSendDelay(2)
                .setSpeed(25)
                .setStartHeight(40)
                .setProjectileId(1682)
                .createProjectileBase();
    }

    @Override
    public NPCAutoAttack apply(SarachnisNpc nightmare) {
        return new NPCAutoAttackBuilder()
                .setSelectPlayersForMultiAttack(NPCAutoAttack.getDefaultSelectPlayersForAttack())
                .setAnimation(new Animation(4410))
                .setCombatType(CombatType.MAGE)
                .setMaxHit(11)
                .setHitDelay(3)
                .setAttackDelay(4)
                .setDistanceRequiredForAttack(24)
                .setMultiAttack(false)
                .setPrayerProtectionPercentage(new Function<NPCCombatAttack, Double>() {
                    @Override
                    public Double apply(NPCCombatAttack npcCombatAttack) {
                        return 0.3;
                    }
                })
                .setProjectile(projectile())
                .createNPCAutoAttack();
    }
}
