package io.exilius.content.commands.punishment.impl;

import io.exilius.content.commands.punishment.OnlinePlayerPunishmentPCP;
import io.exilius.model.entity.player.Player;
import io.exilius.punishments.PunishmentType;
import io.exilius.util.dateandtime.TimeSpan;
import io.exilius.util.discord.Discord;

public class Ban extends OnlinePlayerPunishmentPCP {

    @Override
    public String name() {
        return "ban";
    }

    @Override
    public PunishmentType getPunishmentType() {
        return PunishmentType.BAN;
    }

    @Override
    public void onPunishment(Player staff, Player player, TimeSpan duration) {
        player.forceLogout();
        Discord.writepunishments(staff.getDisplayName() + " Banned `" + player.getDisplayNameFormatted() + "`for the time of " + duration);
    }

    @Override
    public void onRemovePunishment(Player staff, Player player) { }

    @Override
    public String extract(Player player) {
        return player.getDisplayNameLower();
    }
}
