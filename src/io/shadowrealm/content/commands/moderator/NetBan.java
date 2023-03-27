package io.shadowrealm.content.commands.moderator;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.commands.punishment.PunishmentCommand;
import io.shadowrealm.model.entity.player.Player;

import java.util.Optional;

public class NetBan extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        if(player.getRights().getPrimary().isOwner()){
            player.sendMessage("ERROR: Can't netban this person.");
            return;
        }

        new PunishmentCommand(commandName, input).parse(player);
    }

    public Optional<String> getDescription() {
        return Optional.of("Ban all known addresses of an online player.");
    }

    @Override
    public String getFormat() {
        return PunishmentCommand.getFormat(getCommand());
    }
}
