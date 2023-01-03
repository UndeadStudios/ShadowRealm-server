package io.exilius.content.commands.all;

import io.exilius.content.combat.stats.MonsterKillLog;
import io.exilius.content.commands.Command;
import io.exilius.content.dailytasks.DailyTaskInterface;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

public class DT extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        DailyTaskInterface.Companion.open(player);
        player.sendMessage("@blu@You have " + player.dailyTaskPoints + " daily task points.");
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Opens the Daily Task interface.");
    }

}
