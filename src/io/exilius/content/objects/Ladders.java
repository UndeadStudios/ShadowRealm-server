package io.exilius.content.objects;

import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.Player;

public class Ladders {

    public static void climbLadder(final Player player, int x, int y, int h) {
        player.startAnimation(828/*method == "up" ? 828 : 827*/);
        player.getPA().removeAllWindows();
        CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                player.getPA().movePlayer(x, y, h);
                player.startAnimation(65535);
                container.stop();
            }
            public void stop() {
                //player.setStopPacket(false);
            }
        }, 2);
    }

}
