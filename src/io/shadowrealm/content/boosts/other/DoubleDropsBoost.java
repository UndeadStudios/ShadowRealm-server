package io.shadowrealm.content.boosts.other;

import io.shadowrealm.Configuration;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class DoubleDropsBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+100% Drop Rate (" + Misc.cyclesToDottedTime((int) Configuration.DOUBLE_DROPS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Configuration.DOUBLE_DROPS_TIMER > 0;
    }
}
