package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.net.login.RS2LoginProtocol;

public class Addresswhitelist extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        RS2LoginProtocol.ADDRESS_WHITELIST.add(input);
        player.sendMessage("Add character to address whitelist: " + input);
    }
}
