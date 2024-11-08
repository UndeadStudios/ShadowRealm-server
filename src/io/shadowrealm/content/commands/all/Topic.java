package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.Configuration;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Topic extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        try {
            int id = Integer.parseInt(input);
            player.getPA().openWebAddress(Configuration.FORUM_TOPIC_URL + id + "-1");
        } catch (Exception e) {
            player.sendMessage("Invalid format: ::topic 124");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Open a forum topic by the id.");
    }
}
