package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.content.skills.construction.House;
import io.exilius.model.entity.player.Player;

public class Poh extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        House house = new House(c);
        house.enter(c);
       // c.sendMessage("This command is disabled.");

    }

}
