package io.shadowrealm.model.entity.player.packets.objectoptions;

import io.shadowrealm.Server;
import io.shadowrealm.content.skills.construction.Construction;
import io.shadowrealm.content.skills.construction.Objects;
import io.shadowrealm.model.collisionmap.ObjectDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.world.objects.GlobalObject;
import io.shadowrealm.util.Misc;

public class ObjectOptionFive {

    public static void handleOption(final Player c, int objectType, int obX, int obY) {
        if (Server.getMultiplayerSessionListener().inAnySession(c)) {
            return;
        }
        GlobalObject object = new GlobalObject(objectType, obX, obY, c.heightLevel);
        Construction.handleConstructionClick(c, objectType, obX, obY);
        Objects.handleObjectClick(c, objectType);
        c.getPA().resetVariables();
        c.clickObjectType = 0;
        //c.getPA.(obX, obY);
        ObjectDef def = ObjectDef.getObjectDef(objectType);
        if (c.getRights().isOrInherits(Right.OWNER))
            c.sendMessage("Clicked Object Option 5:  "+objectType+", name: "+def.getName()+", face: "+object.getFace()+", type: "+object.getType());
        switch (objectType) {
            case 5492:
                if (c.getItems().playerHasItem(1523, 1)
                        && Misc.random(4) < 3) {
                    c.getPA().movePlayer(3149, 9652, 0);
                    c.sendMessage(
                            "You go down the trapdoor.");
                    c.startAnimation(827);
                    c.getPA().addSkillXPMultiplied(.5,
                            c.playerThieving, true);
                    c.getPA().closeAllWindows();
                } else if (!c.getItems().playerHasItem(1523, 1)
                        && Misc.random(5) < 2) {
                    c.getPA().movePlayer(3149, 9652, 0);
                    c.sendMessage(
                            "You go down the trapdoor.");
                    c.startAnimation(827);
                    c.getPA().addSkillXPMultiplied(.5,
                            c.playerThieving, false);
                    c.getPA().closeAllWindows();
                    c.resetWalkingQueue();
                } else if (c.getItems().playerHasItem(1523, 1)
                        && Misc.random(4) > 3) {
                    c.sendMessage(
                            "You fail to pick the lock.");
                    c
                            .sendMessage(
                                    "Your thieving has been drained, your fingers feel numb.");
                    c.playerLevel[17] = c.getPA()
                            .getLevelForXP(c.playerXP[17]) - 1;
                    c.getPA().refreshSkill(17);
                    c.getItems().deleteItem(1523, 1);
                } else if (!c.getItems().playerHasItem(1523, 1)
                        && Misc.random(5) > 2) {
                    c.sendMessage(
                            "You fail to pick the lock.");
                    c
                            .sendMessage(
                                    "Your thieving has been drained, your fingers feel numb.");
                    c.playerLevel[17] = c.getPA()
                            .getLevelForXP(c.playerXP[17]) - 1;
                    c.getPA().refreshSkill(17);
                }
                break;
            case 12309:
                c.getShops().openShop(14);
                break;
        }
    }

}

