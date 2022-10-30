package io.exilius.content.bosses.nex.attacks;

import io.exilius.content.combat.Hitmark;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

import java.util.List;

public class Wrath {
    public Wrath(NPC npc, List<Player> targets) {
        Wrath(npc, targets);
    }

    void Wrath(NPC npc, List<Player> targets) {
        CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                if (container.getTotalTicks() > 5) {
                    for (Player player : targets) {
                        if(player.getPosition().getAbsDistance(npc.getPosition()) < 5) {
                            player.appendDamage(Misc.random(10, 40), Hitmark.HIT);
                        }
                    }
                    container.stop();
                }
            }
        }, 1);
    }


}
