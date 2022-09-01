package io.exilius.content.boosts.xp;

import io.exilius.content.boosts.BoostType;
import io.exilius.content.boosts.Booster;
import io.exilius.content.boosts.PlayerSkillWrapper;

public abstract class ExperienceBooster implements Booster<PlayerSkillWrapper> {

    @Override
    public BoostType getType() {
        return BoostType.EXPERIENCE;
    }

}
