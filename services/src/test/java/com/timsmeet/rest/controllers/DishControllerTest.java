package com.timsmeet.rest.controllers;

import static org.hamcrest.Matchers.is;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.timsmeet.persistance.DbTestUtil;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.rest.controllers.constants.Endpoint;

@DatabaseSetup("dish/InitialData.xml")
@DatabaseTearDown("dish/ClearTables.xml")
public class DishControllerTest extends BaseControllerTest{

    
    @Before
    public void setUpProviderControler() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(webApplicationContext, DbTable.Contact.TABLE, DbTable.Phone.TABLE, DbTable.Address.TABLE, DbTable.Provider.TABLE, DbTable.Dish.TABLE);
    }   

    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
        @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
        @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Address.TABLE, override = false),
        @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
        @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Dish.TABLE, override = false),
    })
    public void shouldFindDishByProviderId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/-1001/" + Endpoint.DISH))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(-1001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("First Dish")));
    }

}
