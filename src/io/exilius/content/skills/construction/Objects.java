package io.exilius.content.skills.construction;

import com.google.gson.Gson;
import io.exilius.Test1;
import io.exilius.model.collisionmap.ObjectDef;
import io.exilius.model.entity.npc.pets.PetHandler;
import io.exilius.model.entity.player.Player;

public class Objects {

	enum CON_DATA {
		Crude_wooden_chair(4515, 6752, 1, 58, 1539, 2, 960, 2, -1, -1, "Crude wooden chair", 898),
		wooden_chair(4515, 6753, 8, 87, 1539, 3, 960, 3, -1, -1, "Wooden chair", 898),
		rocking_chair(4515, 6754, 14, 87, 1539, 3, 960, 3, -1, -1, "Rocking chair", 898),
		oak_chair(4515, 6755, 19, 120, 8778, 2, -1, -1, -1, -1, "Oak chair", 898),
		oak_armchair(4515, 6756, 26, 180, 8778, 3, -1, -1, -1, -1, "Oak armchair", 898),
		teak_armchair(4515, 6757, 35, 180, 8780, 2, -1, -1, -1, -1, "Teak armchair", 898),
		mahogany_armchair(4515, 6758, 50, 280, 8782, 2, -1, -1, -1, -1, "Mahogany armchair", 898),

		Crude_wooden_chair2(4516, 6752, 1, 58, 1539, 2, 960, 2, -1, -1, "Crude wooden chair", 898),
		wooden_chair2(4516, 6753, 8, 87, 1539, 3, 960, 3, -1, -1, "Wooden chair", 898),
		rocking_chair2(4516, 6754, 14, 87, 1539, 3, 960, 3, -1, -1, "Rocking chair", 898),
		oak_chair2(4516, 6755, 19, 120, 8778, 2, -1, -1, -1, -1, "Oak chair", 898),
		oak_armchair2(4516, 6756, 26, 180, 8778, 3, -1, -1, -1, -1, "Oak armchair", 898),
		teak_armchair2(4516, 6757, 35, 180, 8780, 2, -1, -1, -1, -1, "Teak armchair", 898),
		mahogany_armchair2(4516, 6758, 50, 280, 8782, 2, -1, -1, -1, -1, "Mahogany armchair", 898),

		Crude_wooden_chair3(4517, 6752, 1, 58, 1539, 2, 960, 2, -1, -1, "Crude wooden chair", 898),
		wooden_chair3(4517, 6753, 8, 87, 1539, 3, 960, 3, -1, -1, "Wooden chair", 898),
		rocking_chair3(4517, 6754, 14, 87, 1539, 3, 960, 3, -1, -1, "Rocking chair", 898),
		oak_chair3(4517, 6755, 19, 120, 8778, 2, -1, -1, -1, -1, "Oak chair", 898),
		oak_armchair3(4517, 6756, 26, 180, 8778, 3, -1, -1, -1, -1, "Oak armchair", 898),
		teak_armchair3(4517, 6757, 35, 180, 8780, 2, -1, -1, -1, -1, "Teak armchair", 898),
		mahogany_armchair3(4517, 6758, 50, 280, 8782, 2, -1, -1, -1, -1, "Mahogany armchair", 898),

		WOODEN_BOOKCASE(4521, 6768, 4, 115, 1539, 4, 960, 4, -1, -1, "Wooden bookcase", 898),
		OAK_BOOKCASE(4521, 6769, 29, 180, 8778, 3, -1, -1, -1, -1, "Oak bookcase", 898),
		MAHOGANY_BOOKCASE(4521, 6770, 40, 420, 8782, 3, -1, -1, -1, -1, "Mahogany bookcase", 898),
		CLAY_FIREPLACE(4523, 6780, 10, 30, 1761, 3, -1, -1, -1, -1, "Clay fireplace", 898),
		LIMESTONE_FIREPLACE(4523, 6782, 31, 40, 3420, 2, -1, -1, -1, -1, "Limestone fireplace", 898),
		MARBLE_FIREPLACE(4523, 6784, 52, 500, 8786, 1, -1, -1, -1, -1, "Marble fireplace", 898);

		int fake_object;
		int real_object;
		int Level;
		int xp;
		int item1;
		int amount1;
		int item2;
		int amount2;
		int item3;
		int amount3;
		String objectname;
		int animation;

		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, int item2, int amount2, int item3, int amount3, String objectName, int animation){
			this.fake_object = fake_object;
			this.real_object = real_object;
			this.Level = Level;
			this.xp = xp;
			this.item1 = item1;
			this.amount1 = amount1;
			this.item2 = item2;
			this.amount2 = amount2;
			this.item3 = item3;
			this.amount3 = amount3;
			this.objectname = objectName;
			this.animation = animation;
		}

		public int getFake_object() {
			return fake_object;
		}

		public int getReal_object() {
			return real_object;
		}

		public int getLevel() {
			return Level;
		}

