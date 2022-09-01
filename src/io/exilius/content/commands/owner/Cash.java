package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.Items;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.ImmutableItem;

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
