package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.minigames.raids.Raids;
import io.shadowrealm.model.collisionmap.doors.Location;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;

public class Stuckraids extends Commands {

	@Override
	public void execute(Player player, String commandName, String input) {
		Raids raidInstance = player.getRaidsInstance();
			if(raidInstance != null && Boundary.isIn(player, Boundary.RAIDS)) {
    			player.sendMessage("@blu@Sending you back to starting room...");
    			Location startRoom = raidInstance.getStartLocation();
    			player.getPA().movePlayer(startRoom.getX(), startRoom.getY(), raidInstance.currentHeight);
    			raidInstance.resetRoom(player);
    		} else {
    			player.sendMessage("@red@You need to be in a raid to do this...");
    		}
	}
	public Optional<String> getDescription() {
		return Optional.of("Teleports you out of raids if your stuck.");
	}
}
