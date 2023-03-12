package io.shadowrealm;

import io.shadowrealm.content.battle_pass.BattlePassHandler;
import io.shadowrealm.content.bosses.wintertodt.Wintertodt;
import io.shadowrealm.content.instances.InstanceHeight;
import io.shadowrealm.content.minigames.pk_arena.Highpkarena;
import io.shadowrealm.content.minigames.pk_arena.Lowpkarena;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.net.ChannelHandler;
import io.shadowrealm.net.login.RS2LoginProtocol;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.discord.Discord;
import io.shadowrealm.util.discord.DiscordIntegration;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class GameThread extends Thread {

    public static final String THREAD_NAME = "GameThread";
    public static final int PRIORITY = 9;
    private static final Logger logger = LoggerFactory.getLogger(GameThread.class);
    private final List<Consumer<GameThread>> tickables = new ArrayList<>();
    private final Runnable startup;
    private long totalCycleTime = 0;

    public GameThread(Runnable startup) {
        setName(THREAD_NAME);
        setPriority(PRIORITY);
        this.startup = startup;
        setTickables();
    }

    private void setTickables() {
        tickables.add(i -> Server.itemHandler.process());
        tickables.add(i -> Server.npcHandler.process());
        tickables.add(i -> Server.playerHandler.process());
        tickables.add(i -> Server.objectManager.process());
        tickables.add(i -> Server.shopHandler.process());
        tickables.add(i -> Server.startGlobalvoteCountCounter());
        tickables.add(i -> Server.startvoteCountCounter());
        tickables.add(i -> Highpkarena.process());
        tickables.add(i -> DiscordIntegration.givePoints());
        tickables.add(i -> Lowpkarena.process());
        tickables.add(i -> Server.getGlobalObjects().pulse());
        tickables.add(i -> CycleEventHandler.getSingleton().process());
        tickables.add(i -> Server.getEventHandler().process());
        tickables.add(i -> Wintertodt.pulse());
        tickables.add(i -> Server.tickCount++);
        tickables.add(i -> BattlePassHandler.Companion.checkSeasonExpiry());
    }

    @SneakyThrows
    private void tick() {
        for (Consumer<GameThread> tickable : tickables) {
            try {
                tickable.accept(this);
            } catch (Exception e) {
                logger.error("Error caught in GameThread, should be caught up the chain and handled.", e);
//                e.printStackTrace();
            }
        }

        // Report
        if (Server.getTickCount() % 50 == 0) {
            StringJoiner joiner = new StringJoiner(", ");
            joiner.add("runtime=" + Misc.cyclesToTime(Server.getTickCount()));
            joiner.add("connections=" + ChannelHandler.getActiveConnections());
            joiner.add("players=" + PlayerHandler.getPlayers().size());
            joiner.add("uniques=" + PlayerHandler.getUniquePlayerCount());
            joiner.add("npcs=" + (int) NPCHandler.nonNullStream().count());
            joiner.add("reserved-heights=" + InstanceHeight.getReservedCount());
            joiner.add("average-cycle-time=" + (totalCycleTime / Server.getTickCount()) + "ms");
            joiner.add("handshakes-per-tick=" + (RS2LoginProtocol.getHandshakeRequests() / Server.getTickCount()));

            long totalMemory = Runtime.getRuntime().totalMemory();
            long usedMemory = totalMemory - Runtime.getRuntime().freeMemory();
            joiner.add("memory=" + Misc.formatMemory(usedMemory) + "/" + Misc.formatMemory(totalMemory));

            logger.info("Status [" + joiner.toString() + "]");
                }
    }

    @Override
    public void run() {
        startup.run();
        while (!Thread.interrupted()) {
            long time = System.currentTimeMillis();
            try {
                tick();
            } catch (Exception e) {
                logger.error("An error occurred while running the game thread tickables.", e);
                e.printStackTrace();
            }
            long pastTime = System.currentTimeMillis() - time;
            totalCycleTime += pastTime;
            if (pastTime < 600) {
                try {
                    Thread.sleep(600 - pastTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
//                logger.error("Game thread took " + Misc.insertCommas(pastTime + "") + "ms to process!");
            }
        }
    }
}
