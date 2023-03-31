package io.shadowrealm.content.skills.farming;

import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.model.items.ItemAssistant;
import io.shadowrealm.model.items.ItemCacheDefinition;

import java.util.HashMap;
import java.util.Map;

public class Seedling {

    private Player player;

    public Seedling(Player player) {
        this.player = player;
    }

    public enum SeedlingData {
        OAK(5312, 5358, 5364, 5370), WILLOW(5313, 5359, 5365, 5371), MAPLE(
                5314, 5360, 5366, 5372), YEW(5315, 5361, 5367, 5373), MAGIC(
                5316, 5362, 5368, 5374), SPIRIT(5317, 5363, 5369, 5375), APPLE(
                5283, 5480, 5488, 5496), BANANA(5284, 5481, 5489, 5497), ORANGE(
                5285, 5482, 5490, 5498), CURRY(5286, 5483, 5491, 5499), PINEAPPLE(
                5287, 5484, 5492, 5500), PAPAYA(5288, 5485, 5493, 5501), PALM(
                5289, 5486, 5494, 5502), CALQUAT(5290, 5487, 5495, 5503);

        private int seedId;
        private int unwateredSeedlingId;
        private int wateredSeedlingId;
        private int saplingId;

        private static Map<Integer, SeedlingData> seeds = new HashMap<Integer, SeedlingData>();
        private static Map<Integer, SeedlingData> unwatered = new HashMap<Integer, SeedlingData>();
        private static Map<Integer, SeedlingData> watered = new HashMap<Integer, SeedlingData>();

        static {
            for (SeedlingData data : SeedlingData.values()) {
                seeds.put(data.seedId, data);
                unwatered.put(data.unwateredSeedlingId, data);
                watered.put(data.wateredSeedlingId, data);
            }
        }

        SeedlingData(int seedId, int unwateredSeedlingId,
                     int wateredSeedlingId, int saplingId) {
            this.seedId = seedId;
            this.unwateredSeedlingId = unwateredSeedlingId;
            this.wateredSeedlingId = wateredSeedlingId;
            this.saplingId = saplingId;
        }

        public static SeedlingData getSeed(int seedId) {
            return seeds.get(seedId);
        }

        public static SeedlingData getUnwatered(int seedId) {
            return unwatered.get(seedId);
        }

        public static SeedlingData getWatered(int seedId) {
            return watered.get(seedId);
        }

        public int getSeedId() {
            return seedId;
        }

        public int getUnwateredSeedlingId() {
            return unwateredSeedlingId;
        }

        public int getWateredSeedlingId() {
            return wateredSeedlingId;
        }

        public int getSaplingId() {
            return saplingId;
        }
    }

    public void makeSaplingInInv(int itemId) {
        SeedlingData seedlingData = SeedlingData.getWatered(itemId);
        if (seedlingData == null)
            return;
        player.getItems().deleteItem(itemId, 1);
        player.getItems().addItem(seedlingData.getSaplingId(), 1);
    }

    public void makeSaplingInBank(int itemId) {
        SeedlingData seedlingData = SeedlingData.getWatered(itemId);
        if (seedlingData == null)
            return;
        player.getItems().removeFromAnyTabWithoutAdding(itemId, 1, true);
        player.getItems().addToBank(seedlingData.getSaplingId(), 1, true);
    }

    public boolean waterSeedling(int itemUsed, int usedWith) {
        SeedlingData seedlingData = SeedlingData.getUnwatered(itemUsed);
        if (seedlingData == null)
            seedlingData = SeedlingData.getUnwatered(usedWith);
        if (seedlingData == null
                || (!ItemAssistant.getItemName(itemUsed).toLowerCase()
                .contains("watering") && !ItemAssistant.getItemName(usedWith)
                .toLowerCase()
                .contains("watering")))
            return false;

        player.sendMessage(
                "You water the "
                        + ItemAssistant.getItemName(seedlingData.getSeedId()).toLowerCase() + ".");
        player.getItems().deleteItem(seedlingData.getUnwateredSeedlingId(), 1);
        player.getItems().addItem(seedlingData.getWateredSeedlingId(), 1);
        return true;

    }

    public boolean placeSeedInPot(int itemUsed, int usedWith) {
        SeedlingData seedlingData = SeedlingData.getSeed(itemUsed);
        if (seedlingData == null)
            seedlingData = SeedlingData.getUnwatered(usedWith);
        if (seedlingData == null || (itemUsed != 5354 && usedWith != 5354))
            return false;
        player.getItems().deleteItem(seedlingData.getSeedId(), 1);
        player.getItems().replaceItem(player, 5354, (seedlingData.getUnwateredSeedlingId()));
        player.sendMessage(
                "You sow some " + ItemCacheDefinition.forID(seedlingData.seedId).getName() + " in the plant pot.");
        player.sendMessage("They need watering before they will grow.");
        return true;
    }

    public boolean fillPotWithSoil(int itemId, int objectX, int objectY) {
        if (itemId != 5350)
            return false;
        return false;
    }
}
