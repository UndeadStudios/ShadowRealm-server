package io.exilius.content.minigames.TBD.rooms;

import io.exilius.content.instances.InstancedArea;
import io.exilius.content.minigames.TBD.TBDConstants;
import io.exilius.content.minigames.TBD.TBDRoom;
import io.exilius.content.minigames.TBD.bosses.Nylocas;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.model.world.objects.GlobalObject;

public class RoomThree extends TBDRoom {

    @Override
    public Nylocas spawn(InstancedArea instancedArea) {
        return new Nylocas(instancedArea);
    }

    @Override
    public Position getPlayerSpawnPosition() {
        return new Position(3296, 4283);
    }

    @Override
    public boolean handleClickObject(Player player, WorldObject worldObject, int option) {
        return false;
    }

    @Override
    public void handleClickBossGate(Player player, WorldObject worldObject) {
        if (player.getY() >= 4256) {
            player.getPA().movePlayer(player.getX(), 4254, player.getHeight());
        } else {
            player.getPA().movePlayer(player.getX(), 4256, player.getHeight());
        }
    }

    @Override
    public boolean isRoomComplete(InstancedArea instancedArea) {
        return instancedArea.getNpcs().isEmpty();
    }

    @Override
    public Boundary getBoundary() {
        return TBDConstants.NYLOCAS_BOSS_ROOM_BOUNDARY;
    }

    @Override
    public Position getDeathPosition() {
        return new Position(3295, 4256, 0);
    }

    @Override
    public Position getFightStartPosition() {
        return new Position(3295, 4254, 0);
    }

    @Override
    public GlobalObject getFoodChestPosition() {
        return getFoodChest(new Position(3303, 4274, 0), 3);
    }
}
