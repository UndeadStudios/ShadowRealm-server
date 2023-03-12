package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.wogw.Wogw;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class WogwPcPointBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+5 PC Points (" + Misc.cyclesToDottedTime((int) Wogw.PC_POINTS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Wogw.PC_POINTS_TIMER > 0;
    }
}
