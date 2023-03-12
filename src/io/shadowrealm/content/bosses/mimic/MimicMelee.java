package io.shadowrealm.content.bosses.mimic;

import io.shadowrealm.content.combat.npc.NPCAutoAttack;
import io.shadowrealm.content.combat.npc.NPCAutoAttackBuilder;
import io.shadowrealm.content.combat.npc.NPCCombatAttack;
import io.shadowrealm.model.Animation;
import io.shadowrealm.model.CombatType;

import java.util.function.Function;

public class MimicMelee implements Function<MimicNpc, NPCAutoAttack> {

    @Override
    public NPCAutoAttack apply(MimicNpc nightmare) {
        return new NPCAutoAttackBuilder()
                .setAnimation(new Animation(8308))
                .setCombatType(CombatType.MELEE)
                .setMaxHit(23)
                .setHitDelay(2)
                .setAttackDelay(4)
                .setDistanceRequiredForAttack(1)
                .setPrayerProtectionPercentage(new Function<NPCCombatAttack, Double>() {
                    @Override
                    public Double apply(NPCCombatAttack npcCombatAttack) {
                        return 0.2d;
                    }
                })
                .createNPCAutoAttack();
    }
}