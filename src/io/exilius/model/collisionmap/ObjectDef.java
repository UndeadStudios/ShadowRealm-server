package io.exilius.model.collisionmap;

import io.exilius.Server;

import java.io.IOException;

public final class ObjectDef {

	private short[] originalTexture;
	private short[] modifiedTexture;

	public static ObjectDef getObjectDef(int id) {
		if (id > streamIndices.length) {
			id = streamIndices.length - 1;
		}
		
		for (int j = 0; j < 20; j++)
			if (cache[j].type == id)
				return cache[j];

		cacheIndex = (cacheIndex + 1) % 20;
		ObjectDef objectDef = cache[cacheIndex];
		if (id > streamIndices.length - 1 || id < 0) {
			return null;
		}

		stream.currentOffset = streamIndices[id];

		objectDef.type = id;
		objectDef.setDefaults();
		objectDef.readValues(stream);
		switch (id) {
			case 36201: // Raids 1 lobby entrance
				objectDef.actions = new String[]{ "Enter", null, null, null, null};
				break;
			case 591:
				objectDef.name = "Item Upgrades Table";
				objectDef.actions = new String[]{"Forge", null, null, null, null};
				break;
			case 36062:
				objectDef.description = "Teleports you anywhere around Exilius.";
				objectDef.actions = new String[] { "Activate", "Previous Location", null, null, null };
				break;
			case 4152:
				objectDef.name = "Skilling Portal";
				objectDef.description = "Teleports you to various skilling areas.";
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;
			case 1206:
				objectDef.name = "Hespori Vines";
				objectDef.description = "The vines of Hespori.";
				objectDef.actions = new String[] { "Pick", null, null, null, null };
				break;
			case 33222:
				objectDef.name = "Burning Ore";
				objectDef.description = "I should try heating this up.";
				objectDef.actions = new String[] { "Mine", null, null, null, null };
				break;
			case 8880:
				objectDef.name = "Tool Box";
				objectDef.description = "Useful tools for resources in the area.";
				objectDef.actions = new String[] { "Take-tools", null, null, null, null };
				break;
			case 29771:
				objectDef.name = "Tools";
				objectDef.description = "Useful tools for resources in the area.";
				objectDef.actions = new String[] { null , null, null, null, null };
				break;
			case 33223:
				objectDef.name = "Enchanted stone";
				objectDef.description = "A fragile ancient stone.";
				objectDef.actions = new String[] { "Mine", null, null, null, null };
				break;

			case 33311:
				objectDef.name = "Fire";
				objectDef.description = "Looks very hot.";
				objectDef.actions = new String[] { "Burn-essence", "Burn-runes", null, null, null };
				break;
			case 12768:
				objectDef.name = "@gre@Nature Chest";
				objectDef.description = "Requires a Hespori key to open.";
				break;
			case 4121:
				objectDef.name = "@bla@Nex @red@ Chest";
				objectDef.description = "Requires a Nex key to open.";
				break;
			case 37743: // nightmare good flower
				objectDef.animation = 8617;
				break;
			case 37740: // nightmare bad flower
				objectDef.animation = 8623;
				break;
			case 37738: // nightmare spore spawn
				objectDef.animation = 8630;
				break;
			case 35914:
				objectDef.name = "Ahrim The Blighted";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 9362:
				objectDef.name = "Dharok The Wretched";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 14766:
				objectDef.name = "Verac The Defiled";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 9360:
				objectDef.name = "Torag The Corrupted";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 28723:
				objectDef.name = "Karil The Tainted";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 31716:
				objectDef.name = "Guthan The Infested";
				objectDef.actions = new String[] { "Awaken", null, null, null, null };
				break;
			case 31622:
				objectDef.name = "Outlast Entrance";
				objectDef.actions = new String[] { "Enter", "Check Players", "Check Active", null, null };
				break;
			case 31624:
				objectDef.name = "@pur@Platinum Altar";
				break;
			case 29064:
				objectDef.name = "Exilius Leaderboards";
				objectDef.actions = new String[] { "View", null, null, null, null };
				break;
			case 33320:
				objectDef.name = "Fire of Exchange";
				objectDef.actions = new String[] { "Burn", "Burn Rates", null, null, null };
				break;
			case 33318:
				objectDef.name = "Fire of Destruction";
				objectDef.actions = new String[] { "Sacrifice", null, null, null, null };
				break;
			case 32508:
				objectDef.name = "Hunllef's Chest";
				objectDef.actions = new String[] { "Unlock", null, null, null, null };
				break;
			case 6097:
				objectDef.actions = new String[] { "Donate", null, null, null, null };
				break;
			case 14888:
				objectDef.name = "Jewelry Oven";
				break;
			case 29165:
				objectDef.name = "Coin Stack";
				objectDef.actions = new String[] { null, "Steal From", null, null, null };
				break;
			case 13681:
				objectDef.name = "Animal Cage";
				objectDef.actions = new String[] { null, null, null, null, null };
				break;
			case 30720:
				objectDef.name = "@red@Corrupt Chest";
				objectDef.actions = new String[] { "Open", null, null, null, null };
				break;
			case 34662:
				objectDef.actions = new String[] { "Open", "Teleport", null, null, null };
				break;
			case 12202:
				objectDef.actions = new String[] { "Dig", null, null, null, null };
				break;
			case 30107:
				objectDef.name = "Raids Reward Chest";
				objectDef.actions = new String[] { "Open", null, null, null, null };
				break;
			case 36197:
				objectDef.name = "Home Teleport";

				break;
			case 10823:
				objectDef.name = "Yew";
				break;
			case 10562:
				objectDef.actions = new String[] { "Open", null, null, null, null };
				break;
			case 8720:
				objectDef.name = "Vote shop";
				break;
			case 29150:
				objectDef.actions = new String[] { "Venerate", null, null, null, null };
				break;
			case 6764:
				objectDef.name = null;
				objectDef.actions = new String[] { null, null, null, null, null };
				break;
			case 2341:
				objectDef.actions = new String[] { null, null, null, null, null };
				break;
			case 14217:
				objectDef.actions = new String[5];
				break;
			case 3840:
				objectDef.actions = new String[5];
				objectDef.actions[0] = "Fill";
				objectDef.actions[1] = "Empty-From";
				objectDef.name = "Compost Bin";
				break;
			case 172:
				objectDef.name = "Ckey chest";
				break;
			case 31925:
				objectDef.name = "Max Island";
				objectDef.actions = new String[] { "Tele to", null, null, null, null };
				break;
			case 31722:
				objectDef.name = "Bond fire";
				objectDef.actions = new String[] { "Burn logs", null, null, null, null };
				break;
			case 2996:
				objectDef.name = "Vote Chest";
				objectDef.actions = new String[] { "Unlock", null, null, null, null };
				break;

			case 12309:
				objectDef.actions = new String[5];
				objectDef.actions[0] = "Bank";
				objectDef.actions[1] = "Buy gloves";
				objectDef.actions[2] = null;
				objectDef.name = "Chest";
				break;
			case 32572:
				objectDef.actions = new String[5];
				objectDef.actions[0] = "Bank";
				objectDef.name = "Group chest";
				break;
			case 1750:
				objectDef.modelIds = new int[] { 8131, };
				objectDef.name = "Willow";
				objectDef.xLength = 2;
				objectDef.yLength = 2;
				objectDef.aByte737 = 25;
				objectDef.actions = new String[] { "Chop down", null, null, null, null };
				objectDef.mapSceneId = 3;
				break;

			case 26782:
				objectDef.actions = new String[] { "Recharge", null, null, null, null };
				break;

			case 1751:
				objectDef.modelIds = new int[] { 8037, 8040, };
				objectDef.name = "Oak";
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.aByte737 = 25;
				objectDef.actions = new String[] { "Chop down", null, null, null, null };
				objectDef.mapSceneId = 1;
				break;

			case 7814:
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;

			case 8356:
				objectDef.actions = new String[] { "Teleport", "Mt. Quidamortem", null, null, null };
				break;

			case 28900:
				objectDef.actions = new String[] { "Teleport", "Recharge Crystals", null, null, null };
				break;
			case 26740:
				objectDef.name = "Player Outlast";
				objectDef.actions = new String[] { "Join", "Setup", null, null, null };
				break;

			case 28837:
				objectDef.actions = new String[] { "Set Destination", null, null, null, null };
				break;

			case 7811:
				objectDef.name = "District Supplies";
				objectDef.actions = new String[] { "Blood Money", "Free", "Quick-Sets", null, null };
				break;
			case 10061:
			case 10060:
				objectDef.name = "Trading Post";
				objectDef.actions = new String[] { "Bank", "Open", "Collect", null, null };
				break;
			case 13287:
				objectDef.name = "Storage chest (UIM)";
				objectDef.description = "A chest to store items for UIM.";
				break;
			case 1752:
				objectDef.modelIds = new int[] { 4146, };
				objectDef.name = "Hollow tree";
				objectDef.aByte737 = 25;
				objectDef.actions = new String[] { "Chop down", null, null, null, null };
				objectDef.originalModelColors = new int[] { 13592, 10512, };
				objectDef.modifiedModelColors = new int[] { 7056, 6674, };
				objectDef.mapSceneId = 0;
				break;
			case 4873:
				objectDef.name = "Wilderness Lever";
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.aByte737 = 25;
				objectDef.actions = new String[] { "Enter Deep Wildy", null, null, null, null };
				objectDef.mapSceneId = 3;
				break;
			case 29735:
				objectDef.name = "Basic Slayer Dungeon";
				break;
			case 2544:
				objectDef.name = "Dagannoth Manhole";
				break;
			case 29345:
				objectDef.name = "Training Teleports Portal";
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;
			case 29346:
				objectDef.name = "Wilderness Teleports Portal";
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;
			case 29347:
				objectDef.name = "Boss Teleports Portal";
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;
			case 29349:
				objectDef.name = "Mini-Game Teleports Portal";
				objectDef.actions = new String[] { "Teleport", null, null, null, null };
				break;
			case 7127:
				objectDef.name = "Leaderboards";
				objectDef.actions = new String[] { "Open", "Wins", "Kills", "KDR", null };
				break;
			case 4155:
				objectDef.name = "Zul Andra Portal";
				break;
			case 2123:
				objectDef.name = "Mt. Quidamortem Slayer Dungeon";
				break;
			case 4150:
				objectDef.name = "Warriors Guild Mini-game Portal";
				break;
			case 11803:
				objectDef.name = "Donator Slayer Dungeon";
				break;
			case 4151:
				objectDef.name = "Barrows Mini-game Portal";
				break;
			case 1753:
				objectDef.modelIds = new int[] { 8157, };
				objectDef.name = "Yew";
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.aByte737 = 25;
				objectDef.actions = new String[] { "Chop down", null, null, null, null };
				objectDef.mapSceneId = 3;
				break;

			case 6943:
				objectDef.modelIds = new int[] { 1270, };
				objectDef.name = "Bank booth";
				objectDef.aBoolean757 = false;
				objectDef.aByte737 = 25;
				objectDef.aByte742 = 25;
				objectDef.actions = new String[] { null, "Bank", "Collect", null, null };
				break;

			case 25016:
			case 25017:
			case 25018:
			case 25029:
				objectDef.actions = new String[] { "Push-Through", null, null, null, null };
				break;

			case 19038:
				objectDef.actions = new String[] { null, null, null, null, null };
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.width = 340; // Width
				objectDef.thickness = 500; // Thickness
				objectDef.height = 400; // Height
				break;

			case 18826:
			case 18819:
			case 18818:
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.width = 200; // Width
				objectDef.thickness = 200; // Thickness
				objectDef.height = 100; // Height
				break;

			case 27777:
				objectDef.name = "Gangplank";
				objectDef.actions = new String[] { "Travel to CrabClaw Isle", null, null, null, null };
				objectDef.xLength = 1;
				objectDef.yLength = 1;
				objectDef.width = 80; // Width
				objectDef.thickness = 80; // Thickness
				objectDef.height = 250; // Height
				break;
			case 13641:
				objectDef.name = "Teleportation Device";
				objectDef.actions = new String[] { "Quick-Teleport", null, null, null, null };
				objectDef.xLength = 1;
				objectDef.yLength = 1;
				objectDef.width = 80; // Width
				objectDef.thickness = 80; // Thickness
				objectDef.height = 250; // Height
				break;

			case 29333:
				objectDef.name = "Trading post";
				objectDef.actions = new String[] { "Open", null, "Collect", null, null };
				objectDef.modelIds = new int[] { 60884 };
				objectDef.aByte737 = 25;
				objectDef.aBoolean769 = false;
				objectDef.description = "Buy and sell items with players here!";
				break;

			case 11700:
				objectDef.modelIds = new int[] { 4086 };
				objectDef.name = "Venom";
				objectDef.xLength = 3;
				objectDef.yLength = 3;
				objectDef.aBoolean767 = false;
				objectDef.clipType = -1;
				objectDef.animation = 1261;
				objectDef.modifiedModelColors = new int[] { 31636 };
				objectDef.originalModelColors = new int[] { 10543 };
				objectDef.thickness = 160;
				objectDef.height = 160;
				objectDef.width = 160;
				objectDef.actions = new String[5];
				// objectDef.description = new String(
				// "It's a cloud of venomous smoke that is extremely toxic.");
				break;
			case 46324:
				objectDef.modelIds = new int[] { 39095 };
				objectDef.name = "Christmas Tree";
				objectDef.aBoolean757 = false;
				objectDef.xLength = 7;
				objectDef.yLength = 7;
				objectDef.aBoolean767 = true;
				objectDef.aByte737 = 25;
				objectDef.aByte742 = 25;
				objectDef.thickness = 112;
				objectDef.height = 112;
				objectDef.width = 112;
				break;
			case 11601: // 11601
				objectDef.originalTexture = new short[] { 2 };
				objectDef.modifiedTexture = new short[] { 46 };
				break;
		}
		return objectDef;
	}

