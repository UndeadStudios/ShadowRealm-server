package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * 
 * @author Grant_ | www.rune-server.ee/members/grant_ | 10/15/19
 *
 */
public class Broadcast extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        try {
            new io.shadowrealm.model.entity.player.broadcasts.Broadcast(input).submit();
        } catch (Exception e) {
            player.sendMessage("Error.. executing command.. invalid input! try again!");
        }
    }
}