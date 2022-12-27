package io.exilius.content.skills.thieving;

import com.google.common.collect.Lists;
import io.exilius.model.Items;
import io.exilius.model.items.GameItem;
import io.exilius.util.Misc;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StallData {
    VEG(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 750)));
        }
    }, 2, 10, 30, 45000, 634),
    Baker(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(Items.CAKE, 1), new GameItem(Items.BREAD, 1), new GameItem(1901, 1)));
        }
    }, 5, 16, 30, 45000, 634),
    Baker2(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(Items.CAKE, 1), new GameItem(Items.BREAD, 1), new GameItem(1901, 1)));
        }
    }, 5, 16, 30, 45000, 6984),
    food(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(Items.BANANA, 1)));
        }
    }, 5, 16, 30, 45000, 4797),
    Spice(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(712, 1)));
        }
    }, 65, 67, 30, 43000, 634),
    Fruit(new HashMap<Rarity, List<GameItem>>() {
        {
        put(Rarity.COMMON, Arrays.asList(new GameItem(Items.COOKING_APPLE), new GameItem(Items.BANANA, 1), new GameItem(Items.STRAWBERRY, 1), new GameItem(Items.JANGERBERRIES, 1), new GameItem(Items.LEMON, 1), new GameItem(Items.REDBERRIES, 1), new GameItem(Items.PINEAPPLE, 1), new GameItem(Items.LIME, 1), new GameItem(Items.STRANGE_FRUIT, 1)));
        put(Rarity.UNCOMMON, Arrays.asList(new GameItem(Items.GOLOVANOVA_FRUIT_TOP)));
        put(Rarity.RARE, Arrays.asList(new GameItem(Items.PAPAYA_FRUIT)));
        }
    }, 25, 28, 30, 43000, 27537),
    crafting2(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(Items.CHISEL, 1), new GameItem(Items.NECKLACE_MOULD, 1), new GameItem(Items.RING_MOULD, 1)));
        }
    }, 5, 16, 30, 45000, 6984),
    Silver(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(Items.SILVER_ORE, 1)));
        }
    }, 50, 54, 30, 40000, 634),
    Silver2(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(Items.SILVER_ORE, 1)));
        }
    }, 50, 54, 30, 40000, 6984),
    Gem(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(1623, 1), new GameItem(1621, 1)));
            put(Rarity.UNCOMMON, Arrays.asList(new GameItem(1619, 1)));
            put(Rarity.RARE, Arrays.asList(new GameItem(1617, 1)));
        }
    }, 75, 80, 30, 38000, 4797),
    Gem2(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(1623, 1), new GameItem(1621, 1)));
            put(Rarity.UNCOMMON, Arrays.asList(new GameItem(1619, 1)));
            put(Rarity.RARE, Arrays.asList(new GameItem(1617, 1)));
            put(Rarity.SUPER_RARE, Arrays.asList(new GameItem(Items.UNCUT_ONYX, 1)));
        }
    }, 75, 80, 30, 38000, 634),
    Gem3(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(1623, 1), new GameItem(1621, 1)));
            put(Rarity.UNCOMMON, Arrays.asList(new GameItem(1619, 1)));
            put(Rarity.RARE, Arrays.asList(new GameItem(1617, 1)));
            put(Rarity.SUPER_RARE, Arrays.asList(new GameItem(Items.UNCUT_ONYX, 1)));
        }
    }, 75, 80, 30, 38000, 6984),
    Crossbow(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.COMMON, Arrays.asList(new GameItem(Items.BRONZE_BOLTS, 3), new GameItem(Items.BRONZE_LIMBS, 1), new GameItem(Items.WOODEN_STOCK, 1)));
        }
    }, 49, 52, 30, 45000, 6984),
    Scimitar(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(1325, 1)));
        }
    }, 65, 160, 30, 36500, 4797),
    General(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(Items.TINDERBOX, 1)));
        }
    }, 5, 16, 30, 46000, 4797),
    Silk(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(950, 1)));
        }
    }, 25, 35, 30, 40000, 634),
    FUR(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(958, 1)));
        }
    }, 35, 36, 30, 40000, 634),
    FISH(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(331, 1)));
        }
    }, 42, 42, 30, 40000, 634),
    Gold(new HashMap<Rarity, List<GameItem>>() {
        {
            put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 20000+ Misc.random(1000, 200000))));
        }
    }, 90, 50, 1, 40000, 29165);
    /**
     * The item received from the stall
     */
    private Map<Rarity, List<GameItem>> items = new HashMap<>();

    /**
     * The experience gained in thieving from a single stall thieve
     */
    final double experience;

    /**
     * The probability that the stall will deplete
     */
    final int depletionProbability;

    /**
     * The level required to steal from the stall
     */
    final int level;

    /**
     * The chance of receiving a pet
     */
    final int petChance;

    /**
     * the depletion object id
     */
    final int depletionobject;

    /**
     * Constructs a new {@link StallData} object with a single parameter, {@link GameItem} which is the item received when interacted with.
     *
     * @param items the item received upon interaction
     */
    StallData(Map<Rarity, List<GameItem>> items, int level, int experience, int depletionProbability, int petChance, int depletionobject) {
        this.items = items;
        this.level = level;
        this.experience = experience;
        this.depletionProbability = depletionProbability;
        this.petChance = petChance;
        this.depletionobject = depletionobject;
    }
    GameItem getRandomItem() {
        for (Map.Entry<Rarity, List<GameItem>> entry : items.entrySet()) {
            final Rarity rarity = entry.getKey();

            if (rarity == Rarity.ALWAYS) {
                continue;
            }
            final List<GameItem> items = entry.getValue();

            if (items.isEmpty()) {
                continue;
            }

            if (RandomUtils.nextInt(1, rarity.rarity) == 1) {
                return Misc.getItemFromList(items).randomizedAmount();
            }
        }

        List<GameItem> always = items.getOrDefault(Rarity.ALWAYS, Lists.newArrayList());

        if (!always.isEmpty()) {
            return Misc.getItemFromList(always).randomizedAmount();
        }
        return null;
    }
}
