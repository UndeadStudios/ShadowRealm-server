package io.shadowrealm.content.skills.fishing;

import io.shadowrealm.Server;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.achievement_diary.impl.FaladorDiaryEntry;
import io.shadowrealm.content.achievement_diary.impl.KandarinDiaryEntry;
import io.shadowrealm.content.achievement_diary.impl.KaramjaDiaryEntry;
import io.shadowrealm.content.achievement_diary.impl.WildernessDiaryEntry;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.items.ItemAssistant;
import io.shadowrealm.util.Misc;

import java.util.Random;

/**
 * Fishing
 *
 * @author Micheal/01053 https://www.rune-server.org/members/01053/
 */

public class Fishing {

    public static final Random random = new Random();

    public static int[] anglerOuftit = { 13258, 13259, 13260, 13261 };
    private static boolean hasFishingEquipment(Player c, int equipment) {
        if (!c.getItems().playerHasItem(equipment)) {

            if(equipment == 311 || equipment == 21028) {
                if(c.getItems().playerHasItem(21028) || c.playerEquipment[3] == 21028) {
                    return true;
                }
                if(c.getItems().playerHasItem(21031) || c.playerEquipment[3] == 21031) {
                    return true;
                }
                if(c.getItems().playerHasItem(10129) || c.playerEquipment[3] == 10129) {
                    return true;
                }
            }

            c.sendMessage("You need a " + ItemAssistant.getItemName(equipment) + " to fish here.");
            return false;
        }
        return true;
    }
    public static void startFishing(Player c, int npcId, int i) {
        startFishing(c, npcId, i, false);
    }

