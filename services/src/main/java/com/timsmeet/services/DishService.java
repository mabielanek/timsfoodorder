package com.timsmeet.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.timsmeet.dto.Dish;

public interface DishService {

    public final static String EMBED_DISH_COMPONENTS = "dishComponents";
    public final static String EMBED_DISH_COMPONENT_ELEMENTS = "dishComponents.dishElements";
    public final static String EMBED_DISH_GENERES = "dishGeneres";

    public final static String ALLOW_SORT_ID = "id";
    public final static String ALLOW_SORT_NAME = "name";

    Dish saveDish(Long providerId, Dish dish);

    List<Dish> readDishes(Long providerId, Boolean onlyActive, String[] embeded, Pageable pageable);

    void deleteDish(Long providerId, Long dishId);
}
