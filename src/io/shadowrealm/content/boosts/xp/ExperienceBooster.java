package io.shadowrealm.content.boosts.xp;

import io.shadowrealm.content.boosts.BoostType;
import io.shadowrealm.content.boosts.Booster;
import io.shadowrealm.content.boosts.PlayerSkillWrapper;

public abstract class ExperienceBooster implements Booster<PlayerSkillWrapper> {

    @Override
    public BoostType getType() {
        return BoostType.EXPERIENCE;
    }

}
