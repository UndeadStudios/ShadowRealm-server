package io.shadowrealm.sql; // dont forget to change packaging ^-^

import io.shadowrealm.Server;
import io.shadowrealm.content.achievement.AchievementType;
import io.shadowrealm.content.achievement.Achievements;
import io.shadowrealm.content.commands.owner.Npc;
import io.shadowrealm.model.entity.npc.NPC;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.ImmutableItem;
import io.shadowrealm.sql.vote.VoteRecord;
import io.shadowrealm.util.Misc;
import io.shadowrealm.util.discord.Discord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RandomQuote implements Runnable  {

    public static final String HOST = "localhost";
    public static final String USER = "votes";
    public static final String PASS = "zazu";
    public static final String DATABASE = "exilius";

    private NPC npc;
    private Connection conn;
    private Statement stmt;
    public static int voteCount;
    public static int globalvoteCount;
    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    public RandomQuote(NPC Getnpc) {
        this.npc = Getnpc;

    }

    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }


                ResultSet rs = executeQuery("SELECT quote FROM randomquotes ORDER BY RAND() LIMIT 1");
                rs.next();
                String RandomQuote = rs.getString("quote");
                npc.forceChat(RandomQuote);
                destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println("Failing connecting to database!");
            this.doStop();
            System.out.println("Quotes stopped");
            return false;
        }
    }

    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch(Exception e) {
            e.printStackTrace();
            this.doStop();
            System.out.println("Votes stopped");
        }
    }

    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
