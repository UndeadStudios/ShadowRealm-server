package io.exilius.util.logging.player;

import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;
import io.exilius.util.logging.PlayerLog;

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
