//package io.exilius.content.leaderboards;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import io.exilius.Server;
//import io.exilius.annotate.Init;
//import io.exilius.model.entity.player.Player;
//import io.exilius.model.items.GameItem;
//import io.exilius.model.items.ImmutableItem;
//import io.exilius.sql.leaderboard.LeaderboardAdd;
//import io.exilius.sql.leaderboard.LeaderboardGetAllUnclaimedRewards;
//import io.exilius.util.JsonUtil;
//import io.exilius.util.logging.player.LeaderboardRewardCollected;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class LeaderboardUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(LeaderboardUtils.class);
//    public static final Map<LeaderboardType, List<LeaderboardEntry>> leaderboards = new ConcurrentHashMap<>();
//    public static final Map<LeaderboardType, List<LeaderboardEntry>> daily = new ConcurrentHashMap<>();
//    public static final Map<LeaderboardType, List<LeaderboardEntry>> weekly = new ConcurrentHashMap<>();
//
//    public static final List<LeaderboardReward> rewards = new ArrayList<>();
//
//    @Init
//    @SuppressWarnings({"unused", "RedundantSuppression"})
//    public static void loadLeaderboardRewards() throws IOException {
//        rewards.addAll(JsonUtil.fromYaml(Server.getDataDirectory() + "/cfg/leaderboard_rewards.yaml", new TypeReference<List<LeaderboardReward>>() {}));
//    }
//
//    public static void checkRewards(Player player) {
//        Server.getDatabaseManager().exec((context, connection) -> {
//            List<GameItem> entries = new LeaderboardGetAllUnclaimedRewards(player).execute(context, connection);
//
//            if (!entries.isEmpty()) {
//                logger.debug("Giving {} rewards {}", player, entries);
//                player.addQueuedAction(plr -> {
//                    for (GameItem item : entries) {
//                        if (!player.getInventory().addToBank(new ImmutableItem(item))) {
//                            player.getCollectionBox().add(player, item);
//                        }
//                        player.sendMessage("<clan=6> You've received {} for placing in the leaderboards!", item.getFormattedString());
//                        Server.getLogging().write(new LeaderboardRewardCollected(player, item));
//                    }
//                });
//            }
//
//            return null;
//        });
//    }
//
//    public static void addCount(LeaderboardType type, Player player, int amount) {
//        Server.getDatabaseManager().batch(new LeaderboardAdd(new LeaderboardEntry(type, player.getLoginName(), amount, LocalDateTime.now())));
//    }
//}
