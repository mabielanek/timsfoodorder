package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ProviderWorkingHoursEntityAccess implements DestinationCollectionAccess<Long, ProviderEntity, ProviderWorkingHourEntity> {

    @Override
    public Collection<ProviderWorkingHourEntity> getChilds(ProviderEntity source) {
        return source.getWorkingHours();
    }

    @Override
    public Long getChildId(ProviderWorkingHourEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ProviderEntity parent, ProviderWorkingHourEntity child) {
        parent.removeWorkingHour(child);
    }

    @Override
    public void addChild(ProviderEntity parent, ProviderWorkingHourEntity child) {
        parent.addWorkingHour(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<ProviderWorkingHourEntity>(){}.getType();
    }


}
