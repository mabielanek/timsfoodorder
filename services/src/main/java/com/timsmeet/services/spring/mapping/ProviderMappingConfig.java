package com.timsmeet.services.spring.mapping;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.ChildEntityConverterBuilder;
import com.timsmeet.services.mapper.provider.ProviderAdditionalCostsAccess;
import com.timsmeet.services.mapper.provider.ProviderAdditionalCostsEntityAccess;
import com.timsmeet.services.mapper.provider.ProviderAddressAccess;
import com.timsmeet.services.mapper.provider.ProviderAddressEntityAccess;
import com.timsmeet.services.mapper.provider.ProviderContactAccess;
import com.timsmeet.services.mapper.provider.ProviderContactEntityAccess;
import com.timsmeet.services.mapper.provider.ProviderDishesAccess;
import com.timsmeet.services.mapper.provider.ProviderDishesEntityAccess;
import com.timsmeet.services.mapper.provider.ProviderVacationsAccess;
import com.timsmeet.services.mapper.provider.ProviderVacationsEntityAccess;
import com.timsmeet.services.mapper.provider.ProviderWorkingHoursAccess;
import com.timsmeet.services.mapper.provider.ProviderWorkingHoursEntityAccess;

@Service
public class ProviderMappingConfig {

    public void addMappings(ModelMapper modelMapper) {

        modelMapper.addMappings(new PropertyMap<Provider, ProviderEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
                skip().setAddress(null);// done by post conversion, because setter should be called from entity ?
                skip().setContact(null);// done by post conversion
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Provider, ProviderEntity>()
              //done by post conversion because there is no getter/setter on collections
                .addCollectionConverterFrom(new ProviderVacationsAccess(), new ProviderVacationsEntityAccess())
                .addCollectionConverterFrom(new ProviderAdditionalCostsAccess(), new ProviderAdditionalCostsEntityAccess())
                .addCollectionConverterFrom(new ProviderWorkingHoursAccess(), new ProviderWorkingHoursEntityAccess())
                .addCollectionConverterFrom(new ProviderDishesAccess(), new ProviderDishesEntityAccess())
                .addChildConverterFrom(new ProviderContactAccess(), new ProviderContactEntityAccess())
                .addChildConverterFrom(new ProviderAddressAccess(), new ProviderAddressEntityAccess())
                .build());

        //-------------------------

        modelMapper.addMappings(new PropertyMap<ProviderEntity, Provider>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map(source.getAddress()).setAddress(null);
                when(Conditions.isNotNull()).map(source.getContact()).setContact(null);
            }
        });
    }

}
