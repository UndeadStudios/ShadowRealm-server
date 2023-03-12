package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.skills.construction.House;
import io.shadowrealm.model.entity.player.Player;

public class Poh extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        House house = new House(c);
        house.enter(c);
       // c.sendMessage("This command is disabled.");

    }

}
