package io.exilius.content.commands.moderator;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

/**
 * Teleport the player to the staffzone.
 * 
 * @author Emiel
 */
public class Staffzone extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.getPosition().inClanWars() || !Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		c.getPA().startTeleport(1759, 5485, 0, "modern", false);
	}
}
