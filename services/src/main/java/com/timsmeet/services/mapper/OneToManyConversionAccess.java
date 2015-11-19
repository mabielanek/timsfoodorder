package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

import com.timsmeet.dto.entity.BaseEntity;

public interface OneToManyConversionAccess<S, D, SC extends BaseEntity, DC> {
    Collection<SC> getSourceChilds(S source);

    Collection<DC> getDestinationChilds(D destination);

    Long getSouceChildId(SC child);

    Long getDestinationChildId(DC child);

    void removeDestinationChild(D parent, DC child);

    void addDestinationChild(D parent, DC child);

    Type getDestinationChildType();

}
