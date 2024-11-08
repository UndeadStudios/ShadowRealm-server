package io.shadowrealm.content.skills.thieving;

import com.google.common.collect.Lists;
import io.shadowrealm.Server;
import io.shadowrealm.content.SkillcapePerks;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.achievement_diary.impl.*;
import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.event.eventcalendar.EventChallenge;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Boundary;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.model.items.GameItem;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.model.world.objects.GlobalObject;
import io.shadowrealm.util.Location3D;
import io.shadowrealm.util.Misc;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A representation of the thieving skill. Support for both object and npc actions will be supported.
 *
 * @author Jason MacKeigan
 * @date Feb 15, 2015, 7:12:14 PM
 */
public class Thieving {

    private static final int[] rogueOutfit = {5553, 5554, 5555, 5556, 5557};

    /**
     * The managing player of this class
     */
    private final Player player;

    /**
     * The last interaction that player made that is recorded in milliseconds
     */
    private long lastInteraction;

    /**
     * The constant delay that is required inbetween interactions
     */
    private static final long INTERACTION_DELAY = 1_500L;

    /**
     * The stealing animation
     */
    private static final int ANIMATION = 881;

    /**
     * Constructs a new {@link Thieving} object that manages interactions between players and stalls, as well as players and non playable characters.
     *
     * @param player the visible player of this class
     */
    public Thieving(final Player player) {
        this.player = player;
    }

    /**
     * A method for stealing from a stall
     *
     * @param stall    the stall being stolen from
     * @param location the location of the stall
     */
    public void steal(StallData stall, int objectId, Location3D location) {
        if (System.currentTimeMillis() - lastInteraction < INTERACTION_DELAY) {
            //player.sendMessage("You must wait a few more seconds before you can steal again.");
            return;
        }
        if (player.getItems().freeSlots() == 0) {
            player.sendMessage("You need at least one free slot to steal from this.");
            return;
        }

        if (player.playerLevel[Skill.THIEVING.getId()] < stall.level) {
            player.sendMessage("You need a thieving level of " + stall.level + " to steal from this.");
            return;
        }
//		if (Misc.random(200) == 0 && player.getInterfaceEvent().isExecutable()) {
//			player.getInterfaceEvent().execute();
//			return;
//		}
        player.getEventCalendar().progress(EventChallenge.THIEVE_X_STALLS);
        switch (stall) {
            case Baker:
                if (Boundary.isIn(player, Boundary.ARDOUGNE_BOUNDARY)) {
                    player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.STEAL_CAKE);
                }
                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.THEIVE_BAKER_STALL.getDailyTask().getTaskName())){
					DailyTaskHandler.Companion.handleProgress(player, 1);
                    System.out.println("Theive from the baker stall");
                }
                break;
            case Gem2:
            case Gem:
                if (Boundary.isIn(player, Boundary.ARDOUGNE_BOUNDARY)) {
                    player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.STEAL_GEM_ARD);
                }
                if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                    player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.STEAL_GEM_FAL);
                }
                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.THEIVE_GEM_STALL.getDailyTask().getTaskName())) {
					DailyTaskHandler.Companion.handleProgress(player, 1);
                    System.out.println("Theive from the baker stall");
                }
                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.THEIVE_GEM_STALL.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case Scimitar:
                break;
            case Silver:
                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.THEIVE_SILVER_STALL.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player, 1);
                }
                break;
            case FUR:
                if (Boundary.isIn(player, Boundary.ARDOUGNE_BOUNDARY)) {
                    player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.STEAL_FUR);
                }
                break;
