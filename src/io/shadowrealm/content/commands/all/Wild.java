package io.shadowrealm.content.commands.all;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.wildwarning.WildWarning;
import io.shadowrealm.model.entity.player.Player;

import java.util.Optional;

/**
 * Show the current position.
 * 
 * @author Noah
 *
 */
public class Wild extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		WildWarning.sendWildWarning(player, null);
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Open the wild warning interface.");
	}
}
