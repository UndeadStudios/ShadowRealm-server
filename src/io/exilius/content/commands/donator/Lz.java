package io.exilius.content.commands.donator;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Right;

/**
 * Teleports the player to the donator zone.
 * 
 * @author Emiel
 */
public class Lz extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.inTrade || c.inDuel || c.getPosition().inWild()) {
			return;
		}
		if (c.getPosition().inClanWars() || !Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
			c.sendMessage("@cr10@This player is currently at the pk district.");
			return;
		}
		if (c.amDonated < 250 && !c.getRights().isOrInherits(Right.HELPER)) {
			c.sendMessage("@red@You need legendary donator to do this command");
			return;
			} 
		c.getPA().startTeleport(2846, 5089, 0, "modern", false);

	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to legendary zone.");
	}

}
