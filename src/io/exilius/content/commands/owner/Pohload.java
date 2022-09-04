package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.content.skills.construction.House;
import io.exilius.model.entity.player.Player;

public class Pohload extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        player.setHouse(House.load(player));
    }
}
