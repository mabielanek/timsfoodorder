package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishEntity;

public class DishDishComponentsAccess implements
        OneToManyConversionAccess<com.timsmeet.dto.Dish, com.timsmeet.persistance.model.DishEntity, DishComponent, DishComponentEntity> {

    @Override
    public Collection<DishComponent> getSourceChilds(Dish source) {
        return source.getDishComponents();
    }

    @Override
    public Collection<DishComponentEntity> getDestinationChilds(DishEntity destination) {
        return destination.getDishComponents();
    }

    @Override
    public Long getSouceChildId(DishComponent child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(DishComponentEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(DishEntity parent, DishComponentEntity child) {
        parent.removeDishComponent(child);
    }

    @Override
    public void addDestinationChild(DishEntity parent, DishComponentEntity child) {
        parent.addDishComponent(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<DishComponentEntity>(){}.getType();
    }

}
