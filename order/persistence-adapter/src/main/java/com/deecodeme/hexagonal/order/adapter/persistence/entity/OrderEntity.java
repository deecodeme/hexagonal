package com.deecodeme.hexagonal.order.adapter.persistence.entity;

import com.deecodeme.hexagonal.order.domain.Order;
import com.deecodeme.hexagonal.order.domain.OrderFactory;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;
import java.util.function.Function;


@Entity
@Getter
public class OrderEntity extends BaseEntity {
    private final Order.OrderStatus status;
    private final String customerId;
    private final Map<String, Integer> itemQuantityMap;
    private final String description;
    private final String price;

    @Builder
    public OrderEntity(String id, Instant createdAt, Instant updatedAt, Order.OrderStatus status, String customerId,
                       Map<String, Integer> itemQuantityMap, String description, String price) {
        super(id, createdAt, updatedAt);
        this.status = status;
        this.customerId = customerId;
        this.itemQuantityMap = itemQuantityMap;
        this.description = description;
        this.price = price;
    }

    public static Function<OrderEntity, Order> toOrderMapper = orderEntity -> OrderFactory.of(
            Order.OrderId.of(orderEntity.getId()),
            orderEntity.getStatus(),
            Order.CustomerId.of(orderEntity.getCustomerId()),
            orderEntity.getItemQuantityMap()
    );

    public static Function<Order, OrderEntity> fromOrderEntityMapper = order -> OrderEntity.builder()
            .id(order.getId())
            .customerId(order.getCustomerId().toString())
            .itemQuantityMap(order.getItemQuantityMap())
            .build();
}
