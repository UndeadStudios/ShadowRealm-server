package io.exilius.content.commands.owner;


import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Walkto extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        c.getPA().walkTo(x, y);
    }
}