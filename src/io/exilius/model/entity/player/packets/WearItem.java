package io.exilius.model.entity.player.packets;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.CompletionistCape;
import io.exilius.content.DiceHandler;
import io.exilius.content.lootbag.LootingBag;
import io.exilius.content.skills.runecrafting.Pouches;
import io.exilius.model.Items;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;
import io.exilius.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.exilius.model.multiplayersession.MultiplayerSessionStage;
import io.exilius.model.multiplayersession.MultiplayerSessionType;
import io.exilius.model.multiplayersession.duel.DuelSession;
import io.exilius.util.Misc;

import java.util.Objects;

/**
 * Wear Item
 **/
public class WearItem implements PacketType {

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
		int wearId = c.wearId;
		wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.wearItemInterfaceId = c.getInStream().readUnsignedWordA();
		c.alchDelay = System.currentTimeMillis();
		c.nextChat = 0;
		c.dialogueOptions = 0;
		c.graniteMaulSpecialCharges = 0;		
		if (!c.getItems().playerHasItem(wearId, 1)) {
			return;
		}
		if (c.getPA().viewingOtherBank) {
			c.getPA().resetOtherBank();
			return;
		}
		if (c.isStuck) {
			c.isStuck = false;
			c.sendMessage("@red@You've disrupted stuck command, you will no longer be moved home.");
			return;
		}
		if (c.getBankPin().requiresUnlock()) {
			c.getBankPin().open(2);
			return;
		}
//		if (c.getInterfaceEvent().isActive()) {
//			c.sendMessage("Please finish what you're doing.");
//			return;
//		}
		if (Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
			Server.getMultiplayerSessionListener().finish(c, MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
			c.sendMessage("You cannot remove items from your equipment whilst trading, trade declined.");
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
		if ((c.playerAttackingIndex > 0 || c.npcAttackingIndex > 0) && wearId != 4153 && wearId != 12848 && !c.usingMagic && !c.usingBow && !c.usingOtherRangeWeapons && !c.usingCross && !c.usingBallista)
			c.attacking.reset();
		if (c.canChangeAppearance) {
			c.sendMessage("You can't wear an item while changing appearence.");
			return;
		}

		if (LootingBag.isLootingBag(wearId)) {
			c.getLootingBag().openWithdrawalMode();
			return;
		}

		if (wearId == Items.COMPLETIONIST_CAPE && !CompletionistCape.hasRequirements(c)) {
			c.sendMessage("You don't have the requirements to wear that, see Mac to view the requirements.");
			return;
		}

		if (wearId == 4155) {
			if (!c.getSlayer().getTask().isPresent()) {
				c.sendMessage("You do not have a task!");
				return;
			}
			c.sendMessage("I currently have @blu@" + c.getSlayer().getTaskAmount() + " " + c.getSlayer().getTask().get().getPrimaryName() + "@bla@ to kill.");
			c.getPA().closeAllWindows();
			return;
			
		}
		if(wearId == 8817) {
			if(!c.getLoginName().equalsIgnoreCase("osiris") && !c.getLoginName().equalsIgnoreCase("Anubis")) {
				c.sendMessage("Only Osiris or Anubis can wear this.");
				c.getItems().deleteItem(8817, 1);
				c.getItems().addItem(995, 10000000);
				return;
			}
		}
		if(wearId == 29180) {
			if(!c.getLoginName().equalsIgnoreCase("osiris") && !c.getLoginName().equalsIgnoreCase("Anubis")) {
				c.sendMessage("Only Osiris or Anubis can wear this.");
				c.getItems().deleteItem(29180, 1);
				c.getItems().addItem(995, 10000000);
				return;
			}
		}
		if (wearId == 23351) {
			c.isSkulled = true;
			c.skullTimer = Configuration.SKULL_TIMER;
			c.headIconPk = 0;
			c.sendMessage("@blu@The @red@Cape of skulls@blu@ has automatically made you skull for @yel@20 minutes.");
		}
		if(wearId == 7927) {
			c.resetWalkingQueue();
			for (int i = 0; i < 14; i++) {
				c.setSidebarInterface(i, 6014);
				c.getPA().sendFrame126("Unmorph", 6020);
			}
			c.isMorphed = true;
			c.sendMessage("As you put on the ring you turn into an egg!");
			int npcid3 = 5538 + Misc.random(5);
			c.npcId2 = npcid3;
			c.playerStandIndex = NPCCacheDefinition.forID(npcid3).getStandAnim();
			c.playerWalkIndex = NPCCacheDefinition.forID(npcid3).getWalkAnim();
			c.isNpc = true;
			c.setUpdateRequired(true);
			c.appearanceUpdateRequired = true;
		}
		if(wearId == 23185) {
			c.resetWalkingQueue();
			for (int i = 0; i < 14; i++) {
				c.setSidebarInterface(i, 6014);
				c.getPA().sendFrame126("Unmorph", 6020);
			}
			c.isMorphed = true;
			c.sendMessage("As you put on the ring you turn into an 3rd age equipment!");
			int npcid3 = 8645 + Misc.random(19);
			c.npcId2 = npcid3;
			c.playerStandIndex = -1;
			c.playerWalkIndex = -1;
			c.isNpc = true;
			c.setUpdateRequired(true);
			c.appearanceUpdateRequired = true;
		}
		if(wearId == 20017) {
			c.resetWalkingQueue();
			for (int i = 0; i < 14; i++) {
				c.setSidebarInterface(i, 6014);
				c.getPA().sendFrame126("Unmorph", 6020);
			}
			c.isMorphed = true;
			c.sendMessage("As you put on the ring you turn into a pile of coins!");
			int npcid3 = 7315;
			c.npcId2 = npcid3;
			c.playerStandIndex = NPCCacheDefinition.forID(npcid3).getStandAnim();
			c.playerWalkIndex = NPCCacheDefinition.forID(npcid3).getWalkAnim();
			c.isNpc = true;
			c.setUpdateRequired(true);
			c.appearanceUpdateRequired = true;
		}
		switch (wearId) {
		case 21347:
			c.boltTips = true;
			c.arrowTips = false;
			c.javelinHeads = false;
			c.sendMessage("Your Amethyst method is now Bolt Tips!");
			break;
		case 5509:
			Pouches.empty(c, 0);
			break;
		case 5510:
			Pouches.empty(c, 1);
			break;
		case 5512:
			Pouches.empty(c, 2);
			break;
		}
		
		if (wearId == DiceHandler.DICE_BAG) {
			DiceHandler.selectDice(c, wearId);
		}
		if (wearId > DiceHandler.DICE_BAG && wearId <= 15100) {
			DiceHandler.rollDice(c);
		}


		if (!Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
			c.getPlayerAssistant().resetFollow();
			c.attacking.reset();
			c.getItems().equipItem(wearId, c.wearSlot);
		}
	}

}
