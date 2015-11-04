package com.timsmeet.services;

import java.sql.Timestamp;
import java.util.List;

import com.timsmeet.dto.FoodOrder;
import com.timsmeet.persistance.enums.FoodOrderStatus;

public interface FoodOrderService {

    List<FoodOrder> readFoodOrders(FoodOrderStatus foodOrderStatus, Timestamp orderTime, String[] embeded);
    
    FoodOrder readFoodOrder(Long fooldOrderId, String[] embeded);
}
