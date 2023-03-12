package io.shadowrealm.content.skills.crafting;

import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.definitions.AnimationLength;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.util.Misc;

public class SplitbarkCrafting
{
public static void OpenInterface(Player c){
    if(c.playerIsCrafting) {
        c.getPA().sendChatboxInterface(8938);
        c.getPA().sendFrame126("What would you like to make?", 8966);
        c.getPA().sendFrame246(8941, 190, 3385);
        c.getPA().sendFrame246(8942, 190, 3387);
        c.getPA().sendFrame246(8943, 190, 3391);
        c.getPA().sendFrame246(8944, 190, 3389);
        c.getPA().sendFrame246(8945, 190, 3393);
        c.getPA().sendFrame126(ItemCacheDefinition.forID(3385).getName(), 8949);
        c.getPA().sendFrame126(ItemCacheDefinition.forID(3387).getName(), 8953);
        c.getPA().sendFrame126(ItemCacheDefinition.forID(3391).getName(), 8957);
        c.getPA().sendFrame126(ItemCacheDefinition.forID(3389).getName(), 8961);
        c.getPA().sendFrame126(ItemCacheDefinition.forID(3393).getName(), 8965);
        c.playerIsCrafting = true;
    }
    }
    private static int amount;

    public static void craft(Player c, final int buttonId){
        if (c.playerIsCrafting) {
            return;
        }
        for (final CraftingData.splitbarkData g : CraftingData.splitbarkData.values()) {
            if (buttonId == g.getButtonId(buttonId)) {
                if (c.playerLevel[12] < g.getLevel()) {
                    c.sendMessage("You need a crafting level of " + g.getLevel() + " to make this.");
                    c.getPA().removeAllWindows();
                    return;
                }
                if (!c.getItems().playerHasItem(1734, 1)) {
                    c.sendMessage("You have run out of Thread.");
                    c.getPA().removeAllWindows();
                    return;
                }
                if (!c.getItems().playerHasItem(g.getItemid1(), g.getItem1Amount())) {
                    c.sendMessage("You need "+g.getItem1Amount()+" of  "+ItemCacheDefinition.forID(g.getItemid1()).getName()+" to make this");
                    c.getPA().removeAllWindows();
                    return;
                }
                if (!c.getItems().playerHasItem(g.getItemid2(), g.getItem2Amount())) {
                    c.sendMessage("You need "+g.getItem2Amount()+" of  "+ItemCacheDefinition.forID(g.getItemid2()).getName()+" to make this");
                    c.getPA().removeAllWindows();
                    return;
                }
                c.startAnimation(5243);
                c.getPA().closeAllWindows();
                c.playerIsCrafting = true;
                c.issplitbarkcrafting = true;
                amount = g.getAmount(buttonId);
                CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
                    @Override
                    public void execute(CycleEventContainer container) {
                        if (c == null || c.isDisconnected() || c.getSession() == null) {
                            onStopped();
                            return;
                        }
                        if (c.playerIsCrafting) {
                            if (amount == 0) {
                                c.issplitbarkcrafting = false;
                                container.stop();
                                return;
                            }
                            if (!c.getItems().playerHasItem(1734, 1)) {
                                c.sendMessage("You have run out of Thread.");
                                container.stop();
                                return;
                            }
                            if (!c.getItems().playerHasItem(g.getItemid1(), g.getItem1Amount())) {
                                c.sendMessage("You have run out of bark.");
                                container.stop();
                                return;
                            }
                            if (!c.getItems().playerHasItem(g.getItemid2(), g.getItem2Amount())) {
                                c.sendMessage("You have run out of fine cloth.");
                                container.stop();
                                return;
                            }
                            if (Misc.random(30) == 6) {
                                c.getItems().deleteItem2(1734, 1);
                            }
                            c.getItems().deleteItem2(g.getItemid1(), g.getItem1Amount());
                            c.getItems().deleteItem2(g.getItemid2(), g.getItem2Amount());
                            c.getItems().addItem(g.getProduct(), 1);
                            c.sendMessage("You make a " + ItemDef.forId(g.getProduct()).getName() + ".");
                            c.getPA().addSkillXPMultiplied((int) g.getXp(), 12, true);
                            c.startAnimation(5243);
                            amount--;
                        } else {
                            c.issplitbarkcrafting = false;
                            container.stop();
                        }

                    }

                    @Override
                    public void onStopped() {
                        c.startAnimation(65535);
                        c.playerIsCrafting = true;
                    }
                }, AnimationLength.getFrameLength(5243));
            }
        }
    }
}
