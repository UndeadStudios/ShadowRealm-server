package io.exilius.model.entity.player.packets;

import java.util.Objects;

import io.exilius.Server;
import io.exilius.content.ItemSpawner;
import io.exilius.content.skills.crafting.JewelryMaking;
import io.exilius.content.skills.smithing.Smithing;
import io.exilius.content.tradingpost.Listing;
import io.exilius.model.ContainerAction;
import io.exilius.model.ContainerActionType;
import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.mode.group.GroupIronmanBank;
import io.exilius.model.items.GameItem;
import io.exilius.model.multiplayersession.MultiplayerSession;
import io.exilius.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.exilius.model.multiplayersession.MultiplayerSessionStage;
import io.exilius.model.multiplayersession.MultiplayerSessionType;
import io.exilius.model.multiplayersession.duel.DuelSession;
import io.exilius.model.multiplayersession.flowerpoker.FlowerPokerSession;
import io.exilius.model.multiplayersession.trade.TradeSession;

/**
 * Bank 10 Items
 **/
public class ContainerAction3 implements PacketType {

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
		int interfaceId = c.getInStream().readUnsignedWordBigEndian();
		int removeId = c.getInStream().readUnsignedWordA();
		int removeSlot = c.getInStream().readUnsignedWordA();

		ContainerAction action = new ContainerAction(ContainerActionType.ACTION_3, interfaceId, removeId, removeSlot);

		if (c.debugMessage)
			c.sendMessage("ContainerAction3: interfaceid: "+interfaceId+", removeSlot: "+removeSlot+", removeID: " + removeId);

		
//		if (c.getInterfaceEvent().isActive()) {
//			c.sendMessage("Please finish what you're doing.");
//			return;
//		}
		if (c.getLootingBag().isWithdrawInterfaceOpen() || c.getLootingBag().isDepositInterfaceOpen()) {
			if (c.getLootingBag().handleClickItem(removeId, 10)) {
				return;
			}
		}
		if (c.viewingRunePouch) {
			if (c.getRunePouch().handleClickItem(removeId, 10, interfaceId)) {
				return;
			}
		}

		if (c.getBank().withdraw(interfaceId, removeId, 10)) {
			return;
		}

		switch (interfaceId) {
		case GroupIronmanBank.INTERFACE_ITEM_CONTAINER_ID:
			GroupIronmanBank.processContainerAction(c, action);
			break;
		case ItemSpawner.CONTAINER_ID:
			ItemSpawner.spawn(c, removeId, -1);
			break;
		case 36022:
			Listing.buyListing(c, removeSlot, 10);
			break;
		case 48500: //Listing interface
			if(c.isListing) {
				int amount = 10;
				if(c.getItems().getItemAmount(removeId) < 10)
					amount = c.getItems().getItemAmount(removeId);
				Listing.openSelectedItem(c, removeId, amount, 0);
			}
		break;
		
		case 4233:
		case 4239:
		case 4245:
			JewelryMaking.mouldItem(c, removeId, 10);
			break;
		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			Smithing.readInput(c.playerLevel[Player.playerSmithing], Integer.toString(removeId), c, 27);
			break;
		case 1688:
			c.getPA().useOperate(removeId);
			break;
		case 3900:
			c.getShops().buyItem(removeId, removeSlot, 5);
			break;
		case 64016:
			c.getShops().buyItem(removeId, removeSlot, 5);
			break;

		case 3823:
			c.getShops().sellItem(removeId, removeSlot, 5);
			break;

		case 5064:
			if (Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
				Server.getMultiplayerSessionListener().finish(c, MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				c.sendMessage("You cannot do this whilst trading.");
				return;
			}
			DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
			if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
				c.sendMessage("You have declined the duel.");
				duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
				duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				return;
			}
			if (c.isBanking) {
				c.getItems().addToBank(removeId, 10, true);
			} else {
				GroupIronmanBank.processContainerAction(c, action);
			}
			break;

		case 3322:
			MultiplayerSession session = Server.getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof TradeSession || session instanceof DuelSession || session instanceof FlowerPokerSession) {
				session.addItem(c, new GameItem(removeId, 10));
			}
			break;

		case 3415:
			session = Server.getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof TradeSession || session instanceof FlowerPokerSession) {
				session.removeItem(c, removeSlot, new GameItem(removeId, 10));
			}
			break;

		case 6669:
			session = Server.getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof DuelSession) {
				session.removeItem(c, removeSlot, new GameItem(removeId, 10));
			}
			break;

		}
	}

}
