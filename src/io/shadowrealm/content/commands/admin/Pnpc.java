package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.entity.npc.NPCCacheDefinition;
import io.shadowrealm.model.entity.player.Player;

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

		if (npc > 12140) {
			c.sendMessage("Max npc id is: 12140");
			return;
		}
		c.npcId2 = npc;
		c.playerStandIndex = NPCCacheDefinition.forID(npc).getStandAnim();
		c.playerWalkIndex = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.playerRunIndex = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.playerTurnIndex = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.playerTurn180Index = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.playerTurn90CCWIndex = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.playerTurn90CWIndex = NPCCacheDefinition.forID(npc).getWalkAnim();
		c.isNpc = true;
		c.setUpdateRequired(true);
		c.appearanceUpdateRequired = true;
	}

//	@Override
//	public void execute(Player player, String commandName, String input) {
//
//	}
}