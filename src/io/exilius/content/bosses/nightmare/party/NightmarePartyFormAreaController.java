package io.exilius.content.bosses.nightmare.party;

import io.exilius.content.bosses.nightmare.NightmareConstants;
import io.exilius.content.party.PartyFormAreaController;
import io.exilius.content.party.PlayerParty;
import io.exilius.model.entity.player.Boundary;

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
