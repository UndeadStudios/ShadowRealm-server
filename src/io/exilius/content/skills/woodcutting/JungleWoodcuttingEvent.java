package io.exilius.content.skills.woodcutting;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.SkillcapePerks;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.*;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.skills.Skill;
import io.exilius.content.skills.firemake.Firemaking;
import io.exilius.model.Items;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Position;
import io.exilius.model.items.ItemCacheDefinition;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;

import java.util.Optional;

public class JungleWoodcuttingEvent extends Event<Player> {
	private static boolean woodcuttingJungle;
	private final Jungle tree;
	private final Machete hatchet;
	private final int objectId;
	private final int x;
	private final int y;
	private int chops;
	
	private final int[] lumberjackOutfit = { 10933, 10939, 10940, 10941 };

	public JungleWoodcuttingEvent(Player player, Jungle tree, Machete hatchet, int objectId, int x, int y) {
		super("skilling", player, 1);
		this.tree = tree;
		this.hatchet = hatchet;
		this.objectId = objectId;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		double osrsExperience;
		double experience;
		int pieces = 0;
		pieces=handleOutfit(pieces);
		osrsExperience = tree.getExperience() + tree.getExperience() / 10 * pieces;
		if (canChop()) return;
//		if (Misc.random(300) == 0 && attachment.getInterfaceEvent().isExecutable()) {
//			attachment.getInterfaceEvent().execute();
//			super.stop();
//			return;
//		}
		chops++;
		int chopChance = 1 + (int) (tree.getChopsRequired() * hatchet.getChopSpeed());
		if (Boundary.isIn(attachment, Boundary.WOODCUTTING_GUILD_BOUNDARY)){
			chopChance *= 1.5;
		}
		if (Misc.random(tree.getChopdownChance()) == 0 || tree.equals(Jungle.LIGHT_JUNGLE) && Misc.random(chopChance) == 0) {
			int face = 0;
			Optional<WorldObject> worldObject = attachment.getRegionProvider().get(x, y).getWorldObject(objectId, x, y, 0);
			if (worldObject.isPresent()) {
				face = worldObject.get().getFace();
			}
			int stumpId = 0;
			if (tree.equals(Tree.REDWOOD)) {
				face = (attachment.absX < 1568) ? 1 : (attachment.absX > 1573) ? 3 : (attachment.absY < 3480) ? 0 : 2;
				if (objectId == 29668)
					stumpId = 29669;
				else if (objectId == 29670)
					stumpId = 29671;
			}

			Server.getGlobalObjects().add(new GlobalObject(tree.getStumpId(), x, y, attachment.heightLevel, face, 10, tree.getRespawnTime(), objectId));
			attachment.sendMessage("You get some "+ ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase()+".");
			attachment.getItems().addItem(tree.getWood(), 1);
			attachment.getEventCalendar().progress(EventChallenge.CUT_DOWN_X_MAGIC_LOGS);
			attachment.getPA().addSkillXPMultiplied((int)osrsExperience, Skill.WOODCUTTING.getId(), true);
			Achievements.increase(attachment, AchievementType.WOODCUT, 1);
			attachment.getPA().sendSound(2734);
			handleRewards();

			super.stop();
			return;
		}
		if (!tree.equals(Jungle.LIGHT_JUNGLE)) {
			if (Misc.random(chopChance) == 0 || chops >= tree.getChopsRequired()) {
				chops = 0;
				int random = Misc.random(4);
				attachment.getPA().addSkillXPMultiplied((int) osrsExperience, Skill.WOODCUTTING.getId(), true);
				Achievements.increase(attachment, AchievementType.WOODCUT, 1);
				if ((attachment.getItems().isWearingItem(13241) || attachment.getItems().playerHasItem(13241)) && random == 2) {
					Firemaking.lightFire(attachment, tree.getWood(), "infernal_axe");
					return;
				}
				handleDiary(tree);
				//foeArtefact(attachment);
				//handleWildernessRewards();
				handleRewards();
				if ((SkillcapePerks.WOODCUTTING.isWearing(attachment) || SkillcapePerks.isWearingMaxCape(attachment)) && attachment.getItems().freeSlots() < 2) {
					attachment.sendMessage("You have run out of free inventory space.");
					super.stop();
					return;
				}
				attachment.sendMessage("You get some "+ ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase()+".");
				attachment.getItems().addItem(tree.getWood(), SkillcapePerks.WOODCUTTING.isWearing(attachment) || SkillcapePerks.isWearingMaxCape(attachment) ? 2 : 1);
			}
		}
		if (super.getElapsedTicks() % 4 == 0) {
			attachment.startAnimation(hatchet.getAnimation());
		}
	}

