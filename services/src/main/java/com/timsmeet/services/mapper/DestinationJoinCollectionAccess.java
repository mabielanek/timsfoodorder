package com.timsmeet.services.mapper;

import java.util.Collection;

public interface DestinationJoinCollectionAccess<ID, D, DC, JC> {

    Collection<JC> getJoinChilds(D destination);

    ID getChildIdFromJoin(JC join);

    void removeJoin(D parent, JC joinChild);

    void createAndAddJoin(D parent, DC destinationChild);

    DC findDbChild(ID childId);
}
