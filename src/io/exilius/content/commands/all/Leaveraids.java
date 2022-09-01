package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.content.minigames.raids.Raids;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;

public class Leaveraids extends Commands {

	@Override
	public void execute(Player player, String commandName, String input) {
		Raids raidInstance = player.getRaidsInstance();
		if(raidInstance != null) {
			player.sendMessage("@blu@You are now leaving the raid...");
			if (Boundary.isIn(player, Boundary.FULL_RAIDS)) {
				raidInstance.leaveGame(player);
			} else {
				player.sendMessage("Please re-log and report this issue to staff...");
			}
		} else {
			player.sendMessage("@red@You need to be in a raid to do this...");
		}
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Forces you to leave raids.");
	}

}
