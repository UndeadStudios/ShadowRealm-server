package io.shadowrealm.content.bosses.sarachnis;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.combat.melee.CombatPrayer;
import io.shadowrealm.content.combat.npc.NPCAutoAttack;
import io.shadowrealm.content.combat.npc.NPCAutoAttackBuilder;
import io.shadowrealm.content.combat.npc.NPCCombatAttack;
import io.shadowrealm.content.combat.npc.NPCCombatAttackHit;
import io.shadowrealm.model.Animation;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.entity.player.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class SarachnisMelee implements Function<SarachnisNpc, NPCAutoAttack> {

    @Override
    public NPCAutoAttack apply(SarachnisNpc nightmare) {
        Consumer<NPCCombatAttackHit> onDamage = t -> {
            if (t.getCombatHit().missed())
                return;
            if (t.getVictim().isPlayer()) {
                Player player = (Player) t.getVictim();
                if (!CombatPrayer.isPrayerOn(player, CombatPrayer.PROTECT_FROM_MELEE)) {
                    t.getNpc().appendDamage(5, Hitmark.HEAL_PURPLE);
                    t.getNpc().getHealth().increase(10);
                }
            }
        };
        Consumer<NPCCombatAttack> onAttack = t -> {
            nightmare.attackCounter++;
        };
        return new NPCAutoAttackBuilder()
                .setAnimation(new Animation(8147))
                .setCombatType(CombatType.MELEE)
                .setSelectAutoAttack(attack -> attack.getNpc().distance(attack.getVictim().getPosition()) == 1)
                .setMaxHit(31)
                .setHitDelay(2)
                .setAttackDelay(4)
                .setDistanceRequiredForAttack(1)
                .setOnHit(onDamage)
                .setOnAttack(onAttack)
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