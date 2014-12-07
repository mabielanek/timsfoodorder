package com.timsmeet.services.find.entity;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.timsmeet.persistance.model.CompanyEntity;
import com.timsmeet.persistance.model.CompanyWorkingHourEntity;
import com.timsmeet.persistance.model.EmployeeEntity;
import com.timsmeet.persistance.model.EmployeeWorkingHourEntity;

@Service
public class WorkingHoursFind {

	public CompanyWorkingHourEntity findCompanyWorikingHourById(CompanyEntity company, final long workingHourId) {
		Collection<CompanyWorkingHourEntity> matchingWorkingHours =
				Collections2.filter(company.getWorkingHours(), new Predicate<CompanyWorkingHourEntity>() {
					@Override
					public boolean apply(CompanyWorkingHourEntity input) {
						return input.getId() == workingHourId;
					}
				});
		if(matchingWorkingHours.size() == 0) {
			throw new IllegalArgumentException("Existing working hour entity with key: " + workingHourId + " not found for company.");
		} else if(matchingWorkingHours.size() > 1) {
			throw new IllegalStateException("More than one working hour with key: " + workingHourId + " found in collection for company.");
		}
		return matchingWorkingHours.iterator().next();
	}

	public EmployeeWorkingHourEntity findEmployeeWorkingHourById(EmployeeEntity employee, final long workingHourId) {
		Collection<EmployeeWorkingHourEntity> matchingWorkingHours =
				Collections2.filter(employee.getWorkingHours(), new Predicate<EmployeeWorkingHourEntity>() {
					@Override
					public boolean apply(EmployeeWorkingHourEntity input) {
						return input.getId() == workingHourId;
					}
				});
		if(matchingWorkingHours.size() == 0) {
			throw new IllegalArgumentException("Existing working hour entity with key: " + workingHourId + " not found for company.");
		} else if(matchingWorkingHours.size() > 1) {
			throw new IllegalStateException("More than one working hour with key: " + workingHourId + " found in collection for company.");
		}
		return matchingWorkingHours.iterator().next();
	}

}
