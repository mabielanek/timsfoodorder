package com.timsmeet.services;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.timsmeet.services.spring.ServicesConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class})
@ActiveProfiles("test")
//@Transactional
//@TransactionConfiguration(defaultRollback = true)
@Ignore
public class BaseServiceTest {
  
  
//  @Autowired 
//  private CompanyService companyService;
//  @Autowired
//  private ServiceLocationService serviceLocationService;

  
//  @Test
//  public void sampleTest() {
//    CompanyEntity company = companyService.createCompany("company name", ActivityStatus.ACTIVE);
//    Assert.assertNotNull("company id should be generated", company.getId());
//    
//    CompanyWorkingHourEntity origWorkingHour = new CompanyWorkingHourEntityBuilder().weekDay(WeekDay.MONDAY).startTime(new Date()).endTime(new Date()).build();
//    AddressEntity origCompanyAddress = new AddressEntityBuilder().address1("xxx").address2("yyy").city("RZE").displayIndex(0).status(ActivityStatus.ACTIVE).build();
//    CompanyEntity origCompany = new CompanyEntityBuilder().name("Company1").status(ActivityStatus.ACTIVE).addWorkingHour(origWorkingHour).address(origCompanyAddress).build();
//    
//    origCompany = companyService.save(origCompany);
//    
//    CompanyEntity savedCompany = companyService.readCompany(origCompany.getId(), null);
//    AddressEntity savedAddress = savedCompany.getAddress();
//    savedCompany.getWorkingHours().size();
//    CompanyWorkingHourEntity savedWorkingHour = savedCompany.getWorkingHours().get(0);
//
//    CompanyWorkingHourEntity modWorkingHour = new CompanyWorkingHourEntityBuilder().weekDay(WeekDay.WEDNESDAY).startTime(new Date()).endTime(new Date()).build();
//    AddressEntity modCompanyAddress = new AddressEntityBuilder().address1("aaa").address2("bbb").city("OPK").displayIndex(0).status(ActivityStatus.INACTIVE).build();
//    origCompany.setAddress(modCompanyAddress);
//    origCompany.removeWorkingHour(origCompany.getWorkingHours().get(0));
//    origCompany.addWorkingHour(modWorkingHour);
//    origCompany.setStatus(ActivityStatus.INACTIVE);
//    
//    CompanyEntity savedCompany2 = companyService.save(origCompany);
//    
//    savedCompany2 = companyService.readCompany(origCompany.getId(), null);
//    AddressEntity savedAddress2 = savedCompany2.getAddress();
//    savedCompany2.getWorkingHours().size();
//    CompanyWorkingHourEntity savedWorkingHour2 = savedCompany2.getWorkingHours().get(0);
//    
//    int i = 0;
//
//    
//  }
  
//  @Test 
//  public void serviceLocationTest() {
//    
//    CompanyEntity company = companyService.createCompany("company name", ActivityStatus.ACTIVE);
//    
//    serviceLocationService.createServiceLocation(company, "location name", ActivityStatus.ACTIVE);
//    
//    List<ServiceLocationEntity> locations = serviceLocationRepository.findServiceLocationsByCompanyId(company.getId());
//    
//    Assert.isTrue(locations.size() == 1, "Locations size should be 1.");
//    
//  }
}