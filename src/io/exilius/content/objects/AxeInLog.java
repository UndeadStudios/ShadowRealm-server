package io.exilius.content.objects;

import io.exilius.Server;
import io.exilius.model.entity.player.Player;
import io.exilius.model.world.objects.GlobalObject;

public class AxeInLog {

    public static void pullAxeFromLog(Player client, int x, int y) {
        if (client.getItems().freeSlots() <= 0) {
            client.sendMessage(
                    "Not enough space in your inventory.");
            return;
        }
        client.startAnimation(832);
        client.getItems().addItem(1351, 1);
        client.sendMessage(
                "You take the axe from the log.");
        Server.getGlobalObjects().add(new GlobalObject(5582, x, y, client.heightLevel, 2, 10, 100, 5581));
    }
}
