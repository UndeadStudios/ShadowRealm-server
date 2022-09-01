package io.exilius.model.entity.player;

public interface PacketType {
	void processPacket(Player c, int packetType, int packetSize);
}
