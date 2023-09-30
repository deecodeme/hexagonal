package com.deecodeme.hexagonal.order.adapter.persistence.dao;

import com.deecodeme.hexagonal.exception.DataAccessException;
import com.deecodeme.hexagonal.order.adapter.persistence.entity.OrderEntity;
import com.deecodeme.hexagonal.order.domain.Order;
import com.deecodeme.hexagonal.order.port.out.FindOrder;
import com.deecodeme.hexagonal.order.port.out.SaveOrder;

import java.util.Optional;

public class OrderDao implements FindOrder, SaveOrder {
    private OrderRepository orderRepository;
    @Override
    public Optional<Order> byId(final String orderId) {
        try {
            return orderRepository.findById(orderId).map(OrderEntity.toOrderMapper);
        }catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Order update(Order order) {
        try {
            OrderEntity orderEntity = orderRepository.save(OrderEntity.fromOrderEntityMapper.apply(order));
            return OrderEntity.toOrderMapper.apply(orderEntity);
        }catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Order create(Order order) {
        try {
            OrderEntity orderEntity = orderRepository.save(OrderEntity.fromOrderEntityMapper.apply(order));
            return OrderEntity.toOrderMapper.apply(orderEntity);
        }catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
