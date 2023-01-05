package io.exilius.content.skills.smithing;

import io.exilius.content.dailytasks.DailyTaskData;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.dialogue.DialogueBuilder;
import io.exilius.content.dialogue.types.MakeItemDialogue;
import io.exilius.content.skills.Skill;
import io.exilius.model.Items;
import io.exilius.model.SkillLevel;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.Right;
import io.exilius.model.items.ImmutableItem;
import io.exilius.model.tickable.impl.ItemProductionTickableBuilder;

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