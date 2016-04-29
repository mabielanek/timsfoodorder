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
	public FindEntityWithIdAccessor<ProviderVacationEntity, Long> getProviderVacationFind() {
		FindEntityWithIdAccessor<ProviderVacationEntity, Long> providerVacationFind = new FindEntityWithIdAccessor<ProviderVacationEntity, Long>();
		providerVacationFind.setIdAccessor(new IdAccessors.VacationEntityById<ProviderVacationEntity>());
		return providerVacationFind;
	}

	@Bean(name = "providerWorkingHourFind")
	public FindEntityWithIdAccessor<ProviderWorkingHourEntity, Long> getProviderWorkingHourFind() {
		FindEntityWithIdAccessor<ProviderWorkingHourEntity, Long> providerWorkingHourFind = new FindEntityWithIdAccessor<ProviderWorkingHourEntity, Long>();
		providerWorkingHourFind.setIdAccessor(new IdAccessors.WorkingHourEntityById<ProviderWorkingHourEntity>());
		return providerWorkingHourFind;
	}

	@Bean(name = "emailFind")
	public FindEntityWithIdAccessor<EmailEntity, Long> getEmailFind() {
		FindEntityWithIdAccessor<EmailEntity, Long> emailFind = new FindEntityWithIdAccessor<EmailEntity, Long>();
		emailFind.setIdAccessor(new IdAccessors.EmailEntityById());
		return emailFind;
	}

	@Bean(name = "phoneFind")
	public FindEntityWithIdAccessor<PhoneEntity, Long> getPhoneFind() {
		FindEntityWithIdAccessor<PhoneEntity, Long> phoneFind = new FindEntityWithIdAccessor<PhoneEntity, Long>();
		phoneFind.setIdAccessor(new IdAccessors.PhoneEntityById());
		return phoneFind;
	}

	@Bean(name = "webUrlFind")
	public FindEntityWithIdAccessor<WebUrlEntity, Long> getWebUrlFind() {
		FindEntityWithIdAccessor<WebUrlEntity, Long> webUrlFind = new FindEntityWithIdAccessor<WebUrlEntity, Long>();
		webUrlFind.setIdAccessor(new IdAccessors.WebUrlEntityById());
		return webUrlFind;
	}
	
	@Bean(name = "dishComponentFind")
	public FindEntityWithIdAccessor<DishComponentEntity, Long> getDishComponentFind() {
	    FindEntityWithIdAccessor<DishComponentEntity, Long> dishComponentFind = new FindEntityWithIdAccessor<DishComponentEntity, Long>();
	    dishComponentFind.setIdAccessor(new IdAccessors.DishComponentEntityById());
	    return dishComponentFind;
	}
	
	@Bean(name = "dishElementFind")
	public FindEntityWithIdAccessor<DishElementEntity, Long> getDishElementFind() {
	    FindEntityWithIdAccessor<DishElementEntity, Long> dishElementFind = new FindEntityWithIdAccessor<DishElementEntity, Long>();
	    dishElementFind.setIdAccessor(new IdAccessors.DishElementEntityById());
	    return dishElementFind;
	}
	
	@Bean(name = "genereFind")
	public FindEntityWithIdAccessor<GenereEntity, Long> getGenereFind() {
	    FindEntityWithIdAccessor<GenereEntity, Long> genereFind = new FindEntityWithIdAccessor<GenereEntity, Long>();
	    genereFind.setIdAccessor(new IdAccessors.GenereEntityById());
	    return genereFind;
	}
	
	@Bean(name = "dishGenereFind")
	public FindEntityWithIdAccessor<DishGenereEntity, Long> getDishGenereFind() {
	    FindEntityWithIdAccessor<DishGenereEntity, Long> dishGenereFind = new FindEntityWithIdAccessor<DishGenereEntity, Long>();
	    dishGenereFind.setIdAccessor(new IdAccessors.DishGenereEntityByGenereId());
	    return dishGenereFind;
	}
	
	@Bean(name = "dishFind")
	public FindEntityWithIdAccessor<DishEntity, Long> getDishFind() {
	    FindEntityWithIdAccessor<DishEntity, Long> dishFind = new FindEntityWithIdAccessor<DishEntity, Long>();
	    dishFind.setIdAccessor(new IdAccessors.DishEntityById());
	    return dishFind;
	}
	
}
