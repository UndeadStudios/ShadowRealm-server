package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class CelastrusBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "x2 Brimstone Keys (" + Misc.cyclesToDottedTime((int) Hespori.CELASTRUS_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Hespori.CELASTRUS_TIMER > 0;
    }
}
