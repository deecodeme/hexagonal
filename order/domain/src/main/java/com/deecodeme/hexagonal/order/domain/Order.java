package com.deecodeme.hexagonal.order.domain;

import com.deecodeme.hexagonal.ddd.Entity;
import com.deecodeme.hexagonal.ddd.ValueObject;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Getter
@ToString
public class Order extends Entity {
    private final OrderId orderId;
    private OrderStatus status;
    private final CustomerId customerId;
    private final Map<String, Integer> itemQuantityMap;

    Order(OrderId orderId, OrderStatus status, CustomerId customerId, Map<String, Integer> itemQuantityMap) {
        super(orderId.id);
        this.orderId = orderId;
        this.status = status;
        this.itemQuantityMap = itemQuantityMap;
        this.customerId = customerId;
    }

    static Order create(CustomerId customerId, Map<String, Integer> itemQuantityMap) {
        return new Order(OrderId.of(UUID.randomUUID().toString()), OrderStatus.NEW, customerId, itemQuantityMap);
    }

    static Order of(OrderId orderId, OrderStatus orderStatus, CustomerId customerId, Map<String, Integer> itemQuantityMap) {
        return new Order(orderId, orderStatus, customerId, itemQuantityMap);
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

    public static class OrderId implements ValueObject {
        private final String id;

        private OrderId(String id) {
            this.id = id;
        }

        public static OrderId of(String id) {
            return new OrderId(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrderId orderId = (OrderId) o;

            return id.equals(orderId.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static class CustomerId implements ValueObject {
        private final String id;

        private CustomerId(String id) {
            this.id = id;
        }

        public static CustomerId of(String id) {
            return new CustomerId(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerId)) return false;
            CustomerId that = (CustomerId) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
