package io.exilius.content.battle_pass.season_data

import io.exilius.content.battle_pass.BattlePassConfig
import io.exilius.model.items.GameItem
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Created by @author Flub
 * Set 25 free rewards and 25 paid rewards
 */
enum class BattlePassSeasons(
    val startDate: LocalDateTime, val freeRewards: ArrayList<GameItem>, val premiumRewards: ArrayList<GameItem>
) {
    SEASON_ONE(
        startDate = LocalDateTime.of(2022, 11, 1, 0,0,0), freeRewards = arrayListOf(
            GameItem(
                990, 10
            ),
            GameItem(
                995, 2500000
            ),
            GameItem(
                537, 250
            ),
            GameItem(
                13438, 1
            ),
            GameItem(
                6199, 1
            ),
            GameItem(
                13442, 250
            ),
            GameItem(
                11937, 250
            ),
            GameItem(
                406, 10
            ),
            GameItem(
                995, 10000000
            ),
            GameItem(
                6828, 1
            ),
            GameItem(
                6792, 3
            ),
            GameItem(
                22374, 5
            ),
            GameItem(
                21326, 2500
            ),
            GameItem(
                995, 20000000
            ),
            GameItem(
                6828, 2
            ),
            GameItem(
                23951, 5
            ),
            GameItem(
                23933, 50
            ),
            GameItem(
                13346, 1
            ),
            GameItem(
                995, 25000000
            ),
            GameItem(
                13346, 2
            ),
        ), premiumRewards = arrayListOf(
            GameItem(
                990, 20
            ),
            GameItem(
                21046, 20
            ),
            GameItem(
                22125, 100
            ),
            GameItem(
                12783, 1
            ),
            GameItem(
                6828, 2
            ),
            GameItem(
                13442, 500
            ),
            GameItem(
                23686, 100
            ),
            GameItem(
                13438, 5
            ),
            GameItem(
                693, 2
            ),
            GameItem(
                8167, 2
            ),
            GameItem(
                6792, 1
            ),
            GameItem(
                990, 5
            ),
            GameItem(
                11212, 1000
            ),
            GameItem(
                6199, 5
            ),
            GameItem(
                12789, 3
            ),
            GameItem(
                23951, 7
            ),
            GameItem(
                23933, 75
            ),
            GameItem(
                13346, 1
            ),
            GameItem(
                8167, 1
            ),
            GameItem(
                13346, 5
            ),
        )
    ),
    SEASON_TWO(
        startDate = LocalDateTime.of(2023, 1, 15, 0,0,0),
        freeRewards = arrayListOf(
            GameItem(
                990, 10
            ),
            GameItem(
                995, 2500000
            ),
            GameItem(
                537, 250
            ),
            GameItem(
                13438, 1
            ),
            GameItem(
                6199, 1
            ),
            GameItem(
                13442, 250
            ),
            GameItem(
                11937, 250
            ),
            GameItem(
                406, 10
            ),
            GameItem(
                995, 10000000
            ),
            GameItem(
                6828, 1
            ),
            GameItem(
                6792, 3
            ),
            GameItem(
                22374, 5
            ),
            GameItem(
                21326, 2500
            ),
            GameItem(
                995, 20000000
            ),
            GameItem(
                6828, 2
            ),
            GameItem(
                23951, 5
            ),
            GameItem(
                23933, 50
            ),
            GameItem(
                13346, 1
            ),
            GameItem(
                995, 25000000
            ),
            GameItem(
                13346, 2
            ),
        ), premiumRewards = arrayListOf(
            GameItem(
                990, 20
            ),
            GameItem(
                21046, 20
            ),
            GameItem(
                22125, 100
            ),
            GameItem(
                12783, 1
            ),
            GameItem(
                6828, 2
            ),
            GameItem(
                13442, 500
            ),
            GameItem(
                23686, 100
            ),
            GameItem(
                13438, 5
            ),
            GameItem(
                693, 2
            ),
            GameItem(
                8167, 2
            ),
            GameItem(
                6792, 1
            ),
            GameItem(
                990, 5
            ),
            GameItem(
                11212, 1000
            ),
            GameItem(
                6199, 5
            ),
            GameItem(
                12789, 3
            ),
            GameItem(
                23951, 7
            ),
            GameItem(
                23933, 75
            ),
            GameItem(
                13346, 1
            ),
            GameItem(
                8167, 1
            ),
            GameItem(
                13346, 5
            ),
        )
    ),
    SEASON_THREE( //banned do this season
        startDate = LocalDateTime.of(2023, 2, 20, 0,0,0),
        freeRewards = arrayListOf(
            GameItem(
                1042, 1
            ),
            GameItem(
                1043, 1
            ),
            GameItem(
                1044, 1
            ),
            GameItem(
                1045, 1
            ),
            GameItem(
                1046, 1
            ),
            GameItem(
                1047, 1
            ),
            GameItem(
                1048, 1
            ),
            GameItem(
                1049, 1
            ),
            GameItem(
                1050, 1
            ),
            GameItem(
                1051, 1
            ),
            GameItem(
                1052, 1
            ),
            GameItem(
                1053, 1
            ),
            GameItem(
                1054, 1
            ),
            GameItem(
                1055, 1
            ),
            GameItem(
                1056, 1
            ),
            GameItem(
                1057, 1
            ),
            GameItem(
                1058, 1
            ),
            GameItem(
                1059, 1
            ),
            GameItem(
                1060, 1
            ),
            GameItem(
                1061, 1
            ),
        ),
        premiumRewards = arrayListOf(
            GameItem(
                44, 1
            ),
            GameItem(
                45, 1
            ),
            GameItem(
                46, 1
            ),
            GameItem(
                47, 1
            ),
            GameItem(
                48, 1
            ),
            GameItem(
                49, 1
            ),
            GameItem(
                46, 1
            ),
            GameItem(
                47, 1
            ),
            GameItem(
                48, 1
            ),
            GameItem(
                49, 1
            ),
            GameItem(
                50, 1
            ),
            GameItem(
                51, 1
            ),
            GameItem(
                52, 1
            ),
            GameItem(
                53, 1
            ),
            GameItem(
                54, 1
            ),
            GameItem(
                55, 1
            ),
            GameItem(
                56, 1
            ),
            GameItem(
                57, 1
            ),
            GameItem(
                58, 1
            ),
            GameItem(
                59, 1
            ),
        )
    ),

    SEASON_FOUR(
    startDate = LocalDateTime.of(2023, 4, 15, 0,0,0),
    freeRewards = arrayListOf(
    GameItem(
    990, 10
    ),
    GameItem(
    995, 2500000
    ),
    GameItem(
    537, 250
    ),
    GameItem(
    13438, 1
    ),
    GameItem(
    6199, 1
    ),
    GameItem(
    13442, 250
    ),
    GameItem(
    11937, 250
    ),
    GameItem(
    406, 10
    ),
    GameItem(
    995, 10000000
    ),
    GameItem(
    6828, 1
    ),
    GameItem(
    6792, 3
    ),
    GameItem(
    22374, 5
    ),
    GameItem(
    21326, 2500
    ),
    GameItem(
    995, 20000000
    ),
    GameItem(
    6828, 2
    ),
    GameItem(
    23951, 5
    ),
    GameItem(
    23933, 50
    ),
    GameItem(
    13346, 1
    ),
    GameItem(
    995, 25000000
    ),
    GameItem(
    13346, 2
    ),
    ), premiumRewards = arrayListOf(
    GameItem(
    990, 20
    ),
    GameItem(
    21046, 20
    ),
    GameItem(
    22125, 100
    ),
    GameItem(
    12783, 1
    ),
    GameItem(
    6828, 2
    ),
    GameItem(
    13442, 500
    ),
    GameItem(
    23686, 100
    ),
    GameItem(
    13438, 5
    ),
    GameItem(
    693, 2
    ),
    GameItem(
    8167, 2
    ),
    GameItem(
    6792, 1
    ),
    GameItem(
    990, 5
    ),
    GameItem(
    11212, 1000
    ),
    GameItem(
    6199, 5
    ),
    GameItem(
    12789, 3
    ),
    GameItem(
    23951, 7
    ),
    GameItem(
    23933, 75
    ),
    GameItem(
    13346, 1
    ),
    GameItem(
    8167, 1
    ),
    GameItem(
    13346, 5
    )
    )
    ),

}