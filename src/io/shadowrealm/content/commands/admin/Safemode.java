package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Toggles whether the player will lose items on death or not.
 * 
 * @author Emiel
 */
public class Safemode extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.inSafemode()) {
			c.setSafemode(false);
			c.sendMessage("You now lose items on death again.");
		} else {
			c.setSafemode(true);
			c.sendMessage("You no longer lose items on death.");
		}
	}
}
