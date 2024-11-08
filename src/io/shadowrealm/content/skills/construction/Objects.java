package io.shadowrealm.content.skills.construction;

import com.google.gson.Gson;
import io.shadowrealm.Test1;
import io.shadowrealm.model.collisionmap.ObjectDef;
import io.shadowrealm.model.entity.player.Player;

public class Objects {

	enum CON_DATA {
		Crude_wooden_chair(4515, 6752, 1, 58, 1539, 2, 960, 2, "Crude wooden chair", 898, 0),
		wooden_chair(4515, 6753, 8, 87, 1539, 3, 960, 3, "Wooden chair", 898, 1),
		rocking_chair(4515, 6754, 14, 87, 1539, 3, 960, 3, "Rocking chair", 898, 2),
		oak_chair(4515, 6755, 19, 120, 8778, 2, "Oak chair", 898, 3),
		oak_armchair(4515, 6756, 26, 180, 8778, 3, "Oak armchair", 898, 4),
		teak_armchair(4515, 6757, 35, 180, 8780, 2, "Teak armchair", 898, 5),
		mahogany_armchair(4515, 6758, 50, 280, 8782, 2, "Mahogany armchair", 898, 6),

		Crude_wooden_chair2(4516, 6752, 1, 58, 1539, 2, 960, 2, "Crude wooden chair", 898, 0),
		wooden_chair2(4516, 6753, 8, 87, 1539, 3, 960, 3, "Wooden chair", 898, 1),
		rocking_chair2(4516, 6754, 14, 87, 1539, 3, 960, 3, "Rocking chair", 898, 2),
		oak_chair2(4516, 6755, 19, 120, 8778, 2, "Oak chair", 898, 3),
		oak_armchair2(4516, 6756, 26, 180, 8778, 3, "Oak armchair", 898, 4),
		teak_armchair2(4516, 6757, 35, 180, 8780, 2, "Teak armchair", 898, 5),
		mahogany_armchair2(4516, 6758, 50, 280, 8782, 2, "Mahogany armchair", 898, 6),

		Crude_wooden_chair3(4517, 6752, 1, 58, 1539, 2, 960, 2, "Crude wooden chair", 898, 0),
		wooden_chair3(4517, 6753, 8, 87, 1539, 3, 960, 3, "Wooden chair", 898, 1),
		rocking_chair3(4517, 6754, 14, 87, 1539, 3, 960, 3, "Rocking chair", 898, 2),
		oak_chair3(4517, 6755, 19, 120, 8778, 2, "Oak chair", 898, 3),
		oak_armchair3(4517, 6756, 26, 180, 8778, 3, "Oak armchair", 898, 4),
		teak_armchair3(4517, 6757, 35, 180, 8780, 2, "Teak armchair", 898, 5),
		mahogany_armchair3(4517, 6758, 50, 280, 8782, 2, "Mahogany armchair", 898, 6),

		WOODEN_BOOKCASE(4521, 6768, 4, 115, 1539, 4, 960, 4, "Wooden bookcase", 898, 0),
		OAK_BOOKCASE(4521, 6769, 29, 180, 8778, 3, "Oak bookcase", 898, 1),
		MAHOGANY_BOOKCASE(4521, 6770, 40, 420, 8782, 3, "Mahogany bookcase", 898, 2),
		CLAY_FIREPLACE(4523, 6780, 10, 30, 1761, 3, "Clay fireplace", 898, 0),
		LIMESTONE_FIREPLACE(4523, 6782, 31, 40, 3420, 2, "Limestone fireplace", 898, 1),
		MARBLE_FIREPLACE(4523, 6784, 52, 500, 8786, 1, "Marble fireplace", 898, 2),

		Wooden_table(15405, 13577, 12, 87, 960, 3, 1539, 3, "Wooden table", 898, 0),
		oak_table(15405, 13578, 32, 180, 8778, 3, "Oak table", 898, 1),
		teak_table(15405, 13579, 42, 270, 8780, 3, "Teak table", 898, 2),

