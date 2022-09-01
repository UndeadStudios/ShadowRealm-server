package io.exilius.content.miniquests;

import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Player;

public class MageArena {

	Player player;

	public MageArena(Player player) {
		this.player = player;
	}

	public void start() {
		player.getPA().removeAllWindows();
		NPC kolodion = NPCHandler.getNpc(1603);
		kolodion.facePlayer(player.getIndex());
		kolodion.startAnimation(811);
		player.getPA().startTeleport(3105, 3934, 0, "modern", false);
		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				NPCSpawning.spawnNpcOld(player, 1605, 3106, 3934, 0, 1, 3, 17, 70, 60, true, true);
				container.stop();
			}
		}, 8);
	}

}
