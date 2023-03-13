package io.shadowrealm.content.bosses.nex.attacks;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.util.List;

public class ShadowSmash {
    public ShadowSmash(List<Player> targets) {
        ShadowSmash(targets);
    }

    void ShadowSmash(List<Player> targets) {
        for (Player player : targets) {
            if (player != null) {
                player.gfx0(381);
                int dmg = Misc.random(50);
                if (player.protectingMagic()) {
                    dmg = (dmg / 2);
                }

                player.appendDamage(dmg, (dmg > 0 ? Hitmark.HIT : Hitmark.MISS));
            }
        }
    }
}