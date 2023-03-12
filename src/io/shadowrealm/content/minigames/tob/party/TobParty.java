package io.shadowrealm.content.minigames.tob.party;

import io.shadowrealm.content.party.PartyInterface;
import io.shadowrealm.content.party.PlayerParty;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

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
