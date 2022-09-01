package io.exilius.content.boosts.other;

import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class KeldaBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Larren's Keys (" + Misc.cyclesToDottedTime((int) Hespori.KELDA_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.KELDA_TIMER > 0;
    }
}
