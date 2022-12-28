package io.exilius.model.entity.npc.data;

import io.exilius.content.bosses.wildypursuit.FragmentOfSeren;
import io.exilius.content.bosses.wildypursuit.TheUnbearable;
import io.exilius.content.skills.hunter.trap.impl.BirdSnare;
import io.exilius.content.skills.hunter.trap.impl.BoxTrap;
import io.exilius.model.Npcs;
import io.exilius.model.entity.npc.NPC;
import io.exilius.model.entity.player.Boundary;

public class RespawnTime {

    public static int get(NPC npc) {
        //if (Server.isDebug()) {
         //   return 5;
      //  }
        int id = npc.getNpcId();

        for (BoxTrap.BoxTrapData boxTrap : BoxTrap.BoxTrapData.values()) {
            if (id == boxTrap.getNpcId()) {
                return boxTrap.getRespawn();
            }
        }

        for (BirdSnare.BirdData birdSnare : BirdSnare.BirdData.values()) {
            if (id == birdSnare.getNpcId()) {
                return birdSnare.getRespawn();
            }
        }

        switch (id) {

            case FragmentOfSeren.NPC_ID:
            case TheUnbearable.NPC_ID:
                if(Boundary.isIn(npc, Boundary.MZ) || Boundary.isIn(npc, Boundary.OZ)){
                    return 60;
                } else {
                    return -1;
                }

            case Npcs.SARACHNIS:
                    return 20;
            case 8703:
                return 50;
            case 6600:
            case 6601:
            case 6602:
            case 320:
            case 1049:
            case 6617:
            case 3118:
            case 3120:
            case 6768:
            case Npcs.SKELETON_HELLHOUND:
            case 2402:
            case 2401:
            case 2400:
            case 2399:
            case 5916:
            case 7604:
            case 7605:
            case 7606:
            case 7585:
            case 5129:
            case FragmentOfSeren.FRAGMENT_ID:
            case FragmentOfSeren.CRYSTAL_WHIRLWIND:
            case 7563:
            case 7573:
            case 7544:
            case 7566:
            case 7559:
            case 7553:
            case 7554:
            case 7555:
            case 7560:
            case 7527:
            case 7528:
            case 7529:
                return -1;
            case 492:
                return 20;
            case 5862: //cerberus
                return 15;
            case 5001://anti-santa
            case 6477:
            case 5462:
            case 7858:
            case 7859:
                return -1;
            case 2818:
                return 40;
            case 2816:
                return 60;
            case 963:
            case 965:
                return 10;
            case 8609:
                return 40;
            case 6618:
            case 6619:
            case 319:
            case 5890:
                return 30;
            case 1773:
            case 101: // goblin
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
            case 3073:
            case 3074:
            case 5204:
            case 3075://armed
            case 3076:
            case 2484:
            case 2485:
            case 2486:
            case 2487:
            case 2488:
            case 2489:
            case 656:
            case 658:
            case 660:
            case 661:
            case 663:
            case 665:
            case 667:
            case 668:
                return 35;
            case 9756:
            case 9757:
            case 9758:
            case 9759:
            case 9760:
            case 9761:
            case 9762:
            case 9763:
                return 25;
            case 1046:
            case 465:
                return 60;

            case 6609://callisto
                return 40;
            case 2265:
            case 2266:
            case 2267:
                return 70;

            case 6611:
            case 6612:
            case 8781:
                return 40;
            case 2558:
            case 2559:
            case 2560:
            case 2561:
            case 2562:
            case 2563:
            case 2564:
            case 2205:
            case 2215:
            case 3129:
            case 3162:
            case 1641:
            case 1642:
                return 100;
            case 11958:
                return 30;

            case 11283:
                return -1;

//            case 494:
//                    return -1;

            case 11284:
                return -1;

            case 11285:
                return -1;

            case 11286:
                return -1;

            case 1643:
                return 180;

            case 1654:
                return 250;
            case 2216:
            case 2217:
            case 2218:
            case 3163:
            case 3164:
            case 3165:
            case 2206:
            case 2207:
            case 2208:
            case 3130:
            case 3131:
            case 3132:
                return 40;
            case 3777:
            case 3778:
            case 3779:
            case 3780:
            case 7302:
            case 8748:
                return 500;
            default:
                return 35;
        }
    }
}
