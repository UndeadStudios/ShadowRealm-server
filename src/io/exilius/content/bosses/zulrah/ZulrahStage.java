package io.exilius.content.bosses.zulrah;

import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.entity.player.Player;

public abstract class ZulrahStage extends CycleEvent {

	protected Zulrah zulrah;

	protected Player player;

	public ZulrahStage(Zulrah zulrah, Player player) {
		this.zulrah = zulrah;
		this.player = player;
	}

}
