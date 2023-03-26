package io.shadowrealm.content.skills.mining;

import io.shadowrealm.Server;
import io.shadowrealm.content.ShootingStar;
import io.shadowrealm.content.SkillcapePerks;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.achievement_diary.impl.*;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.content.skills.smithing.Smelting;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.collisionmap.WorldObject;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.model.world.objects.GlobalObject;
import io.shadowrealm.util.Location3D;
import io.shadowrealm.util.Misc;
import org.apache.commons.lang3.RandomUtils;

import java.util.Optional;

/**
 * Represents a singular event that is executed when a player attempts to mine.
 *
 * @author Jason MacKeigan
 * @date Feb 18, 2015, 6:17:11 PM
 */
public class MiningEvent extends Event<Player> {

    public static int[] prospectorOutfit = {12013, 12014, 12015, 12016, 25549, 25551, 25553, 25555};

    /**
     * The amount of cycles that must pass before the animation is updated
     */
    private final int ANIMATION_CYCLE_DELAY = 3;

    /**
     * The value in cycles of the last animation
     */
    private int lastAnimation;

    /**
     * The pickaxe being used to mine
     */
    private final Pickaxe pickaxe;

    /**
     * The mineral being mined
     */
    private final Mineral mineral;

    /**
     * The object that we are mning
     */
    private int objectId;

    /**
     * The location of the object we're mining
     */
    private final Location3D location;

    /**
     * The npc the player is mining, if any
     */
    private NPC npc;

    /**
     * Constructs a new {@link MiningEvent} for a single player
     *
     * @param plr      the player this is created for
     * @param objectId the id value of the object being mined from
     * @param location the location of the object being mined from
     * @param mineral  the mineral being mined
     * @param pickaxe  the pickaxe being used to mine
     */
    public MiningEvent(Player plr, int objectId, Location3D location, Mineral mineral, Pickaxe pickaxe, int time) {
        super("skilling", plr, time);
        this.objectId = objectId;
        this.location = location;
        this.mineral = mineral;
        this.pickaxe = pickaxe;
    }

    /**
     * Constructs a new {@link MiningEvent} for a single player
     *
     * @param plr      the player this is created for
     * @param npc      the npc being from from
     * @param location the location of the npc
     * @param mineral  the mineral being mined
     * @param pickaxe  the pickaxe being used to mine
     */
    public MiningEvent(Player plr, NPC npc, Location3D location, Mineral mineral, Pickaxe pickaxe, int time) {
        super("skilling", plr, time);
        this.npc = npc;
        this.location = location;
        this.mineral = mineral;
        this.pickaxe = pickaxe;
    }

    @Override
    public void update() {
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(pickaxe.getItemId()) && !plr.getItems().isWearingItem(pickaxe.getItemId())) {
            plr.sendMessage("That is strange! The pickaxe could not be found.");
            stop();
            return;
        }

