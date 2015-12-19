package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.dto.AdditionalCost;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.AdditionalCostEntity;
import com.timsmeet.persistance.model.ProviderEntity;

public class ProviderAdditionalCostsConvertionAccess implements OneToManyConversionAccess<Provider, ProviderEntity, AdditionalCost, AdditionalCostEntity> {

    @Override
    public Collection<AdditionalCost> getSourceChilds(Provider source) {
        return source.getAdditionalCosts();
    }

    @Override
    public Collection<AdditionalCostEntity> getDestinationChilds(ProviderEntity destination) {
        return destination.getAdditionalCosts();
    }

    @Override
    public Long getSouceChildId(AdditionalCost child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(AdditionalCostEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(ProviderEntity parent, AdditionalCostEntity child) {
        parent.removeAdditionalCost(child);
    }

    @Override
    public void addDestinationChild(ProviderEntity parent, AdditionalCostEntity child) {
        parent.addAdditionalCost(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<AdditionalCostEntity>(){}.getType();
    }

}
