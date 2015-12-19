package com.timsmeet.services;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Provider;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.repositories.DishRepository;
import com.timsmeet.persistance.repositories.ProviderRepository;

@Service
public class DishServiceImpl implements DishService {

    @Autowired 
    private ProviderRepository providerRepository;
    
    @Autowired
    private DishRepository dishRepository;

    @Autowired 
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<Dish> readDishes(Long providerId, Boolean onlyActive, String[] embeded, Pageable pageable) {
        Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
                : Collections.<String> emptySet();
        
        
        Page<DishEntity> dbDishes = null;
        if(onlyActive != null && onlyActive) {
            dbDishes = dishRepository.findByProviderIdAndStatus(providerId, ActivityStatus.ACTIVE.getCode(), pageable);
        } else {
            dbDishes = dishRepository.findByProviderId(providerId, pageable);
        }

        if (dbDishes == null || !dbDishes.hasContent()) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_READ_DISHES_NOT_FOUND, providerId));
        }

        initializeDishDependencies(dbDishes.getContent(), 
                embededSet.contains(DishService.EMBED_DISH_COMPONENTS) || embededSet.contains(DishService.EMBED_DISH_COMPONENT_ELEMENTS), 
                embededSet.contains(DishService.EMBED_DISH_COMPONENT_ELEMENTS),
                embededSet.contains(DishService.EMBED_DISH_GENERES));

        Type listType = new TypeToken<List<Dish>>(){}.getType();
        List<Dish> result = modelMapper.map(dbDishes.getContent(), listType);
        return result;
    }
    
    @Override
    @Transactional
    public List<Dish> saveDishes(Long providerId, List<Dish> dishes) {
        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider != null) {
            Provider provider = new Provider();
            modelMapper.map(dbProvider, provider);
            provider.setDishes(dishes);
            initializeDishDependencies(dbProvider.getDishes(), true, true, true);
            modelMapper.map(provider, dbProvider);
            dbProvider = providerRepository.save(dbProvider);
        } else {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_SAVE_DISHES_NOT_FOUND, providerId));
        }
        
        Type listType = new TypeToken<List<Dish>>(){}.getType();
        List<Dish> savedDishes = modelMapper.map(dbProvider.getDishes(), listType);
        return savedDishes;
    }

    private void initializeDishDependencies(Collection<DishEntity> dbDishes, boolean initializeDishComponents, boolean initializeDishElements, boolean initializeDishGeneres) {
        for(DishEntity dbDish : dbDishes) {
            if (initializeDishComponents) {
                Hibernate.initialize(dbDish.getDishComponents());
                
                if(initializeDishElements) {
                    for(DishComponentEntity dbDishComponent : dbDish.getDishComponents()) {
                        Hibernate.initialize(dbDishComponent.getDishElements());
                    }
                }
            }

            if (initializeDishGeneres) {
                Hibernate.initialize(dbDish.getDishGneres());
                for(DishGenereEntity dbDishGenere : dbDish.getDishGneres()) {
                    Hibernate.initialize(dbDishGenere.getGenere());
                }
            }
        }        
    }

    
}
