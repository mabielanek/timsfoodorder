package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderVacationEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ProviderVacationsEntityAccess implements DestinationCollectionAccess<Long, ProviderEntity, ProviderVacationEntity> {

    @Override
    public Collection<ProviderVacationEntity> getChilds(ProviderEntity source) {
        return source.getVacations();
    }

    @Override
    public Long getChildId(ProviderVacationEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ProviderEntity parent, ProviderVacationEntity child) {
        parent.removeVacation(child);
    }

    @Override
    public void addChild(ProviderEntity parent, ProviderVacationEntity child) {
        parent.addVacation(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<ProviderVacationEntity>(){}.getType();
    }

}
