package com.timsmeet.services;

import java.sql.Timestamp;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.timsmeet.dto.Address;
import com.timsmeet.dto.Company;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.WeekDay;

@Ignore
public class CompanyServiceTest extends BaseServiceTest {

  private Long companyId = null;
  
  @Autowired 
  private CompanyService companyService;
  
  @Before
  @Transactional
  public void prepareData() {
    Vacation vacation = new Vacation.Builder(new Timestamp(0), new Timestamp(0)).build();
    WorkingHour workingHour1 = new WorkingHour.Builder(WeekDay.MONDAY, new Timestamp(0), new Timestamp(0)).build();
    WorkingHour workingHour2 = new WorkingHour.Builder(WeekDay.TUESDAY, new Timestamp(0), new Timestamp(0)).build();
    Address companyAddress = new Address.Builder(ActivityStatus.ACTIVE).address1("xxx").address2("yyy").city("RZE").displayIndex(0).build();
    Company company = new Company.Builder("Company1", ActivityStatus.ACTIVE)
    	.address(companyAddress)
    	.vacations(Arrays.asList(vacation))
    	.workingHours(Arrays.asList(workingHour1, workingHour2))
    	.build();
    
    companyId = companyService.save(company).getId();
    
  }
  
  @Test
  public void readCompany() {
    Company entity = companyService.readCompany(companyId, null);
    
    Assert.assertNotNull("Should read company", entity);
    Assert.assertNull("Should not read working hours", entity.getWorkingHours());
  }
  
  @Test
  public void readCompanyWithWorkingHours() {
    Company entity = companyService.readCompany(companyId, new String[]{"workingHours"});
    
    Assert.assertNotNull("Should read company", entity);
    Assert.assertNotNull("Should read working hours", entity.getWorkingHours());
    Assert.assertNull("Should not read vacations", entity.getVacations());
  }

}
