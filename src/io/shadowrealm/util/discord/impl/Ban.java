package io.shadowrealm.util.discord.impl;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.save.PlayerAddresses;
import io.shadowrealm.model.multiplayersession.MultiplayerSession;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.shadowrealm.punishments.Punishment;
import io.shadowrealm.punishments.PunishmentType;
import io.shadowrealm.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class Ban extends ListenerAdapter {

    public void onGuildMessageReceived(MessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("-");
        if (string == null || string.length != 2) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String name = string[1];

        Player player = PlayerHandler.getPlayerByDisplayName(name);
        if (player != null) {
            PlayerAddresses addresses = player.getValidAddresses();

            Server.getPunishments().add(new Punishment(PunishmentType.NET_BAN, Long.MAX_VALUE, addresses.getIp()));

            player.sendMessage(player.getDisplayName() + " You have been Banned by : " + user.getName());
            Discord.writepunishments("[Ban-Log] " + user.getName() + " Banned " + player.getDisplayName());

            List<Player> clientList = PlayerHandler.nonNullStream().filter(p -> p.connectedFrom.equals(addresses.getIp())).collect(Collectors.toList());

            for (Player pz : clientList) {
                Server.getPunishments().add(new Punishment(PunishmentType.BAN, Long.MAX_VALUE, pz.getLoginName()));
                if (Server.getMultiplayerSessionListener().inAnySession(pz)) {
                    MultiplayerSession session = Server.getMultiplayerSessionListener().getMultiplayerSession(pz);
                    session.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
                }
                pz.forceLogout();
            }
        } else {
            Discord.writepunishments("[Ban-Log] " + name + " isn't fucking online, how do you purpose we deal with this situation now??");
        }
    }

}

