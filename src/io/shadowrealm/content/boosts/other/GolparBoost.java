package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

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
