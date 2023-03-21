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
        GameItem(//edit here banned change all ids and amounts this is the free rewards
            990, 10
        ),
        GameItem(
            995, 20000000
        ),
        GameItem(
            6686, 50
        ),
        GameItem(
            3025, 50
        ),
        GameItem(
            6199, 1
        ),
        GameItem(
            406, 10
        ),
        GameItem(
            23951, 3
        ),
        GameItem(
            12696, 100
        ),
        GameItem(
            693, 1
        ),
        GameItem(
            6828, 1
        ),
        GameItem(
            995, 20000000
        ),
        GameItem(
            8167, 1
        ),
        GameItem(
            23933, 25
        ),
        GameItem(
            12789, 1
        ),
        GameItem(
            22374, 5
        ),
        GameItem(
            21046, 25
        ),
        GameItem(
            693, 2
        ),
        GameItem(
            995, 30000000
        ),
        GameItem(
            13346, 2
        ),
        GameItem(
            2403, 1
        ),
    ),
    premiumRewards = arrayListOf(
        GameItem(//baned changes these ids this is premium rewards
            990, 20
        ),
        GameItem(
            995, 50000000
        ),
        GameItem(
            6686, 250
        ),
        GameItem(
            3025, 250
        ),
        GameItem(
            6828, 1
        ),
        GameItem(
            13438, 5
        ),
        GameItem(
            23951, 7
        ),
        GameItem(
            23686, 150
        ),
        GameItem(
            693, 3
        ),
        GameItem(
            10025, 1
        ),
        GameItem(
            995, 50000000
        ),
        GameItem(
            8167, 2
        ),
        GameItem(
            23933, 75
        ),
        GameItem(
            12789, 2
        ),
        GameItem(
            22374, 5
        ),
        GameItem(
            21046, 50
        ),
        GameItem(
            693, 3
        ),
        GameItem(
            995, 100000000
        ),
        GameItem(
            13346, 2
        ),
        GameItem(
            6830, 1
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