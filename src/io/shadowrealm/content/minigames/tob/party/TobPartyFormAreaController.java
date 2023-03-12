package io.shadowrealm.content.minigames.tob.party;

import io.shadowrealm.content.minigames.tob.TobConstants;
import io.shadowrealm.content.party.PartyFormAreaController;
import io.shadowrealm.content.party.PlayerParty;
import io.shadowrealm.model.entity.player.Boundary;

import java.util.Set;

public class TobPartyFormAreaController extends PartyFormAreaController {

    @Override
    public String getKey() {
        return TobParty.TYPE;
    }

    @Override
    public Set<Boundary> getBoundaries() {
        return Set.of(TobConstants.TOB_LOBBY);
    }

    @Override
    public PlayerParty createParty() {
        return new TobParty();
    }
}
