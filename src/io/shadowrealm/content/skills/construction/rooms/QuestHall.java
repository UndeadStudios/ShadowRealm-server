package io.shadowrealm.content.skills.construction.rooms;

import io.shadowrealm.content.skills.construction.Room;
import io.shadowrealm.content.skills.construction.RoomObject;

import java.util.ArrayList;
import java.util.List;

public class QuestHall extends Room {

	public QuestHall() {
		super(1896, 5744);
		setName("Quest hall");
		List<RoomObject> objects = new ArrayList<RoomObject>();
		objects.add(new RoomObject(15397, 0, 1, 0, 10, "Bookcase space"));
		objects.add(new RoomObject(15394, 0, 6, 0, 10, "Guild trophy space"));
		objects.add(new RoomObject(15392, 1, 7, 0, 10, "Portrait space"));
		objects.add(new RoomObject(15396, 7, 1, 0, 10, "Map space"));
		objects.add(new RoomObject(15395, 7, 6, 0, 10, "Sword space"));
		objects.add(new RoomObject(15393, 6, 7, 0, 10, "Landscape space"));
		objects.add(new RoomObject(15390, 3, 3, 0, 10, "Stair space"));
		objects.add(new RoomObject(15313, 0, 4, 0, 0, "Door hotspot"));
		objects.add(new RoomObject(15314, 0, 3, 0, 0, "Door hotspot"));
		objects.add(new RoomObject(15313, 7, 4, 2, 0, "Door hotspot"));
		objects.add(new RoomObject(15314, 7, 3, 2, 0, "Door hotspot"));
		objects.add(new RoomObject(15313, 4, 0, 3, 0, "Door hotspot"));
		objects.add(new RoomObject(15314, 3, 0, 3, 0, "Door hotspot"));
		setObjects(objects);
		List<RoomObject> globalReplace = new ArrayList<RoomObject>();
		globalReplace.add(new RoomObject(13099, 2, 0, 3, 0, "Window"));
		globalReplace.add(new RoomObject(13099, 5, 0, 3, 0, "Window"));
		globalReplace.add(new RoomObject(13099, 7, 2, 2, 0, "Window"));
		globalReplace.add(new RoomObject(13099, 7, 5, 2, 0, "Window"));
		globalReplace.add(new RoomObject(13099, 0, 2, 0, 0, "Window"));
		globalReplace.add(new RoomObject(13099, 0, 5, 0, 0, "Window"));
		globalReplace.add(new RoomObject(-1, 2, 0, 0, 5, "Curtain"));
		globalReplace.add(new RoomObject(-1, 5, 0, 0, 5, "Curtain"));
		globalReplace.add(new RoomObject(-1, 7, 2, 0, 5, "Curtain"));
		globalReplace.add(new RoomObject(-1, 7, 5, 0, 5, "Curtain"));
		globalReplace.add(new RoomObject(-1, 0, 2, 0, 5, "Curtain"));
		globalReplace.add(new RoomObject(-1, 0, 5, 0, 5, "Curtain"));
		this.setGlobalReplacementObjects(globalReplace);
	}
}
