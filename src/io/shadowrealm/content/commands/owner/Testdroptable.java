package io.shadowrealm.content.commands.owner;

import io.shadowrealm.Server;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Testdroptable extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
//		if (Config.SERVER_STATE != ServerState.PRIVATE || Config.SERVER_STATE != ServerState.PUBLIC_SECONDARY) {
//			c.sendMessage("You can only do this on the private server, not on the local server.");
//			return;
//		}
		c.getBank().getCurrentBankTab().getItems().clear();
		int npcId = Integer.parseInt(input.split("-")[0]);
		int amount = Integer.parseInt(input.split("-")[1]);
		Server.getDropManager().test(c, npcId, amount);
	}

}
