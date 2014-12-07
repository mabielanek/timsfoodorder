package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.ProviderEntity;

public interface ProviderRepository extends CrudRepository<ProviderEntity, Long> {

}
