package com.timsmeet.services.mapper;

public interface SourceChildAccess<S, SC> {

    SC getChild(S source);
}
