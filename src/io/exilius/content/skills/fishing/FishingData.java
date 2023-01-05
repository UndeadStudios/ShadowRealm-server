package io.exilius.content.skills.fishing;

import java.util.HashMap;

/**
 * Fishing Data
 * @author Micheal/01053 : https://www.rune-server.org/members/01053/
 */

public class FishingData {

    public enum data {
        Shrimp(1, 1, new int[]{317, 321}, 303, -1, 621, 10),
        Sardine(2, 5, new int[]{327, 345}, 307, 313, 622, 20),
        Trout(3, 20, new int[]{335, 331}, 309, 314, 622, 50),
        Pike(4, 25, new int[]{349}, 307, 313, 622, 60),
        Tuna(5, 35, new int[]{359, 371}, 311, -1, 618, 80),
        Lobster(6, 40, new int[]{377}, 301, -1, 619, 90),
        Monkfish(7, 62, new int[]{7944}, 303, -1, 620, 120),
        Karambwan(8, 65, new int[]{3142}, 3159, -1, 620, 50),
        Shark(9, 76, new int[]{383}, 311, -1, 618, 110),
        Turtle(10, 79, new int[]{395, 389}, 305, -1, 620, 57),
        MANTA_RAY(11, 81, new int[]{389}, 305, -1, 620, 69),
        Angler(12, 82, new int[]{13439}, 307, 13431, 622, 120),
        DARK_CRAB(13, 85, new int[]{11934}, 301, 11940, 619, 130),
        SACRED_EEL(14, 87, new int[]{13339}, 307, 313, 622, 105);

        public int requirement, itemRequired, baitRequired, animation, experience, fishingIdentifier;

        public int[] fish;

        public static HashMap<Integer, data> fishingSpots = new HashMap<>();

        static {
            for (data fishing : data.values()) {
                fishingSpots.put(fishing.fishingIdentifier, fishing);
            }
        }

        private data(int fishingIdentifier, int requirement, int[] fish, int itemRequired, int baitRequired, int animation, int experience) {
            this.fishingIdentifier = fishingIdentifier;
            this.requirement = requirement;
            this.fish = fish;
            this.itemRequired = itemRequired;
            this.baitRequired = baitRequired;
            this.animation = animation;
            this.experience = experience;
        }

        public int getIdentifier() {
            return fishingIdentifier;
        }

        public int getRequirement() {
            return requirement;
        }

        public int[] getFish() {
            return fish;
        }

        public int getItemReq() {
            return itemRequired;
        }

        public int getBait() {
            return baitRequired;
        }

        public int getAnimation() {
            return animation;
        }

        public int getExperience() {
            return experience;
        }

        public static data forId(int id) {
            return fishingSpots.get(id);
        }
    }
}