package io.exilius.content.minigames.TBD;

import io.exilius.content.instances.InstancedArea;
import io.exilius.content.minigames.TBD.instance.TBDInstance;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Position;

public class TBDBoss extends NPC {

    public TBDBoss(int npcId, Position position, InstancedArea instancedArea) {
        super(npcId, position);
        instancedArea.add(this);
        getBehaviour().setRespawn(false);
        getBehaviour().setAggressive(true);
    }

    public void onDeath() {
        Entity killer = calculateKiller();
        if (getInstance() != null) {
            getInstance().getPlayers().forEach(plr -> {
                int points = 4;
                if (killer != null && killer.equals(plr)) {
                    points += 2;
                }
                ((TBDInstance) plr.getInstance()).getMvpPoints().award(plr, points);
                ((TBDInstance) plr.getInstance()).getFoodRewards().award(plr, points);
            });
        }
    }

}
