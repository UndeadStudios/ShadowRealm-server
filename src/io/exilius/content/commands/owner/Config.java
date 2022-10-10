package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Config extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");
        int configID = Integer.parseInt(args[0]);
        int configstate = Integer.parseInt(args[1]);
        c.getPA().sendConfig(configID, configstate);
    }
}
