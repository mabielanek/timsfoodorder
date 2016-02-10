package com.timsmeet.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.timsmeet.dto.Provider;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.repositories.AddressRepository;
import com.timsmeet.persistance.repositories.ContactRepository;
import com.timsmeet.persistance.repositories.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Provider> readProviders(Pageable pageable) {

        List<ProviderEntity> dbProviders = Lists.newArrayList(providerRepository.findAll(pageable));
        List<Provider> providers = Lists.newArrayListWithCapacity(dbProviders.size());
        for (ProviderEntity dbProvider : dbProviders) {
            Provider provider = new Provider();
            modelMapper.map(dbProvider, provider);
            providers.add(provider);
        }
        return providers;
    }

    @Override
    @Transactional
    public Provider save(Provider provider) {
        ProviderEntity dbProvider =
                (provider.getId() == null) ? new ProviderEntity() : providerRepository.findOne(provider.getId());

        if (dbProvider != null) {
            modelMapper.map(provider, dbProvider);
            //providerMapper.map(provider, dbProvider);
            dbProvider = providerRepository.save(dbProvider);
        } else {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_UPDATE_NOT_FOUND, provider.getId()));
        }

        Provider savedProvider = new Provider();
        modelMapper.map(dbProvider, savedProvider);
        //providerMapper.inverseMap(dbProvider, savedProvider);
        return savedProvider;
    }

    @Override
    @Transactional
    public Provider readProvider(Long providerId, String[] embeded) {
        Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
                : Collections.<String> emptySet();
        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_READ_NOT_FOUND, providerId));
        }

        if (embededSet.contains(EMBED_WORKING_HOURS)) {
            Hibernate.initialize(dbProvider.getWorkingHours());
        }
        if (embededSet.contains(EMBED_VACATIONS)) {
            Hibernate.initialize(dbProvider.getVacations());
        }
        if (embededSet.contains(EMBED_CONTACT_EMAILS)) {
            Hibernate.initialize(dbProvider.getContact().getEmails());
        }
        if (embededSet.contains(EMBED_CONTACT_PHONES)) {
            Hibernate.initialize(dbProvider.getContact().getPhones());
        }
        if (embededSet.contains(EMBED_CONTACT_WEB_URLS)) {
            Hibernate.initialize(dbProvider.getContact().getWebUrls());
        }
        if(embededSet.contains(EMBED_ADDITIONAL_COSTS)) {
            Hibernate.initialize(dbProvider.getAdditionalCosts());
        }

        Provider provider = new Provider();
        modelMapper.map(dbProvider, provider);
        //providerMapper.inverseMap(dbProvider, provider);
        return provider;
    }

    @Override
    public void delete(Long providerId) {

        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_DELETE_NOT_FOUND, providerId));
        }

        providerRepository.delete(dbProvider);
        addressRepository.delete(dbProvider.getAddress());
        contactRepository.delete(dbProvider.getContact());
    }

}
