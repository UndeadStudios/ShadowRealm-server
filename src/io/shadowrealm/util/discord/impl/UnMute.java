package io.shadowrealm.util.discord.impl;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.save.PlayerAddresses;
import io.shadowrealm.punishments.PunishmentType;
import io.shadowrealm.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UnMute extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("-");
        if (string == null || string.length != 2) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String name = string[1];

        Player p = PlayerHandler.getPlayerByDisplayName(name);
        if (p != null) {
            p.setHelpCcMuted(false);
            p.muteEnd = 0;
            PlayerAddresses addresses = p.getValidAddresses();
            Server.getPunishments().remove(PunishmentType.NET_MUTE, addresses.getIp());
            if (addresses.getMac() != null)
                Server.getPunishments().remove(PunishmentType.NET_MUTE, addresses.getMac());
            if (addresses.getUUID() != null && p.getUUID().length() > 0)
                Server.getPunishments().remove(PunishmentType.NET_MUTE, addresses.getUUID());
            Discord.writepunishments("[Mute-log] " + p.getDisplayName() + " has been unmuted by " + user.getName());
            p.sendMessage("You have been unmuted by " + user.getName());
        } else {
            Discord.writepunishments("[Mute-log] Well it's come to my attention that either they don't exist or you have a serious spelling issue, you fucktard.");
        }
    }

}
