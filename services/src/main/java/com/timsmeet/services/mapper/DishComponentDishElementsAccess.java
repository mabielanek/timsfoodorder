package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;

public class DishComponentDishElementsAccess implements OneToManyConversionAccess<DishComponent, DishComponentEntity, DishElement, DishElementEntity> {

    @Override
    public Collection<DishElement> getSourceChilds(DishComponent source) {
        return source.getDishElements();
    }

    @Override
    public Collection<DishElementEntity> getDestinationChilds(DishComponentEntity destination) {
        return destination.getDishElements();
    }

    @Override
    public Long getSouceChildId(DishElement child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(DishElementEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(DishComponentEntity parent, DishElementEntity child) {
        parent.removeDishElement(child);
    }

    @Override
    public void addDestinationChild(DishComponentEntity parent, DishElementEntity child) {
        parent.addDishElement(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<DishElementEntity>(){}.getType();
    }

}
