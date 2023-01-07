package io.exilius.content.combat.melee;

import io.exilius.content.combat.magic.CombatSpellData;
import io.exilius.content.combat.weapon.AttackStyle;
import io.exilius.content.combat.weapon.CombatStyle;
import io.exilius.model.CombatType;
import io.exilius.model.Items;
import io.exilius.model.definitions.ItemDef;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.ItemAssistant;

public class MeleeData {

	public static boolean usingSytheOfVitur(Player player) {
		return player.attacking.getCombatType() == CombatType.MELEE && player.getItems().isWearingItem(Items.SCYTHE_OF_VITUR);
	}
	public static boolean usingScytheOfOsiris(Player player) {
		return player.attacking.getCombatType() == CombatType.MELEE && player.getItems().isWearingItem(8817);
	}
	public static boolean usingLavaScythe(Player player) {
		return player.attacking.getCombatType() == CombatType.MELEE && player.getItems().isWearingItem(29499);
	}
	public static boolean usingIceScythe(Player player) {
		return player.attacking.getCombatType() == CombatType.MELEE && player.getItems().isWearingItem(29047);
	}
	public static boolean usingHally(Player c) {
		switch (c.playerEquipment[Player.playerWeapon]) {
		case 3190:
		case 3192:
		case 3194:
		case 3196:
		case 3198:
		case 2054:
		case 3202:
		case 3204:
		case 13092:
			return true;

		default:
			return false;
		}
	}

