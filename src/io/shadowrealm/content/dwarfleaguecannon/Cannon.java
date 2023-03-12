package io.shadowrealm.content.dwarfleaguecannon;

import com.google.common.collect.Lists;
import io.shadowrealm.Server;
import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.Projectile;
import io.shadowrealm.model.ProjectileBaseBuilder;
import io.shadowrealm.model.collisionmap.PathChecker;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Position;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.world.objects.GlobalObject;
import io.shadowrealm.util.Misc;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.ALLOWED_REV_AREAS;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.CANNON_PIECES;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.CANNON_SIZE;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.COMPLETE_CANNON_OBJECT_ID;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.MAX_DAMAGE;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.MAX_GRANITE_DAMAGE;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.PLACING_ANIMATION;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.PROHIBITED_CANNON_AREAS;
import static io.shadowrealm.content.dwarfleaguecannon.CannonConstants.PROJECTILE_ID;

@Slf4j
public class Cannon {

    private static HashSet<Boundary> BLOCKED_BOUNDARIES = new HashSet<>(Arrays.asList(
            Boundary.MAGE_ARENA
    ));

    public static void attemptPlace(Player player) {
        if (Arrays.stream(PROHIBITED_CANNON_AREAS).anyMatch(boundary -> boundary.in(player)) && !Arrays.stream(ALLOWED_REV_AREAS).anyMatch(boundary -> boundary.in(player))) {
            player.sendMessage("You can't place a cannon in this area.");
        } else if ((player.getInstance() != null || player.getHeight() > 3) && !Boundary.CORPOREAL_BEAST_LAIR.in(player) && !Boundary.CRYSTAL_CAVE_AREA.in(player)) {
            player.sendMessage("You can't place a cannon inside an instance.");
        } else if (Boundary.CRYSTAL_CAVE_AREA.in(player) && player.getPosition().getHeight() == 4) {
            player.sendMessage("You can't place a cannon in this cave.");
        } else if (player.getLeagueCannon() != null && !player.getRights().isOrInherits(Right.OWNER) || player.getCannon() != null && !player.getRights().isOrInherits(Right.OWNER)) {
            player.sendMessage("You already have a cannon.");
        } else if (!Arrays.stream(CANNON_PIECES).allMatch(piece -> player.getItems().playerHasItem(piece))) {
            player.sendMessage("Your missing a cannon piece!");
        } else if (!io.shadowrealm.content.dwarfleaguecannon.CannonRepository.hasDistanceFromOtherCannons(player.getPosition())) {
            player.sendMessage("You can't place your cannon this close to another cannon.");
        } else {
            // Clipping check
            Optional<Boundary> inBlockedBoundary = BLOCKED_BOUNDARIES.stream().filter(boundary -> boundary.in(player)).findAny();
            if (inBlockedBoundary.isPresent()) {
                player.sendMessage("You cannot place a cannon here.");
                return;
            }

            for (int x = player.getPosition().getX(); x < player.getPosition().getX() + 2; x++) {
                for (int y = player.getPosition().getY(); y < player.getPosition().getY() + 2; y++) {
                    if (player.getRegionProvider().getClipping(x, y, player.getHeight()) != 0
                            && !player.getRegionProvider().isOccupiedByNpc(x, y, player.getHeight())) {
                        player.sendMessage("You don't have enough space to place a cannon here.");
                        return;
                    }
                }
            }

            player.setLeagueCannon(new io.shadowrealm.content.dwarfleaguecannon.Cannon(player.getPosition()));
            player.getLeagueCannon().place(player);
        }
    }

    public static boolean clickObject(Player player, int objectId, Position position, int option) {
        if (objectId == COMPLETE_CANNON_OBJECT_ID) {
            if (!io.shadowrealm.content.dwarfleaguecannon.CannonRepository.exists(position)) {
                log.error("No cannon exists but is being clicked: " + position);
            }

            if (player.getLeagueCannon() != null && player.getLeagueCannon().getPosition().equals(position)) {
                if (option == 1) {
                    player.getLeagueCannon().load(player);
                } else if (option == 2) {
                    player.getLeagueCannon().pickup(player, true);
                } else if (option == 3) {
                    player.getLeagueCannon().unload(player);
                }
            } else {
                player.sendMessage("This isn't your cannon.");
            }

            return true;
        }

        return false;
    }

    public static boolean clickItem(Player player, int itemId) {
        if (itemId == 26520) {
            io.shadowrealm.content.dwarfleaguecannon.Cannon.attemptPlace(player);
            return true;
        }

        return false;
    }

    /**
     * Cannon identifier.
     */
    private static int globalCannonIdentifier = 0;

    /**
     * Unique id to identify the cannon instance.
     */
    private final int identifier;

    /**
     * The amount of balls in the cannon.
     */
    private int cannonballsLoaded = 0;

    /**
     * Is the cannon rotating and shooting?
     */
    private boolean operating = false;

    /**
     * Cannon position;
     */
    private final Position position;

    /**
     * The cannon game object.
     */
    private final GlobalObject object;

