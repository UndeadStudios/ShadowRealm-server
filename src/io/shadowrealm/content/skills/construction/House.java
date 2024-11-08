package io.shadowrealm.content.skills.construction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import io.shadowrealm.Server;
import io.shadowrealm.content.skills.construction.rooms.Default;
import io.shadowrealm.content.skills.construction.rooms.Garden;
import io.shadowrealm.content.skills.construction.rooms.Parlour;
import io.shadowrealm.content.skills.construction.rooms.Roof;
import io.shadowrealm.model.collisionmap.Region;
import io.shadowrealm.model.collisionmap.RegionProvider;
import io.shadowrealm.model.cycleevent.CycleEvent;
import io.shadowrealm.model.cycleevent.CycleEventContainer;
import io.shadowrealm.model.cycleevent.CycleEventHandler;
import io.shadowrealm.model.entity.player.Player;
import io.shadowrealm.util.Misc;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class House {

	@Expose public Room[][][] rooms = new Room[13][13][4];
	
	private int height;

	private boolean buildMode = false, open = false;
	
	public Player owner;
	
	private List<Player> guests = new ArrayList<Player>();

	public House(Player player) {
		for (int x = 4; x < 9; x++) {
			for (int y = 4; y < 9; y++) {
				Room room = new Default();
				room.setPosition(new int[] {x, y});
				rooms[x][y][0] = room;
			}
		}
		Room room;
		room = new Garden();
		room.setRotation(0);
		room.setPosition(new int[] {6, 6});
		rooms[6][6][0] = room;
		room = new Parlour();
		room.setRotation(3);
		room.setPosition(new int[] {5, 6});
		rooms[5][6][0] = room;
		this.owner = player;
		this.height = owner.getIndex() * 4;
		owner.setHouse(this);
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public boolean isBuildMode() {
		return buildMode;
	}
	
	public void setBuildMode(boolean buildMode) {
		this.buildMode = buildMode;
	}
	
	public boolean isLocked() {
		return !open;
	}
	
	public void setLocked(boolean locked) {
		this.open = !locked;
	}

	public void EnterWithLoad(final Player c){
		if (c.getHouse() == null) {
			load(c);
			return;
		}
		enter(c);
	}
	public void enter(final Player client) {
		if (isBuildMode() && !getOwner().equals(client)) {
			client.sendMessage(Misc.formatPlayerName(getOwner().getLoginName()) + " is currently in build mode.");
			return;
		}
		
		if (!guests.contains(getOwner()) && !getOwner().equals(client)) {
			client.sendMessage(Misc.formatPlayerName(getOwner().getLoginName()) + " doesn't appear to be home right now.");
			return;
		}
		
		if (isLocked() && !getOwner().equals(client)) {
			client.sendMessage("The owner has their house locked.");
			return;
		}
		
		guests.add(client);
		
		//client.getPA().sendFrame126("Entering house...", 12285);
		CycleEventHandler.getSingleton().addEvent(client, new CycleEvent() {
			int time = 0;
			@Override
			public void execute(CycleEventContainer container) {
				if(time == 1) {
					client.getPA().showInterface(24568);
					client.getRegionProvider().LoadRegion(owner);
					client.getPA().movePlayer(32, 32, height);
				}
				if(time == 3) {
					client.getRegionProvider().LoadRegion(owner);
					client.getPA().movePlayer(32, 32, height);
				}
				if (client == null || time >= 5) {
					client.getPA().movePlayer(34, 35, height);
					client.getPA().closeAllWindows();
						container.stop();
						return;
				}
				if (time >= 0) {
					time++;
				}
			}
			public void stop() {
				client.getRegionProvider().LoadRegion(owner);
				client.getPA().movePlayer(32, 32, height);

				//client.getPA().removeAllWindows();
			}
			
		}, 1);
		// loadObjects(host, visiter);
	}
	
	public void buildRoom(final Player client) {
		if(client.inConstruction()) {
			replaceRoom(client.toReplace, client.replaceWith);

			final int x = client.absX, y = client.absY;

			client.toReplace = client.replaceWith = null;
			//client.getPA().sendFrame126("Building room...", 12285);
			//client.getPA().showInterface(31992);
			client.getPA().movePlayer(x, y, height);
			CycleEventHandler.getSingleton().addEvent(client, new CycleEvent() {
				int time = 0;

				@Override
				public void execute(CycleEventContainer container) {
					if (time == 1) {
						client.getPA().showInterface(24568);
						client.getRegionProvider().LoadRegion(owner);
						client.getPA().movePlayer(x, y, height);
					}
					if (time == 3) {
						client.getRegionProvider().LoadRegion(owner);
						client.getPA().movePlayer(x, y, height);
					}
					if (client == null || time >= 5) {
						client.getPA().closeAllWindows();
						container.stop();
						return;
					}
					if (time >= 0) {
						time++;
					}
				}

				public void stop() {
					client.getRegionProvider().LoadRegion(client);
					client.getPA().movePlayer(x, y, height);

					//client.getPA().removeAllWindows();
				}

			}, 2);
		}
	}
	
	private void replaceRoom(Room oldRoom, Room newRoom) {
		oldRoom.delete(getOwner());
		newRoom.setPosition(oldRoom.getPosition());
		rooms[oldRoom.getPosition()[0]][oldRoom.getPosition()[1]][0] = newRoom;
	}
	
	public void leave(Player client) {
		guests.remove(client);
	}
	
	public void kickAllGuests() {
		List<Player> g = new ArrayList<Player>();
		
		for (Player client : guests) {
			g.add(client);
		}
		
		Iterator<Player> it1 = g.iterator();
		while (it1.hasNext()) {
			Player client = it1.next();
			if (client.isDisconnected() || client.getHouse() == null || !client.getHouse().equals(this) || client.equals(getOwner()))
				continue;
			
			client.getPA().movePlayer(3080, 3492, 0);
			leave(client);
		}
	}
	
	public void save() {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(Server.getSaveDirectory() + "houses/" + owner.getLoginName() + ".json", "UTF-8");
			writer.println(gson.toJson(this));
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static House load(Player client) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		try {
			House house = gson.fromJson(Misc.loadFile(Server.getSaveDirectory() + "houses/" + client.getLoginName() + ".json"), House.class);
			
			if (house == null) {
				return new House(client);
			}
			
			for (int z = 0; z < 4; z++) {
				for (int x = 0; x < 13; x++) {
					for (int y = 0; y < 13; y++) {
						Room room = house.rooms[x][y][z];
						if (room == null)
							continue;
						
						Room newRoom = (Room) Class.forName(room.getClassName()).newInstance();
						newRoom.setCustomObjects(room.getCustomObjects());
						newRoom.setRotation(room.getRotation());
						newRoom.setPosition(room.getPosition());
						house.guests = new ArrayList<Player>();
						house.rooms[x][y][z] = newRoom;
					}
				}
			}
			
			house.owner = client;
			house.height = client.getIndex() * 4;
			return house;
		} catch (Exception e) {
		}
		return new House(client);
	}
	
	public static boolean hasHouse(Player client) {
		File file = new File(Server.getSaveDirectory() + "houses/" + client.getLoginName() + ".json");
		return file.exists();
	}
	
	public Room getRoom(int[] coords) {
		if (coords.length == 3) {
			return getRoom(coords[0], coords[1], coords[2]);
		}
		return getRoom(coords[0], coords[1]);
	}
	
	public Room getRoom(int x, int y) {
		return getRoom(x, y, 0);
	}
	
	public Room getRoom(int x, int y, int z) {
		return rooms[x][y][z];
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addRoom(Player player, int id, int x, int y, Room room, int slotX, int slotY) {
		player.getHouse().rooms[slotX + 2][slotY + 2][0] = room;
		enter(player);
	}
}