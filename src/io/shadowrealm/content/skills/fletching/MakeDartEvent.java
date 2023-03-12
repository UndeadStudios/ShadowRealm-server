package io.shadowrealm.content.skills.fletching;

import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.Player;

public class MakeDartEvent extends Event<Player> {

    private FletchableDart d = null;

    public MakeDartEvent(Player att, FletchableDart a) {
        super("skilling", att, 2);
        this.d = a;
    }
    @Override
    public void execute() {
        if (plr == null || plr.isDisconnected() || plr.getSession() == null) {
            stop();
            return;
        }
        if (d == null) {
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(314, 10)) {
            plr.sendMessage("You need at least 10 feathers to do this.");
            plr.getPA().removeAllWindows();
            stop();
            return;
        }
        if (!plr.getItems().playerHasItem(d.getId(), 10)) {
            plr.sendMessage("You need at least 10 dart tips to do this.");
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
        plr.getItems().deleteItem2(314, 10);
        plr.getItems().deleteItem2(d.getId(), 10);
        plr.getItems().addItem(d.getReward(), 10);
        plr.getPA().addSkillXPMultiplied((int) (10 * d.getExperience()), Skill.FLETCHING.getId(), true);
    }
}
