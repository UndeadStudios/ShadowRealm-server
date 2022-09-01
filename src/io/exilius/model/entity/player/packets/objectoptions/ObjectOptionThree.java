package io.exilius.model.entity.player.packets.objectoptions;

import io.exilius.Server;
import io.exilius.content.dialogue.impl.OutlastLeaderboard;
import io.exilius.content.skills.agility.AgilityHandler;
import io.exilius.content.tradingpost.Listing;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Right;

/*
 * @author Matt
 * Handles all 3rd options for objects.
 */

public class ObjectOptionThree {

	public static void handleOption(final Player c, int objectType, int obX, int obY) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		c.clickObjectType = 0;
		// c.sendMessage("Object type: " + objectType);


		if (c.getRights().isOrInherits(Right.OWNER) && c.debugMessage)
			c.sendMessage("Clicked Object Option 3:  "+objectType+"");

		if (OutlastLeaderboard.handleInteraction(c, objectType, 3))
			return;

		switch (objectType) {
		case 31858:
		case 29150:
			c.sendMessage("You switch to the lunar spellbook.");
			c.setSidebarInterface(6, 29999);
			c.playerMagicBook = 2;
			break;

		case 29777:
		case 29734:
		case 10777:
		case 29879:
			c.objectDistance = 4;

			break;

		case 2884:
		case 16684:
		case 16683:
			if (c.absY == 3494 || c.absY == 3495 || c.absY == 3496) {
				AgilityHandler.delayEmote(c, "CLIMB_DOWN", c.getX(), c.getY(), c.getHeight() - 1, 2);
			}
			break;
		case 29333:
			if (c.getMode().isIronmanType()) {
				c.sendMessage("@red@You are not permitted to make use of this.");			}
			Listing.collectMoney(c);
			
			break;

//			case 16682:
//				c.getPA().movePlayer(2532, 3546, 0);
//				break;
//
//			case 42487:
//				c.getPA().movePlayer(2532, 3546, 0);
//				break;

		case 6448:
			if (c.getMode().isIronmanType()) {
				Listing.openPost(c, false);	
			}
			c.sendMessage("@red@You cannot enter the trading post on this mode.");
			break;
		case 8356://streexerics
			c.getPA().movePlayer(1311, 3614, 0);
			break;
		case 7811:
			if (!c.getPosition().inClanWarsSafe()) {
				return;
			}
			c.getDH().sendDialogues(818, 6773);
			break;
		}
	}

}
