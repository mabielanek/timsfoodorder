package com.timsmeet.services;

import java.util.List;
import com.timsmeet.dto.Provider;

public interface ProviderService {

    List<Provider> readProviders();

    Provider save(Provider provider);

    Provider readProvider(Long providerId, String[] embeded);

    void delete(Long providerId);
}
