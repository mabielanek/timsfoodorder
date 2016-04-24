package com.timsmeet.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.timsmeet.dto.Location;

public interface LocationService {

    public static final String ALLOW_SORT_ID = "id";
    public static final String ALLOW_SORT_NAME = "name";

    List<Location> readLocations(Long organizationId, Pageable pageable);

    Location save(Long organizationId, Location location);

    void delete(Long organizationId, Long locationId);

}
