package io.exilius.model.entity.npc.data;

import io.exilius.content.bosses.Skotizo;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.bosses.hydra.HydraStage;
import io.exilius.content.bosses.wildypursuit.FragmentOfSeren;
import io.exilius.content.bosses.wildypursuit.TheUnbearable;
import io.exilius.content.bosses.zulrah.Zulrah;
import io.exilius.content.minigames.inferno.InfernoWaveData;
import io.exilius.content.minigames.warriors_guild.AnimatedArmour;
import io.exilius.model.Npcs;
import io.exilius.model.entity.npc.NPCHandler;

public class DeathAnimation extends NPCHandler {

    public static int handleEmote(int npcId) {
        if (AnimatedArmour.isAnimatedArmourNpc(npcId)) {
            return 836;
        }
        switch (npcId) {
            case 11246:
                return 9280;
            case 9118:
                return 6092;
            case 8703:
                return 5321;
            case Npcs.BABY_BLUE_DRAGON:
            case Npcs.BABY_BLUE_DRAGON_2:
            case Npcs.BABY_BLUE_DRAGON_3:
            case Npcs.BABY_RED_DRAGON:
            case Npcs.BABY_RED_DRAGON_2:
            case Npcs.BABY_RED_DRAGON_3:
            case Npcs.BABY_RED_DRAGON_4:
            case Npcs.BABY_GREEN_DRAGON:
            case Npcs.BABY_GREEN_DRAGON_2:
            case Npcs.BABY_GREEN_DRAGON_3:
            case Npcs.BABY_BLACK_DRAGON:
            case Npcs.BABY_BLACK_DRAGON_2:
            case Npcs.BABY_BLACK_DRAGON_3:
                return 28;
            case 5823:
            case 5824:
            case 5825:
            case 5826:
            case 5828:
            case 5829:
            case 5830:
            case 5831:
                return 287;
            case 1047:
                return 4233;
            case 2098:
            case 2099:
            case 2100:
            case 2101:
            case 2102:
            case 2103:
            case 7261:
            case 10374:
            case 10375:
            case 10376:
            case 11195:
                return 4653;
            case Npcs.THE_MIMIC_2:
                return 8310;
            case Npcs.SARACHNIS:
            case Npcs.SPAWN_OF_SARACHNIS:
            case Npcs.SPAWN_OF_SARACHNIS_2:
                return 8318;
            case Npcs.REVENANT_CYCLOPS:
                return 4653;
            case 11293:
                return 9192;
            case 8194:
                return 3262;
            case Npcs.BRYOPHYTA:
            case Npcs.MOSS_GIANT:
            case Npcs.MOSS_GIANT_2:
            case Npcs.MOSS_GIANT_3:
            case Npcs.MOSS_GIANT_4:
            case Npcs.MOSS_GIANT_5:
            case Npcs.MOSS_GIANT_6:
            case Npcs.MOSS_GIANT_7:
            case Npcs.MOSS_GIANT_8:
                return 4659;
            case 2837: // unicorn
            case 2849:
                return 6377;
            case Npcs.HESPORI:
                return Hespori.DEATH_ANIMATION;
            case 936:
                return 287;
            case 8612: //Drake
                return 8277;
            case 8614: // Sulphur Lizard
                return 8284;
            case 8611: //wyrm
                return 8269;
            case 2916:
            case 2917:
                return 1580;
            /**
             * Theatre of blood npcs start
             */
            case 8360:
                return 8094;
            /**
             * Theatre of blood npcs end
             */

            //Wyverns
            case 7792: //longtailed
            case 7793: //taloned
            case 7794://spitting
            case 7795://ancient
                return 7652;

            /**
             * Mage Arena 2 npcs start
             */
            case 7860:
                return 7843; //7841 attack mage //7840 melee
            case 7859:
                return 7850;
            case 7858:
                return 7854;
            /**
             * Mage arena 2 npcs end
             */
            case 852:
                return 5726;
            case 9026://rat
            case 2510:
            case 4501:
            case 2511:
            case 2512:
                return 4935;
            case 9027://spider
            case 3018:
            case 3017:
            case 2477:
            case 3023:
                return 5329;
            case 9028://bat
                return 4917;
            case 9029://unicorn
                return 6377;
            case 9030://scorpion
            case 3024:
            case 2479:
            case 2480:
                return 6256;
            case Npcs.BLACK_BEAR:
            case Npcs.GRIZZLY_BEAR:
            case Npcs.GRIZZLY_BEAR_2:
            case Npcs.ANGRY_BEAR:
            case Npcs.REANIMATED_BEAR:
            case Npcs.ANGRY_BEAR_2:
            case Npcs.CRYSTALLINE_BEAR:
                return 4929;
            case 9033://dragon
                return 92;
            case 9034://dark beast
                return 2733;
            case 8062://vorkath crab
                return -1;
            case 9293:
                return 8501;
            case 2241:
            case 132:
            case 3049:
            case 3050:
            case 3286:
            case 3287:
                return 167;
            //Inferno Npcs
            case TheUnbearable.NPC_ID:
                return 1759; //7841 attack mage //7840 melee
            case InfernoWaveData.JAL_NIB:
                return 7576;
            case InfernoWaveData.JAL_MEJRAH:
                return 7580;
            case InfernoWaveData.JAL_AK:
                return 7584;
            case InfernoWaveData.JAL_AKREK_KET:
                return 7584;
            case InfernoWaveData.JAL_AKREK_MEJ:
            case InfernoWaveData.JAL_AKREK_XIL:
                return 7584;
            case InfernoWaveData.JAL_IMKOT:
                return 7599;
            case InfernoWaveData.JAL_XIL:
                return 7606;
            case InfernoWaveData.JAL_ZEK:
                return 7613;
            case InfernoWaveData.JALTOK_JAD:
                return 7594;
            case InfernoWaveData.TZKAL_ZUK:
            case InfernoWaveData.JAL_MEJJAK:
                return 2865;
            case 8622:
                return HydraStage.ENRAGED.getDeathAnimation();
            case 8781:
            case 6707:
                return -1;

            case 7527:
            case 7528:
            case 7529:
                return 7432;
            case 1605:
                return 714;
            case 1606:
                return 131;
            case 1607:
                return 5321;
            case 1608:
                return 843;
            case 1609:
                return 68;
            case 8028:
                return 7949;
            case 7566:
                return 7415;
            case 7573:
                return 7196;
            case 7563:
                return 7426;
            case 7585:
                return 6949;
            case Skotizo.SKOTIZO_ID:
                return 4624;
            case Skotizo.REANIMATED_DEMON:
                return 67;
            case 7604: // Skeletal mystic
            case 7605: // Skeletal mystic
            case 7606: // Skeletal mystic
                return 5491;
            case 2085:
                return 4673;
            case 7544: // Tekton
                return 7495;
            case 5916:
                return 4521;
            case 5890:
                return 7100;
            case 1635:
            case 1636:
            case 1637:
            case 1638:
            case 1639:
            case 1640:
            case 1641:
            case 1642:
            case 1643:
            case 1654:
            case 7302:
            case 8748:
                return 6615;
            case 6604:
                return 307;
            case 6594:
                return 7146;
            case 5816:
                return 5784;

            case 5744:
                return 5098;
            case 5762:
                return 5397;

            /* Hydra */
            case 8609:
                return 8264;

            //xeric minigame
            case 7547:
            case 7548:
                return 258;
            case 7559:
            case 7560:
                return 2304;
            case 7576:
            case 7577:
            case 7578:
            case 7579:
                return 1314;
            case 7586:
                return 1580;
            case 7538:
                return 7459;
            case 7531:
                return 7459;
            case 7543:
                return 7495;

            case 955:
            case 957:
            case 959:
                return 6229;
            case 9021:
            case 9022: //range
            case 9023: //mage
                return 8422;
            case 9024:
                return 8422;
            case 7144:
            case 7145:
            case 7146:
                return 7229;
            case 458:
                return 2778;
            case 3544:
                return 278;
            case 1267:
                return 2021;
            case 2064:
                return 1013;
            case 2067:
                return 6791;

            case 5129:
                return 6502;
            case FragmentOfSeren.NPC_ID:
                return FragmentOfSeren.DEATH_ANIMATION;
            case FragmentOfSeren.CRYSTAL_WHIRLWIND:
                return FragmentOfSeren.CRYSTAL_DEATH_ANIMATION;
            case 2593:
                return 6537;

            case 963:
                return 6229;

            case 965:
                return 6233;

            case 5548:
            case 5549:
            case 5550: // Hunter birds
            case 5551:
            case 5552:
                return 6780;

            case 1505: // Hunter ferret
                return 5188;

            case 2910:
            case 2911: // Hunter chinchompas
            case 2912:
                return 5182;

            case 5867:
            case 5868:
            case 5869:
                return 4503;

            case 5862:
                return 4495;

            case 465: // Skeletal wyverns
                return 2987;

            case 6766: // Lizardman shaman
            case 6768:
            case 6914:
            case 6915:
            case 6916:
            case 6917:
            case 6918:
            case 6919:
                return 7196;

            case 6615: // Scorpia
            case 6616:
            case 6617:
                return 6256;
            case 1277:
            case 2527:
            case 85:
                return 5542;
            case 2528:
            case 2529:
            case 2530:
            case 2531:
            case 2532:
            case 2533:
            case 2534:
            case 1786:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
                return 5534;
            case 2145:
            case 6462: // Zombies
            case 6465:
            case 26:
            case 27:
            case 28:
            case 29:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 30:
                return 5575;
            case 320: // Dark core
                return 1688;
            case 6367: // Evil chicken
                return 2301;
            case 6369: // Agrith-na-na
                return 3503;
            case 6370: // Flambeed
                return 1752;
            case 6372: // Dessourt
                return 3509;

            case 2084: // Fire giant
                return 4668;
            case 6373:
            case 6374:
            case 6375:
            case 6376:
            case 6377:
            case 6378:
                return 1342;
            case 970: // Dagannoths
            case 975:
            case 983:
            case 984:
            case 985:
            case 986:
            case 987:
            case 988:
                return 1342;
            case 5944: // Rock lobster
                return 2862;
            case 5938:// Wallasalki
                return 2367;

            case 319: // Corporeal Beast
                return 1676;

            case 406: // Cave Crawler
            case 7389:
                return 228;
            case 481:
                return 6081;
            case 498: // Smoke Devil
            case 499:
            case 7406:
                return 3849;
            case 1610:
            case 1611:
            case 6618:
            case 6619:
                return 836;
            case 1612:
                return 196;
            case 2805:
                return 5851;

            case 2211:
            case 2212:
                return 2304;
            case 2234:
                return 2938;
            case 2235:
                return 4653;
            case 2237:
                return 4321;
            case 2242:
            case 2243:
            case 2244:
                return 4321;
            case 2245:
                return 6182;
            case 3133:
                return 6567;
            case 3134:
                return 172;
            case 3135:
                return 6537;
            case 3137:
                return 2304;
            case 3138:
                return 1553;
            case 3139:
            case 3140:
                return 1580;
            case 3141:
                return 4302;
            case 3160:
            case 3159:
            case 3161:
                return 2304;
            case 3162:
                return 6979;
            case 3163:
            case 3164:
            case 3165:
            case 3166:
            case 3167:
            case 3168:
            case 3174:
            case 3169:
                return 6959;

            case 2042:
            case 2043:
            case 2044:
                return -1;

            case Zulrah.SNAKELING:
                return 2408;
            case 6601:
            case 6602:
                return 65535;
            case 1739:
            case 1740:
            case 1741:
            case 1742:
                return 65535;
            case 1734:
                return 3894;
            case 1724:
                return 3922;
            case 1709:
                return 3910;
            case 1704:
                return 3917;
            case 1699:
                return 3901;
            case 1689:
                return 3888;
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
                return 5491;
            case 437: // Jelly
            case 7277:
                return -1;
            case 7400:
            case 7399:
                return 1587;
            case 3209: // Cave Horror
            case 7401:
                return 4233;
            case 411: // Kurask
                return 1513;
            case 7405:
                return 4233;
            case Npcs.SKELETON_HELLHOUND:
                return 6564;
            case 6611:
            case 6612:
                return 5491;
            case 6610:
                return 5329;
            case 494:
            case 492:
                return 3987;
            case 871:// Ogre Shaman
                return 361;
            case 6609: // Castillo
                return 4929;
            case 5399:
                return 5569;
            case 438:
            case 439:
            case 440:
            case 441:
            case 442: // Tree spirit
            case 443:
                return 97;
            case 391:
            case 392:
            case 393:
            case 394:
            case 395:// river troll
            case 396:
                return 287;
            case 421: // Rockslug
            case 7392:
                return 1568;
            case 420:
            case 422:
                // case 423:
                // begin new updates
            case 7258:
                return 5542;
            case 2834: // bats
                return 4917;
            // end
            case 2206:
                return 6377;
            case 2207:
                return 7034;
            case 2216:
            case 2217:
            case 2218:
                return 6156;
            case 3129:
            case 3130:
            case 3131:
            case 3132:
                return 67;
            case 6142:
                return 1;
            case 6143:
                return 1;
            case 6144:
                return 1;
            case 6145:
                return 1;
            case 5935:// sand crab
                return 1314;
            case 100:// rc
                return 1314;
            case 414:// Battle Mage
            case 7272:
            case 7391:
                return 1524;
            case 7390:
                return -1;
            case 3742:// Ravager
            case 3743:
            case 3744:
            case 3745:
            case 3746:
                return 3916;
            case 3772:// Brawler
            case 3773:
            case 3774:
            case 3775:
            case 3776:
                return 3894;
            case 5779: // giant mole
                return 3313;
            case 135:
            case 104:
            case 7935:
                return 6567;
            case 2205:
            case 11958:
                return 6968;
            case 2215:
                return 7020;
            case 11278://nex anims death
                return 7020;
            case 6267:
                return 357;
            case 6268:
                return 2938;
            case 6269:
                return 4653;
            case 6270:
                return 4653;
            case 6271:
                return 4321;
            case 6272:
                return 4321;
            case 6273:
                return 4321;
            case 6274:
            case 7937:
                return 4321;
            case 2790:
            case 2791:
            case 2793:
            case 2795:
            case 5842:
            case 6401:
            case 10598:
                return 5784;
            case 2792:
            case 2794:
            case 2801:
                return 5851;
            case 2463:
            case Npcs.OBOR:
                return 4653;
            case 1459:
                return 1404;
            case 2559:
            case 2560:
            case 2561:
                return 6956;
            case 3121:
                return 2630;
            case 3118:
                return 2627;
            case 2167:
                return 2607;
            case 3116:
                return 2620;
            case 3120:
                return 2627;
            case 3123:
                return 2638;
            case 2746:
                return 2638;
            case 3125:
                return 2646;
            case 3127:
                return 2654;
            case 3777:
            case 3778:
            case 3779:
            case 3780:
                return -1;
            case 6342:
                return 5898;
            case 2054:
                return 3147;
            case 2035: // spider
                return 146;
            case 2033: // rat
                return 141;
            case 484: // Bloodveld 76
            case 485: // Bloodveld 76
            case 486: // Bloodveld 76
            case 487: // Bloodveld 76
                return 2073;
            case 9756:
            case 9757:
            case 9758:
            case 9759:
            case 9760:
            case 9761:
            case 9762:
            case 9763:
                return 8723;
            case 655:
            case 657://axes
            case 659://axes
            case 662://axes
            case 664://axes
            case 666://axes
            case 3028://unarmed
            case 3029:
            case 3030:
            case 3031:
            case 3032:
            case 3033:
            case 3034:
            case 3035:
            case 3036:
            case 3037:
            case 3038:
            case 3039:
            case 3040:
            case 5192:
            case 5193:
            case 2484:
            case 2485:
            case 2486:
            case 2487:
            case 2488:
            case 2489:
            case 5195:
            case 5196:
            case 5197:
            case 5198:
            case 5199:
            case 5200:
            case 5201:
            case 5202:
            case 5203:
            case 5205:
            case 5206:
            case 5207:
            case 5208:
                return 6182;
            case 2481:
            case 2482:
            case 2483:
                return 4265;
            case 2490:
            case 2491:
            case 106:
            case 9031://wolf
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
                return 6558;
            case 2498:
            case 2499:
            case 2500:
                return 1187;
            case 2501:
            case 2502:
            case 2503:
            case 2504:
            case 2505:
            case 2506:
            case 2507:
            case 2508:
            case 2509:
                return 5569;
            case 2492:
                return 2707;
            case 1767:
            case 397:
            case 1766:
            case 1768:
            case 81: // cow
                return 5851;
            case 41: // chicken
                return 57;
            case 1338: // dagannoth
            case 1340:
            case 1342:
                return 1342;
            case 2265:
            case 2266:
            case 2267:
                return 2856;
            case 2086: // Ice giant
            case 2087: // Ice giant
            case 2088: // Ice giant
            case 7878: // Ice giant 67
            case 7879: // Ice giant 67
            case 7880: // Ice giant 67
            case 2089: // Ice giant
                return 4659;

            case 2841: // ice warrior
                return 843;
            case 751:// Zombies!!
                return 302;
            case 1626:
            case 1627:
            case 1628:
            case 1629:
            case 1630:
            case 1631:
            case 1632: // turoth!
                return 1597;
            case 417: // basilisk
            case 7395:
                return 1548;
            case 1653: // hand
                return 1590;
            case 2005: // Lesser demon 82
            case 2006: // Lesser demon 82
            case 2007: // Lesser demon 82
            case 2008: // Lesser demon 82
            case 2018: // Lesser demon 82
            case 2025: // Greater demon 92
            case 2026: // Greater demon 92
            case 2027: // Greater demon 92
            case 2028: // Greater demon 92
            case 2029: // Greater demon 92
            case 2030: // Greater demon 92
            case 2031: // Greater demon 92
            case 2032: // Greater demon 92
            case 7244: // Greater demon 101
            case 7245: // Greater demon 100
            case 7246: // Greater demon 113
            case 7247: // Lesser demon 87
            case 7248: // Lesser demon 94
            case 3982: // Lesser demon 82
            case 7656: // Lesser demon 82
            case 7657: // Lesser demon 82
            case 7664: // Lesser demon 82
            case 7865: // Lesser demon 94
            case 7866: // Lesser demon 94
            case 7867: // Lesser demon 94
            case 7936:
            case 1432:// Black Demon
            case 5874: // Black demon 172
            case 5875: // Black demon 172
            case 5876: // Black demon 172
            case 5877: // Black demon 172
            case 2048: // Black demon 172
            case 7242: // Black demon 184
            case 7243: // Black demon 178
            case 2049: // Black demon 172
            case 2050: // Black demon 172
            case 2051: // Black demon 172
            case 2052: // Black demon 172
            case 240: // Black demon 172
            case 1472:// jungle demon
            case 7871: // Greater demon 104
            case 7872: // Greater demon 104
            case 7873: // Greater demon 104
            case 7874: // Black demon 188
            case 7875: // Black demon 188
            case 7876: // Black demon 188
                return 67;
            case 1153:
                return 361;
            case 6:// abby spec
            case 7279: // Deviant spectre
            case 7403:
                return 1508;
            case 51:// baby drags
            case 52:
            case 1589:
            case 3376:
                return 28;
            case 1543: // Gargoyle
            case 7407:
                return 1518;
            case 1619:
            case 7276:
            case 7398:
            case 7397:
                return 1553;
            case 2095://ogre
            case 2096:
            case 2233:
            case 4368:
            case 4369:
            case 4370:
            case 4371:
            case 4372:
            case 4373:
                return 361;
            case 419: // Cockatrice
            case 1621:
            case 7393:
                return 1563;
            case 4005: // Dark Beast
            case 7938:
                return 2732;
            case 7409:
                return 2733;
            case 415: // Abby Demon
            case 7410:
                return 1538;
            case 423: // Dust devil
            case 7404:
                return 1558;
            case 11: // Nechryael
            case 7411:
            case 7278:
                return 1530;
            case 435: // Pyrefiend
            case 1634:
            case 7932:
            case 7394:
                return 1580;
            case 7293:
                return 1563;
            case 448: // Crawling hand
            case 449:
            case 450:
            case 451:
            case 452:
            case 453:
            case 454:
            case 455:
            case 456:
            case 457:
                return 1590;
            case 102:
                return 313;
            case 412:
                // case 2834:
                return 36;
            case 122:
            case 123:
            case 7933:
                return 167;
            case 2827:
                return 4917;
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 3021:
                return 5329;
            case 118:
            case 291:
                return 102;
            case 3019://spider
                return 6251;
            case 290:
                return 102;
            case 239:// drags
            case 7940:
                return 92;
            case 247: // Red dragon
            case 248: // Red dragon
            case 249: // Red dragon
            case 250: // Red dragon
            case 251: // Red dragon
            case 252: // Black dragon
            case 253: // Black dragon
            case 254: // Black dragon
            case 255: // Black dragon
            case 256: // Black dragon
            case 257: // Black dragon
            case 258: // Black dragon
            case 259: // Black dragon
            case 7861: // Black dragon 247
            case 7862: // Black dragon 247
            case 7863: // Black dragon 247
            case 260: // Green dragon
            case 261: // Green dragon
            case 262: // Green dragon
            case 263: // Green dragon
            case 264: // Green dragon
            case 7868: // Green dragon 88
            case 7869: // Green dragon 88
            case 7870: // Green dragon 88
            case 265: // Blue dragon
            case 266: // Blue dragon
            case 267: // Blue dragon
            case 268: // Blue dragon
            case 269: // Blue dragon
            case 270:
            case 2919:
            case 1270:
            case 273:
            case 274:
            case 6593:
            case 7273:
            case 7274:
            case 2918:
            case 7275:
            case 8030:
            case 8031:
            case 8027:
                return 92;
            case 1680:
                return 4935;
            case 1679:
                return 241;
            case 1678:
                return 2072;
            case 1683:
            case 1684:
                return 6246;
            case 1685:
                return 5491;
            default:
                return 2304;
        }
    }
}
