package io.exilius.util.logging.player;

import java.util.Set;

import io.exilius.model.entity.player.Player;
import io.exilius.util.logging.PlayerLog;

public class RollDiceLog extends PlayerLog {

    private final int roll;

    public RollDiceLog(Player player, int roll) {
        super(player);
        this.roll = roll;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("dice_rolls");
    }

    @Override
    public String getLoggedMessage() {
        return "Rolled " + roll;
    }
}
