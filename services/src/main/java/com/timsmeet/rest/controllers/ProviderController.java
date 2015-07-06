package com.timsmeet.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.timsmeet.dto.ErrorCollection;
import com.timsmeet.dto.ErrorInfo;
import com.timsmeet.dto.Provider;
import com.timsmeet.services.NotFoundException;
import com.timsmeet.services.ProviderService;

@Controller
@RequestMapping(value = "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProviderController {

  @Autowired
  private ProviderService providerService;

  private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<Provider> readProviders() {
    return providerService.readProviders();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{providerId}")
  @ResponseBody
  public Provider getProvider(@PathVariable Long providerId, @RequestParam(required = false) String embeded) {
    String[] embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded)).toArray(new String[0]);
    return providerService.readProvider(providerId, embededFields);
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

  @RequestMapping(method = RequestMethod.DELETE, value = "/{providerId}")
  @ResponseBody
  public void deleteProvider(@PathVariable Long providerId) {
	  providerService.delete(providerId);
  }


  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorCollection handleTodoNotFoundException(NotFoundException ex) {
	  ErrorCollection errors = new ErrorCollection();
	  ErrorInfo errorInfo = new ErrorInfo();
	  errorInfo.setErrorCode("0001");
	  errorInfo.setMessage(ex.getMessage());
	  errors.setErrors(Lists.newArrayList(errorInfo));
	  return errors;
  }

}
