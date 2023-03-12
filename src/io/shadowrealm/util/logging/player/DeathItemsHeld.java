package io.shadowrealm.util.logging.player;

import io.shadowrealm.model.SlottedItem;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.List;
import java.util.Set;

public class DeathItemsHeld extends PlayerLog {

    private final List<SlottedItem> inventory;
    private final List<SlottedItem> equipment;
    private final Position position;

    public DeathItemsHeld(Player player, List<SlottedItem> inventory, List<SlottedItem> equipment) {
        super(player);
        this.inventory = inventory;
        this.equipment = equipment;
        this.position = player.getPosition();
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("unsafe_death");
    }

    @Override
    public String getLoggedMessage() {
        return Misc.replaceBracketsWithArguments("{} held on death inventory={}, equipment={}", position, inventory, equipment);
    }
}
