package com.deecodeme.hexagonal.order.application.service;

import com.deecodeme.hexagonal.order.domain.Order;
import com.deecodeme.hexagonal.order.domain.OrderFactory;
import com.deecodeme.hexagonal.order.port.in.CancelOrderResult;
import com.deecodeme.hexagonal.order.port.in.CancelOrderUseCase;
import com.deecodeme.hexagonal.order.port.in.NewOrderUseCase;
import com.deecodeme.hexagonal.order.port.out.FindOrder;
import com.deecodeme.hexagonal.order.port.out.SaveOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private FindOrder findOrder;
    @Mock
    private SaveOrder saveOrder;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void createOrderShouldCreateAndSaveOrder() {
        NewOrderUseCase.NewOrderCommand command = NewOrderUseCase.NewOrderCommand.of("customerId", Map.of("Item1", 2));
        Order expectedOrder = OrderFactory.create(Order.CustomerId.of(command.getCustomerId()), command.getItemQuantityMap());

        given(saveOrder.create(any(Order.class))).willReturn(expectedOrder);

        Order result = orderService.createOrder(command);

        then(saveOrder).should().create(any(Order.class));
        assertEquals(expectedOrder, result);
    }

    @Test
    public void cancelOrderShouldReturnFailureWhenOrderNotFound() {
        CancelOrderUseCase.CancelOrderCommand command = CancelOrderUseCase.CancelOrderCommand.of("orderId");

        given(findOrder.byId(command.getOrderId())).willReturn(Optional.empty());

        CancelOrderResult result = orderService.cancelOrder(command);

        then(findOrder).should().byId(command.getOrderId());
        assertEquals(false, result.isSuccess());
    }

    @Test
    public void cancelOrderShouldReturnSuccessWhenOrderFound() {
        CancelOrderUseCase.CancelOrderCommand command = CancelOrderUseCase.CancelOrderCommand.of("orderId");
        Order order = OrderFactory.create(Order.CustomerId.of("customerId"), Map.of("Item1", 2));

        given(findOrder.byId(command.getOrderId())).willReturn(Optional.of(order));

        CancelOrderResult result = orderService.cancelOrder(command);

        then(findOrder).should().byId(command.getOrderId());
        assertEquals(true, result.isSuccess());
    }
}