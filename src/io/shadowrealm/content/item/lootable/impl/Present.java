package io.shadowrealm.content.item.lootable.impl;

import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.content.item.lootable.MysteryBoxLootable;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Revamped a simple means of receiving a random item based on chance.
 *
 * @author Jason MacKeigan
 * @date Oct 29, 2014, 1:43:44 PM
 *
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
public class Present extends MysteryBoxLootable {

    /**
     * A map containing a List of {@link GameItem}'s that contain items relevant to their LootRarity.
     */
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    /**
     * Stores an array of items into each map with the corresponding rarity to the list
     */
    static {

        items.put(LootRarity.COMMON, //50% chance
                Arrays.asList(
                        new GameItem(12887), // Santa mask
                        new GameItem(12888), // Santa jacket
                        new GameItem(12889), // Santa pantaloons
                        new GameItem(12890), // Santa gloves
                        new GameItem(12891), // Santa boots
                        new GameItem(12892), // Antisanta mask
                        new GameItem(12893), // Antisanta jacket
                        new GameItem(12894), // Antisanta pantaloons
                        new GameItem(12895), // Antisanta gloves
                        new GameItem(12896) // Antisanta boots
                ));

        items.put(LootRarity.UNCOMMON,//40% Chance
                Arrays.asList(

                        new GameItem(13343), // Black santa hat
                        new GameItem(13344), // Inverted santa hat
                        new GameItem(21847), // Snow imp costume head
                        new GameItem(21849), // Snow imp costume body
                        new GameItem(21851), // Snow imp costume legs
                        new GameItem(21853), // Snow imp costume tail
                        new GameItem(21855), // Snow imp costume gloves
                        new GameItem(21857), // Snow imp costume feet
                        new GameItem(21859), // Wise old man's santa hat
                        new GameItem(26310), // Festive elf slippers
                        new GameItem(26312), // Festive elf hat
                        new GameItem(26314) // Snowman ring
                )
        );

        items.put(LootRarity.RARE,//8% chance
                Arrays.asList(
                        new GameItem(22713), // Star-face
                        new GameItem(22715), // Tree top
                        new GameItem(22717), // Tree skirt
                        new GameItem(22719), // Candy cane
                        new GameItem(24428), // Green gingerbread shield
                        new GameItem(24430), // Red gingerbread shield
                        new GameItem(24431), // Blue gingerbread shield
                        new GameItem(25314), // Giant boulder
                        new GameItem(20834), // Sack of presents
                        new GameItem(20836), // Giant present
                        new GameItem(27566), // Christmas jumper
                        new GameItem(27568), // Snow goggles & hat
                        new GameItem(27570), // Sack of coal
                        new GameItem(27572), // Festive nutcracker top
                        new GameItem(27574), // Festive nutcracker trousers
                        new GameItem(27576), // Festive nutcracker hat
                        new GameItem(27578), // Festive nutcracker boots
                        new GameItem(27580), // Festive nutcracker staff
                        new GameItem(27582), // Sweet nutcracker top
                        new GameItem(27583), // Sweet nutcracker trousers
                        new GameItem(27584), // Sweet nutcracker hat
                        new GameItem(27585), // Sweet nutcracker boots
                        new GameItem(27586), // Sweet nutcracker staff
                        new GameItem(27588) // Festive games crown
                )
        );


    }

    /**
     * Constructs a new myster box to handle item receiving for this player and this player alone
     *
     * @param player the player
     */
    public Present(Player player) {
        super(player);
    }

    @Override
    public int getItemId() {
        return 6542;
    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }
}
