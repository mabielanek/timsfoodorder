package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.timsmeet.persistance.model.OrganizationEntity;

public interface OrganizationRepository extends PagingAndSortingRepository<OrganizationEntity, Long> {

}
