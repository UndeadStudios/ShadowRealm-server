package io.exilius;

import io.exilius.annotate.Init;
import io.exilius.annotate.PostInit;
import io.exilius.content.boosts.Boosts;
import io.exilius.content.bosses.Nex;
import io.exilius.content.bosses.godwars.GodwarsEquipment;
import io.exilius.content.bosses.godwars.GodwarsNPCs;
import io.exilius.content.bosses.nightmare.NightmareStatusNPC;
import io.exilius.content.bosses.sarachnis.SarachnisNpc;
import io.exilius.content.collection_log.CollectionLog;
import io.exilius.content.combat.NPCSpawns;
import io.exilius.content.combat.stats.TrackedMonster;
import io.exilius.content.commands.CommandManager;
import io.exilius.content.dailyrewards.DailyRewardContainer;
import io.exilius.content.dailyrewards.DailyRewardsRecords;
//import io.exilius.content.donationrewards.DonationReward;
import io.exilius.content.event.eventcalendar.EventCalendar;
import io.exilius.content.event.eventcalendar.EventCalendarWinnerSelect;
import io.exilius.content.events.monsterhunt.MonsterHunt;
import io.exilius.content.fireofexchange.FireOfExchangeBurnPrice;
import io.exilius.content.polls.PollTab;
import io.exilius.content.preset.PresetManager;
import io.exilius.content.referral.ReferralCode;
import io.exilius.content.skills.runecrafting.ouriana.ZamorakGuardian;
import io.exilius.content.tournaments.TourneyManager;
import io.exilius.content.tradingpost.Listing;
import io.exilius.content.trails.TreasureTrailsRewards;
import io.exilius.content.vote_panel.VotePanelManager;
import io.exilius.content.wogw.Wogw;
import io.exilius.content.worldevent.WorldEventContainer;
import io.exilius.model.Npcs;
import io.exilius.model.collisionmap.ObjectDef;
import io.exilius.model.collisionmap.Region;
import io.exilius.model.collisionmap.doors.DoorDefinition;
import io.exilius.model.cycleevent.impl.BonusApplianceEvent;
import io.exilius.model.cycleevent.impl.DidYouKnowEvent;
//import io.exilius.model.cycleevent.impl.LeaderboardUpdateEvent;
import io.exilius.model.cycleevent.impl.UpdateQuestTab;
import io.exilius.model.definitions.AnimationLength;
import io.exilius.model.definitions.ItemDef;
import io.exilius.model.definitions.ItemStats;
import io.exilius.model.definitions.NpcDef;
import io.exilius.model.definitions.NpcStats;
import io.exilius.model.definitions.ShopDef;
import io.exilius.model.entity.npc.NPCRelationship;
import io.exilius.model.entity.npc.NpcSpawnLoader;
import io.exilius.model.entity.npc.stats.NpcCombatDefinition;
import io.exilius.model.entity.player.PlayerFactory;
import io.exilius.model.entity.player.save.PlayerSave;
import io.exilius.model.lobby.LobbyManager;
import io.exilius.model.world.ShopHandler;
import io.exilius.punishments.PunishmentCycleEvent;
import io.exilius.model.entity.player.save.backup.PlayerSaveBackup;
import io.exilius.util.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stuff to do on startup.
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
public class ServerStartup {

    private static final Logger logger = LoggerFactory.getLogger(ServerStartup.class);

    static void load() throws Exception {
        Reflection.getMethodsAnnotatedWith(Init.class).forEach(method -> {
            try {
                method.invoke(null);
            } catch (Exception e) {
                logger.error("Error loading @Init annotated method[{}] inside class[{}]", method, method.getClass(), e);
                e.printStackTrace();
                System.exit(1);
            }
        });

        //DonationReward.load();
        PlayerSave.loadPlayerSaveEntries();
        EventCalendarWinnerSelect.getInstance().init();
        TrackedMonster.init();
        Boosts.init();
        ItemDef.load();
        ShopDef.load();
        ShopHandler.load();
        NpcStats.load();
        ItemStats.load();
        NpcDef.load();
        // Npc Combat Definition must be above npc load
        NpcCombatDefinition.load();
        Server.npcHandler.init();
        NPCRelationship.setup();
        EventCalendar.verifyCalendar();
        Server.getPunishments().initialize();
        Server.getEventHandler().submit(new DidYouKnowEvent());
        Server.getEventHandler().submit(new BonusApplianceEvent());
        Server.getEventHandler().submit(new PunishmentCycleEvent(Server.getPunishments(), 50));
        Server.getEventHandler().submit(new UpdateQuestTab());
//        Server.getEventHandler().submit(new LeaderboardUpdateEvent());
        Listing.init();
        Wogw.init();
        PollTab.init();
        DoorDefinition.load();
        GodwarsEquipment.load();
        GodwarsNPCs.load();
        LobbyManager.initializeLobbies();
        VotePanelManager.init();
        TourneyManager.initialiseSingleton();
        TourneyManager.getSingleton().init();
        Server.getDropManager().read();
        TreasureTrailsRewards.load();
        AnimationLength.startup();
        PresetManager.getSingleton().init();
        ObjectDef.loadConfig();
        CollectionLog.init();
        Region.load();
        Server.getGlobalObjects().loadGlobalObjectFile();

        // Keep this below region load and object loading
        NpcSpawnLoader.load();
        //NPCSpawns.loadNPCSpawns();
        MonsterHunt.spawnNPC();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        CommandManager.initializeCommands();
        NightmareStatusNPC.init();
        if (Server.isDebug()) {
            PlayerFactory.createTestPlayers();
        }
        ReferralCode.load();
        DailyRewardContainer.load();
        DailyRewardsRecords.load();
        WorldEventContainer.getInstance().initialise();
        FireOfExchangeBurnPrice.init();
        Server.getLogging().schedule();

        ZamorakGuardian.spawn();
        new SarachnisNpc(Npcs.SARACHNIS, SarachnisNpc.SPAWN_POSITION);

        if (Server.isPublic()) {
            PlayerSaveBackup.start(Configuration.PLAYER_SAVE_TIMER_MILLIS, Configuration.PLAYER_SAVE_BACKUP_EVERY_X_SAVE_TICKS);
        }

        Reflection.getMethodsAnnotatedWith(PostInit.class).forEach(method -> {
            try {
                method.invoke(null);
            } catch (Exception e) {
                logger.error("Error loading @PostInit annotated method[{}] inside class[{}]", method, method.getClass(), e);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

}
