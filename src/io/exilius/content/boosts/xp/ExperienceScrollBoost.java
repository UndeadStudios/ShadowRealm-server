package io.exilius.content.boosts.xp;

import io.exilius.content.boosts.PlayerSkillWrapper;

public class ExperienceScrollBoost extends ExperienceBooster {
    @Override
    public String getDescription() {
        return "Bonus +50% XP";
    }

    @Override
    public boolean applied(PlayerSkillWrapper p) {
        return p.getPlayer().bonusXpTime > 0 || p.getPlayer().xpScroll;
    }
}
