package io.exilius.content.skills.woodcutting;

import io.exilius.model.entity.player.Player;

enum Machete {
	MACHETE(975, 1, 2382, 1.0),
	OPAL_MACHETE(6313, 10, 6085, .9),
	JADE_MACHETE(6315, 20, 6086, .65),
	RED_TOPAZ_MACHETE(6317, 30, 6087, .45);

	private final int itemId;
    private final int levelRequired;
    private final int animation;
	private final double chopSpeed;

	/**
	 * Constructs a new {@link Machete} used to cut down trees.
	 *
	 * @param itemId the item identification value of the hatchet
	 * @param levelRequired the level required for use
	 * @param animation the animation displayed during use
	 * @param chopSpeed the effectiveness of the hatchet when determining a log has been cut
	 */
    Machete(int itemId, int levelRequired, int animation, double chopSpeed) {
		this.itemId = itemId;
		this.levelRequired = levelRequired;
		this.animation = animation;
		this.chopSpeed = chopSpeed;
	}

	/**
	 * The item id associated with the hatchet.
	 * 
	 * @return the item id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * The level required to operate the hatchet whether its in your inventory or in your equipment.
	 * 
	 * @return the level required for operation
	 */
	public int getLevelRequired() {
		return levelRequired;
	}

	/**
	 * The animation displayed when the hatchet is being operated
	 * 
	 * @return the hatchet animation
	 */
	public int getAnimation() {
		return animation;
	}

	/**
	 * The speed at which this axe effects log cut time
	 * 
	 * @return the chop speed of the hatchet
	 */
	public double getChopSpeed() {
		return chopSpeed;
	}

	/**
	 * Determines the best hatchet the player has in their inventory, or equipment.
	 * 
	 * @param player the player we're trying to find the best axe for
	 * @return null if the player doesn't have a hatchet they can operate, otherwise the best hatchet on their person.
	 */
	public static Machete getBest(Player player) {
		Machete machete = null;
		for (Machete h : values()) {
			if ((player.getItems().playerHasItem(h.itemId) || player.getItems().isWearingItem(h.itemId)) && player.playerLevel[Player.playerWoodcutting] >= h.levelRequired) {
				if (machete == null) {
					machete = h;
					continue;
				}
				if (machete.levelRequired < h.levelRequired) {
					machete = h;
				}
			}
		}
		return machete;
	}
}
