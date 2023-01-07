package io.exilius.model.items;

import io.exilius.Server;
import io.exilius.model.items.customs.ItemDefinition_Sub1;
import io.exilius.model.items.customs.ItemDefinition_Sub2;
import io.exilius.util.Stream;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * A class that loads & manages NPC configurations. 
 * 
 * <p>An <code>NPCDefinition</code> is a component of the NPC configuration file
 * that defines several aspects of the NPC (such as models, model colors, animations, 
 * name, description, and combat level). We use these definitions both client and server-
 * sided in order to display information on the client and pull data when necessary
 * server-sided (i.e. - for combat formulas). </p>  
 * @author Craze/Warren
 * @date Sep 20, 2015 5:13:47 PM

 */
public class ItemCacheDefinition {
	
	/**
	 * Represents the total whole number integer of Items.
	 */
	public static int ITEM_TOTAL = 30000;
    private String opcode150;

    /**
	 * Returns a {@link ItemCacheDefinition} for the specified ID.
	 * @param i	the id of the Item to get the definition for
	 * @return	the definition
	 */
    public static final ItemCacheDefinition forID(int i) {
        for(int j = 0; j < 10; j++){
            if(cache[j].id == i){
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemCacheDefinition ItemDef = cache[cacheIndex] = new ItemCacheDefinition();
       		npcData.currentOffset = streamIndices[i];
        ItemDef.id = i;
	if(i < totalItems)
        	ItemDef.decode(npcData);
        customItems(ItemDef.id);
    if(ItemDef.certTemplateID != -1) {
    	ItemDef.toNote();
     }
    if(i == 577) {
    	ItemDef.name = "Blue wizard robe top";
    }
    if(i == 4069) {
    	ItemDef.name = "Red decorative body";
    }
	    if(i == 4070) {
    	ItemDef.name = "Red decorative legs";
    }
		    if(i == 11893) {
    	ItemDef.name = "Red decorative skirt";
    }
		    if(i == 4504) {
		    	ItemDef.name = "White decorative body";
		    }
			    if(i == 4505) {
		    	ItemDef.name = "White decorative legs";
		    }
				    if(i == 11894) {
		    	ItemDef.name = "White decorative skirt";
		    }
				    if(i == 4509) {
				    	ItemDef.name = "Gold decorative body";
				    }
					    if(i == 4510) {
				    	ItemDef.name = "Gold decorative legs";
				    }
						    if(i == 11895) {
				    	ItemDef.name = "Gold decorative skirt";
				    }
						    if(i == 4071) {
						    	ItemDef.name = "Red decorative helm";
						    }
							    if(i == 4072) {
						    	ItemDef.name = "Red decorative shield";
						    }
							    if(i == 4506) {
						    	ItemDef.name = "White decorative helm";
						    }
							    if(i == 4507) {
						    	ItemDef.name = "White decorative shield";
						    }
							    if(i == 4511) {
						    	ItemDef.name = "Gold decorative helm";
						    }
							    if(i == 4512) {
						    	ItemDef.name = "Gold decorative shield";
						    }
							    if(i == 11898) {
							    	ItemDef.name = "Decorative magic hat";
							    }
								    if(i == 11896) {
							    	ItemDef.name = "Decorative magic robe top";
							    }
								    if(i == 11897) {
							    	ItemDef.name = "Decorative magic robe legs";
							    }
									    if(i == 11899) {
							    	ItemDef.name = "Decorative range top";
							    }
										    if(i == 11900) {
							    	ItemDef.name = "Decorative range legs";
							    }
	return ItemDef;
    }
    public static ItemCacheDefinition copy(ItemCacheDefinition itemDef, int newId, int copyingItemId, String newName, String...actions) {
        ItemCacheDefinition copyItemDef = forID(copyingItemId);
        itemDef.id = newId;
        itemDef.name = newName;
        itemDef.description = copyItemDef.description;
        itemDef.modifiedModelColors = copyItemDef.modifiedModelColors;
        itemDef.originalModelColors = copyItemDef.originalModelColors;
        itemDef.modelId = copyItemDef.modelId;
        itemDef.primaryMaleModel = copyItemDef.primaryMaleModel;
        itemDef.primaryFemaleModel = copyItemDef.primaryFemaleModel;
        itemDef.spriteScale = copyItemDef.spriteScale;
        itemDef.spritePitch = copyItemDef.spritePitch;
        itemDef.spriteCameraRoll = copyItemDef.spriteCameraRoll;
        itemDef.spriteTranslateX = copyItemDef.spriteTranslateX;
        itemDef.spriteTranslateY = copyItemDef.spriteTranslateY;
        itemDef.itemActions = copyItemDef.itemActions;
        itemDef.itemActions = new String[5];
        if (actions != null) {
            for (int index = 0; index < actions.length; index++) {
                itemDef.itemActions[index] = actions[index];
            }
        }
        return itemDef;
    }
    private static void customItems(int itemId) {
        ItemCacheDefinition itemDef = forID(itemId);
        ItemDefinition_Sub1.itemDef(itemId, itemDef);
        ItemDefinition_Sub2.itemDef(itemId, itemDef);
        switch (itemId) {
            case 30000:
                copy(itemDef, 30_000, 11738, "Resource box(small)", "Open");
                break;
            case 30001:
                 copy(itemDef, 30_001, 11738, "Resource box(medium)", "Open");
                 break;
            case 30002:
                 copy(itemDef, 30_002, 11738, "Resource box(large)", "Open");
                 break;
            case 22375:
                 copy(itemDef, 22375, 22374, "Mossy key");
                 break;

            case 33056:

                itemDef.id = 33056;
                itemDef.modelId = 65270;
                itemDef.name = "Completionist cape";
                itemDef.description = "A cape worn by those who've overachieved.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 65297;
                itemDef.primaryFemaleModel = 65316;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Teleports";
                itemDef.itemActions[3] = "Features";
                itemDef.itemActions[4] = "Drop";
break;
            case 33057:

                itemDef.id = 33057;
                itemDef.modelId = 65273;
                itemDef.name = "Completionist hood";
                itemDef.description = "A hood worn by those who've over achieved.";

                itemDef.spriteScale = 760;
                itemDef.spritePitch = 11;
                itemDef.spriteCameraRoll = 0;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 0;

                itemDef.primaryMaleModel = 65292;
                itemDef.primaryFemaleModel = 65310;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
break;
            case 8817:

                itemDef.id = 8817;
                itemDef.modelId = 52300;
                itemDef.name = "scythe of Osiris";
                itemDef.description = "A scythe for the finest.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 52300;
                itemDef.primaryFemaleModel = 52300;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 29499:

                itemDef.id = 29499;
                itemDef.modelId = 52401;
                itemDef.name = "Lava Scythe";
                itemDef.description = "A scythe from lava.";
                itemDef.spriteScale = 2105;
                itemDef.spritePitch = 327;
                itemDef.spriteCameraRoll = 23;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 1;
                itemDef.spriteTranslateY = 17;

                itemDef.primaryMaleModel = 52400;
                itemDef.primaryFemaleModel = 52400;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 29047:

                itemDef.id = 29047;
                itemDef.modelId = 55742;
                itemDef.name = "Ice Scythe";
                itemDef.description = "A scythe from Ice.";
                itemDef.spriteScale = 2105;
                itemDef.spritePitch = 327;
                itemDef.spriteCameraRoll = 23;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 1;
                itemDef.spriteTranslateY = 17;

                itemDef.primaryMaleModel = 52906;
                itemDef.primaryFemaleModel = 52906;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 29180:

                itemDef.id = 29180;
                itemDef.modelId = 50000;
                itemDef.name = "Osiris Chainmace";
                itemDef.description = "A Mace From Hell.";
                itemDef.spriteScale = 2105;
                itemDef.spritePitch = 23;
                itemDef.spriteCameraRoll = 327;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 1;
                itemDef.spriteTranslateY = 17;

                itemDef.primaryMaleModel = 50000;
                itemDef.primaryFemaleModel = 50000;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 8813:

                itemDef.id = 8813;
                itemDef.modelId = 65132;
                itemDef.name = "Lava Torva Platebody";
                itemDef.description = "A body from lava.";
                itemDef.spriteScale = 1780;
                itemDef.spritePitch = 2042;
                itemDef.spriteCameraRoll = 473;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = -1;
                itemDef.spriteTranslateY = -1;

                itemDef.primaryMaleModel = 65133;
                itemDef.secondaryMaleModel = 65129;
                itemDef.primaryFemaleModel = 65133;
                itemDef.secondaryFemaleModel = 65129;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 8812:

                itemDef.id = 8812;
                itemDef.modelId = 65134;
                itemDef.name = "Lava Torva Platelegs";
                itemDef.description = "Platelegs made from lava.";
                itemDef.spriteScale = 1780;
                itemDef.spritePitch = 2045;
                itemDef.spriteCameraRoll = 474;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = -1;
                itemDef.spriteTranslateY = 8;

                itemDef.primaryMaleModel = 65135;
                itemDef.primaryFemaleModel = 65135;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 8814:

                itemDef.id = 8814;
                itemDef.modelId = 65130;
                itemDef.name = "Lava Torva Full Helm";
                itemDef.description = "A Helm from lava.";
                itemDef.spriteScale = 1780;
                itemDef.spritePitch = 1867;
                itemDef.spriteCameraRoll = 85;
                itemDef.spriteCameraYaw = 1;
                //itemDef.spriteTranslateX = -1;
                itemDef.spriteTranslateY = -2;
                itemDef.itemActions = new String[5];
                itemDef.primaryMaleModel = 65131;
                itemDef.primaryFemaleModel = 65131;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wield";
                itemDef.itemActions[4] = "Drop";
break;
            case 4371:

                itemDef.id = 4371;
                itemDef.modelId = 56670;
                itemDef.name = "Admin Cape";
                itemDef.description = "A Cape for the finest.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 56670;
                itemDef.primaryFemaleModel = 56670;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Teleports";
                itemDef.itemActions[3] = "Features";
                itemDef.itemActions[4] = "Drop";
break;
            case 26230:

                itemDef.id = 26230;
                itemDef.modelId = 50500;
                itemDef.name = "burning skeleteon boots";
                itemDef.description = "spooky skeleton.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 50500;
                itemDef.primaryFemaleModel = 50500;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Drop";
break;
            case 26231:

                itemDef.id = 26231;
                itemDef.modelId = 50501;
                itemDef.name = "Burning skeleteon Gloves";
                itemDef.description = "spooky skeleton.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 50501;
                itemDef.primaryFemaleModel = 50501;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Drop";
break;
            case 26232:

                itemDef.id = 26232;
                itemDef.modelId = 50502;
                itemDef.name = "Burning skeleteon mask";
                itemDef.description = "spooky skeleton.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 50502;
                itemDef.primaryFemaleModel = 50502;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Drop";
break;
            case 26233:

                itemDef.id = 26233;
                itemDef.modelId = 50503;
                itemDef.name = "Burning skeleteon leggings";
                itemDef.description = "spooky skeleton.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 50503;
                itemDef.primaryFemaleModel = 50503;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Drop";
break;
            case 26234:

                itemDef.id = 26234;
                itemDef.modelId = 50505;
                itemDef.name = "Burning skeleteon Skirt";
                itemDef.description = "spooky skeleton.";

                itemDef.spriteScale = 1385;
                itemDef.spritePitch = 279;
                itemDef.spriteCameraRoll = 948;
                itemDef.spriteCameraYaw = 0;
                itemDef.spriteTranslateX = 0;
                itemDef.spriteTranslateY = 24;

                itemDef.primaryMaleModel = 50505;
                itemDef.secondaryMaleModel = 50504;
                itemDef.primaryFemaleModel = 50505;
                itemDef.secondaryFemaleModel = 50504;
                //itemDef.groundActions = new String[5];
                //itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[2] = "Drop";
break;
        }

    }
	public static void dumpItemDefs() {
		final int[] wikiBonuses = new int[18];
		int bonus = 0;
		int amount = 0;
		int value = 0;
		int slot = -1;
		// Testing Variables just so i know format is correct
		String fullmask = "false";
		// boolean stackable1 = false;
		String stackable = "false";
		// boolean noteable1 = false;
		String noteable = "true";
		// boolean tradeable1 = false;
		String tradeable = "true";
		// boolean wearable1 = false;
		String wearable = "true";
		String showBeard = "true";
		String members = "true";
		boolean twoHanded = false;
		System.out.println("Starting to dump item definitions...");
		for (int i = 0; i < totalItems; i++) {
			ItemCacheDefinition item = ItemCacheDefinition.forID(i);
			try {
				try {
					try {
						final URL url = new URL("http://oldschool.runescape.wiki/w/" + item.name.replaceAll(" ", "_"));
						URLConnection con = url.openConnection();
						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String line;
						writer = new BufferedWriter(new FileWriter("itemDefs.json", true));
						while ((line = in.readLine()) != null) {
							try {
								if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
									line = line.replace("</td>", "").replace("%", "").replace("?", "")
											.replace("\"\"", "")
											.replace("<td style=\"text-align: center; width: 35px;\">", "");
									wikiBonuses[bonus] = Integer.parseInt(line);
									bonus++;
								} else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
									line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "")
											.replace("<td style=\"text-align: center; width: 30px;\">", "");
									wikiBonuses[bonus] = Integer.parseInt(line);
									bonus++;
								}
								if (line.contains("<div id=\"GEPCalcResult\" style=\"display:inline;\">")) {
									line = line.replace("</div>", "").replace("%", "").replace("?", "").replace("%", "")
											.replace("<div id=\"GEPCalcResult\" style=\"display:inline;\">", "");
									value = Integer.parseInt(line);
								}

							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
							in.close();
							// fw.write("ItemID: "+itemDefinition.id+" - "+itemDefinition.name);
							// fw.write(System.getProperty("line.separator"));
							// writer.write("[\n");
							writer.write("  {\n\t\"id\": " + item.id + ",\n\t\"name\": \"" + item.name
									+ "\",\n\t\"desc\": \"" + item.name + "\",\n\t\"value\": "
									+ value + ",\n\t\"dropValue\": " + value + ",\n\t\"bonus\": [\n\t  "
									+ wikiBonuses[0] + ",\n\t  " + wikiBonuses[1] + ",\n\t  " + wikiBonuses[2]
									+ ",\n\t  " + wikiBonuses[3] + ",\n\t  " + wikiBonuses[4] + ",\n\t  "
									+ wikiBonuses[5] + ",\n\t  " + wikiBonuses[6] + ",\n\t  " + wikiBonuses[7]
									+ ",\n\t  " + wikiBonuses[8] + ",\n\t  " + wikiBonuses[9] + ",\n\t  "
									+ wikiBonuses[10] + ",\n\t  " + wikiBonuses[13] + ",\n\t],\n\t\"slot\": " + slot
									+ ",\n\t\"fullmask\": " + fullmask + ",\n\t\"stackable\": " + stackable
									+ ",\n\t\"noteable\": " + noteable + ",\n\t\"tradeable\": " + tradeable
									+ ",\n\t\"wearable\": " + wearable + ",\n\t\"showBeard\": " + showBeard
									+ ",\n\t\"members\": " + members + ",\n\t\"twoHanded\": " + twoHanded
									+ ",\n\t\"requirements\": [\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t]\n  },\n");
							/*
							 * writer.write("item = " + i + "	" + item.name.replace(" ", "_") + "	" +
							 * item.description.replace(" ", "_") + "	" + item.value + "	" + item.value +
							 * "	" + item.value + "	" + wikiBonuses[0] + "	" + wikiBonuses[1] + "	" +
							 * wikiBonuses[2] + "	" + wikiBonuses[3] + "	" + wikiBonuses[4] + "	" +
							 * wikiBonuses[5] + "	" + wikiBonuses[6] + "	" + wikiBonuses[7] + "	" +
							 * wikiBonuses[8] + "	" + wikiBonuses[9] + "	" + wikiBonuses[10] + "	" +
							 * wikiBonuses[13]);
							 */
							amount++;
							wikiBonuses[0] = wikiBonuses[1] = wikiBonuses[2] = wikiBonuses[3] = wikiBonuses[4] = wikiBonuses[5] = wikiBonuses[6] = wikiBonuses[7] = wikiBonuses[8] = wikiBonuses[9] = wikiBonuses[10] = wikiBonuses[11] = wikiBonuses[13] = 0;
							writer.newLine();
							writer.close();
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Done dumping " + amount + " item definitions!");
		}
	}
    public static void dumpBonuses() {
        int[] bonuses = new int[14];
        int bonus = 0;
        int amount = 0;
        for (int i = 25000; i < 30000; i++) {
            ItemCacheDefinition item = ItemCacheDefinition.forID(i);
            URL url;
            try {
                try {
                    try {
                        url = new URL("https://oldschool.runescape.wiki/w/" + item.name.replaceAll(" ", "_"));
                        URLConnection con = url.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String line;
                        BufferedWriter writer = new BufferedWriter(new FileWriter("item.cfg", true));
                        while ((line = in.readLine()) != null) {
                            try {
                                if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "")
                                            .replace("\"\"", "")
                                            .replace("<td style=\"text-align: center; width: 35px;\">", "");
                                    bonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                } else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "")
                                            .replace("<td style=\"text-align: center; width: 30px;\">", "");
                                    bonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                }
                            } catch (NumberFormatException e) {

                            }
                            if (bonus >= 13)
                                bonus = 0;
                            // in.close();
                        }
                        in.close();
                        writer.write("item	=	" + i + "	" + item.name.replace(" ", "_") + "	"
                                + item.description.replace(" ", "_") + "	" + item.value + "	" + item.value + "	"
                                + item.value + "	" + bonuses[0] + "	" + bonuses[1] + "	" + bonuses[2] + "	"
                                + bonuses[3] + "	" + bonuses[4] + "	" + bonuses[5] + "	" + bonuses[6] + "	"
                                + bonuses[7] + "	" + bonuses[8] + "	" + bonuses[9] + "	" + bonuses[10] + "	"
                                + bonuses[13]);
                        bonuses[0] = bonuses[1] = bonuses[2] = bonuses[3] = bonuses[4] = bonuses[5] = bonuses[6] = bonuses[7] = bonuses[8] = bonuses[9] = bonuses[10] = bonuses[13] = 0;
                        writer.newLine();
                        amount++;
                        writer.close();
                    } catch (NullPointerException e) {

                    }
                } catch (FileNotFoundException e) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done dumping " + amount + " item bonuses!");
    }

    /**
	 * Unpacks the Item configurations.
	 */
    public static final void unpackConfig() {
    	try {
        npcData = new Stream(FileUtils.readFileToByteArray(new File(Server.getDataDirectory() + "/data/obj.dat")));
        Stream indexStream = new Stream(FileUtils.readFileToByteArray(new File(Server.getDataDirectory() + "/data/obj.idx")));
        totalItems = indexStream.readUnsignedWord();
        streamIndices = new int[totalItems + 30000];
        int i = 2;
        for(int j = 0; j < totalItems; j++)
        {
            streamIndices[j] = i;
            i += indexStream.readUnsignedWord();
        }

        cache = new ItemCacheDefinition[10];
        for(int k = 0; k < 10; k++) {
            cache[k] = new ItemCacheDefinition();
        }
        //dumpItemDefs();
            //dumpBonuses();
        System.out.println("Successfully loaded: " + totalItems + " Item Cache definitions.");
    	} catch (Exception e) {
    		System.err.println("An error has occurred whilst loading Item definitions!");
    		e.printStackTrace();
    	}
    }

	public int placeholderId;
	public int placeholderTemplateId;
    
    private void decode(Stream buffer) {
        while(true){
            int opcode = buffer.readUnsignedByte();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                modelId = buffer.readUnsignedWord();
            } else if (opcode == 2) {
                name = buffer.readString();
            } else if (opcode == 4) {
                spriteScale = buffer.readUnsignedWord();
            } else if (opcode == 5) {
                spritePitch = buffer.readUnsignedWord();
            } else if (opcode == 6) {
                spriteCameraRoll = buffer.readUnsignedWord();
            } else if (opcode == 7) {
                spriteTranslateX = buffer.readUnsignedWord();
                if (spriteTranslateX > 32767) {
                    spriteTranslateX -= 0x10000;
                }
            } else if (opcode == 8) {
                spriteTranslateY = buffer.readUnsignedWord();
                if (spriteTranslateY > 32767) {
                    spriteTranslateY -= 0x10000;
                }
            } else if (opcode == 11) {
                stackable = true;
            } else if (opcode == 12) {
                value = buffer.readInteger();
            } else if (opcode == 16) {
                membersObject = true;
            } else if (opcode == 23) {
                primaryMaleModel = buffer.readUnsignedWord();
                maleTranslation = buffer.readSignedByte();
            } else if (opcode == 24) {
                secondaryMaleModel = buffer.readUnsignedWord();
            } else if (opcode == 25) {
                primaryFemaleModel = buffer.readUnsignedWord();
                femaleTranslation = buffer.readSignedByte();
            } else if (opcode == 26) {
                secondaryFemaleModel = buffer.readUnsignedWord();
            } else if (opcode >= 30 && opcode < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[opcode - 30] = buffer.readString();
                if (groundActions[opcode - 30].equalsIgnoreCase("Hidden")) {
                    groundActions[opcode - 30] = null;
                }
            } else if (opcode >= 35 && opcode < 40) {
                if (itemActions == null) {
                    itemActions = new String[5];
                }

                itemActions[opcode - 35] = buffer.readString();
            } else if (opcode == 40) {
                int len = buffer.readUnsignedByte();
                originalModelColors = new int[len];
                modifiedModelColors = new int[len];
                for (int i = 0; i < len; i++) {
                    originalModelColors[i] = buffer.readUnsignedWord();
                    modifiedModelColors[i] = buffer.readUnsignedWord();
                }
            } else if(opcode == 41) {
                int var3 = buffer.readUnsignedByte();
                this.originalTextureColors = new short[var3];
                this.modifiedTextureColors = new short[var3];

                for(int var4 = 0; var4 < var3; ++var4) {
                   this.originalTextureColors[var4] = (short)buffer.readUnsignedWord();
                   this.modifiedTextureColors[var4] = (short)buffer.readUnsignedWord();
                }
            } else if (opcode == 42) {
                shiftClickIndex = buffer.readUnsignedByte();
            } else if (opcode == 65) {
                searchable = true;
            }  else if (opcode == 78) {
                tertiaryMaleEquipmentModel = buffer.readUnsignedWord();
            } else if (opcode == 79) {
                tertiaryFemaleEquipmentModel = buffer.readUnsignedWord();
            } else if (opcode == 90) {
                primaryMaleHeadPiece = buffer.readUnsignedWord();
            } else if (opcode == 91) {
                primaryFemaleHeadPiece = buffer.readUnsignedWord();
            } else if (opcode == 92) {
                secondaryMaleHeadPiece = buffer.readUnsignedWord();
            } else if (opcode == 93) {
                secondaryFemaleHeadPiece = buffer.readUnsignedWord();
            } else if (opcode == 94){
                    buffer.readUnsignedWord();
            } else if (opcode == 95) {
                spriteCameraYaw = buffer.readUnsignedWord();
            } else if (opcode == 97) {
                certID = buffer.readUnsignedWord();
            } else if (opcode == 98) {
                certTemplateID = buffer.readUnsignedWord();
            } else if (opcode >= 100 && opcode < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[opcode - 100] = buffer.readUnsignedWord();
                stackAmounts[opcode - 100] = buffer.readUnsignedWord();
            } else if (opcode == 110) {
                groundScaleX = buffer.readUnsignedWord();
            } else if (opcode == 111) {
                groundScaleY = buffer.readUnsignedWord();
            } else if (opcode == 112) {
                groundScaleZ = buffer.readUnsignedWord();
            } else if (opcode == 113) {
                ambience = buffer.readSignedByte();
            } else if (opcode == 114) {
                diffusion = buffer.readSignedByte();
            } else if (opcode == 115) 
                team = buffer.readUnsignedByte();
    			else if (opcode == 139)
    				unnotedId = buffer.readUnsignedWord();
    			else if (opcode == 140)
    				notedId = buffer.readUnsignedWord();
    			else if (opcode == 148)
    				placeholderId = buffer.readUnsignedWord();
                else if (opcode == 149) {
                    placeholderTemplateId = buffer.readUnsignedWord();
            } else if (opcode == 150) {
                opcode150 = buffer.readString();
            }
        }
    }
    /**
     * this handles the noting of thems so the server knows its that noted item.
     */
    public void toNote() {
        ItemCacheDefinition noted = forID(this.certTemplateID);
        this.modelId = noted.modelId;
        this.spriteScale = noted.spriteScale;
        this.spritePitch = noted.spritePitch;
        this.spriteCameraRoll = noted.spriteCameraRoll;
        this.spriteCameraYaw = noted.spriteCameraYaw;
        this.spriteTranslateX = noted.spriteTranslateX;
        this.spriteTranslateY = noted.spriteTranslateY;
        this.modifiedModelColors = noted.modifiedModelColors;
        this.originalModelColors = noted.originalModelColors;
        ItemCacheDefinition unnoted = forID(this.certID);
        
        if (unnoted == null || unnoted.name == null) {
            return;
        }
        this.name = unnoted.name;
        this.membersObject = unnoted.membersObject;
        this.value = unnoted.value;
        String s = "a";
        char c = unnoted.name.charAt(0);
  		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
  			s = "an";
  		}
        this.description = ("Swap this note at any bank for " + s + " " + unnoted.name + ".");
        this.stackable = true;
     }

