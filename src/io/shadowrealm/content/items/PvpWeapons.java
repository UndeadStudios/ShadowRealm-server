package io.shadowrealm.content.items;

import io.shadowrealm.model.Items;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.ImmutableItem;
import io.shadowrealm.util.Misc;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @author Arthur Behesnilian 3:04 PM
 */
public class PvpWeapons {

    /**
     * The maximum amount of charges a pvp weapon can hold
     */
    private static final int MAX_CHARGES = 20_000;

    /**
     * All the Pvp Weapons
     */
    private static final int[] PVP_WEAPONS = {
            Items.CRAWS_BOW, Items.THAMMARONS_SCEPTRE, Items.VIGGORAS_CHAINMACE,25865
    };
    private static final int[] bofa = {
            25865,
    };

    /**
     * All the uncharges Pvp weapons
     */
    private static final int[] U_PVP_WEAPONS = {Items.CRAWS_BOW_U, Items.THAMMARONS_SCEPTRE_U,
            Items.VIGGORAS_CHAINMACE_U, Items.BOW_OF_FAERDHINEN};
    private static final int[] u_bofa = {25862};

    /**
     * The player assodciated with this handler
     */
    private Player player;

    /**
     * The amount of charges the player has for Viggora's Chainmace
     */
    private int viggoraChainmaceCharges;
    /**
     * The amount of charges the player has for Thammaron's Sceptre
     */
    private int thammaronSceptreCharges;
    /**
     * The amount of charges the player has for Craw's Bow
     */
    private int crawsBowCharges;
    private int bofaCharges;

    /**
     * Manages the Pvp weapons information for the player
     *
     * @param player The player associated
     */
    public PvpWeapons(Player player) {
        this.player = player;
        //this.viggoraChainmaceCharges = 20_000;
        //this.thammaronSceptreCharges = 20_000;
       // this.crawsBowCharges = 20_000;
    }

    /**
     * Retrieves the amount of charges the player has for the specified pvp weapon
     *
     * @param weaponId The pvp weappon id
     * @return The amount of charges the player has
     */
    public int getCharges(int weaponId) {
        switch (weaponId) {
            case Items.VIGGORAS_CHAINMACE:
                return this.viggoraChainmaceCharges;
            case Items.THAMMARONS_SCEPTRE:
                return this.thammaronSceptreCharges;
            case Items.CRAWS_BOW:
                return this.crawsBowCharges;
            case 25865:
                return this.bofaCharges;
        }
        return 0;
    }
    public int getViggoraChainmaceCharges() {
        return viggoraChainmaceCharges;
    }

    public void setViggoraChainmaceCharges(int viggoraChainmaceCharges) {
        this.viggoraChainmaceCharges = viggoraChainmaceCharges;
    }

    public int getThammaronSceptreCharges() {
        return thammaronSceptreCharges;
    }

    public void setThammaronSceptreCharges(int thammaronSceptreCharges) {
        this.thammaronSceptreCharges = thammaronSceptreCharges;
    }

    public int getCrawsBowCharges() {
        return crawsBowCharges;
    }
    public int getbofaCharges() {
        return bofaCharges;
    }

    public void setCrawsBowCharges(int crawsBowCharges) {
        this.crawsBowCharges = crawsBowCharges;
    }
    public void setbofaCharges(int bofaCharges) {
        this.bofaCharges = bofaCharges;
    }

    /**
     * Gives the player the starting charges for all pvp weapons
     *
     * @param player The player that is being given charges
     */
    public static void giveStartingCharges(Player player) {
        player.getPvpWeapons().setCrawsBowCharges(0);
        player.getPvpWeapons().setViggoraChainmaceCharges(0);
        player.getPvpWeapons().setThammaronSceptreCharges(0);
        player.getPvpWeapons().setbofaCharges(0);
    }

