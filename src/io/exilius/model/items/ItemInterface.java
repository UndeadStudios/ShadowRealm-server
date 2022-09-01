package io.exilius.model.items;

import io.exilius.model.definitions.ItemDef;

public interface ItemInterface {

    default ItemDef getDef() {
        return ItemDef.forId(getId());
    }

    int getId();

    int getAmount();

}
