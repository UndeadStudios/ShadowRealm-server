package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.worldevent.WorldEventContainer;
import io.shadowrealm.content.worldevent.impl.HesporiWorldEvent;
import io.shadowrealm.model.entity.player.Player;

public class Starthespori extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        WorldEventContainer.getInstance().startEvent(new HesporiWorldEvent());
        player.sendMessage("Hespori will start soon.");
    }
}
