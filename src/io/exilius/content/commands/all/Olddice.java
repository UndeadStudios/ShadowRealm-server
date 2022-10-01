package io.exilius.content.commands.all;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

/**
 * Teleport the player to the mage bank.
 * 
 * @author Emiel
 */
public class Olddice extends Command {

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
		c.getPA().spellTeleport(2440, 3089, 0, false);
		c.sendMessage("@red@[WARNING] Recording can help if a scam occurs, use ::grules for rules.");

	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teles you to gambling area");
	}

}
