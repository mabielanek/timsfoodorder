package com.timsmeet.services.mapper.provider;

import java.util.Collection;

import com.timsmeet.dto.Provider;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ProviderWorkingHoursAccess implements SourceCollectionAccess<Long, Provider, WorkingHour> {

    @Override
    public Collection<WorkingHour> getChilds(Provider source) {
        return source.getWorkingHours();
    }

    @Override
    public Long getChildId(WorkingHour child) {
        return child.getId();
    }

}
