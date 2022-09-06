package io.exilius.content.upgrading;

import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.model.items.ItemCacheDefinition;
import io.exilius.util.Misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemUpgrading {

    private final Player player;

    public ItemUpgrading(Player player) {
        this.player = player;
    }

    public int categorySelected;
    public int upgradeItem = -1;
    public int selectedItem = -1;

    public int getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(int category) {
        this.categorySelected = category;
    }

    public boolean handleButtons(int buttonId) {

        int category = buttonId - 101063;

        System.out.println(category);

        if (buttonId >= 101063 && buttonId <= 101066) {
            switchCategory(category);
            return true;
        }

        switch (buttonId) {
            case 101045: {
                player.getPA().closeAllWindows();
                return true;
            }

            case 101157: {
                upgradeItem();
                return true;

            }
        }

        return false;
    }

    private void clearCategoryData() {
        for (int i = 0; i < 28; i++) {
            player.getPA().itemOnInterface2(25928, -1, i, -1);
        }
    }

    private void resetInterface() {
        player.getPA().itemOnInterface2(26007, -1, 0, -1);
        player.getPA().sendString("", 26005);
        player.getPA().sendString("0", 26011);
        player.getPA().sendString("0", 26012);
        selectedItem = -1;
        upgradeItem = -1;
    }

    public void openInterface(int category) {
        clearCategoryData();
        int nextinterfaceid = 0;
        for (int i = 0; i < ItemUpgradeData.getCategorySize(category); i++) {
            //if (nextinterfaceid <= 24535) {
            player.getPA().itemOnInterface2(25928, ItemUpgradeData.forCategoryId(category).get(i).getStartingId(), i, 1);
        	/*} else if (nextinterfaceid > 24535) {
        		player.getPA().itemOnInterface2(24530, ItemUpgradeData.forCategoryId(category).get(i).getStartingId(), i, 1);
        	}*/
        }

        resetInterface();
        player.getPA().showInterface(25898);
    }

    private void switchCategory(int category) {
        clearCategoryData();
        resetInterface();

        setCategorySelected(category);

        for (int i = 0; i < ItemUpgradeData.getCategorySize(category); i++) {
            player.getPA().itemOnInterface2(25928, ItemUpgradeData.forCategoryId(category).get(i).getStartingId(), i, 1);
        }
    }

    public void selectItem(int itemId) {
        ItemUpgradeData data = ItemUpgradeData.forItemId(itemId);

        player.getPA().sendString(Misc.capitalize(ItemCacheDefinition.forID(data.getUpgradedItem().getId()).getName()), 26005);
        player.getPA().sendFrame34(data.getUpgradedItem().getId(), 0, 26007, data.getUpgradedItem().getAmount());

        player.getPA().sendString(Misc.format(data.getCoinCost()) + "gp", 26011);
        player.getPA().sendString(data.getSuccessRate() + "%", 26012);

        upgradeItem = data.getUpgradedItem().getId();
        selectedItem = itemId;
    }

    public void upgradeItem() {
        ItemUpgradeData data = ItemUpgradeData.forItemId(selectedItem);

        if (upgradeItem == -1 && selectedItem == -1) {
            player.sendMessage("You haven't select an item you wish to upgrade.");
            return;
        }

        if (!player.getItems().playerHasItem(selectedItem)) {
            player.sendMessage("You do not have the required items to upgrade.");
            return;
        }

        if (!player.getItems().playerHasItem(995, data.getCoinCost())) {
            player.sendMessage("You need " + Misc.format(data.getCoinCost()) + " coins to upgrade this item.");
            return;
        }

        player.getItems().deleteItem(995, data.getCoinCost());

        if (Misc.percentChance(data.getSuccessRate())) {
            player.getItems().deleteItem(selectedItem, 1);
            player.getItems().addItem(data.getUpgradedItem().getId(), 1);
            player.sendMessage("You upgrade your item and receive a " + ItemCacheDefinition.forID(data.getUpgradedItem().getId()).getName() + ".");
            resetInterface();
        } else {
            player.sendMessage("You have failed to create the upgraded item.");
            player.getItems().deleteItem(selectedItem, 1);
        }

    }

    public enum ItemUpgradeData {

        ZAMORAKIAN_SPEAR(0, 11824, 25_000_000, new GameItem(8802, 1), 10.0),
        SPEAR_OF_HAZZARDS(0, 8802, 80_000_000, new GameItem(8801, 1), 10.0),
        SCYTHE_OF_VITUR(0, 22325, 180_000_000, new GameItem(5700, 1), 15.0),
        STAFF_OF_LIGHT(0, 22296, 175_000_000, new GameItem(22323, 1), 10.0),
        TWISTED_BOW(0, 20997, 200_000_000, new GameItem(2883, 1), 15.0),
        SANG_STAFF(0, 22323, 175_000_000, new GameItem(6613, 1), 10.0),
        HOLY_SANG_STAFF(0, 6613, 275_000_000, new GameItem(8807, 1), 10.0),
        GHRAZI_RAPIER(0, 22324, 100_000_000, new GameItem(8806, 1), 25.0),
        //PH_KNIFE(0, 8806, 100_000_000, new GameItem(8804, 1), 15.0),


        BANDOS_CHESTPLATE(1, 11832, 150_000_000, new GameItem(22327, 1), 20.0),
        BANDOS_TASSETS(1, 11834, 150_000_000, new GameItem(22328, 1), 20.0),
        ANCESTAL_HAT(1, 21018, 100_000_000, new GameItem(8811, 1), 20.0),
        ANCESTAL_ROBE_TOP(1, 21021, 100_000_000, new GameItem(8812, 1), 20.0),
        ANCESTAL_ROBE_BOTTOMS(1, 21024, 100_000_000, new GameItem(8813, 1), 20.0),
        INFERNAL_HAT(1, 8811, 200_000_000, new GameItem(8814, 1), 15.0),

        INFERNAL_ROBE_TOP(2, 8812, 200_000_000, new GameItem(8815, 1), 15.0),
        INFERNAL_ROBE_BOTTOM(2, 8813, 200_000_000, new GameItem(8816, 1), 15.0),
        PRIMORDIAL_BOOTS(2, 13239, 125_000_000, new GameItem(9073, 1), 15.0),


        RING_OF_WEALTH(2, 2572, 50_000_000, new GameItem(12785, 1), 40.0),
        RING_OF_WEALTH_I(2, 12785, 50_000_000, new GameItem(6465, 1), 15.0),
        JUSTICAR_HELM(3,22326, 200_000_000, new GameItem(8808, 1), 25.0),
        JUSTICAR_CHESTPLATE(3, 22327, 200_000_000, new GameItem(8809, 1), 25.0),
        JUSTICAR_LEGGUARDS(3,22328, 200_000_000, new GameItem(8810, 1), 25.0),
        PH_KNIFE(3, 8806, 100_000_000, new GameItem(8804, 1), 15.0),
        HELLS_SCYTHE(3, 5700, 280_000_000, new GameItem(8817, 1), 15.0),

        ;

        private final int category;
        private final int startingId;
        private final int cost;
        private final GameItem outcomeId;
        private final double successRate;


        ItemUpgradeData(int category, int itemId, int cost, GameItem outcomeId, double chance) {
            this.category = category;
            this.startingId = itemId;
            this.cost = cost;
            this.outcomeId = outcomeId;
            this.successRate = chance;
        }

        public int getCategory() {
            return category;
        }

        public int getStartingId() {
            return startingId;
        }

        public int getCoinCost() {
            return cost;
        }

        public GameItem getUpgradedItem() {
            return outcomeId;
        }

        public double getSuccessRate() {
            return successRate;
        }

        public static int getCategorySize(int category) {
            return (int) Arrays.stream(values()).filter(c -> c.getCategory() == category).count();
        }

        public static List<ItemUpgradeData> forCategoryId(int category) {
            return Arrays.stream(values()).filter(c -> c.getCategory() == category).collect(Collectors.toList());
        }

        public static ItemUpgradeData forItemId(int itemId) {
            return Arrays.stream(values()).filter(c -> c.getStartingId() == itemId).findAny().orElse(null);
        }
    }
}
