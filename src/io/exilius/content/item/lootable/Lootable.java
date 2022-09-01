package io.exilius.content.item.lootable;

import java.util.List;
import java.util.Map;

import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

public interface Lootable {

    Map<LootRarity, List<GameItem>> getLoot();

    void roll(Player player);

}
