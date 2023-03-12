package io.shadowrealm.content.commands.helper;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.help.HelpDatabase;
import io.shadowrealm.model.entity.player.Player;

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
