package io.exilius.content.teleportation;

import io.exilius.Configuration;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Position;
import io.exilius.model.entity.player.Right;
import io.exilius.util.Misc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TeleportInterface2 {

    private final Player player;

    public LinkedList<Position> previousTeleports = new LinkedList<>();

    public int categorySelected;

    public int getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(int category) {
        this.categorySelected = category;
    }

    public TeleportInterface2(Player player) {
        this.player = player;
    }

    public boolean handleInterfaceButtons(int buttonId) {

        switch (buttonId) {
            case 102209:
            case 102214:
            case 102219:
            case 102224:
            case 102229:
            case 102234:
            case 102239:
            case 102244:
            case 102249:
            case 102254:
            case 103003:
            case 103008:
            case 103013:
            case 103018:
            case 103023:
            case 103028:
            case 103033:
            case 103038:
            case 103043:
            case 103048: {
                handleTeleport(buttonId, getCategorySelected());
                return true;
            }

            case 102201:
            case 102202:
            case 102203: {
                handlePreviousTeleport(buttonId);
                return true;
            }

            case 102165: {
                if (getCategorySelected() == 1) {
                    return false;
                }

                switchCategory(1);
                return true;
            }
            case 102171: {
                if (getCategorySelected() == 2) {
                    return false;
                }
                switchCategory(2);
                return true;
            }
            case 102177: {
                if (getCategorySelected() == 3) {
                    return false;
                }
                switchCategory(3);
                return true;
            }
            case 102183: {
                if (getCategorySelected() == 4) {
                    return false;
                }
                switchCategory(4);
                return true;
            }
            case 102189: {
                if (getCategorySelected() == 5) {
                    return false;
                }
                switchCategory(5);
                return true;
            }
            case 102195: {
                if (getCategorySelected() == 6) {
                    return false;
                }
                switchCategory(6);
                return true;
            }

        }
        return false;
    }

    public final List<Integer> areaStrings = List.of(26323, 26328, 26333, 26338, 26343, 26348, 26353, 26358, 26363, 26368, 26373, 26378, 26383, 26388, 26393, 26398, 26403, 26408, 26413, 26418);
    public final List<Integer> areaTypeStrings = List.of(26324, 26329, 26334, 26339, 26344, 26349, 26354, 26359, 26364, 26369, 26374, 26379, 26384, 26389, 26394, 26399, 26404, 26409, 26414, 26419);
    public final List<Integer> descriptionStrings = List.of(26325, 26330, 26335, 26340, 26345, 26350, 26355, 26360, 26365, 26370, 26375, 26380, 26385, 26390, 26395, 26400, 26405, 26410, 26415, 26420);

    private void switchCategory(int category) {
        setCategorySelected(category);
        clearInterfaceData();

        if (!previousTeleports.isEmpty()) {
            refreshPrevious();
        }

        Stream<TeleportationData> teleports = Arrays.stream(TeleportationData.values()).filter(it -> it.getCategory() == getCategorySelected());

        AtomicInteger index = new AtomicInteger();

        teleports.forEach(it -> {
            player.getPA().sendString(it.getFormattedName(), areaStrings.get(index.get()));
            player.getPA().sendString(it.getDescription(), descriptionStrings.get(index.get()));
            player.getPA().sendString("Area type: " + (it.isSafe() ? "Safe" : "@red@Wilderness"), areaTypeStrings.get(index.get()));
            index.getAndIncrement();
        });
    }

    private void handleTeleport(int buttonId, int category) {
        TeleportationData data = TeleportationData.getDataForButtonId(buttonId, category);
        if(data.getDescription().equalsIgnoreCase("Regular donator zone lobby.")){
            if (player.getRights().isOrInherits(Right.REGULAR_DONATOR) || player.getRights().hasStaffPosition()) {
                player.getPA().startTeleport(3809, 2844, 0, "modern", false);
            } else {
                player.sendMessage("You need to be a regular donator to teleport here.");
            }
            return;
        }
        if(data.getDescription().equalsIgnoreCase("The Legendary donator zone.")){
            if (player.getRights().isOrInherits(Right.LEGENDARY_DONATOR) || player.getRights().hasStaffPosition()) {
                player.getPA().startTeleport(2846, 5089, 0, "modern", false);
            } else {
                player.sendMessage("You need a donator status of Legendary to tele here.");
            }
            return;
        }
        if(data.getDescription().equalsIgnoreCase("The Onyx donator zone.")){
            if (player.getRights().isOrInherits(Right.ONYX_CLUB)  || player.getRights().hasStaffPosition()) {
                player.getPA().startTeleport(Configuration.ONYX_ZONE_TELEPORT, "modern", false);
            } else {
                player.sendMessage("You need a donator status of Onyx to tele here.");
            }
            return;
        }
        if(data.getDescription().equalsIgnoreCase("The Mythic donator zone.")){
            if (player.amDonated >= 2500  || player.getRights().hasStaffPosition()) {
                player.getPA().startTeleport(2141, 9372, 0, "modern", false);
            } else {
                player.sendMessage("You need a donator status of Mythic to tele here.");
            }
            return;
        }
        player.getPA().startTeleport(data.getLocation(), "modern", false);

        //Adds the teleport to the first index of the list and shifts the others up 1.
        previousTeleports.addFirst(data.getLocation());
        //Refresh the strings on the interface.
        refreshPrevious();

        //Size is 4? Remove the last one to keep the 3 previous needed.
        if (previousTeleports.size() == 4) {
            previousTeleports.removeLast();
            refreshPrevious();
        }
    }

    public void handlePreviousTeleport(int buttonId) {
        int index = buttonId - 69_936 - 24218;

        if (!previousTeleports.isEmpty()) {
            TeleportationData tele = TeleportationData.getDataForPos(previousTeleports.get(index));
            player.getPA().startTeleport(tele.getLocation(), "modern", false);
        } else
            player.sendMessage("You should try teleporting somewhere first.");

    }

    private void refreshPrevious() {
        for (int i = 0, lineId = 26313; i < previousTeleports.size(); i++, lineId++) {
            String name = TeleportationData.getDataForPos(previousTeleports.get(i)).getFormattedName();
            player.getPA().sendString(Misc.formatEnum(name), lineId);
        }
    }

    private void clearInterfaceData() {
        for (int i = 26313; i <= 26315; i++) {
            player.getPA().sendString("---", i);
        }

        for (Integer stringId : areaStrings) {
            player.getPA().sendString("", stringId);
        }
        for (Integer areaStringId : areaTypeStrings) {
            player.getPA().sendString("", areaStringId);
        }
        for (Integer descripId : descriptionStrings) {
            player.getPA().sendString("", descripId);
        }
    }

    public enum TeleportationData {
        AL_KHARID(1, 102209, true, "Al-kharid", new Position(3294,3187,0)),
        BARROWS(1, 102214, true, "Barrows", new Position(3565,3306,0)),
        BURTHORPE(1, 102219, true, "Burthorpe", new Position(2899,3545,0)),
        CAMELOT(1, 102224, true, "A stone's throw east from Seers' Village.", new Position(2757,3478,0)),
        CHAMBERS(1, 102229, true, "Chambers of xeric", new Position(1233, 3564,0)),
        DUEL_ARENA(1, 102234, true, "Duel arena", new Position(3320,3235,0)),
        FALADOR(1, 102239, true, "One of the largest and most major cities.", new Position(2965,3377,0)),
        FIGHT_CAVES(1, 102244, true, "Fight caves", new Position(2438,5171,0)),
        LUMBRIDGE(1, 102249, true, "Lumbridge", new Position(3235,3218,0)),
        PEST_CONTROL(1, 102254, true, "Pest control", new Position(2659,2660,0)),
        RELLEKKA(1, 103003, true, "Rellekka", new Position(2660, 3652, 0)),
        RIMMINGTON(1, 103008, true, "Rimmington", new Position(2956, 3224, 0)),
        SHILO_VILLAGE(1, 103013, true, "Shilo Village", new Position(2856, 2959, 0)),
        SOPHANEM(1, 103018, true, "City of the Dead far south in the Kharidian Desert.", new Position(3305, 2787, 0)),
        LUMBRIDGE_COWS(1, 103023, true, "Shyzien assult", new Position(3254, 3266, 0)),
        TAVERLY(1, 103028, true, "A small Guthixian village north-west of Falador.", new Position(2930, 3452, 0)),
        THE_INFERNO(1, 103033, true, "The inferno", new Position(2496,5114,0)),
        VARROCK(1, 103038, true, "Avarrocka came into being in the year 700.", new Position(3212,3428,0)),
        WARRIORS_GUILD(1, 103043, true, "Warriors guild", new Position(2847,3541,0)),
        YANILLE(1, 103048, true, "The home of magicians and the Magic Guild.", new Position(2605, 3096, 0)),

        EAST_GREEN_DRAGONS(2, 102209, false, "", new Position(3348, 3647, 0)),
        WESTERN_DRAGONS(2, 102214, false, "", new Position(2999,3619,0)),
        MAGE_BANK(2, 102219, false, "Mage Bank", new Position(2540,4715,0)),
        CHAOS_ALTAR(2, 102224, false, "Chaos Altar.", new Position(2962,3820,0)),
        VENENATIS(2, 102229, false, "Wilderness spider", new Position(3341,3722,0)),
        BLACK_CHINCHOPMA(2, 102234, false, "Wilderness hunting", new Position(3138,3785,0)),
        KING_BLACK_DRAGON(2, 102239, false, "King black dragon boss lair.", new Position(2271,4680,0)),
        RECENANT_CAVES(2, 102244, false, "The cave with revenants and more in it", new Position(3241,10234,0)),
        ELDER_DRUIDS(2, 102249, false, "Elder Chaos Druids", new Position(3235, 3635,0)),
        WILDY_RESOURCE_AREA(2, 102254, false, "Wilderness resoruce area", new Position(3184,3946,0)),

        ROCK_CRABS(3, 102209, true, "", new Position(2671,3714,0)),
        SLAYER_TOWER(3, 102214, true, "", new Position(3428,3537,0)),
        FREMENNIK_DUNGEON(3, 102219, true, "Fremennik Slayer Dungeon", new Position(2805,10001,0)),
        WATERBIRTH_DUNGEON(3, 102224, true, "Waterbirth Dungeon.", new Position(1893,4409,0)),
        NEITIZNOT_ISLAND(3, 102229, true, "Neitiznot Island", new Position(2319, 3801,0)),
        ASGARNIAN_ICE_DUNGEON(3, 102234, true, "Asgarnian Ice Dungeon", new Position(3053, 9578, 0)),
        TAVERLY_DUNGEON(3, 102239, true, "Taverly Dungeon.", new Position(2884, 9798, 0)),
        BRIMHAVEN_DUNGEON(3, 102244, true, "Brimhaven Dungeon", new Position(2709,9564,0)),
        LITHKREN_VAULT(3, 102249, true, "Lithkren Vault", new Position(1568,5063,0)),
        DESERT_BANDITS(3, 102254, true, "Desert bandits", new Position(3176,2987,0)),
        EDGEVILLE_DUNGEON(3, 103003, true, "Edgeville Dungeon", new Position(3097, 9870, 0)),
        ELF_WARRIORS(3, 103008, true, "Elf Warriors", new Position(2897, 2725, 0)),
        ANCIENT_CAVERN(3, 103013, true, "Ancient Cavern", new Position(1765, 5365, 1)),
        KOUREND_CATACOMBS(3, 103018, true, "Kourend Catacombs", new Position(1664, 10049, 0)),
        KARUULM_DUNGEON (3, 103023, true, "Karuulm Dungeon", new Position(1311, 10204, 0)),
        SMOKE_DUNGEON(3, 103028, true, "Smoke Dungeon.", new Position(3294, 9373, 0)),
        KALPHITE_CAVES(3, 103033, true, "Kalphites Cave", new Position(3505,9493,0)),
        CAVE_HORRORS(3, 103038, true, "Cave Horrors.", new Position(3746, 9374,0)),
        WYVERN_CAVE(3, 103043, true, "Wyvern Cave", new Position(3603, 10224, 0)),

        GODWARS_DUNGEON(4, 102209, true, "", new Position(2879,5312,2)),
        ZULRAH(4, 102214, true, "", new Position(2201,3056,0)),
        ABYSSAL_SIRE(4, 102219, true, "Abyssal Sire", new Position(3040,4768,0)),
        VORKATH(4, 102224, true, "Vorkath the Blue Dragon.", new Position(2271,4049,0)),
        DAGANNOTH_KINGS(4, 102229, true, "Dagannoth Kings", new Position(1912, 4367, 0)),
        KRAKEN(4, 102234, true, "Kraken boss", new Position(2276,10014,0)),
        KALPHITE_QUEEN(4, 102239, true, "Kalphite Quest Boss.", new Position(3506,9494,0)),
        BARRELCHEST(4, 102244, true, "Barrelchest Boss", new Position(2438,5171,0)),
        LIZARDMEN_SHAMAN(4, 102249, true, "Lizardman Shaman", new Position(1470, 3687, 0)),
        DEMONIC_GORILLA(4, 102254, true, "Demonic Gorilla's", new Position(2155,5659,0)),
        CORPERAL_BEAST(4, 103003, true, "Corperal Beast Boss", new Position(2970,4384,2)),
        CERBERUS(4, 103008, true, "Cerberus", new Position(1308, 1250, 0)),
        HYDRA_BOSS(4, 103013, true, "Hydra Boss", new Position(1354,10259, 0)),
        THERMONUCLEAR_SMOKE_DEVIL(4, 103018, true, "Thermonuclear smoke devil.", new Position(2380, 9452, 0)),
        CHAMBERS_OF_XERIC(4, 103023, true, "Chambers of Xeric", new Position(3033, 6067, 0)),
        THEATRE_OF_BLOOD(4, 103028, true, "Theatre of Blood", new Position(3671, 3219, 0)),

        WOODCUTTING_GUILD(5, 102209, true, "Woodcutting guild", new Position(1658, 3505,0)),
        GNOME_AGILITY_COURSE(5, 102214, true, "Gnome agility course", new Position(2477, 3438, 0)),
        BARBARIAN_AGILITY_COURSE(5, 102219, true, "barbarian agility course", new Position(2552, 3562, 0)),
        MINING_GUILD(5, 102224, true, "Mining guild.", new Position(3056,9777,0)),
        CRAFTING_GUILD(5, 102229, true, "Crafting Zone", new Position(2933, 3287,0)),
        FARMING(5,102234, true,"Farming", new Position(3053, 3301, 0)),
        HUNTER(5, 102239, true, "Hunter", new Position(3560, 4010, 0)),
        THIVING_AREA(5, 102244, true, "Thiving area.", new Position(3042,4969,1)),
        COOKING_GUILD(5, 102249, true, "Cooking Guild", new Position(3143,3442,0)),
        FISHING_GUILD(5, 102254, true, "Fishing Guild", new Position(2593,3420,0)),
        PURO_PURO(5, 103003, true, "Puro-Puro", new Position(2594, 4320, 0)),
        RUNECRAFTING(5, 103008, true, "ZMI Altar", new Position(2453, 3231, 0)),

        DONATOR_ZONE(6, 102209, true, "Regular donator zone lobby.", new Position(1889, 5470, 0)),
        LEGENDARY_DONATOR_ZONE(6, 102214, true, "The Legendary donator zone.", new Position(3042, 4407)),
        ONYX_DONATOR_ZONE(6, 102219, true, "The Onyx donator zone.", Configuration.ONYX_ZONE_TELEPORT),
        MYTHIC_DONATOR_ZONE(6, 102224, true, "The Mythic donator zone.", new Position(2141, 9372, 0))
        ;

        private final int category;
        private final int buttonId;
        private final boolean safe;
        private final String description;
        private final Position location;

        TeleportationData(int category, int buttonId, boolean safe, String description, Position location) {
            this.category = category;
            this.buttonId = buttonId;
            this.safe = safe;
            this.description = description;
            this.location = location;
        }

        public int getCategory() {
            return category;
        }

        public int getButtonId() {
            return buttonId;
        }

        public boolean isSafe() {
            return safe;
        }

        public String getDescription() {
            return description;
        }

        public Position getLocation() {
            return location;
        }

        //Returns the teleport data based off a specific position for the previous teleports.
        public static TeleportationData getDataForPos(Position position) {
            return Arrays.stream(values()).filter(x -> x.getLocation() == position).findFirst().orElse(null);
        }

        public static TeleportationData getDataForButtonId(int buttonId, int category) {
            return Arrays.stream(TeleportationData.values()).filter(x -> x.getButtonId() == buttonId && x.getCategory() == category).findFirst().orElse(null);
        }

        //Formats the enum name value.
        public String getFormattedName() {
            String name = name().toLowerCase().replaceAll("_", " ");
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }
}

