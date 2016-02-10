package com.timsmeet.services;

import java.sql.Timestamp;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.WeekDay;

@Ignore
public class ProviderServiceTest extends BaseServiceTest {

	private Long providerId = null;

	@Autowired
	private ProviderService providerService;

	@Before
	@Transactional
	public void prepareData() {
		Vacation vacation = new Vacation.Builder(new Timestamp(0), new Timestamp(0)).build();
		WorkingHour workingHour1 = new WorkingHour.Builder(WeekDay.MONDAY, new Timestamp(0), new Timestamp(0)).build();
		WorkingHour workingHour2 = new WorkingHour.Builder(WeekDay.TUESDAY, new Timestamp(0), new Timestamp(0)).build();
		Address providerAddress = new Address.Builder(ActivityStatus.ACTIVE).address1("xxx").address2("yyy") .city("RZE").displayIndex(0).build();
		Provider provider = new Provider.Builder("Provider1", "Very good food provider", ActivityStatus.ACTIVE).address(providerAddress)
				.vacations(Arrays.asList(vacation))
				.workingHours(Arrays.asList(workingHour1, workingHour2))
				.build();

		providerId = providerService.save(provider).getId();
	}

	@Test
	public void readProvider() {
		Provider entity = providerService.readProvider(providerId, null);

		Assert.assertNotNull("Should read provider", entity);
		Assert.assertNull("Should not read working hours", entity.getWorkingHours());
	}

	@Test
	public void readProviderWithWorkingHours() {
		Provider entity = providerService.readProvider(providerId, new String[] { "workingHours" });

		Assert.assertNotNull("Should read provider", entity);
		Assert.assertNotNull("Should read working hours", entity.getWorkingHours());
		Assert.assertNull("Should not read vacations", entity.getVacations());
	}

}
