package io.shadowrealm.content.minigames;


import io.shadowrealm.model.entity.player.Player;

public class CastleWarObjects {

    public void handleObject(Player player, int id, int x, int y) {
        switch (id) {
            case 4469:
                if (player.castleWarsTeam == 2) {
                    player.sendMessage("You are not allowed in the other teams spawn point.");
                    break;
                }
                if (x == 2426) {
                    if (player.getY() == 3080)
                        player.getPA().movePlayer(2426, 3081, player.heightLevel);
                    else if (player.getY() == 3081)
                        player.getPA().movePlayer(2426, 3080, player.heightLevel);
                } else if (x == 2422) {
                    if (player.getX() == 2422)
                        player.getPA().movePlayer(2423, 3076, player.heightLevel);
                    else if (player.getX() == 2423)
                        player.getPA().movePlayer(2422, 3076, player.heightLevel);
                }
                break;
            case 4470:
                if (player.castleWarsTeam == 1) {
                    player.sendMessage("You are not allowed in the other teams spawn point.");
                    break;
                }
                if (x == 2373 && y == 3126) {
                    if (player.getY() == 3126)
                        player.getPA().movePlayer(2373, 3127, 1);
                    else if (player.getY() == 3127)
                        player.getPA().movePlayer(2373, 3126, 1);
                } else if (x == 2377 && y == 3131) {
                    if (player.getX() == 2376)
                        player.getPA().movePlayer(2377, 3131, 1);
                    else if (player.getX() == 2377)
                        player.getPA().movePlayer(2376, 3131, 1);
                }
                break;
            case 4417:
                if (x == 2428 && y == 3081 && player.heightLevel == 1)
                    player.getPA().movePlayer(2430, 3080, 2);
                if (x == 2425 && y == 3074 && player.heightLevel == 2)
                    player.getPA().movePlayer(2426, 3074, 3);
                if (x == 2419 && y == 3078 && player.heightLevel == 0)
                    player.getPA().movePlayer(2420, 3080, 1);
                break;
            case 4415:
                if (x == 2419 && y == 3080 && player.heightLevel == 1)
                    player.getPA().movePlayer(2419, 3077, 0);
                if (x == 2430 && y == 3081 && player.heightLevel == 2)
                    player.getPA().movePlayer(2427, 3081, 1);
                if (x == 2425 && y == 3074 && player.heightLevel == 3)
                    player.getPA().movePlayer(2425, 3077, 2);
                if (x == 2374 && y == 3133 && player.heightLevel == 3)
                    player.getPA().movePlayer(2374, 3130, 2);
                if (x == 2369 && y == 3126 && player.heightLevel == 2)
                    player.getPA().movePlayer(2372, 3126, 1);
                if (x == 2380 && y == 3127 && player.heightLevel == 1)
                    player.getPA().movePlayer(2380, 3130, 0);
                break;
            case 4411:
                if (x == 2421 && y == 3073 && player.heightLevel == 1)
                    player.getPA().movePlayer(player.getX(), player.getY(), 0);
                break;
            case 4419:
                if (x == 2417 && y == 3074 && player.heightLevel == 0)
                    player.getPA().movePlayer(2416, 3074, 0);
                break;
            case 4911:
                if (x == 2421 && y == 3073 && player.heightLevel == 1)
                    player.getPA().movePlayer(2421, 3074, 0);
                if (x == 2378 && y == 3134 && player.heightLevel == 1)
                    player.getPA().movePlayer(2378, 3133, 0);
                break;
            case 1747:
                if (x == 2421 && y == 3073 && player.heightLevel == 0)
                    player.getPA().movePlayer(2421, 3074, 1);
                if (x == 2378 && y == 3134 && player.heightLevel == 0)
                    player.getPA().movePlayer(2378, 3133, 1);
                break;
            case 4912:
                if (x == 2430 && y == 3082 && player.heightLevel == 0)
                    player.getPA().movePlayer(player.getX(), player.getY() + 6400, 0);
                if (x == 2369 && y == 3125 && player.heightLevel == 0)
                    player.getPA().movePlayer(player.getX(), player.getY() + 6400, 0);
                break;
            case 1757:
                if (x == 2400 && y == 9508 && player.heightLevel == 0)
                    player.getPA().movePlayer(player.getX(), player.getY() - 6400, 0);
                if (x == 2399 && y == 9499 && player.heightLevel == 0)
                    player.getPA().movePlayer(player.getX(), player.getY() - 6400, 0);
                break;

            case 4418:
                if (x == 2380 && y == 3127 && player.heightLevel == 0)
                    player.getPA().movePlayer(2379, 3127, 1);
                if (x == 2369 && y == 3126 && player.heightLevel == 1)
                    player.getPA().movePlayer(2369, 3127, 2);
                if (x == 2374 && y == 3131 && player.heightLevel == 2)
                    player.getPA().movePlayer(2373, 3133, 3);
                break;
            case 4420:
                if (x == 2382 && y == 3131 && player.heightLevel == 0)
                    if (player.getX() >= 2383 && player.getX() <= 2385)
                        player.getPA().movePlayer(2382, 3130, 0);
                    else
                        player.getPA().movePlayer(2383, 3133, 0);
                break;
            default:

                break;

        }
    }

}