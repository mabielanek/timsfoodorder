package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.timsmeet.persistance.model.ProviderEntity;

public interface ProviderRepository extends PagingAndSortingRepository<ProviderEntity, Long> {

}
