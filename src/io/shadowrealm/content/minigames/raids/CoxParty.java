package io.shadowrealm.content.minigames.raids;

import io.shadowrealm.content.party.PartyInterface;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.content.party.PlayerParty;

public class CoxParty extends PlayerParty {

    public static final String TYPE = "CoX Party";

    public CoxParty() {
        super(TYPE, 100);
    }

    @Override
    public boolean canJoin(Player invitedBy, Player invited) {
        if (Raids.isMissingRequirements(invited)) {
            invitedBy.sendMessage("That player doesn't have the requirements to play Chambers of Xeric.");
            return false;
        }

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
