package io.exilius.content.boosts.other;

import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class KronosBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Raids Keys (" + Misc.cyclesToDottedTime((int) Hespori.KRONOS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.KRONOS_TIMER > 0;
    }
}
