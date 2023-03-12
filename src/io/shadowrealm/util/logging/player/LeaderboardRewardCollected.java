package io.shadowrealm.util.logging.player;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.Set;

public class LeaderboardRewardCollected extends PlayerLog {

    private final GameItem gameItem;

    public LeaderboardRewardCollected(Player player, GameItem gameItem) {
        super(player);
        this.gameItem = gameItem;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("leaderboard_rewards");
    }

    @Override
    public String getLoggedMessage() {
        return "Collected " + gameItem;
    }
}
