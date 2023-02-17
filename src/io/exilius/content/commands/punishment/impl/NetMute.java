package io.exilius.content.commands.punishment.impl;

import io.exilius.Server;
import io.exilius.content.commands.punishment.OnlinePlayerPCP;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.save.PlayerAddresses;
import io.exilius.punishments.PunishmentType;
import io.exilius.util.dateandtime.TimeSpan;
import io.exilius.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class NetMute extends OnlinePlayerPCP {
    @Override
    public String name() {
        return "netmute";
    }

    @Override
    public boolean requiresDuration() {
        return true;
    }

    @SneakyThrows
    @Override
    public void add(Player staff, Player player, TimeSpan duration) {
        PlayerAddresses addresses = player.getValidAddresses();
        Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getIp());
        if (addresses.getMac() != null)
            Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getMac());
        if (addresses.getUUID() != null)
            Server.getPunishments().add(PunishmentType.NET_MUTE, duration, addresses.getUUID());
        staff.sendMessage("Muted all '{}' addresses for {}.", player.getDisplayNameFormatted(), duration);
        player.sendMessage("You've been muted by {} for {}.", staff.getDisplayNameFormatted(), duration.toString());
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle("[Tha Punisher]");
        db.setDescription(staff.getDisplayName()+ " Muted all addresses for "+ player.getDisplayNameFormatted() + " for the time of " + duration);
        db.setImage("https://media.tenor.com/vkDCjozbDksAAAAC/ban-hammer-cinzou.gif");
        db.setColor(new java.awt.Color(0xB00D03));
       Discord.jda.getTextChannelById("1064970616672891012").sendMessageEmbeds(db.build()).queue();

        //Discord.writepunishments(staff.getDisplayName()+ " Muted all addresses for `" + player.getDisplayNameFormatted() + "` for the time of " + duration);

    }

    @Override
    public void remove(Player staff, Player player) {
        PlayerAddresses addresses = player.getValidAddresses();
        Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getIp());
        if (addresses.getMac() != null)
            Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getMac());
        else
            staff.sendMessage("No valid mac to unmute.");
        if (addresses.getUUID() != null && player.getUUID().length() > 0)
            Server.getPunishments().removeWithMessage(staff, PunishmentType.NET_MUTE, addresses.getUUID());
        else
            staff.sendMessage("No valid uuid to unmute.");
    }
}
