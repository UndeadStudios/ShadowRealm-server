package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.npc.drops.DropManager;
import io.exilius.model.entity.player.Player;

import static io.exilius.model.entity.npc.drops.DropManager.getModifier1;

public class Droprate extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        player.forcedChat("My drop rate bonus is : " + getModifier1(player) + "%.");
    }
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Shows drop rate bonus");
	}
}


