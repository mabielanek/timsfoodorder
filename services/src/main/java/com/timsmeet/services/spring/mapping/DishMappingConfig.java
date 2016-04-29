package com.timsmeet.services.spring.mapping;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timsmeet.dto.Dish;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.repositories.GenereRepository;
import com.timsmeet.services.mapper.ChildEntityConverterBuilder;
import com.timsmeet.services.mapper.dish.DishDishComponentsAccess;
import com.timsmeet.services.mapper.dish.DishDishComponentsEntityAccess;
import com.timsmeet.services.mapper.dish.DishGeneresAccess;
import com.timsmeet.services.mapper.dish.DishGeneresJoinEntityAccess;

@Service
public class DishMappingConfig {

    @Autowired
    private GenereRepository genereRepository;

    public void addMappings(ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<Dish, DishEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Dish, DishEntity>()
                .addCollectionConverterFrom(new DishDishComponentsAccess(), new DishDishComponentsEntityAccess())
                .addCollectionConverterFrom(new DishGeneresAccess(), new DishGeneresJoinEntityAccess(genereRepository)).build());
    }
}
