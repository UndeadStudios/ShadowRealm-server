package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

/**
 * Changes the password of the player.
 * 
 * @author Emiel
 *
 */
public class Kdr extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
        c.forcedChat("My Kill/Death ratio is " + c.killcount + " Kills: " + c.deathcount + " Deaths ");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Lets you know your KDR.");
	}
}
