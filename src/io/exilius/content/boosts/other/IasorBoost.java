package io.exilius.content.boosts.other;

import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class IasorBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+10% Drop Rate (" + Misc.cyclesToDottedTime((int) Hespori.IASOR_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.IASOR_TIMER > 0;
    }
}
