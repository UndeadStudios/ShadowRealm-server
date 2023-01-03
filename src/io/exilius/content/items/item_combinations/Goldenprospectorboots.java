package io.exilius.content.items.item_combinations;

import io.exilius.model.entity.player.Player;
import io.exilius.model.items.GameItem;
import io.exilius.model.items.ItemCombination;

import java.util.List;
import java.util.Optional;

public class Goldenprospectorboots extends ItemCombination {

	public Goldenprospectorboots(GameItem outcome, Optional<List<GameItem>> revertedItems, GameItem[] items) {
		super(outcome, revertedItems, items);
	}

	@Override
	public void combine(Player player) {
		items.forEach(item -> player.getItems().deleteItem2(item.getId(), item.getAmount()));
		player.getItems().addItem(outcome.getId(), outcome.getAmount());
		player.startAnimation(6929);
		player.getDH().sendItemStatement("You combined the items and created a pair of Golden prospector boots.", outcome.getId());
		player.setCurrentCombination(Optional.empty());
		player.nextChat = -1;
	}

	@Override
	public void showDialogue(Player player) {
		player.getDH().sendStatement("Combining these are final.", "You cannot revert this.");
	}

}
