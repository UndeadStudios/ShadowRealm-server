package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Testlevelup extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        int levelid = Integer.parseInt(input.split("-")[0]);
        player.getPA().levelUp(levelid);
    }
}
