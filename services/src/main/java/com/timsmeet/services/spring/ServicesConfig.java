package com.timsmeet.services.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.GenereEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.ProviderVacationEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.spring.PersistenceJPAConfig;
import com.timsmeet.services.find.FindEntityWithIdAccessor;
import com.timsmeet.services.find.entity.IdAccessors;

@Configuration
@ComponentScan(basePackages = {"com.timsmeet.services"})
@EnableTransactionManagement
@Import({PersistenceJPAConfig.class, MapperConfig.class})
public class ServicesConfig {

	@Bean(name = "providerVacationFind")
	public FindEntityWithIdAccessor<ProviderVacationEntity> getProviderVacationFind() {
		FindEntityWithIdAccessor<ProviderVacationEntity> providerVacationFind = new FindEntityWithIdAccessor<ProviderVacationEntity>();
		providerVacationFind.setIdAccessor(new IdAccessors.VacationEntityById<ProviderVacationEntity>());
		return providerVacationFind;
	}

	@Bean(name = "providerWorkingHourFind")
	public FindEntityWithIdAccessor<ProviderWorkingHourEntity> getProviderWorkingHourFind() {
		FindEntityWithIdAccessor<ProviderWorkingHourEntity> providerWorkingHourFind = new FindEntityWithIdAccessor<ProviderWorkingHourEntity>();
		providerWorkingHourFind.setIdAccessor(new IdAccessors.WorkingHourEntityById<ProviderWorkingHourEntity>());
		return providerWorkingHourFind;
	}

	@Bean(name = "emailFind")
	public FindEntityWithIdAccessor<EmailEntity> getEmailFind() {
		FindEntityWithIdAccessor<EmailEntity> emailFind = new FindEntityWithIdAccessor<EmailEntity>();
		emailFind.setIdAccessor(new IdAccessors.EmailEntityById());
		return emailFind;
	}

	@Bean(name = "phoneFind")
	public FindEntityWithIdAccessor<PhoneEntity> getPhoneFind() {
		FindEntityWithIdAccessor<PhoneEntity> phoneFind = new FindEntityWithIdAccessor<PhoneEntity>();
		phoneFind.setIdAccessor(new IdAccessors.PhoneEntityById());
		return phoneFind;
	}

	@Bean(name = "webUrlFind")
	public FindEntityWithIdAccessor<WebUrlEntity> getWebUrlFind() {
		FindEntityWithIdAccessor<WebUrlEntity> webUrlFind = new FindEntityWithIdAccessor<WebUrlEntity>();
		webUrlFind.setIdAccessor(new IdAccessors.WebUrlEntityById());
		return webUrlFind;
	}
	
	@Bean(name = "dishComponentFind")
	public FindEntityWithIdAccessor<DishComponentEntity> getDishComponentFind() {
	    FindEntityWithIdAccessor<DishComponentEntity> dishComponentFind = new FindEntityWithIdAccessor<DishComponentEntity>();
	    dishComponentFind.setIdAccessor(new IdAccessors.DishComponentEntityById());
	    return dishComponentFind;
	}
	
	@Bean(name = "dishElementFind")
	public FindEntityWithIdAccessor<DishElementEntity> getDishElementFind() {
	    FindEntityWithIdAccessor<DishElementEntity> dishElementFind = new FindEntityWithIdAccessor<DishElementEntity>();
	    dishElementFind.setIdAccessor(new IdAccessors.DishElementEntityById());
	    return dishElementFind;
	}
	
	@Bean(name = "genereFind")
	public FindEntityWithIdAccessor<GenereEntity> getGenereFind() {
	    FindEntityWithIdAccessor<GenereEntity> genereFind = new FindEntityWithIdAccessor<GenereEntity>();
	    genereFind.setIdAccessor(new IdAccessors.GenereEntityById());
	    return genereFind;
	}
	
	@Bean(name = "dishGenereFind")
	public FindEntityWithIdAccessor<DishGenereEntity> getDishGenereFind() {
	    FindEntityWithIdAccessor<DishGenereEntity> dishGenereFind = new FindEntityWithIdAccessor<DishGenereEntity>();
	    dishGenereFind.setIdAccessor(new IdAccessors.DishGenereEntityByGenereId());
	    return dishGenereFind;
	}
	
	@Bean(name = "dishFind")
	public FindEntityWithIdAccessor<DishEntity> getDishFind() {
	    FindEntityWithIdAccessor<DishEntity> dishFind = new FindEntityWithIdAccessor<DishEntity>();
	    dishFind.setIdAccessor(new IdAccessors.DishEntityById());
	    return dishFind;
	}
	
}
