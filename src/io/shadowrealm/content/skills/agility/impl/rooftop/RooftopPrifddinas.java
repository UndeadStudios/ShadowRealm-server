package io.shadowrealm.content.skills.agility.impl.rooftop;

import io.shadowrealm.content.skills.agility.AgilityHandler;
import io.shadowrealm.content.skills.agility.MarkOfGrace;
import io.shadowrealm.model.entity.player.Player;

public class RooftopPrifddinas {

    public static final int
            LADDER1 = 36221,
            Tightrope1 = 36225,
            chimney = 36227,
            roofEdge = 36228,
            DarkHole = 36229,
            ladder2 = 36231,
            RobeBridge1 = 36233,
            Tightrobe2 = 36234,
            RopeBridge2 = 36235,
            Tightrope3 = 36236,
            Tightrope4 = 36237,
            DarkHole2 = 36238;

    public static int[] PRIFF_OBJECTS = { LADDER1, Tightrope1, chimney, roofEdge, DarkHole, ladder2, RobeBridge1, Tightrobe2, RopeBridge2, Tightrope3, Tightrope4, DarkHole2 };
    public boolean execute(final Player c, final int objectId) {
        for (int id : PRIFF_OBJECTS) {
            if (System.currentTimeMillis() - c.lastObstacleFail < 3000) {
                return false;
            }
            if (c.getAgilityHandler().checkLevel(c, objectId)) {
                return false;
            }
            if (id == objectId) {
                MarkOfGrace.spawnMarks(c, "PRIFF");
            }
        }

        switch (objectId) {
            case LADDER1:
                AgilityHandler.delayEmote(c, "CLIMB_UP", 3255, 6109, 2, 2);
                c.getAgilityHandler().agilityProgress[0] = true;
                return true;
            case Tightrope1:
                 if (AgilityHandler.failObstacle(c, 3264, 6105, 0)) {
                  return false;
                 }
                c.getPA().movePlayer(3257, 6105, c.heightLevel);
                c.setForceMovement(3272, 6105, 0, 220, "EAST", 762);
                c.getAgilityHandler().agilityProgress[2] = true;
                return true;
        }
        return false;
    }
}
