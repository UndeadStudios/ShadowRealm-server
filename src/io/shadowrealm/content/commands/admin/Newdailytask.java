package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.model.entity.player.Player;

public class Newdailytask extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		DailyTaskHandler.Companion.assignNewTask(c);
		c.sendMessage("You have requested a new task!");
	}
}
