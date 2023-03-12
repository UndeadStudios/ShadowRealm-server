package io.shadowrealm.content.item.lootable.impl;

import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.content.item.lootable.MysteryBoxLootable;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperMysteryBox extends MysteryBoxLootable {

    /**
     * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
     */
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    /*
      Stores an array of items into each map with the corresponding rarity to the list
     */

    static {
        items.put(LootRarity.COMMON,//50% chance
                Arrays.asList(
                        new GameItem(11840,1),//dragon boots
                        new GameItem(11836,1),//bandos boots
                        new GameItem(6585,1),//amulet of fury
                        new GameItem(6737,1),//berserker ring
                        new GameItem(6889,1),//mages book
                        new GameItem(12873,1),//guthan set
                        new GameItem(12875,1),//verac set
                        new GameItem(12877,1),//dharok set
                        new GameItem(12879,1),//torags set
                        new GameItem(12881,1),//ahrim set
                        new GameItem(12883,1),//karil set
                        new GameItem(2577,1),//ranger boots
                        new GameItem(6570,1),//,fire cape
                        new GameItem(12596,1),//rangers tunic
                        new GameItem(11920,1),//dragon pickaxe
                        new GameItem(6739,1),//dragon axe
                        new GameItem(6733,1),//archers ring
                        new GameItem(6731,1),//seers ring
                        new GameItem(6735,1),//warrior ring
                        new GameItem(6769,1)//$5 scroll

                ));

        items.put(LootRarity.UNCOMMON,//40% Chance
                Arrays.asList(
                        new GameItem(2403, 1),//10 dollar scroll
                        new GameItem(2572,1),//ring of wealth
                        new GameItem(12924,1),//Toxic blowpipe (empty)
                        new GameItem(11806,1),//Dragon crossbow
                        new GameItem(11907,1),//trident of the sea
                        new GameItem(11806,1),//Dragon crossbow
                        new GameItem(11907,1),//trident of the sea
                        new GameItem(11838,1),//saradomin sword
                        new GameItem(2572,1),//ring of wealth
                        new GameItem(11838,1),//saradomin sword
                        new GameItem(11804,1),//BGS
                        new GameItem(11806,1),//SGS
                        new GameItem(11808,1),//ZGS
                        new GameItem(12924,1),//Toxic blowpipe (empty)
                        new GameItem(11806,1),//Dragon crossbow
                        new GameItem(11907,1),//trident of the sea
                        new GameItem(2403, 1)//10 dollar scroll

                )
        );

        items.put(LootRarity.RARE,//10% chance
                Arrays.asList(
                        new GameItem(11802,1),//AGS
                        new GameItem(11826,1),//armadyl helm
                        new GameItem(11828,1),//armadyl body
                        new GameItem(11830,1),//armadyls legs
                        new GameItem(11832,1),//bandos chestplate
                        new GameItem(11834,1),//bandos tassets
                        new GameItem(13346,1),//ultra mystery box
                        new GameItem(11785,1),//armadyl crossbow
                        new GameItem(2403, 1),//10 dollar scroll
                        new GameItem(13239, 1),//primordial boots
                        new GameItem(13235, 1),//eternal boots
                        new GameItem(13237, 1),//pegasian boots
                        new GameItem(2403, 1),//10 dollar scroll
                        new GameItem(12785,1)));//ring of wealth (i)
    }

    /**
     * Constructs a new myster box to handle item receiving for this player and this player alone
     *
     * @param player the player
     */
    public SuperMysteryBox(Player player) {
        super(player);
    }

    @Override
    public int getItemId() {
        return 6828;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
}
