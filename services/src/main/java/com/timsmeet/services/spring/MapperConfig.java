package com.timsmeet.services.spring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishElement;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Genere;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.AdditionalCostEntity;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.VacationEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.model.WorkingHourEntity;
import com.timsmeet.services.spring.mapping.ContactMappingConfig;
import com.timsmeet.services.spring.mapping.DishComponentMappingConfig;
import com.timsmeet.services.spring.mapping.DishMappingConfig;
import com.timsmeet.services.spring.mapping.ProviderMappingConfig;

@Configuration
public class MapperConfig {

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // private GenereRepository genereRepository;

    @Autowired
    private ProviderMappingConfig providerMappingConfig;

    @Autowired
    private ContactMappingConfig contactMappingConfig;

    @Autowired
    private DishMappingConfig dishMappingConfig;
    
    @Autowired 
    private DishComponentMappingConfig dishComponentMappingConfig;

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

        contactMappingConfig.addMappings(modelMapper);
        // modelMapper.addMappings(new PropertyMap<Contact, ContactEntity>() {
        // @Override
        // protected void configure() {
        // when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
        // }
        // }).setPostConverter(new ChildEntityConverterBuilder<Contact,
        // ContactEntity>()
        // .addCollectionConverterFrom(new ContactEmailsAccess(), new
        // ContactEmailsEntityAccess())
        // .addCollectionConverterFrom(new ContactWebUrlAccess(), new
        // ContactWebUrlEntityAccess())
        // .addCollectionConverterFrom(new ContactPhonesAccess(), new
        // ContactPhonesEntityAccess()).build());

        dishMappingConfig.addMappings(modelMapper);
        // modelMapper.addMappings(new PropertyMap<Dish, DishEntity>() {
        // @Override
        // protected void configure() {
        // when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
        // }
        // }).setPostConverter(new ChildEntityConverterBuilder<Dish,
        // DishEntity>()
        // .addCollectionConverterFrom(new DishDishComponentsAccess(), new
        // DishDishComponentsEntityAccess())
        // .addJoinCollectionConverterFrom(new
        // DishGeneresConversionAccess(genereRepository)).build());

        dishComponentMappingConfig.addMappings(modelMapper);
//        modelMapper.addMappings(new PropertyMap<DishComponent, DishComponentEntity>() {
//            @Override
//            protected void configure() {
//                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
//            }
//        }).setPostConverter(new ChildEntityConverterBuilder<DishComponent, DishComponentEntity>()
//                .addCollectionConverterFrom(new DishComponentDishElementsAccess()).build());

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

        providerMappingConfig.addMappings(modelMapper);
        // modelMapper.addMappings(new PropertyMap<Provider, ProviderEntity>() {
        // @Override
        // protected void configure() {
        // when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
        // skip().setAddress(null);// done by post conversion, because setter
        // should be called from entity ?
        // skip().setContact(null);// done by post conversion
        // }
        // }).setPostConverter(new ChildEntityConverterBuilder<Provider,
        // ProviderEntity>()
        // .addCollectionConverterFrom(new
        // ProviderVacationsConversionAccess())//done by post conversion because
        // there is no getter/setter on collections
        // .addCollectionConverterFrom(new
        // ProviderAdditionalCostsConvertionAccess())
        // .addCollectionConverterFrom(new ProviderWorkingHoursAccess(), new
        // ProviderWorkingHoursEntityAccess())
        // //.addCollectionConverterFrom(new
        // ProviderWorkingHoursConversionAccess())
        // .addCollectionConverterFrom(new ProviderDishesConversionAccess())
        // .addEntityConverterFrom(new ProviderContactConversionAccess())
        // .addEntityConverterFrom(new
        // ProviderAddressConversionAccess()).build());

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

        // ===============================================================================================

        // modelMapper.addMappings(new PropertyMap<ProviderEntity, Provider>() {
        // @Override
        // protected void configure() {
        // when(Conditions.isNotNull()).map(source.getAddress()).setAddress(null);
        // when(Conditions.isNotNull()).map(source.getContact()).setContact(null);
        // }
        // });

        modelMapper.addMappings(new PropertyMap<DishEntity, Dish>() {

            @Override
            protected void configure() {
                // mapping when source and dest properties has different types
                map(source.getDishGeneres()).setGeneres(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<DishGenereEntity, Genere>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setLastModificationId(source.getLastModificationId());
                map().setName(source.getGenere().getName());
            }
        });

        return modelMapper;
    }

}
