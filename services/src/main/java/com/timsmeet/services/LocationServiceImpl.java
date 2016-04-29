package com.timsmeet.services;

import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.timsmeet.dto.Location;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.model.LocationEntity;
import com.timsmeet.persistance.model.OrganizationEntity;
import com.timsmeet.persistance.repositories.LocationRepository;
import com.timsmeet.persistance.repositories.OrganizationRepository;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Location> readLocations(Long organizationId, Pageable pageable) {
        Page<LocationEntity> dbLocations = null;

        dbLocations = locationRepository.findByOrganizationId(organizationId, pageable);

        List<Location> result = Lists.<Location> newArrayList();
        if (dbLocations != null && dbLocations.hasContent()) {
            Type listType = new TypeToken<List<Location>>() {}.getType();
            result = modelMapper.map(dbLocations.getContent(), listType);
        }
        return result;
    }

    @Transactional
    @Override
    public Location save(Long organizationId, Location location) {
        OrganizationEntity dbOrganization = organizationRepository.findOne(organizationId);
        if(dbOrganization == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.ORGANIZATION_TO_SAVE_LOCATION_NOT_FOUND, organizationId));
        }
        LocationEntity dbLocation =
                (location.getId() == null) ? new LocationEntity() : locationRepository.findOne(location.getId());
        if(dbLocation == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.LOCATION_TO_SAVE_NOT_FOUND, location.getId()));
        }

        modelMapper.map(location, dbLocation);
        dbLocation.setOrganization(dbOrganization);
        dbLocation = locationRepository.save(dbLocation);
        modelMapper.map(dbLocation, location);
        return location;
    }

    @Transactional
    @Override
    public void delete(Long organizationId, Long locationId) {
        OrganizationEntity dbOrganization = organizationRepository.findOne(organizationId);

        if (dbOrganization == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.ORGANIZATION_TO_DELETE_LOCATION_NOT_FOUND, organizationId));
        }

        LocationEntity dbLocation = locationRepository.findOne(locationId);

        if(dbLocation == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.LOCATION_TO_DELETE_NOT_FOUND, locationId));
        }

        locationRepository.delete(dbLocation);

    }

}
