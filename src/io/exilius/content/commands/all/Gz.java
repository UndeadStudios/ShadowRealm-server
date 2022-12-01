package io.exilius.content.commands.all;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

public class Gz extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        if (player.getPosition().inWild()) {
            player.sendMessage("You can only use this command outside the wilderness.");
            return;
        }
        player.getPA().startTeleport(2129, 9489, 0, "modern", false);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Takes you to the gambling zone.");
    }
}
