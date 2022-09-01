package io.exilius.content.commands.test;

import java.util.Optional;

import io.exilius.content.ItemSpawner;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Spawn extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        ItemSpawner.open(player);
    }

    public Optional<String> getDescription() {
        return Optional.of("Opens an interface to spawn items.");
    }
}