    /**
     * Determines if the item id is a Pvp weapon
     *
     * @param itemId The item id
     * @return True if it is a pvp weapon
     */
    private static boolean isPvpWeapon(int itemId) {
        return Arrays.stream(U_PVP_WEAPONS).anyMatch(pvpWeaponId -> pvpWeaponId == itemId)
                ||Arrays.stream(PVP_WEAPONS).anyMatch(pvpWeaponId -> pvpWeaponId == itemId);
    }
    private static boolean isbofa(int itemId) {
        return Arrays.stream(u_bofa).anyMatch(bofaid -> bofaid == itemId)
                ||Arrays.stream(bofa).anyMatch(bofaid -> bofaid == itemId);
    }

    /**
     * Resets the charges for the selected pvp weapon
     *
     * @param weaponId The weaponId associated with the pvp weapon
     */
    public static void resetCharges(Player player, int weaponId) {
        switch (weaponId) {
            case Items.THAMMARONS_SCEPTRE:
                player.getPvpWeapons().setThammaronSceptreCharges(0);
                break;
            case Items.CRAWS_BOW:
                player.getPvpWeapons().setCrawsBowCharges(0);
                break;
            case Items.VIGGORAS_CHAINMACE:
                player.getPvpWeapons().setViggoraChainmaceCharges(0);
                break;
            case 25865:
                player.getPvpWeapons().setbofaCharges(0);
                break;
        }
    }

    /**
     * Manipulates the amount of charges for the player's pvp weapon
     *
     * @param player   The player to add charges to
     * @param weaponId The weapon id to add charges to
     * @param amount   The amount of charges to add
     */
    public static void manipulateCharges(Player player, int weaponId, int amount) {
        int charges = player.getPvpWeapons().getCharges(weaponId);
        switch (weaponId) {
            case Items.THAMMARONS_SCEPTRE:
                player.getPvpWeapons().setThammaronSceptreCharges(charges + amount);
                break;
            case Items.CRAWS_BOW:
                player.getPvpWeapons().setCrawsBowCharges(charges + amount);
                break;
            case Items.VIGGORAS_CHAINMACE:
                player.getPvpWeapons().setViggoraChainmaceCharges(charges + amount);
                break;
                case 25865:
                player.getPvpWeapons().setbofaCharges(charges + amount);
                break;
        }
    }
//    public static void manipulatebCharges(Player player, int weaponId2, int amount) {
//        int charges = player.getPvpWeapons().bofaCharges(weaponId2);
//        switch (weaponId2) {
//            case 25865:
//                player.getPvpWeapons().setbofaCharges(charges + amount);
//                break;
//        }
//    }

    /**
     * Handles all item interactions with the Pvp weapon
     *
     * @param itemId The itemId being used
     */
    public static void handleItemOption(Player player, int itemId, int option) {
        if (!isPvpWeapon(itemId))
            return;

        int charges = player.getPvpWeapons().getCharges(itemId);
        ItemDef definition = ItemDef.forId(itemId);
        String itemName = definition.getName();

        switch (option) {
            case 2: // Check charges
                checkCharges(player, definition, charges);
                break;
            case 3: // Uncharge
                uncharge(player, definition, charges);
                break;
        }
    }

    /**
     * Displays the amount of charges the player has for the given pvp weapon
     *
     * @param player  The player to display the information to
     * @param itemDef The item definition of the pvp weapon
     * @param charges The amount of charges the player has
     */
    private static void checkCharges(Player player, ItemDef itemDef, int charges) {
        String totalCharges = Misc.format(charges);
        player.sendMessage("Your " + itemDef.getName() + " has " + totalCharges + " charges.");
    }

