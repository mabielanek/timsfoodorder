package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Dish;
import com.timsmeet.persistance.model.DishEntity;

@Service
public class DishMapper implements Mapper<Dish, DishEntity> {

	@Override
	public void map(Dish source, DishEntity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inverseMap(DishEntity source, Dish target) {
		// TODO Auto-generated method stub
		
	}

}
