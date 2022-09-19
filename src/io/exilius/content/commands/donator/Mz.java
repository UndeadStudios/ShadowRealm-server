package io.exilius.content.commands.donator;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Right;

import java.util.Optional;

public class Mz extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.inTrade || c.inDuel || c.getPosition().inWild())  {
			return;
		}

		if (c.amDonated < 2500 && !c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
			c.sendMessage("@red@You need to be a mystic donator to do this command");
			return;
		}
		c.getPA().startTeleport(2457, 2841, 2, "modern", false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to Mystic zone.");
	}

}


