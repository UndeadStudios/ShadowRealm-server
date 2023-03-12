package io.shadowrealm.content.skills.construction;


import io.shadowrealm.content.skills.construction.rooms.*;
import io.shadowrealm.model.entity.player.Player;

import java.awt.*;

public class RoomInterface {

	public  static void openInterface(Player c){
		c.getPA().showInterface(24595);
		if(c.getItems().playerHasItem(995, 1000)) {
			c.getPA().sendInterfaceTextColor(255 << 10 | 255 << 5 | 0, 24734);
		} else {
			c.getPA().sendInterfaceTextColor(255 << 10 | 0 << 5 | 0, 24734);
		}
		if(c.getItems().playerHasItem(995, 1000)) {
			c.getPA().sendInterfaceTextColor(255 << 10 | 255 << 5 | 0, 24735);
		} else {
			c.getPA().sendInterfaceTextColor(255 << 10 | 255 << 5 | 0, 24735);
		}
		if(c.getItems().playerHasItem(995, 5000)) {
			c.getPA().sendInterfaceTextColor(255 << 10 | 255 << 5 | 0, 24736);
		} else {
			c.getPA().sendInterfaceTextColor(255 << 10 | 0 << 5 | 0, 24736);
		}
	}
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
			c.replaceWith = new Garden2();
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
			case 96190:
				if(c.playerLevel[22] >= 40) {
					if(c.getItems().playerHasItem(995, 50000)) {
						c.getItems().deleteItem(995, 50000);
						c.replaceWith = new Study();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 50000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 40 to make this.");
				}
				break;
			case 96191:
				if(c.playerLevel[22] >= 42) {
					if(c.getItems().playerHasItem(995, 50000)) {
						c.getItems().deleteItem(995, 50000);
						c.replaceWith = new CostumeRoom();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 50000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 42 to make this.");
				}
				break;
			case 96192:
				if(c.playerLevel[22] >= 45) {
					if(c.getItems().playerHasItem(995, 50000)) {
						c.getItems().deleteItem(995, 50000);
						c.replaceWith = new Chapel();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 50000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 45 to make this.");
				}
				break;
			case 96193:
				if(c.playerLevel[22] >= 50) {
					if(c.getItems().playerHasItem(995, 100000)) {
						c.getItems().deleteItem(995, 100000);
						c.replaceWith = new PortalChamber();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 100k coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 50 to make this.");
				}
				break;
			case 96194:
				if(c.playerLevel[22] >= 55) {
					if(c.getItems().playerHasItem(995, 75000)) {
						c.getItems().deleteItem(995, 75000);
						c.replaceWith = new PortalChamber();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 75000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 55 to make this.");
				}
				break;
			case 96195:
				if(c.playerLevel[22] >= 60) {
					if(c.getItems().playerHasItem(995, 75000)) {
						c.getItems().deleteItem(995, 75000);
						c.replaceWith = new Throne();
						c.getDH().sendDialogues(907, 0);
					} else {
						c.sendMessage("You need 150000 coins to do this.");
					}
				} else {
					c.sendMessage("You need a construction level of 60 to make this.");
				}
				break;
		}
		return false;
	}
}