    /**
     * State of the cannon rotation.
     */
    private io.shadowrealm.content.dwarfleaguecannon.CannonRotationState rotationState = io.shadowrealm.content.dwarfleaguecannon.CannonRotationState.NORTH;

    public Cannon(Position position) {
        this.position = position;
        identifier = globalCannonIdentifier++;
        object = new GlobalObject(COMPLETE_CANNON_OBJECT_ID, getPosition(), 0, 10);
    }

    public boolean encroaches(Position position) {
        List<Position> local = getPosition().getTiles(CANNON_SIZE);
        List<Position> other = position.getTiles(CANNON_SIZE);
        return local.stream().anyMatch(other::contains);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        io.shadowrealm.content.dwarfleaguecannon.Cannon cannon = (io.shadowrealm.content.dwarfleaguecannon.Cannon) o;
        return identifier == cannon.identifier &&
                Objects.equals(position, cannon.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, position);
    }

    private void place(Player player) {
        if (io.shadowrealm.content.dwarfleaguecannon.CannonRepository.add(this)) {
            Arrays.stream(CANNON_PIECES).forEach(piece -> player.getItems().deleteItem(piece, 1));
            player.startAnimation(PLACING_ANIMATION);
            Server.getGlobalObjects().add(getObject());
            load(player);
        } else {
            player.setLeagueCannon(null);
            player.sendMessage("You can't set a cannon there!");
        }
    }

    private void load(Player player) {
        int ballType = player.getItems().getInventoryCount(Items.GRANITE_CANNONBALL) > 0 ? Items.GRANITE_CANNONBALL : Items.CANNONBALL;
        int balls = player.getItems().getInventoryCount(ballType);

        if (player.usingGraniteCannonballs && cannonballsLoaded > 0 && ballType == Items.CANNONBALL) {
            player.sendMessage("You cannot mix cannonball types in your cannon.");
            return;
        }
        if (player.getItems().getInventoryCount(Items.GRANITE_CANNONBALL) > 0) {
            ballType = Items.GRANITE_CANNONBALL;
            balls = player.getItems().getInventoryCount(ballType);
            player.usingGraniteCannonballs = true;
        } else {
            player.usingGraniteCannonballs = false;
        }

        int max = getCannonMaxAmmoCount(player);
        int adding = max - cannonballsLoaded;
        if (adding > balls) {
            adding = balls;
        }

        if (cannonballsLoaded == max) {
            player.sendMessage("Your cannon is already fully loaded.");
        } else if (adding > 0) {
            player.getItems().deleteItem(ballType, adding);
            cannonballsLoaded += adding;
            operating = true;
            player.sendMessage("You load " + adding + " cannonballs into your cannon.");
        } else {
            player.sendMessage("You don't have any cannonballs to load.");
        }
    }

    private void unload(Player player) {
        int item = Items.CANNONBALL;
        if (player.usingGraniteCannonballs) {
            item = Items.GRANITE_CANNONBALL;
        }
        if (cannonballsLoaded > 0) {
            if (player.getItems().hasRoomInInventory(item, cannonballsLoaded)) {
                player.getItems().addItem(item, cannonballsLoaded);
                cannonballsLoaded = 0;
            } else {
                player.getItems().sendItemToAnyTab(item, cannonballsLoaded);
                cannonballsLoaded = 0;
            }
        } else {
            player.sendMessage("Your cannon is empty.");
        }
    }

    public void pickup(Player player, boolean manual) {
        if (manual) {
            if (player.getItems().freeSlots() < 4) {
                player.sendMessage("You need at least 4 spaces to pick this up.");
                return;
            }
            Arrays.stream(CANNON_PIECES).forEach(it -> player.getItems().addItem(it, 1));
        } else {
            Arrays.stream(CANNON_PIECES).forEach(it -> player.getItems().addItemUnderAnyCircumstance(it, 1));
        }

        if (cannonballsLoaded > 0) {
            int item = Items.CANNONBALL;
            if (player.usingGraniteCannonballs) {
                item = Items.GRANITE_CANNONBALL;
            }
            if (player.getItems().hasRoomInInventory(item, cannonballsLoaded)) {
                player.getItems().addItem(item, cannonballsLoaded);
            } else {
                player.getItems().sendItemToAnyTab(item, cannonballsLoaded);
            }
        }

        CannonRepository.remove(this);
        Server.getGlobalObjects().remove(getObject());
        Server.getGlobalObjects().updateObject(getObject(), -1);
        player.setLeagueCannon(null);
    }

    private int tickCheck = 1;

    public void tick(Player player) {
        if (operating && player.distance(getPosition()) <= 18) {
            rotate();
            shoot(player);
        }
/*        if (!operating && player.distance(getPosition()) <= 18 && tickCheck <= 0) {
            Server.playerHandler.sendObjectAnimation(getObject(), 9255);
            tickCheck = 1;
        }
        tickCheck--;*/
    }

    private void rotate() {
        int next = (rotationState.ordinal() + 1) % io.shadowrealm.content.dwarfleaguecannon.CannonRotationState.values().length;
        rotationState = io.shadowrealm.content.dwarfleaguecannon.CannonRotationState.values()[next];
        Server.playerHandler.sendObjectAnimation(getObject(), rotationState.getAnimationId());
    }

