package io.shadowrealm.model.world.objects;

import io.shadowrealm.content.instances.InstancedArea;
import io.shadowrealm.model.collisionmap.RegionProvider;
import io.shadowrealm.model.collisionmap.WorldObject;
import io.shadowrealm.model.entity.player.Position;

/**
 * A global object is a visual model that is viewed by all players within a region. This class represents the identification value, x and y position, as well as the height of the
 * object.
 * 
 * A key factor is the ticks remaining. The ticksRemaining variable represents how many game ticks this object will remain visible for. If the value is negative the object will
 * remain indefinitly. On the flip side, if the value is positive then every tick the total remaining will reduce by one until it hits zero.
 * 
 * @author Jason MacKeigan
 * @date Dec 17, 2014, 6:18:20 PM
 */
public class GlobalObject {

	private int id;

	private final int x;

	private final int y;

	private final int height;

	private int face;

	private int ticksRemaining;

	private int restoreId;

	private int type;
	private int picked;
	private InstancedArea instance;

	public GlobalObject(int id, Position position, int face, int type) {
		this.id = id;
		this.x = position.getX();
		this.y = position.getY();
		this.height = position.getHeight();
		this.type = type;
		this.face = face;
	}

	public GlobalObject(int id, int x, int y, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.height = height;
		this.type = 10;
	}

	public GlobalObject(int id, int x, int y, int height, int face) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.height = height;
		this.face = face;
	}
	public GlobalObject(int id, int x, int y, int face, int lol, int lol2, int lol3, int lol4, int lol5, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.face = face;
		this.height = height;
	}
	public GlobalObject(int id, int x, int y, int height, int face, int type) {
		this(id, x, y, height, face);
		this.type = type;
	}

	public GlobalObject(int id, int x, int y, int height, int face, int type, int ticksRemaining) {
		this(id, x, y, height, face, type);
		this.ticksRemaining = ticksRemaining;
	}
	public GlobalObject(int id, Position p) {
		this(id, p.getX(), p.getY(), p.getHeight(), 0, 10, -1, 0);
	}
	public GlobalObject(int id, int x, int y, int height, int face, int type, int ticksRemaining, int restoreId) {
		this(id, x, y, height, face, type, ticksRemaining);
		this.restoreId = restoreId;
	}

	public GlobalObject withPosition(Position position) {
		return new GlobalObject(id, position.getX(), position.getY(), position.getHeight(), face, type, ticksRemaining, restoreId);
	}

	public GlobalObject withId(int objectId) {
		return new GlobalObject(objectId, x, y, height, face, type, ticksRemaining, restoreId);
	}
	public int getPickAmount() {
		return this.picked;
	}
	public void setPickAmount(int amount) {
		this.picked = amount;
	}

	public void incrementPickAmount() {
		this.picked++;
	}
	public boolean samePositionAndType(WorldObject object) {
		return object.x == x && object.y == y && object.height == height && object.type == type;
	}

	public GlobalObject withHeight(int heightLevel) {
		return new GlobalObject(id, x, y, heightLevel, face, type, ticksRemaining, restoreId);
	}

	public void removeTick() {
		this.ticksRemaining--;
	}

	public int getObjectId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getFace() {
		return face;
	}

	public int getTicksRemaining() {
		return ticksRemaining;
	}

	public int getRestoreId() {
		return restoreId;
	}

	public Position getPosition() {
		return new Position(x, y, height);
	}

	public int getType() {
		return type;
	}

	public InstancedArea getInstance() {
		return instance;
	}

	public GlobalObject setInstance(InstancedArea instance) {
		this.instance = instance;
		return this;
	}
	
	public RegionProvider getRegionProvider() {
		if (instance != null) {
			return instance;
		} else {
			return RegionProvider.getGlobal();
		}
	}

	@Override
	public String toString() {
		return "GlobalObject{" +
				"id=" + id +
				", x=" + x +
				", y=" + y +
				", height=" + height +
				", face=" + face +
				", ticksRemaining=" + ticksRemaining +
				", restoreId=" + restoreId +
				", type=" + type +
				", instance=" + instance +
				'}';
	}
}
