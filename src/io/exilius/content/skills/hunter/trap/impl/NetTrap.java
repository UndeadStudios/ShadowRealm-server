package io.exilius.content.skills.hunter.trap.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import io.exilius.Server;
import io.exilius.content.skills.hunter.trap.Trap;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.NPCDumbPathFinder;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Location3D;

import java.util.EnumSet;
import java.util.Optional;

public class NetTrap extends Trap {
    /**
     * Constructs a new {@link Trap}.
     *
     * @param player {@link #player}.
     * */
    public NetTrap(Player player, GlobalObject object, Location3D location) {
        super(player, TrapType.NET_TRAP, object, location);
    }
    /**
     * The npc trapped inside this box.
     */
    private Optional<NPC> trapped = Optional.empty();

    Location3D location2 = new Location3D(player.objectX, player.objectY, player.heightLevel);
    /**
     * Determines if an animal is going to the trap.
     */
    private Optional<CycleEvent> event = Optional.empty();

    /**
     * The object identification for a dismantled failed box trap.
     */
    private static final int FAILED_ID = 9158;

    /**
     * The object identification for a caught box trap.
     */
    private static final int CAUGHT_ID = 9004;

    /**
     * The distance the npc has to have from the box trap before it gets triggered.
     */
    private static final int DISTANCE_PORT = 3;

    /**
     * A collection of all the npcs that can be caught with a box trap.
     */
    public static final ImmutableSet<Integer> NPC_IDS = ImmutableSet.of(NetTrapData.SWAMP_LIZARD.npcId);

    /**
     * Kills the specified {@code npc}.
     * @param npc	the npc to kill.
     */
    /**
     * Kills the specified {@code npc}.
     * @param npc	the npc to kill.
     */
    private void kill(NPC npc) {
        npc.setDead(true);
        trapped = Optional.of(npc);
    }
    @Override
    public boolean canCatch(NPC npc) {
        Optional<NetTrap.NetTrapData> data = NetTrap.NetTrapData.getNetTrapDataByNpcId(npc.getNpcId());

        if(!data.isPresent()) {
            throw new IllegalStateException("Invalid box trap id.");
        }
        if (System.currentTimeMillis() - player.lastPickup < 2500)
            return false;

        if(player.playerLevel[21] < data.get().requirement) {//(player.playerLevel[player.playerHunter] < data.get().requirement)FIXME
            player.lastPickup = System.currentTimeMillis();
            player.sendMessage("You do not have the required level to catch these.");
            setState(TrapState.FALLEN);
            return false;
        }
        return true;
    }

    @Override
    public void onPickUp() {
        player.sendMessage("You pick up your net trap.");
    }

    @Override
    public void onSetup() {
player.sendMessage("You set-up your net trap.");
    }

    @Override
    public void onCatch(NPC npc) {
        Location3D location = new Location3D(object.getX(), getObject().getY(), player.heightLevel);
        if(event.isPresent()) {
            return;
        }
        if(!Server.getGlobalObjects().anyExists(getObject().getX(), getObject().getY(), getObject().getHeight())) {
            //System.out.println("Hunter; No trap existed while attempting to catch");
            return;
        }

        npc.randomWalk = false;

        event = Optional.of(new CycleEvent() {

            @Override
            public void execute(CycleEventContainer container) {
                NPCDumbPathFinder.walkTowards(npc, getObject().getX(), getObject().getY());
                if(isAbandoned()) {
                    container.stop();
                    npc.randomWalk = true;
                    return;
                }

                if(npc.absX == getObject().getX() && npc.absY == getObject().getY()) {
                    npc.randomWalk = true;

                    if(!Server.getGlobalObjects().anyExists(getObject().getX(), getObject().getY(), getObject().getHeight())) {
                        //System.out.println("Hunter; No trap existed while attempting to catch");
                        return;
                    }

                    int count = random.inclusive(180);
                    int formula = successFormula(npc);
                    if(count > formula) {
                        setState(TrapState.FALLEN);
                        container.stop();
                        return;
                    }
                    npc.randomWalk = false;
                    kill(npc);
                    Server.getGlobalObjects().remove(getObject());
                    Server.getGlobalObjects().remove(getObject().getObjectId(), getObject().getX(), getObject().getY(), getObject().getHeight());
                    setObject(CAUGHT_ID, location2);
                    Server.getGlobalObjects().add(getObject());
                    setState(TrapState.CAUGHT);
                    container.stop();
                }
            }

            @Override
            public void onStopped() {
                event = Optional.empty();
            }
        });

        CycleEventHandler.getSingleton().addEvent(player, event.get(), 1);
    }

