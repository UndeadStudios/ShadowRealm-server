package io.exilius.content.commands.all;

import java.util.Optional;

import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

/**
 * Changes the password of the player.
 *
 * @author Emiel
 *
 */
public class Changepassword extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        Changepass.sendChangePasswordDialogue(c);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Change your password");
    }

    @Override
    public Optional<String> getParameter() {
        return Optional.of("password");
    }

}
