package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.skills.construction.House;
import io.shadowrealm.model.entity.player.Player;

public class Pohload extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        //c.sendMessage("This command is disabled.");
        c.setHouse(House.load(c));
    }
}
