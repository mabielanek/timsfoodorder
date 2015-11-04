package com.timsmeet.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.timsmeet.dto.Dish;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.services.DishService;

@Controller
@RequestMapping(value = Endpoint.PROVIDER + Endpoint.PROVDER_ID_PARAM + Endpoint.DISH, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    @Autowired
    private DishService dishService;

    private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Dish> getDishes(@PathVariable Long providerId, @RequestParam(required = false) String embeded) {
        String[] embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded)).toArray(new String[0]);
        return dishService.readDishes(providerId, embededFields);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Dish> saveDishes(@PathVariable Long providerId, @RequestBody List<Dish> dishes) {
        return dishService.saveDishes(providerId, dishes);
    }

}
