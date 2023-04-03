package io.shadowrealm.content.upgrading

import io.shadowrealm.model.items.GameItem
import org.yaml.snakeyaml.events.Event.ID

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

    SCYTHE_OF_VITUR(Categories.WEAPON, 22325, GameItem(29499, 1), 50_000_000, 25.0),
    GHRAZI_RAPIER(Categories.WEAPON, 22324, GameItem(26219, 1), 55_000_000, 25.0),
    SAGUINESTI_STAFF(Categories.WEAPON, 22323, GameItem(27277, 1), 60_000_000, 25.0),
    DRAGON_WARRHAMER(Categories.WEAPON, 13576, GameItem(22622, 1), 50_000_000, 25.0),
    WHIP(Categories.WEAPON, 4151, GameItem(28220, 1), 25_000_000, 25.0),
    DRAGON_CLAWS(Categories.WEAPON, 20784, GameItem(26708, 1), 25_000_000, 35.0),
    URSINECHAINMACE(Categories.WEAPON, 22545, GameItem(25486, 1), 100_000_000, 40.0),
    ACCURSEDSPEPTER(Categories.WEAPON, 22555, GameItem(27665, 1), 100_000_000, 40.0),
    WEBWEARVER(Categories.WEAPON, 22550, GameItem(27655, 1), 100_000_000, 40.0),
    ABYSSALWHIPOR(Categories.WEAPON, 26482, GameItem(26484, 1), 500_000_000, 50.0),

    TORVA_PLATELEGS(Categories.ARMOUR, 26386, GameItem(29376, 1), 160_000_000, 40.0),
    TORVA_PLATEBODY(Categories.ARMOUR, 26384, GameItem(29377, 1), 160_000_000, 40.0),
    TORVA_FULL_HELM(Categories.ARMOUR, 26382, GameItem(29378, 1), 160_000_000, 40.0),
    SHADOW_Cape(Categories.ARMOUR, 28347, GameItem(19480, 1), 105_000_000, 30.0),
    CORRUPTED_HELM_PERFECTED(Categories.ARMOUR, 23842, GameItem(27226, 1), 50_000_000, 30.0),//into masori
    CORRUPTED_BODY_PERFECTED(Categories.ARMOUR, 23845, GameItem(27229, 1), 50_000_000, 30.0),//into masori
    CORRUPTED_LEGS_PERFECTED(Categories.ARMOUR, 23848, GameItem(27232, 1), 50_000_000, 30.0),//into masori
    SHADOWMASORIBODY(Categories.ARMOUR, 27226, GameItem(29381, 1), 80_000_000, 25.0),//into GODLY masori
    SHADOWMASORIHELM(Categories.ARMOUR, 27229, GameItem(29382, 1), 80_000_000, 25.0),//into GODLY masori
    SHADOWMASORICHAPS(Categories.ARMOUR, 27232, GameItem(29380, 1), 80_000_000, 25.0),//into GODLY masori
    BANDOS_CHESTPLATE(Categories.ARMOUR, 11832, GameItem(29163, 1), 50_000_000, 35.0),//bandos_g
    BANDOS_TASSETS(Categories.ARMOUR, 11834, GameItem(29164, 1), 50_000_000, 35.0),//bandos_g
    BANDOS_BOOTS(Categories.ARMOUR, 11836, GameItem(29165, 1), 50_000_000, 35.0),//bandos_g

    RING_OF_WEALTH(Categories.ACCESSORIES, 2572, GameItem(12785, 1), 10_000_000, 20.0),//into rowi


    RAIDS_1_MBOX(Categories.OTHER,10025, GameItem(6830,1),10_000_000,20.0),//into raids 2 mbox
    RAIDS_2_MBOX(Categories.OTHER,6830, GameItem(28825,1),20_000_000,20.0),//into nex box
    CASKET(Categories.OTHER,406,GameItem(28827,1),5_000_000,20.0),//into money box
    SUPER_MYSTERY_BOX(Categories.OTHER,6828, GameItem(13346,1),10_000_000,20.0),//into ultra mystery box
    CRYSTALLINE_KEY(Categories.OTHER,23951, GameItem(23776,1),10_000_000,20.0),//into hunllef's key
    CRYSTAL_KEY(Categories.OTHER,989, GameItem(11942,1),1_500_00,45.0),//into ecumenical key
//    AVAS_ASSEMBLER(Categories.OTHER, 12785, GameItem(12785, 1), 80_000_000, 5.0),
    ;
}