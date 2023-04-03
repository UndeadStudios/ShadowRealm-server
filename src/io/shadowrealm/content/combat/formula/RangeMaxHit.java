package io.shadowrealm.content.combat.formula;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.combat.range.RangeData;
import io.shadowrealm.model.Bonus;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.definitions.NpcStats;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.util.Arrays;
import java.util.Optional;

public class RangeMaxHit extends RangeData {

	public static int calculateRangeDefence(Player c) {
		int defenceLevel = CombatFormula.getPrayerBoostedDefenceLevel(c);
		return CombatFormula.getEffectLevel(defenceLevel, c.getItems().getBonus(Bonus.DEFENCE_RANGED));
		//return defenceLevel + c.getItems().getBonus(Bonus.DEFENCE_RANGED) + (c.getItems().getBonus(Bonus.DEFENCE_RANGED) / 2);
	}

	public static int getBestTwistedBowMagicLevel(NPC npc) {
		NpcStats stats = NpcStats.forId(npc.getNpcId());
		return Math.max(stats.getMagicLevel(), stats.getMagic());
	}
	public static int getBestGTwistedBowMagicLevel(NPC npc) {
		NpcStats stats = NpcStats.forId(npc.getNpcId());
		return Math.max(stats.getMagicLevel(), stats.getMagic());
	}


	public static double getGTwistedBowAccuracyBoost(int magicLevel) {
		if (magicLevel > 500)
			magicLevel = 500;
		double boost = 380 + ((3d * magicLevel - 10d) / 100d) - (Math.pow(3d * magicLevel / 10d - 100d, 2) / 100d);
		return (Math.min(boost, 380) / 100);
	}
	public static double getTwistedBowAccuracyBoost(int magicLevel) {
		if (magicLevel > 500)
			magicLevel = 500;
		double boost = 140 + ((3d * magicLevel - 10d) / 100d) - (Math.pow(3d * magicLevel / 10d - 100d, 2) / 100d);
		return (Math.min(boost, 140) / 100);
	}

	public static double getTwistedBowDamageBoost(int magicLevel, boolean cox) {
		if (magicLevel > 500)
			magicLevel = 500;
		double boost = 280 + ((1.5d * magicLevel - 14d) / 100d) - (Math.pow((1d * magicLevel / 10d) - 140d, 2) / 100d);
		return (Math.min(boost, cox ? 305 : 230) / 100);
	}

	public static double getGTwistedBowDamageBoost(int magicLevel, boolean cox) {
		if (magicLevel > 500)
			magicLevel = 500;
		double boost = 650 + ((1.5d * magicLevel - 14d) / 100d) - (Math.pow((1.5d * magicLevel / 14d) - 140d, 2) / 100d);
		return (Math.min(boost, cox ? 750 : 650) / 100);
	}

	public static int calculateRangeAttack(Player c) {
		int rangeLevel = c.playerLevel[4];
		int actualRangeLevel = c.getLevelForXP(c.playerXP[Player.playerRanged]);
		if (c.npcAttackingIndex > 0 && c.getItems().isWearingItem(Items.TWISTED_BOW)) {
			Optional<NPC> npc = NPCHandler.getNpcAtIndex(c.npcAttackingIndex);
			if (npc.isPresent()) {
				double boost = getTwistedBowAccuracyBoost(getBestTwistedBowMagicLevel(npc.get()));
				rangeLevel += (rangeLevel * boost);
				if (c.debugMessage) {
					c.sendMessage("Twisted bow accuracy boost: " + boost);
				}else if (c.npcAttackingIndex > 0 && c.getItems().isWearingItem(8029)) {
					//Optional<NPC> npc2 = NPCHandler.getNpcAtIndex(c.npcAttackingIndex);
					if (npc.isPresent()) {
						double boost2 = getGTwistedBowAccuracyBoost(getBestGTwistedBowMagicLevel(npc.get()));
						rangeLevel += (rangeLevel * boost2);
						if (c.debugMessage) {
							c.sendMessage("Godly Twisted bow accuracy boost: " + boost2);
						}
					}
				}
			}
		}
		if (c.fullVoidRange()) {
			rangeLevel += actualRangeLevel * .1;
		}
		if (c.playerEquipment[c.playerWeapon] == 12926) {//blowpipe
			rangeLevel *= .85;
		}
		if (c.playerEquipment[c.playerWeapon] == 29238) {//blowpipe
			rangeLevel *= .95;
		}
		rangeLevel = CombatFormula.getPrayerBoostedLevel(rangeLevel, CombatFormula.getPrayerRangedAccuracyBonus(c));

		if (c.npcAttackingIndex > 0 && c.getSlayer().getTask().isPresent()) {
			NPC npc = NPCHandler.npcs[c.npcAttackingIndex];
			if (c.getSlayer().hasSlayerHelmBoost(npc, CombatType.RANGE)) {
				rangeLevel  += actualRangeLevel * .15;
			} else if (c.getItems().isWearingItem(12018, Player.playerAmulet)) {
				if (Misc.linearSearch(Configuration.UNDEAD_NPCS, npc.getNpcId()) != -1) {
					rangeLevel += actualRangeLevel * .20;
				}
			}
		}
		rangeLevel += CombatFormula.EFFECTIVE_LEVEL_BOOST;
		rangeLevel += c.attacking.getFightModeAttackBonus();
		return CombatFormula.getEffectLevel(rangeLevel, c.getItems().getBonus(Bonus.ATTACK_RANGED));
	}

