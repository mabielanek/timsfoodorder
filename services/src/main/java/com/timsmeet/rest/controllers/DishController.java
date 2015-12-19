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

import com.timsmeet.dto.Dish;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.rest.controllers.util.RestParamHelper;
import com.timsmeet.services.DishService;

@Controller
@RequestMapping(value = Endpoint.PROVIDER + Endpoint.PROVDER_ID_PARAM + Endpoint.DISH, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    @Autowired
    private DishService dishService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Dish> getDishes(@PathVariable Long providerId, 
            @RequestParam(required = false) String embeded, 
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Boolean onlyActive) {
        RestParamHelper paramHelper = new RestParamHelper().
                withEmbeded(embeded).
                allowEmbed(DishService.EMBED_DISH_COMPONENTS, 
                        DishService.EMBED_DISH_COMPONENT_ELEMENTS, 
                        DishService.EMBED_DISH_GENERES).
                withSorting(sort).
                allowSortBy(DishService.ALLOW_SORT_ID, DishService.ALLOW_SORT_NAME);
        return dishService.readDishes(providerId, onlyActive, paramHelper.buildEmbeded(), paramHelper.buildPageable());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Dish> saveDishes(@PathVariable Long providerId, @RequestBody List<Dish> dishes) {
        return dishService.saveDishes(providerId, dishes);
    }

}