        if (plr.getItems().playerHasItem(9699, 250)) {
            plr.getDH().sendStatement("You have 250 burned essence please use them before continuing.");
            plr.nextChat = -1;
            return;
        }
        if (plr.getItems().playerHasItem(9698, 250)) {
            plr.getDH().sendStatement("You have 250 burning ore please burn them before continuing.");
            plr.nextChat = -1;
            return;
        }
        if (plr.getItems().freeSlots() == 0) {
            plr.getDH().sendStatement("You have no more free slots.");
            stop();
            return;
        }
//		if (RandomUtils.nextInt(1, 300) == 1 && plr.getInterfaceEvent().isExecutable()) {
//			plr.getInterfaceEvent().execute();
//			stop();
//			return;
//		}
        if (objectId > 0) {
            if (Server.getGlobalObjects().exists(mineral.getDepleteObject(), location.getX(), location.getY(), location.getZ()) && mineral.isDepletable()) {
                plr.sendMessage("This vein contains no more minerals.");
                stop();
                return;
            }
        } else {
            if (npc == null || npc.isDead()) {
                plr.sendMessage("This vein contains no more minerals.");
                stop();
                return;
            }
        }
        if (super.getElapsedTicks() - lastAnimation > ANIMATION_CYCLE_DELAY) {
            plr.startAnimation(pickaxe.getAnimation());
            lastAnimation = super.getElapsedTicks();
        }
    }

    private static void foeArtefact(Player player) {
        int chance = 250;
        int artefactRoll = Misc.random(100);
        if (Misc.random(chance) == 1) {
            if (artefactRoll < 65) {//1/300
                player.getItems().addItemUnderAnyCircumstance(11180, 1);//ancient coin foe for 200
                player.sendMessage("You found a coin that can be used in the Fire of Exchange!");
            } else if (artefactRoll >= 65 && artefactRoll < 99) {//1/600
                player.getItems().addItemUnderAnyCircumstance(681, 1);//anicent talisman foe for 300
                player.sendMessage("You found a talisman that can be used in the Fire of Exchange!");
            } else if (artefactRoll > 99) {//1/1000
                player.getItems().addItemUnderAnyCircumstance(9034, 1);//golden statuette foe for 500
                PlayerHandler.executeGlobalMessage("@bla@[@red@Mining@bla@]@blu@ " + player.getDisplayName() + " @red@just found a Golden statuette while mining!");
            }
        }
    }

    @Override
    public void execute() {
        double osrsExperience = 0;
        int pieces = 0;
        int face = 0;
        for (int i = 0; i < prospectorOutfit.length; i++) {
            if (plr.getItems().isWearingItem(prospectorOutfit[i])) {
                pieces += 6;
            }
        }
        if (plr.getItems().isWearingItem(25541)) {
            pieces += 4;
        }
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }
        if (mineral.isDepletable()) {
            Optional<WorldObject> worldObject = plr.getRegionProvider().get(location.getX(), location.getY()).getWorldObject(objectId, location.getX(), location.getY(), 0);
            if (worldObject.isPresent()) {
                face = worldObject.get().getFace();
            }
            if (RandomUtils.nextInt(0, mineral.getDepletionProbability()) == 0
                    || mineral.getDepletionProbability() == 0) {
                if (objectId > 0) {
                    Server.getGlobalObjects().add(new GlobalObject(mineral.getDepleteObject(), location.getX(), location.getY(),
                            location.getZ(), face, 10, mineral.getRespawnRate(), objectId));
                } else {
                    npc.setDead(true);
                    npc.actionTimer = 0;
                    npc.needRespawn = false;
                }
            }
        }
        if (objectId == 41223 || objectId == 41224 || objectId == 41225 || objectId == 41226 || objectId == 41227 || objectId == 41228 || objectId == 41229) {
            if (ShootingStar.CRASHED_STAR == null || ShootingStar.CRASHED_STAR.getStarObject().getPickAmount() >= ShootingStar.MAXIMUM_MINING_AMOUNT) {
                return;
            } else {
                ShootingStar.CRASHED_STAR.getStarObject().incrementPickAmount();
            }
        }
        plr.facePosition(location.getX(), location.getY());
        Achievements.increase(plr, AchievementType.MINE, 1);
        foeArtefact(plr);
        if (Boundary.isIn(plr, Boundary.RESOURCE_AREA)) {
            if (Misc.random(20) == 5) {
                int randomAmount = 1;
                plr.sendMessage("You received " + randomAmount + " pkp while mining!");
                plr.getItems().addItem(2996, randomAmount);
            }
        }
        if (mineral.equals(Mineral.IRON) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_IRON_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(Mineral.COPPER) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_COPPER_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(Mineral.COAL) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_COAL_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(mineral.GOLD) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_GOLD_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(mineral.GOLD2) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_GOLD_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(Mineral.ADAMANT) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_ADAMANT_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        if (mineral.equals(Mineral.RUNE) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_RUNE_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
		if (mineral.equals(Mineral.AMETHYST) && plr.currentDailyTask.getTaskName().equals(DailyTaskData.MINE_AMATHYST_ORE.getDailyTask().getTaskName())) {
            System.out.println("Mining iron ore");
            DailyTaskHandler.Companion.handleProgress(plr, 1);
        }
        /**
         * Experience calculation
         */
        osrsExperience = mineral.getExperience() + mineral.getExperience() / 10 * pieces;
        plr.getPA().addSkillXPMultiplied((int) osrsExperience, Skill.MINING.getId(), true);
        //System.out.println("player : " + plr.getLoginName() + " is mining mineral: " + mineral);
        switch (mineral) {
            case ADAMANT:

                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_ADAMANT_ORE.getDailyTask().getTaskName())) {
                    //System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                break;
            case AMETHYST:
            case ADAMANT2:
                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_AMATHYST_ORE.getDailyTask().getTaskName())) {
                    //System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }

            case COAL:
            case COAL2:
                if (Boundary.isIn(plr, Boundary.RELLEKKA_BOUNDARY)) {
                    plr.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.MINE_COAL_FREM);
                }
                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_COAL_ORE.getDailyTask().getTaskName())) {
                    //System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                break;
            case COPPER:

                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_COPPER_ORE.getDailyTask().getTaskName())) {
                    //System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                break;
            case ESSENCE:
                plr.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.MINE_ESSENCE);
                break;
            case GEM:
                plr.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.MINE_GEM_FAL);
                break;
            case GOLD:
            case GOLD2:
                //System.out.println("MINING GOLD BAYBAYYYYYYYYYY");

                if (Boundary.isIn(plr, Boundary.TZHAAR_CITY_BOUNDARY)) {
                    plr.getDiaryManager().getKaramjaDiary().progress(KaramjaDiaryEntry.MINE_GOLD_KAR);
                }
                if (Boundary.isIn(plr, Boundary.RELLEKKA_BOUNDARY)) {
                }
                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_GOLD_ORE.getDailyTask().getTaskName())) {
                   // System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                break;
            case IRON:
            case IRON2:
                if (plr.getPosition().inWild()) {
                    plr.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.MINE_IRON_WILD);
                }
                if (Boundary.isIn(plr, Boundary.VARROCK_BOUNDARY)) {
                    plr.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.MINE_IRON);
                }
                if (Boundary.isIn(plr, Boundary.AL_KHARID_BOUNDARY)) {
                    plr.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.MINE_IRON_LUM);
                }
                if (plr.getCurrentDailyTask().getTaskName().equals(DailyTaskData.MINE_IRON_ORE.getDailyTask().getTaskName())) {
                   // System.out.println("MINING Task - gold thingy......");

                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                break;
            case MITHRIL2:
                if (plr.getPosition().inWild()) {
                    plr.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.MINE_MITHRIL_WILD);
                }
                break;
            case TIN:
                break;
            case CLAY:
            case CLAY2:
                plr.getDiaryManager().getDesertDiary().progress(DesertDiaryEntry.MINE_CLAY);
                break;
            default:
                break;

        }
        int amount = SkillcapePerks.MINING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr) ? 1 : 2;
        if (!(mineral.getBarName().contains("star"))) {
            plr.getItems().addItem(mineral.getMineralReturn().generate(), amount);
            plr.sendMessage("You manage to mine some " + ItemCacheDefinition.forID(mineral.getMineralReturn().generate()).getName().toLowerCase() + ".");
        } else {
                if (ShootingStar.MAXIMUM_MINING_AMOUNT == 0) {
                    plr.sendMessage("The Star run out of star dust.");
                    ShootingStar.despawn(false);
                    plr.getPA().stopSkilling();
                    ShootingStar.MAXIMUM_MINING_AMOUNT = 600;
                    plr.stopAnimation();
                    stop();
                    return;
                }
            if (plr.getItems().isWearingItem(Items.MINING_CAPE)) {
                plr.getItems().addItem(25527, Misc.random(1, 2));
            }
                ShootingStar.MAXIMUM_MINING_AMOUNT -= 1;
                //plr.sendMessage(""+ShootingStar.MAXIMUM_MINING_AMOUNT);
                if (Misc.random(200) == 5) {
                    plr.sendMessage("You received Star fragment while mining the star!");
                    plr.getItems().addItem(25547, 1);
                }
        }
        int itemId = mineral.getMineralReturn().generate();
        if ((SkillcapePerks.MINING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr)) && plr.getItems().freeSlots() < 2) {
            plr.sendMessage("You have run out of inventory space.");
            stop();
            return;
        }
        if (plr.getItems().playerHasItem(9699, 250)) {
            plr.getDH().sendStatement("You have 250 burned essence please use them before continuing.");
            plr.nextChat = -1;
            return;
        }
        if (plr.getItems().playerHasItem(9698, 250)) {
            plr.getDH().sendStatement("You have 250 burning ore please burn them before continuing.");
            plr.nextChat = -1;
            return;
        }


        if (mineral == Mineral.GEM) {
            if (itemId == 6571) {
                PlayerHandler.executeGlobalMessage("@pur@" + plr.getDisplayNameFormatted() + " received a drop: " +
                        "" + ItemDef.forId(itemId).getName() + " x " + amount + " from a <col=E9362B>Gem Rock</col>@pur@.");
            }
        }

        if (!mineral.getBarName().equalsIgnoreCase("none")) {
            if (Misc.random(2) == 0) {
                if (plr.getItems().isWearingItem(13243) || plr.getItems().playerHasItem(13243)) {
                    Smelting.startSmelting(plr, mineral.getBarName(), "ONE", "INFERNAL");
                    return;
                }
            }
        }
        int dropRate = 20;
        switch (mineral) {
            case ADAMANT:
            case COAL:
            case GEM:
            case GOLD:
            case MITHRIL:
            case RUNE:
            case AMETHYST:
                dropRate = 60;
                break;
            case COPPER:
            case IRON:
            case TIN:
            case CLAY:
            case ESSENCE:
                dropRate = 10;
                break;
            default:
                break;
        }
        if (plr.fasterCluesScroll) {
            dropRate = dropRate * 2;
        }
        int clueAmount = 1;
        if (Hespori.activeGolparSeed) {
            clueAmount = 2;
        }
        if (Misc.random(mineral.getPetChance() / dropRate) == 10) {
            switch (Misc.random(2)) {
                case 0:
                    plr.getItems().addItemUnderAnyCircumstance(2677, clueAmount);
                    break;

                case 1:
                    plr.getItems().addItemUnderAnyCircumstance(2801, clueAmount);
                    break;
                case 2:
                    plr.getItems().addItemUnderAnyCircumstance(2722, clueAmount);
                    break;

            }
            plr.sendMessage("@blu@You appear to see a clue geode fall within the rock, and pick it up.");
        }

        if (Misc.random(mineral.getPetChance()) / dropRate == 10) {
            plr.getItems().addItemUnderAnyCircumstance(20362, clueAmount);
            plr.sendMessage("@blu@You appear to see a clue geode fall within the rock, and pick it up.");
        }

        int petRate = plr.skillingPetRateScroll ? (int) (mineral.getPetChance() * .75) : mineral.getPetChance();
        if (Misc.random(petRate) == 2 && plr.getItems().getItemCount(13321, false) == 0
                && plr.petSummonId != 7439) {
            PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] @cr20@ <col=255>" + plr.getDisplayName()
                    + "</col> mined a rock and formed the <col=CC0000>Rock golem</col> pet!");
            plr.getCollectionLog().handleDrop(plr, 5, 13321, 1);
            plr.getItems().addItemUnderAnyCircumstance(13321, 1);
        }
    }

    @Override
    public void stop() {
        super.stop();
        if (plr == null) {
            return;
        }
        plr.stopAnimation();
    }
}
