package com.deecodeme.hexagonal.order.port.out;

import com.deecodeme.hexagonal.order.domain.Order;

import java.util.Optional;

public interface FindOrder {
    Optional<Order> byId(String orderId);
}
