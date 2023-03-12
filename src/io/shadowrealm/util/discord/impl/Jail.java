package io.shadowrealm.util.discord.impl;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.util.dateandtime.TimeSpan;
import io.shadowrealm.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Jail extends ListenerAdapter {

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
            if (Server.getMultiplayerSessionListener().inAnySession(p)) {
                Discord.writepunishments("[JAIL] " +"The player is in a trade, or duel. You cannot do this at this time.");
                return;
            }
            TimeSpan timeSpan = new TimeSpan(TimeUnit.DAYS, TimeUnit.DAYS.toMinutes(365 * 5));

            p.setTeleportToX(2086);
            p.setTeleportToY(4466);
            p.heightLevel = 0;
            p.jailEnd = (System.currentTimeMillis() + TimeUnit.DAYS.toMillis(365 * 5));
            p.sendMessage("@red@You have been jailed by " + user.getName() + " for a duration of " + timeSpan.toString());
            Discord.writepunishments("[JAIL] " + user.getName() + " has jailed " + p.getLoginName() + "/" + p.getDisplayName() + " for a duration of " + timeSpan.toString());

        } else {
            Discord.writepunishments("[JAIL] " + name + " has clearly fucking logged out, or you're an idiot and can't spell for shit, I'm telling Ark you fucking idiot.");
        }
    }

}
