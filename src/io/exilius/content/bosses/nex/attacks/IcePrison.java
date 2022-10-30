package io.exilius.content.bosses.nex.attacks;

import io.exilius.Server;
import io.exilius.content.combat.Hitmark;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;

import java.util.List;

public class IcePrison {
    public IcePrison(List<Player> targets) {
        IcePrison(targets);
    }

    void IcePrison(List<Player> targets) {
        Player target = targets.get(Misc.random(targets.size() - 1));
        Boundary iceBounds = new Boundary(target.getX() - 1, target.absY - 2, target.getX() + 2, target.getY() + 1);

        for(int y = -3; y < 4; y++) {
            Server.getGlobalObjects().add(new GlobalObject(42943, target.absX + -3, target.absY + y, target.getHeight(), 0, 10, 6));
            Server.getGlobalObjects().add(new GlobalObject(42943, target.absX + 3, target.absY + y, target.getHeight(), 0, 10, 6));
            Server.getGlobalObjects().add(new GlobalObject(42943, target.absX + y, target.absY + -3, target.getHeight(), 0, 10, 6));
            Server.getGlobalObjects().add(new GlobalObject(42943, target.absX + y, target.absY + 3, target.getHeight(), 0, 10, 6));

        }

        CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                if(container.getTotalTicks() > 2) {
                    for (Player player :
                            targets) {
                        if (iceBounds.in(player)) {
                            player.appendDamage(Misc.random(20, 75), Hitmark.HIT);
                        }
                    }
                    container.stop();
                }
            }
        }, 1);

    }
}