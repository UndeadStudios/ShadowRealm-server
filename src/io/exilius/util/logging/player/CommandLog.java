package io.exilius.util.logging.player;

import java.util.Set;

import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.util.logging.PlayerLog;

public class CommandLog extends PlayerLog {

    private final String command;
    private final Position position;

    public CommandLog(Player player, String command, Position position) {
        super(player);
        this.command = command;
        this.position = position;
    }

    @Override
    public String getLoggedMessage() {
        return username + " typed command " + command + " at X: " + position.getX() + " Y:" + position.getY();
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("commands");
    }
}
