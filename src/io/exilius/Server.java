package io.exilius;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.exilius.content.DiscordConnection;
import io.exilius.content.minigames.CastleWars;
import io.exilius.model.AttributesSerializable;
import io.exilius.model.cycleevent.EventHandler;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.npc.drops.DropManager;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.multiplayersession.MultiplayerSessionListener;
import io.exilius.model.world.ClanManager;
import io.exilius.model.world.ItemHandler;
import io.exilius.model.world.ShopHandler;
import io.exilius.model.world.event.CyclicEventManager;
import io.exilius.model.world.objects.GlobalObjects;
import io.exilius.net.PipelineFactory;
import io.exilius.punishments.Punishments;
import io.exilius.sql.DatabaseCredentials;
import io.exilius.sql.DatabaseManager;
import io.exilius.sql.EmbeddedDatabase;
import io.exilius.sql.Votes;
import io.exilius.util.*;
import io.exilius.util.dateandtime.GameCalendar;
import io.exilius.util.discord.Discord;
import io.exilius.util.logging.GameLogging;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.dv8tion.jda.api.EmbedBuilder;
import org.flywaydb.core.Flyway;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The main class needed to start the server.
 *
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30 Revised by Shawn Notes by Shawn
 */
public class Server {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Server.class);

    private static final Server singleton = new Server();
    static long tickCount = 0;
    private static final Punishments PUNISHMENTS = new Punishments();
    private static final DropManager dropManager = new DropManager();
    private static ServerAttributes serverAttributes;
    /**
     * A class that will manage game events
     */
    private static final EventHandler events = new EventHandler();
    /**
     * Represents our calendar with a given delay using the TimeUnit class
     */
    private static final GameCalendar calendar = new GameCalendar(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    private static final MultiplayerSessionListener multiplayerSessionListener = new MultiplayerSessionListener();
    private static final GlobalObjects globalObjects = new GlobalObjects();
    private final CyclicEventManager cyclicEventManager = new CyclicEventManager();
    private static final GameLogging logging = new GameLogging();
    /**
     * ClanChat Added by Valiant
     */
    public static CastleWars castleWars = new CastleWars();
    public static ClanManager clanManager = new ClanManager();
    /**
     * Server updating.
     */
    public static boolean UpdateServer;
    /**
     * Calls the usage of player items.
     */
    public static ItemHandler itemHandler = new ItemHandler();
    /**
     * Handles logged in players.
     */
    public static PlayerHandler playerHandler = new PlayerHandler();
    /**
     * Handles global NPCs.
     */
    public static NPCHandler npcHandler = new NPCHandler();
    /**
     * Handles global shops.
     */
    public static ShopHandler shopHandler = new ShopHandler();
    /**
     * The server configuration.
     */
    private static ServerConfiguration configuration;
    /**
     * The database manager.
     */
    private static DatabaseManager databaseManager;
    private static EmbeddedDatabase embeddedDatabase;
    private static final ScheduledExecutorService ioExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("io-worker-%d").build());
    private static boolean loaded = false;

    private static void enableExceptionLogging() throws IOException {
        if (!new File(Configuration.ERROR_LOG_DIRECTORY).exists()) {
            Preconditions.checkState(new File(Configuration.ERROR_LOG_DIRECTORY).mkdirs());
        }
        if (!new File(Configuration.CONSOLE_LOG_DIRECTORY).exists()) {
            Preconditions.checkState(new File(Configuration.CONSOLE_LOG_DIRECTORY).mkdirs());
        }
        TeeOutputStream outputStream = new TeeOutputStream(System.err, new FileOutputStream(Configuration.ERROR_LOG_DIRECTORY + Configuration.ERROR_LOG_FILE, true));
        System.setErr(new TimeStampedPrintStream(outputStream));
        outputStream = new TeeOutputStream(System.out, new FileOutputStream(Configuration.CONSOLE_LOG_DIRECTORY + Configuration.CONSOLE_FILE, true));
        System.setOut(new TimeStampedPrintStream(outputStream));
    }

    public static void loadData() throws Exception {
        Preconditions.checkState(!loaded, "Already loaded data once.");
        logger.info("Server state: " + configuration.getServerState());
        loadAttributes();
        databaseManager = new DatabaseManager(getConfiguration().getServerState().isSqlEnabled());
        embeddedDatabase = new EmbeddedDatabase(getConfiguration().getServerState().name().toLowerCase() + "_main",
                null, getConfiguration().getEmbeddedPassword());

        if (configuration.isLocalDatabaseEnabled()) {
            DatabaseCredentials localDatabase = configuration.getLocalDatabase();
            Flyway.configure()
                    .dataSource(localDatabase.getUrl(), localDatabase.getUsername(), localDatabase.getPassword())
                    .load()
                    .migrate();
        }
        ServerStartup.load();
        loaded = true;
    }

    public static void startServerless() throws Exception {
        startServerless(loadConfiguration());
    }

    /**
     * Start the server in 'serverless' mode which means it won't start the server,
     * only load data and give access to things that requires the server to start.
     */
    public static void startServerless(ServerConfiguration configuration) throws Exception {
        setConfiguration(configuration);
        loadData();
    }

    public static void main(String... args) {
        disableWarning();
        new GameThread(() -> {
            try {
                System.out.println("[" + Calendar.getInstance().getTime() + "]: Launching " + Configuration.SERVER_NAME + ".");
                enableExceptionLogging();
                long startTime = System.nanoTime();
                System.setOut(new OutstreamStyle(System.out));
                if (configuration == null) {
                    setConfiguration(loadConfiguration());
                }

                // Set log level for debug mode
                ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
                root.setLevel(isDebug() || isTest() ? ch.qos.logback.classic.Level.ALL : ch.qos.logback.classic.Level.INFO);
                loadData();

                if (isDebug()) {
                    Configuration.DISABLE_NEW_ACCOUNT_CAPTCHA = true;
                    Configuration.DISABLE_CHANGE_ADDRESS_CAPTCHA = true;
                    Configuration.DISABLE_CAPTCHA_EVERY_LOGIN = true;
                }
                bindPorts();
                if (isPublic()) {
                    EmbedBuilder db = new EmbedBuilder();
                    db.setTitle("Exilius Server Status");
                    db.setDescription("@here Server is now online!");
                    db.setImage("https://endless-os.com/logo.png");
                    db.setColor(new java.awt.Color(0xB00D03));
                    Discord.getJDA().getTextChannelById("1064974101611040829").sendMessageEmbeds(db.build()).queue();
                }
                long endTime = System.nanoTime();
                long elapsed = endTime - startTime;
                System.out.println(Configuration.SERVER_NAME + " has successfully started up in " + TimeUnit.SECONDS.convert(elapsed, TimeUnit.NANOSECONDS)+ " seconds.");
            } catch (Exception e) {
                logger.error("An error occurred while starting the server.", e);
                e.printStackTrace();
                System.exit(1);
            }
        }).start();
    }
    public static long getEnd(long ticks) {
        return getTickCount() + ticks;
    }
    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);
            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception ignored) {
        }
    }

    public static boolean isPublic() {
        return configuration.getServerState() == ServerState.PUBLIC
                || configuration.getServerState() == ServerState.TEST_PUBLIC;
    }

    public static boolean isDebug() {
        return configuration.getServerState() == ServerState.DEBUG
                || configuration.getServerState() == ServerState.DEBUG_SQL;
    }

    public static boolean isTest() {
        return isDebug() || configuration.getServerState() == ServerState.TEST;
    }

    private static void loadAttributes() throws IOException {
        serverAttributes = new ServerAttributes();
        serverAttributes = AttributesSerializable.getFromFile(ServerAttributes.getSaveFile(), serverAttributes);
    }

    public static ServerConfiguration loadConfiguration() throws IOException {
        File configurationFile = new File(ServerConfiguration.CONFIGURATION_FILE);
        ServerConfiguration configuration;
        if (!configurationFile.exists()) {
            configuration = ServerConfiguration.getDefault();
            JsonUtil.toYaml(configuration, configurationFile.getPath());
            logger.warn("No configuration present, wrote default configuration file to " + configurationFile.getAbsolutePath());
        } else {
            configuration = JsonUtil.fromYaml(configurationFile, ServerConfiguration.class);
        }
        return configuration;
    }

    /**
     * Get the directory where save files will be saved for the current {@link ServerState}.
     * Will not include files from other {@link ServerState}s.
     */
    public static String getSaveDirectory() {
        return Configuration.SAVE_DIRECTORY + "/" + configuration.getServerState().toString().toLowerCase() + "/";
    }

    public static String getGameLogDirectory() {
        return Configuration.SAVE_DIRECTORY + "/game_logs/" + configuration.getServerState().toString().toLowerCase() + "/";
    }

    public static String getBackupDirectory() {
        return Configuration.SAVE_DIRECTORY + "/backups/" + configuration.getServerState().toString().toLowerCase() + "/";
    }

    public static String getDataDirectory() {
        return "./" + Configuration.DATA_FOLDER;
    }

    public static MultiplayerSessionListener getMultiplayerSessionListener() {
        return multiplayerSessionListener;
    }

    /**
     * Java connection. Ports.
     */
    private static void bindPorts() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setPriority(GameThread.PRIORITY - 1).build());
        EventLoopGroup workerGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setPriority(GameThread.PRIORITY - 1).build());

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new PipelineFactory());
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.bind(new InetSocketAddress(configuration.getServerState().getPort()));
    }
    public static void startvoteCountCounter() {
        try {
            File file = new File("./Data/votes.log");

            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader minuteFile = new BufferedReader(new FileReader(file));
            Votes.voteCount = Integer.parseInt(minuteFile.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void startGlobalvoteCountCounter() {
        try {
            File file = new File("./Data/Globalvotes.log");

            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader minuteFile = new BufferedReader(new FileReader(file));
            Votes.globalvoteCount = Integer.parseInt(minuteFile.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void setvoteCountCounter(int minutesCounter) {
        Votes.voteCount = minutesCounter;
        try {
            BufferedWriter minuteCounter = new BufferedWriter(new FileWriter("./Data/votes.log"));
            minuteCounter.write(Integer.toString(getVoteCounter()));
            minuteCounter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setGlobalvoteCountCounter(int minutesCounter) {
        Votes.globalvoteCount = minutesCounter;
        try {
            BufferedWriter minuteCounter = new BufferedWriter(new FileWriter("./Data/Globalvotes.log"));
            minuteCounter.write(Integer.toString(getGlobalVoteCounter()));
            minuteCounter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getVoteCounter() {
        return Votes.voteCount;
    }
    public static int getGlobalVoteCounter() {
        return Votes.globalvoteCount;
    }
    public static GameCalendar getCalendar() {
        return calendar;
    }

    public static GlobalObjects getGlobalObjects() {
        return globalObjects;
    }

    public static EventHandler getEventHandler() {
        return events;
    }

    public static DropManager getDropManager() {
        return dropManager;
    }

    public static Punishments getPunishments() {
        return PUNISHMENTS;
    }

    public static ServerConfiguration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(ServerConfiguration configuration) {
        Preconditions.checkState(Server.configuration == null, "Server configuration is already set.");
        Server.configuration = configuration;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static EmbeddedDatabase getEmbeddedDatabase() {
        return embeddedDatabase;
    }

    public static ServerAttributes getServerAttributes() {
        return serverAttributes;
    }

    public static long getTickCount() {
        return tickCount;
    }

    public static Server getWorld() {
        return singleton;
    }

    public static ScheduledExecutorService getIoExecutorService() {
        return ioExecutorService;
    }

    public static GameLogging getLogging() {
        return logging;
    }

    public CyclicEventManager getCyclicEventManager() {
        return this.cyclicEventManager;
    }
}
