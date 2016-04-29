package com.timsmeet.services.mapper.provider;

import java.util.Collection;

import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ProviderVacationsAccess implements SourceCollectionAccess<Long, Provider, Vacation> {

    @Override
    public Collection<Vacation> getChilds(Provider source) {
        return source.getVacations();
    }

    @Override
    public Long getChildId(Vacation child) {
        return child.getId();
    }

}
