package io.exilius.content.commands.admin;

import io.exilius.content.commands.Command;
import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.model.entity.player.Player;

public class Forceirontask extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.currentDailyTask = DailyTaskData.PICKPOCKET_MAN.getDailyTask();
		c.sendMessage("We've set your daily task to : " + c.currentDailyTask.getTaskName());
	}
}
