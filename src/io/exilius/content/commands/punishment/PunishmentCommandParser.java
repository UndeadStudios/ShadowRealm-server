package io.exilius.content.commands.punishment;

import io.exilius.model.entity.player.Player;

public interface PunishmentCommandParser {

    String name();

    void add(Player staff, PunishmentCommandArgs args);

    void remove(Player staff, PunishmentCommandArgs args);

    String getFormat(String commandName);
}
