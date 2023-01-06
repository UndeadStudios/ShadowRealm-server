package io.exilius.content.skills.crafting;

enum Gem {
    TOXIC_GEM(23778, 23783, 50, 5, 885),
    DIAMOND(1617, 1601, 43, 107, 886),
    RUBY(1619, 1603, 34, 85, 887),
    EMERALD(1621, 1605, 27, 68, 889),
    SAPPHIRE(1623, 1607, 20, 50, 888),
    OPAL(1625, 1609, 1, 15, 890),
    JADE(1627, 1611, 13, 20, 891),
    TOPAZ(1629, 1613, 16, 25, 892),
    DRAGONSTONE(1631, 1615, 55, 137, 885),
    ONYX(6571, 6573, 67, 168, 2717),
    ZENYTE(19496, 19493, 89, 200, 7185);

    private final int uncut, cut, level, experience, animation;

    Gem(int uncut, int cut, int level, int experience, int animation) {
        this.uncut = uncut;
        this.cut = cut;
        this.level = level;
        this.experience = experience;
        this.animation = animation;
    }

    public int getUncut() {
        return uncut;
    }

    public int getCut() {
        return cut;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAnimation() {
        return animation;
    }
}
