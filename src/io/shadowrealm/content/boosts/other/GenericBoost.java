package io.shadowrealm.content.boosts.other;

import io.shadowrealm.content.boosts.BoostType;
import io.shadowrealm.content.boosts.Booster;
import io.shadowrealm.model.entity.player.Player;

public abstract class GenericBoost implements Booster<Player> {
    @Override
    public BoostType getType() {
        return BoostType.GENERIC;
    }
}
