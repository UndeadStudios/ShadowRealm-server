package io.shadowrealm.content.bosses.nightmare.party;

import io.shadowrealm.content.bosses.nightmare.NightmareConstants;
import io.shadowrealm.content.party.PartyFormAreaController;
import io.shadowrealm.content.party.PlayerParty;
import io.shadowrealm.model.entity.player.Boundary;

import java.util.Set;

public class NightmarePartyFormAreaController extends PartyFormAreaController {

    @Override
    public String getKey() {
        return NightmareParty.TYPE;
    }

    @Override
    public Set<Boundary> getBoundaries() {
        return Set.of(NightmareConstants.LOBBY_BOUNDARY);
    }

    @Override
    public PlayerParty createParty() {
        return new NightmareParty();
    }
}
