package io.exilius.model.entity.player.packets.npcoptions;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.achievement_diary.impl.*;
import io.exilius.content.bosses.nightmare.NightmareActionHandler;
import io.exilius.content.dailyrewards.DailyRewardsDialogue;
import io.exilius.content.dialogue.impl.IronmanNpcDialogue;
import io.exilius.content.minigames.inferno.Inferno;
import io.exilius.content.skills.Fishing;
import io.exilius.content.skills.Skill;
import io.exilius.content.skills.agility.AgilityHandler;
import io.exilius.content.skills.thieving.Thieving;
import io.exilius.content.tradingpost.Listing;
import io.exilius.model.Npcs;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.npc.NPCHandler;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.npc.pets.Probita;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerAssistant;
import io.exilius.util.Misc;

import java.util.concurrent.TimeUnit;

/*
 * @author Matt
 * Handles all 2nd options on non playable characters.
 */

public class NpcOptionTwo {

	public static void handleOption(Player player, int npcType) {
		if (Server.getMultiplayerSessionListener().inAnySession(player)) {
			return;
		}
		player.clickNpcType = 0;
		player.clickedNpcIndex = player.npcClickIndex;
		player.npcClickIndex = 0;

		/*
		 * if(Fishing.fishingNPC(c, npcType)) { Fishing.fishingNPC(c, 2, npcType);
		 * return; }
		 */
		// if (PetHandler.talktoPet(player, npcType))
		// return;
		if (PetHandler.isPet(npcType)) {
			if (PetHandler.getOptionForNpcId(npcType) == "second") {
				if (PetHandler.pickupPet(player, npcType, true))
					return;
			}
		}

		if (NightmareActionHandler.clickNpc(player, npcType, 2)) {
			return;
		}

		NPC npc = NPCHandler.npcs[player.clickedNpcIndex];
		switch (npcType) {
			case 0 :
				if(player.getRights().getPrimary().isOwner()) {
					player.getFarmingTools().loadInterfaces();
				} else {
					player.sendMessage("This is being worked on. u can note items tho.");
				}
				break;
		case Npcs.DONATOR_SHOP:
			//player.getDonationRewards().openInterface();
			player.sendMessage("coming soon");
			break;
		case DailyRewardsDialogue.DAILY_REWARDS_NPC:
			int totalReq = (player.getMode().is5x() ? 100 : 500);
			if (player.totalLevel > totalReq) {
				player.getDailyRewards().openInterface();
				player.sendMessage("@red@[Reminder] @bla@Prizes are reset every month, log in everyday to reach the best prizes!");
			} else {
				player.sendMessage("@red@ You need a total level of 500 to start collecting your daily reward!");
			}
			break;
			case 10389:
				player.getItems().openUpBank();
				break;
		case 326:
		case 327:
			   player.gfx100(1028);
			break;
			case 11289:
				player.getItems().openUpBank();
				break;
		case 1306:
			if (player.getItems().isWearingItems()) {
				player.sendMessage("You must remove your equipment before changing your appearance.");
				player.canChangeAppearance = false;
			} else {
				player.getPA().showInterface(3559);
				player.canChangeAppearance = true;
			}
			break;
		case 7240:
			player.getShops().openShop(91);
			break;
		case 8686:
			player.getShops().openShop(90);
			break;
		case 1011: //infernal gambler
		    if (player.getItems().playerHasItem(6570, 10)) {
		    	int InfernalChance = Misc.random(1000);
                if (InfernalChance == 975) {
                	player.getItems().deleteItem(6570, 10);
                    player.getItems().addItem(21295, 1);
                    player.sendMessage("@red@Congratulations! you have won a infernal cape."); 
                } else {
                	player.getItems().deleteItem(6570, 10);
        			player.sendMessage("@red@Unlucky! better luck next time.");	
        			return;
                }
        			player.sendMessage("@red@You dont have 10 firecapes to gamble.");	
		    }
		    	break;
		case 7690:
			Inferno.gamble(player);
			break;
		case Npcs.PERDU:
			player.getPerduLostPropertyShop().open(player);
			break;
		case 1909:
			player.getDH().sendDialogues(901, 1909);
			break;

		case 2989:
			player.getPrestige().openPrestige();
			break;

		case 3307:
			player.getPA().showInterface(37700);
			player.sendMessage("Set different colors for specific items for easier looting!");
			break;

		case 4321:
			int totalBlood = player.getItems().getItemAmount(13307);
			if (totalBlood >= 1) {
				player.getPA().exchangeItems(PlayerAssistant.PointExchange.BLOOD_POINTS, 13307, totalBlood);
			}
			break;

		case 822:
			player.getShops().openShop(81);
			break;

		case 7520:
			player.getDH().sendDialogues(855, 7520);
			break;

		case 6774:
			player.getShops().openShop(117);
			break;
		case 3248:
			player.getTeleportInterface().repeatLastTeleport();
			break;
		case 6773:
			if (!player.pkDistrict) {
				player.sendMessage("You cannot do this right now.");
				return;
			}
			break;

		case 4407:
			player.getShops().openShop(19);
			break;

		case 2040:
			if (player.getZulrahEvent().isActive()) {
				player.getDH().sendStatement("It seems that a zulrah instance for you is already created.",
						"If you think this is wrong then please re-log.");
				player.nextChat = -1;
				return;
			}
			player.getZulrahEvent().initialize();
			break;

		case 17: // Rug merchant 
			player.getDH().sendDialogues(838, 17);
			break;
		case 2897:
			if (player.getMode().isIronmanType()) {
				player.sendMessage("@red@You are not permitted to make use of this.");			}
			Listing.collectMoney(player);
			break;
		case 3105:
			long milliseconds = (long) player.playTime * 600;
			long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
			long hours = TimeUnit.MILLISECONDS.toHours(milliseconds - TimeUnit.DAYS.toMillis(days));
			String time = days + " days and " + hours + " hours.";
			player.getDH().sendNpcChat1("You've been playing " + Configuration.SERVER_NAME + " for " + time, 3105, "Hans");
			player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.HANS);
			break;
			case 2149:
			case 2150:
			case 2151:
			case 2148:
				if (player.getMode().isIronmanType()) {
					player.sendMessage("@red@You are not permitted to make use of this.");
					return;
				}
				Listing.openPost(player, false);
				break;
		case 3680:
			AgilityHandler.delayFade(player, "NONE", 2674, 3274, 0, "The sailor brings you onto the ship.",
					"and you end up in ardougne.", 3);
			player.getDiaryManager().getKaramjaDiary().progress(KaramjaDiaryEntry.SAIL_TO_ARDOUGNE);
			break;

		case 5906:
			Probita.cancellationOfPreviousPet(player);
			break;

		case 2180:
			player.getDH().sendDialogues(70, 2180);
			break;

			case 4642:
				player.getShops().openShop(250);
				break;
		case 401:
		case 402:
		case 405:
		case 7663:
			player.getDH().sendDialogues(3304, npcType);
			break;
		case 6797: // Nieve
			if (player.fullVoidMelee()) {
				player.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.FULL_VOID);
			}
			if (player.getSlayer().getTask().isPresent()) {
				player.getDH().sendDialogues(3305, 6797);
			} else {
				player.getDH().sendDialogues(180, 6797);
			}
			break;
		case 8761: 
			if (player.getSlayer().getTask().isPresent()) {
				player.getDH().sendDialogues(3305, 8761);
			} else {
				player.getDH().sendDialogues(10955, npcType);
			}
			break;
		case 5870: 
			if (player.getSlayer().getTask().isPresent()) {
				player.getDH().sendDialogues(3305, 8761);
			} else {
				if (player.getLevel(Skill.SLAYER) < 91) {
					player.getDH().sendStatement("You need a Slayer level of 91 to kill these.");
					return;
				}
				if (player.getSlayer().getTask().isPresent()) {
					player.getDH().sendStatement("Please finish your current task first.");		
					return;
				}
				if (!player.getItems().playerHasItem(995, 3_000_000)) {
					player.getDH().sendStatement("Come back when you've got the 3m ready.");
					return;
				}
					player.getItems().deleteItem2(995, 3_000_000);
					player.getSlayer().createNewTask(5870, true);
					player.getDH().sendNpcChat("You have been assigned "+ player.getSlayer().getTaskAmount() + " " + player.getSlayer().getTask().get().getPrimaryName());
					player.nextChat = -1;
			}
			break;

