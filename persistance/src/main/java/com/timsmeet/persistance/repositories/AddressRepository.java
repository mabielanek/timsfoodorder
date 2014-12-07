package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
