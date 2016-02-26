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
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_READ_DISH_NOT_FOUND, providerId));
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
    public Dish saveDish(Long providerId, Dish dish) {
        ProviderEntity dbProvider = providerRepository.findOne(providerId);
        if(dbProvider == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_SAVE_DISH_NOT_FOUND, providerId));
        }
        DishEntity dbDish =
                (dish.getId() == null) ? new DishEntity() : dishRepository.findOne(dish.getId());
        if(dbDish == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.DISH_TO_SAVE_NOT_FOUND, dish.getId()));
        }

        initializeDishDependencies(dbDish, true, true, true);
        modelMapper.map(dish, dbDish);
        dbDish.setProvider(dbProvider);
        dbDish = dishRepository.save(dbDish);
        modelMapper.map(dbDish, dish);
        return dish;
    }

    @Override
    @Transactional
    public void deleteDish(Long providerId, Long dishId) {
        ProviderEntity dbProvider = providerRepository.findOne(providerId);

        if (dbProvider == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.PROVIDER_TO_DELETE_DISH_NOT_FOUND, providerId));
        }

        DishEntity dbDish = dishRepository.findOne(dishId);

        if(dbDish == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.DISH_TO_DELETE_NOT_FOUND, dishId));
        }

        dishRepository.delete(dbDish);
    }

    private void initializeDishDependencies(Collection<DishEntity> dbDishes, boolean initializeDishComponents, boolean initializeDishElements, boolean initializeDishGeneres) {
        for(DishEntity dbDish : dbDishes) {
            initializeDishDependencies(dbDish, initializeDishComponents, initializeDishElements, initializeDishGeneres);
        }
    }

    private void initializeDishDependencies(DishEntity dbDish, boolean initializeDishComponents, boolean initializeDishElements,
            boolean initializeDishGeneres) {
        if (initializeDishComponents) {
            Hibernate.initialize(dbDish.getDishComponents());

            if(initializeDishElements) {
                for(DishComponentEntity dbDishComponent : dbDish.getDishComponents()) {
                    Hibernate.initialize(dbDishComponent.getDishElements());
                }
            }
        }

        if (initializeDishGeneres) {
            Hibernate.initialize(dbDish.getDishGeneres());
            for(DishGenereEntity dbDishGenere : dbDish.getDishGeneres()) {
                Hibernate.initialize(dbDishGenere.getGenere());
            }
        }
    }


}
