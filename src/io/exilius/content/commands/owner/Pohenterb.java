package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.content.skills.construction.House;
import io.exilius.model.entity.player.Player;

public class Pohenterb extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        if (c.getHouse() == null) {
            c.sendMessage("You do not have a house loaded.");
            return;
        }

        House house = c.getHouse();
        house.setBuildMode(true);
        house.enter(c);
      //  c.sendMessage("This command is disabled.");

    }
}
