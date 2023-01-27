package io.exilius.content.minigames.tob.party;

import io.exilius.content.party.PartyInterface;
import io.exilius.content.party.PlayerParty;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class TobParty extends PlayerParty {

    public static final String TYPE = "ToB Party";

    public TobParty() {
        super(TYPE, 5);
    }
    public static int getTotalLevelForTob() {
        return 1000;
    }
    @Override
    public boolean canJoin(Player invitedBy, Player invited) {
        if (invited.getPA().calculateTotalLevel() < getTotalLevelForTob()) {
            invited.sendStatement("You need " + Misc.insertCommas(getTotalLevelForTob()) + " total level to compete.");
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