	private int handleOutfit(int pieces) {

		for (int aLumberjackOutfit : lumberjackOutfit) {
			if (attachment.getItems().isWearingItem(aLumberjackOutfit)) {
				pieces+=2;
			}
		}
		return pieces;
	}

	private boolean canChop() {

		if (attachment == null || attachment.isDisconnected() || attachment.getSession() == null) {
			super.stop();
			return true;
		}
		if (!attachment.getItems().playerHasItem(hatchet.getItemId()) && !attachment.getItems().isWearingItem(hatchet.getItemId())) {
			attachment.sendMessage("Your axe has disappeared.");
			super.stop();
			return true;
		}
		if (attachment.playerLevel[Player.playerWoodcutting] < hatchet.getLevelRequired()) {
			attachment.sendMessage("You no longer have the level required to operate this hatchet.");
			super.stop();
			return true;
		}
		if (attachment.getItems().freeSlots() == 0) {
			attachment.sendMessage("You have run out of free inventory space.");
			super.stop();
			return true;
		}
		return false;
	}

	private void handleWildernessRewards() {

		if (Boundary.isIn(attachment, Boundary.RESOURCE_AREA)) {
			if (Misc.random(20) == 5) {
				int randomAmount = 1;
				attachment.sendMessage("You received " + randomAmount + " pkp while woodcutting!");
				attachment.getItems().addItem(2996, randomAmount);
			}
		}
	}

	private void handleDiary(Jungle tree) {
		switch (tree) {
			default:
				break;

		}
	}
	private static void foeArtefact(Player player) {
		int chance = 250;
		int artefactRoll = Misc.random(100);
		if (Misc.random(chance) == 1) {
			if (artefactRoll <65) {//1/300
				player.getItems().addItemUnderAnyCircumstance(11180, 1);//ancient coin foe for 200
				player.sendMessage("You found a coin that can be used in the Fire of Exchange!");
			} else if (artefactRoll >= 65 && artefactRoll <= 99) {//1/600
				player.getItems().addItemUnderAnyCircumstance(681, 1);//anicent talisman foe for 300
				player.sendMessage("You found a talisman that can be used in the Fire of Exchange!");
			} else if (artefactRoll > 99){//1/1000
				player.getItems().addItemUnderAnyCircumstance(9034, 1);//golden statuette foe for 500
				PlayerHandler.executeGlobalMessage("@bla@[@red@Woodcutting@bla@]@blu@ " + player.getDisplayName() + " @red@just found a Golden statuette while wcing!");
			}
		}
	}

	private void handleRewards() {
		int dropRate = 10;
		int clueAmount = 1;
		if (attachment.fasterCluesScroll) {
			dropRate = dropRate*2;
		}
		if (Hespori.activeGolparSeed) {
			clueAmount = 2;
		}
			if(Misc.random(tree.getPetChance() / dropRate) == 10){
				NPCSpawning.spawn(6408, attachment.getX(), attachment.getY(), attachment.getHeight(), 4, 7, true);
		}
		if (Misc.random(500) == 1) {
			attachment.getItems().addItemUnderAnyCircumstance(lumberjackOutfit[Misc.random(lumberjackOutfit.length - 1)], 1);
			attachment.sendMessage("You notice a lumberjack piece falling from the tree and pick it up.");
		}

		int petRate = attachment.skillingPetRateScroll ? (int) (tree.getPetChance() * .75) : tree.getPetChance();
		if (Misc.random(petRate) == 2 && attachment.getItems().getItemCount(13322, false) == 0 && attachment.petSummonId != 13322) {
			PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] @cr20@ <col=255>" + attachment.getDisplayName() + "</col> chopped down a tree for the <col=CC0000>Beaver</col> pet!");
			attachment.getItems().addItemUnderAnyCircumstance(13322, 1);
			attachment.getCollectionLog().handleDrop(attachment, 5, 13322, 1);
		}
	}

	@Override
	public void stop() {
		super.stop();
		if (attachment != null) {
			attachment.startAnimation(65535);
		}
	}

}
