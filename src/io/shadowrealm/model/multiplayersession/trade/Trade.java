package io.shadowrealm.model.multiplayersession.trade;

import io.shadowrealm.Configuration;
import io.shadowrealm.Server;
import io.shadowrealm.content.DiceHandler;
import io.shadowrealm.content.minigames.pest_control.PestControl;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.multiplayersession.Multiplayer;
import io.shadowrealm.model.multiplayersession.MultiplayerSession;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionStage;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionType;

import java.util.Arrays;
import java.util.Objects;

public class Trade extends Multiplayer {

	public static boolean requestable(Player player, Player requested) {
		if (requested == null) {
			player.sendMessage("The requested player cannot be found.");
			return false;
		}
		if(player.getLoginName().equalsIgnoreCase("rico")){
			player.sendMessage("You cannot trade.");
			return false;
		}
		if(requested.getLoginName().equalsIgnoreCase("rico")){
			requested.sendMessage("This person cannot trade.");
			return false;
		}
//		if(player.getRights().getPrimary().equals(Right.YOUTUBER)){
//			player.sendMessage("You cant not trade.");
//			return false;
//		}
//		if(requested.getRights().getPrimary().equals(Right.YOUTUBER)){
//			requested.sendMessage("You can't trade youtubers.");
//			return false;
//		}
		if (player.getLootingBag().isWithdrawInterfaceOpen() || player.getLootingBag().isDepositInterfaceOpen() ||
				requested.getLootingBag().isWithdrawInterfaceOpen() || requested.getLootingBag().isDepositInterfaceOpen()) {
			return false;
		}
		if (player.getBankPin().requiresUnlock()) {
			player.getBankPin().open(2);
			return false;
		}
		if (requested.getBankPin().requiresUnlock()) {
			return false;
		}
		if (Boundary.isIn(player, Boundary.HESPORI) || Boundary.isIn(requested, Boundary.HESPORI)) {
			player.sendMessage("You can't trade inside Hespori.");
			return false;
		}
		if (player.getLoginName().equals("kms")) {
			player.sendMessage("You arent able to trade.");
			return false;
		}
		if (Boundary.RAIDS_LOBBY.in(player) || Boundary.LOBBY.in(requested)) {
			player.sendMessage("You can't trade inside Raids Lobby.");
			return false;
		}
		if (!player.getMode().isTradingPermitted(player, requested)) {
			player.sendMessage("You are not permitted to trade other players.");
			return false;
		}
		if (!requested.getMode().isTradingPermitted(requested, player)) {
			player.sendMessage("That player is on a game mode that restricts trading.");
			return false;
		}
		if (!Server.isDebug()) {
			if (!player.getRights().isOrInherits(Right.GROUP_IRONMAN)) {
				if (!player.ignoreNewPlayerRestriction(requested)) {
					if (player.hasNewPlayerRestriction()) {
						player.sendMessage("You cannot request a trade, you must play for at least "
								+ Configuration.NEW_PLAYER_RESTRICT_TIME_MIN + " minutes.");
						return false;
					}
					if (requested.hasNewPlayerRestriction()) {
						player.sendMessage("You cannot trade this player, they have not played for "
								+ Configuration.NEW_PLAYER_RESTRICT_TIME_MIN + " minutes.");
						return false;
					}
				}
			}
		}
		if (Objects.equals(player, requested)) {
			player.sendMessage("You cannot trade yourself.");
			return false;
		}
		if (Server.getMultiplayerSessionListener().inAnySession(player)) {
			player.sendMessage("You cannot request a trade whilst in a session.");
			return false;
		}
		if (Server.getMultiplayerSessionListener().inAnySession(requested)) {
			player.sendMessage("This player is currently is a session with another player.");
			return false;
		}
		if (player.teleTimer > 0 || requested.teleTimer > 0) {
			player.sendMessage("You cannot request or accept whilst you, or the other player are teleporting.");
			return false;
		}
		if (Boundary.isIn(player, PestControl.GAME_BOUNDARY) || Boundary.isIn(requested, PestControl.GAME_BOUNDARY)) {
			player.sendMessage("You cannot trade in the pest control minigame.");
			return false;
		}
		if (requested.getMode().isGroupIronman() && !player.getMode().isGroupIronman()) {
			player.sendMessage("You cannot trade with Group Ironman mode accounts.");
			return false;
		}
		
		return true;
	}
	
	public Trade(Player player) {
		super(player);
	}

	@Override
	public boolean requestable(Player requested) {
		if (Server.getMultiplayerSessionListener().requestAvailable(requested, player, MultiplayerSessionType.TRADE) != null) {
			player.sendMessage("You have already sent a request to this player.");
			return false;
		}
		return requestable(player, requested);
	}

	@Override
	public void request(Player requested) {
		if (Objects.isNull(requested)) {
			player.sendMessage("The player cannot be found, try again shortly.");
			return;
		}
		if (Objects.equals(player, requested)) {
			player.sendMessage("You cannot trade yourself.");
			return;
		}
		if (Boundary.isIn(player, Boundary.OUTLAST_HUT)) {
			player.sendMessage("Please leave the outlast hut area to trade.");
			return;
		}
		if (requested.underAttackByPlayer > 0 || requested.underAttackByNpc > 0 && requested.underAttackByNpc != requested.lastNpcAttacked && !requested.getPosition().inMulti()) {
			player.sendMessage("You cannot trade this person whilst he has been recently in combat or in multi.");
			return;
		}

		if (DiceHandler.inDicingArea(player) || DiceHandler.inDicingArea(requested)) {
			if (player.isGambleBanned()) {
				player.sendMessage("You cannot gamble.");
				return;
			}
			if (requested.isGambleBanned()) {
				player.sendMessage("That player cannot gamble.");
				return;
			}
		}

		player.faceUpdate(requested.getIndex());
		MultiplayerSession session = Server.getMultiplayerSessionListener().requestAvailable(player, requested, MultiplayerSessionType.TRADE);
		if (session != null) {
			session.getStage().setStage(MultiplayerSessionStage.OFFER_ITEMS);
			session.populatePresetItems();
			session.updateMainComponent();
			Server.getMultiplayerSessionListener().removeOldRequests(player);
			Server.getMultiplayerSessionListener().removeOldRequests(requested);
			session.getStage().setAttachment(null);
		} else {
			session = new TradeSession(Arrays.asList(player, requested), MultiplayerSessionType.TRADE);
			if (Server.getMultiplayerSessionListener().appendable(session)) {
				player.sendMessage("Sending trade offer...");
				requested.sendMessage(player.getDisplayName() + ":tradereq:");
				session.getStage().setAttachment(player);
				Server.getMultiplayerSessionListener().add(session);
			}
		}
	}

}
