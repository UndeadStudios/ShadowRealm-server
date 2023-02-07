package io.exilius.sql; // dont forget to change packaging ^-^

import io.exilius.Server;
import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.vote_panel.VotePanelManager;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.ImmutableItem;
import io.exilius.sql.vote.VoteRecord;
import io.exilius.util.Misc;
import io.exilius.util.discord.Discord;
import net.dv8tion.jda.api.EmbedBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Votes implements Runnable  {

    public static final String HOST = "localhost";
    public static final String USER = "votes";
    public static final String PASS = "zazu";
    public static final String DATABASE = "vote";

    private Player player;
    private Connection conn;
    private Statement stmt;
    public static int voteCount;
    public static int globalvoteCount;
    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    public Votes(Player player) {
        //votes.start();
//        EmbedBuilder builder = new EmbedBuilder();
//        builder.setTitle(String.format("Vote system:"));
//
//        builder.addField("Name: ", player.getLoginName(), true);
//        builder.addField(player.getLoginName(), " Has Just voted for exilius thank you!", true);
        //DiscordConnection.post(DiscordConnection.ANNOUNCE, builder.build());
        //Broadcast..sendNews(Icon.YELLOW_INFO_BADGE, "Vote System:", player.getName() + " Has Just voted for exilius thank you!!.");
        System.out.println("votes running");
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }

            List<VoteRecord> voteRecords = new ArrayList<>();

            String name = player.getLoginName();//.replace(" ", "_");
            ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='"+name+"' AND claimed=0 AND voted_on != -1");
            while (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                int siteId = rs.getInt("site_id");
                switch (siteId) {// add products according to their ID in the ACP

                    case 1: // example
                        String message = "Vote System:" + player.getLoginName() + " has just voted on Rune-locus";
                        String message2 = "Vote System:" + player.getLoginName() + " has got the voting pet!";
                        int petroll = Misc.random(150);
                     //   player.getInventory().addToInventory(new ImmutableItem(6758, 1));
                        player.getInventory().addAnywhere(new ImmutableItem(1464, 5));
                        player.getInventory().addAnywhere(new ImmutableItem(995, 200_000));
                        if (petroll < 2) {
                            Discord.writeannounceMessage(message2);
                            player.getInventory().addAnywhere(new ImmutableItem(21262, 1));
                        }
                        if (petroll < 65) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        if (petroll < 25) {
                        player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                    }
                        this.voteCount += 1;
                        this.globalvoteCount += 1;
                        Server.setvoteCountCounter(this.voteCount);
                        Server.setGlobalvoteCountCounter(this.globalvoteCount);
                        Achievements.increase(player, AchievementType.VOTER, 1);
                        Discord.writeannounceMessage(message);
                        break;
                    case 2: // example                    builder.setTitle(String.format("Donation system:"));
                        String message3 = "Vote System:" + player.getLoginName() + " has just voted on Top 100";
                        int petroll2 = Misc.random(100);
                     //   player.getInventory().addToInventory(new ImmutableItem(6758, 1));
                        player.getInventory().addAnywhere(new ImmutableItem(1464, 3));
                        player.getInventory().addAnywhere(new ImmutableItem(995, 150_000));
                        if (petroll2 < 65) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        if (petroll2 < 25) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        this.voteCount += 1;
                        this.globalvoteCount += 1;
                        Server.setvoteCountCounter(this.voteCount);
                        Server.setGlobalvoteCountCounter(this.globalvoteCount);
                        Discord.writeannounceMessage(message3);
                        Achievements.increase(player, AchievementType.VOTER, 1);
                        break;
                    case 3: // example
                        String message4 = "Vote System:" + player.getLoginName() + " has just voted on RSPS-List";
                        int petroll3 = Misc.random(100);
                       // player.getInventory().addToInventory(new ImmutableItem(6758, 1));
                        player.getInventory().addAnywhere(new ImmutableItem(1464, 3));
                        player.getInventory().addAnywhere(new ImmutableItem(995, 100_000));
                        if (petroll3 < 65) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        if (petroll3 < 25) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        this.voteCount += 1;
                        this.globalvoteCount += 1;
                        Server.setvoteCountCounter(this.voteCount);
                        Server.setGlobalvoteCountCounter(this.globalvoteCount);
                        Discord.writeannounceMessage(message4);
                        Achievements.increase(player, AchievementType.VOTER, 1);
                        break;
                    case 5: // example
                        String message5 = "Vote System:" + player.getLoginName() + " has just voted on TopG";
                        int petroll4 = Misc.random(100);
                       // player.getInventory().addToInventory(new ImmutableItem(6758, 1));
                        player.getInventory().addAnywhere(new ImmutableItem(1464, 4));
                        player.getInventory().addAnywhere(new ImmutableItem(995, 120_000));
                        if (petroll4 < 65) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        if (petroll4 < 25) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        this.voteCount += 1;
                        this.globalvoteCount += 1;
                        Server.setvoteCountCounter(this.voteCount);
                        Server.setGlobalvoteCountCounter(this.globalvoteCount);
                        Discord.writeannounceMessage(message5);
                        Achievements.increase(player, AchievementType.VOTER, 1);
                        break;

                    case 6: // example
                        String message6 = "Vote System:" + player.getLoginName() + " has just voted on Runelist";
                        int petroll5 = Misc.random(100);
                      //  player.getInventory().addToInventory(new ImmutableItem(6758, 1));
                        player.getInventory().addAnywhere(new ImmutableItem(1464, 5));
                        player.getInventory().addAnywhere(new ImmutableItem(995, 110_000));
                        if (petroll5 < 65) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        if (petroll5 < 25) {
                            player.getInventory().addAnywhere(new ImmutableItem(11739, 1));
                        }
                        this.voteCount += 1;
                        this.globalvoteCount += 1;
                        Server.setvoteCountCounter(this.voteCount);
                        Server.setGlobalvoteCountCounter(this.globalvoteCount);
                        Discord.writeannounceMessage(message6);
                        Achievements.increase(player, AchievementType.VOTER, 1);
                        break;
                }
                // -- ADD CODE HERE TO GIVE TOKENS OR WHATEVER

                System.out.println("[Vote] Vote claimed by "+name+". (sid: "+siteId+", ip: "+ipAddress+")");

                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();

            }

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
            System.out.println("Votes stopped");
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
