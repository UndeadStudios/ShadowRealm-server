package io.shadowrealm.content.skills.firemake;

import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class Burner extends Event<Player> {

    private LogData log = null;

    public Burner(Player player, LogData log) {
        super("skilling", player, (Boundary.isIn(player, Boundary.DONATOR_ZONE) ? 1 : 3));
        this.log = log;
    }

    @Override
    public void execute() {
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }

        if (log == null) {
            stop();
            return;
        }

      /*  if (Misc.random(300) == 0 && plr.getInterfaceEvent().isExecutable()) {
            plr.getInterfaceEvent().execute();
            stop();
            return;
        }*/

        double osrsExperience = 0;

        if (!plr.getItems().playerHasItem(log.getlogId())) {
            plr.sendMessage("You do not have anymore of this log.");
            stop();
            return;
        }

        plr.getItems().deleteItem(log.getlogId(), 1);
        Achievements.increase(plr, AchievementType.FIRE, 1);
        osrsExperience = log.getExperience() + log.getExperience() / 10;

        plr.getPA().addSkillXPMultiplied((int) osrsExperience * 2, Skill.FIREMAKING.getId(), true);

    }
}
