package io.exilius.content.bosses;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.combat.Hitmark;
import io.exilius.model.ForceMovement;
import io.exilius.model.StillGraphic;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Position;
import io.exilius.model.timers.TickTimer;
import io.exilius.util.Misc;
import org.apache.commons.lang3.Range;

import java.util.*;

public class Nex extends NPC {

    protected static Player player;

    public static final int KEY = 3460;
    public static long NEX = System.currentTimeMillis();

    public static int phase = -1;
    public static NPC nex;
    public static boolean spawned = false;
    public static boolean healers;
    public static int stage;
    public static Nex.NexPhase phases;
    public static int currentAttack;
    public static final Position[] chargePosition;
    public static boolean finishedShadows;
    public static ArrayList<Position> deadlySpots;
    public static ArrayList<Position> deadlyPosition;
    public static boolean nexHealed;
    public static TickTimer wrathTimer;
    public static TickTimer wrathDMGTimer;
    public static ArrayList<Position> wrathLocations;

    public Nex(int npcId, Position position) {
        super(npcId, position);
        nex = this;
    }

    public static boolean isMissingRequirements(Player c) {
        if (c.totalLevel < c.getMode().getTotalLevelNeededForNex()) {
            c.sendMessage("You need a total level of at least " + c.getMode().getTotalLevelNeededForNex() + " to join this raid!");
            return true;
        }

        return false;
    }
    public static void rewardPlayers() {
        NEX = System.currentTimeMillis();
        spawned = false;
        healers = false;
        PlayerHandler.nonNullStream().filter(p -> Boundary.isIn(p, Boundary.Nex))
                .forEach(p -> {
                    if (p.getIceQueenDamageCounter() >= 80) {
                        p.sendMessage("@blu@Nex has been killed!");
                        p.sendMessage("@blu@You receive a @red@key@blu@ for doing enough damage to the boss!");
                        p.getItems().addItemUnderAnyCircumstance(KEY, 1);
                        if (p.hasFollower && (p.petSummonId == 30123)) {
                            if (Misc.random(100) < 25) {
                                p.getItems().addItemUnderAnyCircumstance(KEY, 2);
                                p.sendMessage("Your pet provided 2 extra keys!");
                            }
                        }
                        if ((Configuration.DOUBLE_DROPS_TIMER > 0 || Configuration.DOUBLE_DROPS)) {
                            p.getItems().addItemUnderAnyCircumstance(KEY, 2);
                            p.sendMessage("[WOGW] Double drops is activated and you received 2 extra keys!");
                        }
                        p.setNexDamageCounter(0);
                    } else {
                        p.sendMessage("@blu@You didn't do enough damage to the boss to receive a reward.");
                        p.setNexDamageCounter(0);
                    }

                });
    }



    public static void init() {
       // spawned = true;
        healers = true;
        NPCSpawning.spawnNpcOld(11278, 2924, 5203, 0, 0, 3500, 70, 900, 900);
       //NPCSpawning.spawn(11278, 2924, 5203, 0, 0, 70, true);
        Nex.spawnHealer();
    }
    public static NPC getNex() {
        return nex;
    }

    public static void spawnHealer() {
        NPC Nex = NPCHandler.getNpc(11278);
        //healers = true;

        if (healers) {
            List<NPC> healer = Arrays.asList(NPCHandler.npcs);
            if (healer.stream().filter(Objects::nonNull).noneMatch((n) -> n.getNpcId() == 11294 && !n.isDead() && n.getHealth().getCurrentHealth() > 0)) {
                assert Nex != null;
                int maximumHealth = Nex.getHealth().getMaximumHealth();
                int currentHealth = Nex.getHealth().getCurrentHealth();
                int percentRemaining = (int)((double)currentHealth / (double)maximumHealth * 100.0D);
                if (percentRemaining <= 85) {
                    if (Misc.passedProbability(Range.between(0, percentRemaining), 10, true)) {
                        if (stage == 0) {
                            NPCSpawning.spawn(11283, 2914, 5214, 0, 4, 45, true);
                            NPCSpawning.spawn(11284, 2936, 5214, 0, 4, 45, true);
                            NPCSpawning.spawn(11285, 2914, 5192, 0, 4, 45, true);
                            NPCSpawning.spawn(11286, 2936, 5192, 0, 4, 45, true);
                            stage = 1;
                        }

                    }
                }
            }
        }
    }

    public int modifyDamage(Player player, int damage) {
        super.modifyDamage(player, damage);
        return player.getPosition().getAbsDistance(this.getPosition()) > 8 ? 0 : damage;
    }

    public void process() {
        PlayerHandler.nonNullStream().filter((p) -> Boundary.isIn(p, Boundary.Nex)).forEach((p) -> {
            Iterator<Position> var1 = deadlySpots.iterator();

            while(true) {
                Position pos;
                do {
                    do {
                        if (!var1.hasNext()) {
                            if (wrathDMGTimer.isFinished()) {
                                var1 = wrathLocations.iterator();

                                while(true) {
                                    do {
                                        if (!var1.hasNext()) {
                                            return;
                                        }

                                        pos = var1.next();
                                    } while(!p.getPosition().equals(pos));

                                    p.appendDamage(25, Hitmark.HIT);
                                }
                            }

                            return;
                        }

                        pos = var1.next();
                    } while(!p.getPosition().equals(pos));
                } while(!finishedShadows);

                p.appendDamage(Misc.random(10, 30), Hitmark.HIT);
            }
        });
        if (finishedShadows) {
            deadlySpots.removeAll(deadlySpots);
            deadlyPosition.removeAll(deadlyPosition);
            finishedShadows = false;
        }

        if (wrathTimer.isFinished()) {
            wrathLocations.removeAll(wrathLocations);
        }

    }

