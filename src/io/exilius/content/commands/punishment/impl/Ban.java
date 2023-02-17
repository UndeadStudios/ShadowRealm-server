package io.exilius.content.commands.punishment.impl;

import io.exilius.content.commands.punishment.OnlinePlayerPunishmentPCP;
import io.exilius.model.entity.player.Player;
import io.exilius.punishments.PunishmentType;
import io.exilius.util.dateandtime.TimeSpan;
import io.exilius.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class Ban extends OnlinePlayerPunishmentPCP {

    @Override
    public String name() {
        return "ban";
    }

    @Override
    public PunishmentType getPunishmentType() {
        return PunishmentType.BAN;
    }

    @SneakyThrows
    @Override
    public void onPunishment(Player staff, Player player, TimeSpan duration) {
        player.forceLogout();
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle("[Tha Punisher]");
        db.setDescription(staff.getDisplayName()+ " Banned "+ player.getDisplayNameFormatted() + " for the time of " + duration);
        db.setImage("https://media.tenor.com/vkDCjozbDksAAAAC/ban-hammer-cinzou.gif");
        db.setColor(new java.awt.Color(0xB00D03));
       Discord.getJDA2().getTextChannelById("1064970616672891012").sendMessageEmbeds(db.build()).queue();
        //Discord.writepunishments(staff.getDisplayName() + " Banned `" + player.getDisplayNameFormatted() + "`for the time of " + duration);
    }

    @Override
    public void onRemovePunishment(Player staff, Player player) { }

    @Override
    public String extract(Player player) {
        return player.getDisplayNameLower();
    }
}