    /**
     * Removes charges from the player's pvp weapon
     *
     * @param player  The player uncharging the weapon
     * @param itemDef The item definition for the pvp weapon
     * @param charges The amount of charges the pvp weapon has
     */
    private static void uncharge(Player player, ItemDef itemDef, int charges) {
        int itemId = itemDef.getId();

        if (charges > 0) {

            int itemIdx = ArrayUtils.indexOf(PVP_WEAPONS, itemId);
            ImmutableItem ether = new ImmutableItem(Items.REVENANT_ETHER, charges);
            ImmutableItem replacement = new ImmutableItem(U_PVP_WEAPONS[itemIdx], 1);
            boolean hasSpace = player.getItems().freeSlots() > 0;

            if (hasSpace) {
                player.getItems().deleteItem(itemId, 1);
                player.getItems().addItem(replacement.getId(), replacement.getAmount());
                player.getItems().addItem(ether.getId(), ether.getAmount());
                PvpWeapons.resetCharges(player, itemId);
                player.sendMessage("You have removed " + charges + " Revenant ether from your " + itemDef.getName() +
                        ".");
            } else
                player.sendMessage("You have no space in your inventory to do that.");

        } else
            player.sendMessage("Your " + itemDef.getName() + " has no charges.");
    }


    /**
     * Determines if the items used are pvp weapons
     *
     * @param itemsUsed The two items that are used on eachother
     * @return True if it is a pvp weapon
     */
    private static boolean isPvpWeapon(int... itemsUsed) {
        for (int itemUsed : itemsUsed) {
            if (Arrays.stream(PVP_WEAPONS).anyMatch(id -> id == itemUsed)) return true;
            if (Arrays.stream(U_PVP_WEAPONS).anyMatch(id -> id == itemUsed)) return true;
        }
        return false;
    }
    private static boolean isbofa(int... itemsUsed) {
        for (int itemUsed : itemsUsed) {
            if (Arrays.stream(bofa).anyMatch(id -> id == itemUsed)) return true;
            if (Arrays.stream(u_bofa).anyMatch(id -> id == itemUsed)) return true;
        }
        return false;
    }

    /**
     * Finds the replacement id for an uncharged pvp weapon
     *
     * @param pvpWeapon The uncharged pvp weapon
     * @return A value greater than -1 if a replacement is found
     */
    private static int getReplacementId(int pvpWeapon) {
        if (Arrays.stream(U_PVP_WEAPONS).anyMatch(id -> pvpWeapon == id)) {
            int weaponIdx = ArrayUtils.indexOf(U_PVP_WEAPONS, pvpWeapon);
            return PVP_WEAPONS[weaponIdx];
        }
        return -1;
    }
    private static int getReplacementId2(int pvpWeapon) {
        if (Arrays.stream(u_bofa).anyMatch(id -> pvpWeapon == id)) {
            int weaponIdx = ArrayUtils.indexOf(u_bofa, pvpWeapon);
            return bofa[weaponIdx];
        }
        return -1;
    }

