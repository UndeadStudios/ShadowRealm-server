package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.Npcs;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Right;

import java.util.Optional;

public class xslime extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
        Player c2 = null;
        if (optionalPlayer.isPresent()) {
            c2 = optionalPlayer.get();
            if (!c.getRights().isOrInherits(Right.ADMINISTRATOR)) {
//                if (c2.inClanWars() || c2.inClanWarsSafe()) {
//                    c.sendMessage("@cr10@This player is currently at the pk district.");
                return;
            }
        }
        c2.npcId2 = Npcs.PIGLET;
        c2.playerStandIndex = NPCCacheDefinition.forID(Npcs.PIGLET).getStandIndex();
        c2.playerWalkIndex = NPCCacheDefinition.forID(Npcs.PIGLET).getWalkIndex();
        c2.playerRunIndex = NPCCacheDefinition.forID(Npcs.PIGLET).getWalkIndex();
        c2.isNpc = true;
        c2.setUpdateRequired(true);
        c2.appearanceUpdateRequired = true;
    }
}