package io.exilius.content.commands.punishment.impl;

import io.exilius.content.commands.punishment.OnlinePlayerPCP;
import io.exilius.model.entity.player.Player;
import io.exilius.util.dateandtime.TimeSpan;
import io.exilius.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class GambleBan extends OnlinePlayerPCP {

    @Override
    public String name() {
        return "gambleban";
    }

    @Override
    public boolean requiresDuration() {
        return false;
    }

    @SneakyThrows
    @Override
    public void add(Player staff, Player player, TimeSpan duration) {
        player.setGambleBanned(true);
        player.sendMessage("@red@You've been banned from gambling.");
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle("[Tha Punisher]");
        db.setDescription(staff.getDisplayName()+ " gamble banned "+ player.getDisplayNameFormatted() + " for the time of " + duration);
        db.setImage("https://media.tenor.com/vkDCjozbDksAAAAC/ban-hammer-cinzou.gif");
        db.setColor(new java.awt.Color(0xB00D03));
       Discord.getJDA2().getTextChannelById("1064970616672891012").sendMessageEmbeds(db.build()).queue();
        staff.sendMessage(player.getDisplayNameFormatted() + " banned from gambling.");
    }

    @Override
    public void remove(Player staff, Player player) {
        player.setGambleBanned(false);
        player.sendMessage("@red@You've been unbanned from gambling.");
        staff.sendMessage(player.getDisplayNameFormatted() + " unbanned from gambling.");
    }
}
