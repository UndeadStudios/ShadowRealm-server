package io.shadowrealm.util.logging.player;

import io.shadowrealm.content.minigames.tob.instance.TobInstance;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.Set;
import java.util.stream.Collectors;

public class DiedAtTBDLog extends PlayerLog {

    private final TobInstance instance;

    public DiedAtTBDLog(Player player, TobInstance instance) {
        super(player);
        this.instance = instance;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("died_at_tob");
    }

    @Override
    public String getLoggedMessage() {
        String players = "";
        if (instance != null) {
            players = instance.getPlayers().stream().map(Player::getLoginNameLower).collect(Collectors.joining(", "));
        }
        return "Died at tob with " + players;
    }
}
