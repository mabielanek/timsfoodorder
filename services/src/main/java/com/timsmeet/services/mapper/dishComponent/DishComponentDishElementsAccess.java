package com.timsmeet.services.mapper.dishComponent;

import java.util.Collection;

import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class DishComponentDishElementsAccess implements SourceCollectionAccess<Long, DishComponent, DishElement> { 

    @Override
    public Collection<DishElement> getChilds(DishComponent source) {
        return source.getDishElements();
    }

    @Override
    public Long getChildId(DishElement child) {
        return child.getId();
    }

}
