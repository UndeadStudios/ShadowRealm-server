package io.shadowrealm.content.commands.donator;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;

import java.util.Optional;

public class Divine extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (c.inTrade || c.inDuel || c.getPosition().inWild()) {
			return;
		}

		if (c.amDonated < 5000 && !c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
			c.sendMessage("@red@You need to be a divine donator to do this command");
			return;
		}
		c.getPA().startTeleport(3263, 6089, 2, "modern", false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to divine zone.");
	}

}


