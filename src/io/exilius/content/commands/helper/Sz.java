package io.exilius.content.commands.helper;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

/**
 * Teleport the player to the staffzone.
 * 
 * @author Emiel
 */
public class Sz extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.getPosition().inClanWars() || !Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		c.getPA().startTeleport(3164	, 3490, 3, "modern", false);
	}
}
