package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Opens the vote page in the default web browser.
 * 
 * @author Emiel
 */
public class Discord extends Command {

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Invites you to our Discord server");
	}

	@Override
	public void execute(Player player, String commandName, String input) {
		// TODO Auto-generated method stub
		
	}

}
