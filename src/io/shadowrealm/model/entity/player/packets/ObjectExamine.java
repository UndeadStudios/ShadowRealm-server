package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.model.ObjectExamines;
import io.shadowrealm.model.collisionmap.ObjectDef;
import io.shadowrealm.model.collisionmap.Region;
import io.shadowrealm.model.collisionmap.RegionProvider;
import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.model.entity.player.Right;

public class ObjectExamine implements PacketType {

    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        final int x = c.getInStream().readSignedWordBigEndianA();
        final int id = c.getInStream().readUnsignedWord();
        final int y = c.getInStream().readUnsignedWordA();
        final Position position = new Position(x, y, c.getHeight());
        if (c.debugMessage) {
            c.sendMessage("Object Examine: " + id + " objectX: " + x + " objectY: " + y);
        }

        ObjectExamines description = ObjectExamines.forId(id);
        if (description != null) {
            String examine = description.getName();
            c.sendMessage(examine);
        } else {
            if (c.getRights().contains(Right.OWNER)) {
                c.sendMessage("Object id: "+id+" Needs a examine.");
            } else {
                ObjectDef cacheDefinition = ObjectDef.getObjectDef(id);
                c.sendMessage("Its a " + cacheDefinition.name + ".");
            }
        }
    }
}