//		case Gold:
            default:
                break;
        }
        player.facePosition(location.getX(), location.getY());
        if (Misc.random(stall.depletionProbability) == 0) {
            GlobalObject stallObj = Server.getGlobalObjects().get(objectId, location.getX(), location.getY(), location.getZ());
            if (stallObj != null) {
                Server.getGlobalObjects().add(new GlobalObject(stall.depletionobject, location.getX(), location.getY(), location.getZ(), stallObj.getFace(), 10, 8, stallObj.getObjectId()));
            }
        }

        GameItem item = stall.getRandomItem();
        int petRate = player.skillingPetRateScroll ? (int) (stall.petChance * .75) : stall.petChance;
        if (Misc.random(petRate) == 20 && player.getItems().getItemCount(20663, false) == 0 && player.petSummonId != 20663) {
            PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] <col=255>" + player.getDisplayName() + "</col> now goes hand in hand with a <col=CC0000>Rocky</col> pet!");
            player.getItems().addItemUnderAnyCircumstance(20663, 1);
            player.getCollectionLog().handleDrop(player, 5, 20663, 1);
        }

        player.startAnimation(ANIMATION);
        boolean maxCape = SkillcapePerks.THIEVING.isWearing(player) || SkillcapePerks.isWearingMaxCape(player);
        if (item != null) {
            player.getItems().addItem(item.getId(), maxCape ? item.getAmount() * 2 : item.getAmount());
            player.sendMessage("You steal a " + ItemCacheDefinition.forID(item.getId()).getName() + " from the stall.");
        } else {
            player.sendMessage("You were unable to find anything useful.");
        }
        player.getPA().addSkillXPMultiplied((int) (stall.experience * (1 + (getRoguesPieces() * 0.12))), Skill.THIEVING.getId(), true);
        Achievements.increase(player, AchievementType.THIEV, 1);
        lastInteraction = System.currentTimeMillis();
    }

    public int getRoguesPieces() {
        int pieces = 0;
        for (int aRogueOutfit : rogueOutfit) {
            if (player.getItems().isWearingItem(aRogueOutfit)) {
                pieces++;
            }
        }
        return pieces;
    }

    /**
     * A method for pick pocketing npc's
     *
     * @param pickpocket the pickpocket type
     * @param npc        the npc being pick pocketed
     */
    public void steal(Pickpocket pickpocket, NPC npc) {
        if (System.currentTimeMillis() - lastInteraction < INTERACTION_DELAY) {
            //player.sendMessage("You must wait a few more seconds before you can steal again.");
            return;
        }
        if (player.getItems().freeSlots() == 0) {
            player.sendMessage("You need at least one free slot to steal from this npc.");
            return;
        }
        if (player.playerLevel[Skill.THIEVING.getId()] < pickpocket.level) {
            player.sendMessage("You need a thieving level of " + pickpocket.level + " to steal from this npc.");
            return;
        }
//		if (Misc.random(200) == 0 && player.getInterfaceEvent().isExecutable()) {
//			player.getInterfaceEvent().execute();
//			return;
//		}
        /**
         * Incorporate chance for failure
         */
        switch (pickpocket) {
            case MASTER_FARMER:
                if (Boundary.isIn(player, Boundary.ARDOUGNE_BOUNDARY)) {
                    player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.PICKPOCKET_ARD);
                }
                if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                    player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.PICKPOCKET_MASTER_FARMER_FAL);
                }
                if (Boundary.isIn(player, Boundary.DRAYNOR_BOUNDARY)) {
                    player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.PICKPOCKET_FARMER_DRAY);
                }
                break;
            case MAN:
                if (Boundary.isIn(player, Boundary.FALADOR_BOUNDARY)) {
                    player.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.PICKPOCKET_MAN);
                }
                if (Boundary.isIn(player, Boundary.LUMRIDGE_BOUNDARY)) {
                    player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.PICKPOCKET_MAN_LUM);
                }
                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.PICKPOCKET_MAN.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player,1);
                    System.out.println("Theive from a Man ");
                }
                break;
            case GNOME:
                player.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.PICKPOCKET_GNOME);
                break;
            case HERO:
                player.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.PICKPOCKET_HERO);

                if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.PICKPOCKET_HERO.getDailyTask().getTaskName())) {
                    DailyTaskHandler.Companion.handleProgress(player,1);
                    System.out.println("Theive from a Hero");
                }
                break;
            case MENAPHITE_THUG:
                player.getDiaryManager().getDesertDiary().progress(DesertDiaryEntry.PICKPOCKET_THUG);
                break;
            default:
                break;

        }

        player.facePosition(npc.getX(), npc.getY());
        player.startAnimation(ANIMATION);
        GameItem item = pickpocket.getRandomItem();
        boolean maxCape = SkillcapePerks.THIEVING.isWearing(player) || SkillcapePerks.isWearingMaxCape(player);
        if (item != null) {
            player.getItems().addItem(item.getId(), maxCape ? item.getAmount() * 2 : item.getAmount());
        } else {
            player.sendMessage("You were unable to find anything useful.");
        }
        int petRate = player.skillingPetRateScroll ? (int) (pickpocket.petChance * .75) : pickpocket.petChance;
        if (Misc.random(petRate) == 20 && player.getItems().getItemCount(20663, false) == 0 && player.petSummonId != 20663) {
            PlayerHandler.executeGlobalMessage("[<col=CC0000>News</col>] @cr20@ <col=255>" + player.getDisplayName() + "</col> now goes hand in hand with a <col=CC0000>Rocky</col> pet!");
            player.getItems().addItemUnderAnyCircumstance(20663, 1);
            player.getCollectionLog().handleDrop(player, 5, 20663, 1);
        }
        Achievements.increase(player, AchievementType.THIEV, 1);
        player.getPA().addSkillXPMultiplied((int) (pickpocket.experience * (1 + (getRoguesPieces() * 0.12))), Skill.THIEVING.getId(), true);
        lastInteraction = System.currentTimeMillis();
    }

    private enum Rarity {
        ALWAYS(0), COMMON(5), UNCOMMON(10), RARE(15), VERY_RARE(25), SUPER_RARE(300);

        /**
         * The rarity
         */
        private final int rarity;

        /**
         * Creates a new rarity
         *
         * @param rarity the rarity
         */
        Rarity(int rarity) {
            this.rarity = rarity;
        }
    }

    @SuppressWarnings("serial")
    public enum Pickpocket {
        MAN(1, 8, 20000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 750), new GameItem(995, 1000), new GameItem(995, 1250)));
            }
        }),
        FARMER(10, 14, 20000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.COMMON, Arrays.asList(new GameItem(995, 750)));
                put(Rarity.UNCOMMON, Arrays.asList(new GameItem(Items.POTATO_SEED, 1)));
            }
        }),

        DRUNKEN_DWARF(75, 90, 20000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(
                        new GameItem(995, 950),
                        new GameItem(Items.COAL),
                        new GameItem(Items.IRON_ORE),
                        new GameItem(Items.BRONZE_BAR),
                        new GameItem(Items.SEAWEED),
                        new GameItem(Items.BEER),
                        new GameItem(Items.BRONZE_PICKAXE),
                        new GameItem(Items.RAW_COD)));

                put(Rarity.VERY_RARE, Arrays.asList(
                        new GameItem(995, 7850),
                        new GameItem(Items.RUNITE_ORE),
                        new GameItem(Items.COAL_NOTED, 25),
                        new GameItem(Items.RUNITE_BAR),
                        new GameItem(Items.RUNE_PICKAXE)));
            }
        }),

        MASTER_FARMER(38, 43, 20000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.COMMON, Lists.newArrayList(
                        new GameItem(Items.GUAM_SEED, 3), new GameItem(Items.MARRENTILL_SEED, 3), new GameItem(Items.TARROMIN_SEED, 3),
                        new GameItem(Items.HARRALANDER_SEED, 3),

                        new GameItem(Items.POTATO_SEED, 4), new GameItem(Items.ONION_SEED, 4), new GameItem(Items.CABBAGE_SEED, 4),
                        new GameItem(Items.TOMATO_SEED, 4), new GameItem(Items.SWEETCORN_SEED, 4), new GameItem(Items.STRAWBERRY_SEED, 4),
                        new GameItem(Items.WATERMELON_SEED, 4), new GameItem(Items.MARIGOLD_SEED, 4), new GameItem(Items.NASTURTIUM_SEED, 4),
                        new GameItem(Items.WOAD_SEED, 4), new GameItem(Items.LIMPWURT_SEED, 4)
                ));

                put(Rarity.UNCOMMON, Lists.newArrayList(
                        new GameItem(Items.RANARR_SEED, 3), new GameItem(Items.TOADFLAX_SEED, 3),
                        new GameItem(Items.IRIT_SEED, 3), new GameItem(Items.AVANTOE_SEED, 3),
                        new GameItem(Items.KWUARM_SEED, 3), new GameItem(Items.SNAPDRAGON_SEED, 3),
                        new GameItem(Items.CADANTINE_SEED, 3), new GameItem(Items.LANTADYME_SEED, 3),
                        new GameItem(Items.DWARF_WEED_SEED, 3), new GameItem(Items.TORSTOL_SEED, 3)
                ));
            }

        }), MENAPHITE_THUG(65, 137, 18000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 1000), new GameItem(995, 800), new GameItem(995, 950)));
            }
        }), PALADIN(70, 151, 18000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 180), new GameItem(Items.CHAOS_RUNE, 2)));
            }
        }),
        GNOME(75, 198, 18000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 1200), new GameItem(995, 800), new GameItem(995, 1250)));
                put(Rarity.UNCOMMON, Arrays.asList(new GameItem(444), new GameItem(557), new GameItem(13431, 5)));
            }
        }), HERO(80, 273, 17000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.ALWAYS, Arrays.asList(new GameItem(995, 1500), new GameItem(995, 1800), new GameItem(995, 3500)));
                put(Rarity.UNCOMMON, Arrays.asList(new GameItem(560, 2), new GameItem(565), new GameItem(444), new GameItem(1601)));
            }
        }), VYRE(95, 300, 12000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.COMMON, Arrays.asList(new GameItem(995, 1500), new GameItem(995, 1800), new GameItem(995, 3500), new GameItem(Items.BLOOD_PINT, 1)));
                put(Rarity.UNCOMMON, Arrays.asList(new GameItem(Items.UNCUT_RUBY, 1), new GameItem(Items.BLOOD_RUNE, 4)));
                put(Rarity.RARE, Arrays.asList(new GameItem(Items.DIAMOND, 1), new GameItem(Items.COOKED_MYSTERY_MEAT, 1)));
                put(Rarity.SUPER_RARE, Arrays.asList(new GameItem(Items.BLOOD_SHARD, 1)));
            }
        }), ELF(85, 353, 17000, new HashMap<Rarity, List<GameItem>>() {
            {
                put(Rarity.COMMON, Arrays.asList(new GameItem(995, 1500), new GameItem(995, 1800), new GameItem(995, 3500), new GameItem(Items.DEATH_RUNE, 2), new GameItem(Items.JUG_OF_WINE, 2)));
                put(Rarity.UNCOMMON, Arrays.asList(new GameItem(Items.NATURE_RUNE, 3), new GameItem(Items.FIRE_ORB, 1), new GameItem(Items.CRYSTAL_SHARD, 1)));
                put(Rarity.RARE, Arrays.asList(new GameItem(Items.DIAMOND, 1), new GameItem(Items.GOLD_ORE, 1)));
                put(Rarity.SUPER_RARE, Arrays.asList(new GameItem(Items.ENHANCED_CRYSTAL_TELEPORT_SEED, 1)));
            }
        });

        /**
         * The level required to pickpocket
         */
        private final int level;

        /**
         * The experience gained from the pick pocket
         */
        private final int experience;

        /**
         * The chance of receiving a pet
         */
        private final int petChance;

        /**
         * The list of possible items received from the pick pocket
         */
        private Map<Rarity, List<GameItem>> items = new HashMap<>();

        /**
         * Creates a new pickpocket level requirement and experience gained
         *
         * @param level      the level required to steal from
         * @param experience the experience gained from stealing
         */
        Pickpocket(int level, int experience, int petChance, Map<Rarity, List<GameItem>> items) {
            this.level = level;
            this.experience = experience;
            this.petChance = petChance;
            this.items = items;
        }

        GameItem getRandomItem() {
            if (this == DRUNKEN_DWARF && Misc.trueRand(5_000) == 0) {
                return new GameItem(Items.AMMO_MOULD);
            }

            for (Entry<Rarity, List<GameItem>> entry : items.entrySet()) {
                final Rarity rarity = entry.getKey();

                if (rarity == Rarity.ALWAYS) {
                    continue;
                }
                final List<GameItem> items = entry.getValue();

                if (items.isEmpty()) {
                    continue;
                }

                if (RandomUtils.nextInt(1, rarity.rarity) == 1) {
                    return Misc.getItemFromList(items).randomizedAmount();
                }
            }

            List<GameItem> always = items.getOrDefault(Rarity.ALWAYS, Lists.newArrayList());

            if (!always.isEmpty()) {
                return Misc.getItemFromList(always).randomizedAmount();
            }

            return null;
        }
    }

}
