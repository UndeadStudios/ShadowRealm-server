package io.shadowrealm.content.worldevent.impl;

import java.util.List;

import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.bosses.hespori.HesporiSpawner;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.commands.all.Worldevent;
import io.shadowrealm.content.worldevent.WorldEvent;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.model.entity.player.broadcasts.Broadcast;

public class HesporiWorldEvent implements WorldEvent {
    @Override
    public void init() {
        HesporiSpawner.spawnNPC();
    }

    @Override
    public void dispose() {
        if (!isEventCompleted()) {
            Hespori.rewardPlayers(false);
        }
    }

    @Override
    public boolean isEventCompleted() {
        return !HesporiSpawner.isSpawned();
    }

    @Override
    public String getCurrentStatus() {
        return "World Event: @gre@Hespori";
    }

    @Override
    public String getEventName() {
        return "Hespori";
    }

    @Override
    public String getStartDescription() {
        return "spawns";
    }

    @Override
    public Class<? extends Command> getTeleportCommand() {
        return Worldevent.class;
    }

    @Override
    public void announce(List<Player> players) {
        new Broadcast("Hespori world boss has spawned, use ::worldevent to fight!").addTeleport(new Position(3072, 3499, 0)).copyMessageToChatbox().submit();
    }
}
