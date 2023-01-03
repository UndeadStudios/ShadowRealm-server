package io.exilius.content.skills.woodcutting;

import io.exilius.Server;
import io.exilius.content.SkillcapePerks;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.skills.Skill;
import io.exilius.content.skills.firemake.Firemaking;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
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
		if (Boundary.isIn(plr, Boundary.WOODCUTTING_GUILD_BOUNDARY)){
			chopChance *= 1.5;
		}
		if (Misc.random(tree.getChopdownChance()) == 0 || tree.equals(Jungle.LIGHT_JUNGLE) && Misc.random(chopChance) == 0) {
			int face = 0;
			Optional<WorldObject> worldObject = plr.getRegionProvider().get(x, y).getWorldObject(objectId, x, y, 0);
			if (worldObject.isPresent()) {
				face = worldObject.get().getFace();
			}
			int stumpId = 0;
			if (tree.equals(Tree.REDWOOD)) {
				face = (plr.absX < 1568) ? 1 : (plr.absX > 1573) ? 3 : (plr.absY < 3480) ? 0 : 2;
				if (objectId == 29668)
					stumpId = 29669;
				else if (objectId == 29670)
					stumpId = 29671;
			}

			Server.getGlobalObjects().add(new GlobalObject(tree.getStumpId(), x, y, plr.heightLevel, face, 10, tree.getRespawnTime(), objectId));
			plr.sendMessage("You get some "+ ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase()+".");
			plr.getItems().addItem(tree.getWood(), 1);
			plr.getEventCalendar().progress(EventChallenge.CUT_DOWN_X_MAGIC_LOGS);
			plr.getPA().addSkillXPMultiplied((int)osrsExperience, Skill.WOODCUTTING.getId(), true);
			Achievements.increase(plr, AchievementType.WOODCUT, 1);
			plr.getPA().sendSound(2734);
			handleRewards();

			super.stop();
			return;
		}
		if (!tree.equals(Jungle.LIGHT_JUNGLE)) {
			if (Misc.random(chopChance) == 0 || chops >= tree.getChopsRequired()) {
				chops = 0;
				int random = Misc.random(4);
				plr.getPA().addSkillXPMultiplied((int) osrsExperience, Skill.WOODCUTTING.getId(), true);
				Achievements.increase(plr, AchievementType.WOODCUT, 1);
				if ((plr.getItems().isWearingItem(13241) || plr.getItems().playerHasItem(13241)) && random == 2) {
					Firemaking.lightFire(plr, tree.getWood(), "infernal_axe");
					return;
				}
				handleDiary(tree);
				//foeArtefact(attachment);
				//handleWildernessRewards();
				handleRewards();
				if ((SkillcapePerks.WOODCUTTING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr)) && plr.getItems().freeSlots() < 2) {
					plr.sendMessage("You have run out of free inventory space.");
					super.stop();
					return;
				}
				plr.sendMessage("You get some "+ ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase()+".");
				plr.getItems().addItem(tree.getWood(), SkillcapePerks.WOODCUTTING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr) ? 2 : 1);
			}
		}
		if (super.getElapsedTicks() % 4 == 0) {
			plr.startAnimation(hatchet.getAnimation());
		}
	}

	private int handleOutfit(int pieces) {

		for (int aLumberjackOutfit : lumberjackOutfit) {
			if (plr.getItems().isWearingItem(aLumberjackOutfit)) {
				pieces+=2;
			}
		}
		return pieces;
	}

	private boolean canChop() {

		if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
			super.stop();
			return true;
		}
		if (!plr.getItems().playerHasItem(hatchet.getItemId()) && !plr.getItems().isWearingItem(hatchet.getItemId())) {
			plr.sendMessage("Your axe has disappeared.");
			super.stop();
			return true;
		}
		if (plr.playerLevel[Player.playerWoodcutting] < hatchet.getLevelRequired()) {
			plr.sendMessage("You no longer have the level required to operate this hatchet.");
			super.stop();
			return true;
		}
		if (plr.getItems().freeSlots() == 0) {
			plr.sendMessage("You have run out of free inventory space.");
			super.stop();
			return true;
		}
		return false;
	}

	private void handleWildernessRewards() {

		if (Boundary.isIn(plr, Boundary.RESOURCE_AREA)) {
			if (Misc.random(20) == 5) {
				int randomAmount = 1;
				plr.sendMessage("You received " + randomAmount + " pkp while woodcutting!");
				plr.getItems().addItem(2996, randomAmount);
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
		if (plr.fasterCluesScroll) {
			dropRate = dropRate*2;
		}
		if (Hespori.activeGolparSeed) {
			clueAmount = 2;
		}
			if(Misc.random(tree.getPetChance() / dropRate) == 10){
				int npcid = 6409;
				NPCSpawning.spawn(npcid, plr.getX(), plr.getY(), plr.getHeight(), 4, 7, true);
				plr.sendMessage("@red@A "+ NPCCacheDefinition.forID(npcid).getName().toLowerCase()+" Has spawned. ");
		}
		if(Misc.random(tree.getPetChance() / dropRate) == 10){
			int npcid = 6411;
			NPCSpawning.spawn(npcid, plr.getX(), plr.getY(), plr.getHeight(), 4, 7, true);
			plr.sendMessage("@red@A "+ NPCCacheDefinition.forID(npcid).getName().toLowerCase()+" Has spawned. ");
		}
		if(Misc.random(tree.getPetChance() / dropRate) == 10){
			int npcid = 6413;
			NPCSpawning.spawn(npcid, plr.getX(), plr.getY(), plr.getHeight(), 4, 7, true);
			plr.sendMessage("@red@A "+ NPCCacheDefinition.forID(npcid).getName().toLowerCase()+" Has spawned. ");
		}
		if (Misc.random(500) == 1) {
			plr.getItems().addItemUnderAnyCircumstance(lumberjackOutfit[Misc.random(lumberjackOutfit.length - 1)], 1);
			plr.sendMessage("You notice a lumberjack piece falling from the tree and pick it up.");
		}

		int petRate = plr.skillingPetRateScroll ? (int) (tree.getPetChance() * .75) : tree.getPetChance();
		if (Misc.random(petRate) == 2 && plr.getItems().getItemCount(13322, false) == 0 && plr.petSummonId != 13322) {
			PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] @cr20@ <col=255>" + plr.getDisplayName() + "</col> chopped down a tree for the <col=CC0000>Beaver</col> pet!");
			plr.getItems().addItemUnderAnyCircumstance(13322, 1);
			plr.getCollectionLog().handleDrop(plr, 5, 13322, 1);
		}
	}

	@Override
	public void stop() {
		super.stop();
		if (plr != null) {
			plr.startAnimation(65535);
		}
	}

}
