package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Getframeids extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        for(int i = 0; i < 40000; i++)
        {
            c.getPA().sendFrame126(""+i, i);
        }
    }
}
