package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.battle_pass.BattlePassHandler;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;

import java.util.Optional;

/**
 * Open the battlepass interface and fill with inventory items
 * 
 * @author Emiel
 */
public class ResetBPpremium extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
		if (optionalPlayer.isPresent()) {
			Player c2 = optionalPlayer.get();
			BattlePassHandler.Companion.resetPlayerBattlePassPremium(c2);
			c.sendMessage("You have reset " + c2.getDisplayName() + "'s battle pass Premium rewards");
		} else {
			c.sendMessage(input + " is offline. You can only reset online players.");
		}
	}
}
