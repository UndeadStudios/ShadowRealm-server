package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.npc.NPCSpawning;
import io.shadowrealm.model.entity.player.Player;

/**
 * Spawn a specific Npc.
 * 
 * @author Emiel
 *
 */
public class Npc extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		int newNPC = Integer.parseInt(input);
		if (newNPC > 0) {
			NPCSpawning.spawnNpc(c, newNPC, c.absX, c.absY, c.heightLevel, 0, 7, false, false);
			c.sendMessage("You spawn a Npc.");
		} else {
			c.sendMessage("No such NPC.");
		}
	}
}
