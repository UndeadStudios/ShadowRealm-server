package io.shadowrealm.content.battle_pass.season_data

import io.shadowrealm.model.items.GameItem
import java.time.LocalDateTime

/**
 * Created by @author Flub
 * Set 25 free rewards and 25 paid rewards
 */
enum class BattlePassSeasons(
    val startDate: LocalDateTime, val freeRewards: ArrayList<GameItem>, val premiumRewards: ArrayList<GameItem>
) {
//    SEASON_ONE(
//        startDate = LocalDateTime.of(2022, 11, 1, 0,0,0), freeRewards = arrayListOf(
//            GameItem(
//                990, 10
//            ),
//            GameItem(
//                995, 2500000
//            ),
//            GameItem(
//                537, 250
//            ),
//            GameItem(
//                13438, 1
//            ),
//            GameItem(
//                6199, 1
//            ),
//            GameItem(
//                13442, 250
//            ),
//            GameItem(
//                11937, 250
//            ),
//            GameItem(
//                406, 10
//            ),
//            GameItem(
//                995, 10000000
//            ),
//            GameItem(
//                6828, 1
//            ),
//            GameItem(
//                6792, 3
//            ),
//            GameItem(
//                22374, 5
//            ),
//            GameItem(
//                21326, 2500
//            ),
//            GameItem(
//                995, 20000000
//            ),
//            GameItem(
//                6828, 2
//            ),
//            GameItem(
//                23951, 5
//            ),
//            GameItem(
//                23933, 50
//            ),
//            GameItem(
//                13346, 1
//            ),
//            GameItem(
//                995, 25000000
//            ),
//            GameItem(
//                13346, 2
//            ),
//        ), premiumRewards = arrayListOf(
//            GameItem(
//                990, 20
//            ),
//            GameItem(
//                21046, 20
//            ),
//            GameItem(
//                22125, 100
//            ),
//            GameItem(
//                12783, 1
//            ),
//            GameItem(
//                6828, 2
//            ),
//            GameItem(
//                13442, 500
//            ),
//            GameItem(
//                23686, 100
//            ),
//            GameItem(
//                13438, 5
//            ),
//            GameItem(
//                693, 2
//            ),
//            GameItem(
//                8167, 2
//            ),
//            GameItem(
//                6792, 1
//            ),
//            GameItem(
//                990, 5
//            ),
//            GameItem(
//                11212, 1000
//            ),
//            GameItem(
//                6199, 5
//            ),
//            GameItem(
//                12789, 3
//            ),
//            GameItem(
//                23951, 7
//            ),
//            GameItem(
//                23933, 75
//            ),
//            GameItem(
//                13346, 1
//            ),
//            GameItem(
//                8167, 1
//            ),
//            GameItem(
//                13346, 5
//            ),
//        )
//    ),
//    SEASON_TWO(
//        startDate = LocalDateTime.of(2023, 1, 15, 0,0,0),
//        freeRewards = arrayListOf(
//            GameItem(
//                990, 10
//            ),
//            GameItem(
//                995, 2500000
//            ),
//            GameItem(
//                537, 250
//            ),
//            GameItem(
//                13438, 1
//            ),
//            GameItem(
//                6199, 1
//            ),
//            GameItem(
//                13442, 250
//            ),
//            GameItem(
//                11937, 250
//            ),
//            GameItem(
//                406, 10
//            ),
//            GameItem(
//                995, 10000000
//            ),
//            GameItem(
//                6828, 1
//            ),
//            GameItem(
//                6792, 3
//            ),
//            GameItem(
//                22374, 5
//            ),
//            GameItem(
//                21326, 2500
//            ),
//            GameItem(
//                995, 20000000
//            ),
//            GameItem(
//                6828, 2
//            ),
//            GameItem(
//                23951, 5
//            ),
//            GameItem(
//                23933, 50
//            ),
//            GameItem(
//                13346, 1
//            ),
//            GameItem(
//                995, 25000000
//            ),
//            GameItem(
//                13346, 2
//            ),
//        ), premiumRewards = arrayListOf(
//            GameItem(
//                990, 20
//            ),
//            GameItem(
//                21046, 20
//            ),
//            GameItem(
//                22125, 100
//            ),
//            GameItem(
//                12783, 1
//            ),
//            GameItem(
//                6828, 2
//            ),
//            GameItem(
//                13442, 500
//            ),
//            GameItem(
//                23686, 100
//            ),
//            GameItem(
//                13438, 5
//            ),
//            GameItem(
//                693, 2
//            ),
//            GameItem(
//                8167, 2
//            ),
//            GameItem(
//                6792, 1
//            ),
//            GameItem(
//                990, 5
//            ),
//            GameItem(
//                11212, 1000
//            ),
//            GameItem(
//                6199, 5
//            ),
//            GameItem(
//                12789, 3
//            ),
//            GameItem(
//                23951, 7
//            ),
//            GameItem(
//                23933, 75
//            ),
//            GameItem(
//                13346, 1
//            ),
//            GameItem(
//                8167, 1
//            ),
//            GameItem(
//                13346, 5
//            ),
//        )
//    ),
SEASON_THREE( //banned do this season
    startDate = LocalDateTime.of(2023, 3, 14, 0,0,0),
    freeRewards = arrayListOf(
        GameItem(
            995, 500000
        ),
        GameItem(
            989, 1
        ),
        GameItem(
            170, 5
        ),
        GameItem(
            12698, 5
        ),
        GameItem(
            995, 1000000
        ),
        GameItem(
            386, 100
        ),
        GameItem(
            2996, 50
        ),
        GameItem(
            7629, 1
        ),
        GameItem(
            2841, 1
        ),
        GameItem(
            11739, 2
        ),
        GameItem(
            28827, 1
        ),
        GameItem(
            995, 5000000
        ),
        GameItem(
            537, 150
        ),
        GameItem(
            989, 2
        ),
        GameItem(
            1746, 50
        ),
        GameItem(
            140, 20
        ),
        GameItem(
            2528, 1
        ),
        GameItem(
            30002, 10
        ),
        GameItem(
            6199, 1
        ),
        GameItem(
            12863, 1
        ),
        GameItem(
            1514, 200
        ),
        GameItem(
            30010, 1
        ),
        GameItem(
            22093, 1
        ),
        GameItem(
            995, 10000000
        ),
        GameItem(
            23951, 2
        ),
        GameItem(
            454, 1000
        ),
        GameItem(
            9005, 1
        ),
        GameItem(
            406, 5
        ),
        GameItem(
            1632, 100
        ),
        GameItem(
            995, 15000000
        ),
        GameItem(
            2403, 1
        ),
        GameItem(
            3456, 1
        ),
        GameItem(
            2996, 100
        ),
        GameItem(
            12879, 1
        ),
        GameItem(
            372, 1000
        ),
        GameItem(
            28827, 1
        ),
        GameItem(
            691, 1
        ),
        GameItem(
            7629, 2
        ),
        GameItem(
            11840, 1
        ),
        GameItem(
            537, 50
        ),
        GameItem(
            30002, 10
        ),
        GameItem(
            11739, 2
        ),
        GameItem(
            995, 15000000
        ),
        GameItem(
            2506, 100
        ),
        GameItem(
            989, 15
        ),
        GameItem(
            12698, 100
        ),
        GameItem(
            3001, 100
        ),
        GameItem(
            23933, 35
        ),
        GameItem(
            2528, 1
        ),
        GameItem(
            8167, 1
        ),
        GameItem(
            140, 100
        ),
        GameItem(
            22375, 5
        ),
        GameItem(
            22374, 2
        ),
        GameItem(
            23490, 5
        ),
        GameItem(
            6792, 1
        ),
        GameItem(
            4185, 1
        ),
        GameItem(
            692, 1
        ),
        GameItem(
            995, 15000000
        ),
        GameItem(
            27440, 1
        ),
        GameItem(
            13346, 1
        ),
    ),
    premiumRewards = arrayListOf(
        GameItem(
            2403, 1
        ),
        GameItem(
            6199, 1
        ),
        GameItem(
            23733, 25
        ),
        GameItem(
            11730, 10
        ),
        GameItem(
            995, 10000000
        ),
        GameItem(
            6685, 50
        ),
        GameItem(
            2996, 100
        ),
        GameItem(
            7629, 10
        ),
        GameItem(
            2841, 5
        ),
        GameItem(
            11739, 10
        ),
        GameItem(
            28827, 3
        ),
        GameItem(
            995, 20000000
        ),
        GameItem(
            537, 150
        ),
        GameItem(
            6828, 1
        ),
        GameItem(
            1746, 150
        ),
        GameItem(
            140, 50
        ),
        GameItem(
            2528, 5
        ),
        GameItem(
            6828, 1
        ),
        GameItem(
            6199, 3
        ),
        GameItem(
            13346, 1
        ),
        GameItem(
            1514, 500
        ),
        GameItem(
            30110, 1
        ),
        GameItem(
            22093, 2
        ),
        GameItem(
            995, 50000000
        ),
        GameItem(
            23951, 5
        ),
        GameItem(
            9006, 1
        ),
        GameItem(
            4185, 2
        ),
        GameItem(
            406, 10
        ),
        GameItem(
            22375, 10
        ),
        GameItem(
            11730, 25
        ),
        GameItem(
            2403, 1
        ),
        GameItem(
            3456, 5
        ),
        GameItem(
            2996, 100
        ),
        GameItem(
            22374, 10
        ),
        GameItem(
            995, 60000000
        ),
        GameItem(
            6199, 2
        ),
        GameItem(
            692, 1
        ),
        GameItem(
            7629, 10
        ),
        GameItem(
            2577, 1
        ),
        GameItem(
            22125, 200
        ),
        GameItem(
            11730, 50
        ),
        GameItem(
            6199, 3
        ),
        GameItem(
            995, 75000000
        ),
        GameItem(
            691, 1
        ),
        GameItem(
            13346, 1
        ),
        GameItem(
            23689, 100
        ),
        GameItem(
            3001, 500
        ),
        GameItem(
            23933, 75
        ),
        GameItem(
            13346, 2
        ),
        GameItem(
            8167, 1
        ),
        GameItem(
            2403, 1
        ),
        GameItem(
            10025, 1
        ),
        GameItem(
            6830, 1
        ),
        GameItem(
            23490, 15
        ),
        GameItem(
            6792, 5
        ),
        GameItem(
            4185, 5
        ),
        GameItem(
            693, 1
        ),
        GameItem(
            995, 100000000
        ),
        GameItem(
            28825, 1
        ),
        GameItem(
            13346, 3
        ),
    )
),
//    SEASON_FOUR(
//    startDate = LocalDateTime.of(2023, 4, 15, 0,0,0),
//    freeRewards = arrayListOf(
//    GameItem(
//    990, 10
//    ),
//    GameItem(
//    995, 2500000
//    ),
//    GameItem(
//    537, 250
//    ),
//    GameItem(
//    13438, 1
//    ),
//    GameItem(
//    6199, 1
//    ),
//    GameItem(
//    13442, 250
//    ),
//    GameItem(
//    11937, 250
//    ),
//    GameItem(
//    406, 10
//    ),
//    GameItem(
//    995, 10000000
//    ),
//    GameItem(
//    6828, 1
//    ),
//    GameItem(
//    6792, 3
//    ),
//    GameItem(
//    22374, 5
//    ),
//    GameItem(
//    21326, 2500
//    ),
//    GameItem(
//    995, 20000000
//    ),
//    GameItem(
//    6828, 2
//    ),
//    GameItem(
//    23951, 5
//    ),
//    GameItem(
//    23933, 50
//    ),
//    GameItem(
//    13346, 1
//    ),
//    GameItem(
//    995, 25000000
//    ),
//    GameItem(
//    13346, 2
//    ),
//    ), premiumRewards = arrayListOf(
//    GameItem(
//    990, 20
//    ),
//    GameItem(
//    21046, 20
//    ),
//    GameItem(
//    22125, 100
//    ),
//    GameItem(
//    12783, 1
//    ),
//    GameItem(
//    6828, 2
//    ),
//    GameItem(
//    13442, 500
//    ),
//    GameItem(
//    23686, 100
//    ),
//    GameItem(
//    13438, 5
//    ),
//    GameItem(
//    693, 2
//    ),
//    GameItem(
//    8167, 2
//    ),
//    GameItem(
//    6792, 1
//    ),
//    GameItem(
//    990, 5
//    ),
//    GameItem(
//    11212, 1000
//    ),
//    GameItem(
//    6199, 5
//    ),
//    GameItem(
//    12789, 3
//    ),
//    GameItem(
//    23951, 7
//    ),
//    GameItem(
//    23933, 75
//    ),
//    GameItem(
//    13346, 1
//    ),
//    GameItem(
//    8167, 1
//    ),
//    GameItem(
//    13346, 5
//    )
//    )
//    ),

}