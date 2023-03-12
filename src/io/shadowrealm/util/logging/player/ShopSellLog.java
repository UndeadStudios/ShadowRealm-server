package io.shadowrealm.util.logging.player;

import java.util.Set;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.util.logging.PlayerLog;

public class ShopSellLog extends PlayerLog {

    private final int shopId;
    private final String shopName;
    private final GameItem gameItem;

    public ShopSellLog(Player player, int shopId, String shopName, GameItem gameItem) {
        super(player);
        this.shopId = shopId;
        this.shopName = shopName;
        this.gameItem = gameItem;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("items_received_shop", "items_received");
    }

    @Override
    public String getLoggedMessage() {
        return "Sold " + gameItem + " from " + shopName + " with id " + shopId;
    }
}
