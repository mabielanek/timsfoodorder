package com.timsmeet.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.DishEntity;

public interface DishRepository extends CrudRepository<DishEntity, Long> {

    List<DishEntity> findByProviderId(Long providerId);
}
