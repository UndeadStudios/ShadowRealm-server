package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.combat.stats.MonsterKillLog;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class KillLog extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        MonsterKillLog.openInterface(player);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Opens the kill log.");
    }

}
