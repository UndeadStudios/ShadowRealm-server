package io.exilius.content.commands.admin;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.player.Player;

/**
 * Transform a given player into an npc.
 *
 * @author Emiel
 *
 */
public class Pnpc extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		int npc = Integer.parseInt(input);

		if (npc > 11463) {
			c.sendMessage("Max npc id is: 11463");
			return;
		}
		c.npcId2 = npc;
		c.playerStandIndex = NPCCacheDefinition.forID(npc).getStandIndex();
		c.playerWalkIndex = NPCCacheDefinition.forID(npc).getWalkIndex();
		c.playerRunIndex = NPCCacheDefinition.forID(npc).getWalkIndex();
		c.isNpc = true;
		c.setUpdateRequired(true);
		c.appearanceUpdateRequired = true;
	}

//	@Override
//	public void execute(Player player, String commandName, String input) {
//
//	}
}