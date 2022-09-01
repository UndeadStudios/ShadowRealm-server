package io.exilius.model.entity.player.packets;

import java.util.Objects;

import io.exilius.Server;
import io.exilius.content.dialogue.DialogueBuilder;
import io.exilius.content.dialogue.DialogueOption;
import io.exilius.content.tradingpost.Listing;
import io.exilius.content.tradingpost.Sale;
import io.exilius.model.ContainerAction;
import io.exilius.model.ContainerActionType;
import io.exilius.model.definitions.ItemDef;
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
 * Bank All Items
 **/
public class ContainerAction4 implements PacketType {

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
		int removeSlot = c.getInStream().readUnsignedWordA();
		int interfaceId = c.getInStream().readUnsignedWord();
		int removeId = c.getInStream().readUnsignedWordA();

		ContainerAction action = new ContainerAction(ContainerActionType.ACTION_4, interfaceId, removeId, removeSlot);

		if (c.debugMessage)
			c.sendMessage("ContainerAction4: interfaceid: "+interfaceId+", removeSlot: "+removeSlot+", removeID: " + removeId);
//
//		if (c.getInterfaceEvent().isActive()) {
//			c.sendMessage("Please finish what you're doing.");
//			return;
//		}
		if (c.getLootingBag().isWithdrawInterfaceOpen()) {
			if (c.getLootingBag().handleClickItem(removeId, Integer.MAX_VALUE)) {
				return;
			}
		} else if (c.getLootingBag().isDepositInterfaceOpen()) {
			if (c.getLootingBag().handleClickItem(removeId, c.getItems().getItemAmount(removeId))) {
				return;
			}
		}

		if (c.viewingRunePouch) {
			if (c.getRunePouch().handleClickItem(removeId, Integer.MAX_VALUE, interfaceId)) {
				return;
			}
		}

		if (c.getBank().withdraw(interfaceId, removeId, Integer.MAX_VALUE)) {
			return;
		}

		switch (interfaceId) {
		case GroupIronmanBank.INTERFACE_ITEM_CONTAINER_ID:
			GroupIronmanBank.processContainerAction(c, action);
			break;
		case 26022:
			Sale sales = Listing.getSale(c.saleResults.get(removeSlot));
			c.start(new DialogueBuilder(c).option(
					new DialogueOption("Yes, I want to buy @red@ALL " + sales.getQuantity() + " " +ItemDef.forId(sales.getId()).getName()  +".", p -> {
						Listing.buyListing(c, removeSlot, sales.getQuantity() - sales.getTotalSold());
						Listing.postButtons(c, 189234);
					}),
					new DialogueOption("Exit.", p -> Listing.postButtons(c, 189234))
			));
			break;
		case 48500: //Listing interface
			if(c.isListing) {
				Listing.openSelectedItem(c, removeId, c.getItems().getItemAmount(removeId), 0);
			}
		break;
		
		case 3900:
			c.getShops().buyItem(removeId, removeSlot, 10);
			break;
			
		case 64016:
			c.getShops().buyItem(removeId, removeSlot, 10);
			break;

		case 3823:
			c.getShops().sellItem(removeId, removeSlot, 10);
			break;

		case 5064:
			if (c.inTrade) {
				return;
			}
			DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
			if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() > MultiplayerSessionStage.REQUEST
					&& duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
				c.sendMessage("You have declined the duel.");
				duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
				duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				return;
			}
			if (c.isBanking) {
				c.getItems().addToBank(removeId, c.getItems().getItemAmount(removeId), true);
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
				session.addItem(c, new GameItem(removeId, c.getItems().getItemAmount(removeId)));
			}
			break;

		case 3415:
			session = Server.getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof TradeSession || session instanceof FlowerPokerSession) {
				session.removeItem(c, removeSlot, new GameItem(removeId, Integer.MAX_VALUE));
			}
			break;

		case 6669:
			session = Server.getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof DuelSession) {
				session.removeItem(c, removeSlot, new GameItem(removeId, Integer.MAX_VALUE));
			}
			break;

//		case 7295:
//			if (ItemDef.forId(removeId).isStackable()) {
//				c.getItems().addToBank(c.playerItems[removeSlot], c.playerItemsN[removeSlot], false);
//				c.getItems().sendInventoryInterface(7423);
//			} else {
//				c.getItems().addToBank(c.playerItems[removeSlot], c.getItems().itemAmount(c.playerItems[removeSlot]), false);
//				c.getItems().sendInventoryInterface(7423);
//			}
//			break;

		}
	}

}
