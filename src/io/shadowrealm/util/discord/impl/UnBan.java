package io.shadowrealm.util.discord.impl;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.save.PlayerAddresses;
import io.shadowrealm.model.entity.player.save.PlayerSaveOffline;
import io.shadowrealm.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

import static io.shadowrealm.punishments.PunishmentType.*;

public class UnBan extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("-");
        if (string == null || string.length != 2) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String name = string[1];

        Server.getIoExecutorService().submit(() -> {
            try {
                File file = PlayerSaveOffline.getCharacterFile(name);
                if (file == null) {
                    Discord.writepunishments("[UnBan-log] No character file with name " + name);
                    return;
                }

                if (!Server.getPunishments().remove(BAN, name)) {
                    Discord.writepunishments("[UnBan-log] "+name+" isn't banned, what the fuck are you doing?.");
                } else {
                    Discord.writepunishments("[UnBan-log] " + user.getName() + " has unbanned " + name);
                }

                PlayerAddresses addresses = PlayerSaveOffline.getAddresses(file);
                PlayerHandler.addQueuedAction(() -> {
                    Server.getPunishments().remove(NET_BAN, addresses.getIp());
                    Server.getPunishments().remove(MAC_BAN, addresses.getMac());
                    Server.getPunishments().remove(MAC_BAN, addresses.getUUID());
                });
            } catch (Exception ez) {
                ez.printStackTrace();

            }
        });

    }

}
