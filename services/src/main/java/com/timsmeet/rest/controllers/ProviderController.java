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

import com.timsmeet.dto.Provider;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.rest.controllers.util.RestParamHelper;
import com.timsmeet.services.ProviderService;

@Controller
@RequestMapping(value = Endpoint.PROVIDER, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Provider> readProviders(
            @RequestParam(required = false) String sort, 
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage) {
        
        RestParamHelper paramHelper = new RestParamHelper()
            .specifyPaging(page, perPage)
            .withSorting(sort)
            .allowSortBy(ProviderService.ALLOW_SORT_ID, ProviderService.ALLOW_SORT_NAME);
        
        return providerService.readProviders(paramHelper.buildPageable());
    }

    @RequestMapping(method = RequestMethod.GET, value = Endpoint.PROVDER_ID_PARAM)
    @ResponseBody
    public Provider getProvider(@PathVariable Long providerId, @RequestParam(required = false) String embeded) {
        RestParamHelper paramHelper = new RestParamHelper().
                withEmbeded(embeded).
                allowEmbed(ProviderService.EMBED_ADDITIONAL_COSTS, 
                        ProviderService.EMBED_CONTACT_EMAILS, 
                        ProviderService.EMBED_CONTACT_PHONES,
                        ProviderService.EMBED_CONTACT_WEB_URLS, 
                        ProviderService.EMBED_VACATIONS, 
                        ProviderService.EMBED_WORKING_HOURS);
        
        return providerService.readProvider(providerId, paramHelper.buildEmbeded());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Provider saveProvider(@RequestBody Provider provider) {
        return providerService.save(provider);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Provider updateProvider(@RequestBody Provider provider) {
        return providerService.save(provider);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = Endpoint.PROVDER_ID_PARAM)
    @ResponseBody
    public void deleteProvider(@PathVariable Long providerId) {
        providerService.delete(providerId);
    }

}
