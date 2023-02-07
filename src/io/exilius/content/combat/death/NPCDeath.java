package io.exilius.content.combat.death;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.FremennikDiaryEntry;
import io.exilius.content.achievement_diary.impl.MorytaniaDiaryEntry;
import io.exilius.content.bosses.Hunllef;
import io.exilius.content.bosses.Kraken;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.bosses.nightmare.NightmareConstants;
import io.exilius.content.bosses.wildypursuit.FragmentOfSeren;
import io.exilius.content.bosses.wildypursuit.TheUnbearable;
import io.exilius.content.bosspoints.BossPoints;
import io.exilius.content.combat.death.kill_limiter.KillLimitHandler;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.events.monsterhunt.MonsterHunt;
import io.exilius.content.minigames.warriors_guild.AnimatedArmour;
import io.exilius.content.skills.Skill;
import io.exilius.model.Npcs;
import io.exilius.model.definitions.ItemDef;
import io.exilius.model.definitions.NpcDef;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.npc.drops.DropManager;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.player.PathFinder;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Position;
import io.exilius.model.items.EquipmentSet;
import io.exilius.model.items.GameItem;
import io.exilius.util.Location3D;
import io.exilius.util.Misc;
import io.exilius.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.stream.IntStream;

public class NPCDeath {

    public static void dropItems(NPC npc) {
        Player c = PlayerHandler.players[npc.killedBy];
        if (c != null) {
            if (KillLimitHandler.killHandlerEnabled) KillLimitHandler.Companion.handleNpcDeath(c, npc.getNpcId());
            dropItemsFor(npc, c, npc.getNpcId());
        }
    }

