package io.shadowrealm.content.commands.test;

import java.io.IOException;
import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class EquipmentSetup extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        try {
            io.shadowrealm.model.EquipmentSetup.equip(player, input);
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Could not equip equipment setup.");
        }
    }

    public Optional<String> getDescription() {
        return Optional.of("Spawn a specific equipment setup.");
    }
}
