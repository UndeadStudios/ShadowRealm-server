package io.exilius.content.bosses.zulrah.impl;

import io.exilius.content.bosses.zulrah.Zulrah;
import io.exilius.content.bosses.zulrah.ZulrahLocation;
import io.exilius.content.bosses.zulrah.ZulrahStage;
import io.exilius.model.CombatType;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.entity.player.Player;

public class MeleeStageSix extends ZulrahStage {

	public MeleeStageSix(Zulrah zulrah, Player player) {
		super(zulrah, player);
	}

	@Override
	public void execute(CycleEventContainer container) {
		if (container.getOwner() == null || zulrah == null || zulrah.getNpc() == null || zulrah.getNpc().isDead() || player == null || player.isDead
				|| zulrah.getInstancedZulrah() == null) {
			container.stop();
			return;
		}
		if (zulrah.getNpc().totalAttacks > 1 && zulrah.getNpc().attackTimer == 9) {
			player.getZulrahEvent().changeStage(7, CombatType.RANGE, ZulrahLocation.EAST);
			zulrah.getNpc().totalAttacks = 0;
			zulrah.getNpc().setFacePlayer(true);
			container.stop();
			return;
		}
	}

}
