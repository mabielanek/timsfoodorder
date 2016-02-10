package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

public interface OneToManyConversionAccess<S, D, SC, DC> {
    Collection<SC> getSourceChilds(S source);

    Collection<DC> getDestinationChilds(D destination);

    Long getSouceChildId(SC child);

    Long getDestinationChildId(DC child);

    void removeDestinationChild(D parent, DC child);

    void addDestinationChild(D parent, DC child);

    Type getDestinationChildType();

}
