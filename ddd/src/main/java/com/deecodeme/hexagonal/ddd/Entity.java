package com.deecodeme.hexagonal.ddd;

import java.sql.Timestamp;

public abstract class Entity {
    private final String id;

    private final Timestamp createdAt;

    public Entity(String id) {
        this.id = id;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
