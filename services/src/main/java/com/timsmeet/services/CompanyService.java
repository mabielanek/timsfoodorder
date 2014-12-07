package com.timsmeet.services;

import java.util.List;

import com.timsmeet.dto.Company;

public interface CompanyService {

  List<Company> readCompanies();
  Company save(Company company);
  Company readCompany(Long companyId, String[] embeded);
  void delete(Long companyId);
}
