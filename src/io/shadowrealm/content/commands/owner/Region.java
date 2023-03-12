package io.shadowrealm.content.commands.owner;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.entity.player.Right;

public class Region extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {

				// TODO Auto-generated method stub
				int regionId = Integer.parseInt(input);
				int x = (regionId >> 8) << 6;
				int y = (regionId & 0xFF) << 6;
				c.getPA().movePlayer(x, y, c.heightLevel);
			}

}
