package io.shadowrealm.content.keyboard_actions;

import io.shadowrealm.model.entity.player.Player;

@FunctionalInterface
public interface KeyboardStrategy {
    void execute(Player player);
}
