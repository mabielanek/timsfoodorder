package com.timsmeet.services.mapper;

import java.lang.reflect.Type;

public interface DestinationCollectionAccess<ID, D, DC> extends SourceCollectionAccess<ID, D, DC> {

    void removeChild(D parent, DC child);

    void addChild(D parent, DC child);

    Type getChildType();

}
