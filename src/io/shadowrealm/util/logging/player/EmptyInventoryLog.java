package io.shadowrealm.util.logging.player;

import java.util.List;
import java.util.Set;

import io.shadowrealm.model.SlottedItem;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.logging.PlayerLog;

public class EmptyInventoryLog extends PlayerLog {

    private final List<SlottedItem> deleted;

    public EmptyInventoryLog(Player player, List<SlottedItem> deleted) {
        super(player);
        this.deleted = deleted;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("item_lost_empty_command", "item_lost");
    }

    @Override
    public String getLoggedMessage() {
        return "Emptied " + deleted;
    }
}
