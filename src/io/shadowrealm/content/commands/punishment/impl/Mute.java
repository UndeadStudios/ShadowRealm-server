package io.shadowrealm.content.commands.punishment.impl;

import io.shadowrealm.content.commands.punishment.OnlinePlayerPunishmentPCP;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.punishments.PunishmentType;
import io.shadowrealm.util.dateandtime.TimeSpan;
import io.shadowrealm.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class Mute extends OnlinePlayerPunishmentPCP {

    @Override
    public String name() {
        return "mute";
    }

    @Override
    public PunishmentType getPunishmentType() {
        return PunishmentType.MUTE;
    }

    @SneakyThrows
    @Override
    public void onPunishment(Player staff, Player player, TimeSpan duration) {
        player.muteEnd = System.currentTimeMillis() + duration.toMillis();
        player.sendMessage("@red@You have been muted by {} for {}.", staff.getDisplayNameFormatted(), duration.toString());
       // Discord.writepunishments(staff.getDisplayName()+ " Muted `"+ player.getDisplayNameFormatted() + "` for the time of " + duration);
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle("[Tha Punisher]");
        db.setDescription(staff.getDisplayName()+ " Muted "+ player.getDisplayNameFormatted() + " for the time of " + duration);
        db.setImage("https://media.tenor.com/vkDCjozbDksAAAAC/ban-hammer-cinzou.gif");
        db.setColor(new java.awt.Color(0xB00D03));
       Discord.getJDA().getTextChannelById("1064970616672891012").sendMessageEmbeds(db.build()).queue();

    }

    @Override
    public void onRemovePunishment(Player staff, Player player) {
        player.muteEnd = 0;
    }

    @Override
    public String extract(Player player) {
        return player.getDisplayNameLower();
    }
}
