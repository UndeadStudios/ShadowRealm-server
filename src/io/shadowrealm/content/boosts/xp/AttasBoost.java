package io.shadowrealm.content.boosts.xp;

import io.shadowrealm.content.boosts.PlayerSkillWrapper;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.util.Misc;

public class AttasBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "+50% XP (" + Misc.cyclesToDottedTime((int) Hespori.ATTAS_TIMER) + ")";
    }

    @Override
    public boolean applied(PlayerSkillWrapper playerSkillWrapper) {
        return Hespori.ATTAS_TIMER > 0;
    }
}
