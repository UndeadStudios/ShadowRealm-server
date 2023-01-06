package io.exilius.content.skills.woodcutting;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.SkillcapePerks;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.*;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.skills.Skill;
import io.exilius.content.skills.firemake.Firemaking;
import io.exilius.model.Items;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Position;
import io.exilius.model.items.ItemCacheDefinition;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;

import java.util.Optional;

public class WoodcuttingEvent extends Event<Player> {
    private static boolean woodcuttingTree;
    private final Tree tree;
    private final Hatchet hatchet;
    private final int objectId;
    private final int x;
    private final int y;
    private int chops;

    private final int[] lumberjackOutfit = {10933, 10939, 10940, 10941};

    public WoodcuttingEvent(Player player, Tree tree, Hatchet hatchet, int objectId, int x, int y) {
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
        pieces = handleOutfit(pieces);
        osrsExperience = tree.getExperience() + tree.getExperience() / 10 * pieces;
        if (canChop()) return;
//		if (Misc.random(300) == 0 && attachment.getInterfaceEvent().isExecutable()) {
//			attachment.getInterfaceEvent().execute();
//			super.stop();
//			return;
//		}
        chops++;

        int chopChance = 1 + (int) (tree.getChopsRequired() * hatchet.getChopSpeed());
        if (Boundary.isIn(plr, Boundary.WOODCUTTING_GUILD_BOUNDARY)) {
            chopChance *= 1.5;
        }

        if (tree.equals(Tree.HESPORI)) {
            int randomTele = 1;
            if (plr.getItems().playerHasItem(Hespori.KEY)) {
                plr.moveTo(new Position(3072 + randomTele, 3505 + randomTele));
                Hespori.deleteEventItems(plr);
                return;
            }
            int randomTele2 = Misc.random(2);
            plr.canLeaveHespori = true;
            plr.moveTo(new Position(3072 + randomTele2, 3505 + randomTele2, 0));
            //attachment.getPA().teleport(3072 + randomTele2, 3505 + randomTele2, 0, "modern",false);
            plr.getItems().addItem(tree.getWood(), 1);
            if ((Configuration.DOUBLE_DROPS_TIMER > 0 || Configuration.DOUBLE_DROPS) && Misc.random(2) == 1) {
                plr.getItems().addItem(tree.getWood(), 1);
            }

            plr.getPA().addSkillXPMultiplied((int) osrsExperience, Skill.WOODCUTTING.getId(), true);
            handleRewards();
            Hespori.deleteEventItems(plr);
            if (!plr.getMode().isOsrs() && !plr.getMode().is5x()) {
                plr.getPA().addSkillXP(60000, 19, true);
            } else {
                plr.getPA().addSkillXP(3300, 19, true);
            }
            super.stop();
            return;
        }
        if (Misc.random(tree.getChopdownChance()) == 0 || tree.equals(Tree.NORMAL) || tree.equals(Tree.NORMAL2) || tree.equals(Tree.NORMAL3) || tree.equals(Tree.DEAD) || tree.equals(Tree.DEAD2) || tree.equals(Tree.DEAD3) || tree.equals(Tree.DEAD4) && Misc.random(chopChance) == 0) {
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

            Server.getGlobalObjects().add(new GlobalObject(tree.equals(Tree.REDWOOD) ? stumpId : tree.getStumpId(), x, y, plr.heightLevel, face, 10, tree.getRespawnTime(), objectId));
            plr.sendMessage("You get some " + ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase() + ".");
            plr.getItems().addItem(tree.getWood(), 1);
            if (tree.equals(Tree.MAGIC)) {
                plr.getEventCalendar().progress(EventChallenge.CUT_DOWN_X_MAGIC_LOGS);
            }

            if (tree.getWood() == 1511 && plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_NORMAL_TREE.name())) {
                System.out.println("Chopping a normal tree");
                DailyTaskHandler.Companion.handleProgress(plr, 1);
            }
            if (tree.getWood() == 1521 && plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_OAK_TREES.name())) {
                System.out.println("Chopping a oak tree");
                DailyTaskHandler.Companion.handleProgress(plr, 1);
            }
            if (tree.getWood() == 1517 && plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_MAPLE_TREE.name())) {
                System.out.println("Chopping a magic tree");
                DailyTaskHandler.Companion.handleProgress(plr, 1);
            }
            if (tree.getWood() == 1513 && plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_MAGIC_TREE.name())) {
                System.out.println("Chopping a magic tree");
                DailyTaskHandler.Companion.handleProgress(plr, 1);
            }
            if (tree.getWood() == 19669 && plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_REDWOOD_TREE.name())) {
                System.out.println("Chopping a redwood tree");
                DailyTaskHandler.Companion.handleProgress(plr, 1);
            }
            plr.getPA().addSkillXPMultiplied((int) osrsExperience, Skill.WOODCUTTING.getId(), true);
            Achievements.increase(plr, AchievementType.WOODCUT, 1);
            plr.getPA().sendSound(2734);
            handleRewards();

            super.stop();
            return;
        }
        if (!tree.equals(Tree.NORMAL)) {
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
                foeArtefact(plr);
                handleWildernessRewards();
                handleRewards();
                if ((SkillcapePerks.WOODCUTTING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr)) && plr.getItems().freeSlots() < 2) {
                    plr.sendMessage("You have run out of free inventory space.");
                    super.stop();
                    return;
                }
                plr.sendMessage("You get some " + ItemCacheDefinition.forID(tree.getWood()).getName().toLowerCase() + ".");
				int amtToGive = SkillcapePerks.WOODCUTTING.isWearing(plr) || SkillcapePerks.isWearingMaxCape(plr) ? 2 : 1;
                plr.getItems().addItem(tree.getWood(), amtToGive);



            }
        }
        if (super.getElapsedTicks() % 4 == 0) {
            plr.startAnimation(hatchet.getAnimation());
        }
    }

    private int handleOutfit(int pieces) {

        for (int aLumberjackOutfit : lumberjackOutfit) {
            if (plr.getItems().isWearingItem(aLumberjackOutfit)) {
                pieces += 2;
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

    private void handleDiary(Tree tree) {
        switch (tree) {
            case MAGIC:
                plr.getEventCalendar().progress(EventChallenge.CUT_DOWN_X_MAGIC_LOGS, 2);
                if (Boundary.isIn(plr, Boundary.AL_KHARID_BOUNDARY)) {
                    plr.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.CHOP_MAGIC_AL);
                }
                if (Boundary.isIn(plr, Boundary.RESOURCE_AREA_BOUNDARY)) {
                    plr.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.MAGIC_LOG_WILD);
                }
                if (Boundary.isIn(plr, Boundary.SEERS_BOUNDARY)) {
                    plr.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.CUT_MAGIC_SEERS);
                }
                break;
            case MAPLE:
                break;
            case NORMAL:
                break;
            case OAK:
                if (plr.currentDailyTask.getTaskName().equals(DailyTaskData.CHOP_OAK_TREES.name())) {
                    System.out.println("Chopping a fucking oak tree you cunt");
                    DailyTaskHandler.Companion.handleProgress(plr, 1);
                }
                if (Boundary.isIn(plr, Boundary.LUMRIDGE_BOUNDARY) || Boundary.isIn(plr, Boundary.DRAYNOR_BOUNDARY)) {
                    plr.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.CHOP_OAK);
                }
                if (Boundary.isIn(plr, Boundary.RELLEKKA_BOUNDARY)) {
                    plr.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.CHOP_OAK_FREM);
                }
                break;
            case WILLOW:
            case WILLOW2:
                if (Boundary.isIn(plr, Boundary.FALADOR_BOUNDARY)) {
                    plr.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.CHOP_WILLOW);
                }
                if (Boundary.isIn(plr, Boundary.DRAYNOR_BOUNDARY)) {
                    plr.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.CHOP_WILLOW_DRAY);
                }
                break;
            case YEW:
                if (Boundary.isIn(plr, Boundary.FALADOR_BOUNDARY)) {
                    plr.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.CHOP_YEW);
                }
                if (Boundary.isIn(plr, Boundary.VARROCK_BOUNDARY)) {
                    plr.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.YEWS_AND_BURN);
                }
                break;
            case TEAK:
                if (Boundary.isIn(plr, Boundary.DESERT_BOUNDARY)) {
                    plr.getDiaryManager().getDesertDiary().progress(DesertDiaryEntry.CHOP_TEAK);
                }
                break;
            default:
                break;

        }
    }

    private static void foeArtefact(Player player) {
        int chance = 250;
        int artefactRoll = Misc.random(100);
        if (Misc.random(chance) == 1) {
            if (artefactRoll < 65) {//1/300
                player.getItems().addItemUnderAnyCircumstance(11180, 1);//ancient coin foe for 200
                player.sendMessage("You found a coin that can be used in the Fire of Exchange!");
            } else if (artefactRoll >= 65 && artefactRoll <= 99) {//1/600
                player.getItems().addItemUnderAnyCircumstance(681, 1);//anicent talisman foe for 300
                player.sendMessage("You found a talisman that can be used in the Fire of Exchange!");
            } else if (artefactRoll > 99) {//1/1000
                player.getItems().addItemUnderAnyCircumstance(9034, 1);//golden statuette foe for 500
                PlayerHandler.executeGlobalMessage("@bla@[@red@Woodcutting@bla@]@blu@ " + player.getDisplayName() + " @red@just found a Golden statuette while wcing!");
            }
        }
    }

    private void handleRewards() {
        int dropRate = 10;
        int clueAmount = 1;
        if (plr.fasterCluesScroll) {
            dropRate = dropRate * 2;
        }
        if (Hespori.activeGolparSeed) {
            clueAmount = 2;
        }
        if (Misc.random(tree.getPetChance() / dropRate) == 10) {
            switch (Misc.random(2)) {
                case 0:
                    plr.getItems().addItemUnderAnyCircumstance(19712, clueAmount);
                    break;
                case 1:
                    plr.getItems().addItemUnderAnyCircumstance(19714, clueAmount);
                    break;
                case 2:
                    plr.getItems().addItemUnderAnyCircumstance(19716, clueAmount);
                    break;
            }
            plr.sendMessage("@blu@You appear to see a clue nest fall from the tree, and pick it up.");
        }
        if (Misc.random(500) == 1) {
            plr.getItems().addItemUnderAnyCircumstance(lumberjackOutfit[Misc.random(lumberjackOutfit.length - 1)], 1);
            plr.sendMessage("You notice a lumberjack piece falling from the tree and pick it up.");
        }
        if (Misc.random(175) == 1) {
            plr.getItems().addItemUnderAnyCircumstance(Items.BIRD_NEST, 1);
            plr.sendMessage("You notice a Bird's nest falling from the tree and pick it up.");
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
