package io.exilius.content.commands.admin;

import io.exilius.content.commands.Command;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.model.entity.player.Player;

public class Newdailytask extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		DailyTaskHandler.Companion.assignNewTask(c);
		c.sendMessage("You have requested a new task!");
	}
}
