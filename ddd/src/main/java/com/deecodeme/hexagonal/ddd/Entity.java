package com.deecodeme.hexagonal.ddd;

public class Entity {
    private final String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
