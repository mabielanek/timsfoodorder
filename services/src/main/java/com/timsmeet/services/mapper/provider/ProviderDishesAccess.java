package com.timsmeet.services.mapper.provider;

import java.util.Collection;

import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Provider;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ProviderDishesAccess implements SourceCollectionAccess<Long, Provider, Dish> {

    @Override
    public Collection<Dish> getChilds(Provider source) {
        return source.getDishes();
    }

    @Override
    public Long getChildId(Dish child) {
        return child.getId();
    }

}
