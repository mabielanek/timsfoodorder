package com.timsmeet.services.mapper.onetomany;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderVacationEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ProviderVacationsConversionAccess implements
        OneToManyConversionAccess<Provider, ProviderEntity, Vacation, ProviderVacationEntity> {

    @Override
    public Collection<Vacation> getSourceChilds(Provider source) {
        return source.getVacations();
    }

    @Override
    public Collection<ProviderVacationEntity> getDestinationChilds(ProviderEntity destination) {
        return destination.getVacations();
    }

    @Override
    public Long getSouceChildId(Vacation child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(ProviderVacationEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(ProviderEntity parent, ProviderVacationEntity child) {
        parent.removeVacation(child);
    }

    @Override
    public void addDestinationChild(ProviderEntity parent, ProviderVacationEntity child) {
        parent.addVacation(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<ProviderVacationEntity>(){}.getType();
    }

}
