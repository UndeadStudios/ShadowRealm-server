package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;

/**
 * Kill a player.
 * 
 * @author Emiel
 */
public class Kill extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Player player = PlayerHandler.getPlayerByDisplayName(input);
		if (player == null) {
			c.sendMessage("Player is null.");
			return;
		}
		player.appendDamage(c, player.getHealth().getMaximumHealth(), Hitmark.HIT);
		player.sendMessage("You have been merked");
	}
}
