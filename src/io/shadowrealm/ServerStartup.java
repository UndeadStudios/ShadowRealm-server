package io.shadowrealm;

import io.shadowrealm.annotate.Init;
import io.shadowrealm.annotate.PostInit;
import io.shadowrealm.content.BadWords;
import io.shadowrealm.content.battle_pass.BattlePassConfig;
import io.shadowrealm.content.battle_pass.BattlePassHandler;
import io.shadowrealm.content.boosts.Boosts;
import io.shadowrealm.content.bosses.godwars.GodwarsEquipment;
import io.shadowrealm.content.bosses.godwars.GodwarsNPCs;
import io.shadowrealm.content.bosses.nightmare.NightmareStatusNPC;
import io.shadowrealm.content.bosses.sarachnis.SarachnisNpc;
import io.shadowrealm.content.collection_log.CollectionLog;
import io.shadowrealm.content.combat.death.kill_limiter.KillLimitHandler;
import io.shadowrealm.content.combat.stats.TrackedMonster;
import io.shadowrealm.content.commands.CommandManager;
import io.shadowrealm.content.dailyrewards.DailyRewardContainer;
import io.shadowrealm.content.dailyrewards.DailyRewardsRecords;
import io.shadowrealm.content.event.eventcalendar.EventCalendar;
import io.shadowrealm.content.event.eventcalendar.EventCalendarWinnerSelect;
import io.shadowrealm.content.events.monsterhunt.MonsterHunt;
import io.shadowrealm.content.fireofexchange.FireOfExchangeBurnPrice;
import io.shadowrealm.content.polls.PollTab;
import io.shadowrealm.content.preset.PresetManager;
import io.shadowrealm.content.referral.ReferralCode;
import io.shadowrealm.content.skills.runecrafting.ouriana.ZamorakGuardian;
import io.shadowrealm.content.tournaments.TourneyManager;
import io.shadowrealm.content.tradingpost.Listing;
import io.shadowrealm.content.trails.TreasureTrailsRewards;
import io.shadowrealm.content.vote_panel.VotePanelManager;
import io.shadowrealm.content.wogw.Wogw;
import io.shadowrealm.content.worldevent.WorldEventContainer;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.ObjectExamines;
import io.shadowrealm.model.collisionmap.ObjectDef;
import io.shadowrealm.model.collisionmap.Region;
import io.shadowrealm.model.collisionmap.doors.DoorDefinition;
import io.shadowrealm.model.cycleevent.impl.BonusApplianceEvent;
import io.shadowrealm.model.cycleevent.impl.DidYouKnowEvent;
import io.shadowrealm.model.cycleevent.impl.UpdateQuestTab;
import io.shadowrealm.model.definitions.*;
import io.shadowrealm.model.definitions.cache.VarBitDefinitions;
import io.shadowrealm.model.entity.npc.NPCCacheDefinition;
import io.shadowrealm.model.entity.npc.NPCRelationship;
import io.shadowrealm.model.entity.npc.NpcExamines;
import io.shadowrealm.model.entity.npc.NpcSpawnLoader;
import io.shadowrealm.model.entity.npc.stats.NpcCombatDefinition;
import io.shadowrealm.model.entity.player.PlayerFactory;
import io.shadowrealm.model.entity.player.save.PlayerSave;
import io.shadowrealm.model.entity.player.save.backup.PlayerSaveBackup;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.model.lobby.LobbyManager;
import io.shadowrealm.model.objects.Doors;
import io.shadowrealm.model.objects.DoubleDoors;
import io.shadowrealm.model.world.GlobalDropsHandler;
import io.shadowrealm.model.world.ShopHandler;
import io.shadowrealm.punishments.PunishmentCycleEvent;
import io.shadowrealm.util.Reflection;
import io.shadowrealm.util.discord.DiscordIntegration;
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
        BadWords.loadBadWords();
        ShopDef.load();
        ShopHandler.load();
        NpcStats.load();
        ItemStats.load();
        Doors.getSingleton().load();
        DoubleDoors.getSingleton().load();
        DiscordIntegration.loadConnectedAccounts();
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
        Server.castleWars.process();
        TreasureTrailsRewards.load();
        AnimationLength.startup();
        PresetManager.getSingleton().init();
        ObjectDef.loadConfig();
        ObjectExamines.load();
        NpcExamines.load();
        NPCCacheDefinition.unpackConfig();
        ItemCacheDefinition.unpackConfig();
        VarBitDefinitions.unpackConfig();
        CollectionLog.init();
        Region.load();
        Server.getGlobalObjects().loadGlobalObjectFile();

        // Keep this below region load and object loading
        NpcSpawnLoader.load();
        //NPCSpawns.loadNPCSpawns();
        MonsterHunt.spawnNPC();
        GlobalDropsHandler.initialize();
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
        if (KillLimitHandler.killHandlerEnabled) KillLimitHandler.Companion.loadKillLimits();
        if (BattlePassConfig.IS_ENABLED) BattlePassHandler.Companion.handleServerStartup();

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