	public static boolean wearingCrystalBow(Player c) {
		return c.playerEquipment[3] != -1 && Arrays.stream(RangeData.CRYSTAL_BOWS).anyMatch(it -> c.playerEquipment[3] == it);
	}

	public static int maxHit(Player c) {
		int rangedStrength;
		if (wearingCrystalBow(c) || c.getItems().isWearingItem(Items.CRAWS_BOW, Player.playerWeapon)) {
			rangedStrength = RangeData.getRangeStr(c, Player.playerWeapon);
		} else if (c.playerEquipment[Player.playerWeapon] == Items.TOXIC_BLOWPIPE) {//blowpipe
			rangedStrength = RangeData.getRangeStr(c, Player.playerArrows) + getRangeStr(c.getToxicBlowpipeAmmo());
		} else if (c.getCombatItems().wearingCrawsBow() || c.usingOtherRangeWeapons) {
			rangedStrength = RangeData.getRangeStr(c, Player.playerArrows);
		} else {
			rangedStrength = RangeData.getRangeStr(c);
		}

		double b = CombatFormula.getPrayerRangedStrengthBonus(c);

		if (c.fullVoidRange()) {
			b += 0.1;
		}

		if (c.npcAttackingIndex > 0) {
			NPC npc = NPCHandler.npcs[c.npcAttackingIndex];

			if (c.getItems().isWearingItem(21012) && c.getItems().isWearingItem(25916)) {
				if (Misc.linearSearch(Configuration.DRAG_IDS, npc.getNpcId()) != -1) {
					b += 0.60;
				}
			} else if (c.getItems().isWearingItem(12018, Player.playerAmulet)) {
				if (Misc.linearSearch(Configuration.UNDEAD_NPCS, npc.getNpcId()) != -1) {
					b += 0.20;
				}
			} else if (c.getSlayer().getTask().isPresent()) {
				if (c.getSlayer().hasSlayerHelmBoost(npc, CombatType.RANGE)) {
					b += 0.15;
				}
			}
		}
		boolean hasDarkVersion = (c.petSummonId == 30116 || c.petSummonId == 30120 || c.petSummonId == 30122);

		if (c.getItems().isWearingItem(19480)) {
			b *= 1.20;
		}
		if (c.hasFollower
				&& ((c.petSummonId == 30016  || c.petSummonId == 30020 || c.petSummonId == 30022)
				|| (hasDarkVersion))) {
			if (hasDarkVersion) {
				b *= 1.10;
			} else if (Misc.random(1) == 1) {
				b *= 1.10;
			}
		}
		double e = (c.playerLevel[4] * b) + 8 + c.attacking.getFightModeStrengthBonus();
		double max = (e * (1d + (double) rangedStrength / 64d) + 5d) / 10d;

		if (c.npcAttackingIndex > 0) {
			NPC npc = NPCHandler.npcs[c.npcAttackingIndex];

			if (c.getItems().isWearingItem(Items.TWISTED_BOW)) {
				double boost = getTwistedBowDamageBoost(getBestTwistedBowMagicLevel(npc), Boundary.FULL_RAIDS.in(c));
				max *= boost;
				if (max < 42)
					max = 42;
				if (c.debugMessage) {
					c.sendMessage("Twisted bow damage boost: " + boost);
				}else if (c.getItems().isWearingItem(8029)) {
					double boostG = getGTwistedBowDamageBoost(getBestGTwistedBowMagicLevel(npc), Boundary.FULL_RAIDS.in(c));
					max *= boostG;
					if (max < 42)
						max = 42;
					if (c.debugMessage) {
						c.sendMessage("Godly Twisted bow damage boost: " + boostG);
					}
				}
			}
		}

		return (int) max;
	}
}