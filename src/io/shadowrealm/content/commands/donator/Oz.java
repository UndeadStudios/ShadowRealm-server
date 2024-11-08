package io.shadowrealm.content.commands.donator;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;

import java.util.Optional;

public class Oz extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        if (c.inTrade || c.inDuel || c.getPosition().inWild()) {
            return;
        }
        if (c.getPosition().inClanWars() || Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
            c.sendMessage("@cr10@This player is currently at the pk district.");
            return;
        }
        if (c.amDonated <= 999 && !c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
            c.sendMessage("@red@You need legendary donator to do this command");
            return;
        }
        c.getPA().startTeleport(Configuration.ONYX_ZONE_TELEPORT, "modern", false);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Teleports you to Onyx zone.");
    }

}


