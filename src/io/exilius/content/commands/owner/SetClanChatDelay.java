package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.model.world.Clan;

/**
 * @author Arthur Behesnilian 1:11 PM
 */
public class SetClanChatDelay extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        long newDelay = Integer.parseInt(input);
        Clan.CLAN_CHAT_DELAY = newDelay;
        player.sendMessage("You set the clan chat delay to " + newDelay + " ms.");
    }

}
