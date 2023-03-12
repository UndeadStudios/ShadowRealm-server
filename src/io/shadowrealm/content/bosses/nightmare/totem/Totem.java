package io.shadowrealm.content.bosses.nightmare.totem;

import io.shadowrealm.content.instances.InstancedArea;
import io.shadowrealm.model.CombatType;
import io.shadowrealm.model.definitions.NpcStats;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class Totem extends NPC {

    private final TotemSpawn totemSpawn;

    public Totem(TotemSpawn totemSpawn, int npcId, InstancedArea instancedArea) {
        super(npcId, instancedArea.resolve(totemSpawn.getPosition()));
        this.totemSpawn = totemSpawn;
        getHealth().setMaximumHealth(0);
        getHealth().setCurrentHealth(0);
        instancedArea.add(this);
        getBehaviour().setWalkHome(false);
        getBehaviour().setRespawn(false);
        setDefaultNpcStats(NpcStats.builder().setRangeDef(300).setCrush(300).setSlash(300).setSlashDef(300).createNpcStats());
    }

    @Override
    public boolean canBeAttacked(Entity entity) {
        return getNpcId() == totemSpawn.getAttackableNpcId();
    }

    @Override
    public void setDead(boolean dead) {
        // ignore
    }

    @Override
    public void process() {
        if (getNpcId() == totemSpawn.getAttackableNpcId() && getHealth().getCurrentHealth() == 0) {
            requestTransform(totemSpawn.getChargedNpcId());
        }
    }

    @Override
    public int getAttackDistanceModifier(Player player, CombatType combatType) {
        return combatType == CombatType.MAGE ? 6 : 0;
    }

    public TotemSpawn getTotemSpawn() {
        return totemSpawn;
    }
}
