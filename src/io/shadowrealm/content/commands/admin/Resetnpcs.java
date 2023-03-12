package io.shadowrealm.content.commands.admin;

import io.shadowrealm.Configuration;
import io.shadowrealm.Server;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;

public class Resetnpcs extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        int[] SKIP_NPCS = {2627, 2630,  2743,  2745,  2746,  2738,  3500,  3491,  3493,  3494,  3495,  3496};
        for (int i = 0; i < Configuration.MAX_NPCS; i++) {
            boolean skip = false;
            NPC n = Server.npcHandler.npcs[i];
            if (n != null) {
                for (int k : SKIP_NPCS) {
                    if (n.getIndex() == k) {
                        skip = true;
                    }
                }
                if (!skip) {
                    n.isDead = true;
                }
            }
        }
    }
}
