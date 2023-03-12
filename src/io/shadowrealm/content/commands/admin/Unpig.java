package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Change back to the original player model.
 * 
 * @author Emiel
 *
 */
public class Unpig extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.isNpc = false;
		c.isXslimed = false;
		c.setUpdateRequired(true);
		c.appearanceUpdateRequired = true;
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
		c.getPA().requestUpdates();
	}
}
