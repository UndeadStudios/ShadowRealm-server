package io.exilius.util.discord;


import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.util.Misc;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Discord extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Discord.class);
    private static final Map<String, TextChannel> channels = new ConcurrentHashMap<>();
    public static JDA jda = null;
    public static String PREFIX = "::";
    public static String OWNER_ROLE = "1074439303981170728";//Currently set to God Tier on beta.
    public static String MANAGER_ROLE = "1074439470943846473";
    public static String DEVELOPER_ROLE = "1065072881815539802";
    public static String ADMIN_ROLE = "1064728188044976168";
    public static String GLOBAL_MOD_ROLE = "1064733810014560357";
    public static String SUPPORT_ROLE = "1064735941178183770";

    /**
     * Write to a channel that contains misc. types of information about player activity.
     */
    public static void writeServerSyncMessage(String message, Object...args) {
        sendChannelMessage(1001848628257759333L, message, args);//Server-Logs
    }

    public static void writeOnlineNotification(String message, Object...args) {
        sendChannelMessage(1001848567725555852L, message, args);//Bot-Information
    }

    public static void writeBugMessage(String message, Object...args) {
//        sendChannelMessage("report-section", message, args);
    }

    public static void writePickupMessage(String message, Object...args) {
        sendChannelMessage(1002389865507332149L, message, args);//pickup-logs
    }

    public static void writeXmasMessage(String message, Object...args) {
        sendChannelMessage(1055318696375091252L, message, args);//xmas-logs
    }

    public static void writeSuggestionMessage(String message, Object...args) {
//        sendChannelMessage("suggestions", message, args);
    }

    public static void writeFoeMessage(String message, Object...args) {
        writeServerSyncMessage(message, args);
        sendChannelMessage(1001848567725555852L, message, args);//Bot-Information
    }

    public static void writeReferralMessage(String message, Object...args) {
        writeServerSyncMessage(message, args);
    }

    public static void writeCheatEngineMessage(String message, Object...args) {
        writeServerSyncMessage(message, args);
    }

    public static void writeDeathHandler(String message, Object...args) {
        sendChannelMessage(1002389817126035477L, message, args);
    }

    public static void writeDropHandler(String message, Object...args) {
        sendChannelMessage(1002389976845140068L, message, args);
    }

    public static void writeGiveLog(String message, Object...args) {
        sendChannelMessage(1001848628257759333L, message, args);
    }

    /**
     * Write to a channel that should not be ignored by staff.
     */
    public static void writeAddressSwapMessage(String message, Object...args) {
        writeServerSyncMessage(message, args);
//        sendChannelMessage("server-bot-notification", message, args);
    }



    private static void sendChannelMessage(long channelName, String message, Object...args) {
        if (Configuration.DISABLE_DISCORD_MESSAGING) {
            System.out.println("Retard set the config to false.");
            return;
        }
        Server.getIoExecutorService().submit(() -> {
            try {
                if (getJDA().getTextChannelById(channelName) != null) {
                    getJDA().getTextChannelById(channelName).
                            sendMessage(Misc.replaceBracketsWithArguments(message, args)).queue();
                } else {
                    for (TextChannel textChannel : getJDA().getTextChannels()) {
                        System.out.println(textChannel.getName() + " / " + textChannel.getId() + " / " + textChannel.getIdLong());
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    private static TextChannel getChannel(String name) {
        if (Configuration.DISABLE_DISCORD_MESSAGING)
            return null;
        if (channels.containsKey(name))
            return channels.get(name);

        List<TextChannel> foundChannels = getJDA().getTextChannelsByName(name, true);
        if (foundChannels.isEmpty()) {
            logger.error("No discord channel found with name: " + name);
            return null;
        }

        TextChannel channel = foundChannels.get(0);
        channels.put(name, channel);
        return channels.get(name);
    }

    public static JDA getJDA() {
       return jda;
   }


    private static boolean enabled() {
        return !Configuration.DISABLE_DISCORD_MESSAGING;
    }

    public void init() {
        if (Configuration.DISABLE_DISCORD_MESSAGING)
            return;
        JDABuilder builder = JDABuilder.createDefault("MTAwNDExNDAyNTA0ODcxNTM4Ng.GX1HBB.RIIPCLrWOrTrDWXP3iUPIqwH_zqe64oms20MEI")
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableCache(CacheFlag.ACTIVITY)
                .setMemberCachePolicy(MemberCachePolicy.ONLINE)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL);
        System.out.println("Loading Discord Bot!");
        try {
            jda = builder.build();
            jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.playing("Exilius with "+ ((int) (PlayerHandler.getPlayerCount() * 1)) + " players!"));
            jda.addEventListener(this);
            jda.getGuilds().forEach(Guild::loadMembers);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        if (Configuration.DISABLE_DISCORD_MESSAGING) {
            System.out.println("Retard set the config to false.");
            return;
        }

        if (e.getAuthor().isBot()) {
            return;
        }

        if (e.getChannel().getIdLong() == 1046804022583099485L && e.getMessage().getContentDisplay().equalsIgnoreCase("!connect")) {
                User user = e.getAuthor();

                if (DiscordIntegration.connectedAccounts.containsValue(user.getIdLong())) {
                    DiscordIntegration.sendPrivateMessage(user, e.getTextChannel(), "This discord account is already connected to another in-game account!");
                }

                if (DiscordIntegration.idForCode.containsValue(user.getIdLong())) {
                    String code = null;
                    for (Map.Entry<String, Long> entry : DiscordIntegration.idForCode.entrySet()) {
                        if (entry.getValue() == user.getIdLong()) {
                            code = entry.getKey();
                        }
                    }

                    if (code == null) {
                        code = DiscordIntegration.generateCode(4);
                    }

                    DiscordIntegration.sendPrivateMessage(user, e.getTextChannel(), "Hello! You have already generated a code! Enter the following in the discord integration prompt:\n"
                            + code);
                    return;
                }

                String code = DiscordIntegration.generateCode(4);

                while (DiscordIntegration.idForCode.containsKey(code)) {
                    code = DiscordIntegration.generateCode(4);
                }

                DiscordIntegration.idForCode.put(code, e.getAuthor().getIdLong());

                DiscordIntegration.sendPrivateMessage(user, e.getTextChannel(),
                        "Hello! To connect your discord account to your in-game account, enter the following in the discord integration prompt when you click \"sync\":\n"
                                + code);
        } else if (e.getChannel().getIdLong() == 1001848632443666552L) {
            DiscordCommands command = DiscordCommands.isCommand(e);

            if (Objects.isNull(command)) {
                return;
            }

            command.getAdapter().onMessageReceived(e);
        }
    }
}