    public static void dropItemsFor(NPC npc, Player player, int npcId) {
        if (player.getTargeted() != null && npc.equals(player.getTargeted())) {
            player.setTargeted(null);
            player.getPA().sendEntityTarget(0, npc);
        }
        player.getAchievements().kill(npc);
        PetHandler.rollOnNpcDeath(player, npc);
        if (npcId >= 1610 && npcId <= 1612) {
            //	c.setArenaPoints(c.getArenaPoints() + 1);
            player.getQuestTab().updateInformationTab();
            //	c.sendMessage("@red@You gain 1 point for killing the Mage! You now have " + c.getArenaPoints()
            //+ " Arena Points.");
        }

        switch (npcId) {

            case 7781:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_REV_IMP.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7249:
            case 423:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DUST_DEVILS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2052:
            case 1432:
            case 2048:
            case 2049:
            case 2050:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_BLACK_DEMONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 100:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ROCKCRABS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5935:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SANDCRABS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7261:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_HILL_GIANTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2090:
            case 2091:
            case 2092:
            case 2093:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_MOSS_GIANTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2791:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_COWSS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5370:
            case 85:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_GHOSTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 421:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ROCK_SLUG.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 481:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CAVE_BUG.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 136:
            case 1153:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_OGRES.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 414:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_BANSHEES.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 448:
            case 453:
            case 454:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CRAWLING_HANDINGS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7768:
            case 264:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_GREEN_DRAGONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 924:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SKELETONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6594:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ENTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6604:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_MAMOTHS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7932:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_REV_PYREFRIEND.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 484:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_BLOODVELD.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7277:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_WRAP_JELLY.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 11238:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DUST_DEVILS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5779:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_GIANT_MOLE.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 8:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_NECHRAELS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 465:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SKELETON_WYVERNS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 498:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SMOKE_DEVILS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 239:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_KBD.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7937:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_REVENANT_ORK.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 8704:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_BLUE_DRAGONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7573:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_LIZARD_SHAMEN.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2075:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_FIRE_GIANTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5874:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_BLACK_DEMONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 104:
            case 135:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_HELL_HOUNDS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5862:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CERBERUS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 11239:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_FIRE_GIANTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 415:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ABYSSAL_DEMONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 4005:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DARK_BEASTS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6593:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_LAVA_DRAGONS.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2042:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ZULRAH.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6640:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_KRAKEN.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6619:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CHAOS_FANATIC.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2054:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CHAOS_ELEMENTAL.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6615:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SCORPIA.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 3162:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_KREEARRA.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2215:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_GENERAL_GRAARDOR.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6493:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_COMMANDER_ZILY.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 6495:
            case 3129:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_Kril.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 8615:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_A_HYDRA.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 11278:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_NEX.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 9425:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_THE_NIGHTMARE.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 8026:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_VORKATH.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 5886:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_ABYSSAL_SIRE.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 319:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_CORP.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 7286:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_SKOTIZIO.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2266:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DAG_PRIME.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2267:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DAG_REX.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case 2265:
                if (player.currentDailyTask.getTaskName().equals(DailyTaskData.KILL_DAG_SUPREME.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;

        }

        if (npcId == 2266 || npcId == 2267 || npcId == 2265) {
            player.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.KILL_DAGANNOTH_KINGS);
        }
        if (npcId == 411) {
            player.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.KILL_KURASK);
        }

        if (npcId == 9021 || npcId == 9022 || npcId == 9023 || npcId == 9024) {
            player.hunllefDead = true;
            //PlayerHandler.executeGlobalMessage("@red@[EVENT]@blu@ Hunllef has been defeated by @bla@" + c.playerName + "@blu@!");
            Hunllef.rewardPlayers(player);
        }
        if (npcId == 1047) {
            player.getDiaryManager().getMorytaniaDiary().progress(MorytaniaDiaryEntry.KILL_CAVE_HORROR);
        }

        if (npcId == 1673) { //barrows npcs
            Achievements.increase(player, AchievementType.DH_KILLS, 1);
        }
        if (npcId == 5744 || npcId == 5762) {
            player.setShayPoints(player.getShayPoints() + 1);
            player.sendMessage("@red@You gain 1 point for killing the Penance! You now have " + player.getShayPoints()
                    + " Assault Points.");
        }

        if (npc.getNpcId() == 3162 || npc.getNpcId() == 3129 || npc.getNpcId() == 2205 || npc.getNpcId() == 2215) {
            player.getEventCalendar().progress(EventChallenge.KILL_X_GODWARS_BOSSES_OF_ANY_TYPE);
        }
        if (npc.getNpcId() == 9293) {
            player.getEventCalendar().progress(EventChallenge.KILL_BASILISK_KNIGHTS_X_TIMES);
        }

        if (npc.getNpcId() == 2042 || npc.getNpcId() == 2043 || npc.getNpcId() == 2044) {
            player.getEventCalendar().progress(EventChallenge.KILL_ZULRAH_X_TIMES);
        }
        if (npc.getNpcId() == 9021) {
            player.getEventCalendar().progress(EventChallenge.KILL_HUNLLEF_X_TIMES);
        }
        if (IntStream.of(DropManager.wildybossesforgiveaway).anyMatch(id -> id == npc.getNpcId()) && player.getPosition().inWild()) {
            player.getEventCalendar().progress(EventChallenge.KILL_X_WILDY_BOSSES);
        }
        if (npcId >= 7931 && npcId <= 7940) {
            player.getEventCalendar().progress(EventChallenge.KILL_X_REVS_IN_WILDY);
        }

        if (npcId == Npcs.CORPOREAL_BEAST) {
            NPCHandler.kill(Npcs.DARK_ENERGY_CORE, npc.heightLevel);
        }
        if (npcId == 7278) {
            if ((player.getSlayer().getTask().isPresent() && player.getSlayer().getTask().get().getPrimaryName().equals("nechryael"))) {
                player.getPA().addSkillXPMultiplied(100, Skill.SLAYER.getId(), true);
            }
        }

        if (npcId == Npcs.DUSK_9) {
            Achievements.increase(player, AchievementType.GROTESQUES, 1);
        }
        if (npcId == Npcs.ALCHEMICAL_HYDRA_7) {
            Achievements.increase(player, AchievementType.HYDRA, 1);
        }

        if (npcId == 2266 || npcId == 2267 || npcId == 2265) {
            if ((player.getSlayer().getTask().isPresent() && player.getSlayer().getTask().get().getPrimaryName().equals("dagannoth"))) {
                player.getPA().addSkillXPMultiplied(165, Skill.SLAYER.getId(), true);
            }
        }

        if (npcId == 1673 || npcId == 1674 || npcId == 1677 || npcId == 1676 || npcId == 1675
                || npcId == 1672) {//barrows
            Achievements.increase(player, AchievementType.BARROWS_KILLS, 1);
            player.getEventCalendar().progress(EventChallenge.KILL_X_BARROWS_BROTHERS);
            if (EquipmentSet.AHRIM.isWearingBarrows(player) || EquipmentSet.KARIL.isWearingBarrows(player)
                    || EquipmentSet.DHAROK.isWearingBarrows(player)
                    || EquipmentSet.VERAC.isWearingBarrows(player)
                    || EquipmentSet.GUTHAN.isWearingBarrows(player)
                    || EquipmentSet.TORAG.isWearingBarrows(player)) {
                player.getDiaryManager().getMorytaniaDiary().progress(MorytaniaDiaryEntry.BARROWS_CHEST);
            }
        }
        if (npcId == 7144 || npcId == 7145 || npcId == 7146) {
            player.getEventCalendar().progress(EventChallenge.KILL_X_DEMONIC_GORILLAS, 1);
        }

        if (npcId == 1739 || npcId == 1740 || npcId == 1741 || npcId == 1742)
        {
            player.getEventCalendar().progress(EventChallenge.GAIN_X_PEST_CONTROL_POINTS, 7);
            player.pcPoints += 7;
        }

        if (npcId == FragmentOfSeren.NPC_ID && npc.getHealth().getCurrentHealth() <= 0) {
            // Player p = PlayerHandler.players[npc.killedBy];
            int randomPkp = Misc.random(15) + 10;
            player.pkp += randomPkp;
            player.getQuestTab().updateInformationTab();
            MonsterHunt.setCurrentLocation(null);
            player.sendMessage("Well done! You killed Seren!");
            player.sendMessage("You received: " + randomPkp + " PK Points for killing the wildy boss.");

        }

        if (npcId == TheUnbearable.NPC_ID && npc.getHealth().getCurrentHealth() <= 0) {
            int randomPkp = Misc.random(15) + 10;
            player.pkp += randomPkp;
            player.getQuestTab().updateInformationTab();
            MonsterHunt.setCurrentLocation(null);
            player.sendMessage("Well done! You killed The Unbearable!");
            player.sendMessage("You received: " + randomPkp + " PK Points for killing the wildy boss.");
        }

        if (npcId == Hespori.NPC_ID && npc.getHealth().getCurrentHealth() <= 0) {
            player.getQuestTab().updateInformationTab();
            player.sendMessage("Well done! You killed Hespori!");
        }
        int dropX = npc.absX;
        int dropY = npc.absY;
        int dropHeight = npc.heightLevel;

        if (!PathFinder.getPathFinder().accessable(player, dropX, dropY)) {
            for (Position border : npc.getBorder()) {
                if (PathFinder.getPathFinder().accessable(player, dropX, dropY)) {
                    dropX = border.getX();
                    dropY = border.getY();
                    break;
                }
            }
        }

        if (npcId == 492 || npcId == 494 || npcId == NightmareConstants.NIGHTMARE_ACTIVE_ID || npcId == Npcs.VORKATH) {
            dropX = player.absX;
            dropY = player.absY;
        }
        if (npcId == 2042 || npcId == 2043 || npcId == 2044
                || npcId == 6720) {
            dropX = 2268;
            dropY = 3069;
            player.getItems().addItem(12938, 1);
            player.getZulrahEvent().stop();
        }
        if (npcId == Kraken.KRAKEN_ID) {
            dropX = 2280;
            dropY = 10031;
        }

        /**
         * Warriors guild
         */
        player.getWarriorsGuild().dropDefender(npc.absX, npc.absY);
        if (AnimatedArmour.isAnimatedArmourNpc(npcId)) {
            if (npc.getX() == 2851 && npc.getY() == 3536) {
                dropX = 2851;
                dropY = 3537;
                AnimatedArmour.dropTokens(player, npcId, npc.absX, npc.absY + 1);
            } else if (npc.getX() == 2857 && npc.getY() == 3536) {
                dropX = 2857;
                dropY = 3537;
                AnimatedArmour.dropTokens(player, npcId, npc.absX, npc.absY + 1);
            } else {
                AnimatedArmour.dropTokens(player, npcId, npc.absX, npc.absY);
            }
        }


        Location3D location = new Location3D(dropX, dropY, dropHeight);
        int amountOfDrops = 1;
        if (isDoubleDrops()) {
            amountOfDrops++;
        }

        int bossPoints = BossPoints.getPointsOnDeath(npc);
        BossPoints.addPoints(player, bossPoints, false);

        if (NpcDef.forId(npcId).getCombatLevel() >= 1) {
            player.getNpcDeathTracker().add(NpcDef.forId(npcId).getName(), NpcDef.forId(npcId).getCombatLevel(), bossPoints);
        }

        Server.getDropManager().create(player, npc, location, amountOfDrops, npcId);
    }

