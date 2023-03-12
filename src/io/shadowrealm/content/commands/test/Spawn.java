package io.shadowrealm.content.commands.test;

import java.util.Optional;

import io.shadowrealm.content.ItemSpawner;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Spawn extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        ItemSpawner.open(player);
    }

    public Optional<String> getDescription() {
        return Optional.of("Opens an interface to spawn items.");
    }
}
