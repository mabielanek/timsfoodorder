package com.timsmeet.services.mapper;

import java.util.Collection;

public interface SourceCollectionAccess<ID, S, SC> {

    Collection<SC> getChilds(S source);
    ID getChildId(SC child);
}
