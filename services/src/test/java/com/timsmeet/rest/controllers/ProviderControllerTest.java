package com.timsmeet.rest.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
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

import com.github.springtestdbunit.ExcludedColumns;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.Provider;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.DbTestUtil;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.PhoneNumberType;
import com.timsmeet.persistance.enums.WeekDay;
import com.timsmeet.persistance.repositories.ProviderRepository;
import com.timsmeet.rest.RestTestUtil;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.services.ProviderService;
import com.timsmeet.services.builder.DateBuilder;
import com.timsmeet.spring.ColumnSensingReplacementDataSetLoader;

/*
 * @DbUnitConfiguration(dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
 *      - replacement dataset loader is used, so for null db values, [null] string can be entered in falt xml dataset
 * @ExpectedDatabase(value = "provider/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
 *      - table is specified, as only then this can be overwritten in specific test (otherwise whole dataset is loaded and compared)
 *      - override = false - default true break checking for other tables (bug in spring-test-dbunit-1.2.1 ?)
 *      - excluded columns - to exclude e.g. last_modification_id, but this should be present in file for @DatabaseSetup, only for @ExpectedDatabase can be ommited
 */

@DbUnitConfiguration(dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
@DatabaseSetup("provider/InitialData.xml")
@DatabaseTearDown("provider/ClearTables.xml")
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
@ExcludedColumns("last_modification_id")
public class ProviderControllerTest extends BaseControllerTest {

	@Autowired
	private ProviderService providerService;

	@Autowired
	private ProviderRepository providerRepository;

    @Before
    public void setUpProviderControler() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(webApplicationContext, DbTable.Contact.TABLE, DbTable.Phone.TABLE, DbTable.Email.TABLE, DbTable.WebUrl.TABLE, DbTable.Address.TABLE, DbTable.Provider.TABLE, DbTable.WorkingHour.TABLE, DbTable.Vacation.TABLE);
    }

  @Test
  public void shouldFindAllProviders() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
  }

  @Test
  public void shouldErrosOnWrongPageSize() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER).param("perPage", "-2"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PER_PAGE_SHOULD_BE_POSITIVE.getErrorCode())));
  }

  @Test
  public void shouldErrosOnWrongPage() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER).param("page", "-2"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PAGE_PARAM_IS_NEGATIVE.getErrorCode())));
  }

  @Test
  public void shouldErrosOnWrongSort() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER).param("sort", "unknown"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.INVALID_SORT_PARAM.getErrorCode())));
  }

  @Test
  public void shouldSortByNameAsc() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER).param("sort", "name"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Acme Corp")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Binton Corp")));
  }

  @Test
  public void shouldSortByNameDesc() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER).param("sort", "-name"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Binton Corp")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Acme Corp")));
  }


  @Test
  public void shouldFindProviderById() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Acme Corp")));
  }

  @Test
  public void shouldErrorNotFoundWhenGetNotExistingProvider() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/-3001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isNotFound())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PROVIDER_TO_READ_NOT_FOUND.getErrorCode())));

  }

  @Test
  public void shouldErrorWhenWrongEmbeded() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/-1001").param("embeded", "wrongEmbeded"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode",  equalTo(ErrorDescribedEnum.INVALID_EMBEDED_PARAM.getErrorCode())));

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
      mockMvc.perform(MockMvcRequestBuilders.delete(Endpoint.PROVIDER + "/1"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  public void shouldErrorNotFoundWhenDeleteNotExistingProvider() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.delete(Endpoint.PROVIDER + "/-3001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isNotFound())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PROVIDER_TO_DELETE_NOT_FOUND.getErrorCode())));
  }


  @Test
  public void shouldFindByIdWithEmbededWorkingHours() throws Exception {
	  ResultActions resultActions =
      mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "workingHours"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)));

	  resultActions = andValidateReadComanyWorkingHours(resultActions);
  }

  private ResultActions andValidateReadComanyWorkingHours(ResultActions resultActions) throws Exception {
      return resultActions

    		  .andExpect(MockMvcResultMatchers.jsonPath("$.workingHours", hasSize(6)))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==1)].weekDay", hasItem(WeekDay.MONDAY.toString())))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==1)].startTime", hasItem("2000-06-01T06:00:00.000+0000")))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==1)].endTime", hasItem("2000-06-01T13:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==2)].weekDay", hasItem(WeekDay.MONDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==2)].startTime", hasItem("2000-06-01T14:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==2)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==3)].weekDay", hasItem(WeekDay.TUESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==3)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==3)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==4)].weekDay", hasItem(WeekDay.WEDNESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==4)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==4)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==5)].weekDay", hasItem(WeekDay.THURSDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==5)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==5)].endTime", hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==6)].weekDay", hasItem(WeekDay.FRIDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==6)].startTime", hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==6)].endTime", hasItem("2000-06-01T16:00:00.000+0000")));
  }

  @Test
  public void shouldFindByIdWithEmbededVacations() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "vacations"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)));
	  resultActions = andValidateProviderVacations(resultActions);
  }

  private ResultActions andValidateProviderVacations(ResultActions resultActions) throws Exception {
	  return resultActions
              .andExpect(MockMvcResultMatchers.jsonPath("$.vacations", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==1)].startDay", hasItem("2000-06-01T00:00:00.000+0000")))
              .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==1)].endDay", hasItem("2000-06-05T00:00:00.000+0000")))

	  		  .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==2)].startDay", hasItem("2000-08-01T00:00:00.000+0000")))
		      .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==2)].endDay", hasItem("2000-08-02T00:00:00.000+0000")));
  }

  @Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/operations/VacationAdd.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/VacationAdd.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldAddEmbededVacations() throws Exception {
      final Timestamp vacationStart1 = DateBuilder.utcDateAsTimestamp(2014, 9, 10);
      final Timestamp vacationEnd1 = DateBuilder.utcDateAsTimestamp(2014, 9, 11);

      MockHttpServletResponse existingProviderResponse =
              mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", ProviderService.EMBED_VACATIONS)).andReturn().getResponse();

      Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
      provider.getVacations().add(new Vacation.Builder(vacationStart1, vacationEnd1).build());

      mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER)
              .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
              .content(RestTestUtil.convertObjectToJsonBytes(provider)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse();
  }

  @Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/operations/VacationDelete.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/VacationDelete.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldRemoveEmbededVacations() throws Exception {
      final Timestamp vacationToRemoveStart = DateBuilder.utcDateAsTimestamp(2000, 6, 1);

      MockHttpServletResponse existingProviderResponse = mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "vacations"))
              .andReturn().getResponse();

      Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
      provider.getVacations().remove(findVacationByStartDay(provider.getVacations(), vacationToRemoveStart));
      //findVacationByStartDay(provider.getVacations(), vacationToRemoveStart).getEntityAspect().setEntityState(EntityState.DELETED);

      mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER)
              .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
              .content(RestTestUtil.convertObjectToJsonBytes(provider)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse();
  }

  @Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/operations/VacationModify.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/VacationModify.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldModifyEmbededVacations() throws Exception {
      final Timestamp vacationToModifyStart = DateBuilder.utcDateAsTimestamp(2000, 6, 1);
      final Timestamp modifiedStart = DateBuilder.utcDateAsTimestamp(2000, 6, 15);
      final Timestamp modifiedEnd = DateBuilder.utcDateAsTimestamp(2000, 6, 18);

      MockHttpServletResponse existingProviderResponse = mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "vacations"))
              .andReturn().getResponse();

      Provider provider = RestTestUtil.convertJsonBytesToObject(existingProviderResponse.getContentAsByteArray(), Provider.class);
      Vacation vacationToModify = findVacationByStartDay(provider.getVacations(), vacationToModifyStart);
      vacationToModify.setStartDay(modifiedStart);
      vacationToModify.setEndDay(modifiedEnd);

      mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER)
              .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
              .content(RestTestUtil.convertObjectToJsonBytes(provider)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse();
  }

  @Test
  public void shouldFindByIdWithEmbededContacts() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "contact.emails", "contact.webUrls", "contact.phones"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)));
	  
	  
	  resultActions = andValidateProviderContacts(resultActions);
  }

  private ResultActions andValidateProviderContacts(ResultActions resultActions) throws Exception {
	  return resultActions
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.emails", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==1)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==1)].comment", hasItem("Office email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==1)].emailAddress", hasItem("office@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==1)].displayIndex", hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==2)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==2)].comment", hasItem("Support email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==2)].emailAddress", hasItem("support@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==2)].displayIndex", hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.webUrls", hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==1)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==1)].webUrlAddress", hasItem("http://acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==1)].comment", hasItem("Main web address")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==1)].displayIndex", hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.phones", hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].phone", hasItem("508334321")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].phoneExt", hasItem("1234")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].numberType", hasItem(PhoneNumberType.MOBILE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].comment", hasItem("Mobile phone to manager")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==1)].displayIndex", hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==2)].status", hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==2)].phone", hasItem("345223421")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==2)].numberType", hasItem(PhoneNumberType.LANDLINE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==2)].comment", hasItem("Provider std phone")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==2)].displayIndex", hasItem(0)));
  }


  @Test
  public void shouldFindByIdWithMultiEmbededEntities() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "workingHours", "vacations", "contact.emails", "contact.webUrls", "contact.phones"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)));
	  resultActions = andValidateReadComanyWorkingHours(resultActions);
	  resultActions = andValidateProviderVacations(resultActions);
	  resultActions = andValidateProviderContacts(resultActions);
  }


  @Test
  @ExpectedDatabases({
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Contact.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Phone.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Email.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.WebUrl.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Address.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Provider.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.WorkingHour.TABLE, override=false),
      @ExpectedDatabase(value="provider/operations/ProviderAdd.xml", table=DbTable.Vacation.TABLE, override=false),
  })
  public void shouldSaveProviderWithEmbededObjects() throws Exception {

	  final Timestamp vacationStart1 = DateBuilder.utcDateAsTimestamp(2014, 2, 3);
	  final Timestamp vacationEnd1 = DateBuilder.utcDateAsTimestamp(2014, 2, 6);
	  final Timestamp workingHourStart1 = DateBuilder.utcTimeAsTimestamp(8, 30);
	  final Timestamp workingHourEnd1 = DateBuilder.utcTimeAsTimestamp(16, 30);
	  final Timestamp workingHourStart2 = DateBuilder.utcTimeAsTimestamp(9, 15);
	  final Timestamp workingHourEnd2 = DateBuilder.utcTimeAsTimestamp(16, 45);


	  Provider providerToSave = new Provider.Builder("Added Provider", "Added Provider Comment", ActivityStatus.ACTIVE)
	  	.vacations(Arrays.asList(new Vacation.Builder(vacationStart1, vacationEnd1).build()))
	  	.workingHours(Arrays.asList(
	  			new WorkingHour.Builder(WeekDay.MONDAY, workingHourStart1, workingHourEnd1).build(),
	  			new WorkingHour.Builder(WeekDay.TUESDAY, workingHourStart2, workingHourEnd2).build()
	  			))
	  	.address(new Address.Builder(ActivityStatus.ACTIVE).address1("ADD1").address2("ADD2").city("Paris").comment("add comment").country("France").displayIndex(0).state("ASD").zipCode("321123").build())
	  	.contact(new Contact.Builder(ActivityStatus.ACTIVE)
	  		.phones(Arrays.asList(
	  				new Phone.Builder(ActivityStatus.ACTIVE, PhoneNumberType.MOBILE).displayIndex(0).phone("112233").phoneExt("23").comment("pha").build(),
	  				new Phone.Builder(ActivityStatus.ACTIVE, PhoneNumberType.LANDLINE).displayIndex(1).phone("556677").phoneExt("34").comment("phb").build()))
	  		.webUrls(Arrays.asList(
	  				new WebUrl.Builder(ActivityStatus.ACTIVE).displayIndex(0).webUrlAddress("http://allo.allo").comment("allo comment").build(),
	  				new WebUrl.Builder(ActivityStatus.ACTIVE).displayIndex(1).webUrlAddress("https://support.allo").comment("support comment").build()))
	  		.emails(Arrays.asList(
	  				new Email.Builder(ActivityStatus.ACTIVE).displayIndex(0).emailAddress("mis@allo.com").comment("write an email here").build()))
	  	.build())
	  	.build();

	  mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER)
			  .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
			  .content(RestTestUtil.convertObjectToJsonBytes(providerToSave)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", notNullValue()))
              .andExpect(MockMvcResultMatchers.jsonPath("$.lastModificationId", notNullValue()))
              .andReturn().getResponse();

  }


	@Test
	  @ExpectedDatabases({
	      @ExpectedDatabase(value="provider/operations/ProviderModify.xml", table=DbTable.Address.TABLE, override=false),
	      @ExpectedDatabase(value="provider/operations/ProviderModify.xml", table=DbTable.Provider.TABLE, override=false),
	  })
	public void shouldUpdateProviderWithEmbededObjects() throws Exception {

		MockHttpServletResponse response =
				mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1").param("embeded", "workingHours", "vacations", "contact.emails", "contact.webUrls", "contact.phones"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
	            .andReturn().getResponse();

		Provider provider = RestTestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Provider.class);

		provider.setName("Modified name");
		provider.getAddress().setAddress1("Modified add 1");
		provider.getAddress().setAddress2(null);


		mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER)
				.contentType(RestTestUtil.APPLICATION_JSON_UTF8)
				.content(RestTestUtil.convertObjectToJsonBytes(provider)))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andReturn().getResponse();
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
