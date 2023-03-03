package io.exilius.model.entity.player.packets.objectoptions;

import io.exilius.Server;
import io.exilius.content.bosses.wintertodt.WintertodtActions;
import io.exilius.content.dialogue.impl.OutlastLeaderboard;
import io.exilius.content.objects.Ladders;
import io.exilius.content.skills.agility.AgilityHandler;
import io.exilius.content.tradingpost.Listing;
import io.exilius.model.collisionmap.ObjectDef;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Right;
import io.exilius.model.world.objects.GlobalObject;

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

		ObjectDef def = ObjectDef.getObjectDef(objectType);
		if((def!=null ? def.name : null)!= null && def.name.toLowerCase().equals("ladder")) {
			if(def.actions[2].toLowerCase().equals("climb-up")) {
				if(obX == 3069 && obY == 10256) { // custom locations
					Ladders.climbLadder(c,3017, 3850, 0);
					return;
				}
				if(obX == 3017 && obY == 10249) { // custom locations
					Ladders.climbLadder(c,3069, 3857, 0);
					return;
				}
				if(c.getY() > 6400) {
					Ladders.climbLadder(c,c.getX(), c.getY()-6400, c.heightLevel);
					return;
				} else {
					Ladders.climbLadder(c,c.absX, c.absY, c.heightLevel+1);
					return;
				}
			}
			if(def.actions[2].toLowerCase().equals("climb-down")) {
				if(obX == 3017 && obY == 3849) { // custom locations
					c.getPA().movePlayer(3069, 10257, 0);
					return;
				}
				if(obX == 3069 && obY == 3856) { // custom locations
					c.getPA().movePlayer(3017, 10248, 0);
					return;
				}
				if(obX == 1570 && obY == 2829 && c.heightLevel == 1) {
					c.getPA().movePlayer(1570, 2830, 0);
					return;
				}
				if(obX == 1560 && obY == 2829 && c.heightLevel == 1) {
					c.getPA().movePlayer(1560, 2830, 0);
					return;
				}
				if(c.getX() < 6400 && (c.heightLevel & 3) == 0) {
					c.getPA().movePlayer(c.getX(), c.getX()+6400, c.heightLevel);
					return;
				} else {
					c.getPA().movePlayer(c.absX, c.absY, c.heightLevel-1);
					return;
				}
			}
		}
		if((def!=null ? def.name : null)!= null && def.name.toLowerCase().equals("staircase")) {
			if(def.actions[2].equals("Climb-up")) {
				if(c.getY() > 6400) {

					c.getPA().movePlayer(c.getX(), c.getY() - 6400, c.heightLevel);
					return;
				} else {
					c.getPA().movePlayer( c.absX, c.absY, c.heightLevel + 1);
					return;
				}

			}
			if(def.actions[2].equals("Climb-down")) {
				if(obX == 3103 && obY == 3159) { // Wizard tower
					c.getPA().movePlayer(3104, 3161, 0);
					return;
				}
				if(obX == 3053 && obY == 3383){
					c.getPA().movePlayer(3065, 9951, 0);
					return;
				}
				if(obX == 3037 && obY == 3383){
					c.getPA().movePlayer(3014, 9951, 0);
					return;
				}
				if(c.getX() < 6400 && (c.heightLevel & 3) == 0) {
					c.getPA().movePlayer(c.getX(), c.getX()+6400, c.heightLevel);
					return;
				} else {
					c.getPA().movePlayer(c.absX, c.absY, c.heightLevel-1);
					return;
				}
			}
		}
		GlobalObject object = new GlobalObject(objectType, obX, obY, c.heightLevel);
		if (c.getRights().isOrInherits(Right.OWNER) && c.debugMessage)
			c.sendMessage("Clicked Object Option 3:  "+objectType+"");

		if (OutlastLeaderboard.handleInteraction(c, objectType, 3))
			return;
		if (WintertodtActions.handleObjects(object, c, 3))
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
