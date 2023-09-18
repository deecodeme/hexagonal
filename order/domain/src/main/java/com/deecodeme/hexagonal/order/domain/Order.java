package com.deecodeme.hexagonal.order.domain;

import com.deecodeme.hexagonal.ddd.Entity;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Getter
@ToString
public class Order extends Entity {
    private OrderStatus status;
    private final String customerId;
    private final Map<String, Integer> itemQuantityMap;

    private Order(String customerId, Map<String, Integer> itemQuantityMap) {
        super(UUID.randomUUID().toString());
        this.itemQuantityMap = itemQuantityMap;
        this.customerId = customerId;
    }

    public static Order create(String customerId, Map<String, Integer> itemQuantityMap) {
        return new Order(customerId, itemQuantityMap);
    }

    public boolean cancel() {
        if (this.status.equals(OrderStatus.NEW)) {
            this.status = OrderStatus.CANCELED;
            return true;
        }
        return false;
    }

    public static enum OrderStatus {
        NEW, CONFIRMED, PREPARING, DELIVERING, DELIVERED, CANCELED
    }
}
