package com.timsmeet.rest.controllers.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.rest.controllers.WrongParamException;


public class RestParamHelperTest {
    
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 100;
    
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void defaultPagableTest() {
        Pageable defaultPageable = RestParamHelper.defaultPageable();
        assertThat(defaultPageable.getOffset(), equalTo(DEFAULT_PAGE_NUMBER * DEFAULT_PAGE_SIZE));
        assertThat(defaultPageable.getPageNumber(), equalTo(DEFAULT_PAGE_NUMBER));
        assertThat(defaultPageable.getPageSize(), equalTo(DEFAULT_PAGE_SIZE));
        assertThat(defaultPageable.getSort(), nullValue());
    }
    
    @Test
    public void pageAndPerPageTest() {
        int page = 4;
        int perPage = 25;
        Pageable pageable = new RestParamHelper().specifyPaging(page, perPage).buildPageable();
        assertThat(pageable.getPageNumber(), equalTo(page));
        assertThat(pageable.getPageSize(), equalTo(perPage));
        assertThat(pageable.getSort(), nullValue());
    }
    
    @Test
    public void embededWithAllowdTest() {
        String[] embeded = 
                new RestParamHelper().allowEmbed("aaa", "bbb", "ccc", "ddd").withEmbeded("aaa,bbb,ddd").buildEmbeded();
        assertThat(embeded, arrayWithSize(3));
        assertThat(embeded, allOf(arrayContainingInAnyOrder("aaa", "bbb", "ddd"), not(arrayContaining("ccc"))));
    }
    
    @Test
    public void embededWithoutAllowedTest() {
        String[] embeded = 
                new RestParamHelper().withEmbeded("aaa,bbb,ddd").buildEmbeded();
        assertThat(embeded, arrayWithSize(3));
        assertThat(embeded, arrayContainingInAnyOrder("aaa", "bbb", "ddd"));
    }
    
    @Test
    public void embededWithNullTest() {
        String[] embeded = 
                new RestParamHelper().withEmbeded(null).buildEmbeded();
        
        assertThat(embeded, arrayWithSize(0));
    }
    
    @Test
    public void embededWithEmptyTest() {
        String[] embeded = 
                new RestParamHelper().withEmbeded("").buildEmbeded();
        
        assertThat(embeded, arrayWithSize(0));
    }
    
    @Test
    public void embededWithAllowedNotExistingTest() {
        thrown.expect(WrongParamException.class);
        thrown.expectMessage(new IsEqualIgnoringWhiteSpace(
                String.format(ErrorDescribedEnum.INVALID_EMBEDED_PARAM.getErrorMessage(), "bbb,ddd")));

        new RestParamHelper().allowEmbed("aaa", "ccc").withEmbeded("aaa,bbb,ddd").buildEmbeded();
    }
    
    @Test 
    public void sortWithAllowSortTest() {
        Pageable pageable = new RestParamHelper().allowSortBy("aaa","bbb","ccc").withSorting("-bbb,ccc").buildPageable();
        assertThat(pageable.getSort(), equalTo(new Sort(new Order(Direction.DESC, "bbb"), new Order(Direction.ASC, "ccc"))));

        pageable = new RestParamHelper().allowSortBy("aaa","bbb","ccc").withSorting("ccc,-aaa").buildPageable();
        assertThat(pageable.getSort(), equalTo(new Sort(new Order(Direction.ASC, "ccc"), new Order(Direction.DESC, "aaa"))));
    }
    
    @Test
    public void sortWithoutAllowSortTest() {
        Pageable pageable = new RestParamHelper().withSorting("-bbb,ccc").buildPageable();
        assertThat(pageable.getSort(), equalTo(new Sort(new Order(Direction.DESC, "bbb"), new Order(Direction.ASC, "ccc"))));
    }

    @Test
    public void sortWithAllowSortNotExistingTest() {
        thrown.expect(WrongParamException.class);
        thrown.expectMessage(new IsEqualIgnoringWhiteSpace(
                String.format(ErrorDescribedEnum.INVALID_SORT_PARAM.getErrorMessage(), "-bbb")));
        new RestParamHelper().allowSortBy("aaa","ccc").withSorting("-bbb,ccc").buildPageable();
    }
    
    @Test
    public void sortWithNullTest() {
        Pageable pageable = new RestParamHelper().withSorting(null).buildPageable();
        assertThat(pageable.getSort(), nullValue());
    }

    @Test
    public void sortWithEmptyTest() {
        Pageable pageable = new RestParamHelper().withSorting("").buildPageable();
        assertThat(pageable.getSort(), nullValue());
    }

    @Test
    public void pageableAndEmbededCreationTest() {
        int page = 2;
        int perPage = 50;

        RestParamHelper paramHelper = new RestParamHelper().
                specifyPaging(page, perPage).
                allowSortBy("aaa","bbb","ccc", "ddd").withSorting("-bbb,ddd").
                allowEmbed("aaa", "bbb", "ccc", "ddd.eee").withEmbeded("aaa,bbb,ddd.eee");
        
        Pageable pageable = paramHelper.buildPageable();
        assertThat(pageable.getPageNumber(), equalTo(page));
        assertThat(pageable.getPageSize(), equalTo(perPage));
        assertThat(pageable.getSort(), equalTo(new Sort(new Order(Direction.DESC, "bbb"), new Order(Direction.ASC, "ddd"))));

        String[] embeded = paramHelper.buildEmbeded();
        assertThat(embeded, arrayWithSize(3));
        assertThat(embeded, arrayContainingInAnyOrder("aaa", "bbb", "ddd.eee"));
    }
}