	private void setDefaults() {
		models = null;
		modelIds = null;
		name = null;
		description = null;
		modifiedModelColors = null;
		originalModelColors = null;
		xLength = 1;
		yLength = 1;
		aBoolean767 = true;
		aBoolean757 = true;
		hasActions = false;
		clipType = -1;
		aBoolean769 = false;
		aBoolean764 = false;
		animation = -1;
		anInt775 = 16;
		aByte737 = 0;
		aByte742 = 0;
		actions = null;
		mapFunctionId = -1;
		mapSceneId = -1;
		aBoolean751 = false;
		clipped = true;
		thickness = 128;
		height = 128;
		width = 128;
		anInt768 = 0;
		anInt738 = 0;
		anInt745 = 0;
		anInt783 = 0;
		aBoolean736 = false;
		aBoolean766 = false;
		anInt760 = -1;
		varpId = -1;
		configId = -1;
		childrenIDs = null;
	}

	static ByteStreamExt stream;

	private static int[] streamIndices;

	public static void loadConfig() throws IOException {
		stream = new ByteStreamExt(getBuffer("loc.dat"));
		ByteStreamExt stream = new ByteStreamExt(getBuffer("loc.idx"));
		totalObjects = stream.readUnsignedWord();
		System.out.println("Total objects: " + totalObjects);
		streamIndices = new int[totalObjects];
		int i = 2;
		for (int j = 0; j < totalObjects; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ObjectDef[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new ObjectDef();
		System.out.println(totalObjects + " Object definitions loaded.");
	}

	public static byte[] getBuffer(String s) {
		try {
			java.io.File f = new java.io.File(Server.getDataDirectory() + "/mapdata/" + s);
			if (!f.exists())
				return null;
			byte[] buffer = new byte[(int) f.length()];
			try (java.io.DataInputStream dis = new java.io.DataInputStream(new java.io.FileInputStream(f))) {
				dis.readFully(buffer);
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return buffer;
		} catch (Exception e) {
		}
		return null;
	}
	private void readValues(ByteStreamExt buffer) {
		int i = -1;
		label0: do {
			int opcode;

				opcode = buffer.readUnsignedByte();
				if (opcode == 0)
					break label0;
				if (opcode == 1) {
					int modelLength = buffer.readUnsignedByte();
							models = new int[modelLength];
							modelIds = new int[modelLength];
							for (int i2 = 0; i2 < modelLength; i2++) {
								models[i2] = buffer.readUnsignedWord();
								modelIds[i2] = buffer.readUnsignedByte();
					}
				} else if (opcode == 2)
					name = buffer.readString();
				else if (opcode == 3)
					description = buffer.readString();
				else if (opcode == 5) {
					int length = buffer.readUnsignedByte();
							modelIds = null;
							models = new int[length];
							for (int l1 = 0; l1 < length; l1++)
								models[l1] = buffer.readUnsignedWord();
				} else if (opcode == 14)
					xLength = buffer.readUnsignedByte();
				else if (opcode == 15)
					yLength = buffer.readUnsignedByte();
				else if (opcode == 17) {
					interactType = 0;
					aBoolean767 = false;
				}else if (opcode == 18)
					aBoolean757 = false;
				else if (opcode == 19) {
					hasActions = (stream.readUnsignedByte() == 1);
				} else if (opcode == 21)
					clipType = 0;
				else if (opcode == 22)
					aBoolean769 = true;
				else if (opcode == 23)
					aBoolean764 = true;
				else if (opcode == 24) {
					animation = buffer.readUnsignedWord();
					if (animation == 65535)
						animation = -1;
				} else if (opcode == 27)
					interactType = 1;
				 else if (opcode == 28)
					anInt775 = buffer.readUnsignedByte();
				else if (opcode == 29)
					aByte737 = buffer.readSignedByte();
				else if (opcode == 39)
					aByte742 = buffer.readSignedByte();
				else if (opcode >= 30 && opcode < 39) {
					if (actions == null)
						actions = new String[10];
					actions[opcode - 30] = buffer.readString();
					if (actions[opcode - 30].equalsIgnoreCase("hidden"))
						actions[opcode - 30] = null;
				} else if (opcode == 40) {
					int i1 = buffer.readUnsignedByte();
					originalModelColors = new int[i1];
					modifiedModelColors = new int[i1];
					for (int i2 = 0; i2 < i1; i2++) {
						modifiedModelColors[i2] = buffer.readUnsignedWord();
						originalModelColors[i2] = buffer.readUnsignedWord();
					}
				} else if (opcode == 41) {
					int length = buffer.readUnsignedByte();
					 originalTexture = new short[length];
					 modifiedTexture = new short[length];
					for (int textureId = 0; textureId < length; textureId++) {
						 originalTexture[textureId] = (short) buffer.readUnsignedWord();
						 modifiedTexture[textureId] = (short) buffer.readUnsignedWord();
					}
				} else if (opcode == 61)
				opcode61 = buffer.readUnsignedWord();
				else if (opcode == 62)
					aBoolean751 = true;
				else if (opcode == 64)
					clipped = false;
				else if (opcode == 65)
					thickness = stream.readUnsignedWord();
				else if (opcode == 66)
					height = stream.readUnsignedWord();
				else if (opcode == 67)
					width = stream.readUnsignedWord();
				else if (opcode == 68)
					mapSceneId = buffer.readUnsignedWord();
				else if (opcode == 69)
					anInt768 = buffer.readUnsignedByte();
				else if (opcode == 70)
					anInt738 = buffer.readSignedWord();
				else if (opcode == 71)
					anInt745 = buffer.readSignedWord();
				else if (opcode == 72)
					anInt783 = buffer.readSignedWord();
				else if (opcode == 73)
					aBoolean736 = true;
				else if (opcode == 74) {
					aBoolean766 = true;
				} else if (opcode == 75) {
					stream.readUnsignedByte();
				} else if (opcode == 77 || opcode == 92) {
					varpId = buffer.readUnsignedWord();
					if (varpId == 65535)
						varpId = -1;
					configId = buffer.readUnsignedWord();
					if (configId == 65535)
						configId = -1;
					int var3 = -1;
					if(opcode == 92)
						var3 = buffer.readUnsignedWord();
					int j1 = buffer.readUnsignedByte();
					childrenIDs = new int[j1 + 2];
					for (int j2 = 0; j2 <= j1; j2++) {
						childrenIDs[j2] = buffer.readUnsignedWord();
						if (childrenIDs[j2] == 65535)
							childrenIDs[j2] = -1;
					}
					childrenIDs[j1 + 1] = var3;
				} else if(opcode == 78) {//ambient sound
					buffer.readUnsignedWord();
					buffer.readUnsignedByte();
				} else if(opcode == 79) {
					buffer.currentOffset += 5;
					int len = stream.readUnsignedByte();
					buffer.currentOffset += len * 2;
				} else if(opcode == 81) {
					clipType = (buffer.readUnsignedByte() * 256);
				} else if(opcode == 82) {
				mapFunctionId = buffer.readUnsignedWord();
				}
		} while (true);
		if (i == -1) {
			hasActions = models != null && (modelIds == null || modelIds[0] == 10);
			if (actions != null)
				hasActions = true;
		}
		if (aBoolean766) {
			aBoolean767 = false;
			aBoolean757 = false;
		}
		if (anInt760 == -1)
			anInt760 = aBoolean767 ? 1 : 0;
	}
	private ObjectDef() {
		type = -1;
	}

	public boolean hasActions() {
		return hasActions;
	}

	public boolean hasName() {
		return name != null && name.length() > 1;
	}

	public boolean solid() {
		return clipped;
	}
	public boolean isWalkable() {
		// TODO Auto-generated method stub
		return aBoolean757;
	}

	public int xLength() {
		return xLength;
	}

	public int yLength() {
		return yLength;
	}

	public boolean aBoolean767() {
		return aBoolean767;
	}

	private boolean aBoolean766;
	public boolean aBoolean736;
	public String name;
	public int xLength;
	public int mapFunctionId;
	private int[] originalModelColors;
	public int configId;
	public static boolean lowMem;
	public int type;
	public boolean aBoolean757;
	public int mapSceneId;
	public int[] childrenIDs;
	public int yLength;
	public int clipType;
	public boolean aBoolean764;
	public boolean aBoolean767;
	public int interactType = 2;
	public int anInt768;
	private static int cacheIndex;
	private int[] models;
	public int varpId;
	public int anInt775;
	public int anInt738;
	public int width; // Width
	public int anInt745;
	private int opcode61;
	public int anInt783;
	private boolean field3621;
	public int height; // Height
	private int[] modelIds;
	public int anInt760;
	public String description;
	public boolean hasActions;
	public boolean clipped;
	public byte aByte737;
	public byte aByte742;
	public boolean aBoolean769;
	public int thickness; // Thickness
	public boolean aBoolean751;
	public int animation;
	public static int totalObjects;
	private static ObjectDef[] cache;
	private int[] modifiedModelColors;
	public String[] actions;

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