    public static void startFishing(Player player, int npcId, int i, boolean cont) {
        FishingData.data fishing = FishingData.data.fishingSpots.get(npcId);
        if (player.getItems().freeSlots() == 0) {
            player.getDH().sendStatement("You have no more free slots.");
            return;
        }
        if (player.playerLevel[10] < fishing.getRequirement()) {
            player.sendMessage("You need a fishing level of " + fishing.getRequirement() + " to fish this fish.");
            return;
        }
        if (!hasFishingEquipment(player, fishing.getItemReq())) {
            return;
        }
        if ((!player.getItems().playerHasItem(fishing.getBait()) && fishing.getBait() != 314) ||
                (fishing.getBait() == 314 && (!player.getItems().playerHasItem(2950) && (!player.getItems().playerHasItem(fishing.getBait())))))  {
            player.sendMessage("You need a " + player.getItems().getItemName(fishing.getBait()) + " to catch this fish!");
            return;
        }
        if (player.stopPlayerPacket) {
            return;
        }
        player.alreadyFishing = true;
        player.stopPlayerPacket = true;
        if(!cont)
            player.sendMessage("You start fishing.");
        player.fishingNpc = i;
        int x = Server.npcHandler.npcs[i].getX();
        int y = Server.npcHandler.npcs[i].getY();
        player.startAnimation(fishing.getAnimation());
        CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {

            @Override
            public void execute(CycleEventContainer container) {
                if (player == null || player.isDisconnected() || player.teleporting || player.isDead) {
                    container.stop();
                    return;
                }
                if (player.getItems().freeSlots() == 0) {
                    player.getDH().sendStatement("You have no more free slots.");
                    container.stop();
                    return;
                }
                if (!hasFishingEquipment(player, fishing.getItemReq())) {
                    container.stop();
                    return;
                }
                if(player.fishingNpc == -1 || NPCHandler.npcs[player.fishingNpc] == null) {
                    player.alreadyFishing = false;
                    container.stop();
                    if(player.debugMessage) {
                        player.sendMessage("fishing test 1");
                    }
                    return;
                }
                if (!player.alreadyFishing) {
                    container.stop();
                    if(player.debugMessage) {
                        player.sendMessage("fishing test 2");
                    }
                    return;
                }
                if (!player.stopPlayerPacket) {
                    container.stop();
                    if(player.debugMessage) {
                        player.sendMessage("fishing test 3");
                    }
                    return;
                }
                int cont = Misc.random(10);
                int chance = Misc.random(3000);
                int petchance = Misc.random(4500);
                int petchance1 = Misc.random(3000);
                Achievements.increase(player, AchievementType.FISH, 1);
                clueBottles(player, chance);
                foeArtefact(player, chance);

                if (petchance1 == 1) {
                    player.getItems().addItemUnderAnyCircumstance(anglerOuftit[Misc.random(anglerOuftit.length - 1)], 1);
                    player.sendMessage("You notice a angler piece floating in the water and pick it up.");
                }
                if (petchance == 2 && player.getItems().getItemCount(13320, true) == 0 && player.petSummonId != 13320) {
                    PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] <col=255>" + player.getDisplayName() + "</col> caught a fish and a <col=CC0000>Heron</col> pet!");
                    player.getItems().addItemUnderAnyCircumstance(13320, 1);
                    player.getCollectionLog().handleDrop(player, 5, 13320, 1);
                }
                if (npcId == fishing.getIdentifier()) {
                    int r = random.nextInt(fishing.getFish().length);

                    //Achievements.increase(player, AchievementType.FISHING, 1);
                    if(fishing.getIdentifier() == 16){
                        player.getItems().addItem(fishing.getFish()[r], Misc.get(1, 150));
                    } else {
                        player.getItems().addItem(fishing.getFish()[r], 1);
                    }
                    if (player.playerEquipment[player.playerHat] == 13258 && player.playerEquipment[player.playerChest] == 13259 && player.playerEquipment[player.playerLegs] == 13260 && player.playerEquipment[player.playerFeet] == 13261) {
                        player.getPA().addSkillXPMultiplied(fishing.getExperience(), 10, true);
                    } else {
                        player.getPA().addSkillXPMultiplied(fishing.getExperience(), 10, true);
                    }
                    if(!(fishing.getBait() == 314 && player.getItems().playerHasItem(2950)) || fishing.getBait() != 314)
                        player.getItems().deleteItem(fishing.getBait(), 1);
                    switch (fishing.getFish()[r]) {
                        case 389:
                            if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                                player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.FISH_MANTA);
                            }
                            break;
                        case 371:
                            if (Boundary.isIn(player, Boundary.CATHERBY_BOUNDARY)) {
                                player.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.FISH_SWORD);
                                DailyTaskHandler.Companion.handleProgress(player,1);
                            }
                            break;

                        case 377:
                            if (Boundary.isIn(player, Boundary.KARAMJA_BOUNDARY)) {
                                player.getDiaryManager().getKaramjaDiary().progress(KaramjaDiaryEntry.FISH_LOBSTER_KAR);
                            }
                            break;
                        case 3142:
                            if (Boundary.isIn(player, Boundary.RESOURCE_AREA_BOUNDARY)) {
                                player.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.KARAMBWAN);
                            }
                            break;
                        case 317:
                            break;
                    }
                    switch (fishing.getFish()[r]) {
                        case 389:
                            if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                                player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.FISH_MANTA);
                            }
                            break;
                    }

                    switch (fishing.getFish()[r]) {
                        case 389:
                            if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                                player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.FISH_MANTA);
                            }
                            break;

                        case 377:
                            if (Boundary.isIn(player, Boundary.KARAMJA_BOUNDARY)) {
                                player.getDiaryManager().getKaramjaDiary().progress(KaramjaDiaryEntry.FISH_LOBSTER_KAR);
                            }
                            break;

                        case 3142:
                            if (Boundary.isIn(player, Boundary.RESOURCE_AREA_BOUNDARY)) {
                                player.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.KARAMBWAN);
                            }
                            break;
                    }
                    player.sendMessage("You catch a <col=0000FF>" + player.getItems().getItemName(fishing.getFish()[r]) + "<col=000000>.");
                    player.alreadyFishing = false;
                    int index = player.fishingNpc;
                    container.stop();
                        startFishing(player, npcId, index, true);

                }
            }


            @Override
            public void onStopped() {
                player.stopPlayerPacket = false;
                player.fishingNpc = -1;
                super.onStopped();
            }

        }, fishing.getIdentifier() + 5 + playerFishingLevel(player));
    }

    private static int playerFishingLevel(Player c) {
        return (10 - (int) Math.floor(c.playerLevel[c.playerFishing] / 10));
    }

    private static void clueBottles(Player player, int petChance) {
        int chance =  petChance/40;
        int easyChance = 50;
        int medChance = 100;
        int rewardAmount = 1;
        if (player.fasterCluesScroll) {
            chance = petChance/80;
            easyChance = 25;
            medChance = 50;
        }
        if (Hespori.activeGolparSeed) {
            rewardAmount = 2;
        }
        int bottleRoll = Misc.random(chance);
        if (Misc.random(chance) == 1) {
            player.sendMessage("You catch a clue bottle!");
            if (bottleRoll < easyChance) {
                player.getItems().addItemUnderAnyCircumstance(2677, rewardAmount);
            } else if (bottleRoll >= easyChance && bottleRoll < medChance) {
                player.getItems().addItemUnderAnyCircumstance(2801, rewardAmount);
            } else {
                player.getItems().addItemUnderAnyCircumstance(2722, rewardAmount);
            }
        }
    }
    private static void foeArtefact(Player player, int petChance) {
        int chance = petChance/24;
        int artefactRoll = Misc.random(100);
        if (Misc.random(chance) == 1) {
            if (artefactRoll <65) {//1/300
                player.getItems().addItemUnderAnyCircumstance(11180, 1);//ancient coin foe for 200
                player.sendMessage("You found a coin that can be used in the Fire of Exchange!");
            } else if (artefactRoll >= 65 && artefactRoll < 99) {//1/600
                player.getItems().addItemUnderAnyCircumstance(681, 1);//anicent talisman foe for 300
                player.sendMessage("You found a talisman that can be used in the Fire of Exchange!");
            } else if (artefactRoll > 99){//1/1000
                player.getItems().addItemUnderAnyCircumstance(9034, 1);//golden statuette foe for 500
                PlayerHandler.executeGlobalMessage("@bla@[@red@Fishing@bla@]@blu@ " + player.getDisplayName() + " @red@just found a Golden statuette while fishing!");
            }
        }
    }
}