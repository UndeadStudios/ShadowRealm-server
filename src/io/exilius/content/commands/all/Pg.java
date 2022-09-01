package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.Configuration;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

/**
 * Teleport the player to the mage bank.
 * 
 * @author Emiel
 */
public class Pg extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		 c.getPA().sendFrame126(Configuration.PRICE_GUIDE, 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens the unofficial price guide.");
	}

}
