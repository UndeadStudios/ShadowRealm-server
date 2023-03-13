package io.shadowrealm.content.commands.helper;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.commands.punishment.PunishmentCommand;
import io.shadowrealm.model.entity.player.Player;

/**
 * Forces a given player to log out.
 * 
 * @author Emiel
 */
public class UnccMute extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		new PunishmentCommand(commandName, input).parse(c);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Unmutes a player from the help cc.");
	}

	@Override
	public String getFormat() {
		return PunishmentCommand.getFormat(getCommand());
	}

}