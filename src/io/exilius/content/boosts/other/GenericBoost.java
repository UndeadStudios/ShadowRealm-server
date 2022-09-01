package io.exilius.content.boosts.other;

import io.exilius.content.boosts.BoostType;
import io.exilius.content.boosts.Booster;
import io.exilius.model.entity.player.Player;

public abstract class GenericBoost implements Booster<Player> {
    @Override
    public BoostType getType() {
        return BoostType.GENERIC;
    }
}
