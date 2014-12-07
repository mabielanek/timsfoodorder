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
import com.timsmeet.dto.Company;
import com.timsmeet.dto.ErrorCollection;
import com.timsmeet.dto.ErrorInfo;
import com.timsmeet.services.CompanyService;
import com.timsmeet.services.NotFoundException;

@Controller
@RequestMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

  @Autowired
  private CompanyService companyService;
  
  private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<Company> readCompanies() {
    return companyService.readCompanies();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{companyId}")
  @ResponseBody  
  public Company getCompany(@PathVariable Long companyId, @RequestParam(required = false) String embeded) {
    String[] embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded)).toArray(new String[0]);
    return companyService.readCompany(companyId, embededFields);
  }
  
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public Company saveCompany(@RequestBody Company company) {
	  return companyService.save(company);
  }
  
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public Company updateCompany(@RequestBody Company company) {
	  return companyService.save(company);
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "/{companyId}")
  @ResponseBody
  public void deleteCompany(@PathVariable Long companyId) {
	  companyService.delete(companyId);
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
  
//  @RequestMapping(method = RequestMethod.POST)
//  @ResponseBody
//  public CompanyEntity create(@RequestBody CompanyEntity company) {
//      return companyService.createCompany("ACME company", ActivityStatus.ACTIVE);
//  }
}
