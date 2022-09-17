package io.exilius.content.minigames.TBD;

import io.exilius.content.minigames.TBD.instance.TBDInstance;
import io.exilius.content.minigames.TBD.party.TobParty;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;

import java.util.List;

/**
 * Handles actions outside of tob instance.
 */
public class TBDContainer {

    private final Player player;

    public TBDContainer(Player player) {
        this.player = player;
    }

    public void displayRewardInterface(List<GameItem> rewards) {
        player.getItems().sendItemContainer(22961, rewards);
        player.getPA().showInterface(22959);
    }

    public boolean handleClickObject(WorldObject object, int option) {
        if (object.getId() != TBDConstants.ENTER_TOB_OBJECT_ID)
            return false;

        startTob();
        return true;
    }

    public void startTob() {
        if (!player.inParty(TobParty.TYPE)) {
            player.sendMessage("You must be in a party to start Theatre of Blood.");
            return;
        }

        if (player.getPA().calculateTotalLevel() < player.getMode().getTotalLevelForTob()) {
            player.sendStatement("You need " + Misc.insertCommas(player.getMode().getTotalLevelForTob()) + " total level to compete.");
            return;
        }

        player.getParty().openStartActivityDialogue(player, "Theatre of Blood", TBDConstants.TOB_LOBBY::in, list -> new TBDInstance(list.size()).start(list));
    }

    public boolean handleContainerAction1(int interfaceId, int slot) {
        if (inTob()) {
            return ((TBDInstance) player.getInstance()).getFoodRewards().handleBuy(player, interfaceId, slot);
        }
        return false;
    }

    public boolean inTob() {
        return player.getInstance() != null && player.getInstance() instanceof TBDInstance;
    }

}
