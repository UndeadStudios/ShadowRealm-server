package io.shadowrealm.content.boosts.xp;

import io.shadowrealm.content.bonus.DoubleExperience;
import io.shadowrealm.content.boosts.PlayerSkillWrapper;

public class BonusWeekendBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP Bonus Weekend";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return DoubleExperience.isDoubleExperience();
    }
}
