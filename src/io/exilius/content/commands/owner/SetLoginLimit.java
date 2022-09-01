package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.net.login.LoginRequestLimit;

public class SetLoginLimit extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        try {
            int set = Integer.parseInt(input);
            LoginRequestLimit.MAX_LOGINS_PER_TICK = set;
            player.sendMessage("Set max login attempts per tick to " + set + ".");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            player.sendMessage("Invalid usage, use ::setloginlimit amount");
        }
    }
}