    ItemCacheDefinition()
    {
        modelId = 0;
        name = "null";
        description = "";
        modifiedModelColors = null;
        originalModelColors = null;
        this.modifiedTextureColors = null;
        this.originalTextureColors = null;
        spriteScale = 2000;
        spritePitch = 0;
        spriteCameraRoll = 0;
        spriteCameraYaw = 0;
        spriteTranslateX = 0;
        spriteTranslateY = 0;
        stackable = false;
        value = 1;
        membersObject = false;
        groundActions = new String[]{null, null, "Take", null, null};;
        itemActions = new String[]{null, null, null, null, "Drop"};
        primaryMaleModel = -1;
        secondaryMaleModel = -1;
        maleTranslation = 0;
        primaryFemaleModel = -1;
        secondaryFemaleModel = -1;
        femaleTranslation = 0;
        tertiaryMaleEquipmentModel = -1;
        tertiaryFemaleEquipmentModel = -1;
        primaryMaleHeadPiece = -1;
        secondaryMaleHeadPiece = -1;
        primaryFemaleHeadPiece = -1;
        secondaryFemaleHeadPiece = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        groundScaleX = 128;
        groundScaleY = 128;
        groundScaleZ = 128;
        ambience = 0;
        diffusion = 0;
        team = 0;
		notedId = -1;
		unnotedId = -1;
		placeholderId = -1;
		placeholderTemplateId = -1;
    }

