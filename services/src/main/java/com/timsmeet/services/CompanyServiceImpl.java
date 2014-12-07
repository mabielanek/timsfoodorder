package com.timsmeet.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.timsmeet.dto.Company;
import com.timsmeet.persistance.model.CompanyEntity;
import com.timsmeet.persistance.repositories.AddressRepository;
import com.timsmeet.persistance.repositories.CompanyRepository;
import com.timsmeet.persistance.repositories.ContactRepository;
import com.timsmeet.services.mapper.CompanyMapper;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CompanyMapper companyMapper;


	@Override
	public List<Company> readCompanies() {
		List<CompanyEntity> dbCompanies = Lists.newArrayList(companyRepository.findAll());
	    List<Company> companies = Lists.newArrayListWithCapacity(dbCompanies.size());
	    for(CompanyEntity dbCompany : dbCompanies) {
	    	Company company = new Company();
	    	companyMapper.inverseMap(dbCompany, company);
	    	companies.add(company);
	    }
	    return companies;
	}

	@Override
	@Transactional
	public Company save(Company company) {
		CompanyEntity dbCompany = null;
		if(company.getId() == null) {
			dbCompany = new CompanyEntity();
		} else {
			dbCompany = companyRepository.findOne(company.getId());
		}
		
		if(dbCompany != null) {
			companyMapper.map(company, dbCompany);
			dbCompany = companyRepository.save(dbCompany);
		} else {
			throw new IllegalStateException("No Company to update"); //TODO proper handling of not found
		}
		
		Company savedCompany = new Company();
		companyMapper.inverseMap(dbCompany, savedCompany);
		return savedCompany;
	}

	@Override
	@Transactional
	public Company readCompany(Long companyId, String[] embeded) {
		Set<String> embededSet = embeded != null ? Sets.newHashSet(embeded)
				: Collections.<String> emptySet();
		CompanyEntity dbCompany = companyRepository.findOne(companyId);
		
		if(dbCompany == null) {
			throw new NotFoundException("Company with id: " + companyId + " not found.");
		} else {
			if (embededSet.contains("serviceTypes")) {
				Hibernate.initialize(dbCompany.getServiceTypes());
			}
			if (embededSet.contains("serviceLocations")) {
				Hibernate.initialize(dbCompany.getServiceLocations());
			}
			if (embededSet.contains("workingHours")) {
				Hibernate.initialize(dbCompany.getWorkingHours());
			}
			if (embededSet.contains("vacations")) {
				Hibernate.initialize(dbCompany.getVacations());
			}
			if (embededSet.contains("fields")) {
				Hibernate.initialize(dbCompany.getFields());
			}
			if (embededSet.contains("contact.emails")) {
				Hibernate.initialize(dbCompany.getContact().getEmails());
			}
			if (embededSet.contains("contact.phones")) {
				Hibernate.initialize(dbCompany.getContact().getPhones());
			}
			if (embededSet.contains("contact.webUrls")) {
				Hibernate.initialize(dbCompany.getContact().getWebUrls());
			}
		}

		Company company = new Company();
    	companyMapper.inverseMap(dbCompany, company);
    	return company;
	}

	@Override
	public void delete(Long companyId) {
		
		CompanyEntity dbCompany = companyRepository.findOne(companyId);
		
		if(dbCompany == null) {
			throw new NotFoundException("Company with id: " + companyId + " not found.");
		}

		companyRepository.delete(dbCompany);
		contactRepository.delete(dbCompany.getContact());
		addressRepository.delete(dbCompany.getAddress());
	}

}
