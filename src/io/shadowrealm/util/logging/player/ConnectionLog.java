package io.shadowrealm.util.logging.player;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.Set;

public class ConnectionLog extends PlayerLog {

    private final boolean login;
    private final String cause;

    public ConnectionLog(Player player, boolean login, String cause) {
        super(player);
        this.login = login;
        this.cause = cause;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("connection");
    }

    @Override
    public String getLoggedMessage() {
        return login ? "Logged in" : "Logged out" + (cause != null ? ", cause: " + cause : "");
    }
}