		case 5919: // Grace
			player.getShops().openShop(18);
			break;
		case Npcs.ADAM:
			IronmanNpcDialogue.giveIronmanArmour(player, npc);
			break;
		case 6747:
			player.getShops().openShop(77);
			break;
		case 2580:
			player.getPA().startTeleport(3039, 4835, 0, "modern", false);
			player.getDiaryManager().getWildernessDiary().progress(WildernessDiaryEntry.ABYSS_TELEPORT);
			player.dialogueAction = -1;
			player.teleAction = -1;
			break;
		case 3936:
			player.getDH().sendDialogues(459, -1);
			break;
		case 6970:
			player.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.PICKPOCKET_GNOME);
			player.getThieving().steal(Thieving.Pickpocket.MAN, npc);
			break;
		case 3295: //for diary

			if (Boundary.isIn(player, Boundary.ARDOUGNE)) {
				player.getThieving().steal(Thieving.Pickpocket.HERO, npc);
				player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.PICKPOCKET_ARD);
				player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.PICKPOCKET_HERO);
			}
			break;
		case 3894:
			player.getShops().openShop(26);
			break;
		case 5730:
			if(player.getMode().isIronmanType()){
				player.getThieving().steal(Thieving.Pickpocket.MASTER_FARMER, npc);
				if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
					player.getThieving().steal(Thieving.Pickpocket.MASTER_FARMER, npc);
				}
				if (Boundary.isIn(player, Boundary.DRAYNOR_BOUNDARY)) {
					player.getThieving().steal(Thieving.Pickpocket.MASTER_FARMER, npc);
					player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.PICKPOCKET_FARMER_DRAY);
				}
				player.sendMessage("@red@Restricted accounts can only steal from this npc.");
			}else{
				player.getShops().openShop(16);
			}
			break;
			case 500:
			case 501:
			case 502:
			case 503:
			case 504:
				player.getShops().openShop(16);
				break;
		case 6987:
			player.getThieving().steal(Thieving.Pickpocket.MAN, npc);
			break;
		case 3550:
			player.getThieving().steal(Thieving.Pickpocket.MENAPHITE_THUG, npc);
			break;
		case 6094:
			player.getThieving().steal(Thieving.Pickpocket.GNOME, npc);
			break;
			case 9685: // Valentin Rasputin 0
			case 9686: // Von van Von 0
			case 9687: // Vlad Bechstein 0
			case 9688: // Draconis Sanguine 0
			case 9689: // Mort Nightshade 0
			case 9690: // Vampyrus Diaemus 0
			case 9691: // Carnivus Belamorta 0
			case 9692: // Vormar Vakan 0
			case 9693: // Misdrievus Shadum 0
			case 9694: // Vlad Diaemus 0
			case 9695: // Noctillion Lugosi 0
			case 9696: // Alek Constantine 0
			case 9697: // Grigor Rasputin 0
			case 9698: // Haemas Lamescus 0
			case 9699: // Remus Kaninus 0
			case 9700: // Vallessia Dracyula 0
			case 9701: // Violetta Sanguine 0
			case 9702: // Diphylla Bechstein 0
			case 9703: // Episcula Helsing 0
			case 9704: // Vampyressa van Von 0
			case 9705: // Vallessia von Pitt 0
			case 9706: // Vonnetta Varnis 0
			case 9707: // Natalidae Shadum 0
			case 9708: // Mortina Daubenton 0
			case 9709: // Lasenna Rasputin 0
			case 9710: // Caninelle Draynar 0
			case 9711: // Valentina Diaemus 0
			case 9712: // Nakasa Jovkai 0
			case 9713: // Crimsonette van Marr 0
			case 9714: // Pipistrelle Draynar 0
			case 9715: // Lady Nadezhda Shadum 0
			case 9716: // Lord Mischa Myrmel 0
			case 9717: // Lord Alexei Jovkai 0
				player.getThieving().steal(Thieving.Pickpocket.VYRE, npc);
				break;
			case 9052: // Rhiannon 0
			case 9053: // Amrod 0
			case 9054: // Miriel 0
			case 9055: // Curufin 0
			case 9056: // Enerdhil 0
			case 9057: // Tatie 0
			case 9058: // Finduilas 0
			case 9059: // Gelmir 0
			case 9060: // Mithrellas 0
			case 9061: // Erestor 0
			case 9062: // Lindir 0
			case 9063: // Idril 0
			case 9064: // Ingwion 0
			case 9065: // Thingol 0
			case 9066: // Elenwe 0
			case 9067: // Orophin 0
			case 9068: // Vaire 0
			case 9069: // Elladan 0
			case 9070: // Guilin 0
			case 9071: // Ingwe 0
			case 9072: // Cirdan 0
			case 9073: // Glorfindel 0
			case 9074: // Aredhel 0
			case 9075: // Celegorm 0
			case 9076: // Anaire 0
			case 9077: // Maeglin 0
			case 9078: // Edrahil 0
			case 9079: // Fingon 0
			case 9080: // Salgant 0
			case 9081: // Celebrian 0
			case 9082: // Imin 0
			case 9083: // Oropher 0
			case 9084: // Fingolfin 0
			case 9085: // Mahtan 0
			case 9086: // Indis 0
			case 9087: // Iminye 0
			case 9088: // Feanor 0
			case 9089: // Saeros 0
			case 9090: // Nellas 0
			case 9091: // Rhyfel 0
			case 9092: // Gwyl 0
			case 9093: // Enilly 0
			case 9094: // Ffoni 0
			case 9095: // Ymladd 0
			case 9096: // Sadwrn 0
			case 9097: // Diol 0
			case 9098: // Ysbeid 0
			case 9099: // Cleddyf 0
			case 9100: // Saeth 0
			case 9101: // Nimrodel 0
			case 9102: // Maedhros 0
			case 9103: // Finarfin 0
			case 9104: // Gwindor 0
			case 9105: // Eldalote 0
			case 9106: // Enelye 0
			case 9107: // Nerdanel 0
			case 9108: // Nimloth 0
			case 9109: // Findis 0
			case 9110: // Earwen 0
			case 9111: // Caranthir 0
			case 9112: // Enel 0
			case 9113: // Hendor 0
			case 9114: // Galathil 0
			case 9115: // Turgon 0
			case 9116: // Lenwe 0
			case 9117: // Aranwe 0
				player.getThieving().steal(Thieving.Pickpocket.ELF, npc);
				break;
			case 3293: // Paladin 62
			case 3294: // Paladin 62
				player.getThieving().steal(Thieving.Pickpocket.PALADIN, npc);
				break;
			case 3114: // Farmer 7
			case 3243: // Farmer 7
			case 3244: // Farmer 7
				player.getThieving().steal(Thieving.Pickpocket.FARMER, npc);
				break;
		case 3106:
			player.getThieving().steal(Thieving.Pickpocket.HERO, npc);
			break;
		case 637:
			player.getShops().openShop(6);
			break;
		case 3219:
			player.getShops().openShop(113);
			break;
		case 534:
			if (Boundary.isIn(player, Boundary.VARROCK_BOUNDARY)) {
				player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.DRESS_FOR_SUCESS);
			}
			player.getShops().openShop(114);
			break;
		case 732:
			player.getShops().openShop(16);
			break;
		case 5809:
			player.getShops().openShop(20);
			break;
		case 308:
			player.getShops().openShop(80);
			break;
		case 6599:
			player.getShops().openShop(79);
			break;
		case 3341:
			PlayerAssistant.refreshSpecialAndHealth(player);
			break;
		case 403:
			player.getDH().sendDialogues(12001, -1);
			break;
		case 3216:
			player.getShops().openShop(8);
			break;
		case 2578:
			player.getDH().sendDialogues(2400, -1);
			break;
		case 3913: // BAIT + NET
			case 1518:
			case 7947:
			Fishing.attemptdata(player, 2);
			break;
		case 310:
		case 314:
		case 317:
		case 318:
		case 328:
		case 329:
		case 331:
		case 3417:
		case 6825:// BAIT + LURE
			Fishing.attemptdata(player, 6);
			break;
			case 7946:
		case 3657:
			case 1519:
		case 321:
		case 324:// SWORDIES+TUNA-CAGE+HARPOON
			Fishing.attemptdata(player, 7);
			break;
		case 1520:
		case 322:
		case 334: // NET+HARPOON
			Fishing.attemptdata(player, 10);
			break;
		case 532:
			player.getShops().openShop(47);
			break;
		case 1599:
			player.getShops().openShop(10);
			player.sendMessage("You currently have <col=a30027>" + Misc.insertCommas(player.getSlayer().getPoints()) + " </col>slayer points.");
			break;
		case 953: // Banker
		case 2574: // Banker
		case 166: // Gnome Banker
		case 1702: // Ghost Banker
		case 494: // Banker
		case 495: // Banker
		case 496: // Banker
		case 497: // Banker
		case 498: // Banker
		case 499: // Banker
		case 394:
		case 567: // Banker
		case 766:
		case 1036: // Banker
		case 1360: // Banker
		case 2163: // Banker
		case 2164: // Banker
		case 2354: // Banker
		case 2355: // Banker
		case 2568: // Banker
		case 2569: // Banker
		case 2570: // Banker
		case 2200:
			player.getPA().c.itemAssistant.openUpBank();
			break;

		case 1785:
			player.getShops().openShop(8);
			break;

		case 3218:// magic supplies
			player.getShops().openShop(6);
			break;
		case 3217:// range supplies
			player.getShops().openShop(48);
			break;
		case 3796:
			player.getShops().openShop(6);
			break;

		case 1860:
			player.getShops().openShop(6);
			break;

		case 519:
			player.getShops().openShop(7);
			break;

		case 548:
			player.getDH().sendDialogues(69, player.npcType);
			break;

		case 2258:

			break;



		case 506:
			if (player.getMode().isIronmanType()) {
				player.getShops().openShop(41);
			} else {
				player.sendMessage("You must be an Iron Man to access this shop.");
			}
			break;
		case 507:
			player.getShops().openShop(2);
			break;

		case 528:
			player.getShops().openShop(9);
			break;

		}
	}

}
