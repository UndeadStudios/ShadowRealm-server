package io.shadowrealm.model.entity.player.packets;

import io.shadowrealm.Configuration;
import io.shadowrealm.Server;
import io.shadowrealm.content.PlayerEmotes;
import io.shadowrealm.content.QuestTab;
import io.shadowrealm.content.Sawmill;
import io.shadowrealm.content.UimStorageChest;
import io.shadowrealm.content.battle_pass.BattlePassInterface;
import io.shadowrealm.content.bosses.hespori.Hespori;
import io.shadowrealm.content.cheatprevention.CheatEngineBlock;
import io.shadowrealm.content.collection_log.CollectionRewards;
import io.shadowrealm.content.combat.magic.CombatSpellData;
import io.shadowrealm.content.combat.magic.MagicRequirements;
import io.shadowrealm.content.combat.magic.NonCombatSpellData;
import io.shadowrealm.content.combat.melee.CombatPrayer;
import io.shadowrealm.content.combat.melee.QuickPrayers;
import io.shadowrealm.content.dialogue.DialogueAction;
import io.shadowrealm.content.dialogue.DialogueActionButton;
import io.shadowrealm.content.dialogue.DialogueConstants;
import io.shadowrealm.content.help.HelpDatabase;
import io.shadowrealm.content.item.lootable.LootableInterface;
import io.shadowrealm.content.itemskeptondeath.ItemsKeptOnDeathInterface;
import io.shadowrealm.content.polls.PollTab;
import io.shadowrealm.content.preset.PresetManager;
import io.shadowrealm.content.skills.construction.House;
import io.shadowrealm.content.skills.construction.RoomInterface;
import io.shadowrealm.content.skills.cooking.Cooking;
import io.shadowrealm.content.skills.crafting.*;
import io.shadowrealm.content.skills.crafting.CraftingData.tanningData;
import io.shadowrealm.content.skills.magic.EnchantBoits;
import io.shadowrealm.content.skills.slayer.SlayerRewardsInterface;
import io.shadowrealm.content.skills.smithing.Smelting;
import io.shadowrealm.content.tournaments.TourneyManager;
import io.shadowrealm.content.tradingpost.Listing;
import io.shadowrealm.content.tutorial.TutorialDialogue;
import io.shadowrealm.content.vote_panel.VotePanelInterface;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.definitions.ItemDef;
import io.shadowrealm.model.entity.npc.NPCHandler;
import io.shadowrealm.model.entity.npc.pets.PetHandler;
import io.shadowrealm.model.entity.player.*;
import io.shadowrealm.model.entity.player.mode.group.GroupIronmanBank;
import io.shadowrealm.model.entity.player.packets.dialogueoptions.*;
import io.shadowrealm.model.items.ContainerUpdate;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.model.items.bank.BankItem;
import io.shadowrealm.model.items.bank.BankTab;
import io.shadowrealm.model.multiplayersession.MultiplayerSession;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionStage;
import io.shadowrealm.model.multiplayersession.MultiplayerSessionType;
import io.shadowrealm.model.multiplayersession.duel.DuelSession;
import io.shadowrealm.model.multiplayersession.duel.DuelSessionRules;
import io.shadowrealm.model.multiplayersession.flowerpoker.FlowerPokerSession;
import io.shadowrealm.model.multiplayersession.trade.TradeSession;
import io.shadowrealm.model.shops.ShopAssistant;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.discord.DiscordIntegration;
import io.shadowrealm.util.logging.player.ClickButtonLog;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Clicking most buttons
 *
 */
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(final Player c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		int realButtonId = c.getInStream().readUnsignedWord();
		if (c.debugMessage) {
			c.sendMessage("actionbutton: " + actionButtonId + ", DialogueID: " + c.dialogueAction + ", real id: " + realButtonId);
		}
		if (c.isDead || c.getHealth().getCurrentHealth() <= 0) {
			return;
		}

		if (c.isFping()) {
			/**
			 * Cannot do action while fping
			 */
			return;
		}

		Server.getLogging().write(new ClickButtonLog(c, actionButtonId, false));

		if (c.isIdle) {
			if (c.debugMessage)
				c.sendMessage("You are no longer in idle mode.");
			c.isIdle = false;
		}

		if (TutorialDialogue.inTutorial(c)) {
			if (actionButtonId == 9154) {
				c.attemptLogout();
				return;
			}
			if (c.getModeSelection().clickButton(actionButtonId))
				return;
			dialogueOption(c, actionButtonId);
			return;
		}

		if (c.getMovementState().isLocked())
			return;
		if (c.getLootingBag().handleButton(actionButtonId)) {
			return;
		}
		if (c.getPrestige().prestigeClicking(actionButtonId)) {
			return;
		}
		if (TourneyManager.getSingleton().handleActionButtons(c, actionButtonId)) {
			return;
		}
		if (c.getExpLock().ExpLockClicking(actionButtonId)) {
			return;
		}
		if (PollTab.handleActionButton(c, actionButtonId)) {
			return;
		}
		if (c.getTeleportInter().handleInterfaceButtons(actionButtonId)) {
			return;
		}
		if (CollectionRewards.handleButton(c,realButtonId)){
			return;
		}
		if (c.getRunePouch().handleButton(actionButtonId)) {
			return;
		}
//		if (c.getInterfaceEvent().isActive()) {
//			c.getInterfaceEvent().clickButton(actionButtonId);
//			return;
//		}
		if (c.getSlayer().onActionButton(actionButtonId)) {
			return;
		}
		if (PresetManager.getSingleton().handleActionButtons(c, actionButtonId)) {
			return;
		}
		if (VotePanelInterface.handleActionButton(c, actionButtonId)) {
			return;
		}
		if (c.getCollectionLog().handleActionButtons(c, actionButtonId)) {
			return;
		}
		if (c.getQuestTab().handleHelpTabActionButton(actionButtonId)) {
			return;
		}
		if (SlayerRewardsInterface.clickButton(c, actionButtonId)) {
			return;
		}
		if (LootableInterface.button(c, actionButtonId)) {
			return;
		}
		if (c.attacking.clickWeaponTabButton(actionButtonId)) {
			return;
		}
		if (c.getModeSelection().clickButton(actionButtonId)) {
			return;
		}
		if (c.getNotificationsTab().clickButton(actionButtonId)) {
			return;
		}
		if (c.getItemUpgradeSystem().handleButtons(actionButtonId)) {
			return;
		}

//		if (LeaderboardInterface.handleButtons(c, realButtonId)) {
//			return;
//		}

		// (TeleportationInterface.actions(c, actionButtonId)) {
		// return;
		// }
		Listing.postButtons(c, actionButtonId);

		/** Drop Manager Buttons **/
		if (actionButtonId >= 128240 && actionButtonId <= 129113) {
			Server.getDropManager().select(c, actionButtonId);
			return;
		}

		if (actionButtonId == 166027) {
			c.sendMessage("[@red@Warning@bla@] If using anti-aliasing with fullscreen, you may experience lag.");
			return;
		}


		if (actionButtonId == 146130) {
			DiscordIntegration.buttonClick(c);
			return;
		}

		if (actionButtonId == 146128) {
			DiscordIntegration.disconnectUser(c);
			return;
		}

		if (actionButtonId == 146129) {
			DiscordIntegration.syncUser(c);
			return;
		}
		/*
		 * if (actionButtonId >= 175205 && actionButtonId <= 176149) { int id = 175204;
		 * if (!StaffControl.isUsingControl) { StaffControl.loadOnPlayerOptions(c);
		 * StaffControl.username = p.playerName;
		 * c.getPA().sendFrame126("<col=0xFF981F>Player: " + p.playerName, 45254); }
		 * c.setSidebarInterface(2, 45000); }
		 */
		if (actionButtonId >= 232182 && actionButtonId <= 233022) {
			HelpDatabase.getDatabase().view(c, actionButtonId);
			HelpDatabase.getDatabase().delete(c, actionButtonId);
			return;
		}
		// if (BattlestaveMaking.handleActions(c, actionButtonId)) {
		// return;
		// }
		/*
		 * if (actionButtonId >= 166035 && actionButtonId < 166035 +
		 * DropManager.AMOUNT_OF_TABLES) { Server.getDropManager().select(c,
		 * actionButtonId); return; }
		 */
		if (actionButtonId == 15040) {
			c.getDH().sendDialogues(68, -1);
			return;
		}

		if (actionButtonId == 15041) {
			Server.getDropManager().open2(c);
			return;
		}
		if (actionButtonId == 166023) {
			c.getPA().removeAllWindows();
			return;
		}
		if (BattlePassInterface.Companion.handleButton(actionButtonId, c)) return;

		c.getPestControlRewards().click(actionButtonId);
		if (c.getTitles().click(actionButtonId)) {
			return;
		}
		if (c.battlestaffDialogue) {
			BattlestaveMaking.craftBattlestave(c, actionButtonId);
		}
		if (c.craftDialogue) {
			LeatherMaking.craftLeather(c, actionButtonId);
		}
		if (c.braceletDialogue) {
			BraceletMaking.craftBracelet(c, actionButtonId);
		}
		for (tanningData t : tanningData.values()) {
			if (actionButtonId == t.getButtonId(actionButtonId)) {
				Tanning.tanHide(c, actionButtonId);
			}
		}

		DuelSession duelSession = null;
		c.getFletching().select(actionButtonId);
		GlassBlowing.glassBlowing(c, actionButtonId);
		Sawmill.handleButtons(c, actionButtonId);
		RoomInterface.clickButton(c, actionButtonId);
		EnchantBoits.EnchantButton(c, actionButtonId);
		//SplitbarkCrafting.craft(c, actionButtonId);
		PlayerEmotes.performEmote(c, actionButtonId);
		// int[] teleportButtons = { 4140, 4143, 4146, 4150, 6004, 6005, 29031,
		// 50235, 50245, 50253, 51005, 51013, 51023, 51031, 51039,
		// 117112, 117131, 117154, 117186, 117210, 118018, 118042, 118058 };
		// if (IntStream.of(teleportButtons).anyMatch(id -> actionButtonId == id)) {
		// CityTeleports.teleport(c, actionButtonId);
		// }
		QuickPrayers.clickButton(c, actionButtonId);
		dialogueOption(c, actionButtonId);
		makeOptions(c, actionButtonId);
		switch (actionButtonId) {
			case 106136:
				c.getPA().removeAllWindows();
				break;
		case 108012: // call follower
			if (c.petSummonId > 0) {
				Arrays.stream(NPCHandler.npcs).filter(npc -> npc != null && npc.spawnedBy == c.getIndex()).forEach(npc -> {
					if (PetHandler.isPet(npc.getNpcId())) {
						npc.teleport(c.getAdjacentPosition());
					}
				});
			} else {
				c.sendMessage("You don't have a pet.");
			}
			break;
			case 23132: //unmorph
				c.isMorphed = false;
				c.setSidebarInterface(0, 2423);
				c.setSidebarInterface(1, 25402); // Skilltab > 3917
				c.setSidebarInterface(2, QuestTab.INTERFACE_ID);
				c.setSidebarInterface(3, 3213);
				c.setSidebarInterface(4, 1644);
				c.setSidebarInterface(5, 15608);
				switch (c.playerMagicBook) {
					case 0:
						c.setSidebarInterface(6, 938); // modern
						break;
					case 1:
						c.setSidebarInterface(6, 838); // ancient
						break;
					case 2:
						c.setSidebarInterface(6, 29999); // ancient
						break;
				}
				c.setSidebarInterface(7, 18128);
				c.setSidebarInterface(8, 5065);
				c.setSidebarInterface(9, 5715);
				c.setSidebarInterface(10, 2449);
				c.setSidebarInterface(11, 42500); // wrench tab
				c.setSidebarInterface(12, 26041); // run tab
				if (c.playerEquipment[c.playerRing] == 7927) {
					c.getItems().deleteEquipment(c.playerEquipment[c.playerRing]);
					c.getItems().addItem(7927,1);

				}
				if (c.playerEquipment[c.playerRing] == 20017) {
					c.getItems().deleteEquipment(c.playerEquipment[c.playerRing]);
					c.getItems().addItem(20017,1);
				}
				if (c.playerEquipment[c.playerRing] == 23185) {
					c.getItems().deleteEquipment(c.playerEquipment[c.playerRing]);
					c.getItems().addItem(23185,1);
				}
				c.isNpc = false;
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.setUpdateRequired(true);
				c.appearanceUpdateRequired = true;
				break;
		case 132111: //abyssal demon
			c.getPA().startTeleport(1671, 10087, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132112: //ankou
			c.getPA().startTeleport(1642, 9996, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132113://black demon
			c.getPA().startTeleport(1721, 10085, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132114://bronze dragon
			c.getPA().startTeleport(1648, 10098, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132115://brutal black dragon
			c.getPA().startTeleport(1615, 10093, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132116://brutal blue dragon
			c.getPA().startTeleport(1635, 10075, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132117://brutal red dragon
			c.getPA().startTeleport(1614, 10074, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132118://cyclops
			c.getPA().startTeleport(1650, 10019, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132119://dagannoth
			c.getPA().startTeleport(1666, 9997, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132120://dust devil
			c.getPA().startTeleport(1715, 10025, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132121://deviant spectre
			c.getPA().startTeleport(1607, 10011, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132122://fire giant
			c.getPA().startTeleport(1632, 10058, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132123://ghost
			c.getPA().startTeleport(1662, 10024, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132124://greater demon
			c.getPA().startTeleport(1685, 10086, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132125://greater nech
			c.getPA().startTeleport(1701, 10080, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132126://hellhound
			c.getPA().startTeleport(1646, 10065, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132127://hill giant
			c.getPA().startTeleport(1653, 10037, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132128://iron dragon
			c.getPA().startTeleport(1665, 10089, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132129://king sand crab
			c.sendMessage("This NPC is currently unavailable in the Catacombs.");//done
			break;
		case 132130://magic axe
			c.getPA().startTeleport(1640, 10035, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132131://moss giant
			c.getPA().startTeleport(1689, 10033, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132132://mutated bloodveld
			c.getPA().startTeleport(1677, 10074, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132133://possessed pickaxe
			c.getPA().startTeleport(1640, 10035, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132134://steel dragon
			c.getPA().startTeleport(1608, 10054, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132135://shade
			c.getPA().startTeleport(1607, 10028, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132136://skeleton
			c.getPA().startTeleport(1639, 10046, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132137://twisted banshee
			c.getPA().startTeleport(1617, 9997, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132138://warped jelly
			c.getPA().startTeleport(1687, 9996, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 132139://dark beast
			c.getPA().startTeleport(1621, 10060, 0, "MODERN", false);//done
			c.getPA().closeAllWindows();
			break;
		case 185152:
			c.getCollectionLog().openInterface(c);
			break;
		case 19236:
			if (c.getOutStream() != null) {
				c.getOutStream().createFrame(248);
				c.getOutStream().writeWordA(54000);
				c.getOutStream().writeUnsignedWord(5065);
				c.flushOutStream();
			}
			break;
		case 108003:
			c.sendMessage("Please use the trading post for a price guide.");
			break;
		case 57057:
			c.getBH().teleportToTarget();
			break;
		case 183156:
			if (!c.inDonatorBox && !c.getUltraInterface().isActive() && !c.getNexBoxInterface().isActive() && !c.getraids1mInterface().isActive() && !c.getraids2mInterface().isActive() && !c.getSuperBoxInterface().isActive() && !c.getNormalBoxInterface().isActive() && !c.getFoeInterface().isActive() && !c.getMoneyBoxInterface().isActive()) {
				CheatEngineBlock.DonatorBoxAlert(c);
				return;
			}
			if (!(c.getSuperMysteryBox().canMysteryBox) || !(c.getNormalMysteryBox().canMysteryBox) || !(c.getPresent().canMysteryBox) ||
					!(c.getUltraMysteryBox().canMysteryBox) || !(c.getFoeMysteryBox().canMysteryBox) ||
					!(c.getYoutubeMysteryBox().canMysteryBox) || !(c.getMoneyBox().canMysteryBox)|| !(c.getRaids2mbox().canMysteryBox)|| !(c.getRaidsmbox().canMysteryBox)|| !(c.getNexBox().canMysteryBox)
			) {
				c.getPA().showInterface(47000);
				c.sendMessage("@red@[WARNING] @blu@Please do not interrupt or you @red@WILL@blu@ lose items! @red@NO REFUNDS");
				return;
			}
			for (int i = 0; i < 66; i++) {
				c.getPA().sendFrame34a(47101, -1, i, 1);
			}

			switch(c.boxCurrentlyUsing) {
			case 12789:
				c.getYoutubeMysteryBox().spin();
				break;
				case 13346: //ultra rare
					c.getUltraMysteryBox().spin();
					break;
				case 28825: //ultra rare
					c.getNexBox().spin();
					break;
			case 6199:
				c.getNormalMysteryBox().spin();
				break;
			case 6828:
				c.getSuperMysteryBox().spin();
				break;
			case 8167:
				c.getFoeMysteryBox().spin();
				break;
				case 28827:
					c.getMoneyBox().spin();
					break;
				case 6830:
					c.getRaids2mbox().spin();
					break;
				case 10025:
					c.getRaidsmbox().spin();
					break;
			}
			break;
		case 117112:
		case 117131:
		case 117154:
		case 117186:
		case 117210:
		case 118018:
		case 118042:
		case 118058:
		case 4140:
		case 4143:
		case 4146:
		case 4150:
		case 6004:
		case 6005:
		case 29031:
		case 50235:
		case 50245:
		case 50253:
		case 51005:
		case 53014:
		case 51023:
		case 51031:
		case 51039:
			c.getTeleportInter().openInterface(c);
			break;

			case 130131:
				c.getPA().removeAllWindows();
				c.sendMessage("@bla@You currently have @red@"+c.exchangePoints +"@bla@ Exchange Points.");
				c.sendMessage("@bla@Examine pets or use @red@::foepets@bla@ to learn more about the FoE pets.");
				c.getShops().openShop(171);
				break;
			case 130133:
				if (!Boundary.isIn(c, Boundary.TOURNAMENT_LOBBIES_AND_AREAS)) {
					c.getPA().removeAllWindows();
					c.getDH().sendDialogues(130135, 7456); //asks if you want to delete your item in foe or not
				}
				break;

			/*
			 * case 166056: c.getPA().showInterface(53000); break;
			 */
		case 90077:
			c.getPA().showInterface(37700);
			break;

		case 113234:// Players online
			c.sendMessage("There are currently " + PlayerHandler.getPlayerCount() + " players online.");
			break;

		case 226158:
			if (c.inBank == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inBank == false) {
				CheatEngineBlock.BankAlert(c);
				return;
			}
			c.placeHolders = !c.placeHolders;
			c.getPA().sendChangeSprite(58014, c.placeHolders ? (byte) 1 : (byte) 0);
			break;

			// Close interface for drop checker
		case 152109:
		case 128234:
		case 186122://lookback
		case 174194:
			c.getPA().removeAllWindows();
			break;

		case 242150:
		case 154078:
			c.getPA().closeAllWindows();
			break;

		case 183155:
			if (!(c.getSuperMysteryBox().canMysteryBox) || !(c.getNormalMysteryBox().canMysteryBox)   || !(c.getPresent().canMysteryBox)|| !(c.getUltraMysteryBox().canMysteryBox) || !(c.getFoeMysteryBox().canMysteryBox)
			|| !(c.getYoutubeMysteryBox().canMysteryBox)) {
				c.getPA().showInterface(47000);
				c.sendMessage("@red@[WARNING] @blu@Please do not interrupt or you @red@WILL@blu@ lose items! @red@NO REFUNDS");
				break;
			}
			c.getPA().closeAllWindows();
			break;
		case 185150:
			c.getPA().sendFrame126(Configuration.DONATOR_BENEFITS_LINK, 12000);
			break;
		case 185151:
		case 166048:
			c.getTitles().display();
			break;

		case 148137:
			c.getPA().sendInterfaceHidden(1, 38020);
			c.getPA().sendChangeSprite(38006, (byte) 1);
			c.getPA().sendChangeSprite(38007, (byte) 1);
			c.getPA().sendChangeSprite(38008, (byte) 1);
			c.sendMessage("You decided to end your donation to the well of goodwill.");
			break;


		case 19136:
			QuickPrayers.toggle(c);
			c.getPA().sendFrame36(197, 1);
			break;
		case 19137:
			for (int p = 0; p < CombatPrayer.PRAYER.length; p++) { // reset prayer glows
				if (c.prayerActive[p]) {
					c.sendMessage("You need to deactivate your active prayers before doing this.");
					return;
				}
			}
			c.isSelectingQuickprayers = true;
			c.setSidebarInterface(5, 17200);
			break;

		case 114093:
			c.setSidebarInterface(2, 29265); // 29265
			break;
		case 114083:
			c.setSidebarInterface(2, 638);
			break;

		case 74003:
			if (System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inBank == false) {
				CheatEngineBlock.BankAlert(c);
				return;
			}
			c.inPresets = true;
			//if (c.getRights().contains(Right.OWNER)) {
			PresetManager.getSingleton().open(c);
			//c.sendMessage("@red@Presets are being fixed, will be back soon."); // its sending this message even though its slashed out
			break;

			/**
			 * Dialogue Handling
			 */



		case 255255:
			c.sendMessage("You reset your experience counter.");
			c.setExperienceCounter(0L);
			break;

		case 135114:
		case 92122:
		case 118026:
			if (c.getPosition().inClanWars() || Boundary.isIn(c, Boundary.CLAN_WARS_FREE_FOR_ALL)) {
				c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
				return;
			}
			c.getBH().teleportToTarget();
			break;

		case 4135:
		case 62005:
			NonCombatSpellData.attemptDate(c, actionButtonId);
			break;

		case 55095: // Destroy item yes
			c.getPA().removeAllWindows();
			if (c.destroyItem == null || c.destroyItem.getItemId() == -1 || c.isDead)
				return;

			if (!c.getItems().isItemInInventorySlot(c.destroyItem.getItemId(), c.destroyItem.getItemSlot()))
				return;

			int itemId = c.destroyItem.getItemId();
			switch (c.destroyItem.getType()) {
				case DESTROY:
					if (ItemDef.forId(itemId).isStackable())
						return;
					c.getItems().deleteItem(c.destroyItem.getItemId(), c.destroyItem.getItemSlot(), 1);
					break;
				case DROP:
					DropItem.dropItem(c, c.destroyItem.getItemId(), c.destroyItem.getItemSlot());
					break;
				case LOW_ALCH:
				case HIGH_ALCH:
					c.usingMagic = true;
					c.getPA().alchemy(itemId, c.destroyItem.getType() == DestroyType.HIGH_ALCH ? "high" : "low");
					break;
			}

			c.destroyItem = null;
			break;
		case 55096: // Destroy item no
			c.getPA().removeAllWindows();
			c.destroyItem = null;
			break;

			/*
			 * case 191109: c.getAchievements().currentInterface = 0;
			 * c.getAchievements().drawInterface(0); break;
			 * 
			 * case 191110: c.getAchievements().currentInterface = 1;
			 * c.getAchievements().drawInterface(1); break;
			 * 
			 * case 191111: c.getAchievements().currentInterface = 2;
			 * c.getAchievements().drawInterface(2); break;
			 */

		case 250002:
		case 140244:
		case 141088:
		case 148122:
			c.getPA().closeAllWindows();
			break;
		case 24150:
			c.getPA().closeAllWindows();
			break;

		case 20174:
			if (System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inBank == false) {
				CheatEngineBlock.BankAlert(c);
				return;
			}
			c.clickDelay = System.currentTimeMillis();
			c.getPA().closeAllWindows();
			c.getBankPin().openInterface();
			break;

		case 226162:
			if (System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}

			if (!c.inBank) {
				GroupIronmanBank.bankAll(c, false);
				return;
			}

			if (c.inBank == false) {
				CheatEngineBlock.BankAlert(c);
				return;
			}
			if (c.getPA().viewingOtherBank) {
				c.getPA().resetOtherBank();
				return;
			}
			if (!c.isBanking)
				return;

			for (int slot = 0; slot < c.playerItems.length; slot++) {
				if (c.playerItems[slot] > 0 && c.playerItemsN[slot] > 0) {
					c.getItems().addToBank(c.playerItems[slot] - 1, c.playerItemsN[slot], false);
				}
			}
			c.getItems().addContainerUpdate(ContainerUpdate.INVENTORY);
			c.getItems().queueBankContainerUpdate();
			c.getItems().resetTempItems();
			break;

		case 226170:
			if (System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}

			if (!c.inBank) {
				GroupIronmanBank.bankAll(c, true);
				return;
			}

			if (c.inBank == false) {
				CheatEngineBlock.BankAlert(c);
				return;
			}
			if (c.getPA().viewingOtherBank) {
				c.getPA().resetOtherBank();
				return;
			}
			if (!c.isBanking)
				return;
			if (c.getBankPin().requiresUnlock()) {
				c.isBanking = false;
				c.getBankPin().open(2);
				return;
			}
			if (!c.getMode().isBankingPermitted()) {
				c.sendMessage("You cannot do that with a storage chest.");
				return;
			}
			for (int slot = 0; slot < c.playerEquipment.length; slot++) {
				if (c.playerEquipment[slot] > 0 && c.playerEquipmentN[slot] > 0) {
					if (c.getItems().addEquipmentToBank(c.playerEquipment[slot], slot, c.playerEquipmentN[slot],
							false)) {
						c.getItems().equipItem(-1, 0, slot);
					} else {
						c.sendMessage("Your bank is full.");
						break;
					}
				}
			}
			c.getPA().resetAutocast();
			c.getItems().addContainerUpdate(ContainerUpdate.INVENTORY);
			c.getItems().queueBankContainerUpdate();
			c.getItems().resetTempItems();
			break;

		case 226186:
		case 226198:
		case 226209:
		case 226220:
		case 226231:
		case 226242:
		case 226253:
		case 227008:
		case 227019:
			if (c.inBank == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (!c.isBanking) {
				c.getPA().removeAllWindows();
				return;
			}
			if (c.getBankPin().requiresUnlock()) {
				c.isBanking = false;
				c.getBankPin().open(2);
				return;
			}
			int tabId = actionButtonId == 226186 ? 0
					: actionButtonId == 226198 ? 1
							: actionButtonId == 226209 ? 2
									: actionButtonId == 226220 ? 3
											: actionButtonId == 226231 ? 4
													: actionButtonId == 226242 ? 5
															: actionButtonId == 226253 ? 6
																	: actionButtonId == 227008 ? 7
																			: actionButtonId == 227019 ? 8 : -1;
			if (tabId <= -1 || tabId > 8)
				return;
			c.previousTab = c.getBank().getCurrentBankTab().getTabId();
			BankTab tab = c.getBank().getBankTab(tabId);
			if (tab.getTabId() == c.getBank().getCurrentBankTab().getTabId())
				return;
			if (tab.size() <= 0 && tab.getTabId() != 0) {
				c.sendMessage("Drag an item into the new tab slot to create a tab.");
				return;
			}
			c.getBank().setCurrentBankTab(tab);
			c.getPA().c.itemAssistant.openUpBank();
			break;

		case 226197:
		case 226208:
		case 226219:
		case 226230:
		case 226241:
		case 226252:
		case 227007:
		case 227018:
			if (c.getPA().viewingOtherBank) {
				c.getPA().resetOtherBank();
				return;
			}
			if (!c.isBanking) {
				c.getPA().removeAllWindows();
				return;
			}
			if (c.getBankPin().requiresUnlock()) {
				c.isBanking = false;
				c.getBankPin().open(2);
				return;
			}
			tabId = actionButtonId == 226197 ? 1
					: actionButtonId == 226208 ? 2
							: actionButtonId == 226219 ? 3
									: actionButtonId == 226230 ? 4
											: actionButtonId == 226241 ? 5
													: actionButtonId == 226252 ? 6
															: actionButtonId == 227007 ? 7
																	: actionButtonId == 227018 ? 8 : -1;
			tab = c.getBank().getBankTab(tabId);
			if (tab == null || tab.getTabId() == 0 || tab.size() == 0) {
				c.sendMessage("You cannot collapse this tab.");
				return;
			}
//			if (tab.size() + c.getBank().getBankTab()[0].size() >= Configuration.BANK_TAB_SIZE) {
//				c.sendMessage("You cannot collapse this tab. The contents of this tab and your");
//				c.sendMessage("main tab are greater than " + Configuration.BANK_TAB_SIZE + " unique items.");
//				return;
//			}
			for (BankItem item : tab.getItems()) {
				if (!c.getMode().isBankingPermitted()) {
					if (!UimStorageChest.isStorageItem(c, item.getId())) {
						c.sendMessage("Your game mode prohibits use of the banking system.");
						return;
					}
				}
				c.getBank().getBankTab()[0].add(item);
			}
			tab.getItems().clear();
			c.getBank().openTab(0);
			c.getPA().c.itemAssistant.openUpBank();
			break;

		case 226185:
		case 226196:
		case 226207:
		case 226218:
		case 226229:
		case 226240:
		case 226251:
		case 227006:
		case 227017:
			if (c.getPA().viewingOtherBank) {
				c.getPA().resetOtherBank();
				return;
			}
			if (!c.isBanking) {
				c.getPA().removeAllWindows();
				return;
			}
			if (c.getBankPin().requiresUnlock()) {
				c.isBanking = false;
				c.getBankPin().open(2);
				return;
			}
			tabId = actionButtonId == 226185 ? 0
					: actionButtonId == 226196 ? 1
							: actionButtonId == 226207 ? 2
									: actionButtonId == 226218 ? 3
											: actionButtonId == 226229 ? 4
													: actionButtonId == 226240 ? 5
															: actionButtonId == 226251 ? 6
																	: actionButtonId == 227006 ? 7
																			: actionButtonId == 227017 ? 8 : -1;
			tab = c.getBank().getBankTab(tabId);
			long value = 0;
			if (tab == null || tab.size() == 0)
				return;
			for (BankItem item : tab.getItems()) {
				long tempValue = item.getId() - 1 == 995 ? 1 : ShopAssistant.getItemShopValue(item.getId() - 1);
				value += tempValue * item.getAmount();
			}

			c.sendMessage("<col=255>The total net worth of tab " + tab.getTabId() + " is </col><col=600000>"
					+ Misc.insertCommas(String.valueOf(value)) + " gp</col>.");

			if (tabId == 0) {
				value = 0;
				for (BankTab tabIt : c.getBank().getBankTab()) {
					for (BankItem item : tabIt.getItems()) {
						long tempValue = item.getId() - 1 == 995 ? 1 : ShopAssistant.getItemShopValue(item.getId() - 1);
						value += tempValue * item.getAmount();
					}
				}

				c.sendMessage("<col=255>The total net worth of all tabs is </col><col=600000>"
						+ Misc.insertCommas(String.valueOf(value)) + " gp</col>.");
			}
			break;

		case 22024:
		case 86008:
			c.getPA().c.itemAssistant.openUpBank();
			break;
		case 140162:
			c.getPA().removeAllWindows();
			break;
			/** End Achievement Interface - Grant **/
			// case 113248: //Spawntab
			// c.getPA().sendFrame171(0, 36200);
			// c.setSidebarInterface(2, 36200); //638
			// break;
			// case 141112:
			// c.setSidebarInterface(2, 638); //638
			// c.getPA().sendFrame171(1, 36200);
			// c.getPA().sendFrame126("Name", 36202);
			// c.getPA().sendFrame126("Amount", 36205);
			// break;

		case 164034:
		case 164035:
		case 164036:
		case 164037:
			int index = actionButtonId - 164034;
			String[] removed = c.getSlayer().getRemoved();
			if (index < 0 || index > removed.length - 1) {
				return;
			}
			if (removed[index].isEmpty()) {
				c.sendMessage("There is no task in this slot that is being blocked.");
				return;
			}
			removed[index] = "";
			c.getSlayer().setRemoved(removed);
			c.getSlayer().updateCurrentlyRemoved();
			break;

		case 164028:
			c.getSlayer().cancelTask();
			break;
		case 164029:
			c.getSlayer().removeTask();
			break;

		case 162030:
		case 164018:
		case 160042:
			c.getPA().removeAllWindows();
			break;
		case 251246:
			c.getPA().removeAllWindows();
			for (int i = 0; i < 12; i++) {
				c.getPA().itemOnInterface(-1, -1, 64503, i);
			}
			break;

		case 114121:
			c.getDiaryManager().getVarrockDiary().display();
			break;
		case 114122:
			c.getDiaryManager().getArdougneDiary().display();
			break;
		case 114123:
			c.getDiaryManager().getDesertDiary().display();
			break;
		case 114124:
			c.getDiaryManager().getFaladorDiary().display();
			break;
		case 114125:
			c.getDiaryManager().getFremennikDiary().display();
			break;
		case 114126:
			c.getDiaryManager().getKandarinDiary().display();
			break;
		case 114127:
			c.getDiaryManager().getKaramjaDiary().display();
			break;
		case 114128:
			c.getDiaryManager().getLumbridgeDraynorDiary().display();
			break;
		case 114129:
			c.getDiaryManager().getMorytaniaDiary().display();
			break;
		case 114130:
			c.getDiaryManager().getWesternDiary().display();
			break;
		case 114134:
			c.getDiaryManager().getWildernessDiary().display();
			break;
		case 40196:
			break;
		case 39243:
			c.forcedChat("My KDR is [Kills: "+c.killcount+ " Deaths "+c.deathcount+"]");
			break;
		case 39241:
			/*long milliseconds = (long) c.playTime * 600;
			long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
			long hours = TimeUnit.MILLISECONDS.toHours(milliseconds - TimeUnit.DAYS.toMillis(days));
			String time = days + " days, " + hours + " hours.";
			c.forcedChat("I've played Sovark for a total of : " + time);*/
			break;

		case 113240:
			//c.forcedChat("I currently have: " + c.pkp + " PK Points.");
			break;
		case 113241:
			//	c.forcedChat("I currently have: " + c.donatorPoints + " Donator Points.");
			break;
		case 113242:
			//c.forcedChat("I currently have: " + c.votePoints + " Vote Points.");
			break;
		case 113243:
			//c.forcedChat("I currently have: " + c.pcPoints + " PC Points.");
			break;
		case 185154: // view the forums
			c.getPA().sendFrame126(Configuration.WEBSITE, 12000);
			break;
		case 185155: // Discord
			c.getPA().sendFrame126(Configuration.DISCORD_INVITE, 12000);
			break;
		case 185156: // Store
			c.getPA().sendFrame126(Configuration.STORE_LINK, 12000);
			break;
		case 185157: // in-game rules
			c.getPA().sendFrame126(Configuration.RULES_LINK, 12000);
			break;
		case 185158: // forum guides
			c.getPA().sendFrame126(Configuration.GUIDES_LINK, 12000);
			break;
		case 113244:
			c.forcedChat("I currently have: " + c.getArenaPoints() + " Mage Arena Points.");
			break;
		case 113246:
			c.sendMessage("@blu@I currently have: " + c.getSlayer().getConsecutiveTasks() + " consecutive slayer tasks.");
			break;
		case 39244:
			break;
		case 98246:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 0;
			c.getPA().sendConfig(261, 1);
			c.sendMessage("You select Attack");
			break;
		case 98247:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 2;
			c.getPA().sendConfig(261, 2);
			c.sendMessage("You select Strength");
			break;
		case 98248:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 4;
			c.getPA().sendConfig(261, 3);
			c.sendMessage("You select Ranged");
			break;
		case 98249:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 6;
			c.getPA().sendConfig(261, 4);
			c.sendMessage("You select Magic");
			break;
		case 98250:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 1;
			c.getPA().sendConfig(261, 5);
			c.sendMessage("You select Defence");
			break;
		case 99037:
			c.getPA().closeAllWindows();
			break;
		case 98251:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 3;
			c.getPA().sendConfig(261, 6);
			c.sendMessage("You select Hitpoints");
			break;
		case 98252:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 5;
			c.getPA().sendConfig(261, 7);
			c.sendMessage("You select Prayer");
			break;
		case 98253:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 16;
			c.getPA().sendConfig(261, 8);
			c.sendMessage("You select Agility");
			break;
		case 98254:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 15;
			c.getPA().sendConfig(261, 9);
			c.sendMessage("You select Herblore");
			break;
		case 98255:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 17;
			c.getPA().sendConfig(261, 10);
			c.sendMessage("You select Thieving");
			break;
		case 99000:
			if (c.inLamp == false && c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 12;
			c.getPA().sendConfig(261, 11);
			c.sendMessage("You select Crafting");
			break;
		case 99001:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 20;
			c.getPA().sendConfig(261, 12);
			c.sendMessage("You select Runecrafting");
			break;
		case 99009:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 18;
			c.getPA().sendConfig(261, 20);
			c.sendMessage("You select Slayer");
			break;
		case 99010:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 19;
			c.getPA().sendConfig(261, 21);
			c.sendMessage("You select Farming");
			break;
		case 99002:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 14;
			c.getPA().sendConfig(261, 13);
			c.sendMessage("You select Mining");
			break;
		case 99003:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 13;
			c.getPA().sendConfig(261, 14);
			c.sendMessage("You select Smithing");
			break;
		case 99004:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 10;
			c.getPA().sendConfig(261, 15);
			c.sendMessage("You select Fishing");
			break;
		case 99005:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 7;
			c.getPA().sendConfig(261, 16);
			c.sendMessage("You select Cooking");
			break;
		case 99006:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 11;
			c.getPA().sendConfig(261, 17);
			c.sendMessage("You select Firemaking");
			break;
		case 99007:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 8;
			c.getPA().sendConfig(261, 18);
			c.sendMessage("You select Woodcutting");
			break;
		case 99008:
			if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.inLamp == false) {
				CheatEngineBlock.ExperienceAbuseAlert(c);
				return;
			}
			c.antiqueItemResetSkillId = 9;
			c.getPA().sendConfig(261, 19);
			c.sendMessage("You select Fletching");
			break;
			case 99012:
				if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
					return;
				}
				if (c.inLamp == false) {
					CheatEngineBlock.ExperienceAbuseAlert(c);
					return;
				}
				c.antiqueItemResetSkillId = 21;
				c.getPA().sendConfig(261, 23);
				c.sendMessage("You select Hunter.");
				break;
			case 99011:
				if (c.inLamp == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
					return;
				}
				if (c.inLamp == false) {
					CheatEngineBlock.ExperienceAbuseAlert(c);
					return;
				}
				c.antiqueItemResetSkillId = 22;
				c.sendMessage("You select Construction.");
				break;
		case 99013:
			if (c.inLamp == false && Hespori.activeKronosSeed == false && System.currentTimeMillis() - c.clickDelay <= 2200) {
				return;
			}
			if (c.getItems().playerHasItem(13148)) {
				if (c.getItems().freeEquipmentSlots() != 14) {
					c.sendMessage("Please take off all equipment before doing this.");
					return;
				}
				if (c.antiqueItemResetSkillId == 3) {
					c.sendMessage("@red@You cannot reset your hitpoints level.");
					return;
				}
				c.playerLevel[c.antiqueItemResetSkillId] = 1;
				c.playerXP[c.antiqueItemResetSkillId] = c.getPA().getXPForLevel(1) + 1;
				c.getPA().refreshSkill(c.antiqueItemResetSkillId);
				c.getPA().setSkillLevel(c.antiqueItemResetSkillId, c.playerLevel[c.antiqueItemResetSkillId], c.playerXP[c.antiqueItemResetSkillId]);
				c.getItems().deleteItem(13148, 1);
				c.getPA().sendConfig(261, 0);
				c.sendMessage("@red@You have reset your skill of choice.");
				c.getPA().closeAllWindows();
				return;
			}

			if (!c.getItems().playerHasItem(2528) && (!c.getItems().playerHasItem(Items.DARK_RELIC) && (!c.getItems().playerHasItem(13148)))) {
				return;
			}


			if (c.usingLamp) {
				if (c.getItems().playerHasItem(2528) && c.normalLamp && !c.antiqueLamp) {
					c.usingLamp = false;
					c.inLamp = false;

					if (!c.getMode().isOsrs() && !c.getMode().is5x()) {
						c.getPA().addSkillXP(125000, c.antiqueItemResetSkillId, true);

					} else {
						c.getPA().addSkillXP(12500, c.antiqueItemResetSkillId, true);
						c.sendMessage("As a restricted game mode you receive less xp.");
					}

					c.getItems().deleteItem(2528, 1);
					c.sendMessage("The lamp mysteriously vanishes...");

					c.getPA().closeAllWindows();
				}

				if (c.getItems().playerHasItem(Items.DARK_RELIC) && c.normalLamp && !c.antiqueLamp) {
					c.usingLamp = false;
					c.inLamp = false;

					if (!c.getMode().isOsrs() && !c.getMode().is5x()) {
						c.getPA().addSkillXP(125_000, c.antiqueItemResetSkillId, true);
					} else {
						c.getPA().addSkillXP(12_500, c.antiqueItemResetSkillId, true);
					}

					c.getItems().deleteItem(Items.DARK_RELIC, 1);
					c.sendMessage("The dark relic mysteriously vanishes...");
					c.sendMessage("...and you gain some experience!");
					c.getPA().closeAllWindows();
				}
				if (c.getItems().playerHasItem(27299)) {
					c.usingLamp = false;
					c.inLamp = false;
					c.getPA().addSkillXP(1_225_000, c.antiqueItemResetSkillId, true);
					c.getItems().deleteItem(27299, 1);
					c.sendMessage("The antique Lamp mysteriously vanishes...");
					c.sendMessage("...and you gain some experience!");
					c.getPA().closeAllWindows();
				}


			}
			break;

			/*
			 * case 28172: if (c.expLock == false) { c.expLock = true; c.sendMessage(
			 * "Your experience is now locked. You will not gain experience.");
			 * c.getPA().sendFrame126( "@whi@EXP: @gre@LOCKED", 7340); } else { c.expLock =
			 * false; c.sendMessage(
			 * "Your experience is now unlocked. You will gain experience.");
			 * c.getPA().sendFrame126( "@whi@EXP: @gre@UNLOCKED", 7340); } break;
			 */
		case 28215:
			if (c.getSlayer().getTask().isPresent()) {
				c.sendMessage("You do not have a task, please talk with a slayer master!");
			} else {
				c.forcedChat("I must slay another " + c.getSlayer().getTaskAmount() + " "
						+ c.getSlayer().getTask().get().getPrimaryName() + ".");
			}
			break;
		case 185149:
			c.getPA().showInterface(39500);
			break;
		case 15147:// Bronze, 1
			Smelting.startSmelting(c, "bronze", "ONE", "FURNACE");
			break;
		case 15146:// Bronze, 5
			Smelting.startSmelting(c, "bronze", "FIVE", "FURNACE");
			break;
		case 10247:// Bronze, 10
			Smelting.startSmelting(c, "bronze", "TEN", "FURNACE");
			break;
		case 9110:// Bronze, 28
			Smelting.startSmelting(c, "bronze", "ALL", "FURNACE");
			break;
		case 15151:// Iron, 1
			Smelting.startSmelting(c, "iron", "ONE", "FURNACE");
			break;
		case 15150:// Iron, 5
			Smelting.startSmelting(c, "iron", "FIVE", "FURNACE");
			break;
		case 15149:// Iron, 10
			Smelting.startSmelting(c, "iron", "TEN", "FURNACE");
			break;
		case 15148:// Iron, 28
			Smelting.startSmelting(c, "iron", "ALL", "FURNACE");
			break;
		case 15155:// silver, 1
			Smelting.startSmelting(c, "silver", "ONE", "FURNACE");
			break;
		case 15154:// silver, 5
			Smelting.startSmelting(c, "silver", "FIVE", "FURNACE");
			break;
		case 15153:// silver, 10
			Smelting.startSmelting(c, "silver", "TEN", "FURNACE");
			break;
		case 15152:// silver, 28
			Smelting.startSmelting(c, "silver", "ALL", "FURNACE");
			break;
		case 15159:// steel, 1
			Smelting.startSmelting(c, "steel", "ONE", "FURNACE");
			break;
		case 15158:// steel, 5
			Smelting.startSmelting(c, "steel", "FIVE", "FURNACE");
			break;
		case 15157:// steel, 10
			Smelting.startSmelting(c, "steel", "TEN", "FURNACE");
			break;
		case 15156:// steel, 28
			Smelting.startSmelting(c, "steel", "ALL", "FURNACE");
			break;
		case 15163:// gold, 1
			Smelting.startSmelting(c, "gold", "ONE", "FURNACE");
			break;
		case 15162:// gold, 5
			Smelting.startSmelting(c, "gold", "FIVE", "FURNACE");
			break;
		case 15161:// gold, 10
			Smelting.startSmelting(c, "gold", "TEN", "FURNACE");
			break;
		case 15160:// gold, 28
			Smelting.startSmelting(c, "gold", "ALL", "FURNACE");
			break;
		case 29017:// mithril, 1
			Smelting.startSmelting(c, "mithril", "ONE", "FURNACE");
			break;
		case 29016:// mithril, 5
			Smelting.startSmelting(c, "mithril", "FIVE", "FURNACE");
			break;
		case 24253:// mithril, 10
			Smelting.startSmelting(c, "mithril", "TEN", "FURNACE");
			break;
		case 16062:// mithril, 28
			Smelting.startSmelting(c, "mithril", "ALL", "FURNACE");
			break;
		case 29022:// addy, 1
			Smelting.startSmelting(c, "adamant", "ONE", "FURNACE");
			break;
		case 29021:// addy, 5
			Smelting.startSmelting(c, "adamant", "FIVE", "FURNACE");
			break;
		case 29019:// addy, 10
			Smelting.startSmelting(c, "adamant", "TEN", "FURNACE");
			break;
		case 29018:// addy, 28
			Smelting.startSmelting(c, "adamant", "ALL", "FURNACE");
			break;
		case 29026:// rune, 1
			Smelting.startSmelting(c, "rune", "ONE", "FURNACE");
			break;
		case 29025:// rune, 5
			Smelting.startSmelting(c, "rune", "FIVE", "FURNACE");
			break;
		case 29024:// rune, 10
			Smelting.startSmelting(c, "rune", "TEN", "FURNACE");
			break;
		case 29023:// rune, 28
			Smelting.startSmelting(c, "rune", "ALL", "FURNACE");
			break;

			/*
			 * case 58025: case 58026: case 58027: case 58028: case 58029: case 58030: case
			 * 58031: case 58032: case 58033: case 58034:
			 * c.getBankPin().pinEnter(actionButtonId); break;
			 */

		case 53152:
			Cooking.cookItem(c, c.cookingItem, 1, c.cookingObject);
			break;
		case 53151:
			Cooking.cookItem(c, c.cookingItem, 5, c.cookingObject);
			break;
		case 53150:
			Cooking.cookItem(c, c.cookingItem, 10, c.cookingObject);
			break;
		case 53149:
			Cooking.cookItem(c, c.cookingItem, 28, c.cookingObject);
			break;
			case 99182:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.attackSkill = true;
				} else {
					c.getSI().attackComplex(1);
					c.getSI().selected = 0;
				}
				break;
			case 99185:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.strengthSkill = true;
				} else {
					c.getSI().strengthComplex(1);
					c.getSI().selected = 1;
				}
				break;
			case 99188:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.defenceSkill = true;
				} else {
					c.getSI().defenceComplex(1);
					c.getSI().selected = 2;
				}
				break;
			case 99191:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.rangeSkill = true;
				} else {
					c.getSI().rangedComplex(1);
					c.getSI().selected = 3;
				}
				break;
			case 99194:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.prayerSkill = true;
				} else {
					c.getSI().prayerComplex(1);
					c.getSI().selected = 4;
				}
				break;
			case 99201:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.mageSkill = true;
				} else {
					c.getSI().magicComplex(1);
					c.getSI().selected = 5;
				}
				break;
			case 99183:
				if (c.getPosition().inClanWarsSafe()) {
					c.getPA().sendEnterAmount(0);
					c.healthSkill = true;
				} else {
					c.getSI().hitpointsComplex(1);
					c.getSI().selected = 7;
				}
				break;
			case 99204: // runecrafting
				c.getSI().runecraftingComplex(1);
				c.getSI().selected = 6;
				break;
			case 99186: // agility
				c.getSI().agilityComplex(1);
				c.getSI().selected = 8;
				break;
			case 99189: // herblore
				c.getSI().herbloreComplex(1);
				c.getSI().selected = 9;
				break;
			case 99192: // theiving
				c.getSI().thievingComplex(1);
				c.getSI().selected = 10;
				break;
			case 99195: // crafting
				c.getSI().craftingComplex(1);
				c.getSI().selected = 11;
				break;
			case 99202: // fletching
				c.getSI().fletchingComplex(1);
				c.getSI().selected = 12;
				break;
			case 99205:// slayer
				c.getSI().slayerComplex(1);
				c.getSI().selected = 13;
				break;
			case 99184:// mining
				c.getSI().miningComplex(1);
				c.getSI().selected = 14;
				break;
			case 99187: // smithing
				c.getSI().smithingComplex(1);
				c.getSI().selected = 15;
				break;
			case 99190: // fishing
				c.getSI().fishingComplex(1);
				c.getSI().selected = 16;
				break;
			case 99193: // cooking
				c.getSI().cookingComplex(1);
				c.getSI().selected = 17;
				break;
			case 99196: // firemaking
				c.getSI().firemakingComplex(1);
				c.getSI().selected = 18;
				break;
			case 99203: // woodcut
				c.getSI().woodcuttingComplex(1);
				c.getSI().selected = 19;
				break;
			case 99206: // farming
				c.getSI().farmingComplex(1);
				c.getSI().selected = 20;
				break;
			case 99207: // farming
				c.getSI().hunterComplex(1);
				c.getSI().selected = 21;
				break;
			case 99208: // farming
				c.getSI().ConstructionComplex(1);
				c.getSI().selected = 22;
				break;
		case 34142: // tab 1
			c.getSI().menuCompilation(1);
			break;

		case 34119: // tab 2
			c.getSI().menuCompilation(2);
			break;

		case 34120: // tab 3
			c.getSI().menuCompilation(3);
			break;

		case 34123: // tab 4
			c.getSI().menuCompilation(4);
			break;

		case 34133: // tab 5
			c.getSI().menuCompilation(5);
			break;

		case 34136: // tab 6
			c.getSI().menuCompilation(6);
			break;

		case 34139: // tab 7
			c.getSI().menuCompilation(7);
			break;

		case 34155: // tab 8
			c.getSI().menuCompilation(8);
			break;

		case 34158: // tab 9
			c.getSI().menuCompilation(9);
			break;

		case 34161: // tab 10
			c.getSI().menuCompilation(10);
			break;

		case 59199: // tab 11
			c.getSI().menuCompilation(11);
			break;

		case 59202: // tab 12
			c.getSI().menuCompilation(12);
			break;
		case 59203: // tab 13
			c.getSI().menuCompilation(13);
			break;
			// case 73113: // tab 13
			// c.getSI().menuCompilation(21);
			// break;\
		case 94051:
		case 93202:
		case 89061:
		case 24010:
		case 93225:
		case 93233:
		case 93209:
		case 93240:
			case 6226:
			if (c.autoRet == 0) {
				c.autoRet = 1;
			} else {
				c.autoRet = 0;
			}
			c.getPA().sendFrame36(172, c.autoRet);
			break;
		case 108005:
/*			  if (Server.getMultiplayerSessionListener().inAnySession(c)) { return; }
			  c.getPA().showInterface(15106); c.getItems().writeBonus();*/
			c.getPA().showInterface(15106);
			break;
		case 108006: // items kept on death
			ItemsKeptOnDeathInterface.open(c);
			break;

		case 59004:
			c.getPA().removeAllWindows();
			break;

		case 26010:
			c.getPA().resetAutocast();
			break;
		case 166012:
			//c.setSidebarInterface(11, 24543);
			break;
			case 95245:
				//c.setSidebarInterface(11, 42500);
				break;
			case 95238:
				/*House house;
				if(c.inConstruction()) {
					if (c.getHouse() == null) {
						house = House.load(c);
					} else {
						house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c);
					}
					house.setBuildMode(true);
					c.setHouse(house);
					house.enter(c);
				}*/
				c.getPA().sendConfig(261, 1);
				break;
			case 95225:
				/*if(c.inConstruction()) {
					if (c.getHouse() == null) {
						house = House.load(c);
					} else {
						house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c);
					}
					house.setBuildMode(false);
					c.setHouse(house);
					house.enter(c);
				}*/
				c.getPA().sendConfig(261, 0);
				break;
			case 95239:
				/*if(c.inConstruction()) {
					if (c.getHouse() == null) {
						house = House.load(c);
					} else {
						house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c);
					}
					c.setHouse(house);
					house.kickAllGuests();
				}*/
				break;
			case 95237:
				/*if(c.inConstruction()) {
					if (c.getHouse() == null) {
						house = House.load(c);
					} else {
						house = c.getHouse().getOwner().equals(c) ? c.getHouse() : House.load(c);
					}
					c.setHouse(house);
					c.getPA().movePlayer(3080, 3492);
					house.leave(c);
				}*/
				break;
		case 166013:
			c.getPA().sendFrame126(Configuration.STORE_LINK, 12000);
			break;
		case 7212:
			c.setSidebarInterface(0, 328);
			c.getPA().resetAutocast();
			break;
		case 94047:
		case 1093:
		case 1094:
		case 1097:
			if (c.autocasting) {
				c.getPA().resetAutocast();
				if (c.debugMessage) {
					c.sendMessage("Reset autocast");
				}
			} else {
				c.getPA().openGameframeTab(6);
				c.sendMessage("Right-click spells to autocast.");
			}
			break;
			/* VENG */
		case 118098:
			c.getPA().castVengeance();
			break;

        // Dueling
		case 26065:
		case 26040:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.FORFEIT);
			break;

		case 26066: // no movement
		case 26048:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			if (!duelSession.getRules().contains(DuelSessionRules.Rule.FORFEIT)) {
				duelSession.toggleRule(c, DuelSessionRules.Rule.FORFEIT);
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_MOVEMENT);
			break;

		case 26069: // no range
		case 26042:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_RANGE);
			break;

		case 26070: // no melee
		case 26043:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_MELEE);
			break;

		case 26071: // no mage
		case 26041:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_MAGE);
			break;

		case 26072: // no drinks
		case 26045:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_DRINKS);
			break;

		case 26073: // no food
		case 26046:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_FOOD);
			break;

		case 26074: // no prayer
		case 26047:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_PRAYER);
			break;

		case 26076: // obsticals
		case 26075:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.OBSTACLES);
			break;

		case 2158: // fun weapons
		case 2157:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			if (duelSession.getRules().contains(DuelSessionRules.Rule.WHIP_AND_DDS)) {
				duelSession.toggleRule(c, DuelSessionRules.Rule.WHIP_AND_DDS);
				return;
			}
			if (!DuelSessionRules.Rule.WHIP_AND_DDS.getReq().get().meets(c)
					&& !duelSession.getRules().contains(DuelSessionRules.Rule.NO_SPECIAL_ATTACK)) {
				c.getPA().sendString("You must have a whip and dragon dagger to select this.", 6684);
				return;
			}
			if (!DuelSessionRules.Rule.WHIP_AND_DDS.getReq().get().meets(duelSession.getOther(c))) {
				c.getPA().sendString("Your opponent does not have a whip and dragon dagger.", 6684);
				return;
			}
			if (duelSession.getStage().getStage() != MultiplayerSessionStage.OFFER_ITEMS) {
				c.sendMessage("You cannot change rules whilst on the second interface.");
				return;
			}
			duelSession.getRules().reset();
			for (DuelSessionRules.Rule rule : DuelSessionRules.Rule.values()) {
				index = rule.ordinal();
				if (index == 3 || index == 8 || index == 10 || index == 14) {
					continue;
				}
				duelSession.toggleRule(c, rule);
			}
			break;

		case 30136: // sp attack
		case 30137:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_SPECIAL_ATTACK);
			break;

		case 53245: // no helm
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_HELM);
			break;

		case 53246: // no cape
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_CAPE);
			break;

		case 53247: // no ammy
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_AMULET);
			break;

		case 53249: // no weapon
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_WEAPON);
			break;

		case 53250: // no body
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_BODY);
			break;

		case 53251: // no shield
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_SHIELD);
			break;

		case 53252: // no legs
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_LEGS);
			break;

		case 53255: // no gloves
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_GLOVES);
			break;

		case 53254: // no boots
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_BOOTS);
			break;

		case 53253: // no rings
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_RINGS);
			break;

		case 53248: // no arrows
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (Objects.isNull(duelSession)) {
				return;
			}
			duelSession.toggleRule(c, DuelSessionRules.Rule.NO_ARROWS);
			break;

		case 26018:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c,
					MultiplayerSessionType.DUEL);
			if (System.currentTimeMillis() - c.getDuel().getLastAccept() < 1000) {
				return;
			}
			c.getTrade().setLastAccept(System.currentTimeMillis());
			if (Objects.nonNull(duelSession) && duelSession instanceof DuelSession) {
				duelSession.accept(c, MultiplayerSessionStage.OFFER_ITEMS);
			}
			break;

		case 25120:
			duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
			if (System.currentTimeMillis() - c.getDuel().getLastAccept() < 1000) {
				return;
			}
			c.getTrade().setLastAccept(System.currentTimeMillis());
			if (Objects.nonNull(duelSession) && duelSession instanceof DuelSession) {
				duelSession.accept(c, MultiplayerSessionStage.CONFIRM_DECISION);
			}
			break;

		case 4169: // god spell charge
			c.usingMagic = true;
			if (MagicRequirements.checkMagicReqs(c, 48, true)) {
				if (System.currentTimeMillis() - c.godSpellDelay < 300000L) {
					c.sendMessage("You still feel the charge in your body!");
				} else {
					c.godSpellDelay = System.currentTimeMillis();
					c.sendMessage("You feel charged with a magical power!");
					c.gfx100(CombatSpellData.MAGIC_SPELLS[48][3]);
					c.startAnimation(CombatSpellData.MAGIC_SPELLS[48][2]);
					c.usingMagic = false;
				}
			}
			break;

			/*
			 * case 152: c.isRunning2 = !c.isRunning2; int frame = c.isRunning2 == true ? 1
			 * : 0; c.getPA().sendFrame36(173,frame); break;
			 */
		case 101229:
			System.out.println("Setting cape: " + c.playerEquipment[Player.playerCape]);
			if (c.playerEquipment[Player.playerCape] == -1) {
				c.sendMessage("You must be wearing a skillcape in order to do this emote.");
				return;
			}
			PlayerEmotes.performSkillcapeAnimation(c, new GameItem(c.playerEquipment[Player.playerCape]));
			break;
		case 166011:
		case 152:
			if (Boundary.isIn(c, Boundary.ICE_PATH) || c.getRunEnergy() < 1) {
				c.updateRunningToggled(false);
				return;
			}
			c.updateRunningToggled(!c.isRunningToggled());
			break;

		case 48176:
			c.acceptAid = !c.acceptAid;
			c.getPA().setConfig(427, c.acceptAid ? 1 : 0);
			break;

		case 9154:
			c.attemptLogout();
			break;
			case 104036:
				c.getPA().closeAllWindows();
				break;
		case 226146:
			if (!c.swaping) {
				c.sendMessage("You can't disable this, please select the other option!");
				c.getPA().sendConfig(304, 1);
				return;
			}
			c.getPA().sendConfig(305, 0);
			c.swaping = false;
			break;

		case 73241:
			if (c.swaping) {
				c.sendMessage("You can't disable this, please select the other option!");
				c.getPA().sendConfig(305, 1);
				return;
			}
			c.getPA().sendConfig(304, 0);
			c.swaping = true;
			break;

		case 226154:
			c.getPA().sendConfig(117, 0);
			c.takeAsNote = false;
			break;

		case 73245:
			c.getPA().sendConfig(116, 0);
			c.takeAsNote = true;
			break;
			// home teleports
		case 4171:
		case 117048:
		case 84237:
		case 75010:
			if(c.wildLevel > Configuration.NO_TELEPORT_WILD_LEVEL) {
				c.sendMessage("You can't teleport above " + Configuration.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
				return;
			}
			c.getPA().spellTeleport(3086, 3488, 0, true);
			//c.getPA().showInterface(51000);
			//c.getTeleport().selection(c, 0);
			break;
		case 50056:
			if (c.homeTeleport >= 1 && c.homeTeleport <= 10) {
				return;
			}
			c.getPA().spellTeleport(3086, 3488, 0, true);
			break;



		case 166056:
			c.getPA().showInterface(53000);
			break;

		case 166028:
			c.getPA().showInterface(39000);
			break;
			/**
			 * Prayers *
			 */
		case 21233: // thick skin
			CombatPrayer.activatePrayer(c, 0);
			break;
		case 21234: // burst of str
			CombatPrayer.activatePrayer(c, 1);
			break;
		case 21235: // charity of thought
			CombatPrayer.activatePrayer(c, 2);
			break;
		case 77100: // range
			CombatPrayer.activatePrayer(c, 3);
			break;
		case 77102: // mage
			CombatPrayer.activatePrayer(c, 4);
			break;
		case 21236: // rockskin
			CombatPrayer.activatePrayer(c, 5);
			break;
		case 21237: // super human
			CombatPrayer.activatePrayer(c, 6);
			break;
		case 21238: // improved reflexes
			CombatPrayer.activatePrayer(c, 7);
			break;
		case 21239: // hawk eye
			CombatPrayer.activatePrayer(c, 8);
			break;
		case 21240:
			CombatPrayer.activatePrayer(c, 9);
			break;
		case 21241: // protect Item
			CombatPrayer.activatePrayer(c, 10);
			break;
		case 77104: // 26 range
			CombatPrayer.activatePrayer(c, 11);
			break;
		case 77106: // 27 mage
			CombatPrayer.activatePrayer(c, 12);
			break;
		case 21242: // steel skin
			CombatPrayer.activatePrayer(c, 13);
			break;
		case 21243: // ultimate str
			CombatPrayer.activatePrayer(c, 14);
			break;
		case 21244: // incredible reflex
			CombatPrayer.activatePrayer(c, 15);
			break;
		case 21245: // protect from magic
			CombatPrayer.activatePrayer(c, 16);
			break;
		case 21246: // protect from range
			CombatPrayer.activatePrayer(c, 17);
			break;
		case 21247: // protect from melee
			CombatPrayer.activatePrayer(c, 18);
			break;
		case 77109: // 44 range
			CombatPrayer.activatePrayer(c, 19);
			break;
		case 77111: // 45 mystic
			CombatPrayer.activatePrayer(c, 20);
			break;
		case 2171: // retrui
			CombatPrayer.activatePrayer(c, 21);
			break;
		case 2172: // redem
			CombatPrayer.activatePrayer(c, 22);
			break;
		case 2173: // smite
			CombatPrayer.activatePrayer(c, 23);
			break;
		case 153233: // preserve
			CombatPrayer.activatePrayer(c, 24);
			break;
		case 77113: // chiv
			CombatPrayer.activatePrayer(c, 25);
			break;
		case 77115: // piety
			CombatPrayer.activatePrayer(c, 26);
			break;
		case 153236: // rigour
			CombatPrayer.activatePrayer(c, 27);
			break;

		case 153239: // augury
			CombatPrayer.activatePrayer(c, 28);
			break;
		case 13092://accept trade
			//if (!Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
			//	c.sendMessage("You are not trading!");
			//	return;
			//}

			MultiplayerSession multiSession = Server.getMultiplayerSessionListener().getMultiplayerSession(c);

			if (Objects.isNull(multiSession)) {
				return;
			}

			if (Boundary.isIn(c, Boundary.TOURNAMENT_LOBBIES_AND_AREAS)) {
				c.sendMessage("You cannot do this right now.");
				return;
			}
			if (System.currentTimeMillis() - c.getTrade().getLastAccept() < 1000) {
				return;
			}

			if (multiSession instanceof TradeSession || multiSession instanceof FlowerPokerSession) {
				c.getTrade().setLastAccept(System.currentTimeMillis());
				multiSession.accept(c, MultiplayerSessionStage.OFFER_ITEMS);
			}

			/*if (Boundary.isIn(c, Boundary.TOURNAMENT_LOBBIES_AND_AREAS)) {
				c.sendMessage("You cannot do this right now.");
				return;
			}
			if (System.currentTimeMillis() - c.getTrade().getLastAccept() < 1000) {
				return;
			}

			c.getTrade().setLastAccept(System.currentTimeMillis());
			Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.TRADE).accept(c, MultiplayerSessionStage.OFFER_ITEMS);*/
			break;

		case 13218://accept trade 2
			multiSession = Server.getMultiplayerSessionListener().getMultiplayerSession(c);

			if (Objects.isNull(multiSession)) {
				return;
			}

			if (Boundary.isIn(c, Boundary.TOURNAMENT_LOBBIES_AND_AREAS)) {
				c.sendMessage("You cannot do this right now.");
				return;
			}
			if (System.currentTimeMillis() - c.getTrade().getLastAccept() < 1000) {
				return;
			}

			if (multiSession instanceof TradeSession || multiSession instanceof FlowerPokerSession) {
				c.getTrade().setLastAccept(System.currentTimeMillis());
				multiSession.accept(c, MultiplayerSessionStage.CONFIRM_DECISION);
			}

			/*if (!Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
				c.sendMessage("You are not trading!");
				return;
			}
			if (Boundary.isIn(c, Boundary.TOURNAMENT_LOBBIES_AND_AREAS)) {
				c.sendMessage("You cannot do this right now.");
				return;
			}
			if (System.currentTimeMillis() - c.getTrade().getLastAccept() < 1000) {
				return;
			}
			c.getTrade().setLastAccept(System.currentTimeMillis());
			Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.TRADE).accept(c,
					MultiplayerSessionStage.CONFIRM_DECISION);*/
			break;

		case 125011: // Click agree
			if (!c.ruleAgreeButton) {
				c.ruleAgreeButton = true;
				c.getPA().sendFrame36(701, 1);
			} else {
				c.ruleAgreeButton = false;
				c.getPA().sendFrame36(701, 0);
			}
			break;
		case 125003:// Accept
			if (c.ruleAgreeButton) {
				c.getPA().showInterface(3559);
			} else if (!c.ruleAgreeButton) {
				c.sendMessage("You need to agree before you can carry on.");
			}
			break;
		case 125006:// Decline
			c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
			break;
			/* End Rules Interface Buttons */
			/* Player Options */
		case 74176:
		case 166055:
			if (!c.mouseButton) {
				c.mouseButton = true;
				c.getPA().sendFrame36(500, 1);
				c.getPA().sendFrame36(170, 1);
			} else if (c.mouseButton) {
				c.mouseButton = false;
				c.getPA().sendFrame36(500, 0);
				c.getPA().sendFrame36(170, 0);
			}
			break;
		case 74184:
		case 166046:
		case 3189:
			if (!c.splitChat) {
				c.splitChat = true;
				c.getPA().sendFrame36(502, 1);
				c.getPA().sendFrame36(287, 1);
			} else {
				c.splitChat = false;
				c.getPA().sendFrame36(502, 0);
				c.getPA().sendFrame36(287, 0);
			}
			break;
		case 74180:
		case 166045:
			if (!c.chatEffects) {
				c.chatEffects = true;
				c.getPA().sendFrame36(501, 1);
				c.getPA().sendFrame36(171, 0);
			} else {
				c.chatEffects = false;
				c.getPA().sendFrame36(501, 0);
				c.getPA().sendFrame36(171, 1);
			}
			break;
		case 74188:
		case 166010:
			if (!c.acceptAid) {
				c.acceptAid = true;
				c.getPA().sendFrame36(503, 1);
				c.getPA().sendFrame36(427, 1);
			} else {
				c.acceptAid = false;
				c.getPA().sendFrame36(503, 0);
				c.getPA().sendFrame36(427, 0);
			}
			break;
		case 74192:
			c.updateRunningToggled(!c.isRunningToggled());
			break;
		case 74201:// brightness1
			c.getPA().sendFrame36(505, 1);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 1);
			break;
		case 74203:// brightness2
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 1);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 2);
			break;

		case 74204:// brightness3
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 1);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 3);
			break;

		case 74205:// brightness4
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 1);
			c.getPA().sendFrame36(166, 4);
			break;
		case 74206:// area1
			c.getPA().sendFrame36(509, 1);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74207:// area2
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 1);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74208:// area3
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 1);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74209:// area4
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 1);
			break;
		case 43092:
			c.startAnimation(0x558);
			break;
			/*
			 * case 72254: //c.startAnimation(3866); break; /* END OF EMOTES
			 */

		case 24017:
			c.getPA().resetAutocast();
			// c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
			c.getItems().sendWeapon(c.playerEquipment[Player.playerWeapon]
			);
			// c.setSidebarInterface(0, 328);
			// c.setSidebarInterface(6, c.playerMagicBook == 0 ? 938 :
			// c.playerMagicBook == 1 ? 838 : 938);
			break;
		}
		if (c.isAutoButton(actionButtonId)) {
			c.assignAutocast(actionButtonId);
		}
	}

	private static void dialogueOption(Player c, int buttonId) {
		switch (buttonId) {
			case 9167:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_1);
					return;
				}
				OptionHandler.handleOptions(c, 1);
				ThreeOptions.handleOption1(c);
				break;
			case 9168:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_2);
					return;
				}
				OptionHandler.handleOptions(c, 2);
				ThreeOptions.handleOption2(c);
				break;
			case 9169:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_3);
					return;
				}
				OptionHandler.handleOptions(c, 3);
				ThreeOptions.handleOption3(c);
				break;
			case 9157:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_1);
					return;
				}
				OptionHandler.handleOptions(c, 1);
				TwoOptions.handleOption1(c);
				break;

			case 9158:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_2);
					return;
				}
				OptionHandler.handleOptions(c, 2);
				TwoOptions.handleOption2(c);
				break;

			case 9178:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_1);
					return;
				}
				OptionHandler.handleOptions(c, 1);
				FourOptions.handleOption1(c);
				break;

			case 9179:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_2);
					return;
				}
				OptionHandler.handleOptions(c, 2);
				FourOptions.handleOption2(c);
				break;

			case 9180:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_3);
					return;
				}
				OptionHandler.handleOptions(c, 3);
				FourOptions.handleOption3(c);
				break;

			case 9181:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_4);
					return;
				}
				OptionHandler.handleOptions(c, 4);
				FourOptions.handleOption4(c);
				break;

			case 9190:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_1);
					return;
				}
				OptionHandler.handleOptions(c, 1);
				FiveOptions.handleOption1(c);
				break;

			case 9191:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_2);
					return;
				}
				OptionHandler.handleOptions(c, 2);
				FiveOptions.handleOption2(c);
				break;

			case 9192:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_3);
					return;
				}
				OptionHandler.handleOptions(c, 3);
				FiveOptions.handleOption3(c);
				break;

			case 9193:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_4);
					return;
				}
				OptionHandler.handleOptions(c, 4);
				FiveOptions.handleOption4(c);
				break;

			case 9194:
				if (c.getDialogueBuilder() != null) {
					c.getDialogueBuilder().getCurrent().handleAction(c, DialogueAction.OPTION_5);
					return;
				}
				OptionHandler.handleOptions(c, 5);
				FiveOptions.handleOption5(c);
				break;
		}
	}



	private static void makeOptions(Player player, int actionButtonId) {
		if (player.getDialogueBuilder() != null) {
			Optional<DialogueActionButton> dialogueActionOptional = DialogueConstants.BUTTONS.stream().filter(button
					-> Arrays.stream(button.getButtonIds()).anyMatch(buttonId -> actionButtonId == buttonId)).findFirst();
			dialogueActionOptional.ifPresent(action -> player.getDialogueBuilder().dispatchAction(action.getAction()));
		}
	}
}
