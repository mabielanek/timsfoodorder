package com.timsmeet.services.mapper.dishComponent;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class DishComponentDishElementsEntityAccess implements DestinationCollectionAccess<Long, DishComponentEntity, DishElementEntity> {

    @Override
    public Collection<DishElementEntity> getChilds(DishComponentEntity destination) {
        return destination.getDishElements();
    }

    @Override
    public Long getChildId(DishElementEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(DishComponentEntity parent, DishElementEntity child) {
        parent.removeDishElement(child);
    }

    @Override
    public void addChild(DishComponentEntity parent, DishElementEntity child) {
        parent.addDishElement(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<DishElementEntity>(){}.getType();
    }
}
