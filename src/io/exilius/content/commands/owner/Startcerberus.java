package io.exilius.content.commands.owner;

import io.exilius.content.bosses.Cerberus;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

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
