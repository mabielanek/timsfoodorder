package com.timsmeet.services.spring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.timsmeet.dto.AdditionalCost;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.AdditionalCostEntity;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.VacationEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.model.WorkingHourEntity;
import com.timsmeet.services.mapper.ChildEntityConverterBuilder;
import com.timsmeet.services.mapper.ContactEmailsConversionAccess;
import com.timsmeet.services.mapper.ContactPhonesConversionAccess;
import com.timsmeet.services.mapper.ContactWebUrlsConversionAccess;
import com.timsmeet.services.mapper.DishComponentDishElementsAccess;
import com.timsmeet.services.mapper.DishDishComponentsAccess;
import com.timsmeet.services.mapper.ProviderAdditionalCostsConvertionAccess;
import com.timsmeet.services.mapper.ProviderAddressConversionAccess;
import com.timsmeet.services.mapper.ProviderContactConversionAccess;
import com.timsmeet.services.mapper.ProviderDishesConversionAccess;
import com.timsmeet.services.mapper.ProviderVacationsConversionAccess;
import com.timsmeet.services.mapper.ProviderWorkingHoursConversionAccess;

@Configuration
public class MapperConfig {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        final PersistenceUnitUtil unitUtil = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                return unitUtil.isLoaded(context.getSource());
            }
        });

        modelMapper.addMappings(new PropertyMap<AdditionalCost, AdditionalCostEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Address, AddressEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Contact, ContactEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Contact, ContactEntity>()
                .addChildCollectionConverterFrom(new ContactEmailsConversionAccess())
                .addChildCollectionConverterFrom(new ContactWebUrlsConversionAccess())
                .addChildCollectionConverterFrom(new ContactPhonesConversionAccess()).build());

        modelMapper.addMappings(new PropertyMap<Dish, DishEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Dish, DishEntity>()
                .addChildCollectionConverterFrom(new DishDishComponentsAccess()).build());

        modelMapper.addMappings(new PropertyMap<DishComponent, DishComponentEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<DishComponent, DishComponentEntity>()
                .addChildCollectionConverterFrom(new DishComponentDishElementsAccess()).build());

        modelMapper.addMappings(new PropertyMap<DishElement, DishElementEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Email, EmailEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Phone, PhoneEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Provider, ProviderEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
                skip().setAddress(null);// done by post conversion
                skip().setContact(null);// done by post conversion
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Provider, ProviderEntity>()
                .addChildCollectionConverterFrom(new ProviderVacationsConversionAccess())
                .addChildCollectionConverterFrom(new ProviderAdditionalCostsConvertionAccess())
                .addChildCollectionConverterFrom(new ProviderWorkingHoursConversionAccess())
                .addChildCollectionConverterFrom(new ProviderDishesConversionAccess())
                .addChildEntityConverterFrom(new ProviderContactConversionAccess())
                .addChildEntityConverterFrom(new ProviderAddressConversionAccess()).build());

        modelMapper.addMappings(new PropertyMap<Vacation, VacationEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<WebUrl, WebUrlEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });

        modelMapper.addMappings(new PropertyMap<WorkingHour, WorkingHourEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        });



        //===============================================================================================

        modelMapper.addMappings(new PropertyMap<ProviderEntity, Provider>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map(source.getAddress()).setAddress(null);
                when(Conditions.isNotNull()).map(source.getContact()).setContact(null);
            }
        });


        return modelMapper;
    }

}
