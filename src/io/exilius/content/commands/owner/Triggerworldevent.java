package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.content.worldevent.WorldEventContainer;
import io.exilius.model.entity.player.Player;

public class Triggerworldevent extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        WorldEventContainer.getInstance().setTriggerImmediateEvent(true);
        player.sendMessage("Triggering next world event, please allow up to 30 seconds.");
    }
}
