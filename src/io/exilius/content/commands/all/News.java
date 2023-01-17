package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

/**
 * Teleport the player to the mage bank.
 * 
 * @author Emiel
 */
public class News extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		 c.getPA()
         .sendFrame126(
                 "https://www.endless-os.com/index.php?/forum/5-update-log/", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Check out all of our updates.");
	}

}
