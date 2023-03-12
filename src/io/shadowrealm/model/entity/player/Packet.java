package io.shadowrealm.model.entity.player;

/**
 * Packet interface.
 * 
 * @author Graham
 * 
 */
public interface Packet {

	void handlePacket(Player client, int packetType, int packetSize);

}
