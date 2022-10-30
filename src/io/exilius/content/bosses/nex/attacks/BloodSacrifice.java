package io.exilius.content.bosses.nex.attacks;

import io.exilius.content.combat.Hitmark;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

public class BloodSacrifice {
    public BloodSacrifice(NPC npc, Player target) {
        BloodSacrifice(npc, target);
    }

    void BloodSacrifice(NPC npc, Player target) {
        CycleEventHandler.getSingleton().addEvent(new Object(), new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                if (!Boundary.isIn(target, Boundary.Nex)) {
                    container.stop();
                }
                if (container.getTotalTicks() == 1) {
                    target.gfx100(374);
                    target.sendMessage("You have been chosen as a sacrifice, RUN!");
                }
                if(container.getTotalTicks() == 7 && target.getPosition().getAbsDistance(npc.getPosition()) < 7) {
                    int newPrayerPoints = (int) (target.prayerPoint * 0.67);
                    target.prayerPoint = newPrayerPoints;
                    int damage = Misc.random(30, 50);
                    if (target.protectingMagic()) {
                        damage = (damage / 2);
                    }
                    npc.appendHeal(damage, Hitmark.HEAL_PURPLE);
                    target.appendDamage(damage, (damage > 0 ? Hitmark.HIT : Hitmark.MISS));
                }
                if (container.getTotalTicks() > 7) {
                    container.stop();
                }
            }
        }, 1);
    }
}