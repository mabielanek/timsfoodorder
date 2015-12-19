package com.timsmeet.services.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableBuilderTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void defaultPageAndSize() {
        Pageable pageable = new PageableBuilder().build();
        assertThat(pageable.getPageNumber(), equalTo(0));
        assertThat(pageable.getPageSize(), equalTo(100));
        assertThat(pageable.getSort(), nullValue());
    }
    
    @Test
    public void negativePageNumberShouldCauseException() {
        thrown.expect(IllegalArgumentException.class);
        new PageableBuilder().page(-1).build();
    }
    
    @Test 
    public void negativePageSizeShouldCauseException() {
        thrown.expect(IllegalArgumentException.class);
        new PageableBuilder().size(-2).build();
    }
    
    @Test 
    public void nonPositivePageSizeShouldCauseException() {
        thrown.expect(IllegalArgumentException.class);
        new PageableBuilder().size(0).build();
    }
    
    @Test
    public void sortWithoutPropertyShouldCauseException() {
        thrown.expect(IllegalArgumentException.class);
        new PageableBuilder().addSort(Direction.ASC, "");
    }

    @Test
    public void sortWithNullPropertyShouldCauseException() {
        thrown.expect(IllegalArgumentException.class);
        new PageableBuilder().addSort(Direction.ASC, null);
    }

    @Test
    public void defaultAscSortingWhenDirectionIsNull() {
        Pageable pageable = new PageableBuilder().addSort(null, "aaa").build();
        assertThat(pageable.getSort(), equalTo(new Sort(Direction.ASC, "aaa")));
    }
    
    @Test
    public void correctSortShouldBeBuild() {
        Pageable pageable = new PageableBuilder().page(3).size(30).addSort(Direction.DESC, "xxx").build();
        assertThat(pageable.getPageNumber(), equalTo(3));
        assertThat(pageable.getPageSize(), equalTo(30));
        assertThat(pageable.getSort(), equalTo(new Sort(Direction.DESC, "xxx")));
    }
    
    @Test
    public void correctSortByMultFieldsShouldBeBuild() {
        Pageable pageable = new PageableBuilder().page(1).size(125).addSort(Direction.DESC, "xxx").addSort(Direction.ASC, "yyy").addSort(Direction.DESC, "zzz").build();
        assertThat(pageable.getPageNumber(), equalTo(1));
        assertThat(pageable.getPageSize(), equalTo(125));
        assertThat(pageable.getSort(), equalTo(new Sort(Direction.DESC, "xxx").and(new Sort(Direction.ASC, "yyy").and(new Sort(Direction.DESC, "zzz")))));
    }

}
