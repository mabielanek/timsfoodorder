package com.timsmeet.services.mapper;

import java.lang.reflect.Type;

import com.timsmeet.dto.entity.BaseEntity;

public interface OneToOneConversionAccess<S, D, SC extends BaseEntity, DC> {

    SC getSourceChild(S source);
    DC getDestinationChild(D destination);
    void setDestincationChild(D destination, DC child);
    Type getDestinationChildType();
}
