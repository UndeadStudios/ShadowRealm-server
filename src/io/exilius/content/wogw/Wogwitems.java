package io.exilius.content.wogw;

public class Wogwitems {

	public enum itemsOnWell {

		ABYSSAL_WHIP(4151, 5000000),
		ARMADYL_GODSWORD(11802, 62500000), 
		ABYSSAL_DAGGER(13265, 37500000), 
		ABYSSAL_TENTACLE(12006, 37500000),
		RING_OF_WEALTH_I(12785, 17500000),
		ABYSSAL_BLUDGEON(13263, 42500000),
		BLESSED_SARADOMIN_SWORD(12809, 17500000),
		ELYSIAN_SPIRIT_SHIELD(12817, 100000000),
		DRACONIC_VISAGE(11286, 17500000),
		DRAGONFIRE_SHIELD(11284, 17500000),
		MALEDICTION_WARD(12806, 20000000),
		ODIUM_WARD(12807, 20000000),
		ARMADYL_CROSSBOW(11785, 42500000),
		HEAVY_BALLISTA(19481, 25000000),
		LIGHT_BALLISTA(19478, 20000000),
		TOXIC_BLOWPIPE(12924, 75000000),
		DARK_BOW(11235, 20000000),
		SEERS_RING(6731, 2500000),
		SEERS_RING_I(11770, 7500000),
		ARCHERS_RING(6733, 2500000),
		ARCHERS_RING_I(11771, 7500000),
		WARRIORS_RING(6735, 2500000),
		WARRIORS_RING_I(11772, 7500000),
		BERSERKER_RING(6737, 2500000),
		BERSERKER_RING_I(11773, 7500000),
		TYRANNICAL_RING_I(12691, 15000000),
		TREASONOUS_RING_I(12692, 15000000),
		RING_OF_SUFFERING(19550, 12500000),
		AMULET_OF_THE_DAMNED(12853, 12500000),
		AMULET_OF_FURY(6585, 5000000),
		AMULET_OF_TORTURE(19553, 20000000),
		NECKLACE_OF_ANGUISH(19547, 20000000),
		TORMENTED_BRACELET(19544, 10000000),
		PRIMORDIAL_BOOTS(13239, 25000000),
		ETERNAL_BOOTS(13235, 25000000),
		PEGASIAN_BOOTS(13237, 25000000),
		SMOULDERING_STONE(13233, 22500000),
		BANDOS_CHESTPLATE(11832, 25000000),
		BANDOS_TASSETS(11834, 25000000),
		ARMADYL_HELMET(11826, 37500000),
		ARMADYL_CHESTPLATE(11828, 37500000),
		ARMADYL_CHAINSKIRT(11830, 37500000),
		ZAMORAK_GODSWORD(11808, 10000000),
		BANDOS_GODSWORD(11804, 10000000),
		SARADOMIN_GODSWORD(11806, 30000000),
		RING_OF_WEALTH(2572, 10000000),
		DRAGON_BOOTS(11840, 4000000),
		KRAKEN_TENTACLE(12004, 30000000),
		//DINHS_BULWARK(21015, 1),
		//ELDER_MAUL(21003, 1),
		//ANCESTRAL_ROBE_BOTTOM(21024, 1),
		//ANCESTRAL_ROBE_TOP(21021, 1),
		//ANCESTRAL_HAT(21018, 1),
		SARADOMIN_SWORD(11838, 10000000),
		RANGER_BOOTS(2577, 10000000),
		DRAGON_PICKAXE(11920, 7500000),
		DRAGON_AXE(6739, 5000000),
		KODAI_WAND(21006, 40000000),
		KODAI_INSIGNIA(21043, 30000000),
		BALLISTA_LIMBS(19592, 2500000),
		BALLISTA_SPRING(19601, 2500000),
		LIGHT_FRAME(19586, 5000000),
		HEAVY_FRAME(19589, 7500000),
		MONKEY_TAIL(19610, 7500000),
		INFINITY_HAT(6918, 1000000),
		INFINITY_ROBE_TOP(6916, 1000000),
		INFINITY_ROBE_BOTTOM(6924, 1000000),
		INFINITY_GLOVES(6922, 500000),
		INFINITY_BOOTS(6920, 500000),
		BANDOS_BOOTS(11836, 2000000),
		TWISTED_BUCKLER(21000, 30000000);

		private final int itemId;
        private final int itemWorth;

		itemsOnWell(final int itemId, final int itemWorth) {
			this.itemId = itemId;
			this.itemWorth = itemWorth;
		}

		public int getItemId() {
			return itemId;
		}

		public int getItemWorth() {
			return itemWorth;
		}
	}
	
}