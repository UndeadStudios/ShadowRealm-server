package io.exilius.content.commands.all;

import io.exilius.content.commands.Command;
import io.exilius.content.dailytasks.DailyTaskInterface;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

public class DT extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        System.out.println("User used the DT command");
        DailyTaskInterface.Companion.open(player);
        player.sendMessage("@blu@You have " + player.dailyTaskPoints + " daily task points.");
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Opens the Daily Task interface.");
    }

}
