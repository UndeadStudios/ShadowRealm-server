package io.shadowrealm.content.skills.fletching;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import io.shadowrealm.Server;
import io.shadowrealm.content.dialogue.DialogueBuilder;
import io.shadowrealm.content.dialogue.DialogueOption;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.content.skills.slayer.SlayerUnlock;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.ItemAssistant;
import io.shadowrealm.model.items.ItemCacheDefinition;
import io.shadowrealm.util.Misc;

public class Fletching {

	public static final String BROAD_ARROW_MESSAGE = "You need to unlock the ability to craft broad ammo through Slayer Rewards.";
	
	private static final Set<FletchableGem> GEMS = Collections.unmodifiableSet(EnumSet.allOf(FletchableGem.class));

	private static final Set<FletchableUnfinishedBolt> UNFINISHED_BOLTS = Collections.unmodifiableSet(EnumSet.allOf(FletchableUnfinishedBolt.class));

	private static final Set<FletchableBow> BOWS = Collections.unmodifiableSet(EnumSet.allOf(FletchableBow.class));

	private static final Set<FletchableCrossbow> CROSSBOWS = Collections.unmodifiableSet(EnumSet.allOf(FletchableCrossbow.class));

	public static final Set<FletchableArrow> ARROWS = Collections.unmodifiableSet(EnumSet.allOf(FletchableArrow.class));

	private static final Set<FletchableJavelin> JAVELINS = Collections.unmodifiableSet(EnumSet.allOf(FletchableJavelin.class));

	private static final Set<FletchableDart> DARTS = Collections.unmodifiableSet(EnumSet.allOf(FletchableDart.class));

	private static final Set<FletchableBolt> BOLTS = Collections.unmodifiableSet(EnumSet.allOf(FletchableBolt.class));

	/**
	 * An unmodifiable {@link Set} of {@link FletchableLogGroup} elements
	 */
	private static final Set<FletchableLogGroup> FLETCHABLE_LOG_GROUP = Collections.unmodifiableSet(EnumSet.allOf(FletchableLogGroup.class));
	/**
	 * An array of values that represent the amount of some selection
	 */
	private static final int[] FLETCHABLE_AMOUNTS = { 1, 5, 10, -1 };

	/**
	 * The {@link Player} that will be fletching
	 */
	private final Player player;

	/**
	 * An Optional of type {@link FletchableLogGroup} that will keep track of what group the player has selected for fletching.
	 */
	private Optional<FletchableLogGroup> selectedGroup = Optional.empty();

	/**
	 * The {@link FletchableLog} that the player has selected to fletch
	 */
	private Optional<FletchableLog> selectedFletchable = Optional.empty();

	/**
	 * Creates a new single class to manage fletching operations related to the {@code fletching} skill.
	 * 
	 * @param player the player that will be fletching
	 */
	public Fletching(final Player player) {
		this.player = player;
	}

