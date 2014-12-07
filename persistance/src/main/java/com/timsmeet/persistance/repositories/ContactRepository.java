package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.ContactEntity;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {

}
