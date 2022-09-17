package io.exilius.content.minigames.TBD.bosses;

import com.google.common.collect.Lists;
import io.exilius.Server;
import io.exilius.content.combat.Hitmark;
import io.exilius.content.combat.npc.NPCAutoAttackBuilder;
import io.exilius.content.combat.npc.NPCCombatAttack;
import io.exilius.content.instances.InstancedArea;
import io.exilius.content.minigames.TBD.TBDBoss;
import io.exilius.content.minigames.TBD.TBDConstants;
import io.exilius.content.minigames.TBD.instance.TBDInstance;
import io.exilius.model.*;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Position;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Misc;
import io.exilius.util.logging.player.CompletedTBDLog;

import java.util.List;

public class Verzik extends TBDBoss {

    private static final int VERZIK_SUSPENDED_ID = Npcs.VERZIK_VITUR_5;
    private static final int VERZIK_SPIDER_ID = Npcs.VERZIK_VITUR_7;
    private static final Boundary ROOM_BOUNDARY = new Boundary(3155, 4303, 3180, 4322);

    private boolean transforming = false;
    private boolean exitOpen = false;

    public Verzik(InstancedArea instancedArea) {
        super(VERZIK_SUSPENDED_ID, new Position(3167, 4317, instancedArea.getHeight()), instancedArea);
        setAttacks();
    }

    @Override
    public int getDeathAnimation() {
        return 8128;
    }

    @Override
    public void setDead(boolean isDead) {
        if (isDead) {
            if (getNpcId() == VERZIK_SUSPENDED_ID) {
                transform();
            } else {
                super.setDead(isDead);
            }
        }
    }

    @Override
    public void process() {
        if (isDead() && !exitOpen) {
            openExit();
        }

        if (!transforming) {
            super.process();
        }
    }

    private void openExit() {
        exitOpen = true;
        Server.getGlobalObjects().add(new GlobalObject(TBDConstants.TREASURE_ROOM_ENTRANCE_OBJECT_ID, new Position(3167, 4324, getHeight()), 0, 10).setInstance(getInstance()));

        // Teleport dead players to exit
        getInstance().getPlayers().forEach(plr -> {
            if (plr.getAttributes().getBoolean(TBDInstance.TBD_DEAD_ATTR_KEY)) {
                plr.moveTo(new Position(3168, 4322, getInstance().getHeight()));
            }

            plr.getBossTimers().death(TBDConstants.TBD);
            plr.TBDCompletions++;
            plr.sendMessage("You've completed the Theatre of Blood @red@" + plr.TBDCompletions + "@bla@ times.");
            Server.getLogging().write(new CompletedTBDLog(plr, plr.getInstance()));
        });
    }

