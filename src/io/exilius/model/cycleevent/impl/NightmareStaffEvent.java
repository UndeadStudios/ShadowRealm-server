package io.exilius.model.cycleevent.impl;

import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Player;

public class NightmareStaffEvent extends Event<Player> {

    public NightmareStaffEvent(Player attachment) {
        super("volatile_nightmare_staff", attachment, 1);
    }

    @Override
    public void execute() {
        if (plr == null || plr.isDisconnected()) {
            super.stop();
            return;
        }
        if (plr.playerEquipment[Player.playerWeapon] != 24424) {
            super.stop();
            return;
        }
        if (super.getElapsedTicks() > 100) {
            super.stop();
            return;
        }
    }
}
