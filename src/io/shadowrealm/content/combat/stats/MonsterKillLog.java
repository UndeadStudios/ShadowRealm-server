package io.shadowrealm.content.combat.stats;

import java.util.List;

import com.google.common.collect.Lists;
import io.shadowrealm.model.entity.player.Player;
import org.apache.commons.lang3.text.WordUtils;

public class MonsterKillLog {

    private static final int INTERFACE_ID = 34_430;
    private static final int NAMES_CONTAINER = 34_436;
    private static final int KILLS_CONTAINER = 34_438;
    private static final int KILL_TIME_CONTAINER = 34_440;

    public static void openInterface(Player player) {
        List<String> names = Lists.newArrayList();
        List<String> kills = Lists.newArrayList();
        List<String> times = Lists.newArrayList();

        for (TrackedMonster monster : TrackedMonster.getTrackedMonsterList()) {
            names.add(WordUtils.capitalize(monster.getName()));
            kills.add(String.valueOf(player.getNpcDeathTracker().getKc(monster.getName())));
            if (monster.isTrackKillTime()) {
                times.add(player.getBossTimers().getPersonalBest(monster.getName()));
            } else {
                times.add("N/A");
            }
        }

        player.getPA().sendStringContainer(NAMES_CONTAINER, names);
        player.getPA().sendStringContainer(KILLS_CONTAINER, kills);
        player.getPA().sendStringContainer(KILL_TIME_CONTAINER, times);
        player.getPA().showInterface(INTERFACE_ID);
    }
}
