package io.exilius.content.skills.construction.rooms;

import io.exilius.content.skills.construction.Room;
import io.exilius.content.skills.construction.RoomObject;

import java.util.ArrayList;
import java.util.List;

public class SkillHall extends Room {

	public SkillHall() {
		super(1864, 5744);
		setName("Skill hall");
		List<RoomObject> objects = new ArrayList<RoomObject>();
		objects.add(new RoomObject(15386, 0, 6, 0, 10, "Rune case space"));
		objects.add(new RoomObject(15385, 2, 3, 0, 10, "Armour space"));
		objects.add(new RoomObject(15384, 5, 3, 0, 10, "Armour space"));
		objects.add(new RoomObject(15382, 6, 7, 0, 10, "Head trophy space"));
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
