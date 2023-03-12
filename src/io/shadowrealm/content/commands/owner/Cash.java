package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.ImmutableItem;

/**
 * @author Arthur Behesnilian 1:26 PM
 */
public class Cash extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        player.getInventory().addToInventory(new ImmutableItem(Items.COINS, Integer.MAX_VALUE));
        player.sendMessage("You spawn a stack of cash.");
    }

}
