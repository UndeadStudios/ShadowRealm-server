package io.shadowrealm.model.items;

import io.shadowrealm.model.definitions.ItemDef;

public interface ItemInterface {

    default ItemDef getDef() {
        return ItemDef.forId(getId());
    }

    int getId();

    int getAmount();

}
