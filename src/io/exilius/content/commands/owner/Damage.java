package io.exilius.content.commands.owner;

import io.exilius.content.combat.Hitmark;
import io.exilius.content.commands.Command;
import io.exilius.model.entity.player.Player;

public class Damage extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");
        int amount = Integer.parseInt(args[0]);
        c.appendDamage(c, amount, Hitmark.HIT);
    }
}
