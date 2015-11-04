package com.timsmeet.persistance.repositories;

import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.FoodOrderEntity;

public interface FoodOrderRepository extends CrudRepository<FoodOrderEntity, Long> {
    Iterable<FoodOrderEntity> findByOrderStatusAndOrderTimeLessThan(String orderStatus, Timestamp orderTime);
    Iterable<FoodOrderEntity> findByOrderStatus(String orderStatus);
    Iterable<FoodOrderEntity> findByOrderTimeLessThan(Timestamp orderTime);
}
