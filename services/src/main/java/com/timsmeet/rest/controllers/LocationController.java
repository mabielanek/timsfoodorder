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

import com.timsmeet.dto.Location;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.rest.controllers.util.RestParamHelper;
import com.timsmeet.services.LocationService;

@Controller
@RequestMapping(value = Endpoint.ORGANIZATION + Endpoint.ORGANIZATION_ID_PARAM + Endpoint.LOCATION, produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    private LocationService locationService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getLocations(@PathVariable Long organizationId,
            @RequestParam(required = false) String sort, 
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage) {
        RestParamHelper paramHelper = new RestParamHelper().
                withSorting(sort).
                allowSortBy(LocationService.ALLOW_SORT_ID, LocationService.ALLOW_SORT_NAME);
        return locationService.readLocations(organizationId, paramHelper.buildPageable());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Location saveLocation(@PathVariable Long organizationId, @RequestBody Location location) {
        return locationService.save(organizationId, location);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Location updateLocation(@PathVariable Long organizationId, @RequestBody Location location) {
        return locationService.save(organizationId, location);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = Endpoint.LOCATION_ID_PARAM)
    @ResponseBody
    public void deleteLocation(@PathVariable Long organizationId, @PathVariable Long locationId) {
        locationService.delete(organizationId, locationId);
    }

}
