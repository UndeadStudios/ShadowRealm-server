package io.exilius.content.skills.woodcutting;

import io.exilius.Server;
import io.exilius.model.collisionmap.ObjectDef;
import io.exilius.model.entity.player.Player;

public class JungleWoodcutting {

	private static final JungleWoodcutting INSTANCE = new JungleWoodcutting();

	public void chop(Player player, int objectId, int x, int y) {
		Jungle tree = Jungle.forObject(objectId);
		player.facePosition(x, y);
		if (player.playerLevel[Player.playerWoodcutting] < tree.getLevelRequired()) {
			player.sendMessage("You do not have the woodcutting level required to cut this tree down.");
			return;
		}

		Machete machete = Machete.getBest(player);
		if (machete == null) {
			player.sendMessage("You must have an machete and the level required to cut this tree down.");
			return;
		}
		if (player.getItems().freeSlots() == 0) {
			player.sendMessage("You must have at least one free inventory space to do this.");
			return;
		}
		if (Server.getGlobalObjects().exists(tree.getStumpId(), x, y)) {
			player.sendMessage("This tree has been cut down to a stump, you must wait for it to grow.");
			return;
		}
		player.getPA().stopSkilling();
			player.sendMessage("You swing your machete at the "+ ObjectDef.getObjectDef(objectId).getName().toLowerCase()+".");
		player.startAnimation(machete.getAnimation());
		Server.getEventHandler().submit(new JungleWoodcuttingEvent(player, tree, machete, objectId, x, y));
	}

	public static JungleWoodcutting getInstance() {
		return INSTANCE;
	}

}
