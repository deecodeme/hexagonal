package com.deecodeme.hexagonal.order.adapter.persistence.dao;

import com.deecodeme.hexagonal.order.adapter.persistence.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
