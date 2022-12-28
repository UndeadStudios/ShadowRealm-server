package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class sailtest extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");
        c.getPlayerAssistant().showInterface(3281);
        c.getPlayerAssistant().sendConfig(75, Integer.parseInt(args[0]));
    }
}
