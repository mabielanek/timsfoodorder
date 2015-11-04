package com.timsmeet.rest.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.timsmeet.dto.FoodOrder;
import com.timsmeet.persistance.enums.FoodOrderStatus;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.services.FoodOrderService;

@Controller
@RequestMapping(value = Endpoint.FOOD_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<FoodOrder> getFoodOrders(@RequestParam(required = false) String orderStatus, @RequestParam(required = false) Timestamp orderTime, @RequestParam(required = false) String embeded) {
        String[] embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded)).toArray(new String[0]);
        FoodOrderStatus foodOrderStatus = FoodOrderStatus.forCode(orderStatus);
        return foodOrderService.readFoodOrders(foodOrderStatus, orderTime, embededFields);
    }

    @RequestMapping(method = RequestMethod.GET, value = Endpoint.FOOD_ORDER_ID_PARAM)
    @ResponseBody
    public FoodOrder getFoodOrder(@PathVariable Long foodOrderId, @RequestParam(required = false) String embeded) {
        String[] embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded)).toArray(new String[0]);
        return foodOrderService.readFoodOrder(foodOrderId, embededFields);
    }


}
