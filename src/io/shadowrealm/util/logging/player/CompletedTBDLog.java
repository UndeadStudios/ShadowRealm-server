package io.shadowrealm.util.logging.player;

import io.shadowrealm.content.instances.InstancedArea;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.Set;
import java.util.stream.Collectors;

public class CompletedTBDLog extends PlayerLog {

    private final InstancedArea instance;

    public CompletedTBDLog(Player player, InstancedArea instance) {
        super(player);
        this.instance = instance;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("completed_tob");
    }

    @Override
    public String getLoggedMessage() {
        String players = "";
        if (instance != null) {
            players = instance.getPlayers().stream().map(Player::getLoginNameLower).collect(Collectors.joining(", "));
        }
        return "Completed tob with " + players;
    }
}
