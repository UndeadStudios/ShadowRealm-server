package io.shadowrealm.model;

import io.shadowrealm.util.Misc;

public enum SpellBook {
    MODERN, ANCIENT, LUNAR
    ;

    @Override
    public String toString() {
        return Misc.formatPlayerName(name().toLowerCase());
    }
}
