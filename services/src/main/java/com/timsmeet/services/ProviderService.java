package com.timsmeet.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.timsmeet.dto.Provider;

public interface ProviderService {

    public static final String EMBED_WORKING_HOURS = "workingHours";
    public static final String EMBED_VACATIONS = "vacations";
    public static final String EMBED_CONTACT_EMAILS = "contact.emails";
    public static final String EMBED_CONTACT_PHONES = "contact.phones";
    public static final String EMBED_CONTACT_WEB_URLS = "contact.webUrls";
    public static final String EMBED_ADDITIONAL_COSTS = "additionalCosts";
    
    public static final String ALLOW_SORT_ID = "id";
    public static final String ALLOW_SORT_NAME = "name";
    
    List<Provider> readProviders(Pageable pageable);

    Provider save(Provider provider);

    Provider readProvider(Long providerId, String[] embeded);

    void delete(Long providerId);
}
