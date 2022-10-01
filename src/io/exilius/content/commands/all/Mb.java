package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

/**
 * Teleport the player to the mage bank.
 * 
 * @author Emiel
 */
public class Mb extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		if (c.getPosition().inClanWars() || !Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		if (c.getPosition().inWild()) {
			return;
		}
		c.getPA().spellTeleport(2539, 4716, 0, false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to the mage bank");
	}

}
