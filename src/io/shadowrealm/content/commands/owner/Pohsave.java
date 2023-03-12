package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Pohsave extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        if (c.getHouse() != null)
            c.getHouse().save();
       // c.sendMessage("This command is disabled.");
    }
}
