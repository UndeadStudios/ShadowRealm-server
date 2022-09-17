package io.exilius.content.minigames.TBD.party;

import io.exilius.content.party.PartyInterface;
import io.exilius.content.party.PlayerParty;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class TBDParty extends PlayerParty {

    public static final String TYPE = "TBD Party";

    public TBDParty() {
        super(TYPE, 5);
    }

    @Override
    public boolean canJoin(Player invitedBy, Player invited) {
        if (invited.getPA().calculateTotalLevel() < invited.getMode().getTotalLevelForTBD()) {
            invited.sendStatement("You need " + Misc.insertCommas(invited.getMode().getTotalLevelForTBD()) + " total level to compete.");
            invitedBy.sendMessage(invited.getDisplayNameFormatted() + " doesn't have the requirements to compete.");
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
