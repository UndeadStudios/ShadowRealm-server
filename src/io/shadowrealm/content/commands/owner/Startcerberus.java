package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.bosses.Cerberus;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Update the shops.
 * 
 * @author Emiel
 *
 */
public class Startcerberus extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		Cerberus.init(player);
	}
}