    private static ItemCacheDefinition[] definitions = new ItemCacheDefinition[ITEM_TOTAL];

	public static ItemCacheDefinition[] getDefinitions() {
		return definitions;
	}
	
	public boolean isUntradable() {
		return untradable;
	}


    
    /**
     * Gets the items noted id
     * @return
     */
    public int isNoted() {
    	return notedId;
    }
    
    /**
     * Gets the item's value.
     * @return
     */
    public int getvalue() {
    	return value;
    }
    
    public boolean isStackable(){
    	return stackable;
    }
    /**
     * Gets the size of the NPC.
     * @return	size	the size of the NPC
     */
    public int getUnnotedId() {
    	return unnotedId;
    }
    
    /**
     * Gets the name of the NPC.
     * @return	name	the name of the NPC.
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Gets the examine string for the NPC.
     * @return	examine	the examine string
     */
    public String getExamine() {
    	return description;
    }
    
    /**
     * Gets the Actions of the Item
     * @return     */
    public String[] getitemActions() {
        return itemActions;
    }
	/**
	 * Untradable
	 */
	private boolean untradable;
    private static int cacheIndex;
    private static Stream npcData;
    public static int totalItems;
    public String name;
    public String actions[];
    public byte boundDim;
    private static int streamIndices[];
    public long type;
    private byte femaleTranslation;
    private byte maleTranslation;
    public int id;// anInt157
    public int team;
    public int value;// anInt155
    public int certID;
    public int modelId;// dropModel
    public int primaryMaleHeadPiece;
    public int primaryFemaleModel;// femWieldModel
    public int spriteCameraYaw;// modelPositionUp
    public int secondaryFemaleModel;// femArmModel
    public int primaryFemaleHeadPiece;
    public int secondaryMaleModel;// maleArmModel
    public int primaryMaleModel;// maleWieldModel
    public int spriteScale;
    public int spriteTranslateX;
    public int spriteTranslateY;//
    public int certTemplateID;
    public int unnotedId = -1;
    public int notedId = -1;
    public int spriteCameraRoll;// modelRotateRight
    public int spritePitch;// modelRotateUp
    public int[] stackIDs;// modelStack
    public int[] stackAmounts;// itemAmount
    public int[] modifiedModelColors;// newModelColor
    public int[] originalModelColors;
    public short[] modifiedTexture;
    public short[] originalTexture;
    private int ambience;
    private int tertiaryFemaleEquipmentModel;
    private int secondaryMaleHeadPiece;
    private int diffusion;
    private int tertiaryMaleEquipmentModel;
    private int groundScaleZ;
    private int groundScaleY;
    private int groundScaleX;
    //@Export("textureToReplace")
    public short[] originalTextureColors;
   // @ObfuscatedName("v")
   // @Export("textToReplaceWith")
    public short[] modifiedTextureColors;
    private int secondaryFemaleHeadPiece;
    private int shiftClickIndex = -2;
    private boolean stockMarket;
    public boolean searchable;
    private static int[] offsets;
    public boolean stackable;// itemStackable
    public boolean membersObject;// aBoolean161
	private static BufferedWriter writer;
    public static boolean isMembers = true;
    public String description;// itemExamine
    public String itemActions[];// itemMenuOption
    public String groundActions[];
    private static ItemCacheDefinition cache[];

    public int getId() {
        return id;
    }
}