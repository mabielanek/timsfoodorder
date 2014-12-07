package com.timsnet.persistance;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {PersistenceJPAConfig.class})
//@Transactional
//@TransactionConfiguration(defaultRollback = true)
public class BaseServiceTest {
  
//  @Autowired
//  CompanyRepository repository;
//  @Autowired
//  ServiceLocationRepository serviceLocationRepository;
//  
//  @Autowired 
//  private CompanyService companyService;
//  @Autowired
//  private ServiceLocationService serviceLocationService;
//
//  
//  @Test
//  public void sampleTest() {
//    CompanyEntity company = new CompanyEntity();
//    company.setName("first company");
//    company.setStatus(ActivityStatus.ACTIVE);
//    repository.save(company);
//  }
//  
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