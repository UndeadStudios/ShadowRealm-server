package io.shadowrealm.content;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.fireofexchange.FireOfExchange;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.items.ItemAssistant;
import io.shadowrealm.util.Misc;

public class FireOfDestruction {
    public static void burn(Player c, int itemId) {
        c.getPA().closeAllWindows();

        if (Configuration.DISABLE_FOE) {
            c.sendMessage("Fire of Destruction has been temporarily disabled.");
            return;
        }
        if (c.getItems().freeSlots() < 1) {
            c.getDH().sendStatement("@red@You need at least 1 free slot to use this.");
            return;
        }
        if (!c.getItems().playerHasItem(itemId)) {
            c.sendMessage("You do not have an item to burn.");
            return;
        }
        int chance = Misc.random(3);
        int darkPetReward = findDarkVersion(c, itemId);


        c.getItems().deleteItem(itemId, 1);
        if (chance == 3) {
            c.getItems().addItem(darkPetReward, 1);
            if (!c.getRights().hasStaffPosition()) {
                PlayerHandler.executeGlobalMessage("@bla@[@red@@cr18@Dark Pet@bla@] " + c.getDisplayName() + "@pur@ has just received a @bla@" + ItemAssistant.getItemName(darkPetReward) + "!");
            }
            } else {
            //gives back 15% to 25% of foe pet value in coins at 1k gp per foe
//            int base = (int) (FireOfExchange.getExchangeShopPrice(itemId) * 500L * 0.10);
//            int random = Misc.random((int) (FireOfExchange.getExchangeShopPrice(itemId) * 500L / 5L));
            c.getItems().addItemUnderAnyCircumstance(995, 10_000_000);
            c.getItems().addItemUnderAnyCircumstance(691, 1);
            if (!c.getRights().hasStaffPosition()) {
                PlayerHandler.executeGlobalMessage("@bla@[@red@Dark Pet@bla@] " + c.getDisplayName() + "@pur@ sacrificed his @bla@"
                        + ItemAssistant.getItemName(itemId) + " @pur@and received only gp & 10k foe!");
            }
        }
    }

    public static boolean canBurn(int itemToBeBurned) {
        int[] allowedItems = {30010, 30011, 30012, 30013, 30014, 30015, 30016, 30017,
                30018, 30019, 30020, 30021, 30022,  23939};

        for (int item : allowedItems) {
            if (item == itemToBeBurned) {
                return true;
            }
        }
        return false;
    }
    public static int findDarkVersion(Player c, int itemId) {
        if (itemId == 30010) {//postie pete
            return 30110;
        }
        if (itemId == 30011) {//imp
            return 30111;
        }
        if (itemId == 30012) {//toucan
            return 30112;
        }
        if (itemId == 30013) {//king penguin
            return 30113;
        }
        if (itemId == 30014) {//klik
            return 30114;
        }
        if (itemId == 30015) {//shadow warrior
            return 30115;
        }
        if (itemId == 30016) {//ranger
            return 30116;
        }
        if (itemId == 30017) {//mager
            return 30117;
        }
        if (itemId == 30018) {//healer
            return 30118;
        }
        if (itemId == 30019) {//holy
            return 30119;
        }
        if (itemId == 30020) {//corrupt beast
            return 30120;
        }
        if (itemId == 30021) {//roc
            return 30121;
        }
        if (itemId == 30022) {//kratos
            return 30122;
        }
        if (itemId == 23939) {//seren
            return 30123;
        }
        return 0;
    }
}
