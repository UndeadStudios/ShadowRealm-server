package io.shadowrealm.model;

import io.shadowrealm.model.entity.player.Player;

public interface AmountInput {
    void handle(Player player, int amount);
}
