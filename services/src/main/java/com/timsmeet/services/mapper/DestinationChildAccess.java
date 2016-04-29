package com.timsmeet.services.mapper;

import java.lang.reflect.Type;

public interface DestinationChildAccess<D, DC> extends SourceChildAccess<D, DC> {

    void setChild(D destination, DC child);

    Type getChildType();
}
