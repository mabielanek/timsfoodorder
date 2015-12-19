package com.timsmeet.services.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableBuilder {

    private int page = 0;
    private int size = 100;
    private Sort sort = null;
    
    public PageableBuilder page(int page) {
        this.page = page;
        return this;
    }
    
    public PageableBuilder size(int size) {
        this.size = size;
        return this;
    }
    
    public PageableBuilder addSort(Direction direction, String property) {
        if(sort == null) {
            sort = new Sort(new Sort.Order(direction, property));
        } else {
            sort = sort.and(new Sort(new Sort.Order(direction, property)));
        }
        return this;
    }
    
    public Pageable build() {
        return new PageRequest(page, size, sort);
    }
}
