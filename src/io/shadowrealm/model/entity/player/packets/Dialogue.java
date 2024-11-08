package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;

/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		c.getInStream().readSignedWord();
		if (c.lastDialogueSkip == Server.getTickCount()) {
			return;
		}

		c.lastDialogueSkip = Server.getTickCount();

		if (c.getDialogueBuilder() != null) {
			if (c.getDialogueBuilder().getCurrent().isContinuable()) {
				if (c.getDialogueBuilder().hasNext()) {
					if (c.clickedNpcIndex > 0) {
						NPC npc = NPCHandler.npcs[c.clickedNpcIndex];
						if (npc != null) {
							npc.facePlayer(c.getIndex());
							c.facePosition(npc.getPosition());
						}
					}
				}
				c.getDialogueBuilder().sendNextDialogue();
			}
		} else if (!c.lastDialogueNewSystem) {
			if (c.nextChat > 0) {
				c.getDH().sendDialogues(c.nextChat, c.talkingNpc);
				if (c.clickedNpcIndex > 0) {
					NPC npc = NPCHandler.npcs[c.clickedNpcIndex];
					if (npc != null) {
						npc.facePlayer(c.getIndex());
					}
				}
			} else {
				c.getDH().sendDialogues(0, -1);
			}
		}
	}

}
