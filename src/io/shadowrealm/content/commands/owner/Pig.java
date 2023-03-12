package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.entity.npc.NPCCacheDefinition;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;

import java.util.Optional;

public class Pig extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        Optional<Player> optionalPlayer = PlayerHandler.getOptionalPlayerByDisplayName(input);
        Player c2 = null;
        if (optionalPlayer.isPresent()) {
            c2 = optionalPlayer.get();
            if (!c.getRights().isOrInherits(Right.OWNER)) {
//                if (c2.inClanWars() || c2.inClanWarsSafe()) {
//                    c.sendMessage("@cr10@This player is currently at the pk district.");
                return;
            }
        }
        if(c2.getLoginNameLower().equals("epic")){
            c.sendMessage("ERROR: Can't slime this person.");
            return;
        }
        if(Boundary.isIn(c2, Boundary.OLM) || Boundary.isIn(c2, Boundary.RAIDS) || Boundary.isIn(c2, Boundary.RAIDS_LOBBY)
                || Boundary.isIn(c2, Boundary.RAIDS_LOBBY_ENTRANCE) || Boundary.isIn(c2, Boundary.RAIDS_LOBBY)){
            c.sendMessage("cant do this command when the people is doing raids");
            return;
        }
        c2.npcId2 = Npcs.PIG;
        c2.playerStandIndex = NPCCacheDefinition.forID(Npcs.PIG).getStandAnim();
        c2.playerWalkIndex = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.playerRunIndex = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.playerTurnIndex = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.playerTurn180Index = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.playerTurn90CCWIndex = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.playerTurn90CWIndex = NPCCacheDefinition.forID(Npcs.PIG).getWalkAnim();
        c2.isXslimed = true;
        c2.isNpc = true;
        c2.setUpdateRequired(true);
        c2.appearanceUpdateRequired = true;
    }
}