package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ProviderDishesConversionAccess implements OneToManyConversionAccess<Provider, ProviderEntity, Dish, DishEntity> {

    @Override
    public Collection<Dish> getSourceChilds(Provider source) {
        return source.getDishes();
    }

    @Override
    public Collection<DishEntity> getDestinationChilds(ProviderEntity destination) {
        return destination.getDishes();
    }

    @Override
    public Long getSouceChildId(Dish child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildId(DishEntity child) {
        return child.getId();
    }

    @Override
    public void removeDestinationChild(ProviderEntity parent, DishEntity child) {
        parent.removeDish(child);
    }

    @Override
    public void addDestinationChild(ProviderEntity parent, DishEntity child) {
        parent.addDish(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<DishEntity>(){}.getType();
    }


}
