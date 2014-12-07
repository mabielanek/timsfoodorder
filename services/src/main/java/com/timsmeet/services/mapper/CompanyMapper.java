package com.timsmeet.services.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Company;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.CompanyEntity;
import com.timsmeet.persistance.model.CompanyVacationEntity;
import com.timsmeet.persistance.model.CompanyWorkingHourEntity;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.services.find.entity.VacationsFind;
import com.timsmeet.services.find.entity.WorkingHoursFind;

@Service
public class CompanyMapper implements Mapper<Company, CompanyEntity> {
	
	@Autowired
	private AddressMapper addressMapper;
	
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
	public void map(Company source, CompanyEntity target) {

		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setName(source.getName());
		target.setStatus(source.getStatus());

		if(source.getAddress() != null) {
			if(target.getAddress() == null) {
				target.setAddress(new AddressEntity());
			}
			addressMapper.map(source.getAddress(), target.getAddress());
		}
		if(source.getContact() != null) {
			if(target.getContact() == null) {
				target.setContact(new ContactEntity());
			}
			contactMapper.map(source.getContact(), target.getContact());
		}
		
		if(source.getVacations() != null) {
			for(Vacation vacation : source.getVacations()) {
				if(DtoStateHelper.isDeleted(vacation)) {
					CompanyVacationEntity deletedVacation = vacationsFind.findCompanyVacationById(target, vacation.getId());
					if(deletedVacation != null) {
						target.removeVacation(deletedVacation);
					}
				} else {
					CompanyVacationEntity vacationEntity = existingOrNewVacationEntity(target, vacation);
					vacationMapper.map(vacation, vacationEntity);
				}
			}
		}
		
		if(source.getWorkingHours() != null) {
			for(WorkingHour workingHour : source.getWorkingHours()) {
				if(DtoStateHelper.isDeleted(workingHour)) {
					CompanyWorkingHourEntity deletedWorkingHour = workingHoursFind.findCompanyWorikingHourById(target, workingHour.getId()); 
					if(deletedWorkingHour != null) {
						target.removeWorkingHour(deletedWorkingHour);
					}
				} else {
					CompanyWorkingHourEntity workingHourEntity = existingOrNewWorkingHourEntity(target, workingHour);
					workingHourMapper.map(workingHour, workingHourEntity);
				}
			}
		}

		
	}


	private CompanyVacationEntity existingOrNewVacationEntity(CompanyEntity companyEntity, Vacation vacation) {
		if(DtoStateHelper.isNew(vacation)) {
			CompanyVacationEntity companyVacationEntity = new CompanyVacationEntity();
			companyEntity.addVacation(companyVacationEntity);
			return companyVacationEntity;
		}
		return vacationsFind.findCompanyVacationById(companyEntity, vacation.getId());
	}
	
	private CompanyWorkingHourEntity existingOrNewWorkingHourEntity(CompanyEntity companyEntity, WorkingHour workingHour) {
		if(DtoStateHelper.isNew(workingHour)) {
			CompanyWorkingHourEntity companyWorkingHourEntity = new CompanyWorkingHourEntity();
			companyEntity.addWorkingHour(companyWorkingHourEntity);
			return companyWorkingHourEntity;
		}
		return workingHoursFind.findCompanyWorikingHourById(companyEntity, workingHour.getId());
	}

	@Override
	public void inverseMap(CompanyEntity source, Company target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setName(source.getName());
		target.setStatus(source.getStatus());
		
		if(source.getAddress() != null) {
			if(target.getContact() == null) {
				target.setAddress(new Address());
			}
			addressMapper.inverseMap(source.getAddress(), target.getAddress());
		}
		if(source.getContact() != null) {
			if(target.getContact() == null) {
				target.setContact(new Contact());
			}
			contactMapper.inverseMap(source.getContact(), target.getContact());
		}
		
		if(HibernateMapperHelper.isCollectionInitialized(source.getVacations())) {
			List<Vacation> vacations = Lists.newArrayListWithCapacity(source.getVacations().size());
			for(CompanyVacationEntity dbVacation : source.getVacations()) {
				Vacation vacation = new Vacation();
				vacationMapper.inverseMap(dbVacation, vacation);
				vacations.add(vacation);
			}
			target.setVacations(vacations);
		}
		
		if(HibernateMapperHelper.isCollectionInitialized(source.getWorkingHours())) {
			List<WorkingHour> workingHours = Lists.newArrayListWithCapacity(source.getWorkingHours().size());
			for(CompanyWorkingHourEntity dbWorkingHour : source.getWorkingHours()) {
				WorkingHour workingHour = new WorkingHour();
				workingHourMapper.inverseMap(dbWorkingHour, workingHour);
				workingHours.add(workingHour);
			}
			target.setWorkingHours(workingHours);
		}
		
	}

}
