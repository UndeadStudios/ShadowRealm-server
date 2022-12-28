package io.exilius.content.teleportation;

import io.exilius.Configuration;
import io.exilius.Server;
import io.exilius.content.achievement_diary.impl.ArdougneDiaryEntry;
import io.exilius.content.achievement_diary.impl.FaladorDiaryEntry;
import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
import io.exilius.content.achievement_diary.impl.LumbridgeDraynorDiaryEntry;
import io.exilius.content.events.monsterhunt.MonsterHunt;
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
            case 103048:
            case 106053:
            case 106057:
            case 106061:
            case 106065:
            case 106069:
            case 106073:
            case 106077:
            case 106081:
            case 106085:
            case 106089:
            case 106093:
            case 106097:
            case 106105:
            case 106109:
            case 106113:
            case 106117:
            case 106121:
            case 106125:
            case 106129:{
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
            case 106018: {
                if (getCategorySelected() == 7) {
                    return false;
                }
                switchCategory(7);
                return true;
            }
            case 106024: {
                if (getCategorySelected() == 8) {
                    return false;
                }
                switchCategory(8);
                return true;
            }
            case 106036: {
                if (getCategorySelected() == 9) {
                    return false;
                }
                switchCategory(9);
                return true;
            }
        }
        return false;
    }

    public final List<Integer> areaStrings = List.of(
            26323, 26328, 26333, 26338, 26343, 26348, 26353, 26358, 26363, 26368,
            26373, 26378, 26383, 26388, 26393, 26398, 26403, 26408, 26413, 26418,
            27190, 27194, 27198, 27202, 27206, 27210, 27214, 27218, 27214, 27218,
            27222, 27226, 27230, 27234, 27238, 27242, 27246, 27250, 27254, 27258, 27262, 27266
    );
    public final List<Integer> areaTypeStrings = List.of(
            26324, 26329, 26334, 26339, 26344, 26349, 26354, 26359, 26364, 26369,
            26374, 26379, 26384, 26389, 26394, 26399, 26404, 26409, 26414, 26419,
            27191, 27195, 27199, 27203, 27207, 27211, 27215, 27219, 27223, 27227,
            27231, 27235, 27239, 27243, 27247, 27251, 27255, 27259, 27263, 27267

    );
    public final List<Integer> descriptionStrings = List.of(
            26325, 26330, 26335, 26340, 26345, 26350, 26355, 26360, 26365, 26370,
            26375, 26380, 26385, 26390, 26395, 26400, 26405, 26410, 26415, 26420,
            27192, 27196, 27200, 27204, 27208, 27212, 27215, 27220, 27224, 27228,
            27232, 27236, 27240, 27244, 27248, 27252, 27256, 27258, 27260, 27264, 27268
    );

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

    public void openInterface(Player c){
        c.getPA().showInterface(26263);
        switchCategory(1);
    }
    private void handleTeleport(int buttonId, int category) {
        TeleportationData data = TeleportationData.getDataForButtonId(buttonId, category);
        if (Server.getMultiplayerSessionListener().inAnySession(player)) {
            player.sendMessage("You cannot do that right now.");
            return;
        }
        if (player.wildLevel > 20) {
            player.sendMessage("@red@You cannot teleport above 20 wilderness.");
            player.getPA().closeAllWindows();
            return;
        }
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
        if(data.getDescription().equalsIgnoreCase("Camelot")){
            player.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.CAMELOT_TELEPORT);
        }
        if(data.getDescription().equalsIgnoreCase("Ardougne")){
            player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.TELEPORT_ARDOUGNE);
        }
        if(data.getDescription().equalsIgnoreCase("Catherby")){
            player.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.CATHERBY_TELEPORT);
        }
        if(data.getDescription().equalsIgnoreCase("Falador")){
            player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.TELEPORT_TO_FALADOR);
        }
        if(data.getDescription().equalsIgnoreCase("Lumbridge")){
            player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.LUMBRIDGE_TELEPORT);
        }
        if(data.getDescription().equalsIgnoreCase("Godwars")){
            player.getDH().sendDialogues(4487, -1);
            return;
        }
        if(data.getDescription().equalsIgnoreCase("Catacombs.")){
            player.getPA().movePlayer(1664, 10050);
            player.getPA().showInterface(33900);
            player.sendMessage("@cr10@ Use @pur@::level2@bla@ to be sent to @pur@next catacombs level.");
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

        Home(1, 102209, true, "Home", new Position(3091, 3490, 0)),
        Slayer_masters(1, 102214, true, "Slayer Masters", new Position(3083, 3510, 0)),
        Hispori(1, 102219, true, "Hispori", new Position(3075, 3499, 0)),
        Wildy_event(1, 102224, true, "Wildy event", new Position(MonsterHunt.getCurrentLocation().getX(), MonsterHunt.getCurrentLocation().getY(), 0)),
        Ganbling_Zone(1, 102229, true, "Ganbling Zone", new Position(2129, 9489, 0)),
        Skilling_Island(1, 102234, true, "Skilling Island", new Position(3803, 3538, 0)),

        AL_KHARID(2, 102209, true, "Al Kharid", new Position(3293, 3179, 0)),
        Arceuus(2, 102214, true, "Arceuus", new Position(1686,3746,0)),
        Ardougne(2, 102219, true, "Ardougne", new Position(2662, 3305, 0)),
        Barbarian_Village(2, 102224, true, "Barbarian Village", new Position(3083, 3424, 0)),
        Brimhaven(2, 102229, true, "Brimhaven", new Position(2802, 3178, 0)),
        Camelot(2, 102234, true, "Camelot", new Position(2757, 3478, 0)),
        Catherby(2, 102239, true, "Catherby", new Position(2804, 3432, 0)),
        Darkmeyer(2, 102244, true, "Darkmeyer", new Position(3592, 3337, 0)),
        Draynor(2, 102249, true, "Draynor", new Position(3105, 3249, 0)),
        Edgeville(2, 102254, true, "Edgeville", new Position(1999, 9313, 0)),
        Falador(2, 103003, true, "Falador", new Position(2964, 3378, 0)),
        Hosidius(2, 103008, true, "Hosidius", new Position(1758,3604,0)),
        Karamja(2, 103013, true, "Karamja", new Position(2948, 3147, 0)),
        Kebos_Lowlands(2, 103018, true, "Kebos Lowlands", new Position(1310, 3618, 0)),
        Keldagrim(2, 103023, true, "Keldagrim", new Position(2889, 10224, 0)),
        Lovakengj(2, 103028, true, "Lovakengj", new Position(1508,3783,0)),
        Lumbridge(2, 103033, true, "Lumbridge", new Position(3222, 3218)),
        Miscellania(2, 103038, true, "Miscellania", new Position(2531, 3856, 0)),
        Neitiznot(2, 103043, true, "Neitiznot", new Position(2321, 3804, 0)),
        Port_Piscarilius(2, 103048, true, "Port Piscarilius", new Position(1809,3776,0)),
        Rimmington(2, 106053, true, "Rimmington", new Position(2958, 3215, 0)),
        Shayzien(2, 106057, true, "Shayzien", new Position(1558,3561,0)),
        Taverley(2, 106061, true, "Taverley", new Position(2928, 3451, 0)),
        West_Ardougne(2, 106065, true, "West Ardougne", new Position(2553, 3300, 0)),
        Yanille(2, 106069, true, "Yanille", new Position(2606, 3093, 0)),
        Varrock(2, 106073, true, "Varrock", new Position(3210, 3424, 0)),

        Catacombs(3, 102209, true, "Catacombs.", new Position(1664, 10050,0)),
        Sand_Crabs(3, 102214, true, "Sand Crabs", new Position(1691, 3475)),
        rock_crabs(3, 102219, true, "Rock Crabs", new Position(2673, 3710,0)),
        cows(3, 102224, true, "Cows", new Position(3260, 3272,0)),
        Bobs_island(3, 102229, true, "Bob's Island", new Position(2524, 4775,0)),
        Jormungands_Prison(3, 102234, true, "Jormungand's Prison", new Position(2471, 10420, 0)),
        all_giants(3, 102239, true, "All Giants.", new Position(1432, 9911, 0)),
        Forthos_Dungeon(3, 102244, true, "Forthos Dungeon", new Position(1800, 9948,0)),
        Elf_Warriors(3, 102249, true, "Elf Warriors", new Position(2897, 2725,0)),
        Ogers(3, 102254, true, "Ogers", new Position(2528, 3376,0)),
        Dagannoths(3, 103003, true, "Dagannoths", new Position(2442, 10147, 0)),
        ANCIENT_CAVERN(3, 103008, true, "Ancient Cavern", new Position(1765, 5365, 1)),
        SLAYER_TOWER(3, 103013, true, "Slayer Tower", new Position(3428, 3538, 0)),
        Fremennik_Slayer_Dungeon(3, 103018, true, "Fremennik Slayer Dungeon", new Position(2807, 10002, 0)),
        Taverly_Dungeon(3, 103023, true, "Taverly Dungeon", new Position(2883, 9800, 0)),
        Stronghold_Cave(3, 103028, true, "Stronghold Cave", new Position(2452, 9820, 0)),
        Mount_Karuulm(3, 103033, true, "Mount Karuulm", new Position(1311, 3795, 0)),
        Smoke_Devils(3, 103038, true, "Smoke Devils", new Position(2404, 9415, 0)),
        asgarnian_Ice_Dungeon(3, 103043, true, "Asgarnian Ice Dungeon", new Position(3053, 9578, 0)),
        Brimhaven_Dungeon(3, 103048, true, "Brimhaven Dungeon", new Position(2709, 9476, 0)),

        Barrelchest(4, 102209, true, "Barrelchest", new Position(2903, 3612, 0)),
        Bryophyta(4, 102214, true, "Bryophyta", new Position(3174, 9898, 0)),
        Obor(4, 102219, true, "Obor", new Position(3097, 9833, 0)),
        Dagannoth_Kings(4, 102224, true, "Dagannoth Kings", new Position(1913, 4367, 0)),
        Giant_Mole(4, 102229, true, "Giant Mole", new Position(2993, 3376, 0)),
        Kalphite_Queen(4, 102234, true, "Kalphite Queen", new Position(3508, 9492, 0)),
        Lizardman_Shaman(4, 102239, true, "Lizardman Shaman", new Position(1453,3692,0)),
        Sarachnis(4, 102244, true, "Sarachnis", new Position(1842, 9926, 0)),
        Grotesque_Guardians(4, 102249, true, "Grotesque Guardians", new Position(3428, 3541, 2)),
        Thermonuclear_Smoke_Devil(4, 102254, true, "Thermonuclear Smoke Devil", new Position(2404, 9415, 0)),
        Kraken(4, 103003, true, "Kraken", new Position(2280, 10016, 0)),
        Demonic_Gorillas(4, 103008, true, "Demonic Gorillas", new Position(2124, 5660, 0)),
        Godwars(4, 103013, true, "Godwars", new Position(2948, 3147, 0)),
        Corporeal_Beast(4, 103018, true, "Corporeal Beast", new Position(2964, 4382, 2)),
        Zulrah(4, 103023, true, "Zulrah", new Position(2203, 3056, 0)),
        Cerberus(4, 103028, true, "Cerberus", new Position(1310, 1248, 0)),
        Abyssal_Sire(4, 103033, true, "Abyssal Sire", new Position(3038, 4767, 0)),
        Vorkath(4, 103038, true, "Vorkath", new Position(2272, 4050, 0)),
        Alchemical_hydra(4, 103043, true, "Alchemical hydra", new Position(1354, 10259, 0)),
        The_Nightmare(4, 103048, true, "The Nightmare", new Position(3808, 9755, 1)),
        Nex(4, 106053, true, "Nex", new Position(2856, 5220, 0)),
        King_black_dragon(4, 106057, false, "King Black Dragon @red@(42 Wild)", new Position(3005, 3849, 0)),
        Vetion(4, 106061, false, "Vet'ion @red@(40 Wild)", new Position(3200, 3794, 0)),
        Scorpia(4, 106065, false, "Scorpia @red@(54 Wild)", new Position(3233, 3945, 0)),
        Venenatis(4, 106069, false, "Venenatis @red@(28 Wild)", new Position(3345, 3754, 0)),
        Chaos_Elemental(4, 106073, false, "Chaos Elemental @red@(50 Wild)", new Position(3285, 3925, 0)),
        Chaos_Fanatic(4, 106077, false, "Chaos Fanatic @red@(41 Wild)", new Position(2978, 3833, 0)),
        Chaos_Archaeologist(4, 106081, false, "Crazy Archaeologist @red@(23 Wild)", new Position(2984, 3713, 0)),

        Theatre_of_Blood(6, 102209, true, "Theatre of Blood", new Position(3671, 3219, 0)),
        Chambers_of_Xeric(6, 102214, true, "Chambers of Xeric", new Position(3033, 6067, 0)),
        Fight_Caves(6, 102219, true, "Fight Caves", new Position(2444, 5179, 0)),
        The_Inferno(6, 102224, true, "The Inferno", new Position(2437, 5126, 0)),
        barrows(6, 102229, true, "Barrows", new Position(3565, 3316, 0)),
        Duel_arena(6,102234, true,"Duel Arena", new Position(3366, 3266, 0)),
        Warriors_guild(6, 102239, true, "Warriors Guild", new Position(2874, 3546, 0)),
        Soul_wars(6, 102244, true, "Soul Wars", new Position(2209, 2858, 0)),
        Pest_Control(6, 102249, true, "Pest Control", new Position(2660, 2648, 0)),
        Clan_Wars(6, 102254, true, "Clan Wars", new Position(3387, 3158, 0)),
        Outlast(6, 103003, true, "Outlast", new Position(3096, 3509, 0)),
        Mage_Arena(6, 103008, true, "Mage Arena", new Position(2541, 4716, 0)),
        Wintertodt(6, 103013, true, "Wintertodt", new Position(1631,3947,0)),
        Horror_From_The_Deep(6, 103018, true, "Horror From The Deep [Quest]", new Position(2508, 3641, 0)),
        Monkey_Madness(6, 103023, true, "Monkey Madness [Quest]", new Position(2467, 3494, 0)),

        WOODCUTTING_GUILD(8, 102209, true, "Woodcutting guild", new Position(1658, 3505,0)),
        GNOME_AGILITY_COURSE(8, 102214, true, "Gnome agility course", new Position(2477, 3438, 0)),
        BARBARIAN_AGILITY_COURSE(8, 102219, true, "barbarian agility course", new Position(2552, 3562, 0)),
        MINING_GUILD(8, 102224, true, "Mining guild.", new Position(3056,9777,0)),
        CRAFTING_GUILD(8, 102229, true, "Crafting Zone", new Position(2933, 3287,0)),
        FARMING(8,102234, true,"Farming", new Position(3053, 3301, 0)),
        HUNTER(8, 102239, true, "Hunter", new Position(3560, 4010, 0)),
        THIVING_AREA(8, 102244, true, "Thiving area.", new Position(3042,4969,1)),
        COOKING_GUILD(8, 102249, true, "Cooking Guild", new Position(3143,3442,0)),
        FISHING_GUILD(8, 102254, true, "Fishing Guild", new Position(2593,3420,0)),
        PURO_PURO(8, 103003, true, "Puro-Puro", new Position(2594, 4320, 0)),
        RUNECRAFTING(8, 103008, true, "ZMI Altar", new Position(2453, 3231, 0)),

        DONATOR_ZONE(9, 102209, true, "Regular donator zone lobby.", new Position(1889, 5470, 0)),
        LEGENDARY_DONATOR_ZONE(9, 102214, true, "The Legendary donator zone.", new Position(3042, 4407)),
        ONYX_DONATOR_ZONE(9, 102219, true, "The Onyx donator zone.", Configuration.ONYX_ZONE_TELEPORT),
        MYTHIC_DONATOR_ZONE(9, 102224, true, "The Mythic donator zone.", new Position(2141, 9372, 0))
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

