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

    SCYTHE_OF_VITUR(Categories.WEAPON, 22325, GameItem(26399, 1), 250_000_000, 10.0),

    TORVA_PLATELEGS(Categories.ARMOUR, 26386, GameItem(8812, 1), 150_000_000, 10.0),
    TORVA_PLATEBODY(Categories.ARMOUR, 26384, GameItem(8813, 1), 150_000_000, 10.0),
    TORVA_FULL_HELM(Categories.ARMOUR, 26382, GameItem(8814, 1), 150_000_000, 10.0),

    RING_OF_WEALTH(Categories.ACCESSORIES, 2572, GameItem(12785, 1), 20_000_000, 40.0),


    ;
}