package io.shadowrealm.content.bosses.zulrah;

import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.entity.player.Player;

public abstract class ZulrahStage extends CycleEvent {

	protected Zulrah zulrah;

	protected Player player;

	public ZulrahStage(Zulrah zulrah, Player player) {
		this.zulrah = zulrah;
		this.player = player;
	}

}