    public boolean canBeAttacked(Entity entity) {
        return true;
    }

    public boolean canBeDamaged(Entity entity) {
        return true;
    }

    public boolean isFreezable() {
        return false;
    }


    public static void attack(final NPC n) {
        if (n != null && !n.isDead) {
            if (phase >= 2) {
                phase = 0;
            }

            ++currentAttack;
            if (currentAttack >= 20) {
                currentAttack = 0;
            }

            if (n.getHealth().getCurrentHealth() >= 2000) {
                phase = 0;
            } else if (n.getHealth().getCurrentHealth() >= 1800 && n.getHealth().getCurrentHealth() <= 2000) {
//                n.forceChat("Fumus, don't fail me!");
                phase = 1;
            } else if (n.getHealth().getCurrentHealth() >= 680 && n.getHealth().getCurrentHealth() <= 1800) {
//                n.forceChat("Umbra, don't fail me!");
                phase = 2;
            }

            //n.forceChat("attack " + currentAttack + ", phase: " + phase);
            if (currentAttack == 0) {
                switch(phase) {
                    case 0:
                        phases = Nex.NexPhase.SMOKE;
                        n.forceChat(phases.getShout());
                        CycleEventHandler.getSingleton().addEvent(n, new CycleEvent() {
                            int ticks = 0;

                            public void execute(CycleEventContainer container) {
                                if (this.ticks >= 22) {
                                    container.stop();
                                }

                                switch(this.ticks++) {
                                    case 5:
                                        n.forceChat("There is... NO ESCAPE!");
                                        n.teleport(new Position(2925, 5203));
                                    case 11:
                                        ForceMovement forceMovement = new ForceMovement(n, 2, new Position(2937, 5203), Nex.chargePosition[Misc.random(3)], 15, 30);
                                        forceMovement.startForceMovement();
                                        n.startAnimation(9178);
                                        PlayerHandler.nonNullStream().filter((p) -> Boundary.isIn(p, Boundary.Nex)).forEach((p) -> {
                                            if (p.getPosition().deepCopy().withinDistance(n.getPosition().deepCopy(), 15)) {
                                                p.appendDamage(15, Hitmark.HIT);
                                            }

                                        });
                                    default:
                                }
                            }
                        }, 2);
                        break;
                    case 1:
                        phases = Nex.NexPhase.SHADOW;
                        n.forceChat(phases.getShout());
                        CycleEventHandler.getSingleton().addEvent(n, new CycleEvent() {
                            int ticks = 0;

                            public void execute(CycleEventContainer container) {
                                switch(this.ticks++) {
                                    case 4:
                                        n.forceChat("Fear the shadow!");
                                    case 6:
                                        PlayerHandler.nonNullStream().filter((p) -> Boundary.isIn(p, Boundary.Nex)).forEach((p) -> {
                                            Nex.deadlyPosition.add(p.getPosition().deepCopy());

                                            for (Position deadlyLocation : Nex.deadlyPosition) {
                                                Server.playerHandler.sendStillGfx(new StillGraphic(383, deadlyLocation));
                                                Nex.deadlySpots.add(deadlyLocation);
                                            }

                                        });
                                    case 30:
                                        container.stop();
                                    default:
                                }
                            }

                            public void onStopped() {
                                Nex.finishedShadows = true;
                            }
                        }, 2);
                        break;
                    case 2:
                        phases = Nex.NexPhase.ZAROS;
                        n.forceChat(phases.getShout());
//                        for(int t = 0; t < 1+Misc.random(2); t++) {
//                            NPCSpawning.spawn(11293, (n.absX+1)+Misc.random(2), (n.absY+1)+Misc.random(2), 0, 4, 45, true);
//                        }
                        if (!nexHealed) {
                            n.appendHeal(500, Hitmark.HEAL_PURPLE);
                            nexHealed = true;
                        }
                }
            }

        }
    }

    static {
        phases = Nex.NexPhase.SMOKE;
        currentAttack = -1;
        chargePosition = new Position[]{new Position(2925, 5215), new Position(2937, 5203), new Position(2925, 5192), new Position(2914, 5203)};
        deadlySpots = new ArrayList<>();
        deadlyPosition = new ArrayList<>();
        wrathTimer = new TickTimer();
        wrathDMGTimer = new TickTimer();
        wrathLocations = new ArrayList<>();
    }

    enum NexPhase {
        SMOKE("Fill my soul with smoke!", 0),
        SHADOW("Darken my shadow!", 1),
        ZAROS("NOW, THE POWER OF ZAROS!", 2);

        final String shout;
        final int phase;

        NexPhase(String shout, int phase) {
            this.shout = shout;
            this.phase = phase;
        }

        public String getShout() {
            return this.shout;
        }

        public static Nex.NexPhase getPhase() {
            Nex.NexPhase[] var0 = values();
            int var1 = var0.length;
            byte var2 = 0;
            if (var2 < var1) {
                return var0[var2];
            } else {
                return null;
            }
        }
    }
}
