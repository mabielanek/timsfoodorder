package com.timsmeet.services.spring.mapping;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.timsmeet.dto.DishComponent;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.services.mapper.ChildEntityConverterBuilder;
import com.timsmeet.services.mapper.dishComponent.DishComponentDishElementsEntityAccess;
import com.timsmeet.services.mapper.dishComponent.DishComponentDishElementsAccess;

@Service
public class DishComponentMappingConfig {

    public void addMappings(ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<DishComponent, DishComponentEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<DishComponent, DishComponentEntity>()
                .addCollectionConverterFrom(new DishComponentDishElementsAccess(), new DishComponentDishElementsEntityAccess()).build());

    }
}
