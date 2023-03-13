package io.shadowrealm.content.skills.slayer;

import io.shadowrealm.Server;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.event.eventcalendar.EventChallenge;
import io.shadowrealm.content.item.lootable.impl.LarransChest;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.collisionmap.WorldObject;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.util.Calendar;

public class LarrensKey {

    private static final int LARGE_CHEST_OBJECT = 34_832;
    private static final int SMALL_CHEST_OBJECT = 34_831;


    public static void roll(Player player, NPC npc) {
        int rewardAmount = 1;
        if (Hespori.activeKeldaSeed || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            rewardAmount = 2;
        }
        if (Misc.random(1,40) == 1) {
            player.getEventCalendar().progress(EventChallenge.OBTAIN_X_LARRENS_KEYS, 1);
            Server.itemHandler.createGroundItem(player, Items.LARRANS_KEY, npc.getX(), npc.getY(), npc.getHeight(), rewardAmount);
            //PlayerHandler.executeGlobalMessage("@cr21@ @pur@" + player.playerName + " received a drop: Larran's key from wildy slayer.");
            player.sendMessage("@red@A " + ItemDef.forId(Items.LARRANS_KEY).getName() + " has dropped.");
        }
    }

    public static boolean clickObject(Player player, WorldObject object) {
        if (object.getId() == LARGE_CHEST_OBJECT) {
            new LarransChest().roll(player);
            return true;
        } else if (object.getId() == SMALL_CHEST_OBJECT) {
            player.sendMessage("Larran's small chest wasn't added, if you feel it should be suggest it on ::discord!");
            return true;
        }

        return false;
    }
}