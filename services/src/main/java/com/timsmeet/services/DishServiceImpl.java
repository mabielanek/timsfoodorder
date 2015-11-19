package com.timsmeet.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Provider;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
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
    public List<Dish> readDishes(Long providerId, String[] embeded) {
        Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
                : Collections.<String> emptySet();
        List<DishEntity> dbDishes = dishRepository.findByProviderId(providerId);

        if (dbDishes == null || dbDishes.size() == 0) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_READ_DISHES_NOT_FOUND, providerId));
        }

        List<Dish> result = Lists.newArrayListWithCapacity(dbDishes.size());
        for(DishEntity dbDish : dbDishes) {
            if (embededSet.contains("dishComponents") || embededSet.contains("dishComponents.dishElements")) {
                Hibernate.initialize(dbDish.getDishComponents());
                
                if(embededSet.contains("dishComponents.dishElements")) {
                    for(DishComponentEntity dbDishComponent : dbDish.getDishComponents()) {
                        Hibernate.initialize(dbDishComponent.getDishElements());
                    }
                }
            }

            if (embededSet.contains("dishGeneres")) {
                Hibernate.initialize(dbDish.getDishGneres());
                for(DishGenereEntity dbDishGenere : dbDish.getDishGneres()) {
                    Hibernate.initialize(dbDishGenere.getGenere());
                }
            }
            
            Dish dish = new Dish();
            modelMapper.map(dbDish, dish);
            result.add(dish);
        }
        return result;
    }

    @Override
    public List<Dish> saveDishes(Long providerId, List<Dish> dishes) {
        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider != null) {
            Provider provider = new Provider();
            modelMapper.map(dbProvider, provider);
            provider.setDishes(dishes);
            Hibernate.initialize(dbProvider.getDishes());
            modelMapper.map(provider, dbProvider);
            dbProvider = providerRepository.save(dbProvider);
        } else {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_SAVE_DISHES_NOT_FOUND, providerId));
        }
        
        List<Dish> savedDishes = Lists.newArrayListWithCapacity(dbProvider.getDishes().size());
        for(DishEntity dbDish : dbProvider.getDishes()) {
            Dish dish = new Dish();
            modelMapper.map(dbDish, dish);
            savedDishes.add(dish);
        }
        return savedDishes;
    }

}
