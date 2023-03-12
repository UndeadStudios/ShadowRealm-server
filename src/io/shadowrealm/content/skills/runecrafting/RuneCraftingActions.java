package io.shadowrealm.content.skills.runecrafting;

import io.shadowrealm.model.entity.player.Player;

public class RuneCraftingActions {
    public static void handleRuneCrafting(Player player, int objectId, int obX, int obY) {
        switch (objectId) {
            case 34748:// air altar portal
                if (obX == 2841 && obY == 4828) {
                    player.getPlayerAssistant().startTeleport(2983, 3293, 0, "modern", false);
                }
                break;

            case 34749:// mind altar portal
                if (obX == 2793 && obY == 4827) {
                    player.getPlayerAssistant().startTeleport(2980, 3514, 0, "modern", false);
                }
                break;

            case 34750:// water altar portal
                if (obX == 2727 && obY == 4832) {
                    player.getPlayerAssistant().startTeleport(3184, 3162, 0, "modern", false);
                }
                break;

            case 34751:// earth rune portal
                if (obX == 2655 && obY == 4829) {
                    player.getPlayerAssistant().startTeleport(3308, 3476, 0, "modern", false);
                }
                break;

            case 34752:// fire rune portal
                if (obX == 2574 && obY == 4850) {
                    player.getPlayerAssistant().startTeleport(3311, 3256, 0, "modern", false);
                }
                break;

            case 34753:// body altar portal
                if (obX == 2521 && obY == 4833) {
                    player.getPlayerAssistant().startTeleport(3051, 3444, 0, "modern", false);
                }
                break;

            case 34754:// cosmic altar portal
                if (obX == 2163 && obY == 4833 || obX == 2142 && obY == 4854 || obX == 2121 && obY == 4833 || obX == 2412 && obY == 4812) {
                    player.getPlayerAssistant().startTeleport(2410, 4379, 0, "modern", false);
                }
                break;

            case 34755:// law altar portal
                if (obX == 2464 && obY == 4817) {
                    player.getPlayerAssistant().startTeleport(2857, 3379, 0, "modern", false);
                }
                break;

            case 34756:// nature portal altar
                if (obX == 2400 && obY == 4834) {
                    player.getPlayerAssistant().startTeleport(2866, 3022, 0, "modern", false);
                }
                break;

            case 34757:
                if (obX == 3233 && obY == 9312) {// desert
                    // treasure
                    // portal
                    //player.getPlayerAssistant().startTeleport(3233, 2887, 0, "modern", false);
                } else if (obX == 2282 && obY == 4837) {// chaos
                    // altar
                    // portal
                    player.getPlayerAssistant().startTeleport(3062, 3593, 0, "modern", false);
                }
                break;

            case 34758:// death altar portal
                if (obX == 2208 && obY == 4829) {
                    player.getPlayerAssistant().startTeleport(1863, 4639, 0,
                            "modern", false);
                }
                break;
            case 43478:// blood altar portal
                if (obX == 3240 && obY == 4832 || obX == 3224 && obY == 4832) {
                    player.getPlayerAssistant().startTeleport(3558, 9779, 0,
                            "modern", false);
                }
                break;
            case 34760:// air altar crafting
                if (obX == 2843 && obY == 4833) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34761:// mind altar crafting
                if (obX == 2785 && obY == 4840) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34762:// water altar crafting
                if (obX == 2715 && obY == 4835) {
                    player.getRC().craftRunes(objectId);
                }
                break;
            case 43479://blood rune
            if(obX == 3231 & obY == 4831){
                player.getRC().craftRunes(objectId);
            }
            break;
            case 34763:// earth altar crafting
                if (obX == 2657 && obY == 4840) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34764:// fire altar crafting
                if (obX == 2584 && obY == 4837) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34765:// body altar crafting
                if (obX == 2524 && obY == 4831) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34766:// cosmic altar crafting
                if (obX == 2141 && obY == 4832) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34767:// law altar crafting
                if (obX == 2463 && obY == 4831) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34768:// nature altar crafting
                if (obX == 2399 && obY == 4840) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34769:// chaos altar crafting
                if (obX == 2270 && obY == 4841) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34770:// death altar crafting
                if (obX == 2204 && obY == 4835) {
                    player.getRC().craftRunes(objectId);
                }
                break;

            case 34813:// air altar entrance
                if (obX == 2984 && obY == 3291) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34814:// mind altar entrance
                if (obX == 2981 && obY == 3513) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34815:// water altar entrance
                if (obX == 3184 && obY == 3164) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34816:// earth altar entrance
                if (obX == 3305 && obY == 3473) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34817:// fire altar entrance
                if (obX == 3312 && obY == 3254) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34818:// body altar entrance
                if (obX == 3052 && obY == 3444) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34819:// cosmic altar entrance
                if (obX == 2407 && obY == 4376) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34820:// law altar entrance
                if (obX == 2857 && obY == 3380) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34821:// nature altar entrance
                if (obX == 2868 && obY == 3018) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34822:// chaos altar entrance
                if (obX == 3059 && obY == 3590) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;

            case 34823:// death altar entrance
                if (obX == 1859 && obY == 4638) {
                    player.getRC().enterAltar(objectId, 0);
                }
                break;
        }
    }}