    private void transform() {
        if (!transforming) {
            transforming = true;
            CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
                @Override
                public void execute(CycleEventContainer container) {
                   if (container.getTotalExecutions() == 0) {
                       startAnimation(8116);
                   } else if (container.getTotalExecutions() == 2) {
                       requestTransform(VERZIK_SPIDER_ID);
                       startAnimation(8119);
                       setAttacks();
                   } else if (container.getTotalExecutions() == 4) {
                       getHealth().reset();
                       startAnimation(Animation.RESET_ANIMATION);
                       forceChat("Behold my true nature!");
                       container.stop();
                       transforming = false;
                   }
                }
            }, 1);
        }
    }

    private void setAttacks() {
        if (getNpcId() == VERZIK_SUSPENDED_ID) {
            setNpcAutoAttacks(Lists.newArrayList(
                    new NPCAutoAttackBuilder()
                            .setAnimation(new Animation(8114))
                            .setCombatType(CombatType.RANGE)
                            .setAttackDamagesPlayer(false)
                            .setOnAttack(npcCombatAttack -> launchRangeAttack())
                            .setDistanceRequiredForAttack(14)
                            .setAttackDelay(5)
                            .createNPCAutoAttack(),
                    new NPCAutoAttackBuilder()
                            .setAnimation(new Animation(8116))
                            .setAttackDamagesPlayer(false)
                            .setCombatType(CombatType.MELEE)
                            .setDistanceRequiredForAttack(18)
                            .setOnAttack(this::smash)
                            .setAttackDelay(3)
                            .setSelectAutoAttack(npcCombatAttack -> getInstance().getPlayers().stream().anyMatch(plr -> insideOf(plr.getPosition())))
                            .createNPCAutoAttack()
            ));
        } else {
            setNpcAutoAttacks(Lists.newArrayList(
                    new NPCAutoAttackBuilder()
                            .setAnimation(new Animation(8123))
                            .setCombatType(CombatType.MAGE)
                            .setDistanceRequiredForAttack(14)
                            .setEndGraphic(new Graphic(1581))
                            .setAttackDelay(5)
                            .setHitDelay(3)
                            .setMaxHit(40)
                            .setPrayerProtectionPercentage(npcCombatAttack -> 0.5)
                            .setProjectile(new ProjectileBaseBuilder().setProjectileId(1594).setSendDelay(1).createProjectileBase())
                            .createNPCAutoAttack(),
                    new NPCAutoAttackBuilder()
                            .setAttackDamagesPlayer(false)
                            .setSelectAutoAttack(npcCombatAttack -> Misc.trueRand(5) == 0)
                            .setAnimation(new Animation(8126))
                            .setOnAttack(npcCombatAttack -> flowerAttack())
                            .setAttackDelay(18)
                            .createNPCAutoAttack()
            ));
        }
    }

    private void flowerAttack() {
        List<Position> safeSpotList = Lists.newArrayList();
        for (int count = 0; count < 4; count++) {
            int x = ROOM_BOUNDARY.getMinimumX();
            int y = ROOM_BOUNDARY.getMinimumY();
            int xLength = ROOM_BOUNDARY.getMaximumX() - x;
            int yLength = ROOM_BOUNDARY.getMaximumY() - y;

            Position position;
            do {
                position = new Position(x + Misc.trueRand(xLength), y + Misc.trueRand(yLength));
            } while(safeSpotList.contains(position));

            safeSpotList.add(getInstance().resolve(position));
        }

        safeSpotList.forEach(position -> Server.playerHandler.sendStillGfx(new StillGraphic(1595, 0, position), getInstance()));

        CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                if (isDead || isUnregister()) {
                    container.stop();
                    return;
                }
                if (container.getTotalExecutions() == 9) {
                    safeSpotList.forEach(pos -> new ProjectileBaseBuilder().setProjectileId(1596).createProjectileBase()
                            .createTargetedProjectile(Verzik.this, pos).send(getInstance()));
                } else if (container.getTotalExecutions() == 11) {
                    getInstance().getPlayers().forEach(plr -> {
                        if (!Boundary.TBD_VERZIK.in(plr))
                            return;
                        if (safeSpotList.stream().noneMatch(pos -> plr.getPosition().equals(pos))) {
                            plr.appendDamage(40 + Misc.trueRand(40), Hitmark.HIT);
                        } else {
                            plr.appendDamage(0, Hitmark.MISS);
                        }
                    });
                    container.stop();
                }
            }
        }, 1);
    }

    private void smash(NPCCombatAttack npcCombatAttack) {
        CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                getInstance().getPlayers().forEach(plr2 -> {
                    if (insideOf(plr2.getPosition())) {
                        int damage = Misc.trueRand(80);
                        plr2.appendDamage(damage, damage > 0 ? Hitmark.MISS : Hitmark.HIT);
                    }
                });

                container.stop();
            }
        }, 2);
    }

    private void launchRangeAttack() {
        getInstance().getPlayers().forEach(plr -> {
            if (plr.getAttributes().containsBoolean(TBDInstance.TBD_DEAD_ATTR_KEY))
                return;
            Position position = plr.getPosition();
            new ProjectileBaseBuilder().setProjectileId(1583).setSendDelay(1).createProjectileBase()
                    .createTargetedProjectile(this, position).send(getInstance());
            CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
                @Override
                public void execute(CycleEventContainer container) {
                    getInstance().getPlayers().forEach(plr2 -> {
                        if (!Boundary.TBD_VERZIK.in(plr))
                            return;
                        if (plr2.getAttributes().containsBoolean(TBDInstance.TBD_DEAD_ATTR_KEY))
                            return;
                        if (plr2.getPosition().equals(position)) {
                            int damage = Misc.trueRand(70);
                            if (plr2.protectingRange()) {
                                damage /= 2;
                            }
                            plr2.appendDamage(damage, damage > 0 ? Hitmark.MISS : Hitmark.HIT);
                        }
                    });

                    Server.playerHandler.sendStillGfx(new StillGraphic(1675, position), getInstance());
                    container.stop();
                }
            }, 2);
        });
    }

    @Override
    public void onDeath() {
        super.onDeath();
        ((TBDInstance) this.getInstance()).setFinalBossComplete(true);
        String message = "Verzik Vitur has died. If you are stuck inside a cage use the command ::uncage";
        this.getInstance().getPlayers().forEach(p -> p.sendMessage(message));
    }


}
