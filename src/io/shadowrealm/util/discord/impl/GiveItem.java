package io.shadowrealm.util.discord.impl;

import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.discord.Discord;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GiveItem extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] params = e.getMessage().getContentRaw().toLowerCase().split("-");
        if (params == null || params.length != 4) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String name = params[1];
        int id = Integer.parseInt(params[2]);
        int amount = Integer.parseInt(params[3]);

        Player target = PlayerHandler.getPlayerByDisplayName(name);
        if (target == null) {
//            user.openPrivateChannel().queue((channel) -> channel.sendMessage(name + " must be online to give them stuff!").queue());
            e.getMessage().getTextChannel().sendMessage(name + " must be online to give them stuff you fucking moron -.-!").queue();
        } else {
            target.getItems().addItemUnderAnyCircumstance(id, amount);
//            user.openPrivateChannel().queue((channel) -> channel.sendMessage( "Gave " + amount + "x " + ItemDef.forId(id).getName() + " to " + name + ".").queue());
//            e.getMessage().getTextChannel().sendMessage("Gave " + amount + "x " + ItemDef.forId(id).getName() + " to " + name + ".").queue();

            target.sendMessage(user.getName() + " Gave you " + ItemDef.forId(id).getName() + " x " + amount);

            Discord.writepunishments("[Give-Log] " + user.getName() + " gave " + Misc.capitalizeJustFirst(name) + " Item: " + ItemDef.forId(id).getName() + " x " + amount + " (" + id +")");
        }
    }

}
