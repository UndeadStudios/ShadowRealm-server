package io.shadowrealm.content.bosses.nex.attacks;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.util.List;

public class BloodBarrage {


    public BloodBarrage(NPC npc, Player player, List<Player> players) {
        BloodBarrage(npc, player, players);
    }

    void BloodBarrage(NPC npc, Player player, List<Player> players) {

        for (Player possibleTargets: players) {
            if(player.getPosition().getAbsDistance(possibleTargets.getPosition()) <= 3) {
                player.getPA().createPlayersProjectile(npc.getX(), npc.getY(), player.getX(), player.getY(), 16, 10, 374, 43, 43, -1, 65, 3);
                possibleTargets.gfx100(377);
                int dam;
                if(possibleTargets.protectingMagic())
                    dam = Misc.random(15);
                else
                    dam = Misc.random(33);
                if (npc.getHealth().getCurrentHealth() >= 99) {
                    npc.appendHeal(dam, Hitmark.HEAL_PURPLE);
                }

                possibleTargets.appendDamage(dam, (dam > 0 ? Hitmark.HIT : Hitmark.MISS));
            }

        }
    }
}
