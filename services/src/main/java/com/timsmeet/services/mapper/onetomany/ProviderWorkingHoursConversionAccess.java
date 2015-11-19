package com.timsmeet.services.mapper.onetomany;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ProviderWorkingHoursConversionAccess implements OneToManyConversionAccess<Provider, ProviderEntity, WorkingHour, ProviderWorkingHourEntity> {

    @Override
    public Collection<WorkingHour> getSourceChilds(Provider source) {
        return source.getWorkingHours();
    }

    @Override
    public Collection<ProviderWorkingHourEntity> getDestinationChilds(ProviderEntity destination) {
        return destination.getWorkingHours();
    }

    @Override
    public Long getSouceChildId(WorkingHour child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(ProviderWorkingHourEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(ProviderEntity parent, ProviderWorkingHourEntity child) {
        parent.removeWorkingHour(child);
    }

    @Override
    public void addDestinationChild(ProviderEntity parent, ProviderWorkingHourEntity child) {
        parent.addWorkingHour(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<ProviderWorkingHourEntity>(){}.getType();
    }


}