		public int getXp() {
			return xp;
		}

		public int getItem1() {
			return item1;
		}

		public int getAmount1() {
			return amount1;
		}

		public int getItem2() {
			return item2;
		}

		public int getAmount2() {
			return amount2;
		}

		public int getItem3() {
			return item3;
		}

		public int getAmount3() {
			return amount3;
		}

		public String getObjectname() {
			return objectname;
		}

		public int getAnimation() {
			return animation;
		}
	}

	public static void handleObjectClick(Player c, int objectType, int obX, int obY) {
		House house = c.getHouse();

		if (house == null || !house.getOwner().equals(c) || !house.isBuildMode()) {
			c.sendMessage("You must be in build mode to do this.");
			return;
		}
		if (!c.getItems().playerHasItem(2347) || !c.getItems().playerHasItem(8794)) {
			c.sendMessage("You need a hammer and saw to make this.");
			return;
		}
		for (CON_DATA con : CON_DATA.values()) {
			if (objectType ==  con.getFake_object()) {
				if (c.playerLevel[22] >= con.getLevel()) {
					if (c.getItems().playerHasItem(con.getItem1(), con.getAmount1()) && c.getItems().playerHasItem(con.getItem2(), con.getAmount2()) && c.getItems().playerHasItem(con.getItem3(), con.getAmount3())) {
						if (con.getItem1() != -1 ||  con.getItem2() != -1 ||  con.getItem3() != -1) {
							if (c.getItems().playerHasItem(con.getItem1(), con.getAmount1()) && c.getItems().playerHasItem( con.getItem2(),  con.getAmount2()) && c.getItems().playerHasItem( con.getItem3(),  con.getAmount3())) {
								if (c.getItems().isStackable(con.getItem1()) && c.getItems().isStackable(  con.getItem2()) && c.getItems().isStackable(  con.getItem3())) {
									c.getItems().deleteItem2(con.getItem1(), con.getAmount1());
									c.getItems().deleteItem2(con.getItem2(), con.getAmount2());
									c.getItems().deleteItem2(con.getItem3(), con.getAmount3());
								} else {
									c.getItems().deleteItem2( con.getItem1(), con.getAmount1());
									c.getItems().deleteItem2(  con.getItem2(), con.getAmount2());
									c.getItems().deleteItem2(  con.getItem3(), con.getAmount3());
								}
							} else {
								if(con.getItem1() != -1 && con.getItem2() != -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else 	if(con.getItem1() != -1 &&  con.getItem2() != -1 &&  con.getItem3() != -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								}
								return;
							}
						}
						
						Room room = Construction.getCurrentRoom(c);
						ObjectDef def = ObjectDef.getObjectDef( con.getReal_object());
						String objectName = def.name;
						int[] newCoords = Test1.getOriginalObjectCoords(room.getRotation(), new int[] { obX % 8, obY % 8 });
						RoomObject roomObject = room.getObjectByPosition(newCoords[0], newCoords[1]);

						if (roomObject == null) {
							return;
						}
						
						RoomObject replacementObject = new RoomObject( con.getReal_object(), roomObject.getX(), roomObject.getY(), roomObject.getRotation(), objectName);

						/*if (room.getCustomObjectByPosition(newCoords[0], newCoords[1]) != null) {
							c.sendMessage("You remove the <col=0000FF>" + CON_DATA[i][8] + ".");
							replacementObject.setId( CON_DATA[i][0]);
							room.removeCustomObject(c, replacementObject);
							return;
						}*/

						c.getPA().addSkillXPMultiplied( con.getXp(), 22, true);
						if (con.getAnimation() != 0) {
							c.startAnimation(con.getAnimation());
						}
						c.sendMessage("You build a <col=0000FF>" + con.getObjectname() + ".");
						room.newObject(c, replacementObject);
						return;
					} else {
						if(con.getItem1() != -1 && con.getItem2() != -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						} else 	if(con.getItem1() != -1 &&  con.getItem2() != -1 &&  con.getItem3() != -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						} else {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						}
					}
				} else {
					c.sendMessage("You need a construction level of <col=DD5C3E>" + con.getLevel() + " <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
				}
			}
		}

		for (CON_DATA con : CON_DATA.values()) {
			if (objectType ==  con.getReal_object()) {
				Room room = Construction.getCurrentRoom(c);

				int[] newCoords = Test1.getOriginalObjectCoords(room.getRotation(), new int[] { obX % 8, obY % 8 });

				System.out.println(new Gson().toJson(newCoords));
				
				RoomObject roomObject = room.getObjectByPosition(newCoords[0], newCoords[1]);

				if (roomObject == null) {
					return;
				}

				room.removeCustomObject(c, new RoomObject( con.getFake_object(), roomObject.getX(), roomObject.getY(), roomObject.getRotation()));
				c.sendMessage("You remove the <col=0000FF>" + con.getObjectname() + ".");
			}
		}
	}
}
