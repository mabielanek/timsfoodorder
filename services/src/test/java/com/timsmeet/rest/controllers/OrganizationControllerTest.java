package com.timsmeet.rest.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.springtestdbunit.ExcludedColumns;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.timsmeet.dto.Organization;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.DbTestUtil;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.rest.RestTestUtil;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.spring.ColumnSensingReplacementDataSetLoader;

@DbUnitConfiguration(dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
@DatabaseSetup("organization/InitialData.xml")
@DatabaseTearDown("organization/ClearTables.xml")
@ExpectedDatabases({
    @ExpectedDatabase(value = "organization/InitialData.xml", table = DbTable.Organization.TABLE, override = false),
})
@ExcludedColumns("last_modification_id")
public class OrganizationControllerTest extends BaseControllerTest {

    @Before
    public void setUpProviderControler() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(webApplicationContext, DbTable.Organization.TABLE);
    }

    @Test
    public void shouldFindAllOrganizations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldErrosOnWrongPageSize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("perPage", "-2"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PER_PAGE_SHOULD_BE_POSITIVE.getErrorCode())));
    }

    @Test
    public void shouldErrosOnWrongPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("page", "-2"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.PAGE_PARAM_IS_NEGATIVE.getErrorCode())));
    }

    @Test
    public void shouldErrosOnWrongSort() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("sort", "unknown"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.INVALID_SORT_PARAM.getErrorCode())));
    }

    @Test
    public void shouldSortByNameAsc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("sort", "name"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Global corporation")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Random company")));
    }

    @Test
    public void shouldSortByNameDesc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("sort", "-name"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("Random company")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("Global corporation")));
    }

    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value = "organization/operations/OrganizationDelete.xml", table = DbTable.Organization.TABLE, override = false),
    })
    public void shouldDeleteExistingOrganization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(Endpoint.ORGANIZATION + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void shouldErrosOnDeleteNotExistingOrganization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(Endpoint.ORGANIZATION + "/222"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", equalTo(ErrorDescribedEnum.ORGANIZATION_TO_DELETE_NOT_FOUND.getErrorCode())));
    }

    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value="organization/operations/OrganizationModify.xml", table=DbTable.Organization.TABLE, override=false),
    })
    public void shouldUpdateOrganization() throws Exception {

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ORGANIZATION).param("sort", "id")).andReturn().getResponse();

        Organization[] organizations = RestTestUtil.convertJsonBytesToArray(response.getContentAsByteArray(), Organization.class);

        organizations[0].setName("Global modified corporation");

        mockMvc.perform(MockMvcRequestBuilders.put(Endpoint.ORGANIZATION)
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(organizations[0])))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value="organization/operations/OrganizationAdd.xml", table=DbTable.Organization.TABLE, override=false),
    })
    public void shouldAddOrganization() throws Exception {

        Organization organization = new Organization();
        organization.setName("New added organization");

        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.ORGANIZATION)
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(organization)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

}
