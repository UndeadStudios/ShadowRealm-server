package io.shadowrealm.model.items;

import com.google.common.base.Preconditions;
import io.shadowrealm.util.Misc;

public class GameItemVariableAmount extends GameItem {

    private final int minimum;
    private final int maximum;

    public GameItemVariableAmount(int id, int minimum, int maximum) {
        super(id);
        this.minimum = minimum;
        this.maximum = maximum;
        Preconditions.checkState(maximum >= minimum);
    }

    @Override
    public int getAmount() {
        return minimum + (maximum - minimum > 0 ? Misc.random(0, maximum - minimum) : 0);
    }

}
