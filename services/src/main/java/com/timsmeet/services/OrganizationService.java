package com.timsmeet.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.timsmeet.dto.Organization;

public interface OrganizationService {

    public static final String ALLOW_SORT_ID = "id";
    public static final String ALLOW_SORT_NAME = "name";

    List<Organization> readOrganizations(Pageable pageable);

    Organization save(Organization organization);

    void delete(Long organizationId);
    
}
