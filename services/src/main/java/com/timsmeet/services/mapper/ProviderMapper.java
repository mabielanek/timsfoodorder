package com.timsmeet.services.mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import com.timsmeet.dto.AdditionalCost;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.AdditionalCostEntity;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderVacationEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.services.find.FindEntityWithIdAccessor;

public class ProviderMapper implements Mapper<Provider, ProviderEntity> {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private VacationMapper vacationMapper;

    @Autowired
    private WorkingHourMapper workingHourMapper;

    @Autowired
    private AdditionalCostMapper additionalCostMapper;

    @Autowired
    private FindEntityWithIdAccessor<ProviderVacationEntity> providerVacationFind;

    @Autowired
    private FindEntityWithIdAccessor<ProviderWorkingHourEntity> providerWorkingHourFind;

    @Autowired
    private FindEntityWithIdAccessor<AdditionalCostEntity> additionalCostFind;

    @Override
    public void map(Provider source, ProviderEntity target) {

        if (source.getLastModificationId() != null) {
            target.setLastModificationId(source.getLastModificationId());
        }
        target.setName(source.getName());
        target.setStatus(source.getStatus());

        if (source.getAddress() != null) {
            if (target.getAddress() == null) {
                target.setAddress(new AddressEntity());
            }
            addressMapper.map(source.getAddress(), target.getAddress());
        }
        if (source.getContact() != null) {
            if (target.getContact() == null) {
                target.setContact(new ContactEntity());
            }
            contactMapper.map(source.getContact(), target.getContact());
        }

        if (source.getVacations() != null) {
            for (Vacation vacation : source.getVacations()) {
                if (DtoStateHelper.isDeleted(vacation)) {
                    ProviderVacationEntity deletedVacation = providerVacationFind.findById(target.getVacations(), vacation.getId());
                    if (deletedVacation != null) {
                        target.removeVacation(deletedVacation);
                    }
                } else {
                    ProviderVacationEntity vacationEntity = existingOrNewVacationEntity(target, vacation);
                    vacationMapper.map(vacation, vacationEntity);
                }
            }
        }

        if (source.getWorkingHours() != null) {
            for (WorkingHour workingHour : source.getWorkingHours()) {
                if (DtoStateHelper.isDeleted(workingHour)) {
                    ProviderWorkingHourEntity deletedWorkingHour = providerWorkingHourFind.findById(target.getWorkingHours(),
                            workingHour.getId());
                    if (deletedWorkingHour != null) {
                        target.removeWorkingHour(deletedWorkingHour);
                    }
                } else {
                    ProviderWorkingHourEntity workingHourEntity = existingOrNewWorkingHourEntity(target, workingHour);
                    workingHourMapper.map(workingHour, workingHourEntity);
                }
            }
        }

        if(source.getAdditionalCosts() != null) {
            for(AdditionalCost additionalCost : source.getAdditionalCosts()) {
                if(DtoStateHelper.isDeleted(additionalCost)) {
                    AdditionalCostEntity deletedAdditionalCost = additionalCostFind.findById(target.getAdditionalCosts(), additionalCost.getId());
                    if(deletedAdditionalCost != null) {
                        target.removeAdditionalCost(deletedAdditionalCost);
                    }
                } else {
                    AdditionalCostEntity additionalCostEntity = existingOrNewAdditionalCostEntity(target, additionalCost);
                    additionalCostMapper.map(additionalCost, additionalCostEntity);
                }
            }
        }
    }

    private ProviderVacationEntity existingOrNewVacationEntity(ProviderEntity providerEntity, Vacation vacation) {
        if (DtoStateHelper.isNew(vacation)) {
            ProviderVacationEntity providerVacationEntity = new ProviderVacationEntity();
            providerEntity.addVacation(providerVacationEntity);
            return providerVacationEntity;
        }
        return providerVacationFind.findById(providerEntity.getVacations(), vacation.getId());
    }

    private ProviderWorkingHourEntity existingOrNewWorkingHourEntity(ProviderEntity providerEntity, WorkingHour workingHour) {
        if (DtoStateHelper.isNew(workingHour)) {
            ProviderWorkingHourEntity providerWorkingHourEntity = new ProviderWorkingHourEntity();
            providerEntity.addWorkingHour(providerWorkingHourEntity);
            return providerWorkingHourEntity;
        }
        return providerWorkingHourFind.findById(providerEntity.getWorkingHours(), workingHour.getId());
    }

    private AdditionalCostEntity existingOrNewAdditionalCostEntity(ProviderEntity providerEntity, AdditionalCost additionalCost) {
        if(DtoStateHelper.isNew(additionalCost)) {
            AdditionalCostEntity additionalCostEntity = new AdditionalCostEntity();
            providerEntity.addAdditionalCost(additionalCostEntity);
            return additionalCostEntity;
        }
        return additionalCostFind.findById(providerEntity.getAdditionalCosts(), additionalCost.getId());
    }

    @Override
    public void inverseMap(ProviderEntity source, Provider target) {
        target.setId(source.getId());
        target.setLastModificationId(source.getLastModificationId());
        target.setName(source.getName());
        target.setStatus(source.getStatus());

        if (source.getAddress() != null) {
            if (target.getContact() == null) {
                target.setAddress(new Address());
            }
            addressMapper.inverseMap(source.getAddress(), target.getAddress());
        }
        if (source.getContact() != null) {
            if (target.getContact() == null) {
                target.setContact(new Contact());
            }
            contactMapper.inverseMap(source.getContact(), target.getContact());
        }

        if (HibernateMapperHelper.isCollectionInitialized(source.getVacations())) {
            List<Vacation> vacations = Lists.newArrayListWithCapacity(source.getVacations().size());
            for (ProviderVacationEntity dbVacation : source.getVacations()) {
                Vacation vacation = new Vacation();
                vacationMapper.inverseMap(dbVacation, vacation);
                vacations.add(vacation);
            }
            target.setVacations(vacations);
        }

        if (HibernateMapperHelper.isCollectionInitialized(source.getWorkingHours())) {
            List<WorkingHour> workingHours = Lists.newArrayListWithCapacity(source.getWorkingHours().size());
            for (ProviderWorkingHourEntity dbWorkingHour : source.getWorkingHours()) {
                WorkingHour workingHour = new WorkingHour();
                workingHourMapper.inverseMap(dbWorkingHour, workingHour);
                workingHours.add(workingHour);
            }
            target.setWorkingHours(workingHours);
        }

        if(HibernateMapperHelper.isCollectionInitialized(source.getAdditionalCosts())) {
            List<AdditionalCost> additionalCosts = Lists.newArrayListWithCapacity(source.getAdditionalCosts().size());
            for(AdditionalCostEntity dbAdditionalCost : source.getAdditionalCosts()) {
                AdditionalCost additionalCost = new AdditionalCost();
                additionalCostMapper.inverseMap(dbAdditionalCost, additionalCost);
                additionalCosts.add(additionalCost);
            }
            target.setAdditionalCosts(additionalCosts);
        }

    }
}
