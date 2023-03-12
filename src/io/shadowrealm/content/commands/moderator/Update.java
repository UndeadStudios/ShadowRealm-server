package io.shadowrealm.content.commands.moderator;

import java.util.Objects;

import io.shadowrealm.Server;
import io.shadowrealm.content.commands.Command;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.wogw.Wogw;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionFinalizeType;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionStage;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionType;
import io.shadowrealm.model.multiplayersession.duel.DuelSession;

/**
 * Start the update timer and update the server.
 * 
 * @author Emiel
 *
 */
public class Update extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		int seconds = Integer.parseInt(input);
		if (seconds < 15) {
			c.sendMessage("The timer cannot be lower than 15 seconds so other operations can be sorted.");
			seconds = 15;
		}
		PlayerHandler.updateSeconds = seconds;
		PlayerHandler.updateAnnounced = false;
		PlayerHandler.updateRunning = true;
		PlayerHandler.updateStartTime = System.currentTimeMillis();
		Wogw.save();
		for (Player player : PlayerHandler.players) {
			if (player == null) {
				continue;
			}
			Player client = player;
			if (client.getPA().viewingOtherBank) {
				client.getPA().resetOtherBank();
				client.sendMessage("An update is now occuring, you cannot view banks.");
			}
			DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(client, MultiplayerSessionType.DUEL);
			if (Objects.nonNull(duelSession)) {
				if (duelSession.getStage().getStage() == MultiplayerSessionStage.FURTHER_INTERATION) {
					if (!duelSession.getWinner().isPresent()) {
						duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
						duelSession.getPlayers().forEach(p -> {
							p.sendMessage("The duel has been cancelled by the server because of an update.");
							duelSession.moveAndClearAttributes(p);
						});
					}
				} else if (duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
					duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
					duelSession.getPlayers().forEach(p -> {
						p.sendMessage("The duel has been cancelled by the server because of an update.");
						duelSession.moveAndClearAttributes(p);
					});
				}
			}
			player.getPA().sendUpdateTimer();
		}
	}
}
