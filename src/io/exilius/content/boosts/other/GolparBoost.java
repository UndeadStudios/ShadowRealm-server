package io.exilius.content.boosts.other;

import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class GolparBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Bonus Loot (" + Misc.cyclesToDottedTime((int) Hespori.GOLPAR_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.GOLPAR_TIMER > 0;
    }
}
