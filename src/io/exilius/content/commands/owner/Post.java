package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.content.tradingpost.Listing;
import io.exilius.model.entity.player.Player;

public class Post extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Listing.openPost(c, false);
	}
}
