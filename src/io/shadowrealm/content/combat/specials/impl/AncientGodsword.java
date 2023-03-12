package io.shadowrealm.content.combat.specials.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.combat.specials.Special;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;

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

