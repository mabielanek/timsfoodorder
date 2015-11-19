package com.timsmeet.services;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.timsmeet.dto.FoodOrder;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.enums.FoodOrderStatus;
import com.timsmeet.persistance.model.FoodOrderEntity;
import com.timsmeet.persistance.model.OrderItemEntity;
import com.timsmeet.persistance.model.OrderSubItemEntity;
import com.timsmeet.persistance.repositories.FoodOrderRepository;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {

    @Autowired 
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public FoodOrder readFoodOrder(Long foodOrderId, String[] embeded) {
        FoodOrderEntity dbFoodOrder = foodOrderRepository.findOne(foodOrderId);

        if (dbFoodOrder == null) {
            throw new NotFoundException(ErrorBuilder.build(ErrorDescribedEnum.FOOD_ORDER_TO_READ_NOT_FOUND, foodOrderId));
        }
        
        initEmbeded(dbFoodOrder, embeded);
        FoodOrder foodOrder = new FoodOrder();
        modelMapper.map(dbFoodOrder, foodOrder);
        return foodOrder;
        
    }

    @Override
    public List<FoodOrder> readFoodOrders(FoodOrderStatus foodOrderStatus, Timestamp orderTime, String[] embeded) {
        Iterable<FoodOrderEntity> dbFoodOrders = null;
        if(foodOrderStatus != null && orderTime != null) {
            dbFoodOrders = foodOrderRepository.findByOrderStatusAndOrderTimeLessThan(foodOrderStatus.getCode(), orderTime);
        } else if(foodOrderStatus != null) {
            dbFoodOrders = foodOrderRepository.findByOrderStatus(foodOrderStatus.getCode());
        } else if(orderTime != null) {
            dbFoodOrders = foodOrderRepository.findByOrderTimeLessThan(orderTime);
        } else {
            dbFoodOrders = foodOrderRepository.findAll();
        }
        
        List<FoodOrder> result = Lists.newArrayList();
        for(FoodOrderEntity dbFoodOrder : dbFoodOrders) {
            initEmbeded(dbFoodOrder, embeded);
            
            FoodOrder foodOrder = new FoodOrder();
            modelMapper.map(dbFoodOrder, foodOrder);
            result.add(foodOrder);
        }
        
        return result;
    }

    private void initEmbeded(FoodOrderEntity dbFoodOrder, String[] embeded) {
        Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
                : Collections.<String> emptySet();
        
        if (embededSet.contains("person")) {
            Hibernate.initialize(dbFoodOrder.getPerson());
        }
        if (embededSet.contains("provider")) {
            Hibernate.initialize(dbFoodOrder.getProvider());
        }
        if (embededSet.contains("orderItems")) {
            Hibernate.initialize(dbFoodOrder.getOrderItems());
            
            if(embededSet.contains("orderItems.dish")) {
                for(OrderItemEntity dbOrderItem : dbFoodOrder.getOrderItems()) {
                    Hibernate.initialize(dbOrderItem.getDish());
                }
            }
            if(embededSet.contains("orderItems.person")) {
                for(OrderItemEntity dbOrderItem : dbFoodOrder.getOrderItems()) {
                    Hibernate.initialize(dbOrderItem.getPerson());
                }
            }
            if(embededSet.contains("orderItems.orderSubItem")) {
                for(OrderItemEntity dbOrderItem : dbFoodOrder.getOrderItems()) {
                    Hibernate.initialize(dbOrderItem.getOrderSubItems());
                    
                    if(embededSet.contains("orderItems.orderSubItem.dishElement")) {
                        for(OrderSubItemEntity dbOrderSubItem : dbOrderItem.getOrderSubItems()) {
                            Hibernate.initialize(dbOrderSubItem.getDishElement());
                        }
                    }
                    if(embededSet.contains("orderItems.orderSubItem.dishComponent")) {
                        for(OrderSubItemEntity dbOrderSubItem : dbOrderItem.getOrderSubItems()) {
                            Hibernate.initialize(dbOrderSubItem.getDishComponent());
                        }
                    }
                }
            }
        }
    }

}
