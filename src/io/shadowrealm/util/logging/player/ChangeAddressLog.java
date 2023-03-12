package io.shadowrealm.util.logging.player;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.Set;

public class ChangeAddressLog extends PlayerLog {

    private final String type;
    private final String oldAddress;
    private final String newAddress;

    public ChangeAddressLog(Player player, String type, String oldAddress, String newAddress) {
        super(player);
        this.type = type;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("changed_address");
    }

    @Override
    public String getLoggedMessage() {
        return String.format("Changed %s address from '%s' to '%s'.", type, oldAddress, newAddress);
    }
}
