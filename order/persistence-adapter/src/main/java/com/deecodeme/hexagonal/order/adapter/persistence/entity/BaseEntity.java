package com.deecodeme.hexagonal.order.adapter.persistence.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
public abstract class BaseEntity {
    @MongoId
    private final String id;
    private final Instant createdAt;
    private Instant updatedAt;

    public BaseEntity(String id, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
