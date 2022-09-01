package io.exilius.content.boosts.other;

import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class BuchuBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Boss Points (" + Misc.cyclesToDottedTime((int) Hespori.BUCHU_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.BUCHU_TIMER > 0;
    }
}
