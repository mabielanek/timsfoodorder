package com.timsmeet.services.find.entity;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.timsmeet.persistance.model.CompanyEntity;
import com.timsmeet.persistance.model.CompanyVacationEntity;
import com.timsmeet.persistance.model.EmployeeEntity;
import com.timsmeet.persistance.model.EmployeeVacationEntity;

@Service
public class VacationsFind {

	public CompanyVacationEntity findCompanyVacationById(CompanyEntity company, final long vacationId) {
		Collection<CompanyVacationEntity> matchingVacations = Collections2.filter(company.getVacations(), new Predicate<CompanyVacationEntity>() {
			@Override
			public boolean apply(CompanyVacationEntity input) {
				return input.getId() == vacationId;
			}
		});
		if(matchingVacations.size() == 0) {
			throw new IllegalArgumentException("Existing vacation entity with key: " + vacationId + " not found for company.");
		} else if(matchingVacations.size() > 1) {
			throw new IllegalStateException("More than one vacation with key: " + vacationId + " found in collection for company.");
		}
		return matchingVacations.iterator().next();
	}

	public EmployeeVacationEntity findEmployeeVacationById(EmployeeEntity employee, final long vacationId) {
		Collection<EmployeeVacationEntity> matchingVacations = Collections2.filter(employee.getVacations(), new Predicate<EmployeeVacationEntity>() {
			@Override
			public boolean apply(EmployeeVacationEntity input) {
				return input.getId() == vacationId;
			}
		});
		if(matchingVacations.size() == 0) {
			throw new IllegalArgumentException("Existing vacation entity with key: " + vacationId + " not found for employee.");
		} else if(matchingVacations.size() > 1) {
			throw new IllegalStateException("More than one vacation with key: " + vacationId + " found in collection for employee.");
		}
		return matchingVacations.iterator().next();
	}

}
