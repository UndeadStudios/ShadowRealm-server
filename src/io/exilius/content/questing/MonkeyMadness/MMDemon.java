package io.exilius.content.questing.MonkeyMadness;

import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Position;

public class MMDemon extends NPC {

    public MMDemon(Position position) {
        super(1443, position);
        getBehaviour().setAggressive(true);
    }
}
