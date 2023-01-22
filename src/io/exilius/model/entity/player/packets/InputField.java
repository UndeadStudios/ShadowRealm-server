package io.exilius.model.entity.player.packets;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.exilius.Server;
import io.exilius.content.help.HelpDatabase;
import io.exilius.content.help.HelpRequest;
import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;
import io.exilius.model.entity.player.Right;
import io.exilius.model.items.bank.BankPin;
import io.exilius.util.discord.Discord;

public class InputField implements PacketType {

	@Override
	public void processPacket(Player player, int packetType, int packetSize) {
		player.interruptActions();
		int id = player.inStream.readInteger();
		String text = player.inStream.readString();
//		if (player.getInterfaceEvent().isActive()) {
//			player.sendMessage("Please finish what you're doing.");
//			return;
//		}
		switch (id) {
		
			/**
			 * Player shop name
			 */
		case 28054:
			player.sendMessage("Setting player shop name to: " + text);
			break;
			
			/**
			 * Player shop description
			 */
		case 28055:
			player.sendMessage("Setting player shop description to: " + text);
			break;

		case 53536:
			if (text.length() > 16) {
				player.sendMessage("Custom title length can only be sixteen characters, no more.");
				return;
			}
			player.getTitles().setTemporaryCustomTitle(text);
			break;
			
		case 39806:
			Server.getDropManager().search(player, text);
			break;

		case 59527:
			if (text.length() < 25) {
				player.sendMessage("Your help request must contain 25 characters for the description.");
				return;
			}
			List<Player> staff = PlayerHandler.nonNullStream().filter(Objects::nonNull).filter(p -> p.getRights().isOrInherits(Right.HELPER)).collect(Collectors.toList());
			if (HelpDatabase.getDatabase().requestable(player)) {
				HelpDatabase.getDatabase().add(new HelpRequest(player.getDisplayName(), player.connectedFrom, text));
				if (staff.size() > 0) {
					PlayerHandler.sendMessage("[HelpDB] " + player.getDisplayName() + "" + " is requesting help, type ::helpdb to view their request.", staff);
					player.sendMessage("You request has been sent, please wait as a staff member gets back to you.");
					Discord.writetickets("[HelpDB] " + player.getDisplayName() + "" + " is requesting help, type ::helpdb ingame to view their request.", staff);
				} else {
					player.sendMessage("@red@There are no staff online to help you at this time, please be patient");
					player.sendMessage("@gre@ A message has been sent to the staff in discord");
					player.sendMessage("@dre@ do not message the staff when they get on they will review your ticket & contact you.");
					Discord.writetickets( player.getDisplayName() + "**__[HelpDB] __** ** is requesting help, type ::helpdb ingame to view their request. **");
				}
			}
			player.getPA().removeAllWindows();
			break;

		case 59507:
			if (player.getBankPin().getPinState() == BankPin.PinState.CREATE_NEW)
				player.getBankPin().create(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.UNLOCK)
				player.getBankPin().unlock(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.CANCEL_PIN)
				player.getBankPin().cancel(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.CANCEL_REQUEST)
				player.getBankPin().cancel(text);
			break;

		default:
			break;
		}
	}

}
