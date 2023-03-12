package io.shadowrealm.content.skills.fletching;

import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

public class MakeJavelinEvent extends Event<Player> {

    private FletchableJavelin a = null;

    public MakeJavelinEvent(Player att, FletchableJavelin a) {
        super("skilling", att, 2);
        this.a = a;
    }
    @Override
    public void execute() {
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }
        if (a == null) {
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(19584, 15)) {
            plr.sendMessage("You need at least 15 javelin shafts to do this.");
            plr.getPA().removeAllWindows();
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(a.getId(), 15)) {
            plr.sendMessage("You need at least 15 javelin heads to do this.");
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
        plr.getItems().deleteItem2(19584, 15);
        plr.getItems().deleteItem2(a.getId(), 15);
        plr.getItems().addItem(a.getReward(), 15);
        plr.getPA().addSkillXPMultiplied((int) a.getExperience(), Skill.FLETCHING.getId(), true);
    }
}
