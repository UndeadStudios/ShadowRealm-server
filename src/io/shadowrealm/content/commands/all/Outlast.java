package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Open the forums in the default web browser.
 * 
 * @author Emiel
 */
public class Outlast extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.sendMessage("@red@[OUTLAST]@blu@ Bank your items and enter the portal to join the tournament! Good Luck!");
    	c.getPA().spellTeleport(3096, 3509, 0, false);
}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Quick teleport to outlast teleport.");
	}

}
