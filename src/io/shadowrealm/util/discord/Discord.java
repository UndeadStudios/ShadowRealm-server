package io.shadowrealm.util.discord;


import io.shadowrealm.Configuration;
import io.shadowrealm.Server;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.util.Misc;
import lombok.SneakyThrows;
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
    public static JDA jda2 = null;
    public static String PREFIX = "::";
    public static String OWNER_ROLE = "1074439303981170728";//Currently set to God Tier on beta.
    public static String MANAGER_ROLE = "1074439470943846473";
    public static String DEVELOPER_ROLE = "1065072881815539802";
    public static String ADMIN_ROLE = "1064728188044976168";
    public static String GLOBAL_MOD_ROLE = "1064733810014560357";
    public static String SUPPORT_ROLE = "1064735941178183770";

    /**
     * Write to a channel that contains misc. types of information about player activity.
     *///
    public static void writeServerSyncMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970793408270367L, message, args);
        }
    }
    public static void writeAddressSwapMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970793408270367L, message, args);
        }
    }
    public static void writeannounceMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970750408265878L, message, args);
        }
    }

    public static void writeBugMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064749086688952360L, message, args);
        }
    }

    public static void writetickets(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970559227695115L, message, args);
        }
    }

    public static void writepunishments(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970616672891012L, message, args);
        }
    }
    public static void writePickupMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970793408270367L, message, args);
        }
    }
    public static void raredropMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064970750408265878L, message, args);
        }
    }

    public static void writeSuggestionMessage(String message, Object...args) {
        if(Server.isPublic()) {
            sendChannelMessage(1064748679149391905L, message, args);
        }
    }

    public static void writeFoeMessage(String message, Object...args) {
        if(Server.isPublic()) {
            writeServerSyncMessage(message, args);

            sendChannelMessage(1064970793408270367L, message, args);
        }
    }

    public static void writeReferralMessage(String message, Object...args) {
        if(Server.isPublic()) {
            writeServerSyncMessage(message, args);
            sendChannelMessage(1064970793408270367L, message, args);
        }
    }

    public static void writeCheatEngineMessage(String message, Object...args) {
        if (Server.isPublic()) {
            writeServerSyncMessage(message, args);
            sendChannelMessage(1064970793408270367L, message, args);
        }
    }
//
//        /**
//         * Write to a channel that should not be ignored by staff.
//         */
//        public static void writeAddressSwapMessage (String message, Object...args){
//            writeServerSyncMessage(message, args);
//       sendChannelMessage(server-bot-notification, message, args);
//        }
//    }

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

    @SneakyThrows
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

    public static JDA getJDA() throws LoginException, InterruptedException  {
        if (jda == null) {
            jda = JDABuilder.createDefault("MTAwNDExNDAyNTA0ODcxNTM4Ng.GX1HBB.RIIPCLrWOrTrDWXP3iUPIqwH_zqe64oms20MEI").build();
            jda.awaitReady();
            jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching((int) Math.round((PlayerHandler.getPlayerCount() * 1)) + " players!"));
        }
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
            jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.playing("Shadow Realm with "+ ((int) (PlayerHandler.getPlayerCount() * 1)) + " players!"));
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

        if (e.getChannel().getName() == "staff-commands-ingame" && e.getMessage().getContentDisplay().equalsIgnoreCase("!connect")) {
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
        } else if (e.getChannel().getName() == "staff-commands-ingame") {
            DiscordCommands command = DiscordCommands.isCommand(e);

            if (Objects.isNull(command)) {
                return;
            }

            command.getAdapter().onMessageReceived(e);
        }
    }
}
