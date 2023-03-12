package io.shadowrealm.content.commands.all;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.discord.Discord;

public class Claim extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        new java.lang.Thread() {
            public void run() {
                try {
                    com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation
                            .donations("uNjRROu9h87snrVU1OLXV2H9DCXsvww5ffuagLPcchgi12ZQHAUOX0vjxn44UW2uwP2nPHSx", player.getLoginName());
                    if (donations.length == 0) {
                        player.sendMessage("You currently don't have any items waiting. You must donate first!");
                        return;
                    }
                    if (donations[0].message != null) {
                        player.sendMessage(donations[0].message);
                        return;
                    }
                    for (com.everythingrs.donate.Donation donate: donations) {
                        String messaged = "Donation System:" + player.getDisplayName() + " has just Donated for " + ItemDef.forId(donate.product_id).getName();
                        player.getItems().addItem(donate.product_id, donate.product_amount);
                        Discord.writeannounceMessage(messaged);
                    }
                    player.sendMessage("Thank you for donating!");
                } catch (Exception e) {
                    player.sendMessage("Api Services are currently offline. Please check back shortly");
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
