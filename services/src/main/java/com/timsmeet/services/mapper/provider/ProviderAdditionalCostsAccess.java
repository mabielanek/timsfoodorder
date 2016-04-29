package com.timsmeet.services.mapper.provider;

import java.util.Collection;

import com.timsmeet.dto.AdditionalCost;
import com.timsmeet.dto.Provider;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ProviderAdditionalCostsAccess implements SourceCollectionAccess<Long, Provider, AdditionalCost> {

    @Override
    public Collection<AdditionalCost> getChilds(Provider source) {
        return source.getAdditionalCosts();
    }

    @Override
    public Long getChildId(AdditionalCost child) {
        return child.getId();
    }

}
