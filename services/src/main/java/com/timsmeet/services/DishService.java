package com.timsmeet.services;

import java.util.List;

import com.timsmeet.dto.Dish;

public interface DishService {

    List<Dish> readDishes(Long providerId, String[] embeded);

    List<Dish> saveDishes(Long providerId, List<Dish> dishes);
}