		Pump_and_drain(15404, 13559, 7, 100, 2353, 5, "Pump and drain", 898, 0),
		Pump_and_tub(15404, 13561, 27, 200, 2353, 10, "Pump and tub", 898, 1),
		silk(15404, 13564, 47, 300, 2353, 15, "Sink", 898, 2),
		gold_silk(15404, 26458, 47, 11144, 26266, 10, 8782, 5, 8784, 5, "Gold Sink", 898, 3),

		wooden_larder(15403, 13565, 9, 228, 1539, 8, 960, 8, "Wooden larder", 898,0),
		oak_larder(15403, 13566, 33, 480, 8778, 8, "Oak larder", 898, 1),
		teak_larder(15403, 13567, 43, 750, 8780, 8, 8790, 2, "Teak larder", 898, 2),

		Beer_barrel(15401, 13568, 7, 87, 1539, 3, 960, 3, "Beer barrel", 898, 0),
		Cider_barrel(15401, 13569, 12, 91, 1539, 3, 960, 3, 5763, 8, "Cider barrel", 898, 1),
		asgarnian_ale_barrel(15401, 13570, 18, 184, 8778, 3, 1905, 8, "Asgarnian ale", 898, 2),
		greenmans_ale_barrel(15401, 13571, 26, 184, 8778, 3, 1909, 8, "Greenman's ale", 898, 3),
		Dragon_bitter_barrel(15401, 13572, 36, 224, 8778, 3, 2353, 2, 1911, 8, "Dragon bitter", 898, 4),
		Chefs_delight_barrel(15401, 13573, 48, 224, 8778, 3, 2353, 2, 5755, 8, "Chef's delight", 898, 5),

		cat_blanket(15402, 13574, 5, 15, 8790, 1, "Cat blanket", 898, 0),
		cat_basket(15402, 13575, 19, 58, 1539, 2, 960, 2, "Cat basket", 898, 1),
		Cushioned_basket(15402, 13576, 19, 58, 1539, 2, 960, 2, 1737, 2, "Cushioned basket", 898, 2),

		firepit(15398, 13528, 5, 40, 2353, 1, 1761, 2, "Firepit", 898, 0),
		firepit_with_hook(15398, 13529, 11, 60, 2353, 2, 1761, 2, "Firepit with hook", 898, 1),
		firepit_with_pot(15398, 13531, 17, 80, 2353, 3, 1761, 2, "Firepit with pot", 898, 2),
		small_oven(15398, 13533, 24, 80, 2353, 4, "Small oven", 898, 3),
		large_oven(15398, 13536, 29, 100, 2353, 5, "Large oven", 898, 4),
		steel_range(15398, 13539, 34, 120, 2353, 6, "Steel range", 898, 5),
		fancy_range(15398, 13542, 42, 160, 2353, 8, "Fancy range", 898, 6);

