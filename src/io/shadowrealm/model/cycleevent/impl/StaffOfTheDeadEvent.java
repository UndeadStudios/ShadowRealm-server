package io.shadowrealm.model.cycleevent.impl;

import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

public class StaffOfTheDeadEvent extends Event<Player> {

	public StaffOfTheDeadEvent(Player attachment) {
		super("staff_of_the_dead", attachment, 1);
	}

	@Override
	public void execute() {
		if (plr == null || plr.isDisconnected()) {
			super.stop();
			return;
		}
		if (plr.playerEquipment[Player.playerWeapon] != 11791 && plr.playerEquipment[Player.playerWeapon] != 12904 && plr.playerEquipment[Player.playerWeapon] != 22296) {
			super.stop();
			return;
		}
		if (super.getElapsedTicks() > 100) {
			super.stop();
			return;
		}
	}

}
