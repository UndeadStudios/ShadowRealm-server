package io.shadowrealm.content.skills.smithing;

import io.shadowrealm.content.dailytasks.DailyTaskData;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.dialogue.DialogueBuilder;
import io.shadowrealm.content.dialogue.types.MakeItemDialogue;
import io.shadowrealm.content.skills.Skill;
import io.shadowrealm.model.Items;
import io.shadowrealm.model.SkillLevel;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.model.entity.player.Right;
import io.shadowrealm.model.items.ImmutableItem;
import io.shadowrealm.model.tickable.impl.ItemProductionTickableBuilder;

public class CannonballSmelting {

    public static boolean isSmeltingCannonballs(Player player) {
        return player.getItems().playerHasItem(Items.AMMO_MOULD);
    }

    public static void smelt(Player player) {
        player.start(new DialogueBuilder(player).makeItems(
                100, CannonballSmelting::make,
                new MakeItemDialogue.MakeItem(Items.CANNONBALL)
        ));
    }

    private static void make(MakeItemDialogue.PlayerMakeItem makeItem) {
        int amt = 4;
        Player player = makeItem.getPlayer();

        if (player.getRights().isOrInherits(Right.ADMINISTRATOR)) amt = 6;

        if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.SMITHING_CANNONBALLS1.getDailyTask().getTaskName())) {
            DailyTaskHandler.Companion.handleProgress(player, amt);
        }
        if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.SMITHING_CANNONBALLS2.getDailyTask().getTaskName())) {
            DailyTaskHandler.Companion.handleProgress(player, amt);
        }
        if (player.getCurrentDailyTask().getTaskName().equals(DailyTaskData.SMITHING_CANNONBALLS3.getDailyTask().getTaskName())) {
            DailyTaskHandler.Companion.handleProgress(player, amt);
        }

        new ItemProductionTickableBuilder()
                .setPlayer(player)
                .setProductionDelay(3)
                .setProductionAmount(makeItem.getAmount())
                .setExecutionConsumer(task -> task.getPlayer().startAnimation(899))
                .setItemsConsumed(new ImmutableItem(Items.STEEL_BAR))
                .setItemsProduced(new ImmutableItem(Items.CANNONBALL, amt))
                .setExperiencedGained(new SkillLevel(Skill.SMITHING, 25))
                .createItemProductionTask().begin();
    }
}