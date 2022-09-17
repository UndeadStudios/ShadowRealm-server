package io.exilius.content.minigames.TBD;

import io.exilius.content.minigames.TBD.instance.TBDInstance;
import io.exilius.content.minigames.TBD.party.TBDParty;
import io.exilius.model.collisionmap.WorldObject;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;

import java.util.List;

/**
 * Handles actions outside of tBD instance.
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
        if (object.getId() != TBDConstants.ENTER_TBD_OBJECT_ID)
            return false;

        startTBD();
        return true;
    }

    public void startTBD() {
        if (!player.inParty(TBDParty.TYPE)) {
            player.sendMessage("You must be in a party to start Theatre of Blood.");
            return;
        }

        if (player.getPA().calculateTotalLevel() < player.getMode().getTotalLevelForTBD()) {
            player.sendStatement("You need " + Misc.insertCommas(player.getMode().getTotalLevelForTBD()) + " total level to compete.");
            return;
        }

        player.getParty().openStartActivityDialogue(player, "Theatre of Blood", TBDConstants.TBD_LOBBY::in, list -> new TBDInstance(list.size()).start(list));
    }

    public boolean handleContainerAction1(int interfaceId, int slot) {
        if (inTBD()) {
            return ((TBDInstance) player.getInstance()).getFoodRewards().handleBuy(player, interfaceId, slot);
        }
        return false;
    }

    public boolean inTBD() {
        return player.getInstance() != null && player.getInstance() instanceof TBDInstance;
    }

}