	public static void setWeaponAnimations(Player c) {
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;


		int weaponId = c.playerEquipment[Player.playerWeapon];
		String name = ItemDef.forId(weaponId).getName();

		if (name == null) {
			return;
		}

		String weaponName = name.toLowerCase();

		if (weaponName.contains("c'bow")) {
			c.playerStandIndex = 4591;
			c.playerWalkIndex = 4226;
			c.playerRunIndex = 4228;
			return;
		}

		if (weaponName.contains("hunting knife")) {
			c.playerStandIndex = 7329;
			c.playerWalkIndex = 7327;
			c.playerRunIndex = 7327;
			return;
		}
		if(weaponName.contains("boxing")){
			c.playerStandIndex = 3677;
			c.playerWalkIndex = 3680;
			return;
		}
		if (weaponName.contains("bulwark")) {
			c.playerStandIndex = 7508;
			c.playerWalkIndex = 7510;
			c.playerRunIndex = 7509;
			return;
		}
		
		if (weaponName.contains("elder maul")) {
			c.playerStandIndex = 7518;
			c.playerWalkIndex = 7520;
			c.playerRunIndex = 7519;
			return;
		}
		
		if (weaponName.contains("ballista")) {
			c.playerStandIndex = 7220;
			c.playerWalkIndex = 7223;
			c.playerRunIndex = 7221;
			return;
		}
		if (weaponName.contains("clueless")) {
			c.playerStandIndex = 7271;
			c.playerWalkIndex = 7272;
			c.playerRunIndex = 7273;
			return;
		}
		if (weaponName.contains("casket")) {
			c.playerRunIndex = 7274;
			return;
		}
		if (weaponName.contains("halberd") || weaponName.contains("hasta") || weaponName.contains("spear") || weaponName.contains("guthan") || weaponName.contains("sceptre")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}


		if (weaponName.contains("banner")) {
			c.playerStandIndex = 1421;
			c.playerWalkIndex = 1422;
			c.playerRunIndex = 1427;
			return;
		}
		if (weaponName.startsWith("basket")) {
			c.playerStandIndex = 1836;
			c.playerWalkIndex = 1836;
			c.playerRunIndex = 1836;
			return;
		}

		if (weaponName.contains("sled")) {
			c.playerStandIndex = 1461;
			c.playerWalkIndex = 1468;
			c.playerRunIndex = 1467;
			return;
		}
		if (weaponName.contains("dharok")) {
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 2064;
			return;
		}
		if (weaponName.contains("ahrim")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}
		if (weaponName.contains("verac")) {
			c.playerStandIndex = 1832;
			c.playerWalkIndex = 1830;
			c.playerRunIndex = 1831;
			return;
		}
		if (weaponName.contains("wand") || weaponName.contains("staff") || weaponName.contains("trident")) {
			c.playerStandIndex = 809;
			c.playerRunIndex = 1210;
			c.playerWalkIndex = 1146;
			return;
		}
		if (weaponName.contains("karil")) {
			c.playerStandIndex = 2074;
			c.playerWalkIndex = 2076;
			c.playerRunIndex = 2077;
			return;
		}
		if(weaponName.contains("tumeken")) {
			c.playerStandIndex = 9494;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			c.playerTurnIndex = 1702;
			c.playerTurn180Index = 1704;
			c.playerTurn90CWIndex = 1706;
			c.playerTurn90CCWIndex = 1705;
			return;
		}
		if (weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("saradomin sw") || weaponName.contains("saradomin's bless") || weaponName.contains("large spade")) {
			if (weaponId != 7158) {
				c.playerStandIndex = 7053;
				c.playerWalkIndex = 7052;
				c.playerRunIndex = 7043;
				c.playerTurnIndex = 7044;
				c.playerTurn180Index = 7047;
				c.playerTurn90CWIndex = 7047;
				c.playerTurn90CCWIndex = 7048;
				return;
			}
		}
		if (weaponName.contains("bow")) {
			c.playerStandIndex = 808;
			c.playerWalkIndex = 819;
			c.playerRunIndex = 824;
			return;
		}
		
		if (weaponName.contains("zamorakian")) {
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			return;
		}


		switch (weaponId) {
			case Items.NIGHTMARE_STAFF:
				c.playerStandIndex = 4504;
				c.playerWalkIndex = 1205;
				c.playerRunIndex = 1210;
				c.playerTurnIndex = 1209;
				c.playerTurn180Index = 1206;
				c.playerTurn90CWIndex = 1207;
				c.playerTurn90CCWIndex = 1208;
				break;
			case Items.EVENT_RPG:
			case Items.FIXED_DEVICE: //2324 attack anim ;) add this later.
				c.playerStandIndex = 2316;
				c.playerTurnIndex = 2317;
				c.playerWalkIndex = 2317;
				c.playerTurn180Index = 2317;
				c.playerTurn90CWIndex = 2317;
				c.playerTurn90CCWIndex = 2317;
				c.playerRunIndex = 2322;
				break;
		case Items.DRAGON_HUNTER_LANCE:
			c.playerStandIndex = 813;
			c.playerWalkIndex = 1205;
			c.playerRunIndex = 2563;
			c.playerTurnIndex = 1209;
			c.playerTurn180Index = 1206;
			c.playerTurn90CWIndex = 1207;
			c.playerTurn90CCWIndex = 1208;
			break;
			case 10735:
			case 1419:
			case 29499:
			case Items.SCYTHE_OF_OSIRIS:
			case Items.SCYTHE_OF_VITUR:
			case 29047:
				c.playerStandIndex = 8057;
				c.playerWalkIndex = 1146;
				c.playerRunIndex = 1210;
				break;
		case 7158:
			c.playerStandIndex = 2065;
			c.playerWalkIndex = 2064;
			break;
		case 22545:
		case 29180:
			c.playerStandIndex = 244;
			c.playerWalkIndex = 247;
			c.playerRunIndex = 248;
			break;
		case 4151:
		case 12773:
		case 12774:
		case 12006:
			c.playerWalkIndex = 1660;
			c.playerRunIndex = 1661;
			break;
		case 8004:
		case 7960:
			c.playerStandIndex = 2065;
			c.playerWalkIndex = 2064;
			break;
		case 6528:
		case Items.HILL_GIANT_CLUB:
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 2064;
			c.playerRunIndex = 1664;
			break;
		case 12848:
		case 4153:
		case 13263:
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			break;
		case 10887:
			c.playerStandIndex = 5869;
			c.playerWalkIndex = 5867;
			c.playerRunIndex = 5868;
			break;
			case Items.ALE_OF_THE_GODS: //3040, 3039, 3039
				c.playerStandIndex = 3040;
				c.playerTurnIndex = 3039;
				c.playerWalkIndex = 3039;
				c.playerTurn180Index = 3039;
				c.playerTurn90CWIndex = 3039;
				c.playerTurn90CCWIndex = 3039;
				c.playerRunIndex = 3039;
				break;
		case 20368:
		case 20370:
		case 20374:
		case 20372:
		case 11802:
		case 11804:
		case 11838:
		case 12809:
		case 11806:
		case 11808:
			c.playerStandIndex = 7053;
			c.playerWalkIndex = 7052;
			c.playerRunIndex = 7043;
			c.playerTurnIndex = 7049;
			c.playerTurn180Index = 7052;
			c.playerTurn90CWIndex = 7052;
			c.playerTurn90CCWIndex = 7052;
			break;
		case 1305:
			c.playerStandIndex = 809;
			break;
			
		default:
			c.playerStandIndex = 0x328;
			c.playerTurnIndex = 0x337;
			c.playerWalkIndex = 0x333;
			c.playerTurn180Index = 0x334;
			c.playerTurn90CWIndex = 0x335;
			c.playerTurn90CCWIndex = 0x336;
			c.playerRunIndex = 0x338;
			break;
		}
	}

