package io.exilius.content.skills;

import io.exilius.Server;
import io.exilius.content.achievement_diary.impl.FaladorDiaryEntry;
import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.player.Boundary;
import io.exilius.model.entity.player.Player;
import io.exilius.model.world.objects.GlobalObject;
import io.exilius.util.Location3D;
import org.apache.commons.lang3.RandomUtils;

public final class FlaxPicking {

	/**
	 * The single instance of this class
	 */
	private static final FlaxPicking INSTANCE = new FlaxPicking();

	/**
	 * Attempts to pick a new flax on the map by creating an event that continues until there is no flax or the player has no more room.
	 * 
	 * @param player the player picking the flax
	 * @param location the location of the flax
	 */
	public final void pick(Player player, Location3D location) {
		player.getPA().stopSkilling();
		if (player.getItems().freeSlots() == 0) {
			player.sendMessage("You have run out of free slots.");
			return;
		}
		Server.getEventHandler().submit(new FlaxPickingEvent(player, 4, location));
	}

	/**
	 * The single {@link FlaxPicking} object that exists
	 * 
	 * @return the single instance
	 */
	public static final FlaxPicking getInstance() {
		return INSTANCE;
	}

	/**
	 * A class that is created to handle the flax picking event for a single player
	 * 
	 * @author Jason MacKeigan
	 * @date May 15, 2015, 2015, 10:54:44 AM
	 */
	private final class FlaxPickingEvent extends Event<Player> {

		/**
		 * The location of the flax
		 */
		private final Location3D location;

		/**
		 * Creates a new {@link FlaxPickingEvent} for a single player
		 * 
		 * @param attachment the player
		 * @param ticks the time the event is alive for
		 * @param location the location
		 */
		public FlaxPickingEvent(Player attachment, int ticks, Location3D location) {
			super("skilling", attachment, ticks);
			this.location = location;
		}

		@Override
		public void execute() {
			if (plr == null || plr.isDisconnected()) {
				stop();
				return;
			}

			if (plr.getItems().freeSlots() == 0) {
				plr.sendMessage("You have run out of free slots.");
				stop();
				return;
			}

			boolean originalObject = plr.getRegionProvider().get(location.getX(), location.getY()).isWorldObject(14896, location.getX(), location.getY(), location.getZ());
			boolean spawnedObject = Server.getGlobalObjects().exists(14896, location.getX(), location.getY());

			if (!originalObject && !spawnedObject || Server.getGlobalObjects().exists(-1, location.getX(), location.getY())) {
				stop();
				return;
			}
			

			if (Boundary.isIn(plr, Boundary.FALADOR_BOUNDARY)) {
				plr.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.PICK_FLAX);
			}
			if (Boundary.isIn(plr, Boundary.SEERS_BOUNDARY)) {
				plr.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.PICK_FLAX_SEERS);
			}
			plr.startAnimation(827);
			plr.getItems().addItem(1779, 1);

			if (RandomUtils.nextInt(0, 3) == 1) {
				Server.getGlobalObjects().add(new GlobalObject(-1, location.getX(), location.getY(), location.getZ(), 0, 10, 50, 14896));
				stop();
			}
		}

		@Override
		public void stop() {
			super.stop();
			if (plr != null && !plr.isDisconnected()) {
				plr.stopAnimation();
			}
		}

	}

}
