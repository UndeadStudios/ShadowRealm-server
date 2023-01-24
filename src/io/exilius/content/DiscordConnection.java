package io.exilius.content;


import io.exilius.Server;
import io.exilius.model.entity.player.PlayerHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class DiscordConnection extends ListenerAdapter {

    public static JDA jda;

    String token = "MTAwNDExNDAyNTA0ODcxNTM4Ng.GX1HBB.RIIPCLrWOrTrDWXP3iUPIqwH_zqe64oms20MEI";
    public static final String CHANNEL_PUNISHMENTS = "984613575928721489";

    public static final String CHANNEL_OSRS_DONATIONS = "984613576398499895";

    public void initialize() {
        JDABuilder builder = JDABuilder.createDefault(token).setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                .enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(this);
//        builder.addEventListeners(new UserCommands());
//        builder.addEventListeners(new DiscordAuth());
//		builder.addEventListeners(new AdminCommands());
//		builder.addEventListeners(new PrivateMessageReceived());
        try {
            jda = builder.build();
            jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching((int) Math.round((PlayerHandler.getPlayerCount() * 1.6)) + " players!"));
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    static {
//        if (!Server.isPublic()) {
//                jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching(((PlayerHandler.getPlayerCount()) + " players!"));

                /*ChambersOfXeric.confiscateItems(player);*/

        }
    }
