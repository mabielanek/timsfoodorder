package com.timsmeet.services.mapper;

import java.lang.reflect.Type;

public interface OneToOneConversionAccess<S, D, SC, DC> {

    SC getSourceChild(S source);
    DC getDestinationChild(D destination);
    void setDestincationChild(D destination, DC child);
    Type getDestinationChildType();
}
