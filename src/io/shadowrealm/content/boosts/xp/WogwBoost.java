package io.shadowrealm.content.boosts.xp;

import io.shadowrealm.content.boosts.PlayerSkillWrapper;
import io.shadowrealm.content.wogw.Wogw;
import io.shadowrealm.util.Misc;

public class WogwBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP Rate (" + Misc.cyclesToDottedTime((int) Wogw.EXPERIENCE_TIMER) + ")";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return Wogw.EXPERIENCE_TIMER > 0;
    }
}
