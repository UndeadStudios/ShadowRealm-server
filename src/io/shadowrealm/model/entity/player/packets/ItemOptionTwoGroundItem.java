package io.shadowrealm.model.entity.player.packets;

import java.util.Objects;

import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.PacketType;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionStage;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionType;
import io.shadowrealm.model.multiplayersession.duel.DuelSession;

public class ItemOptionTwoGroundItem implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		if (c.getMovementState().isLocked() || c.getLock().cannotInteract(c))
			return;
		if (c.isFping()) {
			/**
			 * Cannot do action while fping
			 */
			return;
		}
		c.interruptActions();
		final int itemX = c.getInStream().readSignedWordBigEndian();
		final int itemY = c.getInStream().readSignedWordBigEndianA();
		final int itemId = c.getInStream().readUnsignedWordA();
//		if (c.getInterfaceEvent().isActive()) {
//			c.sendMessage("Please finish what you're doing.");
//			return;
//		}
		if (c.getBankPin().requiresUnlock()) {
			c.getBankPin().open(2);
			return;
		}
		DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
		if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() > MultiplayerSessionStage.REQUEST
				&& duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
			c.sendMessage("Your actions have declined the duel.");
			duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
			duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
			return;
		}
		//
	}
}