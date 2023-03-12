package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.wogw.Wogw;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class WogwDropBoost extends GenericBoost {
    @Override
    public String getDescription() {
        return "+20% Drop Rate (" + Misc.cyclesToDottedTime((int) Wogw._20_PERCENT_DROP_RATE_TIMER) + ")";
    }

    @Override
    public boolean applied(Player player) {
        return Wogw._20_PERCENT_DROP_RATE_TIMER > 0;
    }
}
