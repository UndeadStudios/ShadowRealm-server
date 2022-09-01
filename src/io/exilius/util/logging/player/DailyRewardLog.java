package io.exilius.util.logging.player;

import java.util.Set;

import io.exilius.model.entity.player.Player;
import io.exilius.model.items.ImmutableItem;
import io.exilius.util.logging.PlayerLog;

public class DailyRewardLog extends PlayerLog {

    private final int day;
    private final ImmutableItem gameItem;

    public DailyRewardLog(Player player, int day, ImmutableItem gameItem) {
        super(player);
        this.day = day;
        this.gameItem = gameItem;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("daily_reward_claims");
    }

    @Override
    public String getLoggedMessage() {
        return "Claimed daily reward " + gameItem + " on day " + day;
    }
}
