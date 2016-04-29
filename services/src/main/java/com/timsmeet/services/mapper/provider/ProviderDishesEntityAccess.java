package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ProviderDishesEntityAccess implements DestinationCollectionAccess<Long, ProviderEntity, DishEntity> {

    @Override
    public Collection<DishEntity> getChilds(ProviderEntity source) {
        return source.getDishes();
    }

    @Override
    public Long getChildId(DishEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ProviderEntity parent, DishEntity child) {
        parent.removeDish(child);
    }

    @Override
    public void addChild(ProviderEntity parent, DishEntity child) {
        parent.addDish(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<DishEntity>(){}.getType();
    }

}
