package io.exilius.content.upgrading

import io.exilius.model.definitions.ItemDef
import io.exilius.model.entity.player.Player
import io.exilius.model.items.GameItem
import io.exilius.model.items.ItemCacheDefinition
import io.exilius.util.Misc

/**
 * @author Flub
 * Handles the upgrade interface (ID: 25898) logic.
 */
class ItemUpgrading(val player: Player) {
    // Used to store the chosen category. Default is Weapon.
    private var categorySelected = Categories.WEAPON
    // This stores the items that we send to the container.
    private var categoryItems = arrayListOf<GameItem>()
    private var upgradeItem = -1
    private var selectedItem = -1

    // The item container ID in the interface
    private val categoryContainerID = 25928

    // If you need an explanation of this, quit coding forever
    private val closeBtn = 101045
    private val upgradeBtn = 101157

    // The ID's of each category button
    private val categoryButtonIds = intArrayOf(101063, 101064, 101065, 101066)

    /**
     * First, check if the pressed button matches the Close, Upgrade or Category buttons.
     * Then, we handle the logic based on the buttons.
     */
    fun handleButtons(buttonId: Int): Boolean {

        if (!categoryButtonIds.contains(buttonId) && buttonId != closeBtn && buttonId != upgradeBtn) return false

        if (categoryButtonIds.contains(buttonId)) {
            categorySelected = Categories.values()[buttonId - 101063]
            println("Category selected: ${categorySelected.name}")
            swapCategory(categorySelected)
        }

        when (buttonId) {
            upgradeBtn -> upgradeItem()
            closeBtn -> player.pa.closeAllWindows()
        }
        return true
    }

    /**
     * If the player chooses a new category,
     * search the data for items that match,
     * add those items to the container,
     * then send the container
     */
    private fun swapCategory(newCategory: Categories) {
        val data = UpgradeData.values().filter { it.category == newCategory }
        clearCategoryData()
        data.forEach { categoryItems.add(GameItem(it.originalItemID)) }
        player.items.sendItemContainer(categoryContainerID, categoryItems)
    }

    /**
     * Clear the item container and then send it to clear the category section.
     */
    private fun clearCategoryData() {
        categoryItems.clear()
        player.items.sendItemContainer(categoryContainerID, categoryItems)
    }

    /**
     * Clear the upgradable item
     */
    private fun resetInterface() {
        player.pa.sendItemsOnInterface(26007, -1, 0, -1)
        // Clear the item container and send it
        clearCategoryData()
        player.items.sendItemContainer(categoryContainerID, categoryItems)
        player.pa.sendString("", 26005)
        player.pa.sendString("0", 26011)
        player.pa.sendString("0", 26012)
        selectedItem = -1
        upgradeItem = -1
        categorySelected = Categories.WEAPON
    }

    /**
     * Open the interface, show the weapon category initially.
     */
    fun openInterface() {
        // Find items in the selected category and then send it to the interface
        resetInterface()
        UpgradeData.values().filter { it.category == Categories.WEAPON }
            .forEach { categoryItems.add(GameItem(it.originalItemID)) }
        player.pa.showInterface(25898)
        player.items.sendItemContainer(categoryContainerID, categoryItems)
    }

    /**
     * Search the upgrade data based on what item the player clicks on
     */
    fun selectItem(itemIdClicked: Int) {
        val data = UpgradeData.values().lastOrNull { it.originalItemID == itemIdClicked } ?: return
        player.pa.sendString(Misc.capitalize(ItemDef.forId(data.upgradedItem.id).name), 26005)
        player.pa.sendItemOnInterface(data.upgradedItem.id, 0, 26007, data.upgradedItem.amount)
        player.pa.sendString(Misc.format(data.price.toLong()) + "gp", 26011)
        player.pa.sendString(data.successRate.toString() + "%", 26012)
        upgradeItem = data.upgradedItem.id
        selectedItem = itemIdClicked
    }

    /**
     * Find the upgradable data using the selected item ID.
     * If null, we return.
     * We then check requirements, delete the GP and item,
     * then test if we succeed.
     */
    private fun upgradeItem() {
        val data = UpgradeData.values().lastOrNull { it.originalItemID == selectedItem } ?: return

        if (upgradeItem == -1 || selectedItem == -1) {
            player.sendMessage("Select an item to upgrade first.")
            return
        }
        if (!player.items.playerHasItem(selectedItem)) {
            player.sendMessage("You do not have the required items to upgrade.")
            return
        }
        if (!player.items.playerHasItem(995, data.price)) {
            player.sendMessage("You need " + Misc.format(data.price.toLong()) + " coins to upgrade this item.")
            return
        }
        player.items.deleteItem(995, data.price)

        if (Misc.percentChance(data.successRate)) {
            player.items.deleteItem(selectedItem, 1)
            player.items.addItem(data.upgradedItem.id, 1)
            player.sendMessage(
                "You upgrade your item and receive a ${ItemDef.forId(data.upgradedItem.id).name}."
            )
            // We clear the interface if they successfully upgrade,
            resetInterface()
        } else {
            player.sendMessage("You have failed to create the upgraded item.")
            player.items.deleteItem(selectedItem, 1)
        }
    }
}