package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Teleport the player to the mage bank.
 * 
 * @author Emiel
 */
public class Crystal extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
    	c.getPA().sendFrame126("https://www.shadowrealm-rsps.com/index.php?/topic/426-crystal-slayer-guide/", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens up the crystal guide.");
	}

}
