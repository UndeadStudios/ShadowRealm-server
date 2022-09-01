package io.exilius.content.boosts.xp;

import io.exilius.content.boosts.PlayerSkillWrapper;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.util.Misc;

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
