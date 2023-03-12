package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;

/**
 * Slient Packet
 **/
public class SilentPacket implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {

	}
}
