package io.shadowrealm.model.shops;

import io.shadowrealm.model.items.GameItem;

public class ShopItem extends GameItem {

    private int price;

    public ShopItem(int id, int amount, int price) {
        super(id, amount);
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
