package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.Configuration;
import io.exilius.content.commands.Command;
import io.exilius.content.itemskeptondeath.ItemsKeptOnDeathInterface;
import io.exilius.model.entity.player.Player;

/**
 * Changes the password of the player.
 * 
 * @author Emiel
 *
 */
public class Skull extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.isSkulled = true;
		c.skullTimer = Configuration.SKULL_TIMER;
		c.headIconPk = 0;
		c.getPA().requestUpdates();
		c.sendMessage("You are now skulled.");
		ItemsKeptOnDeathInterface.refreshIfOpen(c);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Puts a skull above your head..");
	}
}
