package io.exilius.content.skills.fletching;

import io.exilius.content.skills.Skill;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Player;

public class MakeArrowEvent extends Event<Player> {

    private FletchableArrow a = null;

    public MakeArrowEvent(Player att, FletchableArrow a) {
        super("skilling", att, 2);
        this.a = a;
    }
    @Override
    public void execute() {
        if (attachment == null || attachment.isDisconnected() || attachment.getSession() == null) {
            stop();
            return;
        }
        if (a == null) {
            stop();
            return;
        }
        if (!attachment.getItems().playerHasItem(53, 15)) {
            attachment.sendMessage("You need at least 15 headless arrows to do this.");
            attachment.getPA().removeAllWindows();
            stop();
            return;
        }
        if (!attachment.getItems().playerHasItem(a.getId(), 15)) {
            attachment.sendMessage("You need at least 15 arrowheads to do this.");
            attachment.getPA().removeAllWindows();
            stop();
            return;
        }
        if (attachment.getItems().freeSlots() < 1) {
            attachment.sendMessage("You need at least 1 free slot to do this.");
            attachment.getPA().removeAllWindows();
            stop();
            return;
        }
        attachment.getItems().deleteItem2(53, 15);
        attachment.getItems().deleteItem2(a.getId(), 15);
        attachment.getItems().addItem(a.getReward(), 15);
        attachment.getPA().addSkillXPMultiplied((int) a.getExperience() * 10, Skill.FLETCHING.getId(), true);
    }
}