	public boolean combine(int use, int used) {
		selectedGroup = FLETCHABLE_LOG_GROUP.stream().filter(g -> Arrays.stream(g.getFletchables()).anyMatch(f -> f.getItemId() == use || f.getItemId() == used)).findFirst();
		selectedGroup.ifPresent(group -> {
			FletchableLog[] fletchables = group.getFletchables();
			if (fletchables.length == 5) {
				player.getPA().stopSkilling();
				player.getPA().sendChatboxInterface(8938);
				player.getPA().sendFrame126("What would you like to make?", 8966);
				player.getPA().sendFrame246(8941, 190, fletchables[0].getProduct());
				player.getPA().sendFrame246(8942, 190, fletchables[1].getProduct());
				player.getPA().sendFrame246(8943, 190, fletchables[2].getProduct());
				player.getPA().sendFrame246(8944, 190, fletchables[3].getProduct());
				player.getPA().sendFrame246(8945, 190, fletchables[4].getProduct());
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[0].getProduct()).getName(), 8949);
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[1].getProduct()).getName(), 8953);
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[2].getProduct()).getName(), 8957);
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[3].getProduct()).getName(), 8961);
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[4].getProduct()).getName(), 8965);
			} else if (fletchables.length == 4) {
				player.getPA().stopSkilling();
					player.getPA().sendChatboxInterface(8899);
					player.getPA().sendFrame126("What would you like to make?", 8922);
					player.getPA().sendFrame246(8902, 190, fletchables[1].getProduct());
					player.getPA().sendFrame246(8903, 190, fletchables[0].getProduct());
					player.getPA().sendFrame246(8904, 190, fletchables[2].getProduct());
					player.getPA().sendFrame246(8905, 190, fletchables[3].getProduct());
					player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[0].getProduct()).getName(), 8906);
					player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[1].getProduct()).getName(), 8910);
					player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[2].getProduct()).getName(), 8914);
					player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[3].getProduct()).getName(), 8918);
				} else if (fletchables.length == 3) {
				player.getPA().stopSkilling();
			player.getPA().sendChatboxInterface(8880);
			player.getPA().sendFrame126("What would you like to make?", 8898);
			player.getPA().sendFrame246(8884, 190, fletchables[1].getProduct());
			player.getPA().sendFrame246(8883, 190, fletchables[0].getProduct());
			player.getPA().sendFrame246(8885, 190, fletchables[2].getProduct());
			player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[0].getProduct()).getName(), 8889);
			player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[1].getProduct()).getName(), 8893);
			player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[2].getProduct()).getName(), 8897);
			} else if (fletchables.length == 2) {
				player.getPA().stopSkilling();
				player.getPA().sendChatboxInterface(8866);
				player.getPA().sendFrame126("What would you like to make?", 8879);
				player.getPA().sendFrame246(8869, 190, fletchables[0].getProduct());
				player.getPA().sendFrame246(8870, 190, fletchables[1].getProduct());
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[0].getProduct()).getName(), 8871);
				player.getPA().sendFrame126(ItemCacheDefinition.forID(fletchables[1].getProduct()).getName(), 8875);
			}
		});
		return selectedGroup.isPresent();
	}

	public void select(int buttonId) {
		selectedGroup.ifPresent(group -> {
			for (FletchableLog fletchable : group.getFletchables()) {
				int index = Misc.linearSearch(fletchable.getButtonIds(), buttonId);
				if (index != -1) {
					int amount = FLETCHABLE_AMOUNTS[index];
					selectedFletchable = Optional.of(fletchable);
					if (amount == -1) {
						player.getPA().sendEnterAmount(0);
						break;
					}
					fletchLog(fletchable, amount);
					break;
				}
			}
		});
	}

	/**
	 * Attempts to cut a log with a knife in hopes to make some secondary item
	 *
	 */
	public void fletchLog(FletchableLog fletchable, int amount) {
		selectedGroup = Optional.empty();
		selectedFletchable = Optional.empty();
		if (!player.getItems().playerHasItem(fletchable.getItemId())) {
			player.sendMessage("You do not have the items required for this.");
			player.getPA().removeAllWindows();
			return;
		}
		if (player.playerLevel[Skill.FLETCHING.getId()] < fletchable.getLevel()) {
			player.sendMessage("You need a fletching level of " + fletchable.getLevel() + " to do this.");
			player.getPA().removeAllWindows();
			return;
		}
		player.startAnimation(1248);
		player.getPA().removeAllWindows();
		Server.getEventHandler().stop(player, "skilling");
		Server.getEventHandler().submit(new FletchLogEvent(player, 3, fletchable, amount));
	}

	public void fletchGem(int use, int used) {
		selectedGroup = Optional.empty();
		selectedFletchable = Optional.empty();
		Optional<FletchableGem> gem = GEMS.stream().filter(g -> g.getGem() == use || g.getGem() == used).findFirst();
		gem.ifPresent(g -> {
			Consumer<Player> make = plr -> {
				if (!player.getItems().playerHasItem(g.getGem())) {
					player.sendMessage("You do not have the items required for this.");
					player.getPA().removeAllWindows();
					return;
				}
				if (player.playerLevel[Skill.FLETCHING.getId()] < g.getLevel()) {
					player.sendMessage("You need a fletching level of " + g.getLevel() + " to do this.");
					player.getPA().removeAllWindows();
					return;
				}

				player.startAnimation(886);
				player.getItems().deleteItem2(g.getGem(), 1);
				player.getItems().addItem(g.getTips(), g.getAmount());
				player.getPA().addSkillXPMultiplied(g.getExperience(), Skill.FLETCHING.getId(), true);
				player.getPA().removeAllWindows();
			};

			if (g == FletchableGem.ONYX) {
				player.start(new DialogueBuilder(player).option("Fletch onyx bolt tips?",
						new DialogueOption("Yes, fletch my Onyx into bolt tips.", make),
						new DialogueOption("No, I want to keep my Onyx.", plr -> plr.getPA().closeAllWindows())
				));
				return;
			}

			make.accept(player);
		});
	}

	public void fletchUnfinishedBolt(int boltId) {
		Optional<FletchableUnfinishedBolt> bolt = UNFINISHED_BOLTS.stream().filter(b -> b.getUnfinished() == boltId).findFirst();
		bolt.ifPresent(b -> {
			if (bolt.get() == FletchableUnfinishedBolt.BROAD && !player.getSlayer().getUnlocks().contains(SlayerUnlock.BROADER_FLETCHING)) {
				player.sendMessage(BROAD_ARROW_MESSAGE);
				player.getPA().removeAllWindows();
				return;
			}
			if (player.getItems().freeSlots() < 1) {
				player.sendMessage("You need at least 1 free slot to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			if (!player.getItems().playerHasItem(314, 10)) {
				player.sendMessage("You need at least 10 feathers to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			if (!player.getItems().playerHasItem(b.getUnfinished(), 10)) {
				player.sendMessage("You need at least 10 of this bolt type to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			if (player.playerLevel[Skill.FLETCHING.getId()] < b.getLevel()) {
				player.sendMessage("You need a fletching level of " + b.getLevel() + " to fletch this bolt.");
				player.getPA().removeAllWindows();
				return;
			}
			player.getPA().stopSkilling();
			player.getItems().deleteItem2(314, 10);
			player.getItems().deleteItem2(b.getUnfinished(), 10);
			player.getItems().addItem(b.getBolt(), 10);
			player.getPA().addSkillXPMultiplied(b.getExperience() * 10, Skill.FLETCHING.getId(), true);
		});
	}

	public void fletchHeadlessArrows() {
		if (player.getItems().freeSlots() < 1) {
			player.sendMessage("You need at least 1 free slot.");
			player.getPA().removeAllWindows();
			return;
		}
		if (!player.getItems().playerHasItem(52, 15)) {
			player.sendMessage("You need at least 15 arrow shafts to do this.");
			player.getPA().removeAllWindows();
			return;
		}
		if (!player.getItems().playerHasItem(314, 15)) {
			player.sendMessage("You need at least 15 feathers to do this.");
			player.getPA().removeAllWindows();
			return;
		}
		player.getItems().deleteItem2(314, 15);
		player.getItems().deleteItem2(52, 15);
		player.getItems().addItem(53, 15);
		player.getPA().addSkillXPMultiplied(15, Skill.FLETCHING.getId(), true);
	}

	public void fletchUnstrung(int bowId) {
		Optional<FletchableBow> bow = BOWS.stream().filter(b -> b.getItem() == bowId).findFirst();
		bow.ifPresent(b -> {
			player.getPA().stopSkilling();
			if (player.playerLevel[Skill.FLETCHING.getId()] < b.getLevelRequired()) {
				player.sendMessage("You need a fletching level of " + b.getLevelRequired() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			Server.getEventHandler().submit(new StringBowEvent(b, player, 3));
		});
	}

	public void fletchUnstrungCross(int crossbowId) {
		Optional<FletchableCrossbow> crossbow = CROSSBOWS.stream().filter(b -> b.getItem() == crossbowId).findFirst();
		crossbow.ifPresent(b -> {
			player.getPA().stopSkilling();
			if (player.playerLevel[Skill.FLETCHING.getId()] < b.getLevelRequired()) {
				player.sendMessage("You need a fletching level of " + b.getLevelRequired() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			Server.getEventHandler().submit(new StringCrossbowEvent(b, player, 3));
		});
	}

	public void fletchArrow(int arrowId) {
		Optional<FletchableArrow> arrow = ARROWS.stream().filter(a -> a.getId() == arrowId).findFirst();
		arrow.ifPresent(a -> {
			player.getPA().stopSkilling();
			if (arrow.get() == FletchableArrow.BROAD && !player.getSlayer().getUnlocks().contains(SlayerUnlock.BROADER_FLETCHING)) {
				player.sendMessage(Fletching.BROAD_ARROW_MESSAGE);
				player.getPA().removeAllWindows();
				return;
			}
			if (player.playerLevel[Skill.FLETCHING.getId()] < a.getLevelRequired()) {
				player.sendMessage("You need a fletching level of " + a.getLevelRequired() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			Server.getEventHandler().submit(new MakeArrowEvent(player, a));
		});
	}
	
	public void fletchJavelin(int arrowId) {
		Optional<FletchableJavelin> arrow = JAVELINS.stream().filter(a -> a.getId() == arrowId).findFirst();
		arrow.ifPresent(a -> {
			player.getPA().stopSkilling();
			if (player.playerLevel[Skill.FLETCHING.getId()] < a.getLevelRequired()) {
				player.sendMessage("You need a fletching level of " + a.getLevelRequired() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			Server.getEventHandler().submit(new MakeJavelinEvent(player, a));
		});
	}

	public void fletchDart(int dartId) {
		Optional<FletchableDart> dart = DARTS.stream().filter(a -> a.getId() == dartId).findFirst();
		dart.ifPresent(d -> {
			player.getPA().stopSkilling();
			if (player.playerLevel[Skill.FLETCHING.getId()] < d.getLevelRequired()) {
				player.sendMessage("You need a fletching level of " + d.getLevelRequired() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}
			Server.getEventHandler().submit(new MakeDartEvent(player, d));
		});
	}

	public boolean fletchBolt(int boltId, int tipId) {
		Optional<FletchableBolt> bolt = BOLTS.stream().filter(b -> b.getUnfinished() == boltId && b.getTip() == tipId).findFirst();
		bolt.ifPresent(b -> {
			player.getPA().stopSkilling();
			if (bolt.get() == FletchableBolt.BROAD_AMETHYST && !player.getSlayer().getUnlocks().contains(SlayerUnlock.BROADER_FLETCHING)) {
				player.sendMessage(BROAD_ARROW_MESSAGE);
				player.getPA().removeAllWindows();
				return;
			}
			if (player.playerLevel[Skill.FLETCHING.getId()] < b.getLevel()) {
				player.sendMessage("You need a fletching level of " + b.getLevel() + " to do this.");
				player.getPA().removeAllWindows();
				return;
			}

			Server.getEventHandler().submit(new MakeBoltEvent(player, b, boltId, tipId));
		});
		return bolt.isPresent();
	}

	public Optional<FletchableLog> getSelectedFletchable() {
		return selectedFletchable;
	}

}
