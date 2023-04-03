package io.shadowrealm.content.commands.all;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.vote_panel.VotePanelInterface;
import io.shadowrealm.model.entity.player.Player;

import java.util.Optional;


public class Sandesu extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		VotePanelInterface.openInterface(c, true);
		c.getPA().sendFrame126(Configuration.SANDESU, 12000);
		c.sendMessage("@bla@[@blu@YOUTUBER@bla@] Check out all new content from SandesuSRS.");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page where you Check SandesuSRS youtube out");
	}

}