package com.timsmeet.rest.controllers.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.timsmeet.errors.ErrorBuilder;
import com.timsmeet.errors.ErrorDescribedEnum;
import com.timsmeet.rest.controllers.WrongParamException;
import com.timsmeet.services.builder.PageableBuilder;

public class RestParamHelper {

    private final static Integer DEFAULT_PAGE = 0;
    private final static Integer DEFAULT_PER_PAGE = 100;
    private final static String SORT_DESC_PREFIX = "-";
    
    private static final Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
    
    private Integer page = DEFAULT_PAGE;
    private Integer perPage = DEFAULT_PER_PAGE;
    private String embeded = null;
    private String sortBy = null;
    private Set<String> allowedSortBy = Sets.newHashSet();
    private Set<String> allowedEmbed = Sets.newHashSet();
    
    public RestParamHelper specifyPaging(Integer page, Integer perPage) {
        if(page != null) {
            if(page < 0) { 
                throw new WrongParamException(ErrorBuilder.build(ErrorDescribedEnum.PAGE_PARAM_IS_NEGATIVE, page));
            }
            this.page = page;
        }
        
        if(perPage != null) {
            if(perPage <= 0) {
                throw new WrongParamException(ErrorBuilder.build(ErrorDescribedEnum.PER_PAGE_SHOULD_BE_POSITIVE, perPage));
            }
            this.perPage = perPage;
        }
        
        return this;
    }
    
    public RestParamHelper withEmbeded(String embeded) {
        this.embeded = embeded;
        return this;
    }
    
    public RestParamHelper withSorting(String sort) {
        this.sortBy = sort;
        return this;
    }
    
    public RestParamHelper allowSortBy(String ... allowSortBy) {
        if(allowSortBy != null) {
            this.allowedSortBy = Sets.newHashSet(allowSortBy);
        }
        return this;
    }
    
    public RestParamHelper allowEmbed(String ... allowEmbed) {
        if(allowEmbed != null) {
            this.allowedEmbed = Sets.newHashSet(allowEmbed);
        }
        return this;
    }

    public Pageable buildPageable() {
        PageableBuilder builder = new PageableBuilder().page(page).size(perPage);
        addSorting(builder);
        return builder.build();
    }
    
    private void addSorting(PageableBuilder builder) {
        List<String> sortByFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(sortBy));
        Collection<String> invalidSortBy = Collections2.filter(sortByFields, new Predicate<String>() {
            @Override
            public boolean apply(String isAllowed) {
                return !allowedSortBy.isEmpty() && !allowedSortBy.contains(skipFieldSortDirection(isAllowed));
            }
        });
        if(invalidSortBy.size() > 0) {
            throw new WrongParamException(ErrorBuilder.build(ErrorDescribedEnum.INVALID_SORT_PARAM, Joiner.on(",").join(invalidSortBy)));
        }
        
        for(String sortByField : sortByFields) {
            if(!Strings.isNullOrEmpty(sortByField)) {
                builder.addSort(getFieldSortDirection(sortByField), skipFieldSortDirection(sortByField));
            }
        }
    }
    
    private Direction getFieldSortDirection(String field) {
        return Strings.isNullOrEmpty(field) ? Direction.ASC 
                : field.startsWith(SORT_DESC_PREFIX) ? Direction.DESC : Direction.ASC;
    }
    
    private String skipFieldSortDirection(String field) {
        return Strings.nullToEmpty(field).startsWith(SORT_DESC_PREFIX) ?
                field.substring(1) : field;
    }

    public static Pageable defaultPageable() {
        return new PageableBuilder().page(DEFAULT_PAGE).size(DEFAULT_PER_PAGE).build();
    }
    
    public String[] buildEmbeded() {

        List<String> embededFields = COMMA_SPLITTER.splitToList(Strings.nullToEmpty(embeded));
        Collection<String> invalidEmbeded = Collections2.filter(embededFields, new Predicate<String>() {
            @Override
            public boolean apply(String isAllowed) {
                return !allowedEmbed.isEmpty() && !allowedEmbed.contains(isAllowed);
            }
        });
        if(invalidEmbeded.size() > 0) {
            throw new WrongParamException(ErrorBuilder.build(ErrorDescribedEnum.INVALID_EMBEDED_PARAM, Joiner.on(",").join(invalidEmbeded)));
        }
        return embededFields.toArray(new String[0]);
    }
}
