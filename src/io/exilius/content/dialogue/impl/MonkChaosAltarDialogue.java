package io.exilius.content.dialogue.impl;

import io.exilius.content.dialogue.DialogueBuilder;
import io.exilius.content.dialogue.DialogueExpression;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Player;

public class MonkChaosAltarDialogue extends DialogueBuilder {

    public MonkChaosAltarDialogue(Player player, NPC npc) {
        super(player);
        setNpcId(npc.getNpcId());
        optimistic("I'll un-note your bones for 5k each!");
        exit(player1 -> player.getPA().closeAllWindows());
    }

    private void optimistic(String...text) {
        npc(DialogueExpression.SPEAKING_CALMLY, text);
    }

}
