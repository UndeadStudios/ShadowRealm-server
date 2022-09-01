package io.exilius.model.entity.player.packets;

import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;

public class IdleLogout implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		if (!c.isIdle) {
			if (c.debugMessage) {
				c.sendMessage("You are now in idle mode.");
			}
			c.isIdle = true;
		}
	}
}