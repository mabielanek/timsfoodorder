package com.timsmeet.persistance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.DishEntity;

public interface DishRepository extends CrudRepository<DishEntity, Long> {

    Page<DishEntity> findByProviderId(Long providerId, Pageable pageable);

    Page<DishEntity> findByProviderIdAndStatus(Long providerId, String status, Pageable pageable);
}
