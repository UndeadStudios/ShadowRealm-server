package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * @author Arthur Behesnilian 1:05 PM
 */
public class SetAccuracyBonus extends Command {

    public static int MELEE_ATTACK = 74;
    public static int RANGE_ATTACK = 54;
    public static int MAGE_ATTACK = 75;
    public static int SCYTHE_ATTACK_BONUS = 130;
    public static int SCYTHEL_ATTACK_BONUS = 150;

    @Override
    public void execute(Player player, String commandName, String input) {
        String[] parts = input.split(" ");
        String type = parts[0];
        int attack = Integer.parseInt(parts[1]);

        switch (type) {
            case "melee":
                MELEE_ATTACK = attack;
                break;
            case "range":
                RANGE_ATTACK = attack;
                break;
            case "mage":
                MAGE_ATTACK = attack;
                break;
            case "scythe":
                SCYTHE_ATTACK_BONUS = attack;
            default:
                player.sendMessage("No type: " + type + ", [melee, range, mage, scythe].");
                return;
        }

        player.sendMessage("You set the " + type + " attack level bonus to " + attack);
    }

}