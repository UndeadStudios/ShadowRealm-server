package io.exilius.content.commands.test;

import java.io.IOException;
import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class EquipmentSetup extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        try {
            io.exilius.model.EquipmentSetup.equip(player, input);
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Could not equip equipment setup.");
        }
    }

    public Optional<String> getDescription() {
        return Optional.of("Spawn a specific equipment setup.");
    }
}
