package com.timsmeet.rest.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.google.common.collect.Lists;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.persistance.DbTestUtil;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.YesNo;
import com.timsmeet.rest.RestTestUtil;
import com.timsmeet.rest.controllers.constants.Endpoint;
import com.timsmeet.services.builder.DateBuilder;

@DatabaseSetup("dish/InitialData.xml")
@DatabaseTearDown("dish/ClearTables.xml")
@ExpectedDatabases({
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Contact.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Phone.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Address.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Provider.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.Dish.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.DishComponent.TABLE, override = false),
    @ExpectedDatabase(value = "dish/InitialData.xml", table = DbTable.DishElement.TABLE, override = false),
})
public class DishControllerTest extends BaseControllerTest{


    @Before
    public void setUpProviderControler() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(webApplicationContext, DbTable.Contact.TABLE, DbTable.Phone.TABLE, DbTable.Address.TABLE,
                DbTable.Provider.TABLE, DbTable.Dish.TABLE, DbTable.DishComponent.TABLE, DbTable.DishElement.TABLE);
    }

    @Test
    public void shouldFindDishByProviderId() throws Exception {
        ResultActions resultActions =
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("sort", "id"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)));

        resultActions = andValidateProviderDishes(resultActions, false);
    }

    private ResultActions andValidateProviderDishes(ResultActions resultActions, boolean onlyActive) throws Exception {

        resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Kebab")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", is("Best kebab in town")))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("Pizza super")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", is("Really great pizza")))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", is("Chicken sticks")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].description", is("Fat chicken sticks")))

                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].status", is(ActivityStatus.ACTIVE.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name", is("Potato pancakes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].description", is("Potato pancakes with sour cream")));

        if(!onlyActive) {
            resultActions
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].id", is(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].status", is(ActivityStatus.INACTIVE.toString())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].name", is("Dummy dish")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].description", is("Dummy inactive dish")));
        }

        return resultActions;
    }

    @Test
    public void shouldFindActiveDishByProviderId() throws Exception {
        ResultActions resultActions =
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("onlyActive", "true").param("sort", "id"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));

        resultActions = andValidateProviderDishes(resultActions, true);
    }

    @Test
    public void shouldFindActiveDishByProviderIdSortedByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("onlyActive", "true").param("sort", "name"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Chicken sticks")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("Kebab")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", is("Pizza super")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name", is("Potato pancakes")));
    }

    @Test
    public void shouldErrosOnWrongSort() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("sort", "unknown"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", is(ErrorDescribedEnum.INVALID_SORT_PARAM.getErrorCode())));
    }

    @Test
    public void shouldErrorWhenWrongEmbeded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("embeded", "wrongEmbeded"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", is(ErrorDescribedEnum.INVALID_EMBEDED_PARAM.getErrorCode())));

    }

    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value = "dish/operations/DishAdd.xml", table = DbTable.Dish.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishAdd.xml", table = DbTable.DishComponent.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishAdd.xml", table = DbTable.DishElement.TABLE, override = true),
    })
    public void shouldAddNewDish() throws Exception {

        final Timestamp avaiabilityStartDay = DateBuilder.utcDateAsTimestamp(2000, 6, 1);
        final Timestamp avaiabilityEndDay = DateBuilder.utcDateAsTimestamp(2999, 6, 1);

        Dish addedDish = new Dish.Builder("Added dish", ActivityStatus.ACTIVE)
                .description("Added dish desc").avaiabilityEndDay(avaiabilityEndDay).avaiabilityStartDay(avaiabilityStartDay)
                .price(new BigDecimal(1.0))
                .dishComponents(Arrays.asList(
                        new DishComponent.Builder("Added dish component 1", ActivityStatus.ACTIVE)
                            .maximumNumberOfElements(3).numberOfRequiredElements(1).useComponentPriceAsDishPrice(YesNo.NO)
                            .price(new BigDecimal(2.0))
                            .dishElements(Arrays.asList(
                                    new DishElement.Builder(ActivityStatus.ACTIVE)
                                    .name("Added element 1").description("Added element 1 desc").price(new BigDecimal(4.0)).build(),
                                    new DishElement.Builder(ActivityStatus.ACTIVE)
                                    .name("Added element 2").description("Added element 2 desc").price(new BigDecimal(5.0)).build()))
                            .build(),
                        new DishComponent.Builder("Added dish component 2", ActivityStatus.ACTIVE)
                            .maximumNumberOfElements(2).numberOfRequiredElements(0).useComponentPriceAsDishPrice(YesNo.NO)
                            .price(new BigDecimal(3.0)).build()))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.PROVIDER + "/1/" + Endpoint.DISH)
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(addedDish)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }




    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value = "dish/operations/DishDelete.xml", table = DbTable.Dish.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishDelete.xml", table = DbTable.DishComponent.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishDelete.xml", table = DbTable.DishElement.TABLE, override = true),
    })
    public void shouldDeleteDishWithComponentsAndElements() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(Endpoint.PROVIDER + "/1/" + Endpoint.DISH + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
    }


    @Test
    @ExpectedDatabases({
        @ExpectedDatabase(value = "dish/operations/DishModify.xml", table = DbTable.Dish.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishModify.xml", table = DbTable.DishComponent.TABLE, override = true),
        @ExpectedDatabase(value = "dish/operations/DishModify.xml", table = DbTable.DishElement.TABLE, override = true),
    })
    public void shouldModifyDishWithComponentsAndElements() throws Exception {

        MockHttpServletResponse existingDishResponse =
                mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.PROVIDER + "/1/" + Endpoint.DISH).param("sort", "id").param("embeded", "dishComponents"))
                .andReturn().getResponse();

        Dish[] dishes = RestTestUtil.convertJsonBytesToArray(existingDishResponse.getContentAsByteArray(), Dish.class);
        Dish toModify = dishes[0];
        toModify.setDescription("Modified dish description");
        toModify.setName("Modified dish name");
        toModify.setStatus(ActivityStatus.INACTIVE);
        toModify.setPrice(new BigDecimal("11.11"));
        toModify.getDishComponents().get(1).setDescription("Modified component description");
        toModify.getDishComponents().get(1).setPrice(new BigDecimal("22.22"));
        toModify.getDishComponents().remove(toModify.getDishComponents().get(0));
        toModify.getDishComponents().add(
                new DishComponent.Builder("Added kebab component", ActivityStatus.ACTIVE)
                .maximumNumberOfElements(1).numberOfRequiredElements(1).useComponentPriceAsDishPrice(YesNo.NO)
                .price(new BigDecimal("33.33"))
                .dishElements(Lists.newArrayList(
                        new DishElement.Builder(ActivityStatus.ACTIVE).name("Added element 1").description("Added element desc 1")
                        .price(new BigDecimal("44.44")).build(),
                        new DishElement.Builder(ActivityStatus.ACTIVE).name("Added element 2").description("Added element desc 2")
                        .price(new BigDecimal("55.55")).build()))
                .build());

        mockMvc.perform(MockMvcRequestBuilders.put(Endpoint.PROVIDER + "/1/" + Endpoint.DISH)
                .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
                .content(RestTestUtil.convertObjectToJsonBytes(toModify)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

    }


}
