package io.exilius.content.commands.donator;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

/**
 * Teleports the player to the donator zone.
 * 
 * @author Emiel
 */
public class Dz extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.inTrade || c.inDuel || c.getPosition().inWild()) {
			return;
		}
		if (c.getPosition().inClanWars() || !Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
			c.sendMessage("@cr10@This player is currently at the pk district.");
			return;
		}
		c.getPA().startTeleport(3809, 2844, 0, "modern", false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to donator zone.");
	}

}
