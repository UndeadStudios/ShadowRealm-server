package io.shadowrealm.util.logging.global;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.GlobalLog;

import java.util.Set;

public class LoginLog extends GlobalLog {

    private final String message;
    private final String player;

    public LoginLog(String message, Player player) {
        this.message = message;
        this.player = player.toString();
    }

    @Override
    public String getLoggedMessage() {
        return message + ": " + player;
    }

    @Override
    public Set<String> getFileNames() {
        return Set.of("login_success");
    }
}