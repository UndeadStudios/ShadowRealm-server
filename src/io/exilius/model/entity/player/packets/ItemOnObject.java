package io.exilius.model.entity.player.packets;

/**
 * @author Ryan / Lmctruck30
 */

import java.util.Objects;

import io.exilius.Server;
import io.exilius.content.items.UseItem;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.exilius.model.multiplayersession.MultiplayerSessionStage;
import io.exilius.model.multiplayersession.MultiplayerSessionType;
import io.exilius.model.multiplayersession.duel.DuelSession;
import io.exilius.model.tickable.impl.WalkToTickable;

public class ItemOnObject implements PacketType {

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

		int a = c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readInteger();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		int b = c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		c.facePosition(objectX, objectY);
		c.objectX = objectX;
		c.objectY = objectY;
		c.xInterfaceId = -1;
		c.getPA().stopSkilling();


		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		if (!c.goodDistance(c.objectX, c.objectY, c.absX, c.absY, 3)) {
			return;
		}
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


			c.getFarming().handleItemOnObject(itemId, objectId, objectX, objectY);
			switch (c.objectId) {

				case 2030: //Allows for items to be used from both sides of the furnace
					c.objectDistance = 4;
					c.objectXOffset = 3;
					c.objectYOffset = 3;
					break;
				case 26782:
					c.objectDistance = 7;
					break;
				case 33320:
					c.objectDistance = 5;
					break;
				case 33311:
					c.objectDistance = 3;//hespori
					break;
				case 18818:
				case 409:
					c.objectDistance = 3;
					break;
				case 884:
					c.objectDistance = 5;
					c.objectXOffset = 3;
					c.objectYOffset = 3;
					break;

				case 28900:
					c.objectDistance = 3;
					break;

				case 34813:// air altar entrance
				case 34814:// mind altar entrance
				case 34815:// water altar entrance
				case 34816:// earth altar entrance
				case 34817:// fire altar entrance
				case 34818:// body altar entrance
				case 34819:// cosmic altar entrance
				case 34820:// law altar entrance
				case 34821:// nature altar entrance
				case 34822:// chaos altar entrance
				case 34823:// death altar entrance
					c.objectDistance = 6;
					break;

				default:
					c.objectDistance = 1;
					c.objectXOffset = 0;
					c.objectYOffset = 0;
					break;

			}

			c.facePosition(objectX, objectY);
			UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);
	}

}
