package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class resetrewards extends Command {

    public void execute(Player c, String commandName, String input) {
        c.getDailyRewards().setStreak(0);
    }
}
