package io.exilius.util.discord.impl;

import io.exilius.Server;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.save.PlayerAddresses;
import io.exilius.punishments.PunishmentType;
import io.exilius.util.dateandtime.TimeSpan;
import io.exilius.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Mute extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("-");
        if (string == null || string.length != 2) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String name = string[1];

        Player player = PlayerHandler.getPlayerByDisplayName(name);
        if (player != null) {
            TimeSpan timeSpan = new TimeSpan(TimeUnit.DAYS, 1);
            PlayerAddresses addresses = player.getValidAddresses();
            Server.getPunishments().add(PunishmentType.NET_MUTE, timeSpan, addresses.getIp());
            if (addresses.getMac() != null)
                Server.getPunishments().add(PunishmentType.NET_MUTE, timeSpan, addresses.getMac());
            if (addresses.getUUID() != null)
                Server.getPunishments().add(PunishmentType.NET_MUTE, timeSpan, addresses.getUUID());

            player.sendMessage(player.getDisplayName() + " You have been muted by : " + user.getName());

            Discord.writepunishments("[Mute-Log] " + user.getName() + " Muted " + player.getDisplayName());
        } else {
            Discord.writepunishments("[Mute-Log] Oh you're a special one aren't you, Either they don't exist or they're offline.");
        }
    }

}

