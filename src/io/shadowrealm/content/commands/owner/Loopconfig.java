package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.player.Player;

public class Loopconfig extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");

        if (Integer.parseInt(args[0]) > 6000) {
            c.sendMessage("Max config id is: 6000");
            return;
        }

        if (args.length >= 3) {
            final int id = Integer.parseInt(args[0]);
            final int loopamount = Integer.parseInt(args[1]);
            boolean plus = args[2].equals("+");
            CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
                int amoutloop = loopamount;
                @Override
                public void execute(CycleEventContainer container) {
                    c.getPA().sendConfig(id, loopamount);
                    c.sendMessage("Performing config: " + id+ " number:"+amoutloop);
                    if (plus) {
                        amoutloop++;
                    } else {
                        amoutloop--;
                    }
                }
            }, 3);

        } else {
            c.sendMessage("Incorrect.");
        }
    }
}