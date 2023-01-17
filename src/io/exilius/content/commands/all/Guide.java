package io.exilius.content.commands.all;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

import java.util.Optional;

/**
 * Open the forums in the default web browser.
 * 
 * @author Emiel
 */
public class Guide extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().sendFrame126("https://www.endless-os.com/index.php?/topic/199-guide-index/", 12000);//
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page with our forums");
	}

}
