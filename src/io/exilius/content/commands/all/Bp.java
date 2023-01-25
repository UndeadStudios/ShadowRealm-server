package io.exilius.content.commands.all;

import io.exilius.content.battle_pass.BattlePassInterface;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

/**
 * Show the current position.
 * 
 * @author Noah
 *
 */
public class Bp extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		//BattlePassInterface.Companion.open(player);
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Open the battle pass.");
	}
}
