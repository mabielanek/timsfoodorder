package com.timsmeet.services.mapper.dish;

import java.util.Collection;

import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class DishDishComponentsAccess implements SourceCollectionAccess<Long, Dish, DishComponent> {

    @Override
    public Collection<DishComponent> getChilds(Dish source) {
        return source.getDishComponents();
    }

    @Override
    public Long getChildId(DishComponent child) {
        return child.getId();
    }

}
