package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;

/**
 * Clicking in game
 **/
public class ClickingInGame implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		c.getInStream().readInteger(); // Packed integer containng mouse coordinates and click type
	}

}
