package io.exilius.model.entity.npc.data;

import io.exilius.content.bosses.Skotizo;
import io.exilius.content.bosses.hespori.Hespori;
import io.exilius.content.minigames.inferno.InfernoWaveData;
import io.exilius.model.Items;
import io.exilius.model.Npcs;

public class BlockAnimation {

    public static int getAnimation(int npcId) {
        switch (npcId) {
            case 2837: // unicorn
            case 2849:
                return 6375;
            case 936:
                return 285;
            case 2916:
            case 2917:
                return 1581;
            case 11293:
                return 9196;
            case Hespori.NPC_ID:
                return -1;
            //Inferno Npcs
            case InfernoWaveData.JAL_NIB:
                return 7575;
            case InfernoWaveData.JAL_MEJRAH:
                return 7579;
            case InfernoWaveData.JAL_AK:
                return 7585;
            case InfernoWaveData.JAL_AKREK_KET:
                return 7585;
            case InfernoWaveData.JAL_AKREK_MEJ:
                return 7585;
            case InfernoWaveData.JAL_AKREK_XIL:
                return 7585;
            case InfernoWaveData.JAL_IMKOT:
                return 7598;
            case InfernoWaveData.JAL_XIL:
                return 7607;
            case InfernoWaveData.JALTOK_JAD:
                return 7591;
            case InfernoWaveData.TZKAL_ZUK:
                return 7565;
            case Npcs.BRYOPHYTA:
                return 4657;
            case InfernoWaveData.JAL_MEJJAK:
                return 2869;
            case 8609:/* Hydra */
                return -1;
            case 852:
                return 5723;
            case 9026://rat
            case 2510:
            case 2511:
            case 2512:
            case 4501:
                return 4934;
            case 9027://spider
            case 3018:
            case 3017:
            case 2477:
            case 3023:
                return 5328;
            case 9028://bat
                return 4916;
            case 9029://unicorn
                return 6375;
            case 9030://scorpion
            case 3024:
            case 2479:
            case 2480:
                return 6259;
            case 9031://wolf
            case 107:
            case 108:
                return 6578;
            case 9032://bear
            case 2838:
            case 3423:
                return 4927;
            case 9033://dragon
                return 89;
            case 9034://dark beast
                return 2732;
            case 9021:
            case 9022: //range
            case 9023: //mage
                return 8419;
            // case 7573:
            // return 7196;
            case 7563:
                return 7421;
            case Skotizo.SKOTIZO_ID:
                return 4676;
            case 7585:
            case Skotizo.REANIMATED_DEMON:
                return 65;
            case 7604: // Skeletal mystic
            case 7605: // Skeletal mystic
            case 7606: // Skeletal mystic
                return 5489;
            case 2085:
                return 4671;
            case 7573:
                return 7194;
            case 7544: // Tekton
                return 7489;
            case 5916:
                return 4523;
            case 5890:
                return 5755;
            case 955:
            case 957:
            case 959:
                return 6232;
            case 7144:
            case 7145:
            case 7146:
                return 7228;
            case 458:
                return 2777;
            case 3544:
                return 276;
            case 1267:
                return 2020;
            case 2064:
                return 1012;
            case 2067:
                return 6792;

            case 2593:
                return 6538;

            case 963:
                return 6232;

            case 965:
                return 6237;

            case 5862:
                return 4489;

            case 465: // Skeletal wyverns
                return 2983;

            case 6766: // Lizardman shaman
            case 6768:
            case 6914:
            case 6915:
            case 6916:
            case 6917:
            case 6918:
            case 6919:
                return 7194;

            case 6618: // Crazy Archaeologist
            case 6619:
                return 424;

            case 6615: // Scorpia
            case 6616:
            case 6617:
                return 6255;
            case 1277:
            case 2527:
            case 85:
                return 5541;
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
                return 5533;
            //Xeric Minigame
            case 7547:
            case 7548:
                return 256;
            case 7559:
            case 7560:
                return 403;
            case 7576:
            case 7577:
            case 7578:
            case 7579:
                return 1313;
            case 7586:
                return 1581;
            case 7538:
                return 7455;
            case 7529:
                return 7442;
            case 7531:
                return 7455;
            case 7543:
                return 7483;
            case 7566:
                return 7412;
            case 2145:
            case 6462: // Zombies
            case 6465:
                return 5574;
            case 6367: // Evil chicken
                return 2299;
            case 2241:
            case 3049:
            case 3050:
            case 3286:
            case 3287:
                return 165;
            case 6369: // Agrith-na-na
                return 3500;
            case 6370: // Flambeed
                return 1751;
            case 6372: // Dessourt
                return 3505;
            case 6373:
            case 6374:
            case 6375:
            case 6376:
            case 6377:
            case 6378:
                return 1340;
            case 437:
            case 7277: // Jelly
            case 7400:
            case 7399:
            case 411: // Kurask
            case 7405:
            case 3209: // Cave Horror
            case 7401:
                return 4235;
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
                return 5489;
            case 419: // Cockatrice
            case 7393:
            case 8781:
                return -1;
            case 101:
                return 6183;
            case 3835:
                return 6232;
            case 2037:
                return 5489;
            case 5529:
                return 5783;
            case 5219:
            case 5218:
                return 5096;
            case 5235:
                return 5395;
            case 10127:
                return 13170;
            case 10057:
                return 10818;
            case 5904:
                return 6330;
            case 5779:
                return 3311;
            case 5903:
                return 6346;
            case 9463:
            case 9465:
            case 9467:
                return 12792;
            case 6624:
                return 7413;
            case 6649:
                return 7469;
            case 6646:
                return 7462;
            case 3836:
                return 6237;
            case 4005: // Dark Beast
            case 7409:
                return 2732;
            case 8133:
                return 10058;
            case 10141:
                return 13601;
            case 8349:
                return 10923;
            case 9947:
                return 13771;
            case 2215:
                return 7019;
            case 11278:// nex anims block
                return 9185;
            case 1377:
                return 1758;
            case 2216:
            case 2217:
            case 2218:
                return 6155;
            case 3162:
                return 6978;
            case 3163:
            case 3164:
            case 3165:
            case 3169:
                return 6952;
            case 6576:
                return 6944;
            case 3130:
            case 3131:
            case 3132:
                return 65;
            case 6575:
                return 6966;
            case Items.THAMMARONS_SCEPTRE:
            case 22552:
                return 6966;
            case 6342:
                return 5897;
            /*
             * case 2006: return 6375;
             */
            case 6229:
            case 6230:
            case 6231:
            case 6232:
            case 6233:
            case 6234:
            case 6235:
            case 6236:
            case 6237:
            case 6238:
            case 6239:
            case 6240:
            case 6241:
            case 6242:
            case 6243:
            case 6244:
            case 6245:
            case 6246:
                return 6952;
            case 6267:
                return 360;
            case 6268:
                return 2933;
            case 6269:
            case 6270:
                return 4651;
            case 6271:
            case 6272:
            case 6273:
            case 6274:
                return 4322;
            case 6275:
                return 165;
            case 6276:
            case 6277:
            case 6278:
                return 4322;
            case 6279:
            case 6280:
                return 6183;
            case 6281:
                return 6136;
            case 6282:
                return 6189;
            case 6283:
                return 6183;
            case 6210:
                return 6578;
            case 6211:
                return 170;
            case 6212:
            case 6213:
                return 6538;
            case 6215:
                return 1550;
            case 6216:
            case 6217:
                return 1581;
            case 6218:
                return 4301;
            case 6258:
                return 2561;
            case 10775:
                return 13154;
            case 113:
                return 129;
            case 114:
                return 360;
            case 3058:
                return 2937;
            case 3061:
                return 2961;
            case 3063:
                return 2937;
            case 3065:
                return 2720;
            case 3066:
                return 2926;
            case 5935:// sand crabs
                return 1313;
            case 100:// rc
                return 1313;
            case 118:
                return 100;
            case 2263:
                return 2181;
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
            case 1432:// Black Demon
            case 5874: // Black demon 172
            case 5875: // Black demon 172
            case 5876: // Black demon 172
            case 5877: // Black demon 172
            case 2048: // Black demon 172
            case 2049: // Black demon 172
            case 2050: // Black demon 172
            case 2051: // Black demon 172
            case 7871: // Greater demon 104
            case 7872: // Greater demon 104
            case 7873: // Greater demon 104
            case 7874: // Black demon 188
            case 7875: // Black demon 188
            case 7876: // Black demon 188
            case 2052: // Black demon 172
            case 240: // Black demon 172
            case 1472:// jungle demon
                return 65;
            case 3347:
            case 3346:
                return 3325;
            case 1192:
                return 1244;
            case 3062:
                return 2953;
            case 3060:
                return 2964;
            case 2892: // Spinolyp
            case 2894: // Spinolyp
            case 2896: // Spinolyp
                return 2869;
            case 423: // Dust Devil
            case 7404:
                return 1555;
            case 2054:
                return 3148;
            case 1354:
            case 1341:
            case 2455:// dagannoth
            case 2454:
            case 2456:
            case 983:
            case 984:
            case 985:
            case 986:
            case 987:
            case 988:
                return 1340;
            case 127:
                return 186;
            case 291:
                return 100;
            case 2267: // supreme
            case 2266: // prime
            case 2265: // rex
                return 2852;
            case 3452:// penance queen
                return 5413;
            case 2745:
                return 2653;
            case 2743:
                return 2645;
            case 1270:// metal dragon
            case 273:
            case 274:
            case 6593:
            case 8031:
            case 8030:
            case 8027:
                return 89;
            case 2598:
            case 2599:
            case 2600:
            case 2610:
            case 2601:
            case 2602:
            case 2603:
            case 2606:// tzhaar-xil
            case 2591:
            case 2604:// tzhar-hur
                return 2606;
            case 3121:
                return 2629;
            case 66:
            case 67:
            case 168:
            case 169:
            case 162:
            case 68:// gnomes
                return 193;
            case 160:
            case 161:
                return 194;
            case 163:
            case 164:
                return 193;
            case 438:
            case 439:
            case 440:
            case 441:
            case 442: // Tree spirit
            case 443:
                return 95;
            case 391:
            case 392:
            case 393:
            case 394:
            case 395:// river troll
            case 396:
                return 285;

            case 1153:
            case 1154:
            case 1155:
            case 1156:
            case 1157:
            case 1158: // kalphite
                return 1186;
            case 1160: // kalphite
                return 1179;
            case 2734:
            case 2627:// tzhaar
                return 2622;
            case 2630:
            case 2629:
            case 2736:
            case 2738:
                return 2626;
            case 2631:
            case 2632:
                return 2629;
            case 2741:
                return 2635;

            case 908:
                return 129;
            case 909:
                return 147;
            case 911:
                return 65;

            case 1459:// monkey guard
                return 1403;

            case 435: // pyrefiend
            case 3406:
            case 7394:
                return 1581;

            case 414:// banshee
            case 7272:
            case 7391:
            case 7390:
                return 1525;

            case 448:
            case 7388:
            case 1649:
            case 1650:
            case 1651:
            case 1652:
            case 1653:
            case 1654:
            case 1655:
            case 1656:
            case 1657:// crawling hand
                return 1591;

            case 484:
            case 7276:
            case 1619:// bloodveld
            case 7398:
            case 7397:
                return 1550;

            case 446:
            case 7396:
            case 1644:
            case 1645:
            case 1646:
            case 1647:
                return 430;

            case 11:// nechryael
            case 7411:
            case 7278:
                return 1529;

            case 1543:
            case 1611:// gargoyle
            case 7407:
                return 1519;

            case 415:// abyssal demon
            case 7410:
                return 1537;

            case 655://goblins
            case 657:
            case 659:
            case 662:
            case 664:
            case 666:
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
                return 6183;
            case 3073:
            case 3074:
            case 5204:
            case 3075://armed
            case 3076:
            case 656:
            case 658:
            case 2484:
            case 2485:
            case 2486:
            case 2487:
            case 2488:
            case 2489:
            case 660:
            case 661:
            case 663:
            case 665:
            case 667:
            case 668:
                return 6189;
            case 2481:
            case 2482:
            case 2483:
                return 4266;
            case 2490:
            case 2491:
                return 6557;
            case 2498:
            case 2499:
            case 2500:
                return 1251;
            case 2501:
            case 2502:
            case 2503:
            case 2504:
            case 2505:
            case 2506:
            case 2507:
            case 2508:
            case 2509:
                return 5574;
            case 132: // monkey
                return 221;

            case 1030:
            case 1031:
            case 1032:
            case 1033:
            case 1034:
            case 1035: // wolfman
                return 6538;

            case 1456:// monkey archer
                return 1395;
            case 1477:
                return 247;
            case 144:
                return 6255;

            case 1125:// dad
                return 285;

            case 1096:
            case 1097:
            case 1098:
            case 1942:
            case 1101:// troll
            case 1106:
                return 285;
            case 1095:
                return 285;

            case 123:
            case 122:// hobgoblin
                return 165;

            case 135:// hellhound
            case 142:
                return 6578;

            case 1593:
            case 152:
            case 45:
            case 1558: // wolf
            case 1954:
                return 76;
            case 6707:
                return -1;
            case 133:
                return 290;

            case 105:// bear
                return 4921;
            case 5399:
            case 751: // zombie
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
                return 5574;

            case 60:
            case 64:
            case 59:
            case 61:
            case 63:
            case 3021:
            case 2035: // spider
            case 62:
            case 1009:
                return 5328;


            case 1585:
            case 2075: // Fire giant
            case 2076: // Fire giant
            case 2077: // Fire giant
            case 2078: // Fire giant
            case 2079: // Fire giant
            case 2080: // Fire giant
            case 2081: // Fire giant
            case 2082: // Fire giant
            case 2083: // Fire giant
            case 2084: // Fire giant
                return 4671;
            case 111:
            case 2086: // Ice giant
            case 2087: // Ice giant
            case 2088: // Ice giant
            case 7878: // Ice giant 67
            case 7879: // Ice giant 67
            case 7880: // Ice giant 67
            case 2089: // Ice giant
                return 4671;
            case 2098:
            case 2090:
            case 2091:
            case 2092:
            case 2093:
            case 2463:
            case 116:
            case 891:
                return 4651;

            case 239: // kbd
                return 89;
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
            case 2919:
            case 270:
            case 742:
            case 1589:
            case 52:
            case 7273:
            case 7274:
            case 2918:
            case 7275:
                return 89;
            case 2889:
                return 2860;
            case 81: // cow
            case 397:
                return 5850;

            case 708: // imp
                return 170;

            case 47:// rat
                return 2706;
            case 2457:
                return 2366;
            case 128: // snake
            case 1479:
                return 276;

            case 1017:
            case 2693:
            case 41: // chicken
                return 55;

            case 1:
                return 424;
            default:
                return -1;
        }
    }
}
