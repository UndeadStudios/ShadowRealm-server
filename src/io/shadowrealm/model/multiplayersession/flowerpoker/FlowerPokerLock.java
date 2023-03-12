package io.shadowrealm.model.multiplayersession.flowerpoker;

import io.shadowrealm.model.Items;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.lock.CompleteLock;

public class FlowerPokerLock extends CompleteLock {
    @Override
    public boolean cannotClickItem(Player player, int itemId) {
        if (itemId == Items.MITHRIL_SEEDS)
            return false;
        return true;
    }
}
