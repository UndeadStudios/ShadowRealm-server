package io.exilius.content.minigames.TBD.party;

import io.exilius.content.minigames.TBD.TBDConstants;
import io.exilius.content.party.PartyFormAreaController;
import io.exilius.content.party.PlayerParty;
import io.exilius.model.entity.player.Boundary;

import java.util.Set;

public class TobPartyFormAreaController extends PartyFormAreaController {

    @Override
    public String getKey() {
        return TobParty.TYPE;
    }

    @Override
    public Set<Boundary> getBoundaries() {
        return Set.of(TBDConstants.TOB_LOBBY);
    }

    @Override
    public PlayerParty createParty() {
        return new TobParty();
    }
}
