package io.shadowrealm.content.commands.owner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Pohdump extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        //c.sendMessage("This command is disabled.");

       System.out.println(gson.toJson(c.getHouse()));

    }
}
