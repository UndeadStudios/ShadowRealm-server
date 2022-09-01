package io.exilius.model.multiplayersession.flowerpoker;

import io.exilius.model.Items;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.lock.CompleteLock;

public class FlowerPokerLock extends CompleteLock {
    @Override
    public boolean cannotClickItem(Player player, int itemId) {
        if (itemId == Items.MITHRIL_SEEDS)
            return false;
        return true;
    }
}
