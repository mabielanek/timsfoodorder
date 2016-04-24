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

import com.timsmeet.dto.Organization;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.rest.controllers.util.RestParamHelper;
import com.timsmeet.services.OrganizationService;

@Controller
@RequestMapping(value = Endpoint.ORGANIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> readOrganizations(
            @RequestParam(required = false) String sort, 
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage) {
        
        RestParamHelper paramHelper = new RestParamHelper()
            .specifyPaging(page, perPage)
            .withSorting(sort)
            .allowSortBy(OrganizationService.ALLOW_SORT_ID, OrganizationService.ALLOW_SORT_NAME);
        
        return organizationService.readOrganizations(paramHelper.buildPageable());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Organization saveOrganization(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = Endpoint.ORGANIZATION_ID_PARAM)
    @ResponseBody
    public void deleteOrganization(@PathVariable Long organizationId) {
        organizationService.delete(organizationId);
    }

}