    private void shoot(Player player) {
        List<NPC> npcs = getShootableNpcs(player);
        if (!npcs.isEmpty()) {
            if (cannonballsLoaded > 0) {
                for (NPC npc : npcs) {
                    int maxDamage = MAX_DAMAGE;
                    if (player.usingGraniteCannonballs) {
                        maxDamage = MAX_GRANITE_DAMAGE;
                    }
                    if (npc.getNpcId() == Npcs.CORPOREAL_BEAST) {
                        maxDamage /= 2;
                    }
                    if (npc.getNpcId() == 6319) {
                        maxDamage /= 3;
                    }
                    int damage = Misc.random(maxDamage);
                    player.getPA().addSkillXPMultiplied(damage * 2, Skill.RANGED.getId(), true);
                    Projectile.createTargeted(getPosition().getCenterPosition(3), 2, npc, new ProjectileBaseBuilder().setProjectileId(PROJECTILE_ID).setCurve(0).setSendDelay(2).createProjectileBase()).send(null);
                    CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
                        @Override
                        public void execute(CycleEventContainer container) {
                            npc.appendDamage(player, damage, damage > 0 ? Hitmark.HIT : Hitmark.MISS);
                            if (npc.isAutoRetaliate()) {
                                npc.attackEntity(player);
                            }
                            container.stop();
                        }
                    }, 1);

                    cannonballsLoaded--;
                    if (cannonballsLoaded == 0) {
                        break;//TRUE
                    }
                }
            } else if (player.itemAssistant.hasItemOnOrInventory(Items.CANNONBALL) && player.amDonated >= 1000 && cannonballsLoaded <= 5 || player.itemAssistant.hasItemOnOrInventory(Items.GRANITE_CANNONBALL) && player.amDonated >= 1000  && cannonballsLoaded <= 5) {
                load(player);
            } else if (player.itemAssistant.hasItemOnOrInventory(Items.CANNONBALL) && Misc.random(1,10) == 1 && cannonballsLoaded <= 5 || player.itemAssistant.hasItemOnOrInventory(Items.GRANITE_CANNONBALL) && Misc.random(1,10) == 1 && cannonballsLoaded <= 5) {
                load(player);
            } else {
                operating = false;
                player.sendMessage("Your cannon has run out of ammo.");
            }
        }
    }

    public List<NPC> getShootableNpcs(Player player) {
        List<NPC> possibleTargets = Arrays.stream(NPCHandler.npcs).filter(npc -> {
            return npc != null && !npc.isDead && npc.heightLevel == getPosition().getHeight()
                    && npc.distance(getPosition()) <= 12
                    && player.attacking.attackEntityCheck(npc, false)
                    && PathChecker.raycast(player, npc, true);
        }).collect(Collectors.toList());

        int rotationStateIndex = (rotationState.ordinal() + 1) % io.shadowrealm.content.dwarfleaguecannon.CannonRotationState.values().length;
        List<NPC> targets = Lists.newArrayList();
        for (NPC local : possibleTargets) {
            int cannonX = getPosition().getX();
            int cannonY = getPosition().getY();
            int localX = local.getPosition().getX();
            int localY = local.getPosition().getY();

            switch (io.shadowrealm.content.dwarfleaguecannon.CannonRotationState.values()[rotationStateIndex]) {
                case NORTH:
                    if (localY > cannonY && localX >= cannonX - 1 && localX <= cannonX + 1)
                        targets.add(local);
                    break;
                case NORTH_EAST:
                    if (localX >= cannonX + 1 && localY >= cannonY + 1)
                        targets.add(local);
                    break;
                case EAST:
                    if (localX > cannonX && localY >= cannonY - 1 && localY <= cannonY + 1)
                        targets.add(local);
                    break;
                case SOUTH_EAST:
                    if (localY <= cannonY - 1 && localX >= cannonX + 1)
                        targets.add(local);
                    break;
                case SOUTH:
                    if (localY < cannonY && localX >= cannonX - 1 && localX <= cannonX + 1)
                        targets.add(local);
                    break;
                case SOUTH_WEST:
                    if (localX <= cannonX - 1 && localY <= cannonY - 1)
                        targets.add(local);
                    break;
                case WEST:
                    if (localX < cannonX && localY >= cannonY - 1 && localY <= cannonY + 1)
                        targets.add(local);
                    break;
                case NORTH_WEST:
                    if (localX <= cannonX - 1 && localY >= cannonY + 1)
                        targets.add(local);
                    break;
            }
        }

        return targets.stream().limit(2).collect(Collectors.toList());
    }

    private GlobalObject getObject() {
        return object;
    }

    public Position getPosition() {
        return position;
    }

    private int getCannonMaxAmmoCount(Player player) {
        if (player.amDonated >= 500)
            return 100;
        if (player.amDonated >= 250)
            return 80;
        if (player.amDonated >= 100)
            return 70;
        if (player.amDonated >= 50)
            return 50;
        if (player.amDonated >= 20)
            return 40;
        return 30;
    }
}
