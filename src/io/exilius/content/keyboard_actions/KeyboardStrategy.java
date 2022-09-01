package io.exilius.content.keyboard_actions;

import io.exilius.model.entity.player.Player;

@FunctionalInterface
public interface KeyboardStrategy {
    void execute(Player player);
}
