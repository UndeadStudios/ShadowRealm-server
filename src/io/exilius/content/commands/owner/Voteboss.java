package io.exilius.content.commands.owner;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.sql.Votes;

public class Voteboss extends Command
{
    @Override
    public void execute(Player player, String commandName, String input) {
        Votes.voteCount = 100;
    }
}