package io.exilius.content.commands.admin;

import io.exilius.content.commands.Command;
import io.exilius.content.worldevent.WorldEventContainer;
import io.exilius.content.worldevent.impl.HesporiWorldEvent;
import io.exilius.model.entity.player.Player;

public class Starthespori extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        WorldEventContainer.getInstance().startEvent(new HesporiWorldEvent());
        player.sendMessage("Hespori will start soon.");
    }
}
