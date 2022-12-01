package io.exilius.content.commands.all;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;
import io.exilius.util.discord.Discord;

import java.util.Optional;

public class OpenTicket extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		if (input == null) {
			c.sendMessage("Please do ::openticket (explain your issue).");
			return;
		}
		Discord.writetickets("@here The following player needs assistance ` " +c.getDisplayName() + " ` : __**" + input + "**__ any available staff please respond");
		c.sendMessage("Your ticket has been opened. Staff Should respond shortly");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Open a Ticket");
	}
}
