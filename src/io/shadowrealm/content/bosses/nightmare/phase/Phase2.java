package io.shadowrealm.content.bosses.nightmare.phase;

import io.shadowrealm.content.bosses.nightmare.Nightmare;
import io.shadowrealm.content.bosses.nightmare.NightmareAttack;
import io.shadowrealm.content.bosses.nightmare.NightmarePhase;
import io.shadowrealm.content.bosses.nightmare.NightmareStatus;
import io.shadowrealm.content.bosses.nightmare.attack.Curse;
import io.shadowrealm.content.bosses.nightmare.attack.GraspingClaws;
import io.shadowrealm.content.bosses.nightmare.attack.Parasites;

public class Phase2 implements NightmarePhase {


    @Override
    public void start(Nightmare nightmare) {

    }

    @Override
    public NightmareStatus getStatus() {
        return NightmareStatus.PHASE_2;
    }

    @Override
    public NightmareAttack[] getAttacks() {
        return new NightmareAttack[] { new GraspingClaws(), new Curse(), new Parasites() };
    }
}
