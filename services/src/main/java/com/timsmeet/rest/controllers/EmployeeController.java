package com.timsmeet.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timsmeet.dto.Employee;
import com.timsmeet.services.EmployeeService;

@Controller
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	  @Autowired
	  private EmployeeService empoloyeeService;

	  @RequestMapping(method = RequestMethod.GET)
	  @ResponseBody  
	  public List<Employee> getEmpolyees(@RequestParam(required = true) String companyId) {
	    return empoloyeeService.readEmpoyees(Long.valueOf(companyId));
	  }

}
