package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Upgradetest extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        c.getItemUpgradeSystem().openInterface();
    }
}
