package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.AdditionalCostEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ProviderAdditionalCostsEntityAccess implements DestinationCollectionAccess<Long, ProviderEntity, AdditionalCostEntity> {

    @Override
    public Collection<AdditionalCostEntity> getChilds(ProviderEntity source) {
        return source.getAdditionalCosts();
    }

    @Override
    public Long getChildId(AdditionalCostEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ProviderEntity parent, AdditionalCostEntity child) {
        parent.removeAdditionalCost(child);
    }

    @Override
    public void addChild(ProviderEntity parent, AdditionalCostEntity child) {
        parent.addAdditionalCost(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<AdditionalCostEntity>(){}.getType();
    }

}
