package io.shadowrealm.content.collection_log;

import io.shadowrealm.Server;
import io.shadowrealm.content.bosses.Vorkath;
import io.shadowrealm.content.item.lootable.impl.RaidsChestRare;
import io.shadowrealm.content.item.lootable.impl.TheatreOfBloodChest;
import io.shadowrealm.content.trails.TreasureTrailsRewardItem;
import io.shadowrealm.content.trails.TreasureTrailsRewards;
import io.shadowrealm.model.Npcs;
import io.shadowrealm.model.entity.npc.pets.PetHandler;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.items.GameItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CollectionRewards {

    BARREL_CHEST(6342, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),


    DAGGANOTH_SUPREME(2265, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    DAGANNOTH_PRIME(2266, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    DAGANNOTH_REX(2267, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    KING_BLACK_DRAGON(239, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    KALPHITE_QUEEN(965, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    BANODS(2215, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    KRIL(3129, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    KREE(3162, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    COMMANDER(2205, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    CORP(319, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),


    KRAKEN(494, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    CERB(5862, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    SIRE(5890, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    DEMONIC(7145, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    SHAMAN(6766, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    VORKATH(8028, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    ZULRAH(2042, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    HYDRA(8621, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    NIGHTMARE(9425, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),


    SARACHNIS(8713, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    GUARDIANS(7888, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    BRYOPHYA(8195, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    OBOR(7416, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    NEX(11278, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    MALEDICTUS(11246, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),
    GALVEK(8096, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    VETRION(6611, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),

    CALLISTO(6609, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),

    SCORPIA(6615, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),

    VENENATIS(6610, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),
    ELEMENTAL(2054, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),
    FANATIC(6619, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3),new GameItem(4185,3),
            new GameItem(6828,1)}),
    ARCHAEOLOGIST(6618, new GameItem[]{new GameItem(28827,1), new GameItem(6792,3), new GameItem(4185,3),
            new GameItem(6828,1)}),

    OLM(7554, new GameItem[]{new GameItem(10025,1), new GameItem(696,1), new GameItem(21046,100),
            new GameItem(995,200_000_000)}),

    TOB(8360, new GameItem[]{new GameItem(6830,1), new GameItem(696,2), new GameItem(21046,100),
            new GameItem(995,300_000_000)}),

    DHAROK(1673, new GameItem[]{new GameItem(6199,1), new GameItem(691,1), new GameItem(995,20_000_000)}),

    AHRIM(1672, new GameItem[]{new GameItem(6199,1), new GameItem(691,1),
            new GameItem(995,20_000_000)}),
    GUTHAN(1674, new GameItem[]{new GameItem(6199,1), new GameItem(691,1),
            new GameItem(995,20_000_000)}),
    KARIL(1675, new GameItem[]{new GameItem(6199,1), new GameItem(691,1),
            new GameItem(995,20_000_000)}),
    TORAG(1676, new GameItem[]{new GameItem(6199,1), new GameItem(691,1),
            new GameItem(995,20_000_000)}),

    VERAC(1677, new GameItem[]{new GameItem(6199,1), new GameItem(691,1),
            new GameItem(995,20_000_000)}),

    EASY(1, new GameItem[]{new GameItem(2396,1),
            new GameItem(995,25_000_000)}),

    MEDIUM(2, new GameItem[]{new GameItem(786,1),
            new GameItem(995,50_000_000)}),

    HARD(3, new GameItem[]{new GameItem(2396,3),
            new GameItem(995,75_000_000)}),
    MASTER(4, new GameItem[]{new GameItem(761,1), new GameItem(995,100_000_000)}),
    PETS(5, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)}),
    WEAPONS(6, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)}),
    ARMOR(7, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)}),
    ACCESSORY(8, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)}),

    MISC(9, new GameItem[]{new GameItem(6199,2),
            new GameItem(995,200_000)}),
    AOE(10, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)}),
    ARAPHEL_RED(8172, new GameItem[]{new GameItem(6199,1),
            new GameItem(995,100_000)}),

    ARAPHEL(8164, new GameItem[]{new GameItem(6199,3),
            new GameItem(995,100_000)})

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
