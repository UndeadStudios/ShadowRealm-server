package io.shadowrealm.content.item.lootable.impl;

import io.shadowrealm.Server;
import io.shadowrealm.content.event.eventcalendar.EventChallenge;
import io.shadowrealm.content.item.lootable.LootRarity;
import io.shadowrealm.content.item.lootable.Lootable;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.util.Misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnbearableChest implements Lootable {

    private static final int KEY = 4185;
    private static final int ANIMATION = 881;
    private static final Map<LootRarity, List<GameItem>> items = new HashMap<>();

    static {
        items.put(LootRarity.COMMON, Arrays.asList(
                new GameItem(21046, 4),//15% chest rate tomb

                new GameItem(20849, 32),//dragon thrownaxe
                new GameItem(22804, 35),//dragon knife
                new GameItem(995, 240000), //coins
                new GameItem(2996, 35),//pkp tickets
                new GameItem(11230, 32),//dragon darts
                new GameItem(537, 5 + Misc.random(30)),//dragon bones
                new GameItem(1306, 3),//dragon longsword
                new GameItem(1080, 6),//rune platelegs
                new GameItem(1128, 6),//rune platebody
                new GameItem(4087, 1),//dragon platelegs
                new GameItem(4585, 1),//dragon plateskirt
                new GameItem(4151, 1),//whip
                new GameItem(23804, 1),//imbuedifier
                new GameItem(11232, 50 + Misc.random(10)),//dragon darts
                new GameItem(11840),//dragon boots
                new GameItem(6889),//mages book
                new GameItem(2364, 100),//runite bar
                new GameItem(1514, 100),// magic logs
                new GameItem(1632, 50),//uncut dragonstone
                new GameItem(20849, 32),//dragon thrownaxe
                new GameItem(22804, 35),//dragon knife
                new GameItem(995, 240000), //coins
                new GameItem(2996, 35),//pkp tickets
                new GameItem(11230, 32),//dragon darts
                new GameItem(537, 5 + Misc.random(30)),//dragon bones
                new GameItem(1306, 3),//dragon longsword
                new GameItem(1080, 6),//rune platelegs
                new GameItem(1128, 6),//rune platebody
                new GameItem(4087, 1),//dragon platelegs
                new GameItem(4585, 1),//dragon plateskirt
                new GameItem(4151, 1),//whip
                new GameItem(23804, 1),//imbuedifier
                new GameItem(11232, 50 + Misc.random(10)),//dragon darts
                new GameItem(11840),//dragon boots
                new GameItem(6889),//mages book
                new GameItem(2364, 100),//runite bar
                new GameItem(1514, 100),// magic logs
                new GameItem(1632, 50),//uncut dragonstone

                new GameItem(21547, 1),//small foe bone
                new GameItem(21547, 1),//small foe bone
                new GameItem(21547, 1),//small foe bone
                new GameItem(21547, 1),//small foe bone
                new GameItem(21549, 1),//medium foe bone
                new GameItem(21549, 1),//medium foe bone
                new GameItem(21549, 1),//medium foe bone
                new GameItem(21551, 1),//large foe bone
                new GameItem(21551, 1),//large foe bone
                new GameItem(21553, 1),//rare foe bone

                new GameItem(24291),//dagon hai top
                new GameItem(24294),//dagon hai bottom
                new GameItem(24288),//dagon hai hat
                new GameItem(19707)//eternal glory

        ));
        items.put(LootRarity.RARE, Arrays.asList(
                new GameItem(20784, 1), //dragon claws
                new GameItem(21079, 1), //arcane scroll
                new GameItem(22616, 1), //vesta chainbody
                new GameItem(22619, 1), //vesta chainskirt
                new GameItem(22650, 1), //zuriel hood
                new GameItem(22653, 1), //zuriel robe top
                new GameItem(22656, 1), //zuriel robe bottoms
                new GameItem(22625, 1), //statius fullhelm
                new GameItem(28347, 1), //Death Cape
                new GameItem(22628, 1), //statius fullbody
                new GameItem(22631, 1), //statius legs
                new GameItem(22638, 1), //morrigans coif
                new GameItem(22641, 1), //morrigans body
                new GameItem(22644, 1)));//morrigans legs
    }

    private static GameItem randomChestRewardsYoutube(Player c, int chance) {
        int random = Misc.random(chance);
        int rareChance = 980;
        if (c.getItems().playerHasItem(21046)) {
            rareChance = 950;
            c.getItems().deleteItem(21046, 1);
            c.sendMessage("@red@You sacrifice your @cya@tablet @red@for an increased drop rate." );
            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
        }
//        if (c.getItems().playerHasItem(28347)) {
//            rareChance = 930;
//            c.sendMessage("@red@You're Death Cape has given you a increased drop rate." );
//            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
//        }
        if (c.getItems().playerHasItem(19480)) {
            rareChance = 940;
            c.sendMessage("@blu@You're Godly Cape has given you a increased drop rate." );
            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
        }
        List<GameItem> itemList = random <= rareChance ? items.get(LootRarity.COMMON) : items.get(LootRarity.RARE);
        return Misc.getRandomItem(itemList);
    }

    private static GameItem randomChestRewards(Player c, int chance) {
        int random = Misc.random(chance);
        int rareChance = 970;
        if (c.getItems().playerHasItem(21046)) {
            rareChance = 950;
            c.getItems().deleteItem(21046, 1);
            c.sendMessage("@red@You sacrifice your @cya@tablet @red@for an increased drop rate." );
            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
        }
//        if (c.getItems().isWearingItem(28347)) {
//            rareChance = 945;
//            c.sendMessage("@red@You're Death Cape has given you a increased drop rate." );
//            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
//        }
        if (c.getItems().isWearingItem(19480)) {
            rareChance = 940;
            c.sendMessage("@blu@You're Godly Cape has given you a increased drop rate." );
            c.getEventCalendar().progress(EventChallenge.USE_X_CHEST_RATE_INCREASE_TABLETS, 1);
        }
        List<GameItem> itemList;
        if (random <= rareChance) {
            itemList = items.get(LootRarity.COMMON);
            GameItem chooseRandomItem = Misc.getRandomItem(itemList);
            return chooseRandomItem;
        } else {
            itemList = items.get(LootRarity.RARE);
            GameItem chooseRandomItem = Misc.getRandomItem(itemList);
            ItemDef def = ItemDef.forId(chooseRandomItem.getId());
            if (!c.getDisplayName().equalsIgnoreCase("epic") && !c.getDisplayName().equalsIgnoreCase("banned")) {
                PlayerHandler.executeGlobalMessage("@bla@[<col=7f0000>UNBEARABLE@bla@] <col=990000>" + c.getDisplayName() + "@bla@ has just received a <col=990000>" + def.getName() + ".");
            }
            return chooseRandomItem;
        }

    }

    @Override
    public Map<LootRarity, List<GameItem>> getLoot() {
        return items;
    }

    @Override
    public void roll(Player c) {
        int pkpbonus = Misc.random(19) + 10;
        if (c.getItems().playerHasItem(KEY)) {
            c.getItems().deleteItem(KEY, 1);
            c.startAnimation(ANIMATION);
            c.pkp += pkpbonus;
            if (c.getRights().isOrInherits(Right.YOUTUBER)) {
                GameItem reward = randomChestRewards(c, 1000);
                if (!c.getItems().addItem(reward.getId(), reward.getAmount())) {
                    Server.itemHandler.createGroundItem(c, reward.getId(), c.getX(), c.getY(), c.heightLevel, reward.getAmount());
                }
                c.sendMessage("@blu@You also receive @red@" + pkpbonus + " @blu@pkp as a bonus for killing a wildy boss.");
            }
            if (!(c.getRights().isOrInherits(Right.YOUTUBER))) {
                GameItem reward = randomChestRewards(c,1000);
                if (!c.getItems().addItem(reward.getId(), reward.getAmount())) {
                    Server.itemHandler.createGroundItem(c, reward.getId(), c.getX(), c.getY(), c.heightLevel, reward.getAmount());
                }
                c.sendMessage("@blu@You also receive @red@" + pkpbonus + " @blu@pkp as a bonus for killing a wildy boss.");
            }
        } else {
            c.sendMessage("@blu@The chest is locked, it won't budge!");
        }
    }
}