    public static void announce(Player player, GameItem item, int npcId) {
        if (!player.getDisplayName().equalsIgnoreCase("thimble") && !player.getDisplayName().equalsIgnoreCase("top hat")) {
            announceKc(player, item, player.getNpcDeathTracker().getKc(NpcDef.forId(npcId).getName()));
        }
    }

    @SneakyThrows
    public static void announceKc(Player player, GameItem item, int kc) {
        PlayerHandler.executeGlobalMessage("@pur@" + player.getDisplayNameFormatted() + " received a drop: " +
                "" + ItemDef.forId(item.getId()).getName() + " x " + item.getAmount() + " at <col=E9362B>" + kc  + "</col>@pur@ kills.");
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("[Drop System]");
        eb.setDescription(player.getDisplayName() + " Has received a drop: " +
                "" + ItemDef.forId(item.getId()).getName() + " x " + item.getAmount());
        eb.setFooter("at " + kc + " KC!");
        eb.setImage("https://media.tenor.com/JbLU0gejMa8AAAAC/thumbs-up.gif");
        eb.setColor(new java.awt.Color(0, 255, 255));
        Discord.getJDA().getTextChannelById("1064970750408265878").sendMessageEmbeds(eb.build()).queue();
    }

    public static boolean isDoubleDrops() {
        return (Configuration.DOUBLE_DROPS_TIMER > 0 || Configuration.DOUBLE_DROPS);
    }
}
