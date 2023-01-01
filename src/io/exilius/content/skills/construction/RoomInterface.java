package io.exilius.content.skills.construction;


import io.exilius.content.skills.construction.rooms.*;
import io.exilius.model.entity.player.Player;

public class RoomInterface {

	public static boolean clickButton(Player c, int actionButtonId) {
		switch (actionButtonId) {
			case 96207:
				c.getPA().closeAllWindows();
				break;
		case 96180:
			if(c.playerLevel[22] >= 1) {
			if(c.getItems().playerHasItem(995, 1000)) {
				c.getItems().deleteItem(995, 1000);
			c.replaceWith = new Parlour();
				c.getDH().sendDialogues(907, 0);
		// c.start(new RoomDialogue());
			} else {
				c.sendMessage("You need 1000 coins to do this.");
			}
			} else {
				c.sendMessage("You need a construction level of 1 to make this.");
			}
		  break;
		case 96181:
			if(c.playerLevel[22] >= 1) {
				if(c.getItems().playerHasItem(995, 1000)) {
					c.getItems().deleteItem(995, 1000);
			c.replaceWith = new Garden();
					c.getDH().sendDialogues(907, 0);
		 //c.start(new RoomDialogue());
				} else {
					c.sendMessage("You need 1000 coins to do this.");
				}
			} else {
				c.sendMessage("You need a construction level of 1 to make this.");
			}
		  break;
		case 96182:
			if(c.playerLevel[22] >= 5) {
				if(c.getItems().playerHasItem(995, 5000)) {
					c.getItems().deleteItem(995, 5000);
					c.replaceWith = new Kitchen();
					c.getDH().sendDialogues(907, 0);
					//c.start(new RoomDialogue());
				} else {
					c.sendMessage("You need 5000 coins to do this.");
				}
			} else {
				c.sendMessage("You need a construction level of 5 to make this.");
			}
		  break;
		case 96183:
			if(c.playerLevel[22] >= 10) {
				if(c.getItems().playerHasItem(995, 5000)) {
					c.getItems().deleteItem(995, 5000);
					c.replaceWith = new Dining();
					c.getDH().sendDialogues(907, 0);
					//c.start(new RoomDialogue());
				} else {
					c.sendMessage("You need 5000 coins to do this.");
				}
			} else {
				c.sendMessage("You need a construction level of 10 to make this.");
			}
		  break;
		case 96184:
			if(c.playerLevel[22] >= 15) {
				if(c.getItems().playerHasItem(995, 10000)) {
					c.getItems().deleteItem(995, 10000);
					c.replaceWith = new Workshop();
					c.getDH().sendDialogues(907, 0);
					//c.start(new RoomDialogue());
				} else {
					c.sendMessage("You need 10000 coins to do this.");
				}
			} else {
				c.sendMessage("You need a construction level of 15 to make this.");
			}
		  break;
		case 96185:
			if(c.playerLevel[22] >= 20) {
				if(c.getItems().playerHasItem(995, 10000)) {
					c.getItems().deleteItem(995, 10000);
					c.replaceWith = new Bedroom();
					c.getDH().sendDialogues(907, 0);
					//c.start(new RoomDialogue());
				} else {
					c.sendMessage("You need 10000 coins to do this.");
				}
			} else {
				c.sendMessage("You need a construction level of 20 to make this.");
			}
		  break;
			case 96186:
				if(c.playerLevel[22] >= 25) {
					if(c.getItems().playerHasItem(995, 15000)) {
						c.getItems().deleteItem(995, 15000);
						c.replaceWith = new SkillHall();
						c.getDH().sendDialogues(907, 0);
						//c.start(new RoomDialogue());
					} else {
						c.sendMessage("You need 15000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 25 to make this.");
				}
				break;
			case 96187:
				if(c.playerLevel[22] >= 30) {
					if(c.getItems().playerHasItem(995, 25000)) {
						c.getItems().deleteItem(995, 25000);
						c.replaceWith = new Game();
						c.getDH().sendDialogues(907, 0);
						//c.start(new RoomDialogue());
					} else {
						c.sendMessage("You need 25000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 30 to make this.");
				}
				break;
			case 96188:
				if(c.playerLevel[22] >= 32) {
					if(c.getItems().playerHasItem(995, 25000)) {
						c.getItems().deleteItem(995, 25000);
						c.replaceWith = new Combat();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 25000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 32 to make this.");
				}
				break;
			case 96189:
				if(c.playerLevel[22] >= 35) {
					if(c.getItems().playerHasItem(995, 25000)) {
						c.getItems().deleteItem(995, 25000);
						c.replaceWith = new QuestHall();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 25000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 35 to make this.");
				}
				break;
		}
		return false;
	}
}
