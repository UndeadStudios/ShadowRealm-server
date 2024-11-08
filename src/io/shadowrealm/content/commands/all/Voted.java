//package io.shadowrealm.content.commands.all;
//
//import java.sql.Timestamp;
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//import io.shadowrealm.Configuration;
//import io.shadowrealm.Server;
//import io.shadowrealm.content.achievement.AchievementType;
//import io.shadowrealm.content.achievement.Achievements;
//import io.shadowrealm.content.bonus.DoubleExperience;
//import io.shadowrealm.content.commands.Command;
//import io.shadowrealm.content.event.eventcalendar.ChallengeParticipant;
//import io.shadowrealm.content.event.eventcalendar.EventCalendar;
//import io.shadowrealm.content.vote_panel.VotePanelManager;
//import io.shadowrealm.content.vote_panel.VoteUser;
//import io.shadowrealm.content.wogw.Wogw;
//import io.shadowrealm.model.Items;
//import io.shadowrealm.model.entity.player.Boundary;
//import io.shadowrealm.model.entity.player.ClientGameTimer;
//import io.shadowrealm.model.entity.player.Player;
//import io.shadowrealm.model.entity.player.PlayerHandler;
//import io.shadowrealm.model.items.ImmutableItem;
//import io.shadowrealm.sql.Votes;
//import io.shadowrealm.sql.eventcalendar.queries.AddParticipantEntryOnVoteQuery;
//import io.shadowrealm.sql.vote.*;
//import io.shadowrealm.util.DateUtils;
//import io.shadowrealm.util.logging.player.VotedLog;
//
///**
// * Changes the password of the player.
// *
// * @author Emiel
// *
// */
//public class Voted extends Command {
//
//	private static final long XP_SCROLL_TICKS = TimeUnit.MINUTES.toMillis(30) / 600;
//	private static final int GP_REWARD = 1_000_000;
//	public static int globalVotes = 10;
//
//	public static void claimVotes(Player player, List<VoteRecord> votes) {
//
//	}
//
//	private static void votePanel(Player player) {
//		VotePanelManager.addVote(player.getLoginName());
//		VoteUser user = VotePanelManager.getUser(player);
//		if (user != null) {
//			if (player.getLastVotePanelPoint().isBefore(LocalDate.now())) { // Gain one point per day
//				player.setLastVotePanelPoint(LocalDate.now());
//				boolean oldStreakOverflow = user.getDayStreak() >= VoteUser.MAX_DAY_STREAK;
//				user.incrementDayStreak();
//				if (user.getDayStreak() > VoteUser.MAX_DAY_STREAK && !oldStreakOverflow) {
//					user.resetDayStreak();
//					user.incrementDayStreak();
//				}
//
//				if (user.getDayStreak() == VoteUser.MAX_DAY_STREAK || oldStreakOverflow) { //They just hit a 5 day streak (after incrementing) so reward them!
//					player.getItems().addItemToBankOrDrop(22093, 1);
//					player.getItems().addItemToBankOrDrop(6199, 1);
//					player.sendMessage("@pur@One @gre@vote key @pur@has been added to your bank for a 5 vote streak!");
//					player.sendMessage("@red@You just completed a 5 day voting streak!");
//					user.resetDayStreak();
//					if (oldStreakOverflow) {
//						user.incrementDayStreak();
//					}
//				}
//
//				player.debug("Gained one ::vpanel point, streak: {}.", "" + user.getDayStreak());
//				VotePanelManager.saveToJSON();
//			}
//		}
//	}
//
//	private static void rewards(Player player, final int voteCount) {
//		Achievements.increase(player, AchievementType.VOTER, voteCount);
//		player.votePoints += voteCount;
//		player.voteKeyPoints += voteCount;
//		player.xpScroll = true;
//		player.xpScrollTicks += XP_SCROLL_TICKS * voteCount;
//
//		// Send bonus experience timer to player only if double exp isn't already activated (since they're non stackable).
//		if (!DoubleExperience.isDoubleExperience()) {
//			player.getPA().sendGameTimer(ClientGameTimer.BONUS_XP, TimeUnit.MINUTES, (int) ((player.xpScrollTicks / 100)));
//		}
//		player.getInventory().addAnywhere(new ImmutableItem(1464, 5));
//		player.getInventory().addAnywhere(new ImmutableItem(995, 200_000));
//		// Coins
//		boolean firstWeekOfMonth = DateUtils.isFirstWeekOfMonth();
//		int amount = GP_REWARD * voteCount;
//		if (firstWeekOfMonth) {
//			amount *= 2;
//			player.sendMessage("@dre@You have gained " + voteCount + " voting point and extra gp for the first week of month!");
//		} else {
//			player.sendMessage("You have gained " +  voteCount + " voting point and gp!");
//		}
//		player.getItems().addItemUnderAnyCircumstance(Items.COINS, amount);
//	}
//
//	private static void incrementGlobalVote(final int voteCount) {
//		final boolean firstWeekOfMonth = DateUtils.isFirstWeekOfMonth();
//		final var header = firstWeekOfMonth ? "Double Vote Week" : "Vote";
//		globalVotes += voteCount;
//
//		// Dividing by two because it counts both votes, don't have a better way atm
//		if (globalVotes/2 >= 50) {
//			Wogw.votingBonus();
//			globalVotes = 0;
//		} else if (globalVotes/2 == 40) {
//			PlayerHandler.executeGlobalMessage("@cr10@[@pur@" + header + "@bla@] Global votes are at 40, reach 50 for a server boost!");
//		} else if (globalVotes/2 == 20) {
//			PlayerHandler.executeGlobalMessage("@cr10@[@pur@" + header + "@bla@] Global votes are at 20, reach 50 for a server boost!");
//		} else if (globalVotes/2 == 10) {
//			PlayerHandler.executeGlobalMessage("@cr10@[@pur@" + header + "@bla@] Global votes are at 10, reach 50 for a server boost!");
//		}
//	}
//
//	@Override
//	public void execute(Player c, String commandName, String input) {
//
//		List<VoteRecord> votes = new ArrayList<>();
//		if (c.getItems().freeSlots() < 1) {
//			c.sendMessage("You need at least one free slots to use this command.");
//			return;
//		}
//		if (Boundary.isIn(c, Boundary.OUTLAST) || Boundary.isIn(c, Boundary.LUMBRIDGE_OUTLAST_AREA) || Boundary.isIn(c, Boundary.LUMBRIDGE_OUTLAST_LOBBY)) {
//			c.sendMessage("You cannot do this right now.");
//			return;
//	}
//		Thread votes2 = new Thread(new Votes(c));
//		votes2.start();
//		Server.getLogging().write(new VotedLog(c, votes, "Received rewards for these votes"));
//		Server.getDatabaseManager().exec(new AddParticipantEntryOnVoteQuery(new ChallengeParticipant(c, EventCalendar.getDateProvider())));
//
//		int voteCount = votes.size();
//		LocalDate today = LocalDate.now();
//
//		if (Configuration.VOTE_PANEL_ACTIVE) {
//			var votePanelStart = VotePanelManager.wrapper.getStartTime();
//			if (votePanelStart != null && votes.stream().allMatch(it -> it.getVotedTime().toInstant().isBefore(votePanelStart))) {
//				c.sendMessage("@dre@None of your claimed votes were after the Vote Panel start date.");
//			} else {
//				votePanel(c);
//			}
//		}
//
//		c.setLastVote(today);
//		rewards(c, voteCount);
//		incrementGlobalVote(voteCount);
//		Server.setvoteCountCounter(voteCount);
//
//				}
//
//	@Override
//	public Optional<String> getDescription() {
//		return Optional.of("Claim your voted reward.");
//	}
//
//	private static void sendNextVoteTime(Player player, String name, Timestamp timestamp) {
//		if (timestamp == null) return;
//		var lastVoteInstant = timestamp.toInstant();
//		var nextVoteInstant = lastVoteInstant.plus(12, ChronoUnit.HOURS);
//		var waitDuration = Duration.between(Instant.now(), nextVoteInstant);
//		if (waitDuration.isNegative()) {
//			player.sendMessage("You can vote at " + name + ".");
//			return;
//		}
//		var formatted = format(waitDuration);
//		player.sendMessage("You can vote at " + name + " in " + formatted + ".");
//	}
//
//	private static String format(final Duration duration) {
//		var hours = duration.toHours();
//		var minutes = duration.toMinutes();
//		var seconds = duration.toSeconds();
//		// Round everything up by 1.
//		if (hours > 0) {
//			var adjustedHours = hours + 1;
//			return adjustedHours + " hours";
//		} else if (minutes > 0) {
//			var adjustedMinutes = minutes + 1;
//			return adjustedMinutes + " minutes";
//		} else {
//			var adjustedSeconds = seconds + 1;
//			return adjustedSeconds + " seconds";
//		}
//	}
//}