    /**
     * Handles the item on item action for pvp weapons and ether
     *
     * @param player       The player using the items
     * @param itemUsed     The item that was used
     * @param itemUsedWith The item that the item was used on
     * @return True if the handling was processed successfully
     */
    public static boolean handleItemOnItem(Player player, int itemUsed, int itemUsedWith) {
        boolean hasEther = itemUsed == Items.REVENANT_ETHER || itemUsedWith == Items.REVENANT_ETHER;
        //boolean hasCoins = itemUsed2 == 995 || itemUsedWith2 == 995;
        boolean hasPvpWeapon = isPvpWeapon(itemUsed, itemUsedWith);
        //boolean hasbofa = isbofa(itemUsed2, itemUsedWith2);

        // Check if items used are ether and pvp weapon
        if (hasEther && hasPvpWeapon) {
            // Determine what item is what
            final int pvpWeapon = isPvpWeapon(itemUsed) ? itemUsed : itemUsedWith;
            int ether = pvpWeapon == itemUsed ? itemUsedWith : itemUsed;

            // Determine if the uncharged variant should be replaced with charged variant
            int replacementId = PvpWeapons.getReplacementId(pvpWeapon);

            ItemDef definition = ItemDef.forId(pvpWeapon);

            int charges = player.getPvpWeapons().getCharges(pvpWeapon);
            int etherAmount = player.getItems().getItemAmount(ether);

            int chargeSpace = MAX_CHARGES - charges;
            // Check if the player can add more charges
            if (chargeSpace > 0) {
                // Replace uncharged variant with charged variant
                if (replacementId != -1) {
                    player.getItems().deleteItem(pvpWeapon, 1);
                    player.getItems().addItem(replacementId, 1);
                }

                // Remove ether add charges
                int etherToAdd = Math.min(etherAmount, chargeSpace);
                player.getItems().deleteItem(ether, etherToAdd);
                PvpWeapons.manipulateCharges(player, replacementId != -1 ? replacementId : pvpWeapon, etherToAdd);

                player.sendMessage("You have added " + etherToAdd + " Revenant ether to your " + definition.getName());

            } else
                player.sendMessage("Your " + definition.getName() + " already has the maximum amount of charges.");

            return true;
        }      // Check if items used are ether and pvp weapon

        return false;
    }
    public static boolean handleItemOnItem2(Player player, int itemUsed, int itemUsedWith) {
        boolean hasCoins = itemUsed == Items.COINS || itemUsedWith == Items.COINS;
        //boolean hasCoins = itemUsed2 == 995 || itemUsedWith2 == 995;
        boolean hasbofa = isbofa(itemUsed, itemUsedWith);
        //boolean hasbofa = isbofa(itemUsed2, itemUsedWith2);

        // Check if items used are ether and pvp weapon
        if (hasCoins && hasbofa) {
            // Determine what item is what
            final int bofa = isbofa(itemUsed) ? itemUsed : itemUsedWith;
            int coins = bofa == itemUsed ? itemUsedWith : itemUsed;

            // Determine if the uncharged variant should be replaced with charged variant
            int replacementId = PvpWeapons.getReplacementId(bofa);

            ItemDef definition = ItemDef.forId(bofa);

            int charges = player.getPvpWeapons().getCharges(bofa);
            int coinsAmount = player.getItems().getItemAmount(coins);

            int chargeSpace = MAX_CHARGES - charges;
            // Check if the player can add more charges
            if (chargeSpace > 0) {
                // Replace uncharged variant with charged variant


                // Remove ether add charges
                int coinstoadd = Math.min(coinsAmount, chargeSpace);
                player.getItems().deleteItem(coins, coinstoadd * 10000);
                PvpWeapons.manipulateCharges(player, replacementId != -1 ? replacementId : bofa, coinstoadd);

                player.sendMessage("You have added " + coinstoadd * 10000 + " coins  to your " + definition.getName());

            } else
                player.sendMessage("Your " + definition.getName() + " already has the maximum amount of charges.");

            return true;
        }      // Check if items used are ether and pvp weapon

        return false;
    }

    /**
     * Determines whether the pvp weapon effect should be applied
     *
     * @param player   The player that is being checked
     * @param weaponId The weaponId of the player
     * @return True if the player is wearing the pvp item and has enough charges
     */
    public static boolean activateEffect(Player player, int weaponId) {
        return player.getItems().isWearingItem(weaponId, Player.playerWeapon)
                && player.getPvpWeapons().getCharges(weaponId) > 0;
    }

    /**
     * Attempts to degrade the weapon to its uncharged form
     *
     * @param player The player whose weapon is being degraded
     */
    public static void degradeWeaponAfterCombat(Player player, boolean usingMagic) {
        int weaponId = player.getItems().getWeapon();

        // Dont degrade thammarons if not using magic spells
        if (weaponId == Items.THAMMARONS_SCEPTRE && !usingMagic) return;

        if (Arrays.stream(PVP_WEAPONS).anyMatch(id -> weaponId == id)) {
            int charges = player.getPvpWeapons().getCharges(weaponId);
            if (charges <= 0) {
                int weaponIdx = ArrayUtils.indexOf(PVP_WEAPONS, weaponId);
                int unchargedWeapon = U_PVP_WEAPONS[weaponIdx];
                player.getItems().equipItem(unchargedWeapon, 1, Player.playerWeapon);
            }
        }
    }

}