		int fake_object;
		int real_object;
		int Level;
		int xp;
		int item1 = -1;
		int amount1 = -1;
		int item2 = -1;
		int amount2 = -1;
		int item3 = -1;
		int amount3 = -1;
		int item4 = -1;
		int amount4 = -1;
		int item5 = -1;
		int amount5 = -1;
		String objectname;
		int animation;
		int objectverison;

		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, String objectName, int animation, int objectverison){
			this.fake_object = fake_object;
			this.real_object = real_object;
			this.Level = Level;
			this.xp = xp;
			this.item1 = item1;
			this.amount1 = amount1;
			this.objectname = objectName;
			this.animation = animation;
			this.objectverison = objectverison;
		}
		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, int item2, int amount2, String objectName, int animation, int objectverison){
			this.fake_object = fake_object;
			this.real_object = real_object;
			this.Level = Level;
			this.xp = xp;
			this.item1 = item1;
			this.amount1 = amount1;
			this.item2 = item2;
			this.amount2 = amount2;
			this.objectname = objectName;
			this.animation = animation;
			this.objectverison = objectverison;
		}
		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, int item2, int amount2, int item3, int amount3, String objectName, int animation, int objectverison){
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
			this.objectverison = objectverison;
		}
		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, int item2, int amount2, int item3, int amount3, int item4, int amount4, String objectName, int animation, int objectverison){
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
			this.item4 = item4;
			this.amount4 = amount4;
			this.objectname = objectName;
			this.animation = animation;
			this.objectverison = objectverison;
		}
		CON_DATA(int fake_object, int real_object, int Level, int xp, int item1, int amount1, int item2, int amount2, int item3, int amount3, int item4, int amount4, int item5, int amount5, String objectName, int animation, int objectverison){
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
			this.item4 = item4;
			this.amount4 = amount4;
			this.item5 = item5;
			this.amount5 = amount5;
			this.objectname = objectName;
			this.animation = animation;
			this.objectverison = objectverison;
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

		public int getItem4() {
			return item4;
		}

		public int getAmount4() {
			return amount4;
		}

		public int getItem5() {
			return item5;
		}

		public int getAmount5() {
			return amount5;
		}

		public String getObjectname() {
			return objectname;
		}

		public int getAnimation() {
			return animation;
		}

		public int getObjectverison() {
			return objectverison;
		}
	}

	public static void openInterface(Player c, int object) {
		for (CON_DATA con : CON_DATA.values()) {
			if (object == con.getFake_object()) {
				if (c.playerLevel[22] >= con.getLevel()) {
					if(object == 4515){
						c.getPA().showInterface(25030);

					}
				}
			}
		}
	}
	public static void handleObjectClick(Player c, int objectType) {
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
					if (c.getItems().playerHasItem(con.getItem1(), con.getAmount1()) && c.getItems().playerHasItem(con.getItem2(), con.getAmount2()) && c.getItems().playerHasItem(con.getItem3(), con.getAmount3())&& c.getItems().playerHasItem(con.getItem4(), con.getAmount4()) && c.getItems().playerHasItem(con.getItem5(), con.getAmount5())) {
						if (con.getItem1() != -1 || con.getItem2() != -1 || con.getItem3() != -1 || con.getItem4() != -1 || con.getItem5() != -1) {
							if (c.getItems().playerHasItem(con.getItem1(), con.getAmount1()) && c.getItems().playerHasItem(con.getItem2(), con.getAmount2()) && c.getItems().playerHasItem(con.getItem3(), con.getAmount3()) && c.getItems().playerHasItem(con.getItem4(), con.getAmount4()) && c.getItems().playerHasItem(con.getItem5(), con.getAmount5())) {
								if (c.getItems().isStackable(con.getItem1()) && c.getItems().isStackable(con.getItem2()) && c.getItems().isStackable(con.getItem3())) {
									c.getItems().deleteItem2(con.getItem1(), con.getAmount1());
									c.getItems().deleteItem2(con.getItem2(), con.getAmount2());
									c.getItems().deleteItem2(con.getItem3(), con.getAmount3());
									c.getItems().deleteItem2(con.getItem4(), con.getAmount4());
									c.getItems().deleteItem2(con.getItem5(), con.getAmount5());
								} else {
									c.getItems().deleteItem2(con.getItem1(), con.getAmount1());
									c.getItems().deleteItem2(con.getItem2(), con.getAmount2());
									c.getItems().deleteItem2(con.getItem3(), con.getAmount3());
									c.getItems().deleteItem2(con.getItem4(), con.getAmount4());
									c.getItems().deleteItem2(con.getItem5(), con.getAmount5());
								}
							} else {
								if (con.getItem1() != -1 && con.getItem2() != -1 && con.getItem3() == -1 && con.getItem4() == -1 && con.getItem5() == -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else if (con.getItem1() != -1 && con.getItem2() != -1 && con.getItem3() != -1 && con.getItem4() == -1 && con.getItem5() == -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
									c.sendMessage("and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else if (con.getItem1() != -1 && con.getItem2() != -1 && con.getItem3() != -1 && con.getItem4() != -1 && con.getItem5() == -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
									c.sendMessage("and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + " and " + con.getAmount4() + " " + c.getItems().getItemName(con.getItem4()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else if (con.getItem1() != -1 && con.getItem2() != -1 && con.getItem3() != -1 && con.getItem4() != -1 && con.getItem5() != -1) {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
									c.sendMessage("and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + " and " + con.getAmount4() + " " + c.getItems().getItemName(con.getItem4()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								} else {
									c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
								}
								return;
							}
						}
						Room room = Construction.getCurrentRoom(c);
						ObjectDef def = ObjectDef.getObjectDef(con.getReal_object());
						String objectName = def.name;
						int[] newCoords = Test1.getOriginalObjectCoords(room.getRotation(), new int[]{c.objectX % 8, c.objectY % 8});
						RoomObject roomObject = room.getObjectByPosition(newCoords[0], newCoords[1]);

						if (roomObject == null) {
							return;
						}

						RoomObject replacementObject = new RoomObject(con.getReal_object(), roomObject.getX(), roomObject.getY(), roomObject.getRotation(), roomObject.getType(), objectName);

						/*if (room.getCustomObjectByPosition(newCoords[0], newCoords[1]) != null) {
							c.sendMessage("You remove the <col=0000FF>" + CON_DATA[i][8] + ".");
							replacementObject.setId( CON_DATA[i][0]);
							room.removeCustomObject(c, replacementObject);
							return;
						}*/

						c.getPA().addSkillXPMultiplied(con.getXp(), 22, true);
						if (con.getAnimation() != 0) {
							c.startAnimation(con.getAnimation());
						}

						c.sendMessage("You build a <col=0000FF>" + con.getObjectname() + ".");
						room.newObject(c, replacementObject);
						return;
					} else {
						if(con.getItem1() != -1 && con.getItem2() != -1 &&  con.getItem3() == -1 &&  con.getItem4() == -1 &&  con.getItem5() == -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						} else 	if(con.getItem1() != -1 &&  con.getItem2() != -1 &&  con.getItem3() != -1 &&  con.getItem4() == -1 &&  con.getItem5() == -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
							c.sendMessage("<col=DD5C3E>and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						} else 	if(con.getItem1() != -1 &&  con.getItem2() != -1 &&  con.getItem3() != -1 &&  con.getItem4() != -1 &&  con.getItem5() == -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
							c.sendMessage("<col=DD5C3E>and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + " and " + con.getAmount4() + " " + c.getItems().getItemName(con.getItem4()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
						} else 	if(con.getItem1() != -1 &&  con.getItem2() != -1 &&  con.getItem3() != -1 &&  con.getItem4() != -1 &&  con.getItem5() != -1) {
							c.sendMessage("You need at least <col=DD5C3E>" + con.getAmount1() + " " + c.getItems().getItemName(con.getItem1()) + " and " + con.getAmount2() + " " + c.getItems().getItemName(con.getItem2()) + "");
							c.sendMessage("<col=DD5C3E>and " + con.getAmount3() + " " + c.getItems().getItemName(con.getItem3()) + " and " + con.getAmount4() + " " + c.getItems().getItemName(con.getItem4()) + "s <col=000000>to build a <col=0000FF>" + con.getObjectname() + ".");
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

				int[] newCoords = Test1.getOriginalObjectCoords(room.getRotation(), new int[] { c.objectX % 8, c.objectY % 8 });

				System.out.println(new Gson().toJson(newCoords));
				
				RoomObject roomObject = room.getObjectByPosition(newCoords[0], newCoords[1]);

				if (roomObject == null) {
					return;
				}

				room.removeCustomObject(c, new RoomObject( con.getFake_object(), roomObject.getX(), roomObject.getY(), roomObject.getRotation(), roomObject.getType()));
				c.sendMessage("You remove the <col=0000FF>" + con.getObjectname() + ".");
			}
		}
	}
}
