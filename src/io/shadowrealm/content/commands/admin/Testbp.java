package io.shadowrealm.content.commands.admin;

import io.shadowrealm.content.battle_pass.BattlePassHandler;
import io.shadowrealm.content.battle_pass.BattlePassInterface;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;

/**
 * Open the battlepass interface and fill with inventory items
 * 
 * @author Emiel
 */
public class Testbp extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		/*c.getPA().showInterface(27725);
		List<GameItem> free = new ArrayList<GameItem>();
		int index = 0;
		for (SlottedItem item : c.getItems().getInventoryItems()) {
			if (index >= 4) break;
			free.add(new GameItem(item.getId(), item.getAmount()));
			index++;
		}
		List<GameItem> prem = new ArrayList<GameItem>();
		int index1 = 0;
		for (SlottedItem item : c.getItems().getInventoryItems()) {
			if (index1 >= 4) break;
			prem.add(new GameItem(item.getId(), item.getAmount()));
			index1++;
		}
		c.getPA().sendItems(c,27755 , free, 4);
		c.getPA().sendItems(c,27756 , prem, 4);*/
		BattlePassInterface.Companion.open(c);
		BattlePassHandler.Companion.sendInfoToPlayer(c);



	}
}
