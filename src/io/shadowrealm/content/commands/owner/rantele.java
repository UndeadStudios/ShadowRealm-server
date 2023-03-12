package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.util.Misc;

import java.util.Optional;

public class rantele extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
        Player c2 = null;
        if (optionalPlayer.isPresent()) {
            c2 = optionalPlayer.get();
            if (!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
//                if (c2.inClanWars() || c2.inClanWarsSafe()) {
//                    c.sendMessage("@cr10@This player is currently at the pk district.");
                return;
            }
        }
        Player finalC = c2;
        CycleEventHandler.getSingleton().addEvent(c2, new CycleEvent() {
            int time = 0;
            @Override
            public void execute(CycleEventContainer container) {
                finalC.getPA().movePlayer(3000+ Misc.random(1500), 3000+ Misc.random(1500), 0);
            }
            public void stop() {
            }
        }, 6);
    }
}