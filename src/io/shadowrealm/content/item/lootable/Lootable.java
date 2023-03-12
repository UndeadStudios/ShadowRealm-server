package io.shadowrealm.content.item.lootable;

import java.util.List;
import java.util.Map;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;

public interface Lootable {

    Map<LootRarity, List<GameItem>> getLoot();

    void roll(Player player);

}
