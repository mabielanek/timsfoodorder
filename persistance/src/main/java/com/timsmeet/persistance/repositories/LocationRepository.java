package com.timsmeet.persistance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.LocationEntity;

public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

    Page<LocationEntity> findByOrganizationId(Long organizationId, Pageable pageable);
}
