package io.shadowrealm.content.commands.owner;

import io.shadowrealm.Server;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.bank.BankItem;
import io.shadowrealm.util.Misc;

import java.util.Optional;

public class DropTest extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        String[] args = input.split("-");
        if (args.length < 1) {
            player.sendMessage("::droptest-npc_id-amount (default amount is 10k)");
        } else {
            int npcId = Integer.parseInt(args[0]);
            int count = 10_000;
            if (args.length == 2)
                count = Integer.parseInt(args[1]);

            if (Server.getDropManager().getDropSample(player, npcId).isEmpty()) {
                player.sendMessage("No drops for npc: " + npcId);
            } else {
                player.getItems().queueBankContainerUpdate();
                player.getBank().deleteAllItems();
                for (int k = 0; k < count; k++) {
                    Server.getDropManager().getDropSample(player, npcId).forEach(item -> {
                        player.getBank().getCurrentBankTab().add(new BankItem(item.getId() + 1, item.getAmount()));
                    });
                }

                player.getItems().updateBankContainer();
                player.getItems().openUpBank();
                player.sendMessage("Drop testing " + npcId + " x " + Misc.insertCommas(count) + ".");
            }
        }
    }

    public Optional<String> getDescription() {
        return Optional.of("Gets npc drop x times and puts in bank, clears your bank");
    }
}
