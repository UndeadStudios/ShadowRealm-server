package io.exilius.model.entity.player.packets;

import io.exilius.model.entity.player.PacketType;
import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

/**
 * Change appearance
 **/
public class ChangeAppearance implements PacketType {

	private static final int[][] MALE_VALUES = { 
			{ 0, 8 }, // head
			{ 10, 17 }, // jaw
			{ 18, 25 }, // torso
			{ 26, 31 }, // arms
			{ 33, 34 }, // hands
			{ 36, 40 }, // legs
			{ 42, 43 }, // feet
	};

	private static final int[][] FEMALE_VALUES = { 
			{ 45, 54 }, // head
			{ -1, -1 }, // jaw
			{ 56, 60 }, // torso
			{ 61, 65 }, // arms
			{ 67, 68 }, // hands
			{ 70, 77 }, // legs
			{ 79, 80 }, // feet
	};

	private static final int[][] ALLOWED_COLORS = { 
			{ 0, 24 }, // hair color
			{ 0, 28 }, // torso color
			{ 0, 28 }, // legs color
			{ 0, 5 }, // feet color
			{ 0, 10 } // skin color
	};

	public static int getRandomValue(boolean male, int valueIdx) {
		int[][] values = male ? MALE_VALUES : FEMALE_VALUES;
		int[] category = values[valueIdx];
		int minimumVal = category[0];
		int maximumVal = category[1] - 1;
		return Misc.random(minimumVal, maximumVal);
	}

	public static int getRandomColor(int colorIdx) {
		int[][] values = ALLOWED_COLORS;
		int[] category = values[colorIdx];
		int minimumVal = category[0];
		int maximumVal = category[1] - 1;

		int randomColor = Misc.random(minimumVal, maximumVal);
		if (colorIdx == 4 && (randomColor == 8 || randomColor == 9 || randomColor == 10))
			return getRandomColor(colorIdx);

		return randomColor;
	}

	public static void generateRandomAppearance(Player player) {
		boolean isMale = Misc.random(1) == 0;
		player.playerAppearance[0] = isMale ? 0 : 1; // gender
		player.playerAppearance[1] = getRandomValue(isMale, 0); // head
		player.playerAppearance[7] = isMale ? getRandomValue(true, 1) : -1; // jaw
		player.playerAppearance[2] = getRandomValue(isMale, 2);// Torso
		player.playerAppearance[3] = getRandomValue(isMale, 3); // arms
		player.playerAppearance[4] = getRandomValue(isMale, 4); // hands
		player.playerAppearance[5] = getRandomValue(isMale, 5); // legs
		player.playerAppearance[6] = getRandomValue(isMale, 6); // feet

		player.playerAppearance[8] = getRandomColor(0); // hair colour
		player.playerAppearance[9] = getRandomColor(1); // torso colour
		player.playerAppearance[10] = getRandomColor(2); // legs colour
		player.playerAppearance[11] = getRandomColor(3); // feet colour
		player.playerAppearance[12] = getRandomColor(4); // skin colour
	}

	@Override
	public void processPacket(final Player c, final int packetType, final int packetSize) {
		if (c.getMovementState().isLocked())
			return;
		if (c.isFping()) {
			/**
			 * Cannot do action while fping
			 */
			return;
		}
		c.interruptActions();
		int gender = c.getInStream().readSignedByte();
		int head = c.getInStream().readSignedByte();
		int jaw = c.getInStream().readSignedByte();
		int torso = c.getInStream().readSignedByte();
		int arms = c.getInStream().readSignedByte();
		int hands = c.getInStream().readSignedByte();
		int legs = c.getInStream().readSignedByte();
		int feet = c.getInStream().readSignedByte();
		int hairColour = c.getInStream().readSignedByte();
		int torsoColour = c.getInStream().readSignedByte();
		int legsColour = c.getInStream().readSignedByte();
		int feetColour = c.getInStream().readSignedByte();
		int skinColour = c.getInStream().readSignedByte();

		if (c.canChangeAppearance) {


			c.playerAppearance[0] = gender; // gender
			c.playerAppearance[6] = feet; // feet
			c.playerAppearance[7] = jaw; // beard
			c.playerAppearance[8] = hairColour; // hair colour
			c.playerAppearance[9] = torsoColour; // torso colour
			c.playerAppearance[10] = legsColour; // legs colour
			c.playerAppearance[11] = feetColour; // feet colour
			c.playerAppearance[12] = skinColour; // skin colour
			if (head < 0) // head
				c.playerAppearance[1] = head + 256;
			else
				c.playerAppearance[1] = head;
			if (torso < 0)
				c.playerAppearance[2] = torso + 256;
			else
				c.playerAppearance[2] = torso;
			if (arms < 0)
				c.playerAppearance[3] = arms + 256;
			else
				c.playerAppearance[3] = arms;
			if (hands < 0)
				c.playerAppearance[4] = hands + 256;
			else
				c.playerAppearance[4] = hands;
			if (legs < 0)
				c.playerAppearance[5] = legs + 256;
			else
				c.playerAppearance[5] = legs;

			c.getPA().removeAllWindows();
			c.getPA().requestUpdates();
			c.canChangeAppearance = false;


		}
	}

}