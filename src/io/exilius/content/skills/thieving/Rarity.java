package io.exilius.content.skills.thieving;

public enum Rarity {
    ALWAYS(0), COMMON(5), UNCOMMON(10), RARE(15), VERY_RARE(25), SUPER_RARE(1500);

    /**
     * The rarity
     */
    final int rarity;

    /**
     * Creates a new rarity
     *
     * @param rarity the rarity
     */
    Rarity(int rarity) {
        this.rarity = rarity;
    }
}
