package io.shadowrealm.model.entity.player.packets.dialogueoptions;

import com.google.common.collect.Lists;
import io.shadowrealm.content.fireofexchange.FireOfExchange;
import io.shadowrealm.content.achievement_diary.impl.ArdougneDiaryEntry;
import io.shadowrealm.content.achievement_diary.impl.FaladorDiaryEntry;
import io.shadowrealm.content.achievement_diary.impl.VarrockDiaryEntry;
import io.shadowrealm.content.items.TomeOfFire;
import io.shadowrealm.content.skills.construction.House;
import io.shadowrealm.content.skills.construction.Room;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.model.items.bank.BankPin;

/*
 * @author Matt
 * Three Option Dialogue actions
 */

public class ThreeOptions {

	/*
	 * Handles all first options on 'Three option' dialogues.
	 */
	public static void handleOption1(Player c) {
		switch (c.dialogueAction) {
		case 265:
			TomeOfFire.store(c);
            break;
			case 903:
				c.getPA().showInterface(24595);
				break;
			case 745:
				House house;

				if (c.getHouse() == null) {
					house = House.load(c);
				} else {
					house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c) ;
				}
				house.setBuildMode(false);
				c.setHouse(house);
				house.enter(c);
				c.getPA().closeAllWindows();
				break;
		case 152:
			c.getDH().sendDialogues(153, 1603);
			break;
		case 1428:
			c.getPrestige().openPrestige();
			break;
		case 130135:
			c.currentExchangeItemAmount = c.getItems().getItemAmount(c.currentExchangeItem);
			FireOfExchange.exchangeItemForPoints(c);
			c.getPA().closeAllWindows();
			if (Boundary.isIn(c, Boundary.EDGEVILLE_PERIMETER)) {
				c.getItems().sendItemContainer(33403, Lists.newArrayList(new GameItem(4653, 1)));
				c.getPA().sendInterfaceSet(33400, 33404);
				c.getItems().sendInventoryInterface(33405);
				c.getPA().sendFrame126("@gre@" + c.exchangePoints, 33410);
				c.getPA().sendFrame126("@red@0", 33409);
			}
			break;
		case 809: // Withdraw
			// TODO: withdraw 10
			break;
		case 811: // Deposit
			// TODO: withdraw 10
			break;
		case 806:
			c.getDH().sendDialogues(811, 6773);
			break;

		case 71: // Jad, sell cape
			if (!c.getItems().playerHasItem(6570)) {
				c.sendMessage("You do not have a firecape.");
				return;
			}
			c.getItems().deleteItem(6570, 1);
			c.getItems().addItem(6529, 8_000);
			c.getPA().removeAllWindows();
			break;

		case 55:
			c.getCT().seas("TEN");
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
			break;
		case 56:
			c.getCT().swamp("TEN");
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
			break;
		}
		if (c.dialogueAction == 137) {
			c.getPA().c.itemAssistant.openUpBank();
			return;
		}
		if (c.dialogueAction == 126) {
			c.getPA().startTeleport(3039, 4835, 0, "modern", false);
			c.dialogueAction = -1;
			c.teleAction = -1;
			return;
		}
		switch (c.teleAction) {
		case 2:
			c.getPA().spellTeleport(1571, 3656, 0, false);
			break;
		}
		if (c.dialogueAction == 100) {
			c.getShops().openShop(80);
			return;
		}
		if (c.dialogueAction == 2245) {
			c.getPA().startTeleport(2110, 3915, 0, "modern", false);
			c.sendMessage("High Priest teleported you to @red@Lunar Island@bla@.");
			c.getPA().closeAllWindows();
		}
		if (c.dialogueAction == 508) {
			c.getDH().sendDialogues(1030, 925);
			return;
		}
		if (c.teleAction == 2) {
			// brim
			c.getPA().spellTeleport(1571, 3656, 0, false);
		}
		if (c.dialogueAction == 502) {
			c.getDH().sendDialogues(1030, 925);
			return;
		}
		if (c.dialogueAction == 251) {
			c.getPA().c.itemAssistant.openUpBank();
		}
		if (c.teleAction == 200) {
			c.getPA().spellTeleport(2662, 2652, 0, false);
		}
		if (c.doricOption) {
			c.getDH().sendDialogues(306, 284);
			c.doricOption = false;
		}
	}

	/*
	 * Handles all 2nd options on 'Three option' dialogues.
	 */
	public static void handleOption2(Player c) {

		switch (c.dialogueAction) {
			case 903:
				try {
					c.replaceWith = (Room) Class.forName(c.toReplace.getClassName()).newInstance();
					c.replaceWith.setCustomObjects(c.toReplace.getCustomObjects());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
					return;
				}
				c.getDH().sendDialogues(907, 0);
				break;
			case 745:
				House house;

				if (c.getHouse() == null) {
					house = House.load(c);
				} else {
					house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c) ;
				}
				house.setBuildMode(true);
				c.setHouse(house);
				house.enter(c);
				c.getPA().closeAllWindows();
				break;
		case 265:
			TomeOfFire.remove(c);
		    break;
		case 1428:
			c.getPrestige().openShop();
			break;
		case 809: // Withdraw
			// TODO: withdraw 100
			break;
		case 811: // Deposit
			// TODO: withdraw 100
			break;
		case 130135:
			c.currentExchangeItemAmount = 1;
			FireOfExchange.exchangeItemForPoints(c);
			c.getPA().closeAllWindows();
			if (Boundary.isIn(c, Boundary.EDGEVILLE_PERIMETER)) {
				c.getItems().sendItemContainer(33403, Lists.newArrayList(new GameItem(4653, 1)));
				c.getPA().sendInterfaceSet(33400, 33404);
				c.getItems().sendInventoryInterface(33405);
				c.getPA().sendFrame126("@gre@" + c.exchangePoints, 33410);
				c.getPA().sendFrame126("@red@0", 33409);
			}
			break;

			case 806:
			c.getDH().sendDialogues(809, 6773);
			break;
		case 71: // Jad, keep cape
			c.getPA().removeAllWindows();
			break;

		case 55:
			c.getCT().seas("HUNDRED");
			c.getPA().removeAllWindows();
			break;
		case 56:
			c.getCT().swamp("HUNDRED");
			c.getPA().removeAllWindows();
			break;
		}
		BankPin pin = c.getBankPin();
		if (c.dialogueAction == 137) {
			pin = c.getBankPin();
			if (!pin.getPin().isEmpty()) {
				c.sendMessage("You already have a bank pin.");
				c.getPA().removeAllWindows();
			} else {
				pin.open(1);
			}
			return;
		}
		if (c.dialogueAction == 126) {
			if (Boundary.isIn(c, Boundary.VARROCK_BOUNDARY)) {
				c.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.TELEPORT_ESSENCE_VAR);
			}
			if (Boundary.isIn(c, Boundary.ARDOUGNE_BOUNDARY)) {
				c.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.TELEPORT_ESSENCE_ARD);
			}
			if (Boundary.isIn(c, Boundary.FALADOR_BOUNDARY)) {
				c.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.TELEPORT_ESSENCE_FAL);
			}
			c.getPA().startTeleport(2929, 4813, 0, "modern", false);
			c.dialogueAction = -1;
			c.teleAction = -1;
			return;
		}
		switch (c.teleAction) {
		case 2:
			c.getPA().spellTeleport(1663, 3527, 0, false);
			c.teleAction = -1;
			break;
		}
		if (c.dialogueAction == 100) {
			c.getDH().sendDialogues(545, 315);
			return;
		}
		if (c.dialogueAction == 2245) {
			c.getPA().startTeleport(3230, 2915, 0, "modern", false);
			c.sendMessage("High Priest teleported you to @red@Desert Pyramid@bla@.");
			c.getPA().closeAllWindows();
		}
		if (c.dialogueAction == 508) {
			c.getDH().sendDialogues(1027, 925);
			return;
		}
		if (c.teleAction == 2) {
			// Tav
			c.getPA().spellTeleport(1663, 3527, 0, false);
		}
		if (c.dialogueAction == 502) {
			c.getDH().sendDialogues(1027, 925);
			return;
		}
		if (c.teleAction == 200) {
			c.getPA().spellTeleport(3365, 3266, 0, false);

		}
		if (c.doricOption) {
			c.getDH().sendDialogues(303, 284);
			c.doricOption = false;
		}
	}

	/*
	 * Handles all 3rd options on 'Three option' dialogues.
	 */
	public static void handleOption3(Player c) {
		switch (c.dialogueAction) {
			case 903:
				c.getPA().closeAllWindows();
				c.toReplace = c.replaceWith = null;
				break;
			case 745:

				break;
		case 265:
			c.getPA().removeAllWindows();
			break;
		case 809: // Withdraw
			// TODO: withdraw all
			break;
		case 811: // Deposit
			// TODO: withdraw all
			break;
		case 130135:
			c.currentExchangeItemAmount = 1;
			c.sendMessage("You decide to not destroy your item for points.");
			c.getPA().closeAllWindows();
			if (Boundary.isIn(c, Boundary.EDGEVILLE_PERIMETER)) {
				c.getItems().sendItemContainer(33403, Lists.newArrayList(new GameItem(4653, 1)));
				c.getPA().sendInterfaceSet(33400, 33404);
				c.getItems().sendInventoryInterface(33405);
				c.getPA().sendFrame126("@gre@" + c.exchangePoints, 33410);
				c.getPA().sendFrame126("@red@0", 33409);
			} else {

			}
			break;
		case 806:
			c.getDH().sendDialogues(807, 6773);
			break;
		case 71: // Bargain cape
			c.getDH().sendDialogues(72, 2180);
			break;
		case 55:
			c.getCT().seas("THOUSAND");
			c.getPA().removeAllWindows();
			break;
		case 56:
			c.getCT().swamp("THOUSAND");
			c.getPA().removeAllWindows();
			break;

		case 126:
			if (c.dialogueAction == 126) {
				if (c.getItems().getItemCount(5509, true) == 1) {
					c.getDH().sendNpcChat("You already seem to have a pouch.");
				} else {
					c.getItems().addItem(5509, 1);
					c.getDH().sendItemStatement("The mage hands you a pouch", 5509);
					c.sendMessage("[Rc Pouch] Kill npcs with the pouch in inventory to upgrade it! 1\100 chance");
				}
			}
			break;
		}
		if (c.dialogueAction == 137) {
			c.getPA().removeAllWindows();
			return;
		}
		switch (c.teleAction) {
		case 2:
			c.getPA().spellTeleport(1262, 3501, 0, false);
			return;
		}
		if (c.dialogueAction == 14400 || c.dialogueAction == 100) {
			c.getPA().closeAllWindows();
		}
		if (c.dialogueAction == 2245) {
			c.getPA().closeAllWindows();
		}
		if (c.dialogueAction == 508) {
			c.nextChat = 0;
			c.getPA().closeAllWindows();
		}
		if (c.dialogueAction == 502 || c.dialogueAction == 1428) {
			c.nextChat = 0;
			c.getPA().closeAllWindows();
		}
		if (c.teleAction == 2) {
			c.getPA().spellTeleport(1262, 3501, 0, false);
		}
		if (c.dialogueAction == 251) {
			c.getDH().sendDialogues(1015, 394);
		}
		if (c.teleAction == 200) {
			c.getPA().spellTeleport(2439, 5169, 0, false);
			c.sendMessage("Use the cave entrance to start.");
		}
		if (c.doricOption) {
			c.getDH().sendDialogues(299, 284);
		}
	}

}
