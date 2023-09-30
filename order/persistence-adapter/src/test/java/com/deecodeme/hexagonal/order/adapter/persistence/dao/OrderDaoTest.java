package com.deecodeme.hexagonal.order.adapter.persistence.dao;

import com.deecodeme.hexagonal.exception.DataAccessException;
import com.deecodeme.hexagonal.order.adapter.persistence.entity.OrderEntity;
import com.deecodeme.hexagonal.order.domain.Order;
import com.deecodeme.hexagonal.order.domain.OrderFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
public class OrderDaoTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderDao orderDao;

    @Test
    void shouldReturnOrderById() {
        //given
        OrderEntity orderEntity = OrderEntity.builder()
                .customerId("customerId")
                .id("order_id")
                .status(Order.OrderStatus.NEW).build(); // Add necessary data
        Order expectedOrder = OrderFactory.of(Order.OrderId.of("order_id"), orderEntity.getStatus(),
                Order.CustomerId.of("customerId"), Map.of()); // Add necessary data
        given(orderRepository.findById(anyString())).willReturn(Optional.of(orderEntity));

        //when
        Optional<Order> optionalOrder = orderDao.byId("order1");

        //then
        then(optionalOrder).isPresent();
        Order order = optionalOrder.get();
        then(order.getOrderId()).isEqualTo(expectedOrder.getOrderId());
        then(order.getStatus()).isEqualTo(expectedOrder.getStatus());
        then(order.getCustomerId()).isEqualTo(expectedOrder.getCustomerId());
    }

    @Test
    void shouldThrowDatabaseWhenDownstreamException() {
        //given
        given(orderRepository.findById(anyString())).willThrow(RuntimeException.class);

        //when & then
        thenThrownBy(() -> orderDao.byId("order1"))
                .isInstanceOf(DataAccessException.class);
    }

    @Test
    void shouldReturnEmptyWhenOrderNotFound() {
        //given
        given(orderRepository.findById(anyString())).willReturn(Optional.empty());

        //when
        Optional<Order> optionalOrder = orderDao.byId("order1");

        //then
        then(optionalOrder).isEmpty();
    }

    @Test
    void shouldUpdateOrder() {
        OrderEntity orderEntity = OrderEntity.builder().customerId("customerId").id("order_id").status(Order.OrderStatus.NEW).build(); // Add necessary data
        Order expectedOrder = OrderFactory.of(Order.OrderId.of("order_id"), orderEntity.getStatus(),
                Order.CustomerId.of("customerId"), Map.of()); // Add necessary data
        given(orderRepository.save(any())).willReturn(orderEntity);

        Order updatedOrder = orderDao.update(expectedOrder);

        //then
        then(updatedOrder.getOrderId()).isEqualTo(expectedOrder.getOrderId());
        then(updatedOrder.getStatus()).isEqualTo(expectedOrder.getStatus());
        then(updatedOrder.getCustomerId()).isEqualTo(expectedOrder.getCustomerId());
    }

    @Test
    void shouldCreateOrder() {
        OrderEntity orderEntity = OrderEntity.builder().customerId("customerId").id("order_id").build(); // Add necessary data
        Order expectedOrder = OrderFactory.of(Order.OrderId.of("order_id"), orderEntity.getStatus(),
                Order.CustomerId.of("customerId"), Map.of()); // Add necessary data
        given(orderRepository.save(any())).willReturn(orderEntity);

        Order createdOrder = orderDao.create(expectedOrder);

        //then
        then(createdOrder.getOrderId()).isEqualTo(expectedOrder.getOrderId());
        then(createdOrder.getStatus()).isEqualTo(expectedOrder.getStatus());
        then(createdOrder.getCustomerId()).isEqualTo(expectedOrder.getCustomerId());
    }
}
