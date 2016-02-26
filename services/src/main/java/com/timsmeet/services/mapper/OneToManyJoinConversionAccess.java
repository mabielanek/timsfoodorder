package com.timsmeet.services.mapper;

import java.util.Collection;

public interface OneToManyJoinConversionAccess <S, D, SC, DC, JC> {
        Collection<SC> getSourceChilds(S source);

        Collection<JC> getJoinChilds(D destination);

        Long getSouceChildId(SC child);

        Long getDestinationChildIdFromJoin(JC join);

        void removeJoinChild(D parent, JC joinChild);

        void createAndAddJoinChild(D parent, DC destinationChild);

        DC findDbDestinationChild(Long childId);


}
