package io.exilius.model;

import io.exilius.model.entity.player.Player;

public interface AmountInput {
    void handle(Player player, int amount);
}
