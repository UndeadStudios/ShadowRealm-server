package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.model.entity.player.Player;

public class Forceirontask extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		c.currentDailyTask = DailyTaskData.PICKPOCKET_MAN.getDailyTask();
		c.sendMessage("We've set your daily task to : " + c.currentDailyTask.getTaskName());
	}
}
