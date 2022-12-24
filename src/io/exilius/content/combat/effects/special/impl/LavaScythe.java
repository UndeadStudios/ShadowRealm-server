package io.exilius.content.combat.effects.special.impl;

import io.exilius.Server;
import io.exilius.content.combat.effects.special.SpecialEffect;
import io.exilius.model.Direction;
import io.exilius.model.Graphic;
import io.exilius.model.StillGraphic;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;

/**
 * @author Arthur Behesnilian 9:11 PM
 */
public class LavaScythe implements SpecialEffect {

    public static LavaScythe SCYTHE_EFFECT = new LavaScythe();

    public static boolean usingScythe(Player player) {
        return player.getItems().isWearingItem(29499, Player.playerWeapon);
    }

    @Override
    public boolean activateSpecialEffect(Player player, Object... args) {
        boolean usingScythe = player.getItems().isWearingItem(29499, Player.playerWeapon);

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
