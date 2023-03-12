package io.shadowrealm.content.bosses.nex.attacks;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.model.entity.EntityReference;
import io.shadowrealm.model.entity.player.ClientGameTimer;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IceBarrage {
    public IceBarrage(Player player, List<Player> players) {
        IceBarrage(player, players);
    }

    void IceBarrage(Player player, List<Player> players) {
        for (Player possibleTargets:
                players) {
            if(player.getPosition().getAbsDistance(possibleTargets.getPosition()) <= 3) {
                possibleTargets.gfx0(369);
                int dam;
                if(possibleTargets.protectingMagic())
                    dam = Misc.random(15);
                else
                    dam = Misc.random(33);
                possibleTargets.appendDamage(dam, (dam > 0 ? Hitmark.HIT : Hitmark.MISS));
                if (possibleTargets.isFreezable()) {
                    int delay = Misc.random(15,30);
                    possibleTargets.frozenBy = EntityReference.getReference(player);
                    possibleTargets.freezeDelay = delay;
                    possibleTargets.freezeTimer = delay;
                    possibleTargets.resetWalkingQueue();
                    possibleTargets.sendMessage("You have been frozen.");
                    possibleTargets.getPA().sendGameTimer(ClientGameTimer.FREEZE, TimeUnit.MILLISECONDS, 600 * delay);
                }
            }

        }
    }
}
