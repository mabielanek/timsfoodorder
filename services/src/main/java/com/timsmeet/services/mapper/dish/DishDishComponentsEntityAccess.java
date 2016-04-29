package com.timsmeet.services.mapper.dish;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class DishDishComponentsEntityAccess implements DestinationCollectionAccess<Long, DishEntity, DishComponentEntity> {

    @Override
    public Collection<DishComponentEntity> getChilds(DishEntity source) {
        return source.getDishComponents();
    }

    @Override
    public Long getChildId(DishComponentEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(DishEntity parent, DishComponentEntity child) {
        parent.removeDishComponent(child);
    }

    @Override
    public void addChild(DishEntity parent, DishComponentEntity child) {
        parent.addDishComponent(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<DishComponentEntity>(){}.getType();
    }

}
