package io.shadowrealm.model.entity.player.packets.objectoptions;

import io.shadowrealm.Server;
import io.shadowrealm.content.dialogue.impl.OutlastLeaderboard;
import io.shadowrealm.content.skills.construction.Construction;
import io.shadowrealm.content.skills.woodcutting.Jungle;
import io.shadowrealm.content.skills.woodcutting.JungleWoodcutting;
import io.shadowrealm.content.skills.woodcutting.Tree;
import io.shadowrealm.content.skills.woodcutting.Woodcutting;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;

public class ObjectOptionFour {
	
	public static void handleOption(final Player c, int objectType, int obX, int obY) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		c.clickObjectType = 0;
		
		if (c.getRights().isOrInherits(Right.OWNER) && c.debugMessage)
			c.sendMessage("Clicked Object Option 4:  "+objectType+"");
		Jungle tree = Jungle.forObject(objectType);

		if (tree != null) {
			JungleWoodcutting.getInstance().chop(c, objectType, obX, obY);
			return;
		}
		if (OutlastLeaderboard.handleInteraction(c, objectType, 4))
			return;
		switch (objectType) {
		case 31858:
		case 29150:
			c.setSidebarInterface(6, 938);
			c.playerMagicBook = 0;
			c.sendMessage("You feel a drain on your memory.");
			break;
		case 8356://streehosidius
			c.getPA().movePlayer(1679, 3541, 0);
			break;
		}
	}

}
