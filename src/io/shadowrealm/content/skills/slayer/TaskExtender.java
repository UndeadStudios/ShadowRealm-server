package io.shadowrealm.content.skills.slayer;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

public class TaskExtender {

    private static int cost = 15_000_000;
    private static int coins = 995;

    public static void Extend(Player player) {
        if (player.getSlayer().getTask().isEmpty() || !player.getSlayer().getTask().isPresent()) {
            player.sendMessage("You need a task to be able to do this.");
            player.getPA().closeAllWindows();
            return;
        }

        if (player.TaskExtended) {
            player.sendMessage("You must complete a task before being able to extend again!");
            player.getPA().closeAllWindows();
            return;
        }

        if (player.getItems().getInventoryCount(coins) < cost) {
            player.sendMessage("You do not have enough coins to do this.");
            player.getPA().closeAllWindows();
            return;
        }

        int rng = Misc.random(50,100);
        player.getSlayer().setAmountToSlay(player.getSlayer().getTaskAmount() + rng);
        if (player.amDonated < 100) {
            player.getItems().deleteItem(995, cost);
        }
        player.sendMessage("You have extended your task of " + player.getSlayer().getTask().get().getPrimaryName() + "'s, you now have to kill " + player.getSlayer().getTaskAmount() + "!");
        player.TaskExtended = true;
    }
}
