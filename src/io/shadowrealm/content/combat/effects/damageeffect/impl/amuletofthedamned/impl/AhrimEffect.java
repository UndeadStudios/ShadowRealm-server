package io.shadowrealm.content.combat.effects.damageeffect.impl.amuletofthedamned.impl;

import io.shadowrealm.content.combat.Damage;
import io.shadowrealm.content.combat.effects.damageeffect.impl.amuletofthedamned.AmuletOfTheDamnedEffect;
import io.shadowrealm.content.items.Degrade;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.EquipmentSet;

/**
 * @author Arthur Behesnilian 1:02 PM
 */
public class AhrimEffect implements AmuletOfTheDamnedEffect {

    /**
     * The singleton instance of the Amulet of the damned effect for Ahrim
     */
    public static final AmuletOfTheDamnedEffect INSTANCE = new AhrimEffect();

    @Override
    public boolean hasExtraRequirement(Player player) {
        return EquipmentSet.AHRIM.isWearingBarrows(player);
    }

    @Override
    public void useEffect(Player player, Entity other, Damage damage) {
        Degrade.degrade(player, Degrade.DegradableItem.AMULETS_OF_THE_DAMNED);
    }

    /**
     * Applies the Ahrim's Amulet of the damned effect to the maximum damage of the player
     * @param maximumDamage The maximum damage the player can do
     * @return The modified damage
     */
    public static int modifyDamage(int maximumDamage) {
        //TODO REMOVE
        return 0;
    }

}
