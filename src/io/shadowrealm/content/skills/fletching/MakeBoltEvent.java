package io.shadowrealm.content.skills.fletching;

import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

public class MakeBoltEvent extends Event<Player> {

    private FletchableBolt b = null;
    private int boltId, tipId;

    public MakeBoltEvent(Player att, FletchableBolt a, int bolt, int tip) {
        super("skilling", att, 2);
        this.b = a;
        this.boltId = bolt;
        this.tipId = tip;
    }
    @Override
    public void execute() {
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }
        if (b == null) {
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(tipId, 15)) {
            plr.sendMessage("You need at least 15 tips to do this.");
            plr.getPA().removeAllWindows();
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(boltId, 15)) {
            plr.sendMessage("You need at least 15 bolts to do this.");
            plr.getPA().removeAllWindows();
            stop();
            return;
        }
        if (plr.getItems().freeSlots() < 1) {
            plr.sendMessage("You need at least 1 free slot to do this.");
            plr.getPA().removeAllWindows();
            stop();
            return;
        }
        plr.getItems().deleteItem2(boltId, 15);
        plr.getItems().deleteItem2(tipId, 15);
        plr.getItems().addItem(b.getBolt(), 15);
        plr.getPA().addSkillXPMultiplied(b.getExperience(), Skill.FLETCHING.getId(), true);
    }
}