	public static int getWepAnim(Player c) {
		String weaponName = ItemAssistant.getItemName(c.playerEquipment[Player.playerWeapon]).toLowerCase();
		if (c.playerEquipment[Player.playerWeapon] <= 0) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:
					return 422;
				case AGGRESSIVE:
					return 423;
				case DEFENSIVE:
					return 422;
			}
		}
		if (weaponName.contains("bulwark")) {
			return 7511;
		}
		if (weaponName.contains("elder maul")) {
			return 7516;
		}
		if (weaponName.startsWith("boxing")) {
			return 3678;
		}

		if (weaponName.contains("scythe of vitur")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:
				case AGGRESSIVE:
				case CONTROLLED:
				case DEFENSIVE:
					return 8056;
			}
		}

		if (weaponName.contains("ghrazi rapier")) {
			return 8145;
		}
		if (weaponName.contains("zamorakian")) {
			return 2080;
		}
		if (weaponName.contains("hunting knife")) {
			return 7328;
		}
		if (weaponName.contains("ballista")) {
			return 7218;
		}
		if (weaponName.contains("toxic blowpipe")) {
			return 5061;
		}
		if (weaponName.contains("warhammer")) {
			return 401;
		}
		if (weaponName.contains("dart")) {
			return c.getCombatConfigs().getWeaponMode().getAttackStyle() == AttackStyle.AGGRESSIVE ? 806 : 6600;
		}
		if (weaponName.contains("dragon 2h")) {
			return 407;
		}
		if (weaponName.contains("thrownaxe")) {
			return 7617;
		}
		if (weaponName.contains("knife") || weaponName.contains("javelin")) {
			return 806;
		}
		if (weaponName.contains("cross") && !weaponName.contains("karil") || weaponName.contains("c'bow") && !weaponName.contains("karil")) {
			return 4230;
		}
		if (weaponName.contains("halberd")) {
			return 440;
		}
		if (weaponName.startsWith("dragon dagger")) {
			return 402;
		}
		if (weaponName.contains("abyssal dagger")) {
			return c.getCombatConfigs().getWeaponMode().getCombatStyle() == CombatStyle.SLASH ? 3297 : 3294;
		}
		if (weaponName.contains("dagger") || weaponName.contains("arclight")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:// attack
					return 386;
				case AGGRESSIVE:// str
					return 386;
				case DEFENSIVE:// def
					return 386;
				case CONTROLLED:// crush
					return 390;
			}
		}
		if (weaponName.contains("lance")) {
			switch (c.getCombatConfigs().getWeaponMode().getCombatStyle()) {
				case STAB:
					return 8288;
				case CRUSH:
				case SLASH: // 8289 is slash but it's broken
					return 8290;
			}
		}
		if (weaponName.contains("scimitar")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:// attack
					return 390;
				case AGGRESSIVE:// str
					return 390;
				case DEFENSIVE:// def
					return 390;
				case CONTROLLED:// crush
					return 386;
			}
		}
		if (weaponName.contains("axe") && !weaponName.contains("dharok")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:// attack
					return 395;
				case AGGRESSIVE:// str
					return 395;
				case DEFENSIVE:// def
					return 395;
				case CONTROLLED:// crush
					return 401;
			}
		}
		if (weaponName.contains("mace")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:// attack
					return 401;
				case AGGRESSIVE:// str
					return 401;
				case DEFENSIVE:// def
					return 401;
				case CONTROLLED:// crush
					return 400;
			}
		}
		if (weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("aradomin sword") || weaponName.contains("blessed sword") || weaponName.contains("large spade")) {
			switch (c.getCombatConfigs().getWeaponMode().getIndex()) {
				case 0:// attack
					return 7045;
				case 2:// str
					return 7045;
				case 1:// def
					return 7046;
				case 3:// crush
					return 7046;
			}
		}
		if (weaponName.contains("dharok")) {
			switch (c.getCombatConfigs().getWeaponMode().getIndex()) {
			case 0:// attack
				return 2067;
			case 1:// str
				return 2067;
			case 2:// crush
				return 2066;
			case 3:// def
				return 2067;
			}
		}
		if (weaponName.contains("arclight")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case ACCURATE:
					return 451;
				case AGGRESSIVE:
					return 2067;
				case DEFENSIVE:
					return 2067;
			}
		}
		if (weaponName.contains("sword") && !weaponName.contains("training")) {
			switch (c.getCombatConfigs().getWeaponMode().getIndex()) {
				case 0:// attack
					return 386;
				case 2:// str
					return 386;
				case 1:// def
					return 386;
				case 3:// crush
					return 390;
			}
		}
		if (weaponName.contains("karil")) {
			return 2075;
		}
		if (weaponName.contains("bow") && !weaponName.contains("'bow") && !weaponName.contains("karil")) {
			return 426;
		}
		if (weaponName.contains("'bow") && !weaponName.contains("karil")) {
			return 4230;
		}
		if (weaponName.contains("hasta") || weaponName.contains("spear")) {
			if (weaponName.contains("zamorakian")) {
				return 412;
			}
			return 400;
		}
		if (weaponName.contains("warspear")) {
			switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
				case CONTROLLED:
				case DEFENSIVE:
					return 2081;
			}
		}
		if (weaponName.contains("scim")) {
				return 451;
			}
		switch (c.playerEquipment[Player.playerWeapon]) { // if you don't want to use strings

		case 9703:
			return 412;
			case 22545:
			case 29180:
				return 245;
		case 13263:
			return 3298;
			case 29499:
			case Items.SCYTHE_OF_OSIRIS:
			case 29047:
				switch (c.getCombatConfigs().getWeaponMode().getAttackStyle()) {
					case ACCURATE:
					case AGGRESSIVE:
					case CONTROLLED:
					case DEFENSIVE:
						return 8056;
				}
		case 6522:
			return 2614;
		case 11959:
		case 10034:
		case 10033:
			return 2779;
		case 24425:
		case 24424:
		case 24423:
		case 24422:
			return 4505;
		case 11791:
		case 12904:
			return 440;
		case 8004:
		case 7960:
			return 2075;
		case 12848:
		case 4153: // granite maul
			return 1665;
//		case 4726: // guthan
//			return 2081;
		case 4747: // torag
			return 0x814;
		case 4710: // ahrim
			return 406;
		case 4755: // verac
			return 2062;
		case 4734: // karil
			return 2075;
		case 4151:
			case 28213:
			case 28214:
			case 28215:
			case 28216:
			case 28217:
			case 28218:
			case 28219:
			case 28220:
		case 12773:
		case 12774:
		case 12006:
			return 1658;
		  case 20727:
	        	return 2614;
		case 6528:
		case Items.HILL_GIANT_CLUB:
			return 2661;
		case 10887:
			return 5865;
			default:
				switch (c.getCombatConfigs().getWeaponMode().getIndex()) {
					case 0:// attack
						return 422;
					case 2:// str
						return 422;
					case 3:// crush
						return 423;
				}
				return 422;
		}
	}

	public static int getBlockEmote(Player c) {
		String shield = ItemAssistant.getItemName(c.playerEquipment[Player.playerShield]).toLowerCase();
		String weapon = ItemAssistant.getItemName(c.playerEquipment[Player.playerWeapon]).toLowerCase();
		if (shield.contains("defender"))
			return 4177;
		if (shield.contains("2h") && c.playerEquipment[Player.playerWeapon] != 7158)
			return 7050;
		if (shield.contains("book") || (weapon.contains("wand") || (weapon.contains("staff") || weapon.contains("trident")|| weapon.contains("Tumeken's shadow"))))
			return 420;
		if (shield.contains("shield"))
			return 1156;
		if (shield.contains("warhammer"))
			return 397;
		if (shield.contains("bulwark"))
			return 7512;
		if (shield.contains("elder maul"))
			return 7517;
		if (weapon.startsWith("boxing"))
			return 3679;
		switch (c.playerEquipment[Player.playerWeapon]) {
			case Items.SCYTHE_OF_VITUR:
				return 435;
			case Items.SCYTHE_OF_OSIRIS:
				return 435;
			case 29499:
			case 29047:
				return 435;
		case Items.DRAGON_HUNTER_LANCE:
			return 420;
		case 1734:
		case 411:
			
			return 3895;
		case 1724:
			return 3921;
		case 1709:
			return 3909;
		case 1704:
			return 3916;
		case 1699:
			return 3902;
		case 1689:
			return 3890;
		case 4755:
			return 2063;
		case 14484:
			return 397;
		case 12848:
		case 4153:		
		case 13263:
			return 1666;
		case 13265:
		case 13267:
		case 13269:
		case 13271:
			return 3295;
		case 7158:
			return 410;
		case 4151:
			case 28213:
			case 28214:
			case 28215:
			case 28216:
			case 28217:
			case 28218:
			case 28219:
			case 28220:
		case 12773:
		case 12774:
		case 12006:
			return 1659;
		  case 20727:
	        	return 2614;
			case 20368:
			case 20370:
			case 20374:
			case 20372:
		case 11802:
		case 11806:
		case 11808:
		case 11804:
		case 11838:
		case 12809:
		case 11730:
			return 7056;
		case -1:
			return 424;
		default:
			return 424;
		}
	}

	public static int getHitDelay(Player c) {
		String weaponName = ItemAssistant.getItemName(c.playerEquipment[Player.playerWeapon]).toLowerCase();
		if (c.usingMagic) {
			switch (c.getSpellId()) {
				case 100:
					return 5;
			}
			switch (CombatSpellData.getSpellId(c.getSpellId())) {
			case 12891:
				return 4;
			case 12871:
				return 6;
			default:
				return 4;
			}
		}
		if (weaponName.contains("dart")) {
			return 3;
		}
		if (weaponName.contains("knife") || weaponName.contains("javelin") || weaponName.contains("thrownaxe") || weaponName.contains("throwing axe")) {
			return 3;
		}
		if (weaponName.contains("cross") || weaponName.contains("c'bow")) {
			return 4;
		}
		if (weaponName.contains("ballista")) {
			return 5;
		}
		if (weaponName.contains("bow") && !c.dbowSpec) {
			return 4;
		} else if (c.dbowSpec) {
			return 4;
		}

		switch (c.playerEquipment[Player.playerWeapon]) {
		case 6522: // Toktz-xil-ul
			return 3;
		case 10887:
			return 3;
		case 10034:
		case 10033:
			return 3;
		default:
			return 2;
		}
	}
}