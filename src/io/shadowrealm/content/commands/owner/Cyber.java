package io.shadowrealm.content.commands.owner;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Toggle the Cyber Monday sale on or off.
 * 
 * @author Emiel
 *
 */
public class Cyber extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Configuration.CYBER_MONDAY = !Configuration.CYBER_MONDAY;
		String status = Configuration.CYBER_MONDAY ? "On" : "Off";
		c.sendMessage("Cyber monday: " + status);
	}
}
