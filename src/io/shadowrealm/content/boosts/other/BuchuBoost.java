package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

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
