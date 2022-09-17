package io.exilius.content.minigames.TBD.rooms;

import io.exilius.content.instances.InstancedArea;
import io.exilius.content.minigames.TBD.TBDConstants;
import io.exilius.content.minigames.TBD.bosses.PestilentBloat;
import io.exilius.content.minigames.TBD.TBDRoom;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.model.world.objects.GlobalObject;

public class RoomTwo extends TBDRoom {

    @Override
    public PestilentBloat spawn(InstancedArea instancedArea) {
        return new PestilentBloat(instancedArea);
    }

    @Override
    public Position getPlayerSpawnPosition() {
        return new Position(3322, 4448);
    }

    @Override
    public boolean handleClickObject(Player player, WorldObject worldObject, int option) {
        return false;
    }

    @Override
    public void handleClickBossGate(Player player, WorldObject worldObject) {
        if (player.getX() > 3300) {         // West gate
            if (player.getX() <= 3303) {
                player.getPA().movePlayer(3305, player.getY(), player.getHeight());
            } else {
                player.getPA().movePlayer(3303, player.getY(), player.getHeight());
            }
        } else {                            // East gate
            if (player.getX() <= 3286) {
                player.getPA().movePlayer(3288, player.getY(), player.getHeight());
            } else {
                player.getPA().movePlayer(3286, player.getY(), player.getHeight());
            }
        }
    }

    @Override
    public boolean isRoomComplete(InstancedArea instancedArea) {
        return instancedArea.getNpcs().isEmpty();
    }

    @Override
    public Boundary getBoundary() {
        return TBDConstants.BLOAT_BOSS_ROOM_BOUNDARY;
    }


    @Override
    public Position getDeathPosition() {
        return new Position(3305, 4447);
    }

    @Override
    public Position getFightStartPosition() {
        return new Position(3303, 4447, 0);
    }

    @Override
    public GlobalObject getFoodChestPosition() {
        return getFoodChest(new Position(3269, 4449, 0), 1);
    }
}
