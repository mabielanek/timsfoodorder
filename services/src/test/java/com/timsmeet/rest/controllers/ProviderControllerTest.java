package com.timsmeet.rest.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.dto.entity.EntityState;
import com.timsmeet.persistance.DbTestUtil;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.PhoneNumberType;
import com.timsmeet.persistance.enums.WeekDay;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.persistance.model.ProviderWorkingHourEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.repositories.ProviderRepository;
import com.timsmeet.rest.RestTestUtil;
import com.timsmeet.services.ProviderService;
import com.timsmeet.services.builder.DateBuilder;


@DatabaseSetup("provider/InitialData.xml")
@DatabaseTearDown("provider/ClearTables.xml")
public class ProviderControllerTest extends BaseControllerTest {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderRepository providerRepository;

    @Before
    public void setUpProviderControler() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(webApplicationContext, DbTable.Contact.TABLE, DbTable.Phone.TABLE, DbTable.Email.TABLE,
                DbTable.WebUrl.TABLE, DbTable.Address.TABLE, DbTable.Provider.TABLE, DbTable.WorkingHour.TABLE, DbTable.Vacation.TABLE);
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldFindAllProviders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/providers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldFindProviderById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Acme Corp")));
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldErrorNotFoundWhenGetNotExistingProvider() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/providers/-3001"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/ProviderDelete.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldDeleteExistingProviderWithChildrenEntities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/providers/-1001"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldErrorNotFoundWhenDeleteNotExistingProvider() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/providers/-3001"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", is("0001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasItem("0001")));
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldFindByIdWithEmbededWorkingHours() throws Exception {
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "workingHours"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)));

        resultActions = andValidateReadComanyWorkingHours(resultActions);
    }

  private ResultActions andValidateReadComanyWorkingHours(ResultActions resultActions) throws Exception {
      return resultActions

    		  .andExpect(MockMvcResultMatchers.jsonPath("$.workingHours", hasSize(6)))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].weekDay", hasItem(WeekDay.MONDAY.toString())))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].startTime", hasItem("2000-06-01T06:00:00.000+0000")))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].endTime", hasItem("2000-06-01T13:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].weekDay", hasItem(WeekDay.MONDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].startTime", hasItem("2000-06-01T14:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].weekDay", hasItem(WeekDay.TUESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].weekDay", hasItem(WeekDay.WEDNESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].weekDay", hasItem(WeekDay.THURSDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].weekDay", hasItem(WeekDay.FRIDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].endTime", hasItem("2000-06-01T16:00:00.000+0000")));
  }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldFindByIdWithEmbededVacations() throws Exception {
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "vacations"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)));
        resultActions = andValidateProviderVacations(resultActions);
    }

    private ResultActions andValidateProviderVacations(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacations", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1001)].startDay", hasItem("2000-06-01T00:00:00.000+0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1001)].endDay", hasItem("2000-06-05T00:00:00.000+0000")))

                .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1002)].startDay", hasItem("2000-08-01T00:00:00.000+0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1002)].endDay", hasItem("2000-08-02T00:00:00.000+0000")));
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/VacationAdd.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/VacationAdd.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldAddEmbededVacations() throws Exception {
        final Timestamp vacationStart1 = DateBuilder.utcDateAsTimestamp(2014, 8, 10);
        final Timestamp vacationEnd1 = DateBuilder.utcDateAsTimestamp(2014, 8, 11);

        MockHttpServletResponse existingProviderResponse = mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001")).andReturn()
                .getResponse();

        Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
        provider.getVacations().add(new Vacation.Builder(EntityState.ADDED, vacationStart1, vacationEnd1).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/providers")
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(provider)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/VacationDelete.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/operations/VacationDelete.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldRemoveEmbededVacations() throws Exception {
        final Timestamp vacationToRemoveStart = DateBuilder.utcDateAsTimestamp(2000, 5, 1);

        MockHttpServletResponse existingProviderResponse = mockMvc
                .perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "vacations"))
                .andReturn().getResponse();

        Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
        findVacationByStartDay(provider.getVacations(), vacationToRemoveStart).getEntityAspect().setEntityState(EntityState.DELETED);

        mockMvc.perform(MockMvcRequestBuilders.post("/providers")
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(provider)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

  @Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Contact.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Phone.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Email.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.WebUrl.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Address.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.WorkingHour.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/VacationModify.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/VacationModify.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldModifyEmbededVacations() throws Exception {
      final Timestamp vacationToModifyStart = DateBuilder.utcDateAsTimestamp(2000, 5, 1);
      final Timestamp modifiedStart = DateBuilder.utcDateAsTimestamp(2000, 5, 15);
      final Timestamp modifiedEnd = DateBuilder.utcDateAsTimestamp(2000, 5, 18);

      MockHttpServletResponse existingProviderResponse = mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "vacations"))
              .andReturn().getResponse();

      Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
      Vacation vacationToModify = findVacationByStartDay(provider.getVacations(), vacationToModifyStart);
      vacationToModify.setStartDay(modifiedStart);
      vacationToModify.setEndDay(modifiedEnd);
      vacationToModify.getEntityAspect().setEntityState(EntityState.MODIFIED);

      mockMvc.perform(MockMvcRequestBuilders.post("/providers")
              .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
              .content(RestTestUtil.convertObjectToJsonBytes(provider)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse();
  }

@Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Contact.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Phone.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Email.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.WebUrl.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Address.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.WorkingHour.TABLE, override=false),
      @ExpectedDatabase(value="provider/InitialData.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldFindByIdWithEmbededContacts() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "contact.emails", "contact.webUrls", "contact.phones"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)));
	  resultActions = andValidateProviderContacts(resultActions);
  }

  private ResultActions andValidateProviderContacts(ResultActions resultActions) throws Exception {
	  return resultActions
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.emails", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].comment", hasItem("Office email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].emailAddress", hasItem("office@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].displayIndex", hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].comment", hasItem("Support email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].emailAddress", hasItem("support@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].displayIndex", hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.webUrls", hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].webUrlAddress", hasItem("http://acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].comment", hasItem("Main web address")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].displayIndex", hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.phones", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].phone", hasItem("508334321")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].phoneExt", hasItem("1234")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].numberType", hasItem(PhoneNumberType.MOBILE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].comment", hasItem("Mobile phone to manager")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].displayIndex", hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].phone", hasItem("345223421")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].numberType", hasItem(PhoneNumberType.LANDLINE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].comment", hasItem("Provider std phone")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].displayIndex", hasItem(0)));
  }

    @Test
    @ExpectedDatabases({
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Email.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WebUrl.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Address.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.WorkingHour.TABLE, override = false),
            @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Vacation.TABLE, override = false),
    })
    public void shouldFindByIdWithMultiEmbededEntities() throws Exception {
        ResultActions resultActions =
                mockMvc.perform(
                        MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "workingHours", "vacations", "contact.emails",
                                "contact.webUrls", "contact.phones"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)));
        resultActions = andValidateReadComanyWorkingHours(resultActions);
        resultActions = andValidateProviderVacations(resultActions);
        resultActions = andValidateProviderContacts(resultActions);
    }


    @Test
    @Transactional
    public void shouldSaveProviderWithEmbededObjects() throws Exception {

        final Timestamp vacationStart1 = DateBuilder.utcDateAsTimestamp(2014, 1, 3);
        final Timestamp vacationEnd1 = DateBuilder.utcDateAsTimestamp(2014, 1, 6);
        final Timestamp workingHourStart1 = DateBuilder.utcTimeAsTimestamp(8, 30);
        final Timestamp workingHourEnd1 = DateBuilder.utcTimeAsTimestamp(16, 30);
        final Timestamp workingHourStart2 = DateBuilder.utcTimeAsTimestamp(9, 15);
        final Timestamp workingHourEnd2 = DateBuilder.utcTimeAsTimestamp(16, 45);

        Provider providerToSave = new Provider.Builder(EntityState.ADDED, "Added Provider", "Added Provider Comment", ActivityStatus.ACTIVE)
                .vacations(Arrays.asList(new Vacation.Builder(EntityState.ADDED, vacationStart1, vacationEnd1).build()))
                .workingHours(Arrays.asList(
                        new WorkingHour.Builder(EntityState.ADDED, WeekDay.MONDAY, workingHourStart1, workingHourEnd1).build(),
                        new WorkingHour.Builder(EntityState.ADDED, WeekDay.TUESDAY, workingHourStart2, workingHourEnd2).build()
                        ))
                .address(
                        new Address.Builder(EntityState.ADDED, ActivityStatus.ACTIVE).address1("ADD1").address2("ADD2").city("Paris")
                                .comment("add comment").country("France").displayIndex(1).state("ASD").zipCode("321123").build())
                .contact(
                        new Contact.Builder(EntityState.ADDED, ActivityStatus.ACTIVE)
                                .phones(Arrays.asList(
                                        new Phone.Builder(EntityState.ADDED, ActivityStatus.ACTIVE, PhoneNumberType.MOBILE).displayIndex(0)
                                                .phone("112233").phoneExt("23").build(),
                                        new Phone.Builder(EntityState.ADDED, ActivityStatus.ACTIVE, PhoneNumberType.LANDLINE)
                                                .displayIndex(1).phone("556677").build()))
                                .webUrls(
                                        Arrays.asList(
                                                new WebUrl.Builder(EntityState.ADDED, ActivityStatus.ACTIVE).displayIndex(0)
                                                        .webUrlAddress("http://allo.allo").comment("allo comment").build(),
                                                new WebUrl.Builder(EntityState.ADDED, ActivityStatus.ACTIVE).displayIndex(1)
                                                        .webUrlAddress("https://support.allo").build()))
                                .emails(Arrays.asList(
                                        new Email.Builder(EntityState.ADDED, ActivityStatus.ACTIVE).displayIndex(0)
                                                .emailAddress("mis@allo.com").comment("write an email here").build()))
                                .build())
                .build();

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/providers")
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(providerToSave)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastModificationId", notNullValue()))
                .andReturn().getResponse();

        Provider provider = RestTestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Provider.class);

        ProviderEntity insertedProviderDb = providerRepository.findOne(provider.getId());
        assertThat(insertedProviderDb.getVacations(), hasSize(1));
        assertThat(insertedProviderDb.getVacations().get(0), hasProperty("startDay", equalTo(vacationStart1)));
        assertThat(insertedProviderDb.getVacations().get(0), hasProperty("endDay", equalTo(vacationEnd1)));

        assertThat(insertedProviderDb.getAddress(), is(notNullValue()));
        assertThat(insertedProviderDb.getAddress().getAddress1(), equalTo("ADD1"));
        assertThat(insertedProviderDb.getAddress().getAddress2(), equalTo("ADD2"));
        assertThat(insertedProviderDb.getAddress().getCity(), equalTo("Paris"));
        assertThat(insertedProviderDb.getAddress().getComment(), equalTo("add comment"));
        assertThat(insertedProviderDb.getAddress().getCountry(), equalTo("France"));
        assertThat(insertedProviderDb.getAddress().getDisplayIndex(), equalTo(1));
        assertThat(insertedProviderDb.getAddress().getState(), equalTo("ASD"));
        assertThat(insertedProviderDb.getAddress().getZipCode(), equalTo("321123"));
        assertThat(insertedProviderDb.getAddress().getStatus(), equalTo(ActivityStatus.ACTIVE));

        assertThat(insertedProviderDb.getContact().getStatus(), equalTo(ActivityStatus.ACTIVE));

        assertThat(insertedProviderDb.getContact().getPhones(), hasSize(2));
        List<PhoneEntity> phonesCheck = Lists.newArrayList(findDbPhoneByNumber(insertedProviderDb.getContact().getPhones(), "112233"));
        assertThat(phonesCheck, hasSize(1));
        assertThat(phonesCheck.get(0), hasProperty("status", equalTo(ActivityStatus.ACTIVE)));
        assertThat(phonesCheck.get(0), hasProperty("displayIndex", equalTo(0)));
        assertThat(phonesCheck.get(0), hasProperty("numberType", equalTo(PhoneNumberType.MOBILE)));
        assertThat(phonesCheck.get(0), hasProperty("phoneExt", equalTo("23")));

        phonesCheck = Lists.newArrayList(findDbPhoneByNumber(insertedProviderDb.getContact().getPhones(), "556677"));
        assertThat(phonesCheck, hasSize(1));
        assertThat(phonesCheck.get(0), hasProperty("status", equalTo(ActivityStatus.ACTIVE)));
        assertThat(phonesCheck.get(0), hasProperty("displayIndex", equalTo(1)));
        assertThat(phonesCheck.get(0), hasProperty("numberType", equalTo(PhoneNumberType.LANDLINE)));

        assertThat(insertedProviderDb.getContact().getWebUrls(), hasSize(2));
        List<WebUrlEntity> urlsCheck = Lists.newArrayList(findDbWebUrlByUrl(insertedProviderDb.getContact().getWebUrls(),
                "http://allo.allo"));
        assertThat(urlsCheck, hasSize(1));
        assertThat(urlsCheck.get(0), hasProperty("status", equalTo(ActivityStatus.ACTIVE)));
        assertThat(urlsCheck.get(0), hasProperty("displayIndex", equalTo(0)));
        assertThat(urlsCheck.get(0), hasProperty("comment", equalTo("allo comment")));

        urlsCheck = Lists.newArrayList(findDbWebUrlByUrl(insertedProviderDb.getContact().getWebUrls(), "https://support.allo"));
        assertThat(urlsCheck, hasSize(1));
        assertThat(urlsCheck.get(0), hasProperty("status", equalTo(ActivityStatus.ACTIVE)));
        assertThat(urlsCheck.get(0), hasProperty("displayIndex", equalTo(1)));

        assertThat(insertedProviderDb.getContact().getEmails(), hasSize(1));
        assertThat(insertedProviderDb.getContact().getEmails().get(0), hasProperty("emailAddress", equalTo("mis@allo.com")));
        assertThat(insertedProviderDb.getContact().getEmails().get(0), hasProperty("status", equalTo(ActivityStatus.ACTIVE)));
        assertThat(insertedProviderDb.getContact().getEmails().get(0), hasProperty("displayIndex", equalTo(0)));
        assertThat(insertedProviderDb.getContact().getEmails().get(0), hasProperty("comment", equalTo("write an email here")));

        assertThat(insertedProviderDb.getWorkingHours(), hasSize(2));
        List<ProviderWorkingHourEntity> mondayWorkingHoursDb =
                Lists.newArrayList(findDbWorkingHourByWeekDay(insertedProviderDb.getWorkingHours(), WeekDay.MONDAY));
        assertThat(mondayWorkingHoursDb, hasSize(1));
        assertThat(mondayWorkingHoursDb.get(0), hasProperty("weekDay", equalTo(WeekDay.MONDAY)));
        assertThat(mondayWorkingHoursDb.get(0), hasProperty("startTime", equalTo(workingHourStart1)));
        assertThat(mondayWorkingHoursDb.get(0), hasProperty("endTime", equalTo(workingHourEnd1)));

        List<ProviderWorkingHourEntity> tuesdayWorkingHoursDb =
                Lists.newArrayList(findDbWorkingHourByWeekDay(insertedProviderDb.getWorkingHours(), WeekDay.TUESDAY));
        assertThat(tuesdayWorkingHoursDb, hasSize(1));
        assertThat(tuesdayWorkingHoursDb.get(0), hasProperty("weekDay", equalTo(WeekDay.TUESDAY)));
        assertThat(tuesdayWorkingHoursDb.get(0), hasProperty("startTime", equalTo(workingHourStart2)));
        assertThat(tuesdayWorkingHoursDb.get(0), hasProperty("endTime", equalTo(workingHourEnd2)));

    }


	@Test
	@Transactional
	public void shouldUpdateProviderWithEmbededObjects() throws Exception {

		MockHttpServletResponse response =
				mockMvc.perform(MockMvcRequestBuilders.get("/providers/-1001").param("embeded", "workingHours", "vacations", "contact.emails", "contact.webUrls", "contact.phones"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(-1001)))
	            .andReturn().getResponse();

		Provider provider = RestTestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Provider.class);

		provider.setName("Modified name");
		provider.getAddress().setAddress1("Modified add 1");


		mockMvc.perform(MockMvcRequestBuilders.post("/providers")
				.contentType(RestTestUtil.APPLICATION_JSON_UTF8)
				.content(RestTestUtil.convertObjectToJsonBytes(provider)))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andReturn().getResponse();

		ProviderEntity updatedProviderDb = providerRepository.findOne(provider.getId());
		assertThat(updatedProviderDb.getName(), equalTo("Modified name"));
		assertThat(updatedProviderDb.getAddress().getAddress1(), equalTo("Modified add 1"));
	}

	private Iterable<ProviderWorkingHourEntity> findDbWorkingHourByWeekDay(List<ProviderWorkingHourEntity> workingHours, final WeekDay weekDay) {
		return Iterables.filter(workingHours, new Predicate<ProviderWorkingHourEntity>() {
			@Override
			public boolean apply(ProviderWorkingHourEntity input) {
				return weekDay.equals(input.getWeekDay());
			}
		});
	}

	private Iterable<PhoneEntity> findDbPhoneByNumber(List<PhoneEntity> phones, final String number) {
		return Iterables.filter(phones, new Predicate<PhoneEntity>() {
			@Override
			public boolean apply(PhoneEntity input) {
				return number.equals(input.getPhone());
			}
		});
	}

    private Iterable<WebUrlEntity> findDbWebUrlByUrl(List<WebUrlEntity> webUrls, final String url) {
        return Iterables.filter(webUrls, new Predicate<WebUrlEntity>() {
            @Override
            public boolean apply(WebUrlEntity input) {
                return url.equals(input.getWebUrlAddress());
            }
        });
    }

    private Vacation findVacationByStartDay(List<Vacation> vacations, final Timestamp vacationToRemoveStart) {
        return Iterables.find(vacations, new Predicate<Vacation>() {
            @Override
            public boolean apply(Vacation input) {
                return vacationToRemoveStart.equals(input.getStartDay());
            }
        });
    }

}