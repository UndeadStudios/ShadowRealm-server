package io.shadowrealm.content.combat.effects.special.impl;

import io.shadowrealm.Server;
import io.shadowrealm.content.combat.effects.special.SpecialEffect;
import io.shadowrealm.model.Direction;
import io.shadowrealm.model.Graphic;
import io.shadowrealm.model.StillGraphic;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;

/**
 * @author Arthur Behesnilian 9:11 PM
 */
public class ScytheOfOsiris implements SpecialEffect {

    public static ScytheOfOsiris SCYTHE_EFFECT = new ScytheOfOsiris();

    public static boolean usingScythe(Player player) {
        return player.getItems().isWearingItem(8817, Player.playerWeapon);
    }

    @Override
    public boolean activateSpecialEffect(Player player, Object... args) {
        boolean usingScythe = player.getItems().isWearingItem(8817, Player.playerWeapon);

        Entity defender = (Entity) args[0];

        if (usingScythe) {
            Position defenderAdjacentPosition = defender.getAdjacentBorderPosition(player.getPosition());
            Position directional = player.getPosition().toDirectional(defenderAdjacentPosition);
            Direction direction = Direction.fromDirectional(directional);
            Position position = player.getPosition().translate(direction);
            int gfx = direction == Direction.NORTH ? 506
                    : direction == Direction.EAST ? 1172
                    : direction == Direction.SOUTH ? 478
                    : 1231;

            Server.playerHandler.sendStillGfx(new StillGraphic(gfx, Graphic.GraphicHeight.HIGH, position), player.getInstance());
        }

        return usingScythe;
    }

}
