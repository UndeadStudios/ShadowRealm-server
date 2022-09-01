package io.exilius.content.commands.helper;

import io.exilius.content.commands.Command;
import io.exilius.content.help.HelpDatabase;
import io.exilius.model.entity.player.Player;

/**
 * Opens an interface containing all help tickets.
 * 
 * @author Emiel
 */
public class Helpdb extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		HelpDatabase.getDatabase().openDatabase(c);
	}
}
