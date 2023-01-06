package io.exilius.content

import io.exilius.Server
import io.exilius.model.entity.player.Player
import io.exilius.model.entity.player.PlayerHandler
import io.exilius.model.entity.player.Position
import io.exilius.model.world.objects.GlobalObject
import io.exilius.util.Misc
import io.exilius.util.Stopwatch
import java.util.function.Consumer

object ShootingStar {
    private const val TIME = 1800000
    @JvmField
    var MAXIMUM_MINING_AMOUNT = 250
    private val timer = Stopwatch().reset()
    @JvmField
    var CRASHED_STAR: CrashedStar? = null
    var location: LocationData? = null
        private set
    const val STAR_IDS = 41223
    var star: GlobalObject? = null
    val random: LocationData
        get() = LocationData.values()[Misc.random(LocationData.values().size - 1)]

    @JvmStatic
    fun spawnStar() {
        if (CRASHED_STAR == null) {
            PlayerHandler.getPlayers().forEach(Consumer { p: Player -> })
            if (timer.elapsed(TIME.toLong())) {
                var locationData = random
                if (location != null) {
                    if (locationData == location) {
                        locationData = random
                    }
                }
                location = locationData
                CRASHED_STAR = CrashedStar(GlobalObject(STAR_IDS+Misc.random(6), locationData.spawnPos), locationData)
                Server.getGlobalObjects().add(CRASHED_STAR!!.starObject)
                PlayerHandler.executeGlobalMessage("@pur@A star has just crashed " + locationData.clue + "!")
                PlayerHandler.getPlayers().forEach(Consumer { p: Player ->})
                timer.reset()
            }
        } else {
            if (CRASHED_STAR!!.starObject!!.pickAmount >= MAXIMUM_MINING_AMOUNT) {
                despawn(true)
                timer.reset()
            }
        }
    }

    @JvmStatic
    fun despawn(respawn: Boolean) {
        if (respawn) {
            timer.reset()
        } else {
            timer.reset()
        }
        if (CRASHED_STAR != null) {
            for (p in PlayerHandler.getPlayers()) {
                if (p == null) {
                    continue
                }
                if (CRASHED_STAR!!.starObject != null) {
                    p.stopAnimation()
                    p.getPA().stopSkilling()
                    p.sendMessage("The star has been fully mined.")
                }
            }
            PlayerHandler.executeGlobalMessage("@pur@The star is forcasted to crash again in one hour!")
            Server.getGlobalObjects().remove(CRASHED_STAR!!.starObject)
            CRASHED_STAR = null
        }
    }

    class CrashedStar(val starObject: GlobalObject?, val starLocation: LocationData)
    enum class LocationData(val spawnPos: Position, val clue: String, var playerPanelFrame: String) {
        LOCATION_1(Position(3050, 3319), "north of the Falador Farming patches", "Falador Farming"),
        LOCATION_3(Position(2480, 3433), "at the Gnome Agility Course", "Gnome Course"),
        LOCATION_4(Position(2745, 3445), "in the middle of the Flax field", "Flax Field"),
        LOCATION_5(Position(2322, 3796), "in the yak field", "Yak Field"),
        LOCATION_6(Position(2481, 2867), "outside the Myths Guild", "Myths Guild"),
        LOCATION_7(Position(3368, 3269), "in the Pvp Arena", "Duel Arena"),
        LOCATION_8(Position(1746, 5327), "in the Ancient cavern", "Ancient Cavern"),
        LOCATION_9(Position(2882, 9800), "in the Taverly dungeon", "Taverly Dung."),
        LOCATION_10(Position(2666, 2648), "at the Void knight island", "Pest Control"),
        LOCATION_11(Position(3566, 3297), "on the Barrows hills", "Barrows"),
        LOCATION_12(Position(2986, 3599), "in the Wilderness (western dragons)", "West Dragons"),
        LOCATION_13(Position(3091, 3528), "in the Wilderness (Edgeville)", "Edgeville Wild"),
        LOCATION_14(Position(2995, 3911), "outside the Wilderness Agility Course", "Wild. Course"),
        LOCATION_15(Position(3097, 3459), "south of the bank at home", "Home"),
        LOCATION_16(Position(2600, 3386), "outside of the fishing guild.", "Fishing Guild");
    }
}