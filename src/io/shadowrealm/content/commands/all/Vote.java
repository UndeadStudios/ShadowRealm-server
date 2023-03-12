package io.shadowrealm.content.commands.all;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.vote_panel.VotePanelInterface;
import io.shadowrealm.model.entity.player.Player;

import java.util.Optional;


public class Vote extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		VotePanelInterface.openInterface(c, true);
		c.getPA().sendFrame126(Configuration.VOTE_LINK, 12000);
		c.sendMessage("@bla@[@blu@VOTE@bla@] You may also use @red@::claimvote@bla@ to redeem the votes.");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page where you can vote for rewards");
	}

}
