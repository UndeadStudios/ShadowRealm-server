package io.shadowrealm.content.objects;

import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.collisionmap.ObjectDef;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.definitions.AnimationLength;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.util.Misc;

/**
 * made by sgsrocks
 */
public class BondFIres {
    public enum logData {
        LOGS(1511, 1, 40),
        ACHEY_TREE_LOGS(2862, 1, 40),
        OAK_LOGS(1521, 15, 60),
        WILLOW_LOGS(1519, 30, 105),
        TEAK_LOGS(6333, 35, 105),
        MAPLE_LOGS(1517, 45, 135),
        YEW_LOGS(1515, 60, 203),
        ARCTIC_PINE_LOGS(10810, 45, 135),
        MAHOGANY_LOGS(6332, 50, 158),
        MAGIC_LOGS(1513, 75, 304),
        RED_LOGS(7404, 1, 256),
        GREEN_LOGS(7405, 1, 256),
        BLUE_LOGS(7406, 1, 256),
        WHITE_LOGS(10328, 1, 256),
        PURPLE_LOGS(10329, 1, 256),
        REDWOOD_LOGS(19669, 90, 350);

        private int logId, levelRequirement, xp;

        private logData(int logId, int levelRequirement, int xp){
            this.logId = logId;
            this.levelRequirement = levelRequirement;
            this.xp = xp;

        }
        public int getLogID(){
            return logId;
        }
        public int getLevel(){
            return levelRequirement;
        }
        public int getXP(){
            return xp;
        }


    }

    public static void BurnLog(final Player c, int itemId, int objectID) {
        for(final logData g : logData.values()) {
            if(c.getItems().playerHasItem(g.getLogID(), 1)) {
                if (c.playerLevel[11] < g.getLevel()) {
                    c.sendMessage("You need a firemaking level of at least " + g.getLevel() + " to burn the " + ItemCacheDefinition.forID(itemId).getName().toLowerCase() + ".");
                    return;
                }
                if(itemId == g.getLogID()) {
                    c.playerIsFiremaking = true;
                    CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

                        @Override
                        public void execute(CycleEventContainer container) {
                            if(c == null || c.isDisconnected() || c.getSession() == null) {
                                stop();
                                return;
                            }
                            if (c.playerIsFiremaking) {
                                if (!c.getItems().playerHasItem(g.getLogID(), 1)) {
                                    //c.sendMessage("You have run out of molten glass.");
                                    container.stop();
                                    return;
                                }
                                c.startAnimation(733);
                                c.getItems().deleteItem(g.getLogID(), 1);
                                c.getPA().addSkillXPMultiplied(g.getXP(), Skill.FIREMAKING.getId(), true);
                                if (Misc.random(2000) == 1) {
                                    if (c.getItems().getItemCount(20693, false) > 0 || c.petSummonId == 20693) {
                                        return;
                                    }
                                    int rights = c.getRights().getPrimary().getValue() - 1;
                                    c.getItems().addItemUnderAnyCircumstance(20693, 1);
                                    PlayerHandler.executeGlobalMessage("[@red@PET@bla@] @cr20@<col=255> " + c.getDisplayNameFormatted() + "</col> received a Phoenix pet.");
                                    c.getCollectionLog().handleDrop(c, 5, 20693, 1);
                                }
                                //c.sendMessage("You fill "+c.getItems().getItemName(g.getLogID())+" from the "+ ObjectDef.getObjectDef(objectID).name+".");
                            }else {
                                container.stop();
                            }


                        }

                        public void stop() {
                            c.stopAnimation();
                            c.playerIsFiremaking = false;
                        }
                    }, AnimationLength.getFrameLength(733));
                }
            }
        }
    }
}