    @Override
    public void onSequence(CycleEventContainer container) {
        for(NPC npc : NPCHandler.npcs) {
            if(npc == null || npc.isDead()) {
                continue;
            }
            if(!NPC_IDS.stream().anyMatch(id -> npc.getNpcId() == id)) {
                continue;
            }

            if(this.getObject().getHeight() == npc.heightLevel && Math.abs(this.getObject().getX() - npc.absX) <= DISTANCE_PORT && Math.abs(this.getObject().getY() - npc.absY) <= DISTANCE_PORT) {
                if(random.inclusive(100) < 20) {
                    return;
                }
                if(this.isAbandoned()) {
                    return;
                }
                trap(npc);
            }
        }
    }

    @Override
    public GameItem[] reward() {
        if(!trapped.isPresent()) {
            throw new IllegalStateException("No npc is trapped.");
        }

        Optional<NetTrap.NetTrapData> data = NetTrap.NetTrapData.getNetTrapDataByNpcId(trapped.get().getNpcId());

        if(!data.isPresent()) {
            throw new IllegalStateException("Invalid object id.");
        }

        return data.get().reward;
    }

    @Override
    public double experience() {
        if(!trapped.isPresent()) {
            throw new IllegalStateException("No npc is trapped.");
        }

        Optional<NetTrap.NetTrapData> data = NetTrap.NetTrapData.getNetTrapDataByNpcId(trapped.get().getNpcId());

        if(!data.isPresent()) {
            throw new IllegalStateException("Invalid object id.");
        }

        return data.get().experience;
    }

    @Override
    public boolean canClaim(GlobalObject object) {
        return false;
    }
    @Override
    public void setState(TrapState state) {
		/*if(state.equals(TrapState.PENDING)) {
			throw new IllegalArgumentException("Cannot set trap state back to pending.");
		}*/
        if(state.equals(TrapState.FALLEN)) {
            Server.getGlobalObjects().remove(getObject());
            Server.getGlobalObjects().remove(getObject().getObjectId(), getObject().getX(), getObject().getY(), getObject().getHeight());
            this.setObject(FAILED_ID, location2);
            Server.getGlobalObjects().add(this.getObject());
        }
        if (!state.equals(TrapState.TRIGGERED)) {
            player.sendMessage("Your trap has been triggered by something...");
        }
        super.setState(state);
    }
    /**
     * The enumerated type whose elements represent a set of constants
     * used for box trapping.
     * @author <a href="http://www.rune-server.org/members/stand+up/">Stand Up</a>
     */
    public enum NetTrapData {
        SWAMP_LIZARD(2906, 29, 152, 10, new GameItem(10149, 1));

        /**
         * Caches our enum values.
         */
        private static final ImmutableSet<NetTrap.NetTrapData> VALUES = Sets.immutableEnumSet(EnumSet.allOf(NetTrap.NetTrapData.class));

        /**
         * The npc id for this box trap.
         */
        private final int npcId;

        /**
         * The requirement for this box trap.
         */
        private final int requirement;

        /**
         * The experience gained for this box trap.
         */
        private final double experience;

        private final int respawn;

        /**
         * The reward obtained for this box trap.
         */
        private final GameItem[] reward;

        /**
         * Constructs a new {@link BoxTrap.BoxTrapData}.
         * @param npcId			{@link #npcId}.
         * @param requirement	{@link #requirement}.
         * @param experience	{@link #experience}.
         * @param reward		{@link #reward}.
         */
        NetTrapData(int npcId, int requirement, double experience, int respawn, GameItem... reward) {
            this.npcId = npcId;
            this.requirement = requirement;
            this.experience = experience;
            this.reward = reward;
            this.respawn = respawn;
        }

        public int getNpcId() {
            return npcId;
        }

        public int getRespawn() {
            return respawn;
        }

        /**
         * Retrieves a {@link BoxTrap.BoxTrapData} enumerator dependant on the specified {@code id}.
         * @param id	the npc id to return an enumerator from.
         * @return a {@link BoxTrap.BoxTrapData} enumerator wrapped inside an optional, {@link Optional#empty()} otherwise.
         */
        public static Optional<NetTrap.NetTrapData> getNetTrapDataByNpcId(int id) {
            return VALUES.stream().filter(box -> box.npcId == id).findAny();
        }
    }
}
