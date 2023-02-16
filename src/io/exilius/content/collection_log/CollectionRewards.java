package io.exilius.content.collection_log;

import io.exilius.Server;
import io.exilius.content.bosses.Vorkath;
import io.exilius.content.item.lootable.impl.RaidsChestRare;
import io.exilius.content.item.lootable.impl.TheatreOfBloodChest;
import io.exilius.content.trails.TreasureTrailsRewardItem;
import io.exilius.content.trails.TreasureTrailsRewards;
import io.exilius.model.Npcs;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CollectionRewards {

    BARREL_CHEST(6342, new GameItem[]{new GameItem(696,1),
            new GameItem(696,1), new GameItem(6199,1),
            new GameItem(6199,1), new GameItem(6199,1)}),

    DAGGANOTH_SUPREME(2265, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(6199,1),
            new GameItem(6199,1), new GameItem(6828,1)}),

    DAGANNOTH_PRIME(2266, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(6199,1),
            new GameItem(6199,1), new GameItem(6828,1)}),

    DAGANNOTH_REX(2267, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(6199,1),
            new GameItem(6199,1), new GameItem(6828,1)}),

    KING_BLACK_DRAGON(239, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),

    KALPHITE_QUEEN(965, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),

    BANODS(2215, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),
    KRIL(3129, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),
    KREE(3162, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),
    COMMANDER(2205, new GameItem[]{new GameItem(696,2),
            new GameItem(696,2), new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2)}),

    CORP(319, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,12), new GameItem(6199,2),
            new GameItem(696,8), new GameItem(6828,2)}),


    KRAKEN(494, new GameItem[]{new GameItem(6199,2),
            new GameItem(692,5), new GameItem(692,5),
            new GameItem(692,5), new GameItem(692,5)}),
    CERB(5862, new GameItem[]{new GameItem(6828,2),
            new GameItem(696,4), new GameItem(6199,2),
            new GameItem(696,8), new GameItem(696,8)}),
    SIRE(5890, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2),
            new GameItem(696,2), new GameItem(696,2)}),
    DEMONIC(7145, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,4),
            new GameItem(696,2), new GameItem(696,4)}),

    SHAMAN(6766, new GameItem[]{new GameItem(6199,1),
            new GameItem(6199,1), new GameItem(6828,1),
            new GameItem(6828,1), new GameItem(696,1)}),

    VORKATH(8028, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,4),
            new GameItem(696,2), new GameItem(696,4)}),

    ZULRAH(2042, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,2),
            new GameItem(696,2), new GameItem(696,2)}),

    HYDRA(8621, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,8), new GameItem(696,4),
            new GameItem(696,4), new GameItem(696,4)}),
    NIGHTMARE(9425, new GameItem[]{new GameItem(13346,2),
            new GameItem(6199,2), new GameItem(6828,2),
            new GameItem(696,8), new GameItem(696,12)}),


    SARACHNIS(8713, new GameItem[]{new GameItem(6828,1),
            new GameItem(6199,2), new GameItem(692,2),
            new GameItem(692,1), new GameItem(692,1)}),
    GUARDIANS(7888, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,2), new GameItem(696,4),
            new GameItem(696,2), new GameItem(696,4)}),
    BRYOPHYA(8195, new GameItem[]{new GameItem(6828,1),
            new GameItem(6199,2), new GameItem(692,2),
            new GameItem(692,1), new GameItem(692,1)}),

    OBOR(7416, new GameItem[]{new GameItem(6828,1),
            new GameItem(6199,2), new GameItem(692,2),
            new GameItem(692,1), new GameItem(692,1)}),

    NEX(11278, new GameItem[]{new GameItem(6199,2),
            new GameItem(6828,2), new GameItem(696,8),
            new GameItem(13346,2), new GameItem(696,12)}),
    MALEDICTUS(11246, new GameItem[]{new GameItem(26515,1),
            new GameItem(786,1), new GameItem(786,1),
            new GameItem(21262,1), new GameItem(696,20)}),
    GALVEK(8096, new GameItem[]{new GameItem(786,1),
            new GameItem(786,1), new GameItem(2396,1),
            new GameItem(26515,1), new GameItem(696,50)}),

    VETRION(6611, new GameItem[]{new GameItem(696,12),
            new GameItem(13346,1), new GameItem(6199,2),
            new GameItem(6828,2), new GameItem(2996,500)}),

    CALLISTO(6609, new GameItem[]{new GameItem(696,12),
            new GameItem(13346,1), new GameItem(6199,2),
            new GameItem(6828,2), new GameItem(2996,500)}),

    SCORPIA(6615, new GameItem[]{new GameItem(696,12),
            new GameItem(13346,1), new GameItem(6199,2),
            new GameItem(6828,2), new GameItem(2996,500)}),

    VENENATIS(6610, new GameItem[]{new GameItem(696,8),
            new GameItem(696,4), new GameItem(6199,2),
            new GameItem(6828,2), new GameItem(2996,500)}),
    ELEMENTAL(2054, new GameItem[]{new GameItem(696,8),
            new GameItem(696,4), new GameItem(13346,2),
            new GameItem(2996,500), new GameItem(2996,500)}),
    FANATIC(6619, new GameItem[]{new GameItem(696,8),
            new GameItem(696,4), new GameItem(13346,2),
            new GameItem(2996,500), new GameItem(2996,500)}),
    ARCHAEOLOGIST(6618, new GameItem[]{new GameItem(696,8),
            new GameItem(696,4), new GameItem(13346,2),
            new GameItem(2996,500), new GameItem(2996,500)}),

    OLM(7554, new GameItem[]{new GameItem(786,1),
            new GameItem(13346,2), new GameItem(6199,2),
            new GameItem(3464,5), new GameItem(6828,2)}),

    TOB(8360, new GameItem[]{new GameItem(786,1),
            new GameItem(13346,2), new GameItem(6828,2),
            new GameItem(3464,5), new GameItem(6199,2)}),

    DHAROK(1673, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),
    AHRIM(1672, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),
    GUTHAN(1674, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),
    KARIL(1675, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),
    TORAG(1676, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),

    VERAC(1677, new GameItem[]{new GameItem(6199,2),
            new GameItem(696,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(6828,1)}),

    EASY(1, new GameItem[]{new GameItem(6769,1),
            new GameItem(6199,2), new GameItem(696,1),
            new GameItem(696,1), new GameItem(696,2)}),

    MEDIUM(2, new GameItem[]{new GameItem(6828,2),
            new GameItem(696,5), new GameItem(696,5),
            new GameItem(696,5), new GameItem(696,5)}),

    HARD(3, new GameItem[]{new GameItem(13346,2),
            new GameItem(696,10), new GameItem(696,10),
            new GameItem(696,10), new GameItem(696,10)}),
    MASTER(4, new GameItem[]{new GameItem(13346,3),
            new GameItem(696,15), new GameItem(696,15),
            new GameItem(696,15), new GameItem(696,15)}),
    PETS(5, new GameItem[]{new GameItem(761,1),
            new GameItem(696,70), new GameItem(696,70),
            new GameItem(696,70), new GameItem(13346,3)}),
    WEAPONS(6, new GameItem[]{new GameItem(13346,3),
            new GameItem(2396,3), new GameItem(696,70),
            new GameItem(696,70), new GameItem(696,70)}),
    ARMOR(7, new GameItem[]{new GameItem(13346,3),
            new GameItem(2396,3), new GameItem(696,70),
            new GameItem(696,70), new GameItem(696,70)}),
    ACCESSORY(8, new GameItem[]{new GameItem(13346,3),
            new GameItem(2396,3), new GameItem(696,70),
            new GameItem(696,70), new GameItem(696,70)}),

    MISC(9, new GameItem[]{new GameItem(696,160),
            new GameItem(13346,1), new GameItem(13346,1),
            new GameItem(13346,1), new GameItem(33109,1)}),
    AOE(10, new GameItem[]{new GameItem(696,200),
            new GameItem(13346,1), new GameItem(13346,1),
            new GameItem(13346,1), new GameItem(33112,1)}),
    ARAPHEL_RED(8172, new GameItem[]{new GameItem(696,200),
            new GameItem(13346,1), new GameItem(13346,1),
            new GameItem(13346,1), new GameItem(33112,1)}),

    ARAPHEL(8164, new GameItem[]{new GameItem(696,200),
            new GameItem(13346,1), new GameItem(13346,1),
            new GameItem(13346,1), new GameItem(33112,1)})

    ;

    public int NpcID;
    public GameItem[] Rewards;

    CollectionRewards(int NpcID, GameItem[] Rewards) {
        this.NpcID = NpcID;
        this.Rewards = Rewards;
    }

    public static ArrayList<GameItem> getForNpcID(int npcID) {
        ArrayList<GameItem> collectionRewards = new ArrayList<>();
        for (CollectionRewards value : CollectionRewards.values()) {
            if (value.NpcID == npcID) {
                collectionRewards.addAll(Arrays.asList(value.Rewards));
            }
        }
        return collectionRewards;
    }

    public static boolean handleButton(Player player, int ID) {
        if (ID == 23236) {
            if (player.getCollectionLog().getCollections().containsKey(player.getCollectionLogNPC()+ "")) {
                ArrayList<GameItem> itemsObtained = player.getCollectionLog().getCollections().get(player.getCollectionLogNPC()+ "");
                if (itemsObtained != null) {
                    List<GameItem> drops = Server.getDropManager().getNPCdrops(player.getCollectionLogNPC());
                    if (player.getCollectionLogNPC() == 8028) {
                        drops = Vorkath.getVeryRareDrops();
                    }
                    if (player.getCollectionLogNPC() == 7554) {
                        drops = RaidsChestRare.getRareDrops();
                    } else if (player.getCollectionLogNPC() >= 1 && player.getCollectionLogNPC() <= 4) {
                        drops = TreasureTrailsRewardItem.toGameItems(TreasureTrailsRewards.getRewardsForType(player.getCollectionLogNPC()));
                    } else if (player.getCollectionLogNPC() == 5) {
                        drops = PetHandler.getPetIds(true);
                    } else if (player.getCollectionLogNPC() == Npcs.THE_MAIDEN_OF_SUGADINTI) {
                        drops = TheatreOfBloodChest.getRareDrops();
                    }
                    if (drops != null &&
                            drops.size() == itemsObtained.size()
                            && !player.getClaimedLog().contains(player.getCollectionLogNPC())) {
                        player.getClaimedLog().add(player.getCollectionLogNPC());

                        for (GameItem gameItem : CollectionRewards.getForNpcID(player.getCollectionLogNPC())) {
                            player.getItems().addItemUnderAnyCircumstance(gameItem.getId(), gameItem.getAmount());
                        }
                        player.sendMessage("@gre@Your rewards have now been claimed!");

                    } else if (drops != null && drops.size() == itemsObtained.size()
                            && player.getClaimedLog().contains(player.getCollectionLogNPC())) {
                        player.sendMessage("@red@You've already claimed the reward from this log!");
                    } else if (drops != null &&
                            drops.size() != itemsObtained.size()) {
                        player.sendMessage("@red@You have not completed the log yet!");
                    }
                }
            }
            return true;
        }


        return false;
    }

}
