package io.shadowrealm.content.combat.effects.special;

import io.shadowrealm.model.entity.player.Player;

/**
 * @author Arthur Behesnilian 9:09 PM
 */
public interface SpecialEffect {

    boolean activateSpecialEffect(Player player, Object... args);

}
