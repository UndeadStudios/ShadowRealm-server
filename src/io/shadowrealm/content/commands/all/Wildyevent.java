package io.shadowrealm.content.commands.all;

import java.util.Optional;

import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.events.monsterhunt.MonsterHunt;
import io.shadowrealm.model.entity.player.Player;

public class Wildyevent extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		if(MonsterHunt.getCurrentLocation() != null) {
			player.setGlodDamageCounter(0);
			player.setIceQueenDamageCounter(0);
			player.getPA().spellTeleport(MonsterHunt.getCurrentLocation().getX(), MonsterHunt.getCurrentLocation().getY(), 0, false);
		} else {
			player.sendMessage("@red@[Wildy Event] @bla@There is currently no wildy event going on.");
		}
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teles you to wildy event");
	}
}
