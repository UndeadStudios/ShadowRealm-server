package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Changes the password of the player.
 * 
 * @author Emiel
 *
 */
public class Slayer extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.getPosition().inWild()) {
			c.sendMessage("You can only use this command outside the wilderness.");
			return;
		}
		c.getPA().startTeleport(3112, 3514, 0, "modern", false);
		c.stopAnimation();
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to slayer area.");
	}
}
