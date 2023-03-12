package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.polls.PollTab;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.broadcasts.Broadcast;

/**
 * @author Grant_ | www.rune-server.ee/members/grant_ | 2/10/20
 */
public class Reloadpoll extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        PollTab.reloadPoll();
        new Broadcast("A new poll has opened!").submit();
        player.sendMessage("You have loaded in a new poll named, " + PollTab.getPoll().getQuestion());
        PollTab.updateInterface(player);
    }
}
