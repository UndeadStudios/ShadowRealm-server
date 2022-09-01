package io.exilius.content.skills.agility.impl.rooftop;

import io.exilius.content.achievement.AchievementType;
import io.exilius.content.achievement.Achievements;
import io.exilius.content.achievement_diary.impl.KandarinDiaryEntry;
import io.exilius.content.skills.agility.AgilityHandler;
import io.exilius.content.skills.agility.MarkOfGrace;
import io.exilius.model.cycleevent.CycleEvent;
import io.exilius.model.cycleevent.CycleEventContainer;
import io.exilius.model.cycleevent.CycleEventHandler;
import io.exilius.model.entity.player.Player;

/**
 * Rooftop Agility Seers
 * 
 * @author Matt
 */

public class RooftopSeers {

	public static final int WALL = 14927, JUMP_GAP = 14928, TIGHT_ROPE = 14932, JUMP_2ND_GAP = 14929, JUMP_3RD_GAP = 14930, JUMP_EDGE = 14931;
	
	public static int[] SEERS_OBJECTS = { WALL, JUMP_GAP, TIGHT_ROPE, JUMP_2ND_GAP, JUMP_3RD_GAP, JUMP_EDGE };

	public static boolean execute(final Player c, final int objectId) {
		
		for (int id : SEERS_OBJECTS) {
			if (System.currentTimeMillis() - c.lastObstacleFail < 3000) {
				return false;
			}
			if (c.getAgilityHandler().checkLevel(c, objectId)) {
				return false;
			}
			if (id == objectId) {
				MarkOfGrace.spawnMarks(c, "SEERS");
			}
		}
		
		switch (objectId) {
		case WALL:
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				int ticks;
				@Override
				public void execute(CycleEventContainer container) {
					if (c.isDisconnected()) {
						onStopped();
						return;
					}
					switch (ticks++) {
					case 0:
						AgilityHandler.delayEmote(c, "CLIMP_UP_WALL", 2729, 3489, 1, 1);
						break;
						
					case 2:
						AgilityHandler.delayEmote(c, "HANG_ON_POST", 2729, 3491, 3, 1);
						c.getAgilityHandler().agilityProgress[0] = true;
						break;
					}
				}

				@Override
				public void onStopped() {

				}
			}, 1);
			return true;
			
		case JUMP_GAP:
			if (AgilityHandler.failObstacle(c, 2720, 3493, 0)) {
				return false;
			}
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					int ticks;
					@Override
					public void execute(CycleEventContainer container) {
						if (c.isDisconnected()) {
							onStopped();
							return;
						}
						switch (ticks++) {
						case 0:
							AgilityHandler.delayEmote(c, "JUMP_DOWN", 2719, 3495, 2, 1);
							break;
							
						case 3:
							AgilityHandler.delayEmote(c, "JUMP", 2713, 3494, 2, 2);
							c.getAgilityHandler().agilityProgress[1] = true;
							container.stop();
							break;
						}
					}
					@Override
					public void onStopped() {

					}
				}, 1);
			return true;
			
		case TIGHT_ROPE:
			if (AgilityHandler.failObstacle(c, 2710, 3486, 0)) {
				return false;
			}
			c.setForceMovement(2710, 3481, 0, 220, "SOUTH", 762);
			c.getAgilityHandler().agilityProgress[2] = true;
			return true;
			
		case JUMP_2ND_GAP:
			if (AgilityHandler.failObstacle(c, 2711, 3475, 0)) {
				return false;
			}
			
			AgilityHandler.delayEmote(c, "JUMP", 2712, 3472, 3, 2);
			c.getAgilityHandler().agilityProgress[3] = true;
			return true;
			
		case JUMP_3RD_GAP:
			if (AgilityHandler.failObstacle(c, 2696, 3471, 0)) {
				return false;
			}
			
			AgilityHandler.delayEmote(c, "JUMP", 2700, 3465, 2, 2);
			c.getAgilityHandler().agilityProgress[4] = true;
			return true;
			
		case JUMP_EDGE:
			AgilityHandler.delayEmote(c, "JUMP", 2704, 3464, 0, 2);
			c.getAgilityHandler().roofTopFinished(c, 4, 570, 6000);
			c.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.SEERS_AGILITY);
			Achievements.increase(c, AchievementType.AGIL, 1);
			return true;
		}
		return false;
	}

}
