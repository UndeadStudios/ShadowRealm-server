package io.exilius.content.commands.owner;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.player.Player;

public class Addnpc extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        int newNPC = Integer.parseInt(input);
        if (newNPC > 0) {
            Server.npcHandler.addNPC(newNPC, c.getX(), c.getY(), c.getHeight());
            c.sendMessage("You have added the "+ NPCCacheDefinition.forID(newNPC).getName()+" to X: "+c.getX()+" Y:"+c.getY()+".");
        } else {
            c.sendMessage("No such NPC.");
        }
    }
}
