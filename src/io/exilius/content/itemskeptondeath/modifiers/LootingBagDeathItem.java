package io.exilius.content.itemskeptondeath.modifiers;

import io.exilius.content.itemskeptondeath.DeathItemModifier;
import io.exilius.content.lootbag.LootingBag;
import io.exilius.content.lootbag.LootingBagItem;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LootingBagDeathItem implements DeathItemModifier {
    @Override
    public Set<Integer> getItemIds() {
        return Set.of(LootingBag.LOOTING_BAG, LootingBag.LOOTING_BAG_OPEN);
    }

    @Override
    public void modify(Player player, GameItem gameItem, boolean kept, List<GameItem> keptItems, List<GameItem> lostItems) {
        Iterator<LootingBagItem> iterator = player.getLootingBag().getLootingBagContainer().items.iterator();
        while (iterator.hasNext()) {
            LootingBagItem item = iterator.next();
            iterator.remove();
            if (item == null || item.getId() <= 0 || item.getAmount() <= 0)
                continue;
            lostItems.add(item);
        }
    }
}
