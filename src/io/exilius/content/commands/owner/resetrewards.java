package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class resetrewards extends Command {

    public void execute(Player c, String commandName, String input) {
        c.getDailyRewards().setStreak(0);
    }
}
