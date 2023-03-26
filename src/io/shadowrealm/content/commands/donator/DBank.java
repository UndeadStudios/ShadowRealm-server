package io.shadowrealm.content.commands.donator;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;

/**
 * Open the banking interface.
 * 
 * @author Emiel
 */
public class DBank extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.getPA().c.itemAssistant.openUpBank();
		c.inBank = true;
		if (c.amDonated < 250 && !c.getRights().isOrInherits(Right.LEGENDARY_DONATOR)) {
			c.sendMessage("@red@You need legendary donator to do this command");
			return;
		}
	}
}
