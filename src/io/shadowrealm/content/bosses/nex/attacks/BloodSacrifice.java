package io.shadowrealm.content.bosses.nex.attacks;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

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
