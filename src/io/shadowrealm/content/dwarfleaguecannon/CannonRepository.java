package io.shadowrealm.content.dwarfleaguecannon;

import com.google.common.base.Preconditions;
import io.shadowrealm.model.entity.player.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CannonRepository {
    private static final Logger logger = LoggerFactory.getLogger(io.shadowrealm.content.dwarfleaguecannon.Cannon.class);
    private static final Map<Position, io.shadowrealm.content.dwarfleaguecannon.Cannon> CANNON_MAP = new HashMap<>();

    public static boolean add(io.shadowrealm.content.dwarfleaguecannon.Cannon cannon) {
        if (CANNON_MAP.containsKey(cannon.getPosition())) {
            return false;
        }

        CANNON_MAP.put(cannon.getPosition(), cannon);
        return true;
    }

    public static void remove(Cannon cannon) {
        if (!CANNON_MAP.containsKey(cannon.getPosition()))
            return;
        Preconditions.checkState(CANNON_MAP.get(cannon.getPosition()).equals(cannon));
        CANNON_MAP.remove(cannon.getPosition());
    }

    public static boolean hasDistanceFromOtherCannons(Position position) {
        return CANNON_MAP.values().stream().noneMatch(it -> it.getPosition().withinDistance(position, 6) && it.encroaches(position));
    }

    public static boolean exists(Position position) {
        return CANNON_MAP.containsKey(position);
    }

}
