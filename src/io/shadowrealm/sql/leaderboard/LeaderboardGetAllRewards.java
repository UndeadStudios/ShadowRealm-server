//package io.shadowrealm.sql.leaderboard;
//
//import io.shadowrealm.content.leaderboards.LeaderboardPeriodicity;
//import io.shadowrealm.content.leaderboards.LeaderboardReward;
//import io.shadowrealm.content.leaderboards.LeaderboardType;
//import io.shadowrealm.model.items.GameItem;
//import io.shadowrealm.sql.DatabaseManager;
//import io.shadowrealm.sql.SqlQuery;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LeaderboardGetAllRewards implements SqlQuery<List<LeaderboardReward>> {
//
//    @Override
//    public List<LeaderboardReward> execute(DatabaseManager context, Connection connection) throws SQLException {
////        ArrayList<LeaderboardReward> entries = new ArrayList<>();
//
//        PreparedStatement rewards = connection.prepareStatement("SELECT * FROM leaderboards_rewards");
//
//        ResultSet rs = rewards.executeQuery();
//        while (rs.next()) {
//            int item = rs.getInt("item_id");
//            int amount = rs.getInt("amount");
//            int type = rs.getInt("type");
//            int place = rs.getInt("place");
//            int period = rs.getInt("period");
//            entries.add(new LeaderboardReward(LeaderboardType.values()[type], LeaderboardPeriodicity.values()[period],
//                    place, new GameItem(item, amount)));
//        }
//
//        return entries;
//    }
//
//}
