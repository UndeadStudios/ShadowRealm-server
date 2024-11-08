package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.model.ContainerAction;
import io.shadowrealm.model.ContainerActionType;
import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;

public class ContainerAction7 implements PacketType {

	@Override
	public void processPacket(Player player, int packetType, int packetSize) {
		if (player.getMovementState().isLocked() || player.getLock().cannotInteract(player))
			return;
		if (player.isFping()) {
			/**
			 * Cannot do action while fping
			 */
			return;
		}
		player.interruptActions();
		int interfaceId = player.getInStream().readUnsignedWord();
		int itemId = player.getInStream().readSignedWordBigEndianA();
		int itemSlot = player.getInStream().readSignedWordBigEndian();

		ContainerAction action = new ContainerAction(ContainerActionType.ACTION_7, interfaceId, itemId, itemSlot);

		if (player.debugMessage)
			player.sendMessage("ContainerAction4: interfaceid: "+interfaceId+", removeSlot: "+itemSlot+", removeID: " + itemId);
		
//		if (player.getInterfaceEvent().isActive()) {
//			player.sendMessage("Please finish what you're doing.");
//			return;
//		}

		if (player.getBank().withdraw(interfaceId, itemId, player.getBank().getAllButOne(interfaceId, itemId))) {
			return;
		}
	}

}
