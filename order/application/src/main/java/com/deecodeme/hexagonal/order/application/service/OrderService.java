package com.deecodeme.hexagonal.order.application.service;

import com.deecodeme.hexagonal.order.domain.Order;
import com.deecodeme.hexagonal.order.port.in.CancelOrderResult;
import com.deecodeme.hexagonal.order.port.in.CancelOrderUseCase;
import com.deecodeme.hexagonal.order.port.in.NewOrderUseCase;
import com.deecodeme.hexagonal.order.port.out.FindOrder;
import com.deecodeme.hexagonal.order.port.out.SaveOrder;

import java.util.Optional;

public class OrderService implements NewOrderUseCase, CancelOrderUseCase {
    private final FindOrder findOrder;

    private final SaveOrder saveOrder;

    public OrderService(FindOrder findOrder, SaveOrder saveOrder) {
        this.findOrder = findOrder;
        this.saveOrder = saveOrder;
    }

    @Override
    public Order createOrder(NewOrderCommand command) {
        Order newOrder = Order.create(command.getCustomerId(), command.getItemQuantityMap());
        return saveOrder.create(newOrder);
    }

    @Override
    public CancelOrderResult cancelOrder(CancelOrderCommand cancelOrderCommand) {
        Optional<Order> order = findOrder.byId(cancelOrderCommand.getOrderId());
        if(order.isEmpty()){
            return CancelOrderResult.failure("Order not found");
        }
        return CancelOrderResult.success();
    }
}
