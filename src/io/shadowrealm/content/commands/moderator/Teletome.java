package io.shadowrealm.content.commands.moderator;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;

/**
 * Teleport a given player to the player who issued the command.
 * 
 * @author Emiel
 */
public class Teletome extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
		if (optionalPlayer.isPresent()) {
			Player c2 = optionalPlayer.get();
			if (!c.getRights().isOrInherits(Right.MODERATOR)) {
				if (c2.getPosition().inClanWars() || c2.getPosition().inClanWarsSafe()) {
					c.sendMessage("@cr10@This player is currently at the pk district.");
					return;
				}
			}
			c2.setTeleportToX(c.absX);
			c2.setTeleportToY(c.absY);
			c2.heightLevel = c.heightLevel;
			c2.isStuck = false;
			c.sendMessage("You have teleported " + c2.getDisplayName() + " to you.");
			c2.sendMessage("You have been teleported to " + c.getDisplayName() + "");
		} else {
			c.sendMessage(input + " is offline. You can only teleport online players.");
		}
	}
}