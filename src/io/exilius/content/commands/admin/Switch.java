package io.exilius.content.commands.admin;

import io.exilius.content.commands.Command;
import io.exilius.model.SpellBook;
import io.exilius.model.entity.player.Player;

/**
 * @author Arthur Behesnilian 1:04 PM
 */
public class Switch extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        SpellBook nextSpellbook = SpellBook.MODERN;
        switch (player.playerMagicBook) {
            case 0:
                nextSpellbook = SpellBook.ANCIENT;
                break;
            case 1:
                nextSpellbook = SpellBook.LUNAR;
                break;
        }
        player.sendMessage("You switch your spellbook to the " + nextSpellbook.name()+ " spellbook.");
        player.setSpellBook(nextSpellbook);
    }


}
