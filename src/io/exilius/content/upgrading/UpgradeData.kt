package io.exilius.content.upgrading

import io.exilius.model.items.GameItem

/**
 * @author Flub
 * Holds the data for items that can be upgraded.
 */
enum class UpgradeData(
    val category: Categories,
    val originalItemID: Int,
    val upgradedItem: GameItem,
    val price: Int,
    val successRate: Double
) {

    SCYTHE_OF_VITUR(Categories.WEAPON, 22325, GameItem(29499, 1), 250_000_000, 25.0),
    GHRAZI_RAPIER(Categories.WEAPON, 22324, GameItem(26219, 1), 250_000_000, 25.0),
    SAGUINESTI_STAFF(Categories.WEAPON, 22323, GameItem(27277, 1), 250_000_000, 25.0),
    DRAGON_WARRHAMER(Categories.WEAPON, 13576, GameItem(22622, 1), 150_000_000, 25.0),
    WHIP(Categories.WEAPON, 4151, GameItem(28220, 1), 25_000_000, 25.0),

    TORVA_PLATELEGS(Categories.ARMOUR, 26386, GameItem(8812, 1), 300_000_000, 30.0),
    TORVA_PLATEBODY(Categories.ARMOUR, 26384, GameItem(8813, 1), 300_000_000, 30.0),
    TORVA_FULL_HELM(Categories.ARMOUR, 26382, GameItem(8814, 1), 300_000_000, 30.0),
    CORRUPTED_HELM_PERFECTED(Categories.ARMOUR, 23842, GameItem(27226, 1), 350_000_000, 30.0),//into masori
    CORRUPTED_BODY_PERFECTED(Categories.ARMOUR, 23845, GameItem(27229, 1), 350_000_000, 30.0),//into masori
    CORRUPTED_LEGS_PERFECTED(Categories.ARMOUR, 23848, GameItem(27232, 1), 350_000_000, 30.0),//into masori
    BANDOS_CHESTPLATE(Categories.ARMOUR, 11832, GameItem(29163, 1), 50_000_000, 35.0),//bandos_g
    BANDOS_TASSETS(Categories.ARMOUR, 11834, GameItem(29164, 1), 50_000_000, 35.0),//bandos_g
    BANDOS_BOOTS(Categories.ARMOUR, 11836, GameItem(29165, 1), 50_000_000, 35.0),//bandos_g

    RING_OF_WEALTH(Categories.ACCESSORIES, 2572, GameItem(12785, 1), 10_000_000, 20.0),//into rowi
//    AVAS_ASSEMBLER(Categories.OTHER, 12785, GameItem(12785, 1), 80_000_000, 5.0),
    ;
}