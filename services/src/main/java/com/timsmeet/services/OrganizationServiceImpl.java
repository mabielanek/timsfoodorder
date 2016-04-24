package com.timsmeet.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Organization;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.model.OrganizationEntity;
import com.timsmeet.persistance.repositories.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Organization> readOrganizations(Pageable pageable) {
        List<OrganizationEntity> dbOrganizations = Lists.newArrayList(organizationRepository.findAll(pageable));
        List<Organization> organizations = Lists.newArrayListWithCapacity(dbOrganizations.size());
        for (OrganizationEntity dbOrganization : dbOrganizations) {
            Organization organization = new Organization();
            modelMapper.map(dbOrganization, organization);
            organizations.add(organization);
        }
        return organizations;
    }

    @Transactional
    @Override
    public Organization save(Organization organization) {
        OrganizationEntity dbOrganization =
                (organization.getId() == null) ? new OrganizationEntity() : organizationRepository.findOne(organization.getId());

        if (dbOrganization != null) {
            modelMapper.map(organization, dbOrganization);
            dbOrganization = organizationRepository.save(dbOrganization);
        } else {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.ORGANIZATION_TO_UPDATE_NOT_FOUND, organization.getId()));
        }

        Organization savedOrganization = new Organization();
        modelMapper.map(dbOrganization, savedOrganization);
        return savedOrganization;
    }

    @Transactional
    @Override
    public void delete(Long organizationId) {
        OrganizationEntity dbOrganization = organizationRepository.findOne(organizationId);

        if (dbOrganization == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.ORGANIZATION_TO_DELETE_NOT_FOUND, organizationId));
        }

        organizationRepository.delete(dbOrganization);
    }

}
