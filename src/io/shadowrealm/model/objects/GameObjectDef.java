package io.shadowrealm.model.objects;


import io.shadowrealm.model.entity.player.Position;

public class GameObjectDef {
    
    private int id, type, face;
    private Position position;

    public GameObjectDef(int id, int type, int face, Position position) {
        this.id = id;
        this.type = type;
        this.face = face;
        this.position = position;
    }
    
    public int getId() {
        return id;
    }
    
    public int getType() {
        return type;
    }
    
    public int getFace() {
        return face;
    }

    public Position getPosition() {
        return position;
    }
}
