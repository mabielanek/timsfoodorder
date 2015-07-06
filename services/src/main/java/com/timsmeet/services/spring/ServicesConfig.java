package com.timsmeet.services.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.ProviderVacationEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.spring.PersistenceJPAConfig;
import com.timsmeet.services.find.FindEntityWithIdAccessor;
import com.timsmeet.services.find.entity.EmailIdAccessor;
import com.timsmeet.services.find.entity.PhoneIdAccessor;
import com.timsmeet.services.find.entity.VacationIdAccessor;
import com.timsmeet.services.find.entity.WebUrlIdAccessor;
import com.timsmeet.services.find.entity.WorkingHourIdAccessor;

@Configuration
@ComponentScan(basePackages = {"com.timsmeet.services"})
@EnableTransactionManagement
@Import({PersistenceJPAConfig.class})
public class ServicesConfig {

	
	@Bean(name = "providerVacationFind")
	public FindEntityWithIdAccessor<ProviderVacationEntity> getProviderVacationFind() {
		FindEntityWithIdAccessor<ProviderVacationEntity> providerVacationFind = new FindEntityWithIdAccessor<ProviderVacationEntity>();
		providerVacationFind.setIdAccessor(new VacationIdAccessor<ProviderVacationEntity>());
		return providerVacationFind;
	}

	@Bean(name = "providerWorkingHourFind")
	public FindEntityWithIdAccessor<ProviderWorkingHourEntity> getProviderWorkingHourFind() {
		FindEntityWithIdAccessor<ProviderWorkingHourEntity> providerWorkingHourFind = new FindEntityWithIdAccessor<ProviderWorkingHourEntity>();
		providerWorkingHourFind.setIdAccessor(new WorkingHourIdAccessor<ProviderWorkingHourEntity>());
		return providerWorkingHourFind;
	}

	@Bean(name = "emailFind")
	public FindEntityWithIdAccessor<EmailEntity> getEmailFind() {
		FindEntityWithIdAccessor<EmailEntity> emailFind = new FindEntityWithIdAccessor<EmailEntity>();
		emailFind.setIdAccessor(new EmailIdAccessor());
		return emailFind;
	}

	@Bean(name = "phoneFind")
	public FindEntityWithIdAccessor<PhoneEntity> getPhoneFind() {
		FindEntityWithIdAccessor<PhoneEntity> phoneFind = new FindEntityWithIdAccessor<PhoneEntity>();
		phoneFind.setIdAccessor(new PhoneIdAccessor());
		return phoneFind;
	}

	@Bean(name = "webUrlFind")
	public FindEntityWithIdAccessor<WebUrlEntity> getWebUrlFind() {
		FindEntityWithIdAccessor<WebUrlEntity> webUrlFind = new FindEntityWithIdAccessor<WebUrlEntity>();
		webUrlFind.setIdAccessor(new WebUrlIdAccessor());
		return webUrlFind;
	}
}
