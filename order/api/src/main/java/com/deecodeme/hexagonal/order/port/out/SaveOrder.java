package com.deecodeme.hexagonal.order.port.out;

import com.deecodeme.hexagonal.order.domain.Order;

public interface SaveOrder {
    Order update(Order order);
    Order create(Order order);
}
