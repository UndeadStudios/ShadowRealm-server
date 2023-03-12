package io.shadowrealm.model.entity.player;

enum RankUpgrade {
    REGULAR_DONATOR(Right.REGULAR_DONATOR, 20),
    EXTREME_DONOR(Right.EXTREME_DONOR, 100),
    LEGENDARY_DONATOR(Right.LEGENDARY_DONATOR,250),
    DIAMOND_CLUB(Right.DIAMOND_CLUB, 500),
    ONYX_CLUB(Right.ONYX_CLUB, 1000),
    Mythic_Club(Right.Mythic_Club, 2500),
    Divine_Club(Right.Divine_Club, 5000);

    /**
     * The rights that will be appended if upgraded
     */
    public final Right rights;

    /**
     * The amount required for the upgrade
     */
    public final int amount;

    RankUpgrade(Right rights, int amount) {
        this.rights = rights;
        this.amount = amount;
    }
}
