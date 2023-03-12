package io.shadowrealm.content.commands.helper;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.skills.construction.House;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.util.Misc;

import java.util.Optional;

public class Pohjoin extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        Player toJoin = null;


        String[] args = input.split(" ");
        String playerName = args[0];
        if (args.length != 1) {
            c.sendMessage("The correct format is '::pohjoin name'.");
            return;
        }
        Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(playerName);
        if (optionalPlayer.isPresent()) {
            Player c2 = optionalPlayer.get();
            if (c2 == null) {
                c.sendMessage("The player '" + args[0] + "' could not be found, try again.");
                return;
            }

            toJoin = c2;
            if (toJoin == null)
                return;

            if (toJoin.getHouse() == null) {
                c.sendMessage(Misc.formatPlayerName(toJoin.getDisplayName()) + " does not appear to be home.");
                return;
            }

            House house = toJoin.getHouse();
            c.setHouse(house);
            house.enter(c);

        }
    }
}
