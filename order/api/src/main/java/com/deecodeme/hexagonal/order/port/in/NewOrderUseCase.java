package com.deecodeme.hexagonal.order.port.in;

import com.deecodeme.hexagonal.order.domain.Order;

import java.util.Map;

public interface NewOrderUseCase {
    Order createOrder(NewOrderCommand command);

    class NewOrderCommand {
        private final String customerId;
        private final Map<String, Integer> itemQuantityMap;
        public NewOrderCommand(String customerId, Map<String, Integer> itemQuantityMap) {
            this.customerId = customerId;
            this.itemQuantityMap = itemQuantityMap;
        }

        public String getCustomerId() {
            return customerId;
        }

        public Map<String, Integer> getItemQuantityMap() {
            return itemQuantityMap;
        }
    }
}
