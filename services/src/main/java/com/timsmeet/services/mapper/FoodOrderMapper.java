package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.FoodOrder;
import com.timsmeet.persistance.model.FoodOrderEntity;

@Service
public class FoodOrderMapper implements Mapper<FoodOrder, FoodOrderEntity> {

    @Override
    public void map(FoodOrder source, FoodOrderEntity target) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inverseMap(FoodOrderEntity source, FoodOrder target) {
        // TODO Auto-generated method stub
        
    }

}
