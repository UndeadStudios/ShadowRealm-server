package io.exilius.content.bosses;

import com.google.common.collect.Lists;
import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.combat.Hitmark;
import io.exilius.content.event.eventcalendar.EventChallenge;
import io.exilius.content.events.monsterhunt.MonsterHunt;
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
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;
import org.apache.commons.lang3.Range;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Nex extends NPC {
    private final List<NPC> npcs = Lists.newArrayList();

    public static final int KEY = 3460;
    public static long NEX = System.currentTimeMillis();

    public static int phase = -1;
    public static NPC nex;
    public static boolean spawned;
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

    public static void rewardPlayers() {
        NEX = System.currentTimeMillis();
        spawned = false;
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
                       // p.getEventCalendar().progress(EventChallenge.OBTAIN_X_WILDY_EVENT_KEYS);
//					LeaderboardUtils.addCount(LeaderboardType.WILDY_EVENTS, p, 1);
                      //  Achievements.increase(p, AchievementType.WILDY_EVENT, 1);
                        p.setNexDamageCounter(0);
                    } else {
                        p.sendMessage("@blu@You didn't do enough damage to the boss to receive a reward.");
                        p.setNexDamageCounter(0);
                    }

                });
    }



    public void init() {
        NPCSpawning.spawnNpc(11278, 2925, 5203, 0, 0, 70 );
       }

    public static void spawnHealer() {
        NPC Nex = NPCHandler.getNpc(11278);
        spawned = true;
        if (Nex != null) {
            List<NPC> healer = Arrays.asList(NPCHandler.npcs);
            if (!healer.stream().filter(Objects::nonNull).anyMatch((n) -> {
                return n.getNpcId() == 11294 && !n.isDead() && n.getHealth().getCurrentHealth() > 0;
            })) {
                int maximumHealth = Nex.getHealth().getMaximumHealth();
                int currentHealth = Nex.getHealth().getCurrentHealth();
                int percentRemaining = (int)((double)currentHealth / (double)maximumHealth * 100.0D);
                if (percentRemaining <= 60) {
                    if (Misc.passedProbability(Range.between(0, percentRemaining), 10, true)) {
                        if (stage == 0) {
                            NPCSpawning.spawnNpc(11283, 2914, 5214, 0, 0, 30);
                            NPCSpawning.spawnNpc(11284, 2936, 5214, 0, 0, 30);
                            NPCSpawning.spawnNpc(11285, 2914, 5192, 0, 0, 30);
                            NPCSpawning.spawnNpc(11286, 2936, 5192, 0, 0, 30);
                            stage = 1;
                        }

                    }
                }
            }
        }
    }

    public int modifyDamage(Player player, int damage) {
        super.modifyDamage(player, damage);
        return player.getPosition().getDistance(this.getPosition()) > 8 ? 0 : damage;
    }

    public void process() {
        PlayerHandler.nonNullStream().filter((p) -> {
            return Boundary.isIn(p, Boundary.Nex);
        }).forEach((p) -> {
            Iterator var1 = deadlySpots.iterator();

            while(true) {
                do {
                    Position pos;
                    do {
                        do {
                            if (!var1.hasNext()) {
                                if (wrathDMGTimer.isFinished()) {
                                    var1 = wrathLocations.iterator();

                                    while(true) {
                                        do {
                                            do {
                                                if (!var1.hasNext()) {
                                                    return;
                                                }

                                                pos = (Position)var1.next();
                                            } while(!p.getPosition().equals(pos));
                                        } while(p == null && (p.isDead || wrathTimer.isFinished()));

                                        p.appendDamage(25, Hitmark.HIT);
                                    }
                                }

                                return;
                            }

                            pos = (Position)var1.next();
                        } while(!p.getPosition().equals(pos));
                    } while(!finishedShadows);
                } while(p == null && p.isDead);

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
//    public void killNpcs() {
//        npcs.forEach(it -> it.appendDamage(it.getHealth().getCurrentHealth(), Hitmark.HIT));
//    }

    public void onDeath() {
        NPCHandler.kill(11283, 0);
        NPCHandler.kill(11284, 0);
        NPCHandler.kill(11285, 0);
        NPCHandler.kill(11286, 0);
        isDead();
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
                phase = 1;
            } else if (n.getHealth().getCurrentHealth() >= 680 && n.getHealth().getCurrentHealth() <= 1800) {
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
                                        ForceMovement forceMovement = new ForceMovement(n, 2, new Position(2925, 5203), Nex.chargePosition[Misc.random(3)], 15, 30);
                                        forceMovement.startForceMovement();
                                        n.startAnimation(9178);
                                        PlayerHandler.nonNullStream().filter((p) -> {
                                            return Boundary.isIn(p, Boundary.Nex);
                                        }).forEach((p) -> {
                                            if (p.getPosition().deepCopy().withinDistance(n.getPosition().deepCopy(), 5)) {
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
                                        PlayerHandler.nonNullStream().filter((p) -> {
                                            return Boundary.isIn(p, Boundary.Nex);
                                        }).forEach((p) -> {
                                            Nex.deadlyPosition.add(p.getPosition().deepCopy());
                                            Iterator var1 = Nex.deadlyPosition.iterator();

                                            while(var1.hasNext()) {
                                                Position deadlyLocation = (Position)var1.next();
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
        chargePosition = new Position[]{new Position(2925, 5211), new Position(2934, 5203), new Position(2925, 5194), new Position(2916, 5203)};
        deadlySpots = new ArrayList();
        deadlyPosition = new ArrayList();
        wrathTimer = new TickTimer();
        wrathDMGTimer = new TickTimer();
        wrathLocations = new ArrayList();
    }

    static enum NexPhase {
        SMOKE("Fill my soul with smoke!", 0),
        SHADOW("Darken my shadow!", 1),
        ZAROS("NOW, THE POWER OF ZAROS!", 2);

        String shout;
        int phase;

        private NexPhase(String shout, int phase) {
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
                Nex.NexPhase n = var0[var2];
                return n;
            } else {
                return null;
            }
        }
    }
}
