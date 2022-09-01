package io.exilius.util.logging.player;

import java.util.Set;

import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.entity.player.Player;
import io.exilius.util.logging.PlayerLog;

public class ClickObjectLog extends PlayerLog {

    private final WorldObject globalObject;
    private final int option;

    public ClickObjectLog(Player player, WorldObject globalObject, int option) {
        super(player);
        this.globalObject = globalObject;
        this.option = option;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("objects_clicked");
    }

    @Override
    public String getLoggedMessage() {
        return "Clicked object " + globalObject + " option " + option;
    }
}
