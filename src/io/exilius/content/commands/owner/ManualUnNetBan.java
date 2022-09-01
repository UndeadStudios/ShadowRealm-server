package io.exilius.content.commands.owner;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.punishments.PunishmentType;

public class ManualUnNetBan extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        Server.getPunishments().removeWithMessage(player, PunishmentType.NET_BAN, input);
        Server.getPunishments().removeWithMessage(player, PunishmentType.MAC_BAN, input);
    }
}
