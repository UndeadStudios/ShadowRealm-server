package io.shadowrealm.content.combat.effects.damageeffect.impl.amuletofthedamned.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.combat.effects.damageeffect.impl.amuletofthedamned.AmuletOfTheDamnedEffect;
import io.shadowrealm.content.items.Degrade;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.EquipmentSet;
import io.shadowrealm.util.Misc;

/**
 * @author Arthur Behesnilian 12:42 PM
 */
public class DharokEffect implements AmuletOfTheDamnedEffect {

    /**
     * The singleton instance of the Amulet of the damned effect for Dharok
     */
    public static final AmuletOfTheDamnedEffect INSTANCE = new DharokEffect();

    @Override
    public boolean hasExtraRequirement(Player player) {
        return EquipmentSet.DHAROK.isWearingBarrows(player)
                && Misc.isLucky(25);
    }

    @Override
    public void useEffect(Player player, Entity other, Damage damage) {
        double damageAmount = (double) damage.getAmount();
        int damageToDeal = (int) Math.floor(damageAmount * 0.15);
        if (damageToDeal < 1) {
            return;
        }

        other.appendDamage(player, damageToDeal, Hitmark.HIT);
        Degrade.degrade(player, Degrade.DegradableItem.AMULETS_OF_THE_DAMNED);
    }
}
