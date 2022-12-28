package io.exilius.content.traveling;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.npc.NPCCacheDefinition;
import io.exilius.model.entity.player.Player;

public class Sailing {

    private static final int[][] TRAVEL_DATA = { {}, // 0 - Null
            { 2834, 3335, 15 , 1}, // 1 - From Port Sarim to Entrana
            { 3048, 3234, 15, 2}, // 2 - From Entrana to Port Sarim
            { 2853, 3237, 12, 3}, // 3 - From Port Sarim to Crandor
            { 2834, 3335, 13, 4}, // 4 - From Crandor to Port Sarim
            { 2956, 3146, 9, 5}, // 5 - From Port Sarim to Karajama
            { 3029, 3217, 8,6}, // 6 - From Karajama to Port Sarim
            { 2772, 3234, 3, 7}, // 7 - From Ardougne to Brimhaven
            { 2683, 3271, 3, 8}, // 8 - From Brimhaven to Ardougne
            { 2998, 3043, 23, 11 }, // 9 - From Port Khazard to Ship Yard
            { 2676, 3170, 23, 12 }, // 10 - From Ship Yard to Port Khazard
            { 2998, 3043, 17, 13 }, // 11 - From Cairn Island to Ship Yard
            { 2659, 2676, 12, 14 }, // 12 - From Port Sarim to Pest Control
            { 3041, 3202, 12, 15 }, // 13 - From Pest Control to Port Sarim
            { 2763, 2956, 10, 16 }, // 14 - To Cairn Isle from Feldip Hills
            { 2551, 3759, 20, 17}, // 15 - To Waterbirth from Relleka
            { 2620, 3686, 20, 18}, //16 - To Relleka from Waterbirth
    };

    //2620, 3686 - relleka
    //2551, 3759 - waterbirth

    public static boolean checkForCash(Player player) {
        if (!player.getItems().playerHasItem(995, 1000)) {
            //player.getDialogueHandler().sendNpcChat1("You need 1000 coins to to travel on this ship!", 2437, NpcHandler.getNpcListName(player.npcType));
            //player.nextChat = 0;
            return false;
        }
        player.getItems().deleteItem2(995, 1000);
        player.sendMessage("Your free to go and pay the 1000 coins.");
        return true;
    }

    public static boolean checkForCoins(Player player) {
        if (!player.getItems().playerHasItem(995, 30)) {
            player.getDH().sendNpcChat1("You need 30 coins to to travel on this ship!", 3644, NPCCacheDefinition.forID(player.npcType).getName().toLowerCase());
            player.nextChat = 0;
            return false;
        }
        player.getItems().deleteItem2(995, 30);
        player.sendMessage("Your free to go and pay the 30 coins.");
        return true;
    }

    public static boolean searchForAlcohol(Player player) {
        for (int element : Configuration.ALCOHOL_RELATED_ITEMS) {
            if (player.getItems().playerHasItem(element, 1)) {
                player.getDH().sendNpcChat1("You can't bring intoxicating items to Asgarnia!", 1169, NPCCacheDefinition.forID(player.npcType).getName().toLowerCase());
                player.nextChat = 0;
                return false;
            }
        }
        player.sendMessage(
                "Your clean of any possible alchohol.");
        return true;
    }

    public static boolean quickSearch(Player player) {
        for (int element : Configuration.COMBAT_RELATED_ITEMS) {
            if (player.getItems().playerHasItem(element, 1) || player.getItems().playerHasEquipped(element)) {
                player.getDH().sendNpcChat2("Grr! I see you brought some illegal items! Get", "out of my sight immediately!", 1166,  NPCCacheDefinition.forID(player.npcType).getName().toLowerCase());
                player.nextChat = 0;
                return false;
            }
        }
        player.sendMessage("Your clean of any possible weapons.");
        return true;
    }

    public static void startTravel(final Player player, final int i) {
        if (i == 1) {// entrana check
            if (!quickSearch(player)) {
                return;
            }
        }
        if (i == 2) {// entrana check
            if (!searchForAlcohol(player)) {
                return;
            }
        }
        if (i == 5 || i == 6 || i == 7 || i == 8) {
            if (!checkForCoins(player)) {
                return;
            }
        }
        if (i == 17 || i == 18) {
            if (!checkForCash(player)) {
                return;
            }
        }
        player.stopPlayerPacket = true;
        player.getPA().movePlayer(0, 0, 0);
        player.getPlayerAssistant().showInterface(3281);
        player.getPlayerAssistant().sendConfig(75, getconfig(i));

        CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                player.getPlayerAssistant().movePlayer(getX(i), getY(i), 0);
                container.stop();
            }
            public void stop() {

            }
        }, getTime(i) - 1);

        CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                player.stopPlayerPacket = false;
                player.getPlayerAssistant().sendConfig(75, -1);
                player.getPlayerAssistant().closeAllWindows();
                //player.getDialogueHandler().sendStatement("You arrive safely.");
                player.nextChat = 0;
                container.stop();
            }
            public void stop() {

            }
        }, getTime(i));

    }

    public static int getX(int i) {
        return TRAVEL_DATA[i][0];
    }

    public static int getY(int i) {
        return TRAVEL_DATA[i][1];
    }

    public static int getTime(int i) {
        return TRAVEL_DATA[i][2];
    }
    public static int getconfig(int i) {
        return TRAVEL_DATA[i][3];
    }
}
