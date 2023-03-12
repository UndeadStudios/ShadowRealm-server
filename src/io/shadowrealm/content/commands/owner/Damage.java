package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.combat.Hitmark;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

public class Damage extends Command {
    @Override
    public void execute(Player c, String commandName, String input) {
        String[] args = input.split(" ");
        int amount = Integer.parseInt(args[0]);
        c.appendDamage(c, amount, Hitmark.HIT);
    }
}
