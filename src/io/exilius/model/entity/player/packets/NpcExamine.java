package io.exilius.model.entity.player.packets;

import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.npc.NpcExamines;
import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;

public class NpcExamine implements PacketType {

    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        final int npcId = c.getInStream().readUnsignedWord();
        final int index = c.getInStream().readUnsignedWord();
        if (c.debugMessage) {
            c.sendMessage("Npc examine: " + npcId + " slot: " + index);
        }
        final NPCCacheDefinition definition = NPCCacheDefinition.forID(npcId);
        NpcExamines description = NpcExamines.forId(npcId);
        if (description != null) {
            String examine = description.getName();
            c.sendMessage(examine);
            if(c.debugMessage) {
                c.sendMessage("Item id: " + npcId + " Needs a examine.");
            }
        } else {
            NPCCacheDefinition cacheDefinition = NPCCacheDefinition.forID(npcId);
            c.sendMessage("Its a " + cacheDefinition.getName() + ".");
        }

    }
}
