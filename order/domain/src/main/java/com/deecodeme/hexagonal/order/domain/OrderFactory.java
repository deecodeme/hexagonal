package com.deecodeme.hexagonal.order.domain;

import java.util.Map;

public class OrderFactory {
    public static Order create(Order.CustomerId customerId, Map<String, Integer> itemQuantityMap) {
        return Order.create(customerId, itemQuantityMap);
    }

    public static Order of(Order.OrderId orderId, Order.OrderStatus orderStatus, Order.CustomerId customerId,
                    Map<String, Integer> itemQuantityMap) {
        return Order.of(orderId, orderStatus, customerId, itemQuantityMap);
    }
}
