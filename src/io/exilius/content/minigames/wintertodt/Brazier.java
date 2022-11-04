package io.exilius.content.minigames.wintertodt;

import io.exilius.model.entity.npc.NPC;
import io.exilius.model.world.objects.GlobalObject;

public class Brazier {

    private GlobalObject object;
    private NPC pyromancer;

    private int flameOffsetX, flameOffsetY;

    public Brazier(GlobalObject object, NPC pyromancer, int flameOffsetX, int flameOffsetY) {
        this.object = object;
        this.pyromancer = pyromancer;
        this.flameOffsetX = flameOffsetX;
        this.flameOffsetY = flameOffsetY;
    }

    public GlobalObject getObject() {
        return object;
    }

    public NPC getPyromancer() {
        return pyromancer;
    }

    public int getPyromancerState() {
        return pyromancer.getNpcId() == Wintertodt.PYROMANCER ? 1 : 0;
    }

    public boolean isPyromancerAlive() {
        return pyromancer.getNpcId() == Wintertodt.PYROMANCER;
    }

    public int getFlameOffsetX() {
        return flameOffsetX;
    }

    public int getFlameOffsetY() {
        return flameOffsetY;
    }

    public int getBrazierState() {
        return object.getObjectId() == Wintertodt.EMPTY_BRAZIER ? 1 :
                (object.getObjectId() == Wintertodt.BURNING_BRAZIER ? 2 : 0);
    }

}