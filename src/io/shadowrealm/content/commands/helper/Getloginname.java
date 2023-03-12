package io.shadowrealm.content.commands.helper;

import io.shadowrealm.Server;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.sql.displayname.GetLoginNameSqlQuery;

import java.util.Optional;

/**
 * Forces a given player to log out.
 * 
 * @author Emiel
 */
public class Getloginname extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Server.getDatabaseManager().exec((context, connection) -> {
			String name = new GetLoginNameSqlQuery(input).execute(context, connection);
			if (name == null) {
				c.addQueuedAction(plr -> plr.sendMessage("No login name for '@blu@{}@bla@'.", input));
				return null;
			}
			c.addQueuedAction(plr -> plr.sendMessage("Login name for '@blu@{}@bla@' is '@blu@{}@bla@'.", input, name));
			return null;
		});
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Get login name for inputted display name [getloginname display name]");
	}

}
