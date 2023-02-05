package io.exilius.content.combat.specials.impl;

import io.exilius.content.combat.Damage;
import io.exilius.content.combat.Hitmark;
import io.exilius.content.combat.specials.Special;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.player.Player;

public class AncientGodsword extends Special {

    public AncientGodsword() {
        super(5.0, 1.75, 1.375, new int[] { 26233 });
    }

    @Override
    public void activate(Player player, Entity target, Damage damage) {
        player.startAnimation(9171);
        player.gfx0(1996);
    }

    @Override
    public void hit(Player player, Entity target, Damage damage) {
        if(player.goodDistance(target.getX(), target.getY(), player.getX(), player.getY(), 5)) {
            target.appendDamage(25, Hitmark.HIT);
        }
        player.getPA().createPlayersStillGfx(2003, target.getX(), target.getY(), target.getHeight(),  -1);
    }

}

