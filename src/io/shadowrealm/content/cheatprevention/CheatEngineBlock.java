package io.shadowrealm.content.cheatprevention;

import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.discord.Discord;

public class CheatEngineBlock {
	
	public static boolean tradingPostAlert(Player c) {
		if ((!Boundary.isIn(c, Boundary.EDGE_TRADING_AREA) && !Boundary.isIn(c, Boundary.SKILLING_ISLAND_BANK)) || Boundary.isIn(c, Boundary.OUTLAST_HUT)) {
			Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Trading post.");
			Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Trading post.");
			Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Trading post.");
			Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Trading post.");
			if(c.getLoginName().equalsIgnoreCase("swoc")){
				return false;
			}
			Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the @red@trading post!");
			Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the @red@trading post!");

			if(!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				c.setTeleportToX(2086);
				c.setTeleportToY(4466);
				c.heightLevel = 0;
				c.jailEnd = 214700000;
				c.sendMessage("You have been jailed for using a cheat engine, please speak to staff.");
				c.sendMessage("If this was a mistake please recall what exactly you did to get here.");
				c.getPA().closeAllWindows();
			}
			return true;
		} else {
			Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");
			Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");
			return false;
		}
	}


		public static boolean BankAlert(Player c) {
			if ((!Boundary.isIn(c, Boundary.EDGE_TRADING_AREA) && !Boundary.isIn(c, Boundary.SKILLING_ISLAND_BANK)) || Boundary.isIn(c, Boundary.OUTLAST_HUT)) {
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Bank.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Bank.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Bank.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Bank.");
				if(c.getLoginName().equalsIgnoreCase("swoc")){
					return false;
				}
				Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the @red@Bank!");
				Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the @red@Bank!");
				if(!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
					c.setTeleportToX(2086);
					c.setTeleportToY(4466);
					c.heightLevel = 0;
					c.jailEnd = 214700000;
					c.sendMessage("You have been jailed for using a cheat engine, please speak to staff.");
					c.sendMessage("If this was a mistake please recall what exactly you did to get here.");
					c.getPA().closeAllWindows();
				}
				return true;
			} else {
				Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");
				Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");

				return false;
			}
			}
		
		public static boolean PresetAlert(Player c) {
			if ((!Boundary.isIn(c, Boundary.EDGE_TRADING_AREA) && !Boundary.isIn(c, Boundary.SKILLING_ISLAND_BANK)) || Boundary.isIn(c, Boundary.OUTLAST_HUT)) {
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Presets.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Presets.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Presets.");
				Misc.println("" + c.getDisplayName() + " is trying to use a cheatengine to open the Presets.");
				if(c.getLoginName().equalsIgnoreCase("swoc")){
					return false;
				}
				Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " @blu@is using a cheat engine for the @red@Presets!");
				Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " @blu@is using a cheat engine for the @red@Presets!");
//				TourneyManager.getSingleton().leaveLobby(c, false);
//				c.setTeleportToX(2086);
//				c.setTeleportToY(4466);
//				c.heightLevel = 0;
//				c.jailEnd = 214700000;
//				c.sendMessage("You have been jailed for using a cheat engine, please speak to staff.");
//				c.sendMessage("If this was a mistake please recall what exactly you did to get here.");
//				c.getPA().closeAllWindows();
				return true;
			} else {
				Discord.writeServerSyncMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");
				Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " triggered trading post in edge but no jail.");
				return false;
			}
		}
		public static boolean DonatorBoxAlert(Player c) {
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the donator boxes.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the donator boxes.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the donator boxes.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the donator boxes.");
			if(c.getLoginName().equalsIgnoreCase("swoc")){
				return false;
			}
			Discord.writeServerSyncMessage("[CHEAT ENGINE] "+ c.getDisplayName() +" is using a cheat engine for the @red@donator boxes!");
			Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the @red@donator boxes!");
			if(!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				c.setTeleportToX(2086);
				c.setTeleportToY(4466);
				c.heightLevel = 0;
				c.jailEnd = 214700000;
				c.sendMessage("You have been jailed for using a cheat engine, please speak to staff.");
				c.sendMessage("If this was a mistake please recall what exactly you did to get here.");
				c.getPA().closeAllWindows();
			}
			return true;
		}
		public static boolean ExperienceAbuseAlert(Player c) {
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the lamps.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the lamps.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the lamps.");
			Misc.println(""+ c.getDisplayName() +" is trying to use a cheatengine to open the lamps.");
			if(c.getLoginName().equalsIgnoreCase("swoc")){
				return false;
			}
			Discord.writeServerSyncMessage("[CHEAT ENGINE] "+ c.getDisplayName() +" is using a cheat engine for the lamps!");
			Discord.writeCheatEngineMessage("[CHEAT ENGINE] " + c.getDisplayName() + " is using a cheat engine for the lamps!");
			if(!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				c.setTeleportToX(2086);
				c.setTeleportToY(4466);
				c.heightLevel = 0;
				c.jailEnd = 214700000;
				c.sendMessage("You have been jailed for using a cheat engine, please speak to staff.");
				c.sendMessage("If this was a mistake please recall what exactly you did to get here.");
				c.getPA().closeAllWindows();
			}
			return true;
		}

}