package io.shadowrealm.content.bosses.nightmare.party;

import io.shadowrealm.content.party.PartyInterface;
import io.shadowrealm.content.party.PlayerParty;
import io.shadowrealm.model.entity.player.Player;

public class NightmareParty extends PlayerParty {

    public static final String TYPE = "Nightmare Party";

    public NightmareParty() {
        super(TYPE, 100);
    }

    @Override
    public boolean canJoin(Player invitedBy, Player invited) {
        return true;
    }

    @Override
    public void onJoin(Player player) {
        PartyInterface.refreshOnJoinOrLeave(player, this);
    }

    @Override
    public void onLeave(Player player) {
        PartyInterface.refreshOnJoinOrLeave(player, this);
    }
}
