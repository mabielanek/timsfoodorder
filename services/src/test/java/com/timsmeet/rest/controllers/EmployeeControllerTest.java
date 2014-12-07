package com.timsmeet.rest.controllers;

import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.timsmeet.persistance.repositories.EmployeeRepository;
import com.timsmeet.services.EmployeeService;


@DatabaseSetup({"employee/ContactData.xml", "employee/PhoneData.xml", "employee/EmailData.xml", "employee/WebUrlData.xml", "employee/AddressData.xml", 
	"employee/CompanyData.xml", "employee/EmployeeData.xml", "employee/WorkinghourData.xml", "employee/VacationsData.xml"})
@DatabaseTearDown("ClearAllTables.xml")
public class EmployeeControllerTest extends BaseControllerTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	  @Test
	  //@ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
	  public void shouldFindAllEmplyeesForCompany() throws Exception {
	      mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("companyId", "-1001"))
	              .andDo(MockMvcResultHandlers.print())
	              .andExpect(MockMvcResultMatchers.status().isOk())
	              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	              .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
	  }

}
