package io.shadowrealm.content.questing.MonkeyMadness;

import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Position;

public class MMDemon extends NPC {

    public MMDemon(Position position) {
        super(1443, position);
        getBehaviour().setAggressive(true);
    }
}
