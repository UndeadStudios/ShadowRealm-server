package io.exilius.content.bosses.grotesqueguardians;

import io.exilius.content.combat.npc.NPCAutoAttack;
import io.exilius.content.combat.npc.NPCAutoAttackBuilder;
import io.exilius.content.combat.npc.NPCCombatAttack;
import io.exilius.model.Animation;
import io.exilius.model.CombatType;

import java.util.function.Function;

public class DawnMelee implements Function<GrotesqueGuardianNpc, NPCAutoAttack> {

    @Override
    public NPCAutoAttack apply(GrotesqueGuardianNpc nightmare) {
        return new NPCAutoAttackBuilder()
                .setAnimation(new Animation(7769))
                .setCombatType(CombatType.MELEE)
                .setMaxHit(15)
                .setHitDelay(2)
                .setAttackDelay(6)
                .setDistanceRequiredForAttack(1)
                .setSelectAutoAttack(new Function<NPCCombatAttack, Boolean>() {
                    @Override
                    public Boolean apply(NPCCombatAttack npcCombatAttack) {
                        return npcCombatAttack.getNpc().distance(npcCombatAttack.getVictim().getPosition()) <= 1;
                    }
                })
                .setPrayerProtectionPercentage(new Function<NPCCombatAttack, Double>() {
                    @Override
                    public Double apply(NPCCombatAttack npcCombatAttack) {
                        return 0.2d;
                    }
                })
                .createNPCAutoAttack();
    }
}
