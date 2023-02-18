package io.exilius.content.commands.owner;

import io.exilius.Server;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.npc.NPCSpawning;
import io.exilius.model.entity.player.Player;
import io.exilius.sql.Votes;
import io.exilius.util.discord.Discord;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class Voteboss extends Command
{
    @SneakyThrows
    @Override
    public void execute(Player player, String commandName, String input) {
        NPCSpawning.spawn(11958, 1885, 9308, 0, 1, 10, true);
        //Discord.writeannounceMessage("**__ [VOTE SYSTEM] Vote boss has now spawned at " + Server.getVoteCounter() + " Votes!");
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("[VOTE SYSTEM] Vote boss has now spawned!");
        eb.setDescription("Votes: " + Server.getVoteCounter() );
        eb.setImage("https://i.gyazo.com/dfee0f520aa4a48b99345a6c0f1df67e.mp4");
        eb.setColor(new java.awt.Color(0xB00D03));
       Discord.getJDA().getTextChannelById("1064970750408265878").sendMessageEmbeds(eb.build()).queue();

    }
}
