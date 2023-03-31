package io.shadowrealm.content.commands.all;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

import java.util.Optional;

/**
 * Opens the game rule page in the default web browser.
 * 
 * @author Emiel
 */
public class Grules extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().sendFrame126("https://www.shadowrealm-rsps.com/index.php?/topic/403-rules-guide-for-gambling-on-exilius/", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page with in-game rules");
	}

}
