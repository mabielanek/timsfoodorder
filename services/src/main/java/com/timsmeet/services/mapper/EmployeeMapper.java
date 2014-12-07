package com.timsmeet.services.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Employee;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.EmployeeEntity;
import com.timsmeet.persistance.model.EmployeeVacationEntity;
import com.timsmeet.persistance.model.EmployeeWorkingHourEntity;
import com.timsmeet.services.find.entity.VacationsFind;
import com.timsmeet.services.find.entity.WorkingHoursFind;

@Service
public class EmployeeMapper implements Mapper<Employee, EmployeeEntity> {

	@Autowired
	private ContactMapper contactMapper;
	
	@Autowired
	private VacationMapper vacationMapper;
	
	@Autowired
	private WorkingHourMapper workingHourMapper;
	
	@Autowired
	private VacationsFind vacationsFind;
	
	@Autowired
	private WorkingHoursFind workingHoursFind;

	
	@Override
	public void map(Employee source, EmployeeEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setStatus(source.getStatus());
		target.setTitle(source.getTitle());
		target.setEmailPreferences(source.getEmailPreferences());
		
		if(source.getContact() != null) {
			if(target.getContact() == null) {
				target.setContact(new ContactEntity());
			}
			contactMapper.map(source.getContact(), target.getContact());
		}

		if(source.getVacations() != null) {
			for(Vacation vacation : source.getVacations()) {
				if(DtoStateHelper.isDeleted(vacation)) {
					EmployeeVacationEntity deletedVacation = vacationsFind.findEmployeeVacationById(target, vacation.getId());
					if(deletedVacation != null) {
						target.removeVacation(deletedVacation);
					}
				} else {
					EmployeeVacationEntity vacationEntity = existingOrNewVacationEntity(target, vacation);
					vacationMapper.map(vacation, vacationEntity);
				}
			}
		}
		
		if(source.getWorkingHours() != null) {
			for(WorkingHour workingHour : source.getWorkingHours()) {
				if(DtoStateHelper.isDeleted(workingHour)) {
					EmployeeWorkingHourEntity deletedWorkingHour = workingHoursFind.findEmployeeWorkingHourById(target, workingHour.getId());
					if(deletedWorkingHour != null) {
						target.removeWorkingHour(deletedWorkingHour);
					}
				} else {
					EmployeeWorkingHourEntity workingHourEntity = existingOrNewWorkingHourEntity(target, workingHour);
					workingHourMapper.map(workingHour, workingHourEntity);
				}
			}
		}
	}
	

	private EmployeeVacationEntity existingOrNewVacationEntity(EmployeeEntity employeeEntity, Vacation vacation) {
		if(DtoStateHelper.isNew(vacation)) {
			EmployeeVacationEntity employeeVacationEntity = new EmployeeVacationEntity();
			employeeEntity.addVacation(employeeVacationEntity);
			return employeeVacationEntity;
		}
		return vacationsFind.findEmployeeVacationById(employeeEntity, vacation.getId());
	}

	private EmployeeWorkingHourEntity existingOrNewWorkingHourEntity(EmployeeEntity employeeEntity, WorkingHour workingHour) {
		if(DtoStateHelper.isNew(workingHour)) {
			EmployeeWorkingHourEntity employeeWorkingHourEntity = new EmployeeWorkingHourEntity();
			employeeEntity.addWorkingHour(employeeWorkingHourEntity);
			return employeeWorkingHourEntity;
		}
		return workingHoursFind.findEmployeeWorkingHourById(employeeEntity, workingHour.getId());
	}


	@Override
	public void inverseMap(EmployeeEntity source, Employee target) {
		target.setEmailPreferences(source.getEmailPreferences());
		target.setFirstName(source.getFirstName());
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setLastName(source.getLastName());
		target.setStatus(source.getStatus());
		target.setTitle(source.getTitle());
		
		if(source.getContact() != null) {
			if(target.getContact() == null) {
				target.setContact(new Contact());
			}
			contactMapper.inverseMap(source.getContact(), target.getContact());
		}
		
		if(HibernateMapperHelper.isCollectionInitialized(source.getVacations())) {
			List<Vacation> vacations = Lists.newArrayListWithCapacity(source.getVacations().size());
			for(EmployeeVacationEntity dbVacation : source.getVacations()) {
				Vacation vacation = new Vacation();
				vacationMapper.inverseMap(dbVacation, vacation);
				vacations.add(vacation);
			}
			target.setVacations(vacations);
		}

		if(HibernateMapperHelper.isCollectionInitialized(source.getWorkingHours())) {
			List<WorkingHour> workingHours = Lists.newArrayListWithCapacity(source.getWorkingHours().size());
			for(EmployeeWorkingHourEntity dbWorkingHour : source.getWorkingHours()) {
				WorkingHour workingHour = new WorkingHour();
				workingHourMapper.inverseMap(dbWorkingHour, workingHour);
				workingHours.add(workingHour);
			}
			target.setWorkingHours(workingHours);
		}

	} 

}
