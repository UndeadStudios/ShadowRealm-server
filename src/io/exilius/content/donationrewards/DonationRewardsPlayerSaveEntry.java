//package io.exilius.content.donationrewards;
//
//import java.util.List;
//
//import io.exilius.model.entity.player.Player;
//import io.exilius.model.entity.player.save.PlayerSaveEntry;
//import io.exilius.util.Misc;
//import io.exilius.util.SundayReset;
//
//public class DonationRewardsPlayerSaveEntry implements PlayerSaveEntry {
//
//    private static final String AMOUNT = "donation_reward_amount";
//    private static final String RESET = "donation_reward_reset_week";
//
//    @Override
//    public List<String> getKeys(Player player) {
//        return List.of(AMOUNT, RESET);
//    }
//
//    @Override
//    public boolean decode(Player player, String key, String value) {
//        switch (key) {
//            case AMOUNT:
//                player.getDonationRewards().setAmountDonatedThisWeek(Integer.parseInt(value));
//                return true;
//            case RESET:
//                if (value.length() > 1) {
//                    player.getDonationRewards().setSundayReset(new SundayReset(Misc.convertStringToLocalDateTime(value)));
//                }
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public String encode(Player player, String key) {
//        switch (key) {
//            case AMOUNT:
//                return "" + player.getDonationRewards().getAmountDonatedThisWeek();
//            case RESET:
//                return Misc.convertLocalDateTimeToString(player.getDonationRewards().getSundayReset().getReset());
//        }
//        return null;
//    }
//
//    @Override
//    public void login(Player player) {
//
//    }
//}
