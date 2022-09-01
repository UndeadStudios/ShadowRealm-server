package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Maxisland extends Command {
	
	@Override
	public void execute(Player c, String commandName, String input) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		if (c.getPosition().inClanWars() || c.getPosition().inClanWarsSafe()) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		if (c.getPosition().inWild()) {
			return;
		}
		c.getPA().spellTeleport(3828, 3893, 0, false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to Max Island");
	}

}
