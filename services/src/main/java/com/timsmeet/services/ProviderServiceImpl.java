package com.timsmeet.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.repositories.AddressRepository;
import com.timsmeet.persistance.repositories.ContactRepository;
import com.timsmeet.persistance.repositories.ProviderRepository;
import com.timsmeet.services.mapper.ProviderMapper;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> readProviders() {
        List<ProviderEntity> dbProviders = Lists.newArrayList(providerRepository.findAll());
        List<Provider> providers = Lists.newArrayListWithCapacity(dbProviders.size());
        for (ProviderEntity dbProvider : dbProviders) {
            Provider provider = new Provider();
            providerMapper.inverseMap(dbProvider, provider);
            providers.add(provider);
        }
        return providers;
    }

    @Override
    @Transactional
    public Provider save(Provider provider) {
        ProviderEntity dbProvider = null;
        if (provider.getId() == null) {
            dbProvider = new ProviderEntity();
        } else {
            dbProvider = providerRepository.findOne(provider.getId());
        }

        if (dbProvider != null) {
            providerMapper.map(provider, dbProvider);
            dbProvider = providerRepository.save(dbProvider);
        } else {
            throw new IllegalStateException("No Provider to update");
            // TODO proper handling of not found
        }

        Provider savedProvider = new Provider();
        providerMapper.inverseMap(dbProvider, savedProvider);
        return savedProvider;
    }

    @Override
    @Transactional
    public Provider readProvider(Long providerId, String[] embeded) {
        Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
                : Collections.<String> emptySet();
        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider == null) {
            throw new NotFoundException("Provider with id: " + providerId + " not found.");
        }

        if (embededSet.contains("workingHours")) {
            Hibernate.initialize(dbProvider.getWorkingHours());
        }
        if (embededSet.contains("vacations")) {
            Hibernate.initialize(dbProvider.getVacations());
        }
        if (embededSet.contains("contact.emails")) {
            Hibernate.initialize(dbProvider.getContact().getEmails());
        }
        if (embededSet.contains("contact.phones")) {
            Hibernate.initialize(dbProvider.getContact().getPhones());
        }
        if (embededSet.contains("contact.webUrls")) {
            Hibernate.initialize(dbProvider.getContact().getWebUrls());
        }
        if(embededSet.contains("additionalCosts")) {
            Hibernate.initialize(dbProvider.getAdditionalCosts());
        }

        Provider provider = new Provider();
        providerMapper.inverseMap(dbProvider, provider);
        return provider;
    }

    @Override
    public void delete(Long providerId) {

        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider == null) {
            throw new NotFoundException("Provider with id: " + providerId + " not found.");
        }

        providerRepository.delete(dbProvider);
        addressRepository.delete(dbProvider.getAddress());
        contactRepository.delete(dbProvider.getContact());
    }

}
