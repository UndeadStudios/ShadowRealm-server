package io.shadowrealm.model.shops;

import io.shadowrealm.model.items.NamedItem;
import io.shadowrealm.util.ItemConstants;

public class NamedShopItem extends NamedItem {

    private int price;

    public ShopItem toShopItem(ItemConstants itemConstants) {
        return new ShopItem(getId(itemConstants), getAmount(), price);
    }

    public NamedShopItem() {
    }

    public int getPrice() {
        return price;
    }
}
