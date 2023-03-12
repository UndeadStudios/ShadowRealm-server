package io.shadowrealm.util.logging.player;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.logging.PlayerLog;

import java.util.List;
import java.util.Set;

public class DeathItemsKept extends PlayerLog {

    private final List<GameItem> kept;
    private final Position position;


    public DeathItemsKept(Player player, List<GameItem> kept) {
        super(player);
        this.kept = kept;
        this.position = player.getPosition();
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("unsafe_death");
    }

    @Override
    public String getLoggedMessage() {
        return Misc.replaceBracketsWithArguments("{} kept {}", position, kept);
    }
}
